import java.util.ArrayList;

public class Swarm {
    protected ArrayList<Animal> dependentAnimals;
    protected ArrayList<Animal> independentAnimals;
    /**
     * initial search radius for every animal in the swarm
     **/
    protected float searchRadius;
    protected float borderSize;
    /**
     * scalar which controls the speed of random birds
     **/
    protected float randomAnimalSpeed;
    /**
     * radius for the smallest distance between each animal
     **/
    protected float safeSpace;
    /**
     * scalar which controls the speed when an animal is in attraction-mode
     **/
    protected float attractionSpeed;
    /**
     * Maximum distance from the ground
     **/
    protected int maxHeight;
    /**
     * counter of animals in the swarm
     **/
    protected int animalCount;
    /**
     * offset for drawing the animals
     **/
    protected int swarmDrawOffset = 50;
    /**
     * normal Speed of the Swarm
     **/
    protected float speed = 1.0f;

    /**
     * Constructor for Swarm, which allows to set the max. flight altitude and the size of the Swarm.
     * @param maxHeight max. flight altitude
     * @param animalCount size of the Swarm
     */
    public Swarm(int maxHeight, int animalCount) {
        this.dependentAnimals = new ArrayList<>();
        this.independentAnimals = new ArrayList<>();
        this.searchRadius = 100.0f;
        this.borderSize = 5.0f;
        this.randomAnimalSpeed = 1.0f;
        this.safeSpace = 15.0f;
        this.attractionSpeed = 1.0f;
        // this.worldX = x;
        //this.worldY = y;
        this.maxHeight = maxHeight;
        this.animalCount = animalCount;
    }

    /**
     * Default constructor for the Class Swarm
     */
    public Swarm() {
        this.dependentAnimals = new ArrayList<>();
        this.independentAnimals = new ArrayList<>();
        this.searchRadius = 100.0f;
        this.borderSize = 5.0f;
        this.randomAnimalSpeed = 1.0f;
        this.safeSpace = 15.0f;
        this.attractionSpeed = 1.0f;

    }

    /**
     * Returns the size of the Swarm.
     * @return size of the Swarm
     */
    public int getSwarmSize() {
        return this.dependentAnimals.size() + this.independentAnimals.size();
    }

    /**
     * Adds a new Object from type Animal to the Swarm.
     * @param b new Animal which should be added to the Swarm
     */
    public void add(Animal b) {
        this.dependentAnimals.add(b);
    }

    /**
     * Generates n Birds at different position, but same height. They amount of Animals depends on the
     * variable "animalCount", which must be set in the constructor.
     * @param settings parameters which are set in Settings.class
     * @param type reference Animal
     */
    public void generateAnimals(Settings settings, Animal type) {
        ArrayList<Animal> toRemove = this.clear();
        for (Animal temp : toRemove) {
            settings.getFrame().remove(temp);
        }
        for (int i = 0; i < animalCount; i++) {
            Animal b = type.generateNew(swarmDrawOffset + (i % 20) * 25, swarmDrawOffset + (i / 20) * 25, 50);
            this.add(b);
        }
        for (Animal temp : this.getSwarm()) {
            settings.getWindow().add(temp);
        }
    }

    /**
     * Returns a ArrayList of all Animals in the swarm
     * @return list of Animals
     */
    public ArrayList<Animal> getSwarm() {
        ArrayList<Animal> all = new ArrayList<>();
        all.addAll(this.dependentAnimals);
        all.addAll(this.independentAnimals);
        return all;
    }


