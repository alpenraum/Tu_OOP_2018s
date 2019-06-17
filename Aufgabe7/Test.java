/**
 * Finn: Class hierarchy, visitor pattern, labor methods, zusicherungen, testing
 * Landauer: Class hierarchy, implementation class hierarchy, zusicherungen, testing
 */
public class Test {
    /**
     * testing lab1 : normal
     * testing lab2 : no vivariums
     * testing lab3 : extreme case (1 optimal vivarium in between 120 non optimal (but usable) vivariums)
     */

    public static void main(String[] args) {

        //labor 1
        System.out.println("///////////////LABOR 1///////////////");
        Labor testingLabor1 = new Labor();
        testLabor(testingLabor1, 1);

        System.out.println("################ \n Check if inventarliste and Schwarmliste-vivariums are distinct (must be done manually)");
        testingLabor1.schwarmliste();
        testingLabor1.inventarliste();

        System.out.println("///////////////LABOR 2///////////////");
        Labor testingLabor2 = new Labor();
        testLabor(testingLabor2, 2);

        System.out.println("################ \n Check if inventarliste and Schwarmliste-vivariums are distinct (must be done manually)");
        testingLabor2.schwarmliste();
        testingLabor2.inventarliste();

        System.out.println("///////////////LABOR 3///////////////");
        Labor testingLabor3 = new Labor();
        testLabor(testingLabor3, 3);


    }

