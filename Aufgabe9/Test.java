import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.stream.IntStream;
@WrittenBy(nameOfCreator = "Michael Landauer", date ="16.12.2018")
@EditedBy(editedBy = "Finn Zimmer", edit="Ausgabe der Annotations")
public class Test {
    public static void main(String[] args){


        System.out.println("#####Class Annotations#####################");
        System.out.println("AspectPrinter Class");
        Arrays.stream(AspectPrinter.class.getAnnotations()).forEach(System.out::println);
        System.out.println("Organization Class: ");
        Arrays.stream(Organization.class.getAnnotations()).forEach(System.out::println);
        System.out.println("Person Class: ");
        Arrays.stream(Person.class.getAnnotations()).forEach(System.out::println);
        System.out.println("Population Class: ");
        Arrays.stream(Population.class.getAnnotations()).forEach(System.out::println);
        System.out.println("Test Class: ");
        Arrays.stream(Test.class.getAnnotations()).forEach(System.out::println);
        System.out.println("WishList Class: ");
        Arrays.stream(WishList.class.getAnnotations()).forEach(System.out::println);
        System.out.println("YeTreasureMap Class: ");
        Arrays.stream(YeTreasureMap.class.getAnnotations()).forEach(System.out::println);
        System.out.println("###########################################");



        int numberOfWishes = 1000;

        Population P = new Population(1200,numberOfWishes);
        WishList W = new WishList(P);

        Organization O1 = new Organization(numberOfWishes, P, W);
        Organization O2 = new Organization(numberOfWishes, P, W);
        Organization O3 = new Organization(numberOfWishes, P, W);
        Organization[] allO = {O1, O2, O3};



        IntStream.range(0,10).forEach(x -> oneYear(allO, W, P, numberOfWishes));

    }

    private static void oneYear(Organization[] input, WishList W, Population P, int numOfWishes){
        W.calculateNewList();
        Arrays.stream(input).forEach(Organization::influence);
        P.annualPurge(numOfWishes);
    }

}
