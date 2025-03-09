public class BrandFilter implements Filter{

    private String brand; 

    public BrandFilter(String brand) {
        this.brand = brand;
    }

    @Override
    public boolean satisfies(String barcode) {
        String[] brandList = brand.split(",");
        for(int i = 0; i < brandList.length; i++) {
            if (CosmeticsDatabase.getBrand(barcode).contains(brandList[i].trim())) {
                return true;
           }
        }
        return false;
    }

}
