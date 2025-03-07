import java.util.ArrayList;
import java.util.Collections;

public class CosmeticRating {

    public CosmeticRating() {
        CosmeticsDatabase.initialize();
        RatersDatabase.initialize();
    }

    //CALCULATE THE DOTPRODUCT (CLOSENESS) BETWEEN ME AND ANOTHER PERSON
    public double dotProduct(Raters me, Raters r) {
        ArrayList<String> meItemList = me.getItemsRated();
        ArrayList<String> rItemList = r.getItemsRated();
        double productSum = 0.0;

        for (int i = 0; i < meItemList.size(); i++) {
            String meCurrentItem = meItemList.get(i);
            if (rItemList.contains(meCurrentItem)) {
                productSum += (me.getRating(meCurrentItem)-5) * (r.getRating(meCurrentItem)-5);
            }
        }

        return productSum;
    }

    //RETRIEVE RATING ARRAYLIST OF SIMILARITIES (CLOSENESS) BETWEEN ME AND OTHERS
    public ArrayList<Rating> getSimilarities(String rateID) {
        ArrayList<Rating> similar = new ArrayList<Rating>();

        Raters me = RatersDatabase.getRater(rateID);
        for (int i = 0; i < RatersDatabase.size(); i++) {
            Raters r = RatersDatabase.getRaters().get(i);
            double product = dotProduct(me, r);
            //CHECK IF CLOSENESS >= 0
            if (product > 0) {
                //CHECK IF MY BARCODE THAT I RATE EQUAL TO ANOTHER PERSON BARCODE THAT RATED
                if (!me.equals(r)) {
                    similar.add(new Rating(r.getID(), product));
                }   
            }
        }
        //REVERSE ORDER FROM LARGEST TO SMALLEST SIMILARITYSCORE
        Collections.sort(similar, Collections.reverseOrder());
        return similar;
    }

    public ArrayList<Rating> getSimilarRatings(String raterID, int numSimilarRaters, int minimalRaters) {
        ArrayList<Rating> ratings = new ArrayList<Rating>();

        ArrayList<Rating> raterSimilar = getSimilarities(raterID);
        ArrayList<String> cosmetics = CosmeticsDatabase.filterBy(new TrueFilter());

        for (int i = 0; i < cosmetics.size(); i++) {
            String barcode = cosmetics.get(i);
            double totalWeight = 0.0;
            double weight = 0.0;
            int n = 0;

            for (int j = 0; j < numSimilarRaters; j++) {
                //CHECK IF THERE IS ANY SIMILARITY, IF NO RETURN EMPTY ARRAYLIST 
                if (raterSimilar.size() == 0) {
                    return ratings;
                }
                String rater_id = "";
                double similarScore = 0.0;
                //TRY CATCH TO PREVENT PUTING HIGH NUMBER OF NumSimilarRaters WHICH EXCEED NUMBER OF RaterSimilar ARRAYLIST
                try {
                    rater_id = raterSimilar.get(j).getItem();
                    similarScore = raterSimilar.get(j).getValue();
                } catch (Exception e) {
                    break;
                }
                double ratingScore = 0.0;
                if (RatersDatabase.getRater(rater_id).getRating(barcode) != -1) {
                    ratingScore = RatersDatabase.getRater(rater_id).getRating(barcode);
                    // FORMULAR: WEIGHT = SUM(AVGRATING*SIMILARRATING)/TOTAL NUMBER OF RATERS;
                    weight += similarScore * ratingScore;
                    n++;
                }            
            }
            //WEIGHT ONLY CALCULATE IF THERE IS ENOUGH MININAL NUMBER OF RATERS
            if (n >= minimalRaters) {
                totalWeight = weight/n;
            }
            //ONLY ADD BARCODE THAT HAS MORE THAN 0 SIMILARITY SCORE
            if (totalWeight != 0) {
                ratings.add(new Rating(barcode, totalWeight));
            }
        }
        //REVERSE ORDER FROM LARGEST TO SMALLEST SIMILARITYSCORE
        Collections.sort(ratings, Collections.reverseOrder());
        return ratings;
    }

    /* THIS IS FOR TESTING PURPOSE */
    // public static void main(String[] args) {
    //     CosmeticRating obj = new CosmeticRating();
    //     ArrayList<Rating> rating = obj.getSimilarRatings("7",10, 1);
    //     if (rating.size() == 0) {
    //         System.err.println("There is no Cosmestics that is recommended here!");
    //     }
    //     for (int i = 0; i < rating.size(); i++) {
    //         double score = rating.get(i).getValue();
    //         if (score != 0) {
    //             Rating rate = rating.get(i);
    //             System.err.println(i+" "+rate.getItem()+" "+rate.getValue());
    //         }
    //     }
    // }
}
