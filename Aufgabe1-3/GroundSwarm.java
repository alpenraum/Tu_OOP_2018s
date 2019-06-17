import java.util.ArrayList;

public class GroundSwarm extends Swarm {
    /**
     * Generates a GroundSwarm for Animals which are located at height = 0
     * @param maxHeight max. flight altitude
     * @param animalCount size of the Swarm
     * @param swarmDrawOffset
     */
    public GroundSwarm(int maxHeight,int animalCount, int swarmDrawOffset){
        this.dependentAnimals = new ArrayList<>();
        this.independentAnimals = new ArrayList<>();
        this.searchRadius = 100.0f;
        this.borderSize = 5.0f;
        this.randomAnimalSpeed = 0.3f;
        this.safeSpace = 5.0f;
        this.attractionSpeed = 1.0f;
        // this.worldX = x;
        //this.worldY = y;
        this.maxHeight = 0;
        this.animalCount = animalCount;
        this.speed = 0.3f;
        this.swarmDrawOffset = swarmDrawOffset;
    }

    /**
     * Calculates for n different Animals random x and y directions with z = 0. Swarm must have at least n Animals.
     * @param n number of random Animals
     */
    public void calcRandomAnimals(int n) {
        for (Animal b : independentAnimals) {
            b.setColor(b.getColor());
        }
        this.dependentAnimals.addAll(independentAnimals);
        this.independentAnimals.clear();

        for (int i = n; i > 0; i--) {
            //Pick random bird
            int r = (int) (Math.random() * (this.dependentAnimals.size()));
            Animal temp = this.dependentAnimals.remove(r);
            //Calculate random direction
            float rX = (float) (Math.random() * 2 - 1);
            float rY = (float) (Math.random() * 2 - 1);
            //Normalize Vector
            float norm = calcEuclideanNorm(rX, rY);
            rX /= norm;
            rY /= norm;

            //Set the new direction
            temp.setVelX(rX * randomAnimalSpeed);
            temp.setVelY(rY * randomAnimalSpeed);
            temp.setVelZ(0.0f);
            this.independentAnimals.add(temp);
        }
    }

    /**
     * Returns a new Animal with the position of Animal b and a new velocity vector. The direction of the velocity
     * vector points in the opposite direction of the Animal b and the Animal nearest Neighbour.
     * @param b Input Animal
     * @param nearestNeighbour Nearest neighbour of b
     * @return
     */
    protected Animal collisionMode(Animal b, Animal nearestNeighbour){
        float vX = 0.0f;
        float vY = 0.0f;
        float vZ = 0.0f;
        vX = b.getXCoor() - nearestNeighbour.getXCoor();
        vY = b.getYCoor() - nearestNeighbour.getYCoor();
        vZ = 0.0f;
        float norm = calcEuclideanNorm(vX, vY);
        vX /= norm;
        vY /= norm;

        Animal temp = new Animal(b.getXCoor(), b.getYCoor(), 0.0f);
        temp.setVelX(vX);
        temp.setVelY(vY);
        temp.setVelZ(0.0f);

        return temp;
    }

    /**
     * Returns a new Animal with the position of Animal b and a new velocity vector. The direction of the velocity
     * vector in x and y is the mean of all other velocity vectors of the Animals in Neighbours.
     * @param b Input Animal
     * @param Neighbours List of Animal
     * @return
     */
    protected Animal alignmentMode(Animal b, ArrayList<Animal> Neighbours){
        float sumX = 0;
        float sumY = 0;
        float sumZ = 0;
        float vX, vY, vZ = 0;

        for (Animal i: Neighbours) {
            sumX += i.getVelX();
            sumY += i.getVelY();
        }

        sumX /= Neighbours.size();
        sumY /= Neighbours.size();

        float norm = calcEuclideanNorm(sumX, sumY);
        vX = sumX / norm;
        vY = sumY / norm;
        vZ = 0.0f;

        Animal temp = new Animal(b.getXCoor(), b.getYCoor(), b.getZCoor());
        temp.setVelX(vX);
        temp.setVelY(vY);
        temp.setVelZ(vZ);

        return temp;
    }

