
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;


@WrittenBy(nameOfCreator = "Finn Zimmer", date = "17.12.2018")
public aspect AspectPrinter {
    private int year = 1;

    /**
     * PointCut for oneYear() Method
     */
    public pointcut Test2(Organization[] input, WishList W, Population P, int numOfWishes):
            execution(*  Test.oneYear(..)) && args(input,W,P,numOfWishes);



    after(Organization[] input, WishList W, Population P, int numOfWishes): Test2(input,W,P,numOfWishes){
        System.out.println("Jahr: " + year);
        System.out.println("Bevölkerungsgröße: " + P.getPeople().size());
        System.out.println("Durchschnittliche Wünsche pro Person: " + computeAvgWishes(P));
        System.out.println("Durchschnittliche Positive Stärke der Wünsche: " + computeAvgStrengthPos(P));
        System.out.println("Durchschnittliche Negative Stärke der Wünsche: " + computeAvgStrengthNeg(P));
        for(int i=0;i<input.length;i++) {
            System.out.println("Durchschnittliche Anzahl der Wünsche von O" +i+" auf der Wunschliste: "+ computeAvgOrganizationWishes(input[i], W));
        }
        year++;
    }





    /**
     * Calculates the average Number of Wishes over the whole Population
     * @param p The Population this average should be calculated for
     * @return average Wishes (double)
     */
    private double computeAvgWishes(Population p) {
        double divider = p.getPeople().size();
        if (divider == 0.0) {
            return 0;
        }

        ArrayList<Long> numberList = new ArrayList<>();
        p.getPeople().forEach(x -> numberList.add(countWishes(x)));

        int sum = numberList.stream().mapToInt(Math::toIntExact).sum();
        return sum / divider;
    }

    /**
     * counts the number of wishes of one Person
     * @param p Person, whose Wishes should be counted
     * @return number of Wishes (long)
     */
    private long countWishes(Person p) {
        HashMap<Integer, Double> map = p.getWishmap().getMap();
        Long temp = map.entrySet().stream().filter(x -> x.getValue() != 0).count();
        return temp;
    }


    /**
     * Calculates the average positive Strength of Wishes per Population
     * @param p Population
     * @return average positive Strength of Wishes (Double)
     */
    private double computeAvgStrengthPos(Population p) {
        double divider = p.getPeople().size();
        if (divider == 0.0) {
            return 0;
        }

        ArrayList<Double> numberList = new ArrayList<>();
        p.getPeople().forEach(x -> numberList.add(countStrengthAndAvg(x, true)));

        double sum = numberList.stream().filter(x -> !Double.isNaN(x)).reduce(0.0, (x, y) -> x + y);
        return sum / numberList.size();

    }
    /**
     * Calculates the average negative Strength of Wishes per Population
     * @param p Population
     * @return average negative Strength of Wishes (Double)
     */
    private double computeAvgStrengthNeg(Population p) {
        double divider = p.getPeople().size();
        if (divider == 0.0) {
            return 0;
        }

        ArrayList<Double> numberList = new ArrayList<>();
        p.getPeople().forEach(x -> numberList.add(countStrengthAndAvg(x, false)));

        double sum = numberList.stream().filter(x -> !Double.isNaN(x)).reduce(0.0, (x, y) -> x + y);
        return sum / numberList.size();

    }

    /**
     * Calculates the average Strength of all Wishes of this Person
     * @param p Person
     * @param positive if True, only positive Strength will be considered
     *                 if False, only negative Strength will be considered
     * @return average Strength of all Wishes of this Person
     */
    private double countStrengthAndAvg(Person p, boolean positive) {
        HashMap<Integer, Double> map = p.getWishmap().getMap();
        int size = map.size();
        double temp = 0l;
        if (positive) {
            temp = map.entrySet().stream().filter(x -> x.getValue() > 0).mapToDouble(Map.Entry::getValue).sum();
        } else {
            temp = map.entrySet().stream().filter(x -> x.getValue() < 0).mapToDouble(Map.Entry::getValue).sum();
        }

        return temp / size;
    }

    /**
     * Calculates the average number of times a Product of an Organization is in the Wishlist
     * @param o Organization which Wishes should be calculated
     * @param w Wishlist which should be used for calculation
     * @return Average number of times, one Product of this Organization is inside the WishList
     */
    private double computeAvgOrganizationWishes(Organization o, WishList w){
        ArrayList<Integer> productList=o.getProducts();

       long count=  w.getWishSet().stream().filter(productList::contains).count();
       return (double)count/(double)productList.size();
    }
}
