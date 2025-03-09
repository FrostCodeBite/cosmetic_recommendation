public class PriceFilter implements Filter{

    private double minPrice;
    private double maxPrice;

    public PriceFilter(double minPrice, double maxPrice) {
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
    }

    @Override
    public boolean satisfies(String barcode) {
        double price = CosmeticsDatabase.getPrice(barcode);
        return (minPrice <= price) && (price <= maxPrice);
    }

}
