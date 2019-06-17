import java.util.ArrayList;

public class Test {

    /**
     * Aufgabenteilung:
     *  Finn: Aufbau/Beschreibung der Untertypenbeziehung (mittels Klassendiagramm), Zusicherungen, Testklassen
     *  Michael: Aufbau/Beschreibung der Untertypenbeziehung (mittels Klassendiagramm), Zusicherungen, Erstellung der Tests
     */

    /**
     * Begründung für nicht existente Untertypenbeziehungen:
     *      HerdAnimal steht nicht in einer Untertypenbeziehung mit Mammal bzw. FlightlessBird, da dies zu Widersprüchen führen würde. Ein HerdAnimal
            vom Obertyp Mammal kann überall leben, während ein FlightlessBird nicht in der Luft sein kann.
            2 mögliche Lösungen für dieses Problem wären, entweder zwei Untertypen von HerdAnimal (eine für Mammal, eine für FlightlessBird)
            zu erstellen und damit Mammal von FlightlessBird getrennt zu haben, oder dies mit Zusicherungen auszuschließen(siehe HerdAnimal).
            Wir haben uns für das zweite entschieden, da in der Angabe gegeben ist, dass möglichst wenige weitere Untertypen verwendet werden sollen.

     *       FlightlessBird steht in keiner Untertypenbeziehung mit SwarmAnimal, da es auch flugunfähige Vögel gibt, die nicht in einem Schwarm
             leben (z.B. Strauß)

     *       FlightlessBird steht nicht in Beziehung mit MigratoryBird, da dies zu einem Widerspruch führen würde. FlightlessBird kann nicht
             fliegen, MigratoryBird dagegen schon

     *      Bird, Mammal, Insect und Fish stehen in keiner Untertypenbeziehung, da diese Typen distinkte Mengen sind. Es gibt kein Lebewesen,
            welches gleichzeitig zur Klasse der Säugetiere und Insekten gehört. (bspw.: Schnabeltier hat zwar Merkmale von Vögeln und Säugetieren,
            wird aber trotzdem eindeutig den Säugetieren zugeordnet) Dementsprechend stehen auch keine Untertypen dieser Klassen in Beziehung
            zueinander. (Eine Beziehung wäre biologisch falsch; bspw.: MigratoryBird ist ein Untertyp von Fish => Ein Zugvogel
            ist ein Fisch (und ein Vogel)

     *       PackAnimal, SwarmAnimal, SchoolAnimal stehen in keiner direkten Untertypbeziehung, da diese Klassifizierungen disjunkt sind.
     *       SwarmAnimal, School Animal, HerdAnimal stehen in keiner direkten Untertypbeziehung, da diese Klassifizierungen disjunkt sind.
     *
     *       SwarmAnimal ist kein Untertyp einer Spezies (bzw. ein Untertyp davon), weil SwarmAnimal ein Tier jeder Spezies sein kann.
     *       PackAnimal/SchoolAnimal ist kein Untertyp einer anderen Spezies, da PackAnimal/SchoolAnimal schon von Mammal erbt.
     *
     *
     *       Bird, Fish, Mammal, Insect (oder deren Untertypen bis auf MigratoryLocust und MigratoryBird) erben nicht von SocialAnimal (bzw. Untertypen davon),
     *       da es in jeder Klasse auch Gattungen gibt, welche in keinem sozialen Verbund leben.
     *
     *       SocialAnimal ist nur ein Untertyp von Anmimal, da ein SocialAnimal ein Tier einer spezifischen Gattung sein kann.
     *       Würde SozialAnimal ein Untertyp von einer spezifischen Gattung sein, wäre das ein Widerspruch mit dem Kontext. Auch
     *       ist SocialAnimal kein Untertyp von School-, Pack-, Herd-, und SwarmAnimal da SocialAnimal ein Sozialverband(Schwarm, Herde,
     *       Schule, etc.) sein kann.

     */
    public static void main(String[] args) {
        EurasianCurlew TestCurlew = new EurasianCurlew();
        FeralPigeon TestPigeon = new FeralPigeon();
        Goat TestGoat = new Goat();
        GoblinShark TestShark = new GoblinShark();
        Locust TestLocust = new Locust();
        Pinguin TestPinguin = new Pinguin();
        Whale TestWhale = new Whale();
        Wolf TestWolf = new Wolf();

        Animal[] AllAnimals = {TestCurlew, TestPigeon, TestGoat, TestShark, TestLocust, TestPinguin, TestWhale, TestWolf};

        /**Obertyp Animal wird von der Methode verlangt, da jede Tiergattung(Mammal, Bird, Fish, Insect)
         * ein Untertyp von Animal ist Ersetzbarkeit gegeben.**/
        for (Animal a:
             AllAnimals) {
            printAnimal(a);
        }

        /**
         * TestPinguin(Klasse Pinguin) ist Untertyp von HerdAnimal und FlightlessBird,
         * und kann somit nur zu einer Obertyp davon gecastet werden.
         **/
        // Mammal CastMammal = (Mammal) TestPinguin;
        // Insect CastInsect = (Insect) TestPinguin;
        // Fish CastFish = (Fish) TestPinguin;
        Bird CastBird = (Bird) TestPinguin;
        Animal CastAnimal = (Animal) TestPinguin;

        /**
         * Jedes Tier welches in einem Sozialverband lebt, sei es SchoolAnimal, PackAnimal, HerdAnimal, SwarmAnimal
         * kann auf SocialAnimal gecastet werden und in späterer Folge auf Animal.
         */
        SocialAnimal CastSA1 = (SocialAnimal) TestWhale;
        SocialAnimal CastSA2 = (SocialAnimal) TestWolf;
        SocialAnimal CastSA3 = (SocialAnimal) TestGoat;
        SocialAnimal CastSA4 = (SocialAnimal) TestCurlew;

        Animal CastAnimal1 = (Animal) CastSA1;
        Animal CastAnimal2 = (Animal) CastSA2;
        Animal CastAnimal3 = (Animal) CastSA3;
        Animal CastAnimal4 = (Animal) CastSA4;

        /**
         * Unter den gegebenen Kontext kann MigratoryBird nur eine flugunfähiger Vogel sein der in einem Schwarm lebt.
         */
        Bird CastMigratoryBird = (Bird) TestCurlew;
        SwarmAnimal CastMigratoryBird2 = (SwarmAnimal) TestCurlew;
        // FlightlessBird CastMigratoryBird3 = (FlightlessBird) TestCurlew;
        // Insect CastMigratoryBird4 = (Insect) TestCurlew;
        // HerdAnimal CastMigratoryBird5 = (HerdAnimal) TestCurlew;
        SocialAnimal CastMigratoryBird6 = (SocialAnimal) TestCurlew;

        /**
         * MigratoryLocust ähnlich wie MigratoryBird.
         */
        Insect CastMigratoryLocust = (Insect) TestLocust;
        SwarmAnimal CastMigratoryLocust2 = (SwarmAnimal) TestLocust;
        // Mammal CastMigratoryLocust4 = (Mammal) TestLocust;
        // HerdAnimal CastMigratoryLocust5 = (HerdAnimal) TestLocust;
        SocialAnimal CastMigratoryLocust6 = (SocialAnimal) TestLocust;

        /**
         * PackAnimal ähnlich zu MigratoryLocust, MigratoryBird.
         */
        Mammal CastPackAnimal = (Mammal) TestWolf;
        PackAnimal CastPackAnimal2 = (PackAnimal) TestWolf;
        HerdAnimal CastPackAnimal3 = (HerdAnimal) TestWolf;
        // Fish CastPackAnimal4 = (Fish) TestWolf;
        // SchoolAnimal CastPackAnimal5 = (SchoolAnimal) TestWolf;
        SocialAnimal CastPackAnimal6 = (SocialAnimal) TestWolf;

    }

    private static void printAnimal(Animal inputAnimal){
        System.out.println(inputAnimal.toString());
    }
}
