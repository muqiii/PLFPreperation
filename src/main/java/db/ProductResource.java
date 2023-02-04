package db;


import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import pojos.Product;

import java.io.IOException;
import java.util.List;

@Path("/products")
public class ProductResource {

    @GET
    @Produces("application/json")
    public List<Product> getAllProducts(){
        try {
            return ProductDB.getTheInstance().getProduct().getProducts();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