    /**
     * Calculates for n different Animals random directions. Swarm must have at least n Animals.
     * @param n number of random Animals
     */
    public void calcRandomAnimals(int n) {
        for (Animal b : independentAnimals) {
            b.setColor(b.getColor());
        }
        this.dependentAnimals.addAll(independentAnimals);
        this.independentAnimals.clear();

        for (int i = n; i > 0; i--) {
            //ANMERKUNG:Pick random animal
            int r = (int) (Math.random() * (this.dependentAnimals.size()));
            Animal temp = this.dependentAnimals.remove(r);
            //ANMERKUNG:Calculate random direction
            float rX = (float) (Math.random() * 2 - 1);
            float rY = (float) (Math.random() * 2 - 1);
            float rZ = (float) (Math.random() * 2 - 1);
            //ANMERKUNG:Normalize Vector
            float norm = calcEuclideanNorm(rX, rY, rZ);
            rX /= norm;
            rY /= norm;
            rZ /= norm;
            //ANMERKUNG:Set the new direction
            temp.setVelX(rX * randomAnimalSpeed);
            temp.setVelY(rY * randomAnimalSpeed);
            temp.setVelZ(rZ * randomAnimalSpeed);

            this.independentAnimals.add(temp);
        }
    }

    /**
     * GUT: Durch das dynamische Binden mittels Animal, erhöht sich die Wartbarkeit. Ohne dieser Eigentschaft müsste für
     * jedes Animal eine Methode geschrieben werden und bei einer Änderung alle Methode auch ausgebessert werden.
     *
     * Returns a list of neighbours of an Animal. The swarm must contain other animals as the InputAnimal.
     * @param InputAnimal animal in the middle
     * @param radius size of the radius
     * @return a list of animals within the radius of the Input Animal
     */
    protected ArrayList<Animal> calculateAllNeighbours(Animal InputAnimal, float radius) {
        ArrayList<Animal> Neighbours = new ArrayList<>();
        ArrayList<Animal> AllAnimalsWithoutInputAnimal = new ArrayList<>();
        AllAnimalsWithoutInputAnimal.addAll(this.dependentAnimals);
        AllAnimalsWithoutInputAnimal.addAll(this.independentAnimals);
        AllAnimalsWithoutInputAnimal.remove(InputAnimal);

        for (Animal b :
                AllAnimalsWithoutInputAnimal) {
            float x = b.getXCoor() - InputAnimal.getXCoor();
            float y = b.getYCoor() - InputAnimal.getYCoor();
            float z = b.getZCoor() - InputAnimal.getZCoor();

            if (calcEuclideanNorm(x, y, z) <= radius) {
                if (!(b.isResting() || b.getEnergy() <= 0)) {
                    Neighbours.add(b);
                }
            }
        }
        return Neighbours;
    }

    /**
     * The same as updateVelocity(), but it checks whether a foodsource [food] is in the searchRadius of a bird.
     * If thats the case, then this is the new target of the bird
     *
     * @param food an existing FoodSource
     * food must be initialized. No object Variables may be NULL
     */
    public void updateVelocitywithFood(FoodSource food) {
        ArrayList<Animal> NewDependentAnimals = new ArrayList<>();
        for (Animal b : this.independentAnimals) {
            if (b.getZCoor() > this.maxHeight || b.getZCoor() < 0) {
                b.setVelZ(0);
            }
        }
        //ANMERKUNG:Calculate direction for non-random animals
        for (Animal b : this.dependentAnimals) {
            float searchRadius = this.searchRadius;

            float diffXF = Math.abs(b.getXCoor() - food.getCurrX());
            float diffYF = Math.abs(b.getYCoor() - food.getCurrY());
            float diffBF = calcEuclideanNorm(diffXF, diffYF);
            if (!food.isVisible() || diffBF < searchRadius || b.getHasEaten()) {


                ArrayList<Animal> Neighbours = this.calculateAllNeighbours(b, searchRadius);
                //ANMERKUNG:Check if there are enough other animals in range. When not -> make radius bigger/smaller
                if (Neighbours.size() < 5 || Neighbours.size() > 20 || b.hasEaten) {
                    while (Neighbours.size() < 5.0) {
                        searchRadius *= 1.1;
                        Neighbours = this.calculateAllNeighbours(b, searchRadius);
                    }
                    while (Neighbours.size() > 20.0) {
                        searchRadius *= 0.9;
                        Neighbours = this.calculateAllNeighbours(b, searchRadius);
                    }
                }

                Animal newAnimal;

                //ANMERKUNG:Collision-mode: When other animal is to near and not in border-range,
                // the animal flies in the opposite direction of the nearest neighbour
                Animal NearestNeighbour = this.calcNearestNeighbour(b, Neighbours);
                if (calcDistance(b, NearestNeighbour) < this.safeSpace && !(b.getZCoor() > this.maxHeight || b.getZCoor() < 0)) {
                    newAnimal = collisionMode(b, NearestNeighbour);
                }

                //ANMERKUNG:Alignment-mode: When there are more than 10 animals in range, the direction of animal b is the average
                //direction of all neighbours
                else if (Neighbours.size() > 10 && !(b.getZCoor() > this.maxHeight || b.getZCoor() < 0)) {
                    newAnimal = alignmentMode(b, Neighbours);
                }

                //ANMERKUNG:Attraction-mode: When there are only few neighbours in range, the animal b flies to the
                //average position of its neighbours
                else {
                    newAnimal = attractionMode(b, Neighbours);
                }

                newAnimal.setHasEaten(false);
                newAnimal.decreaseTimerToEat();
                NewDependentAnimals.add(newAnimal);
            } else {
                b.setHasEaten(true);
                b.resetTimerToEat();
                ArrayList<Animal> temp = new ArrayList<>();
                temp.add(new Animal(food.getCurrX(), food.getCurrY(), 0));
                Animal newAnimal = attractionMode(b, temp);
                newAnimal.setHasEaten(true);
                newAnimal.resetTimerToEat();
                NewDependentAnimals.add(newAnimal);
            }
        }
        this.copyValuesFrom(NewDependentAnimals);


    }

