package nvp3.backendnvp3.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import nvp3.backendnvp3.authentication.IAuthService;
import nvp3.backendnvp3.entities.*;
import nvp3.backendnvp3.services.UserService;
import nvp3.backendnvp3.services.VacuumCleanerService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Path("/vacuumCleaner")
public class VacuumCleanerController {
    @EJB
    private VacuumCleanerService vacuumCleanerService;
    @EJB
    private UserService userService;
    @EJB
    private IAuthService authService;


    @GET
    @Path("/findForUser")
    @Produces(MediaType.APPLICATION_JSON)
    public List<VacuumCleaner> getAllVacuumeCleaners(@HeaderParam("Authorization") String auth)
    {
        int id = authService.getId(auth);
        return vacuumCleanerService.getAllVacuumCleanersForUser(id);
    }
    @POST
    @Path("/search")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchVacuumCleaners(@HeaderParam("Authorization") String auth,SearchRequest request) {
        if(authService.isAuthorized(auth,"can_search_vacuum")) {
            int id = authService.getId(auth);
            List<VacuumCleaner> result = vacuumCleanerService.searchVacuumCleaners(
                    request.getName(), request.getStatusList(), request.getDateFrom(), request.getDateTo(), id
            );
            return Response.ok(result).build();
        }
        return Response.status(403).build();

    }



    @POST
    @Path("/schedule")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response scheduleActivity(String requestBody) {
        try {
            ObjectMapper objectMapper=new ObjectMapper();
            RequestData requestData = objectMapper.readValue(requestBody, RequestData.class);

            LocalDateTime dateTime = LocalDateTime.parse(requestData.getDateTimeString());
            Date date = Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
            String action = requestData.getAction();
            int id = requestData.getId();

            vacuumCleanerService.scheduleActivity(date,action,id);
            return Response.ok().build();
        } catch (DateTimeParseException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Invalid date and time format. Please use ISO format.").build();
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }



    @POST
    @Path("/addVacuumCleaner")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addVacuumCleaner( @HeaderParam("Authorization") String auth, Map<String, String> payload ) {
        if(authService.isAuthorized(auth,"can_add_vacuum"))
        {
            String name = payload.get("name");
            VacuumCleaner vc = new VacuumCleaner();
            vc.setUsed(0);
            vc.setBusy(false);
            vc.setActive(true);
            vc.setAddedBy(userService.findById(authService.getId(auth)));
            vc.setStatus(Status.OFF);
            vc.setName(name);
            vc.setVersion(0);
            Date d=new Date();
            vc.setCreateDate(d);
            vc = vacuumCleanerService.add(vc);
            if(vc == null) {
                return Response.status(400).build();
            }
            return Response.ok(vc).build();
        }
        return Response.status(403).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response removeVacuumCleaner(@HeaderParam("Authorization") String auth,@PathParam("id") int id) {
        if(authService.isAuthorized(auth,"can_remove_vacuums")) {
            return Response.ok(vacuumCleanerService.myRemoveById(id)).build();
        }
        return Response.status(403).build();
    }

    @POST
    @Path("/start/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response startVacuumCleaner(@HeaderParam("Authorization") String auth,@PathParam("id") int id)  {
        if(authService.isAuthorized(auth,"can_start_vacuum")) {
            int pom=vacuumCleanerService.startVacuumCleaner(id);
            System.out.println(pom);
            if(pom==0)
                return Response.ok().build();
            else {
                vacuumCleanerService.errorOfVacuumCleaner(id,"START",new Date(),authService.getId(auth),pom);
                return Response.status(403).build();
            }
        }

        return Response.status(403).build();
    }

    @POST
    @Path("/stop/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response stopVacuum(@HeaderParam("Authorization") String auth,@PathParam("id") int id)  {
        if(authService.isAuthorized(auth,"can_stop_vacuum")) {
            int pom=vacuumCleanerService.stopVacuumCleaner(id);
            if(pom==0)
                return Response.ok().build();
            else {
                vacuumCleanerService.errorOfVacuumCleaner(id,"STOP",new Date(),authService.getId(auth),pom);
                return Response.status(403).build();
            }
        }
        return Response.status(403).build();
    }


    @POST
    @Path("/discharge/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response dischargeVacuum(@HeaderParam("Authorization") String auth,@PathParam("id") int id)  {
        if(authService.isAuthorized(auth,"can_discharge_vacuum")) {
            int pom=vacuumCleanerService.dischargeVacuumCleaner(id);
            if(pom==0)
                return Response.ok().build();
            else {
                vacuumCleanerService.errorOfVacuumCleaner(id,"DISCHARGE",new Date(),authService.getId(auth),pom);
                return Response.status(403).build();
            }
        }
        return Response.status(403).build();
    }


}
