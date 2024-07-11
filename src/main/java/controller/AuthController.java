package controller;

import java.util.Map;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import service.AuthService;

@Path("/sign_in")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthController {
  private final AuthService authService;

  public AuthController(AuthService authService) {
    this.authService = authService;
  }

  @POST
  @Transactional 
  public Response login(Map<String, String> requestBody) {
    String document = requestBody.get("document");
    String password = requestBody.get("password");

    if (document == null || password == null) {
      return Response.status(Response.Status.BAD_REQUEST).entity("Document and password are required").build();
    }
    
    boolean authenticated = authService.authenticate(document, password);
    if (authenticated) {
        return Response.ok().build();
    } else {
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }
  }

}