    /**
     * Calculates and manipulates the directions of all Birds in the swarm. Each direction of an
     * Animal is calculated independently, based on its position and direction of the other Animals in the Swarm.
     * The Swarm must contain at least 1 Animal.
     *
     * SCHLECHT: Durch die Berechnung des Richtungsvektor ensteht eine starke Objektkopplung. Das Problem ist dass,
     * bei der Berechnung ständig auf die Variablen vom Typ Animals zugegriffen wird. Ich denke dass es zu dieser
     * Schwachstelle kam dass die Berechnung des Richtungsvektor in der Klasse Swarm liegt, anstatt in der Klasse Animal.
     * Ein weiterer Grund ist dass ein Animal immer verschiedenen Nachbarn besitzen kann, anstatt fixe Nachbarn zu besitzen,
     * die in der Klasse Animal definiert sind. Die einzige Möglichkeit wäre die Berechnung in der Animalklasse zu implementieren
     * und fixe Nachbarn zu setzen, dies würde die Objektkopplung schwächen.
     */
    public void updateVelocity() {
        ArrayList<Animal> NewDependentAnimals = new ArrayList<>();
        for (Animal b : this.independentAnimals) {
            if (b.getZCoor() > this.maxHeight || b.getZCoor() < 0) {
                b.setVelZ(0);
            }
        }
        //ANMERKUNG:Calculate direction for non-random animals
        for (Animal b : this.dependentAnimals) {
            float searchRadius = this.searchRadius;

            ArrayList<Animal> Neighbours = this.calculateAllNeighbours(b, searchRadius);
            //ANMERKUNG:Check if there are enough other animals in range. When not -> make radius bigger/smaller
            if (Neighbours.size() < 5 || Neighbours.size() > 20) {
                while (Neighbours.size() < 5.0) {
                    searchRadius *= 1.1;
                    Neighbours = this.calculateAllNeighbours(b, searchRadius);
                }
                while (Neighbours.size() > 20.0) {
                    searchRadius *= 0.9;
                    Neighbours = this.calculateAllNeighbours(b, searchRadius);
                }
            }

            Animal newAnimal;

            //ANMERKUNG:Collision-mode: When other animal is to near and not in border-range,
            // the animal flies in the opposite direction of the nearest neighbour
            Animal NearestNeighbour = this.calcNearestNeighbour(b, Neighbours);
            if (calcDistance(b, NearestNeighbour) < this.safeSpace && !(b.getZCoor() > this.maxHeight || b.getZCoor() < 0)) {
                newAnimal = collisionMode(b, NearestNeighbour);
            }

            //ANMERKUNG:Alignment-mode: When there are more than 10 animals in range, the direction of animal b is the average
            //direction of all neighbours
            else if (Neighbours.size() > 10 && !(b.getZCoor() > this.maxHeight || b.getZCoor() < 0)) {
                newAnimal = alignmentMode(b, Neighbours);
            }

            //ANMERKUNG:Attraction-mode: When there are only few neighbours in range, the animal b flies to the
            //average position of its neighbours
            else {
                newAnimal = attractionMode(b, Neighbours);
            }

            NewDependentAnimals.add(newAnimal);
        }
        this.copyValuesFrom(NewDependentAnimals);
    }

