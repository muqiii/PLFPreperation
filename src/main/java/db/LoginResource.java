package db;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import pojos.User;

@Path("/login")
public class LoginResource {

    public static String SECRET = "secret";

    private String createJWT(String mail){
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        return JWT.create()
                .withClaim("mail", mail)
                .sign(algorithm);
    }
    @POST
    @Consumes("application/json")
    public Response login(@QueryParam("mail") String mail , @QueryParam("password") String password) {

        if(UserDB.getTheInstance().getPassword(mail).equals(password)) {
            String token = createJWT(mail);
            return Response.status(200).header("Authorization", token).build();
        }
        else{
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

    }
}

