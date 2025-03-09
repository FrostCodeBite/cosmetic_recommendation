import java.util.ArrayList;
import java.util.Collections;

public class CosmeticIngredients {

    public CosmeticIngredients() {
        CosmeticsDatabase.initialize();
    }

    public ArrayList<Cosmetics> getFiltering(String ingredient, String brand, double minPrice, double maxPrice) {
        ArrayList<Cosmetics> results = new ArrayList<Cosmetics>();

        AllFilters af = new AllFilters();
        if (!ingredient.equals("")) {
            af.addFilter(new IngredientsFilter(ingredient));
        }
        if (minPrice > 0 && maxPrice > 0) {
            af.addFilter(new PriceFilter(minPrice, maxPrice));
        }
        if (!brand.equals("")) {
            af.addFilter(new BrandFilter(brand));
        } else {
            af.addFilter(new TrueFilter());
        }

        ArrayList<String> cosmetics = CosmeticsDatabase.filterBy(af);
        for (int i = 0; i < cosmetics.size(); i++) {
            results.add(CosmeticsDatabase.getCosmetics(cosmetics.get(i)));
        }
        Collections.sort(results, Collections.reverseOrder());
        return results;
    }

    /* THIS IS FOR TESTING PURPOSE */
    // public static void main(String[] args) {
    //     CosmeticIngredients obj = new CosmeticIngredients();
    //     ArrayList<Cosmetics> cosmeticList = obj.getFiltering("Water, Myristic Acid", "Cosrx, Skin1004", 1.0, 10.0);
    //     for (int i = 0; i < cosmeticList.size(); i++) {
    //         String barcode = cosmeticList.get(i).getBarcode();
    //         String name = cosmeticList.get(i).getProductName();
    //         double price = cosmeticList.get(i).getPrice();
    //         System.err.println(barcode+" "+price+" "+name);
    //     }
    // }

}
