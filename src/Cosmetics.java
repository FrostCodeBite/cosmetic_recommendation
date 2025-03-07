public class Cosmetics {
    
    private String barcode; 
    private String imageURL; 
    private String productName;
    private String category;
    private String brand;
    private double price;
    private String ingredients;
    private String skinCondition;
    private String additionalProperties;
    private double ratingScore;

    public Cosmetics(String barcode, String imageURL, String productName, String category, String brand, double price,
            String ingredients, String skinCondition, String additionalProperties, double ratingScore) {
        this.barcode = barcode;
        this.imageURL = imageURL;
        this.productName = productName;
        this.category = category;
        this.brand = brand;
        this.price = price;
        this.ingredients = ingredients;
        this.skinCondition = skinCondition;
        this.additionalProperties = additionalProperties;
        this.ratingScore = ratingScore;
    }

    public String getBarcode() {
        return barcode;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getProductName() {
        return productName;
    }

    public String getCategory() {
        return category;
    }

    public String getBrand() {
        return brand;
    }

    public double getPrice() {
        return price;
    }

    public String getIngredients() {
        return ingredients;
    }

    public String getSkinCondition() {
        return skinCondition;
    }

    public String getAdditionalProperties() {
        return additionalProperties;
    }

    public double getRatingScore() {
        return ratingScore;
    }

    @Override
    public String toString() {
        return "Cosmetics [barcode=" + barcode + ", imageURL=" + imageURL + ", productName=" + productName
                + ", category=" + category + ", brand=" + brand + ", price=" + price + ", ingredients=" + ingredients
                + ", skinCondition=" + skinCondition + ", additionalProperties=" + additionalProperties
                + ", ratingScore=" + ratingScore + "]";
    }
}  
