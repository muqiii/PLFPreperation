package db;

import jakarta.ws.rs.*;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jwt.JWTNeeded;
import pojos.Product;

import java.io.IOException;


@Path("/shoppingcart")
public class ShoppingCartResource {

   @Context
   public ContainerRequestContext context;

   @POST
   @Consumes
   @JWTNeeded
   public Response createShoppingCart(){
      String mail = context.getProperty("mail").toString();
      ShoppingCartDB.getTheInstance().createShoppingCart(mail);
      return Response.ok(ShoppingCartDB.getTheInstance().getShoppingCart(mail)).build();
   }

   @GET
   @Produces
   @JWTNeeded
   public Response getShoppingCart(){
      try {
         String mail = context.getProperty("mail").toString();

         return Response.ok(ShoppingCartDB.getTheInstance().getShoppingCart(mail)).build();
      }catch (Exception e){
         return Response.status(Response.Status.NOT_FOUND).build();
      }
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
