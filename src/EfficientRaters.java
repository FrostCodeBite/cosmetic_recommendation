import java.util.ArrayList;
import java.util.HashMap;

public class EfficientRaters implements Raters {

    private String myID;
    HashMap<String, Rating> myHM;

    public EfficientRaters(String myID) {
        this.myID = myID;
        myHM = new HashMap<String, Rating>();
    } 

    //ADD NEW RATER INTO HASHMAP<STRING, RATER> WHICH RATER HERE REFER TO HASHMAP<STRING, RATING>
    public void addRating(String item, double rating) {
        Rating rate = new Rating(item, rating);
        myHM.put(item, rate);
    }

    //CHECK IF STRING ALSO EXIST IN HASHMAP<STRING, RATING>
    public boolean hasRating(String item) {        
        return myHM.containsKey(item);
    }

    //GET RATINGSCORE FROM HASHMAP<STRING, RATER>
    public double getRating(String item) {
        if (myHM.containsKey(item)) {
            return myHM.get(item).getValue();
        }
        
        return -1;
    }

    //RETURN BARCODE
    public String getID() {
        return myID;
    }

    //RETURN RATING SIZE
    public int numRatings() {
        return myHM.size();
    }

    //RETURN BARCODE ARRAYLIST
    public ArrayList<String> getItemsRated() {
        ArrayList<String> list = new ArrayList<String>();
        for (String barcode : myHM.keySet()) {
            list.add(barcode);
        }
        return list; 
    }  
}
