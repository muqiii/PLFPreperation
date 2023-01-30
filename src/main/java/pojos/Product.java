package pojos;

import lombok.Data;

@Data
public class Product {
    private int id;
    private String title;
    private String description;
    private Float price;
    private Float discountPercentage;
    private Float rating;
    private int stock;
    private String brand;
    private String category;
    private String thumbnail;
    private String[] images;
}
