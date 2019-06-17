import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@WrittenBy(nameOfCreator = "Finn Zimmer", date ="14.12.2018")
@EditedBy(editedBy = "Michael Landauer", edit= "fixed the annualPurge() - stream")
/**
 * Represents a Population
 */
public class Population {
    /**
     * Contains all Persons of the population
     */
    private ArrayList<Person> people;

    /**
     * Returns the all Persons of the population as ArrayList
     * @return ArrayList which contains all persons
     */
    public ArrayList<Person> getPeople() {
        return people;
    }

    /**
     * Constructor: Generates a Population with n Persons.
     * @param n number of persons
     * @param sizeWishes stands for the range of Wishes from 0 to sizeWishes
     */
    public Population(int n, int sizeWishes){
        people = new ArrayList<>();
        Stream.generate(() -> new Person(sizeWishes)).limit(n).forEach(x -> people.add(x));

    }

    /**
     * Removes a random number of persons from the population and adds a random number of new person.
     * @param sizeWishes stand for the range of Wishes from 0 to sizeWishes
     */
    public void annualPurge(int sizeWishes){
        List<Person> SurvivingPeople = people.stream().filter(x -> Math.random() > 0.2f).collect(Collectors.toList());
        people.clear();
        people.addAll(SurvivingPeople);
        IntStream.range(0,(int) (Math.random()*10) + (int) (people.size() * 0.3f)).forEach(x -> people.add(new Person(sizeWishes)));
    }
}
