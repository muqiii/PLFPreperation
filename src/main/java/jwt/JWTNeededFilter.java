package jwt;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import db.LoginResource;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

import java.io.IOException;

@Provider
@JWTNeeded
public class JWTNeededFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        String authorizationHeader = containerRequestContext.getHeaderString("Authorization");

        if(authorizationHeader == null || authorizationHeader.isEmpty()){
            containerRequestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        }
        try{
        Algorithm algorithm = Algorithm.HMAC256(LoginResource.SECRET);
        JWTVerifier verifier = JWT.require(algorithm).build();
        authorizationHeader = authorizationHeader.substring("Bearer".length()).trim();

        DecodedJWT decodedJWT = verifier.verify(authorizationHeader);

        String mail = decodedJWT.getClaim("mail").asString();

        containerRequestContext.setProperty("mail", mail);

        }catch (Exception e){
        containerRequestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        }

    }
}
