package nvp3.backendnvp3.controllers;

import nvp3.backendnvp3.authentication.IAuthService;
import nvp3.backendnvp3.entities.ErrorMessage;
import nvp3.backendnvp3.entities.SearchRequest;
import nvp3.backendnvp3.entities.VacuumCleaner;
import nvp3.backendnvp3.services.ErrorMessageService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/errorMessage")
public class ErrorMessageController {

    @EJB
    private ErrorMessageService errorMessageService;
    @EJB
    private IAuthService authService;


    @GET
    @Path("/findAll")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchVacuumCleaners(@HeaderParam("Authorization") String auth) {
        if(authService.isAuthorized(auth,"can_search_vacuum")) {
            int id = authService.getId(auth);
            List<ErrorMessage> lista = errorMessageService.getAllErrorMessagesForUserId(id);
            return Response.ok(lista).build();
        }
        return Response.status(403).build();

    }

}
