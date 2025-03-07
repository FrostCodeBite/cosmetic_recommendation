import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import edu.duke.FileResource;

public class CosmeticsDatabase {
    
    private static HashMap<String, Cosmetics> ourCosmetics; 

    //CONNECT WITH DATABASE WITH PROVIDED FILENAME
    public static void initialize(String cosmeticFile) {
        if (ourCosmetics == null) {
            ourCosmetics = new HashMap<String, Cosmetics>();
            // loadMovies("data/" + cosmeticFile);
        }
    }

    //CONNECT WITH DATABASE WITH EXISTING FILENAME
    public static void initialize() {
        if (ourCosmetics == null) {
            ourCosmetics = new HashMap<String, Cosmetics>();
            loadCosmetics("CosmeticDataset.csv");
        }
    }

    //ADD BARCODE & COSMETICS ARRAYLIST INTO HASHMAP<STRING, COSMETICS>
    public static void loadCosmetics(String cosmeticFile) {
        ArrayList<Cosmetics> list = loadCosmeticList(cosmeticFile);
        for (Cosmetics m : list) {
            ourCosmetics.put(m.getBarcode(), m);
        }
    }

    //CALL FOR DATABASE AND ADD DATA INTO COSMETIC ARRAYLIST
    public static ArrayList<Cosmetics> loadCosmeticList(String filename) {
        ArrayList<Cosmetics> cosmetics = new ArrayList<Cosmetics>();

        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser();
        for (CSVRecord record: parser) {
            String barcode = record.get("Barcode").trim();
            String imageURL = record.get("ImageLink").trim(); 
            String productName = record.get("ProductName").trim();
            String category = record.get("Category").trim();
            String brand = record.get("Brand").trim();
            double price = Double.parseDouble(record.get("Price").trim());
            String ingredients = record.get("Ingredients").trim();
            String skinCondition = record.get("SkinCondition").trim();
            String additionalProperties = record.get("AdditionalProperties").trim();
            double ratingScore = Double.parseDouble(record.get("RatingScore").trim());
            cosmetics.add(new Cosmetics(barcode, imageURL, productName, category, brand, 
                    price, ingredients, skinCondition, additionalProperties, ratingScore));
        }
        return cosmetics;
    }

    //CHECKING HASHMAP<STRING, COSMETICS> SIZE
    public static int size() {
        return ourCosmetics.size();
    }

    //CHECK IF COSMETIC INFO SATISTY CONDITION, THEN RETURN BARCODE ARRAYLIST
    public static ArrayList<String> filterBy(Filter f) {
        ArrayList<String> list = new ArrayList<String>();
        for(String id : ourCosmetics.keySet()) {
            if (f.satisfies(id)) {
                list.add(id);
            }
        }
        return list;
    }

    /* THIS IS FOR TESTING PURPOSE */
    // public static void main(String[] args) {
    //     CosmeticsDatabase obj = new CosmeticsDatabase();
    //     obj.initialize();
    //     for (String id : ourCosmetics.keySet()) {
    //         System.err.println(ourCosmetics.get(id));
    //     }
    //     System.err.println("TOTAL COSMETICS ARE: "+ ourCosmetics.size());
    // }
}
