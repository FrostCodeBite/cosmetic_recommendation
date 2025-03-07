import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import edu.duke.FileResource;

public class RatersDatabase {

    private static HashMap<String, Raters> ourRaters; 

    //CONNECT WITH DATABASE WITH PROVIDED FILENAME
    public static void initialize(String raterFile) {
        if (ourRaters == null) {
            ourRaters = new HashMap<String, Raters>();
            // loadMovies("data/" + cosmeticFile);
        }
    }

    //CONNECT WITH DATABASE WITH EXISTING FILENAME
    public static void initialize() {
        if (ourRaters == null) {
            ourRaters = new HashMap<String, Raters>();
            addRatings("RaterDataset.csv");
        }
    }

    //CALL FOR DATABASE AND ADD DATA
    public static void addRatings(String raterFile) {
        FileResource fr = new FileResource(raterFile);
        CSVParser parser = fr.getCSVParser();
        for (CSVRecord record: parser) {
            String raterID = record.get("RaterID").trim();
            String barcode = record.get("Barcode").trim();
            double ratingScore = Double.parseDouble(record.get("RatingScore").trim());
            addRaterRating(raterID, barcode, ratingScore);
        }
    }

    //ADD RATERID AS STRING AND RATING AS CLASS OBJ FOR HASHMAP<STRING, RATER> 
    public static void addRaterRating(String raterID, String barcode, double rating) {
        Raters rater =  null;
                //CHECK IF RATERID ALREADY EXIST, THEN GET PREVIOUS RATING INFORMATION
                if (ourRaters.containsKey(raterID)) {
                    rater = ourRaters.get(raterID); 
                } 
                //IF NOT EXIST, CREATE A NEW RATERID
                else { 
                    rater = new EfficientRaters(raterID);
                    ourRaters.put(raterID,rater);
                }
                //ADD NEW RATING
                rater.addRating(barcode,rating);
    } 

    //CHECK RATER SIZE 
    public static int size() {
        return ourRaters.size();
    }

    //RETURN RATER_ID
    public static Raters getRater(String id) {
        return ourRaters.get(id);
    }

    //RETURN RATER_ID ARRAYLIST
    public static ArrayList<Raters> getRaters() {
        ArrayList<Raters> list = new ArrayList<Raters>(ourRaters.values());
        return list;
    }

    /* THIS IS FOR TESTING PURPOSE */
    // public static void main(String[] args) {
    //     RatersDatabase obj = new RatersDatabase();
    //     obj.initialize();
    //     int totalRating = 0;
    //     for (String id : ourRaters.keySet()) {
    //         System.err.println(id+" "+ourRaters.get(id).getItemsRated());
    //         totalRating += ourRaters.get(id).getItemsRated().size();
    //     }
    //     System.err.println("TOTAL RATER ARE: "+ourRaters.size());
    //     System.err.println("TOTAL RATING ARE: "+totalRating);
    // }
}
