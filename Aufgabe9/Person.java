
import java.util.*;
import java.util.stream.*;

import static java.util.Collections.reverseOrder;

@WrittenBy(nameOfCreator = "Finn Zimmer", date= "14.12.2018")
@EditedBy(editedBy = "Michael Landauer", edit="cleanup of the influence() Method")
/**
 * Represents a person
 */
public class Person {

    /**
     * Wishes of the person
     */
    private YeTreasureMap Wishmap;
    /**
     * Resistance against marketing. Ratio between 0 and 1. 0 means marketing has no affect to this person.
     */
    private double resistance;

    /**
     * Constructor
     * @param sizeWishes stand for the range of Wishes from 0 to sizeWishes
     */
    public Person(int sizeWishes){
        Wishmap = new YeTreasureMap(sizeWishes);
        Random g = new Random();

       IntStream temp = IntStream.range(0,sizeWishes).filter(x -> g.nextGaussian() > 2.8);
       temp.forEach(x -> Wishmap.put(x, (g.nextDouble() - 0.5) * 100));

       resistance = g.nextDouble();
    }

    public YeTreasureMap getWishmap() {
        return Wishmap;
    }

    /**
     * The strength of the wish by this person will be changed in both direction (neg./pos.) randomly.
     * If the person does not have the wish, it will be added to this person. The strength of the added
     * wish is random.
     * @param wish which should be influenced
     */
    public void influence(int wish){
        double oldWishStrength = Wishmap.getWishStrength(wish);

        if(oldWishStrength == 0.0){
            Wishmap.put(wish, 100.0*resistance);
        }
        else{
            Random g = new Random();

            //random double between 0 and 2.
            //[0;1) means, the influencer has a different intention than the person (e.g. influencer wants to promote negatively, person likes it)
            //[1;2] means, the influencer has the same intentions as the person (e.g. both hate the product, both like it)
            Double intention = 0 + (2 - 0) * g.nextDouble();
            Wishmap.put(wish, oldWishStrength*intention*resistance);
        }
    }

    /**
     * Returns the top 5 wishes of the person.
     * @return ArrayList which contains the Top 5 wishes
     */
    public ArrayList<Integer> get5largest(){
       ArrayList<Integer> help = new ArrayList<>();
       Wishmap.getMap().entrySet().stream().sorted(reverseOrder(Map.Entry.comparingByValue())).limit(5).collect(Collectors.toList()).forEach(x -> help.add(x.getKey()));
       return help;
    }
}
