import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;
import java.util.stream.IntStream;
import java.util.stream.Stream;


@WrittenBy(nameOfCreator = "Michael Landauer", date ="15.12.2018")
/**
 * Represents a Organization
 */
public class Organization {
    /**
     * Reference to the population
     */
    private Population PopulationReference;
    /**
     * List of products which the Organization sells. Have the same number as the wish.
     */
    private ArrayList<Integer> products;
    /**
     * Reference to the WishList
     */
    private WishList WishListReference;

    /**
     * Constructor: Generates random products in the range of numberOfWishes
     * @param numberOfWishes stands for the range of Wishes from 0 to sizeWishes
     * @param InputPopulation Reference to the population
     * @param InputWishList Referenct to the wishlist
     */
    public Organization(int numberOfWishes, Population InputPopulation, WishList InputWishList){
        this.PopulationReference = InputPopulation;
        this.products = new ArrayList<>();
        this.WishListReference = InputWishList;

        IntStream.range(0,numberOfWishes).filter(x -> Math.random() < 0.02f).forEach(y -> this.products.add(y));
    }

    /**
     * Influences the wishes of populations. It will only promotes products which are not in the wishset,
     * so it will not negative influence the top5 of the people.
     */
    public void influence(){
        Stream<Person> personStream = this.PopulationReference.getPeople().stream();
        TreeSet<Integer> WishSet = this.WishListReference.getWishSet();

        int[] filteredSet = WishSet.stream().filter(x -> products.contains(x)).mapToInt(e -> e).toArray();

        personStream.forEach(x -> Arrays.stream(filteredSet).forEach(x::influence));
    }

    public ArrayList<Integer> getProducts() {
        return products;
    }
}
