package nvp3.backendnvp3.controllers;


import javax.ejb.EJB;
import javax.ejb.PostActivate;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


import nvp3.backendnvp3.authentication.IAuthService;
import nvp3.backendnvp3.entities.User;
import nvp3.backendnvp3.entities.VacuumCleaner;
import nvp3.backendnvp3.services.UserService;
import org.mindrot.jbcrypt.BCrypt;

@Path("/users")
public class UserController {

    @EJB
    private UserService userService;
    @EJB
    private IAuthService authService;


    @GET
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(@QueryParam("email") String email, @QueryParam("password") String password) {
        System.out.println(this.userService);
        User user = userService.findUser(email, password);
        if (user != null) {
            JsonObject json = Json.createObjectBuilder()
                    .add("jwt", authService.generateJWT(user))
                    .build();
            return Response.ok(json).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("/readUsers")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getReadUsers(@HeaderParam("Authorization") String auth)
    {
        if(authService.isAuthorized(auth,"can_read_users"))
        {
            return Response.ok(userService.getAll()).build();
        }
        return Response.status(403).build();
    }

    @GET
    @Path("/canReadUsers")
    public Response getCanReadUsers(@HeaderParam("Authorization") String auth)
    {
        if(authService.isAuthorized(auth,"can_read_users"))
        {
            return Response.status(200).build();
        }
        return Response.status(403).build();
    }

    @GET
    @Path("/vacuum/canSearch")
    public Response getcanSearchVacuumeCleaner(@HeaderParam("Authorization") String auth)
    {
        if(authService.isAuthorized(auth,"can_search_vacuum"))
        {
            return Response.status(200).build();
        }
        return Response.status(403).build();
    }

    @GET
    @Path("/vacuum/canAdd")
    public Response getCanAddVacuumeCleaner(@HeaderParam("Authorization") String auth)
    {
        if(authService.isAuthorized(auth,"can_add_vacuum"))
        {
            return Response.status(200).build();
        }
        return Response.status(403).build();
    }

    @POST
    @Path("/createUsers")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addUser( @HeaderParam("Authorization") String auth, User user ) {
        if(authService.isAuthorized(auth,"can_create_users"))
        {
            user = userService.add(user);
            if(user == null) {
                return Response.status(400).build();
            }
            return Response.ok(user).build();
        }
        return Response.status(403).build();
    }

    @GET
    @Path("/canCreate")
    @Produces(MediaType.APPLICATION_JSON)
    public Response canAddUser( @HeaderParam("Authorization") String auth ) {
        if(authService.isAuthorized(auth,"can_create_users"))
        {
            return Response.ok().build();
        }
        return Response.status(403).build();
    }

    @GET
    @Path("/canDelete")
    @Produces(MediaType.APPLICATION_JSON)
    public Response canDeleteUser( @HeaderParam("Authorization") String auth ) {
        if(authService.isAuthorized(auth,"can_delete_users"))
        {
            return Response.ok().build();
        }
        return Response.status(403).build();
    }


    @GET
    @Path("/canUpdate")
    @Produces(MediaType.APPLICATION_JSON)
    public Response canUpdateUser( @HeaderParam("Authorization") String auth) {
        if(authService.isAuthorized(auth,"can_update_users"))
        {
            return Response.ok().build();
        }
        return Response.status(403).build();
    }

    @POST
    @Path("/updateUsers")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUser( @HeaderParam("Authorization") String auth, User user ) {
        if(authService.isAuthorized(auth,"can_update_users"))
        {
            boolean res = userService.update(user);
            return Response.ok(res).build();
        }
        return Response.status(403).build();
    }


    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response remove(@HeaderParam("Authorization") String auth,@PathParam("id") int id) {
        if(authService.isAuthorized(auth,"can_delete_users")) {
            userService.removeById(id);
            return Response.ok().build();
        }
        return Response.status(403).build();
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getUsers() {
        return userService.getAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getUserById(@PathParam("id") int id) { // Da bi se u tacan argument prosledio id mora da se oznaci anotacijom
        return userService.findById(id);
    }







}
