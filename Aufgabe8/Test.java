import java.util.Timer;
import java.util.TimerTask;

/**
 * Finn Zimmers: Implementation AquaObject, Correction Aquarium, Fish. Zusicherungen, Simulation.
 * Michael Landauer: Implementation Aquarium, Fish. Zusicherungen, Simulation.
 */

public class Test {
    public static void main(String[] args) {
        Aquarium TestAquarium1 = new Aquarium(15, 10);
        Aquarium TestAquarium2 = new Aquarium(50, 30);
        Aquarium TestAquarium3 = new Aquarium(10, 10);

        Thread Sim1 = new Thread(new Simulation(TestAquarium1, 10));
        Thread Sim2 = new Thread(new Simulation(TestAquarium2, 70));
        Thread Sim3 = new Thread(new Simulation(TestAquarium3, 14));

        Sim1.start();

        try {
            Sim1.join();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }

        Sim2.start();

        try {
            Sim2.join();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }

        Sim3.start();

        try {
            Sim3.join();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
        System.out.println("----------------------------");
        System.out.println("Alle 3 Simulationen beendet!");
    }
}

class Simulation implements Runnable{

    private Aquarium SimulationAquarium;
    private int numberOfFishes;

    public Simulation(Aquarium input, int numberOfFishes){
        this.SimulationAquarium = input;
        this.numberOfFishes = numberOfFishes;
    }

    @Override
    public void run() {
        for (int i = 0; i < numberOfFishes; i++) {
            this.SimulationAquarium.putFishInRandom();
        }

        this.SimulationAquarium.draw();
        this.SimulationAquarium.makeRandomFishToLeader();
        this.SimulationAquarium.generateFishThreads();

        while(!this.SimulationAquarium.getThreadsFinished()){
            try{
                Thread.sleep(30);
            }
            catch (InterruptedException e){
                System.out.println(e);
            }
        }

    }
}
