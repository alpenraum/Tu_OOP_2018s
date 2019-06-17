import java.util.ArrayList;

public class AirSwarm extends Swarm{
    /**
     * Constructor for Swarm, which allows to set the max. flight altitude and the size of the Swarm.
     *
     * @param maxHeight
     * @param animalCount
     * @param swarmDrawOffset
     */
    public AirSwarm(int maxHeight,int animalCount, int swarmDrawOffset){
        this.dependentAnimals = new ArrayList<>();
        this.independentAnimals = new ArrayList<>();
        this.searchRadius = 100.0f;
        this.borderSize = 5.0f;
        this.randomAnimalSpeed = 1.0f;
        this.safeSpace = 15.0f;
        this.attractionSpeed = 1.0f;
        this.maxHeight = maxHeight;
        this.animalCount = animalCount;
        this.swarmDrawOffset = swarmDrawOffset;
    }
}
