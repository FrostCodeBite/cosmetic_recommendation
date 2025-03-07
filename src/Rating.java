public class Rating implements Comparable<Rating>{

    private String item;
    private double value;

    public Rating (String anItem, double aValue) {
        item = anItem;
        value = aValue;
    }

    // RETURN ITEM BEING RATED
    public String getItem () {
        return item;
    }

    // RETURN THE VALUE OF THIS RATING
    public double getValue () {
        return value;
    }

    // RETURN STRING OF ALL INFORMATION
    public String toString () {
        return "[" + getItem() + ", " + getValue() + "]";
    }

    public int compareTo(Rating other) {
        if (value < other.value) return -1;
        if (value > other.value) return 1;
        
        return 0;
    }
}