    /**
     * Calculates and manipulates the directions of all Birds in the swarm. Each direction of an
     * Animal is calculated independently, based on its position and direction of the other Animals in GroundSwarm.
     * The Swarm must contain at least 1 Animal.
     */
    public void updateVelocity(){
        ArrayList<Animal> NewDependetBirds = new ArrayList<>();

        //Calculate direction for non-random birds
        for (Animal b: this.dependentAnimals) {
            float searchRadius = this.searchRadius;

            ArrayList<Animal> Neighbours = this.calculateAllNeighbours(b, searchRadius);
            //Check if there are enough other birds in range. When not -> make radius bigger/smaller
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
            Animal newBird;

            /**Collision-mode: When other bird is to near and not in border-range,
            the bird flies in the opposite direction of the nearest neighbour **/
            Animal NearestNeighbour = this.calcNearestNeighbour(b, Neighbours);
            if(calcDistance(b, NearestNeighbour) < this.safeSpace){
                newBird = collisionMode(b, NearestNeighbour);
            }

            /**Alignment-mode: When there are more than 10 birds in range, the direction of bird b is the average
            direction of all neighbours **/
            else if(Neighbours.size() > 18){
                newBird = alignmentMode(b, Neighbours);
            }

            /**Attraction-mode: When there are only few neighbours in range, the bird b flies to the
            average position of its neighbours **/
            else{
                newBird = attractionMode(b, Neighbours);
            }
            newBird.setZCoor(0.0f);
            newBird.setVelZ(0.0f);
            NewDependetBirds.add(newBird);
        }
        this.copyValuesFrom(NewDependetBirds);
    }

    /**
     * FEHLER: Die Methode des Obertyps besitzt die Nachbedingung dass, die Richtungsvektoren auch die Futtersuche
     * unterstützen. Diese wird aber vom Untertyp abgeschwächt, indem diese Methode einfach die updateVelocity() Methode
     * aufruft ohne dieser Berücksichtigung.
     * Zu diesem Fehler kam es da nur eine Methode in Test für alle Swarms aufgerufen werden kann um die Vektoren zu
     * berechnen. Eine Möglichkeit wäre die updateVelocitywithFood-Methode in updateVelocity() einzubauen, so das es nur
     * eine Funktion gibt und die Berücksichtigung ob eine Swarm Futter sucht in eine boolean Objektvariable zu speichern.
     *
     * Same as the updateVeloctiy Method.
     * @param food an existing FoodSource
     */
    public void updateVelocitywithFood(FoodSource food){
        updateVelocity();
    }

    /**
     * Returns a new Animal with the position of Animal b and a new velocity vector. The direction of the velocity
     * vector points to the mean position of all other Animals in Neighbours and sets the velocity in z-direction to 0.
     * @param b Input Animal
     * @param Neighbours List of Animal
     * @return the new Animal with updated velocity vectors
     */
    protected Animal attractionMode(Animal b, ArrayList<Animal> Neighbours){
        float sumX = 0.0f;
        float sumY = 0.0f;

        float vX = 0.0f;
        float vY = 0.0f;

        for (Animal i: Neighbours) {
            sumX += i.getXCoor();
            sumY += i.getYCoor();

        }
        sumX /= Neighbours.size();
        sumY /= Neighbours.size();

        vX = -b.getXCoor() + sumX;
        vY = -b.getYCoor() + sumY;

        float norm = calcEuclideanNorm(vX,vY);
        vX /= norm;
        vY /= norm;


        vX *= this.attractionSpeed;
        vY *= this.attractionSpeed;

        Animal temp = new Animal(b.getXCoor(), b.getYCoor(), b.getZCoor());
        temp.setVelX(vX);
        temp.setVelY(vY);
        temp.setVelZ(0.0f);

        return temp;
    }
}
