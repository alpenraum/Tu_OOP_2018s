public class Test {
    public static void main(String[] args) {
        /**
         * Finn: Ranks (alphaRank, betaRank), MaleAnimal (Reformatierung, so dass kein Code doppelt vorkommt), FemaleAnimal ((Reformatierung, so
         * dass kein Code doppelt vorkommt), abstract Class Animal (auf Basis von (now deleted) @Deprecated AnimalInterface), Pack, AllPacks,
         * abstractPack(um
         * Codewiederholung zu vermeiden)
         *
         * Landauer: First Version of Male & FemaleAnimals (which imp. a interface Animal), Container(ListNode, DLinkedList, Iterator),
         * HormonValue and homogenization.
         */


        /**
         * Tests
         */

        MaleAnimal Male1 = new MaleAnimal(1, 111);
        MaleAnimal Male2 = new MaleAnimal(2, 222);
        MaleAnimal Male3 = new MaleAnimal(3, 333);
        MaleAnimal Male4 = new MaleAnimal(4, 444);

        Male1.addAdrenalinValue(11,0.1f);
        Male1.addCortisolValue(11,0.2f);
        Male2.addAdrenalinValue(12,0.2f);
        Male2.addCortisolValue(22,0.155f);
        Male3.addAdrenalinValue(13,0.3f);
        Male2.addCortisolValue(33,0.764f);

        FemaleAnimal Female6 = new FemaleAnimal(6, 666);
        FemaleAnimal Female7 = new FemaleAnimal(7, 777);
        FemaleAnimal Female8 = new FemaleAnimal(8, 888);
        FemaleAnimal Female9 = new FemaleAnimal(9, 999);

        Female6.addAdrenalinValue(11,0.1f);
        Female6.addCortisolValue(11, 0.666f);
        Female7.addAdrenalinValue(12,0.2f);
        Female7.addCortisolValue(33, 0.61231321321f);
        Female8.addAdrenalinValue(13,0.3f);

        Male1.setRank(new alphaRank(100));

        //Packs & Allpacks
        Pack packMF = new Pack("pack1");
        packMF.add(Male1);
        packMF.add(Female6);
        packMF.add(Female7);

        Pack packM = new Pack("pack2");
        packM.add(Male2);
        packM.add(Male3);
        packM.add(Male3);
        packM.add(Male4);

        Pack packF = new Pack("pack3");
        packF.add(Female8);
        packF.add(Female9);

        Pack packE = new Pack("pack4");

        AllPacks allPacks1 = new AllPacks("allpacks1");
        allPacks1.add(packMF);
        allPacks1.add(packM);
        allPacks1.add(packF);
        allPacks1.add(packE);

        System.out.println(allPacks1.getPack("pack2").getName());
        System.out.println(allPacks1.getPack("pack2").getAnimal(8));
        System.out.println(allPacks1.getPack("pack1").getAnimal(1));
        System.out.println(allPacks1.getPack("pack3").getAnimal(8));

        allPacks1.remove("pack4");
        allPacks1.remove("pack2");
        allPacks1.add(packM);

        MaleAnimal Male22 = new MaleAnimal(22, 2444);
        FemaleAnimal Female44 = new FemaleAnimal(44, 2444);


        System.out.println(allPacks1.getPack("pack1").getAnimal(6));
        System.out.println(((FemaleAnimal) allPacks1.getPack("pack1").getAnimal(6)).getNumberOfChildren());
        FemaleAnimal tmp = (FemaleAnimal) allPacks1.getPack("pack1").getAnimal(6);
        tmp.setNumberOfChildren(100);
        System.out.println(((FemaleAnimal) allPacks1.getPack("pack1").getAnimal(6)).getNumberOfChildren());

        allPacks1.getPack("pack2").add(Male22);
        allPacks1.getPack("pack3").add(Female44);

        allPacks1.getPack("pack2").remove(4);

        AllPacks allPacksE = new AllPacks("allpacks2");

        System.out.println("\n//////ALLPACKS1/////////\n");


        System.out.println(">>>STATISTICS");
        allPacks1.print();

        System.out.println("\n//////allPacksE/////////\n");

        System.out.println(">>>STATISTICS");
        allPacksE.print();


        System.out.println("\n\n\n");

        /**
         * Test Female/Male und Container
         */

        System.out.println("MaleId: " + Male1.getId());
        System.out.println("FemaleId: " + Female6.getId());

        System.out.println("MaleGeb: " + Male1.getBirthday());
        System.out.println("FemaleGeb: " + Female6.getBirthday());

        Female7.setNumberOfChildren(5);
        System.out.println("NumChilds: " + Female6.getNumberOfChildren());
        System.out.println("NumChilds: " + Female7.getNumberOfChildren());

        Male4.addAdrenalinValue(11,0.1f);
        Male4.addAdrenalinValue(12,0.2f);
        Male4.addAdrenalinValue(13,0.3f);

        System.out.println("AdrenSearch: " + Male4.getAdrenalinValue(11));
        System.out.println("AdrenSearch: " + Male4.getAdrenalinValue(33));
        System.out.println("AdrenSearch: " + Male4.getAdrenalinValue(33));

        Male4.addCortisolValue(11,0.9f);
        Male4.addCortisolValue(12,0.8f);
        Male4.addCortisolValue(13,0.7f);

        System.out.println("CortSearch: " + Male4.getCortisolValue(11));
        System.out.println("CortSearch: " + Male4.getCortisolValue(12));
        System.out.println("CortSearch: " + Male4.getCortisolValue(33));

        // beta -> beta
        Male4.setRank(false, 100);
        System.out.println("AdrenSearch: " + Male4.getAdrenalinValue(11));
        System.out.println("AlphaDate: " + Male4.getAlphaDate());
        // beta -> alpha
        Male4.setRank(true, 100);
        System.out.println("AdrenSearch: " + Male4.getAdrenalinValue(11));
        System.out.println("AlphaDate: " + Male4.getAlphaDate());

        //hinzufügen eines adrenalinvalues
        Male4.addAdrenalinValue(11,0.1f);
        System.out.println("AdrenSearch: " + Male4.getAdrenalinValue(11));



    }
}
