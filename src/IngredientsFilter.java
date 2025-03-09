public class IngredientsFilter implements Filter{

    private String ingredient; 

    public IngredientsFilter(String ingredient) {
        this.ingredient = ingredient;
    }

    //IF TRUE, IF THERE IS AT LEAST ONE INGREDIENT PRESENT IN HASHMAP<STRING, COSMETICS>
    @Override
    public boolean satisfies(String barcode) {
        String[] ingredientList = ingredient.split(",");
        for(int i = 0; i < ingredientList.length; i++) {
            if (CosmeticsDatabase.getIngredients(barcode).contains(ingredientList[i].trim())) {
                return true;
           }
        }
        return false;
    }

}
