import java.util.ArrayList;

/**
 * Represents a Aquarium for Fishes
 */
public class Aquarium {
    /**
     * 2D-Array which stores AquaObjects
     */
    private AquaObject[][] Aquarium2D;
    /**
     * List of all Fish in this Aquarium
     */
    private ArrayList<Fish> FishList;
    /**
     * All active Threads of Fishes in the Aquarium
     */
    private ArrayList<Thread> ThreadList = new ArrayList<>();
    /**
     * Length of the Aquarium
     */
    private int length;
    /**
     * Height of the Aquarium
     */
    private int height;



    /**
     * True when all Threads are finished
     */
    private boolean ThreadsFinished;

    /**
     * Constructor
     * @param length of the Aquarium
     * @param height of the Aquarium
     */
    public Aquarium(int length, int height) {
        this.Aquarium2D = new AquaObject[height][length];
        //creates the border and fills with "FreeSpace"
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < length; j++) {
                Aquarium2D[i][j] = new Aquarium.FreeSpace();
                if (i == 0 || j == 0 || i == height - 1 || j == length - 1) {
                    Aquarium2D[i][j] = new Aquarium.Border();
                }
            }
        }
        this.FishList = new ArrayList<>();
        this.length = length;
        this.height = height;
    }

    public ArrayList<Thread> getThreadList() {
        return ThreadList;
    }

    /**
     * Inserts a fish randomly into this Aquarium.
     * There should only be so many fishes filled into one Aquarium (max 1/8 of the number of spaces), otherwise it will loop infinitely (= can't
     * find a free space for the fish randomly)
     */
    public void putFishInRandom() {
        boolean freePosFound = false;


        while (!freePosFound) {
            int posY = (int) (Math.random() * (this.height - 1));
            int posX = (int) (Math.random() * (this.length - 1));
            int random = (int) (Math.random() * 4);

            if (this.Aquarium2D[posY][posX].isFree()) {
                if (this.Aquarium2D[posY + 1][posX].isFree() && random == 0) {

                    this.Aquarium2D[posY][posX] = new FishPlaceholder(new Fish(0, this));
                    Fish temp = this.Aquarium2D[posY][posX].getFish();
                    this.Aquarium2D[posY + 1][posX] = new FishPlaceholder(temp);
                    this.Aquarium2D[posY][posX].setHead(true);
                    freePosFound = true;
                    this.FishList.add(temp);

                } else if (this.Aquarium2D[posY - 1][posX].isFree() && random == 1) {

                    this.Aquarium2D[posY][posX] = new FishPlaceholder(new Fish(2, this));
                    Fish temp = this.Aquarium2D[posY][posX].getFish();
                    this.Aquarium2D[posY - 1][posX] = new FishPlaceholder(temp);
                    this.Aquarium2D[posY][posX].setHead(true);

                    freePosFound = true;
                    this.FishList.add(temp);

                } else if (this.Aquarium2D[posY][posX + 1].isFree() && random == 2) {

                    this.Aquarium2D[posY][posX] = new FishPlaceholder(new Fish(1, this));
                    Fish temp = this.Aquarium2D[posY][posX].getFish();
                    this.Aquarium2D[posY][posX + 1] = new FishPlaceholder(temp);
                    this.Aquarium2D[posY][posX + 1].setHead(true);

                    freePosFound = true;
                    this.FishList.add(temp);

                } else if (this.Aquarium2D[posY][posX - 1].isFree() && random == 3) {

                    this.Aquarium2D[posY][posX] = new FishPlaceholder(new Fish(3, this));
                    Fish temp = this.Aquarium2D[posY][posX].getFish();
                    this.Aquarium2D[posY][posX - 1] = new FishPlaceholder(temp);
                    this.Aquarium2D[posY][posX - 1].setHead(true);
                    freePosFound = true;
                    this.FishList.add(temp);
                }
            }
        }
    }

    /**
     * Calculates the movement for a fish (randomly) and updates its position.
     * The fish can move only in the same direction, left or right but not backwards.
     * @param input fish, which should move (MUST NOT BE NULL)
     */
    public void calcNewPosRandom(Fish input) {
        ArrayList<Integer> PossibleDirection = new ArrayList<>();
        switch (input.getDirection()) {
            case 0:
                PossibleDirection.add(0);
                PossibleDirection.add(1);
                PossibleDirection.add(3);
                break;
            case 1:
                PossibleDirection.add(1);
                PossibleDirection.add(2);
                PossibleDirection.add(0);
                break;
            case 2:
                PossibleDirection.add(2);
                PossibleDirection.add(1);
                PossibleDirection.add(3);
                break;
            case 3:
                PossibleDirection.add(3);
                PossibleDirection.add(0);
                PossibleDirection.add(2);
                break;
        }


        while (!PossibleDirection.isEmpty()) {
            int index = (int) (Math.random() * PossibleDirection.size());
            int random = PossibleDirection.get(index);
            int[] headPos = this.getHeadPos(input);
            int y = headPos[0];
            int x = headPos[1];
            int tailPos[] = headPos;

            if(y == -1 && x == -1){
                return;
            }

            switch (input.getDirection()) {
                case 0:
                    tailPos[0] = y + 1;
                    break;
                case 1:
                    tailPos[1] = x - 1;
                    break;
                case 2:
                    tailPos[0] = y - 1;
                    break;
                case 3:
                    tailPos[1] = x + 1;
                    break;
            }

            if (x < this.length - 2 && this.Aquarium2D[y][x + 1].isFree() && random == 1) {
                if (input.getDirection() == 1 || input.getDirection() == 2) {
                    synchronized (this.Aquarium2D[tailPos[0]][tailPos[1]]) {
                        synchronized (this.Aquarium2D[y][x]) {
                            synchronized (this.Aquarium2D[y][x + 1]) {
                                this.Aquarium2D[y][x] = new FishPlaceholder(input);
                                this.Aquarium2D[y][x + 1] = new FishPlaceholder(input);
                                this.Aquarium2D[y][x + 1].setHead(true);
                                this.Aquarium2D[tailPos[0]][tailPos[1]] = new FreeSpace();

                                input.setDirection(1);
                                PossibleDirection.clear();
                            }
                        }
                    }
                } else {
                    synchronized (this.Aquarium2D[y][x]) {
                        synchronized (this.Aquarium2D[y][x + 1]) {
                            synchronized (this.Aquarium2D[tailPos[0]][tailPos[1]]) {
                                this.Aquarium2D[y][x] = new FishPlaceholder(input);
                                this.Aquarium2D[y][x + 1] = new FishPlaceholder(input);
                                this.Aquarium2D[y][x + 1].setHead(true);
                                this.Aquarium2D[tailPos[0]][tailPos[1]] = new FreeSpace();

                                input.setDirection(1);
                                PossibleDirection.clear();
                            }
                        }
                    }
                }


            } else if (x > 1 && this.Aquarium2D[y][x - 1].isFree() && random == 3) {
                if (input.getDirection() == 3 || input.getDirection() == 0) {
                    synchronized (this.Aquarium2D[y][x - 1]) {
                        synchronized (this.Aquarium2D[y][x]) {
                            synchronized (this.Aquarium2D[tailPos[0]][tailPos[1]]) {
                                this.Aquarium2D[y][x] = new FishPlaceholder(input);
                                this.Aquarium2D[y][x - 1] = new FishPlaceholder(input);
                                this.Aquarium2D[y][x - 1].setHead(true);
                                this.Aquarium2D[tailPos[0]][tailPos[1]] = new FreeSpace();

                                input.setDirection(3);
                                PossibleDirection.clear();
                            }
                        }
                    }
                } else {
                    synchronized (this.Aquarium2D[tailPos[0]][tailPos[1]]) {
                        synchronized (this.Aquarium2D[y][x - 1]) {
                            synchronized (this.Aquarium2D[y][x]) {
                                this.Aquarium2D[y][x] = new FishPlaceholder(input);
                                this.Aquarium2D[y][x - 1] = new FishPlaceholder(input);
                                this.Aquarium2D[y][x - 1].setHead(true);
                                this.Aquarium2D[tailPos[0]][tailPos[1]] = new FreeSpace();

                                input.setDirection(3);
                                PossibleDirection.clear();
                            }
                        }
                    }
                }

            } else if (y < this.height - 2 && this.Aquarium2D[y + 1][x].isFree() && random == 2) {
                if (input.getDirection() == 2 || input.getDirection() == 1) {
                    synchronized (this.Aquarium2D[tailPos[0]][tailPos[1]]) {
                        synchronized (this.Aquarium2D[y][x]) {
                            synchronized (this.Aquarium2D[y + 1][x]) {
                                this.Aquarium2D[y + 1][x] = new FishPlaceholder(input);
                                this.Aquarium2D[y + 1][x].setHead(true);
                                this.Aquarium2D[tailPos[0]][tailPos[1]] = new FreeSpace();
                                this.Aquarium2D[y][x] = new FishPlaceholder(input);

                                input.setDirection(2);
                                PossibleDirection.clear();
                            }
                        }
                    }
                } else {
                    synchronized (this.Aquarium2D[y][x]) {
                        synchronized (this.Aquarium2D[tailPos[0]][tailPos[1]]) {
                            synchronized (this.Aquarium2D[y+1][x]) {
                                this.Aquarium2D[y + 1][x] = new FishPlaceholder(input);
                                this.Aquarium2D[y + 1][x].setHead(true);
                                this.Aquarium2D[tailPos[0]][tailPos[1]] = new FreeSpace();
                                this.Aquarium2D[y][x] = new FishPlaceholder(input);

                                input.setDirection(2);
                                PossibleDirection.clear();
                            }
                        }
                    }
                }

            } else if (y > 1 && this.Aquarium2D[y - 1][x].isFree() && random == 0) {
                if(input.getDirection() == 0 || input.getDirection() == 3){
                    synchronized (this.Aquarium2D[y-1][x]) {
                        synchronized (this.Aquarium2D[y][x]) {
                            synchronized (this.Aquarium2D[tailPos[0]][tailPos[1]]) {
                                this.Aquarium2D[y - 1][x] = new FishPlaceholder(input);
                                this.Aquarium2D[tailPos[0]][tailPos[1]] = new FreeSpace();
                                this.Aquarium2D[y - 1][x].setHead(true);
                                this.Aquarium2D[y][x] = new FishPlaceholder(input);

                                input.setDirection(0);
                                PossibleDirection.clear();
                            }
                        }
                    }
                }
                else{
                    synchronized (this.Aquarium2D[y - 1][x]) {
                        synchronized (this.Aquarium2D[tailPos[0]][tailPos[1]]) {
                            synchronized (this.Aquarium2D[y][x]) {
                                this.Aquarium2D[y - 1][x] = new FishPlaceholder(input);
                                this.Aquarium2D[tailPos[0]][tailPos[1]] = new FreeSpace();
                                this.Aquarium2D[y - 1][x].setHead(true);
                                this.Aquarium2D[y][x] = new FishPlaceholder(input);

                                input.setDirection(0);
                                PossibleDirection.clear();
                            }
                        }
                    }
                }


            } else {
                PossibleDirection.remove(index);
            }
        }
    }

    /**
     * Searches the space for Fish input and returns the position of its Head
     *
     * @param input Fish which should be located (MUST NOT BE NULL)
     * @return :
     * Coordinates of the head of Fish Input (y,x)
     * OR
     * {-1,-1} IF Fish input is not found
     */
    protected int[] getHeadPos(Fish input) {

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < length; j++) {
                if (Aquarium2D[i][j].getFish() == input && Aquarium2D[i][j].getHead()) {
                    return new int[]{i, j};
                }
            }
        }

        return new int[]{-1, -1};
    }

    /**
     * Draws the Aquarium with the fishes in the console.
     */
    public void draw() {

        for (AquaObject[] row : this.Aquarium2D) {
            for (AquaObject f : row) {
                System.out.print(f.toString());
            }
            System.out.print("\n");
        }

    }

    /**
     * After calling this, 1 random fish in the Aquarium is a Leader.
     * Others will be set to non Leader.
     */
    public void makeRandomFishToLeader() {
        for (Fish f :
                this.FishList) {
            f.changeLeadership(false);
        }
        this.FishList.get((int) (Math.random() * this.FishList.size())).changeLeadership(true);
    }

    /**
     * Starts Threads for all Fishes in the Aquarium
     */
    public void generateFishThreads() {
        for (Fish f :
                this.FishList) {
            Thread t = new Thread(f);
            ThreadList.add( t);
            t.start();

        }

    }

    /**
     * Prints all information of all fishes into the console
     */
    public void printFishInfo(){
        for(Fish f: FishList){
            System.out.println(f.toString());
        }
    }

    public void setThreadsFinished(boolean threadsFinished) {
        ThreadsFinished = threadsFinished;
    }

    public boolean getThreadsFinished() {
        return this.ThreadsFinished;
    }

    /////////inner CLASSES//////////////

    /**Interface for dynamic linking of the spaces in the Aquarium**/
    private interface AquaObject {

        /**
         *
         * @return :
         *      TRUE, if this space is free
         *      FALSE, if this space is  occupied
         */
        boolean isFree();

        /**
         * Sets this space to be the head of a fish
         * @param n variable, which decides whether this space is the head of a fish
         */
        void setHead(boolean n);

        /**
         *
         * @return :
         *      TRUE, if this space is the head of a fish
         *      FALSE, if this space is not the head of a fish
         */
        boolean getHead();

        /**
         *
         * @return :
         *      returns the corresponding fish to this space, If this space is occupied by a fish.
         *      returns NULL otherwise
         */
        Fish getFish();

        String toString();
    }

    /**
     * Represents a space occupied by a fish
     */
    private class FishPlaceholder implements AquaObject {
        /**
         * The fish that is on this space
         */
        private Fish fish;
        /**
         * Saves, whether this space is the head of a fish
         */
        private boolean head;

        /**
         * Creates a new FishPlaceHolder
         * @param that Fish, that is on this space
         */
        FishPlaceholder(Fish that) {
            this.fish = that;
            this.head = false;
        }

        /**
         * Sets this space to be the head of a fish
         * @param n variable, which decides whether this space is the head of a fish
         */
        public void setHead(boolean n) {
            this.head = n;
        }

        /**
         *
         * @return :
         *      TRUE, if this space is the head of a fish
         *      OR
         *      FALSE, if this space is not the head of a fish
         */
        public boolean getHead() {
            return this.head;
        }

        /**
         *
         * @return the fish (saved in object-variable fish) that is on this space
         */
        @Override
        public Fish getFish() {
            return fish;
        }

        /**
         *
         * @return FALSE
         */
        @Override
        public boolean isFree() {
            return false;
        }

        @Override
        public String toString() {
            String out = "X";

            if (this.head) {
                switch (this.fish.getDirection()) {
                    case 0:
                        out = "A";
                        break;
                    case 1:
                        out = ">";
                        break;
                    case 2:
                        out = "V";
                        break;
                    case 3:
                        out = "<";
                        break;

                }
                return out;
            } else {
                return "X";
            }
        }
    }

    /**
     * Represents a Border of this Aquarium
     */
    private class Border implements AquaObject {

        /**
         *
         * @return FALSE
         */
        @Override
        public boolean isFree() {
            return false;
        }

        /**
         * Sets this space to be the Head of a fish.
         * Does nothing, since no fish can be at the same space as a border.
         * (Only here for dynamic linking)
         * @param n variable, which decides whether this space is the head of a fish
         */
        @Override
        public void setHead(boolean n) {

        }

        /**
         *
         * @return FALSE
         */
        @Override
        public boolean getHead() {
            return false;
        }

        /**
         * Returns the fish that is on this space
         * @return NULL
         */
        @Override
        public Fish getFish() {
            return null;
        }

        @Override
        public String toString() {
            return "|";
        }
    }

    /**
     * Represents a free Space where a fish could potentially go
     */
    private class FreeSpace implements AquaObject {

        /**
         *
         * @return TRUE
         */
        @Override
        public boolean isFree() {
            return true;
        }
        /**
         * Sets this space to be the Head of a fish.
         * Does nothing, since no fish can be at the same space as a FreeSpace (at the same time).
         * (Only here for dynamic linking)
         * @param n variable, which decides whether this space is the head of a fish
         */
        @Override
        public void setHead(boolean n) {

        }

        /**
         *
         * @return FALSE
         */
        @Override
        public boolean getHead() {
            return false;
        }

        /**
         *
         * @return NULL
         */
        @Override
        public Fish getFish() {
            return null;
        }

        @Override
        public String toString() {
            return " ";
        }
    }
}