    private static void testLabor(Labor testingLabor, int testingCase) {
        String report = "Automatic Error report for case " + testingCase + ": \n";
        int countCorrect = 0;

        if (testingCase == 1) {

            Vivarium eA = new ExpensiveAquarium(15, 15, 15);
            testingLabor.neu(eA);
            Vivarium eT = new ExpensiveTerrarium(30, 30, 30);
            testingLabor.neu(eT);
            CheapAquarium cA = new CheapAquarium(10, 10, 10);
            testingLabor.neu(cA);
            Terrarium cT = new CheapTerrarium(5, 5, 5);
            testingLabor.neu(cT);

            Swarm cAS = new CheapAquaSwarm("TestingCAS1");
            AquaSwarm cAS2 = new CheapAquaSwarm("TestingCAS2");
            CheapTerraSwarm cTS = new CheapTerraSwarm("TestingCAS3");
            Swarm eAS = new ExpensiveAquaSwarm("TestingEAS1");

            Vivarium v1 = testingLabor.stellebereit(cAS);
            Vivarium v3 = testingLabor.stellebereit(cAS2);
            Vivarium v4 = testingLabor.stellebereit(eAS);
            //should return null
            Vivarium v2 = testingLabor.stellebereit(cTS);

            if (v1 == cA) {
                countCorrect++;
            } else {
                report = report + "" + "- \tSwarm cAS wurde nicht richtig (korrekt wäre: CheapAquarium cA) zugeteilt. \n";
            }

            if (v3 == eA) {
                countCorrect++;
            } else {
                report = report + "" + "- \tSwarm cAS wurde nicht richtig (korrekt wäre: CheapAquarium cA) zugeteilt. \n";
            }

            if (v4 == null) {
                countCorrect++;

            } else {
                report = report + "" + "- \tSwarm eAS wurde nicht richtig (korrekt wäre : null) zugeteilt. \n";
            }

            if (v2 == cT) {
                countCorrect++;
            } else {
                report = report + "" + "- \tSwarm cTS wurde nicht richtig (korrekt wäre : CheapTerarium cT) zugeteilt. \n";
            }

            testingLabor.retourniere(cAS);
            testingLabor.retourniere(cAS);


            Vivarium v5 = testingLabor.stellebereit(eAS);
            Vivarium v6 = testingLabor.stellebereit(cAS2);
            Vivarium cA2 = new CheapAquarium(7, 7, 7);
            testingLabor.neu(cA2);


            if (v5 == null) {
                countCorrect++;
            } else {
                report = report + "" + "- \tSwarm eAS wurde nicht richtig (korrekt wäre : null) zugeteilt. \n";
            }
            if (v6 == null) {
                countCorrect++;
            } else {
                report = report + "" + "- \tSwarm cAS2 wurde doppelt eingefügt \n";
            }


            if (countCorrect == 6) System.out.println("Zuweisungen und Auswahl der Vivarien ist korrekt");


            System.out.print("belegtes Volumen muss 2125 sein. Hier ist es: ");
            testingLabor.volumenbelegt();
            System.out.print("freies Volumen muss 27000 sein. Hier ist es: ");
            testingLabor.volumenfrei(); //must be 27000
            System.out.println("if the volume is negative, it is the fault of the coder who did not read the documentation of any of the " +
                    "Vivarium-classes");
        } else if (testingCase == 2) {


            Swarm cAS3 = new CheapAquaSwarm("TestingCAS3");
            AquaSwarm cAS4 = new CheapAquaSwarm("TestingCAS4");
            Swarm eTS = new ExpensiveTerraSwarm("TestingETS");
            Swarm eAS2 = new ExpensiveAquaSwarm("TestingEAS2");
            TerraSwarm eTS2 = new ExpensiveTerraSwarm("TestingETS2");

            Vivarium v7 = testingLabor.stellebereit(cAS3);
            Vivarium v8 = testingLabor.stellebereit(cAS4);
            Vivarium v9 = testingLabor.stellebereit(eTS);
            Vivarium v10 = testingLabor.stellebereit(eAS2);
            testingLabor.retourniere(eTS2);
            Vivarium v11 = testingLabor.stellebereit(eTS2);

            if (v7 == null) {
                countCorrect++;
            } else {
                report = report + "" + "- \tSwarm cAS3 wurde nicht richtig (korrekt wäre: null) zugeteilt. \n";
            }

            if (v8 == null) {
                countCorrect++;
            } else {
                report = report + "" + "- \tSwarm cAS4 wurde nicht richtig (korrekt wäre: null) zugeteilt. \n";
            }

            if (v9 == null) {
                countCorrect++;

            } else {
                report = report + "" + "- \tSwarm eTS wurde nicht richtig (korrekt wäre : null) zugeteilt. \n";
            }

            if (v10 == null) {
                countCorrect++;
            } else {
                report = report + "" + "- \tSwarm eAS2 wurde nicht richtig (korrekt wäre : null) zugeteilt. \n";
            }
            if (v11 == null) {
                countCorrect++;
            } else {
                report = report + "" + "- \tSwarm eTS2 wurde nicht richtig (korrekt wäre : null) zugeteilt. \n";
            }

            //throws null-exception at volumenfrei / -belegt if this inserts null-vivariums into inventarliste
            testingLabor.retourniere(cAS3);
            testingLabor.retourniere(cAS4);


            if (countCorrect == 5) System.out.println("Zuweisungen und Auswahl der Vivarien ist korrekt");


            System.out.print("belegtes Volumen muss 0 sein. Hier ist es: ");
            testingLabor.volumenbelegt();
            System.out.print("freies Volumen muss 0 sein. Hier ist es: ");
            testingLabor.volumenfrei(); //must be 27000
            System.out.println("if the volume is negative, it is the fault of the coder who did not read the documentation of any of the " +
                    "Vivarium-classes");

        } else if (testingCase == 3) {

            for (int i = 0; i < 67; i++) {
                testingLabor.neu(new ExpensiveTerrarium(1, 1, 1));
            }
            Vivarium cT1 = new CheapTerrarium(1, 1, 1);
            testingLabor.neu(cT1);
            for (int i = 0; i < 67; i++) {
                testingLabor.neu(new ExpensiveTerrarium(1, 1, 1));
            }
            Swarm cTS = new CheapTerraSwarm("TestingCase3CTS");

            Vivarium v31= testingLabor.stellebereit(cTS);

            if(v31 == cT1){
                countCorrect++;
            }else{
                report = report + "" + "- \tSwarm cTS wurde nicht richtig (korrekt wäre : CheapTerarium cT1) zugeteilt. \n";
            }

            if (countCorrect == 1) System.out.println("Zuweisungen und Auswahl der Vivarien ist korrekt");


        }
        System.out.println("######################");
        System.out.println(report);
    }
}
