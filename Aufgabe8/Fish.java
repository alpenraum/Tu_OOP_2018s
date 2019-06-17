import java.util.ArrayList;

/**
 * Represents a Fish
 */
public class Fish implements Runnable{
    /**
     *  Represents the direction where the Fish looks.
     *  0 up, 1 right, 2 down, 3 left
     */
    private int direction;
    /**
     * Reference to the Aquarium where the Fish lives.
     */
    private Aquarium inAquarium;
    /**
     * True, when the Fish is a "Leitfisch"
     */
    private boolean isLeader;
    /**
     * Counter, how often the fish slept
     */
    private int waitCounter = 0;
    /**
     * Counter, how often the fish moved
     */
    private int movementCounter = 0;

    /**
     * Constructor
     * @param direction where the fish swims
     * @param inAquarium defines the Aquarium where the fish lives
     */
    public Fish(int direction, Aquarium inAquarium){
        this.direction = direction;
        this.inAquarium = inAquarium;
        this.isLeader = false;
    }

    /**
     * Calculates a new position and direction for the fish in random intervals
     */
    @Override
    public void run() {
        while (true){
            int time = 5 + (int) (Math.random()*45 + 1);

            try{
                Thread.sleep(time);
                waitCounter++;
            }
            catch(InterruptedException e){
                System.out.println("Threads interrupted, Fish reached Waiting limit");

                //change here, if you want multiple test cases (aka resetting the simulation)
                break;
            }
            checkWaitingCounter();
            this.calcNewPos();
            movementCounter++;



            if(this.isLeader){
                this.inAquarium.draw();
            }
        }
    }

    /**
     * Checks the WaitingCounter for this fish. If it has reached 32, all Threads will be interrupted and the info of all Fishes is printed
     */
    private synchronized void checkWaitingCounter(){
        if(waitCounter>=32){
                synchronized (this){
                ArrayList<Thread> temp = inAquarium.getThreadList();
                for (Thread t : temp) {
                    t.interrupt();
                }
                inAquarium.getThreadList().clear();
                inAquarium.printFishInfo();
                inAquarium.setThreadsFinished(true);
            }
        }
    }


    private void calcNewPos(){
        this.inAquarium.calcNewPosRandom(this);
    }

    public int getDirection(){
        return this.direction;
    }

    public void setDirection(int direction){
        this.direction = direction;
    }

    public void changeLeadership(Boolean input){
        this.isLeader = input;
    }

    public int getWaitCounter() {
        return waitCounter;
    }

    public int getMovementCounter() {
        return movementCounter;
    }

    @Override
    public String toString() {
        int[] head=inAquarium.getHeadPos(this);
        String out="Fish: "+super.toString()+"\n " +
                "\t WaitCounter: "+this.getWaitCounter()+"\n" +
                "\t MovementCounter: "+this.getMovementCounter()+"\n" +
                "\t Position of Fish Head: ( x:"+head[1]+"; y:"+head[0]+") \n" +
                "\t Direction of Fish: "+this.getDirection();

        return out;
    }
}