    /**
     * Returns a new Animal with the position of Animal b and a new velocity vector. The direction of the velocity
     * vector points in the opposite direction of the Animal b and the Animal nearest Neighbour.
     * @param b Input Animal
     * @param nearestNeighbour Nearest neighbour of b
     * @return the new Animal with updated velocity vectors
     */
    protected Animal collisionMode(Animal b, Animal nearestNeighbour) {
        float vX, vY, vZ = 0.0f;
        vX = b.getXCoor() - nearestNeighbour.getXCoor();
        vY = b.getYCoor() - nearestNeighbour.getYCoor();
        vZ = b.getZCoor() - nearestNeighbour.getZCoor();
        float norm = calcEuclideanNorm(vX, vY, vZ);
        vX /= norm;
        vY /= norm;
        vZ /= norm;

        Animal temp = new Animal(b.getXCoor(), b.getYCoor(), b.getZCoor());
        temp.setVelX(vX);
        temp.setVelY(vY);
        temp.setVelZ(vZ);

        return temp;
    }

    /**
     * Returns a new Animal with the position of Animal b and a new velocity vector. The direction of the velocity
     * vector is the mean of all other velocity vectors of the Animals in Neighbours.
     * @param b Input Animal
     * @param Neighbours List of Animal
     * @return the new Animal with updated velocity vectors
     */
    protected Animal alignmentMode(Animal b, ArrayList<Animal> Neighbours) {
        float sumX = 0;
        float sumY = 0;
        float sumZ = 0;
        float vX, vY, vZ = 0;

        for (Animal i : Neighbours) {
            sumX += i.getVelX();
            sumY += i.getVelY();
            sumZ += i.getVelZ();
        }

        sumX /= Neighbours.size();
        sumY /= Neighbours.size();
        sumZ /= Neighbours.size();

        float norm = calcEuclideanNorm(sumX, sumY, sumZ);
        vX = sumX / norm;
        vY = sumY / norm;
        vZ = sumZ / norm;

        Animal temp = new Animal(b.getXCoor(), b.getYCoor(), b.getZCoor());
        temp.setVelX(vX);
        temp.setVelY(vY);
        temp.setVelZ(vZ);

        return temp;
    }

    /**
     * Returns a new Animal with the position of Animal b and a new velocity vector. The direction of the velocity
     * vector points to the mean position of all other Animals in Neighbours
     * @param b Input Animal
     * @param Neighbours List of Animal
     * @return the new Animal with updated velocity vectors
     */
    protected Animal attractionMode(Animal b, ArrayList<Animal> Neighbours) {
        float sumX = 0;
        float sumY = 0;
        float sumZ = 0;
        float vX, vY, vZ = 0.0f;

        for (Animal i : Neighbours) {
            sumX += i.getXCoor();
            sumY += i.getYCoor();
            sumZ += i.getZCoor();
        }

        sumX /= Neighbours.size();
        sumY /= Neighbours.size();
        sumZ /= Neighbours.size();

        vX = -b.getXCoor() + sumX;
        vY = -b.getYCoor() + sumY;
        vZ = -b.getZCoor() + sumZ;

        float norm = calcEuclideanNorm(vX, vY, vZ);
        vX /= norm;
        vY /= norm;
        vZ /= norm;

        vX *= this.attractionSpeed;
        vY *= this.attractionSpeed;
        vZ *= this.attractionSpeed;

        Animal temp = new Animal(b.getXCoor(), b.getYCoor(), b.getZCoor());
        temp.setVelX(vX);
        temp.setVelY(vY);
        temp.setVelZ(vZ);

        return temp;
    }

