import java.util.*;
import java.util.stream.Stream;

@WrittenBy(nameOfCreator = "Finn Zimmer", date ="14.12.2018")
@EditedBy(editedBy = "Michael Landauer", edit= "added calculateNewList(); fixed the stream")
/**
 * Represents a WishList which contains Wishes of a Population
 */
public class WishList {
    /**
     * Reference to the population
     */
    private Population PopulationReference;
    /**
     * WishSet which contains a Set of all Top5 wishes of the population
     */
    private TreeSet<Integer> wishSet;

    /**
     * Constructor
     * @param p reference to the population
     */
    public WishList(Population p){
        this.PopulationReference = p;
        this.wishSet = new TreeSet<>();

        calculateNewList();
    }

    /**
     * Calculates a Set of all Top5 wishes of the population and stores it.
     */
    public void calculateNewList(){
        this.wishSet.clear();
        Stream<Person> peopleStream = this.PopulationReference.getPeople().stream();
        peopleStream.forEach(x -> wishSet.addAll(x.get5largest()));
    }

    /**
     *
     * @return WishSet as TreeSet
     */
    public TreeSet<Integer> getWishSet(){
        return wishSet;
    }


}
