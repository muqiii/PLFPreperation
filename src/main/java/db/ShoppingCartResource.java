package db;

import jakarta.ws.rs.*;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jwt.JWTNeeded;
import pojos.Product;
import pojos.ShoppingCart;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.io.IOException;


@Path("/shoppingcart")
public class ShoppingCartResource {
   @Context
   public ContainerRequestContext context;

   @POST
   @Consumes("application/json")
   @JWTNeeded
   public Response createShoppingCart(){
      String mail = context.getProperty("mail").toString();
      try{
         ShoppingCartDB.getTheInstance().createShoppingCart(mail);
      } catch (KeyAlreadyExistsException | IOException exception) {
         return Response.status(Response.Status.CONFLICT).build();
      }
      return Response.ok(ShoppingCartDB.getTheInstance().getShoppingCart(mail)).build();
   }

   @GET
   @Produces("application/json")
   @JWTNeeded
   public ShoppingCart getShoppingCart(){
      String mail = context.getProperty("mail").toString();

      return ShoppingCartDB.getTheInstance().getShoppingCart(mail);
   }

   @PATCH
   @JWTNeeded
   public Response addProductToShoppingCart(@QueryParam("id")int id) throws IOException {

      String mail = context.getProperty("mail").toString();

      Product product = ProductDB.getTheInstance().getProductById(id);

      ShoppingCartDB.getTheInstance().getShoppingCart(mail).addProduct(product);

      return Response.ok(ShoppingCartDB.getTheInstance().getShoppingCart(mail)).build();
   }
}