    /**
     * Copys the position, velocity, hasEaten Values of an ArrayList of Animals to the Animals in the Swarm.
     * The input must have the same amount of Animal as the Swarm.
     * @param input list of Animals
     */
    protected void copyValuesFrom(ArrayList<Animal> input) {
        int i = 0;
        for (Animal b : this.dependentAnimals) {
            b.setXCoor(input.get(i).getXCoor());
            b.setYCoor(input.get(i).getYCoor());
            b.setZCoor(input.get(i).getZCoor());
            b.setVelX(input.get(i).getVelX() * this.speed);
            b.setVelY(input.get(i).getVelY() * this.speed);
            b.setVelZ(input.get(i).getVelZ() * this.speed);
            b.setHasEaten(input.get(i).getHasEaten());
            b.resetTimerToEat();
            i++;
        }
    }

    /**
     * Returns the length of an vector (1000000.0f to prevent division 0)
     * @param x x-coordinate
     * @param y y-coordinate
     * @return length of the vector
     */
    protected float calcEuclideanNorm(float x, float y) {
        float result = (float) Math.sqrt(x * x + y * y);
        return result == 0.0f ? 1000000.0f : result;
    }

    /**
     * Returns the length of an vector (1000000.0f to prevent division 0)
     * @param x x-coordinate
     * @param y y-coordinate
     * @param z z-coordinate
     * @return length of the vector
     */
    protected float calcEuclideanNorm(float x, float y, float z) {
        float result = (float) Math.sqrt(x * x + y * y + z * z);
        return result == 0.0f ? 1000000.0f : result;
    }

    /**
     * Returns the neareast neighbour from CenterAnimal from a list of Neighbours.
     * Neighbours must contain at least 1 Animal
     * @param CenterAnimal reference Animal
     * @param Neighbours list of Neighbours
     * @return nearest animal of CenterAnimal
     */
    protected Animal calcNearestNeighbour(Animal CenterAnimal, ArrayList<Animal> Neighbours) {
        double shortestDistance = 10000000;
        if (Neighbours.size() > 0) {
            Animal NearestNeighbour = Neighbours.get(0);

            for (Animal b : Neighbours) {
                if (calcDistance(CenterAnimal, b) < shortestDistance) {
                    shortestDistance = calcDistance(CenterAnimal, b);
                    NearestNeighbour = b;
                }
            }
            return NearestNeighbour;
        }
        return new Animal();
    }

    /**
     * Returns the distance between Animal b1, and Animal b2;
     * @param b1 first Animal
     * @param b2 secound Animal
     * @return length of the distance
     */
    protected float calcDistance(Animal b1, Animal b2) {
        float x = b2.getXCoor() - b1.getXCoor();
        float y = b2.getYCoor() - b1.getYCoor();
        float z = b2.getZCoor() - b1.getZCoor();

        return calcEuclideanNorm(x, y, z);
    }

    public void setSearchRadius(float searchRadius) {
        this.searchRadius = searchRadius;
    }

    public void setRandomAnimalSpeed(float randomAnimalSpeed) {
        this.randomAnimalSpeed = randomAnimalSpeed;
    }

    public void setSafeSpace(float safeSpace) {
        this.safeSpace = safeSpace;
    }

    public void setAttractionSpeed(float attractionSpeed) {
        this.attractionSpeed = attractionSpeed;
    }

    /**
     * Deletes all Animals in the swarm and returns a list of the removed Animals
     * @return removed Animals
     */
    public ArrayList<Animal> clear() {
        ArrayList<Animal> temp = this.getSwarm();
        this.dependentAnimals = new ArrayList<>();
        this.independentAnimals = new ArrayList<>();
        return temp;
    }

    /**
     * Moves all Animals of the Swarm in a specific position based on x and y.
     * @param x x-direction
     * @param y y-direction
     */
    public void moveAllBirds(float x, float y) {

        for (Animal n : this.getSwarm()) {
            n.setXCoor(n.getXCoor() + x);
            n.setYCoor(n.getYCoor() + y);
        }
    }

}
