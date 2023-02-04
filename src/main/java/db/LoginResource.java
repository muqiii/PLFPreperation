package db;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import pojos.User;

@Path("/login")
public class LoginResource {


    public static String SECRET = "secretlul";
    public String createJWT(String mail) {
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        return JWT.create().withClaim("mail", mail).sign(algorithm);
    }

    @POST
    @Consumes("application/json")
    public Response login(@HeaderParam("mail") String mail, @HeaderParam("password") String password) {
        if (password.equals(UserDB.getTheInstance().getPassword(mail))) {
            String jwt = createJWT(mail);
            return Response.status(204).header("Authorization", jwt).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

}
