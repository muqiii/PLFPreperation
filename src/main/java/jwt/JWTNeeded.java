package jwt;

import jakarta.ws.rs.NameBinding;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@NameBinding
public @interface JWTNeeded {
}
