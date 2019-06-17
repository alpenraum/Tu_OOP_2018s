import javax.swing.*;
import java.util.ArrayList;
import java.io.*;

/**
 * Who did what?
 * Finn Zimmer [f]: Main Class, Bird Class, everything with Java AWT/Swing ,
 * <p>
 * Michael Landauer [m]: Swarm Class & calculation of the velocity vectors
 * <p>
 * René Appel [r]: Formatting & correcting code; putting hardcoded numbers into variables, where needed;
 * making windowsize, birdcount, drawing of birds easily changeable;
 * <p>
 * <p>
 * <p>
 * Aufgabe 2:
 * <p>
 * Finn Zimmer[f]: implemented a version of 3D by using scale and color gradients, added window class, in which everything is drawn (hopefully makes the free camera easier),
 * Energy of birds . General debugging and documentation , FoodSource
 * <p>
 * Michael Landauer: implemented AirSwarm, GroundSwarm (+different physics), Animal, Ant
 * <p>
 * René Appel [r]: scrolling with mouse, settings, background, generateAnimals, reformatting, renaming, cleaning up/changing comments
 *
 * Aufgabe 3:
 * Anzahl GOOD/GUT: 5
 * Anzahl BAD/SCHLECHT: 4
 * Möglicher Grund, warum nur 4 "BAD" gefunden wurden: durch die Settings-Klasse wurde vermutlich viel abgefangen.
 *
 * Finn Zimmer: Documentation in FoodSource, Background, Window, Bird, part of Animal, part of Swarm, CreateJFrameWindowWithMouseEventHandling;
 *              initial Ideas what's GOOD/BAD
 * Michael Landauer: writing javadoc comments in Swarm, GroundSwarm, AirSwarm and Animal. Wrote 2 BAD and 1 GOOD comment and 1 "ERROR"
 * René Appel: 3 BAD, 2 GOOD Kommentare, removing old comments, adding "ANMERKUNG"en, editing/adding the last few javadoc comments
 *
 **/


public class Test {


    public static void main(String[] args) throws InterruptedException, IOException {


        Settings settings = new Settings();


        int windowX = settings.getWindowX();
        int windowY = settings.getWindowY();


        int randomBirds = settings.getRandomBirds();


        long renderIterations = settings.getRenderIterations();


        int simulationCounter = 1;


        int counterForRandomBirds = 100;


        Swarm swarm = new AirSwarm(100, 50, 200);
        Swarm swarm2 = new GroundSwarm(0, 80, 30);
        ArrayList<Swarm> allSwarms = new ArrayList<>();
        allSwarms.add(swarm);
        allSwarms.add(swarm2);


        FoodSource food = new FoodSource(new float[]{50.0f, 500.0f, 300.0f}, new float[]{50.00f, 500.0f, 300.0f});
        food.setVisibility(false);
        food.setVisible(true);
        food.setDeleting(false);


        Background bg = new Background(settings);


        JFrame frame = CreateJFrameWindowWithMouseEventHandling.createAndShowGUI(settings, allSwarms, bg, food);




        Window window = new Window("Aufgabe1-3/background1.jpg");


        window.setSize(windowX, windowY);
        window.resizeBackground(windowX, windowY);
        window.setLocation(0, 0);
        window.setLayout(null);
        frame.add(window);


        window.add(bg);
        window.add(food);
        settings.setFrame(frame);
        settings.setWindow(window);




        while (simulationCounter <= 3) {
            //ANMERKUNG: Die nächsten auskommentierten Zeilen sind für die Anzahl an Simulationen zuständig.
            //System.out.println("Simulation #" + simulationCounter);


            allSwarms.get(0).generateAnimals(settings, new Bird());
            allSwarms.get(1).generateAnimals(settings, new Ant());

            window.add(bg);


/*            //settings for simulation #2
            if (simulationCounter == 2) {
                //randomBirds = 5;
                Swarm.setSafeSpace(8.0f);
                Swarm.setSearchRadius(60.0f);
                Swarm.setAttractionSpeed(2.0f);
            }
            //settings for simulation #3
            if (simulationCounter == 3) {
                //randomBirds = 10;
                Swarm.setSafeSpace(25.0f);
                Swarm.setSearchRadius(150.0f);
                Swarm.setAttractionSpeed(0.5f);
            }*/ //Simulations deactivated

            frame.repaint();


            // ANMERKUNG: Render Loop
            long j = 0;
            while (true) {
                //ANMERKUNG: calculate every 100 frames a new direction for X random birds
                if (counterForRandomBirds == 100) {
                    counterForRandomBirds = 0;
                    for (Swarm s : allSwarms) {
                        s.calcRandomAnimals(randomBirds);
                    }
                }
                counterForRandomBirds++;
                //ANMERKUNG: calculates the new direction for all birds, except the random ones
                for (Swarm s : allSwarms) {
                    s.updateVelocitywithFood(food);
                }
                //ANMERKUNG: adds for every bird the new velocity to its position and paints it
                for (Swarm s : allSwarms) {
                    for (Animal temp : s.getSwarm()) {
                        temp.updatePos();
                        temp.repaint();
                    }
                }


                 //ANMERKUNG: manages, when the food source appeares and disappears
                if (j % 600 == 300) {
                    food.setVisibility(true);
                    System.out.println("food appeared at" + food.getCurrX() + " , " + food.getCurrY());
                    food.setVisible(true);
                }
                if (j % 600 == 0) {
                    food.setVisibility(false);
                    food.setVisible(false);
                }
                if (food.isVisible()) {
                    food.repaint();
                }

                j++;
                Thread.sleep(20);
            }

            //simulationCounter++;
        }


    }
}





