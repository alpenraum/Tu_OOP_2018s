import java.util.Iterator;

/**
 * Finn Zimmer: [First attempt at Iterator/Container (not used)], Interfaces, Tierklassen, move(), compareAll(),Predicates,
 * Zusicherungen/Documentation
 * Michael Landauer: Implementierung Interator, SocialGroup, Container Klasse, Tests
 *
 */

public class Test {

    public static void main(String[] args){
        /** 1 **/

        SocialGroup<Starling> StarlingGroup = new SocialGroup<>();
        SocialGroup<Zebra> ZebraGroup = new SocialGroup<>();
        SocialGroup<Ostrich> OstrichGroup = new SocialGroup<>();
        SocialGroup<SteppeHerdAnimal> SteppeHerdGroup = new SocialGroup<>();

        for (int i = 0; i < 5; i++) {
            int r = (int) ((Math.random()-0.5f) * 200);
            boolean rB = (int) (Math.random() + 0.5f) == 1;
            Starling temp = new Starling(r, rB);
            StarlingGroup.add(temp);
        }

        for (int i = 0; i < 10; i++) {
            int r = (int) ((Math.random()-0.5f) * 200);
            float r1 = (float) Math.random();
            boolean rB = (int) (Math.random() + 0.5f) == 1;
            Zebra temp = new Zebra(r, rB, r1);
            ZebraGroup.add(temp);
        }
        Zebra TestZebra = new Zebra(30, true, 0.99f);
        ZebraGroup.add(TestZebra);

        for (int i = 0; i < 11; i++) {
            int r = (int) ((Math.random()-0.5f) * 200);
            int r1 = (int) ((Math.random()) * 30) + 5;
            Ostrich temp = new Ostrich(r,r1);
            OstrichGroup.add(temp);
        }
        Ostrich TestOstrich = new Ostrich(35, 25);
        OstrichGroup.add(TestOstrich);

        System.out.println("+++ StarlingGroup +++");
        for (Starling i:
                StarlingGroup) {
            System.out.print(i + "  ");
        }
        System.out.println("\n" + "+++ ZebraGroup +++");
        for (Zebra i :
                ZebraGroup) {
            System.out.print(i + "  ");
        }
        System.out.println("\n" + "+++ OstrichGroup +++");
        for (Ostrich i :
                OstrichGroup) {
            System.out.print(i + "  ");
        }

        OstrichGroup.compareAll(3);
        ZebraGroup.compareAll(2);
        StarlingGroup.compareAll(4);

        OstrichGroup.compareAll(1);
        ZebraGroup.compareAll(2);
        StarlingGroup.compareAll(3);

        System.out.println("\n\n" + "##### After Competition ######");

        System.out.println("+++ StarlingGroup +++");
        for (Starling i:
                StarlingGroup) {
            System.out.print(i + "  ");
        }
        System.out.println("\n" + "+++ ZebraGroup +++");
        for (Zebra i :
                ZebraGroup) {
            System.out.print(i + "  ");
        }
        System.out.println("\n" + "+++ OstrichGroup +++");
        for (Ostrich i :
                OstrichGroup) {
            System.out.print(i + "  ");
        }

        /** Iterator over empty SocialGroup (Check for Nullpointer Exception) **/
        for (SteppeHerdAnimal i :
                SteppeHerdGroup) {
            System.out.print(i + "  ");
        }

        System.out.println("\n\n\n" + "+++ Zebra alpha iterator +++");
        Iterator<Zebra> ZebraIter = ZebraGroup.alpha();
        while(ZebraIter.hasNext()){
            System.out.print(ZebraIter.next() + "  ");
        }

        /** 2 **/
        Zebra.setPredCondition(0.5f);
        Ostrich.setPredCondition(10);
        SteppeHerdGroup.move(ZebraGroup, Zebra.testZebra());
        SteppeHerdGroup.move(OstrichGroup, Ostrich.testOstrich());

        System.out.println("\n\n\n" + "+++ SteppeHerdGroup +++");
        /** Remove all Animals from SteppeHerdAnimal where getFitness returns 35 or 30 **/
        for (SteppeHerdAnimal i :
              SteppeHerdGroup) {
            System.out.print(i + "  ");
            if(i.getFitness() == 35 || i.getFitness() == 30){
                SteppeHerdGroup.remove(i);
            }
        }

        /** 3 **/

        System.out.println("\n\n\n" + "+++ SteppeHerdGroup after removing +++");
        for (SteppeHerdAnimal i :
                SteppeHerdGroup) {
            System.out.print(i + "  ");
        }
        System.out.println("\n" + "Check hierarchical: " + SteppeHerdGroup.hierarchical());
        SteppeHerdGroup.compareAll(5);

        System.out.println("\n\n" + "+++ SteppeHerdGroup after Competition +++");
        for (SteppeHerdAnimal i :
                SteppeHerdGroup) {
            System.out.print(i + "  ");
        }

        System.out.println("\n\n\n" + "+++ SteppeHerdGroup alpha iterator +++");
        Iterator<SteppeHerdAnimal> testIter = SteppeHerdGroup.alpha();
        while(testIter.hasNext()){
            System.out.print(testIter.next() + "  ");
        }
    }
}
