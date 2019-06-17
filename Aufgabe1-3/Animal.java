import javax.swing.*;
import java.awt.*;


/**
 * GOOD: Schwache Objektkopplung. Die meisten Berechnungen werden im Schwarm durchgeführt.
 * */
public class Animal extends JComponent{
    protected static final int maxAnimalSize = 15, minAnimalSize = 3;
    /** x & y coordinate of an animal**/
    protected float x, y;
    /**value between 0 and 100. 100 means "normal", 0 means the animal is as far away as possible (minAnimalSize) **/
    protected float z;
    /**velocity of an animal **/
    protected float velX, velY;
    /**Z-Velocity of animal. Is between -1 and 1 (0 means no movement); -1 means, the animal gets smaller, 1 means the animal gets larger **/
    protected float velZ;
    /**Color of the animal **/
    protected Color color;
    /**set size of the animal **/
    protected int animalSize;

    /**decisive variable whether an animal is resting or not **/
    protected boolean isResting;
    /**Stores the energy of an animal. If 0, the animal (lands and) rests. **/
    protected float energy;
    /**determines how fast an animal rests (default=1) **/
    protected static int chargingFactor = 2;

    /**determines if a bird has eaten in the last "timerToEat" ticks. A bird only looks for food, if hasEaten==false*/
    protected boolean hasEaten=false;
    /**saves the ticks, since the bird has eaten last. If 0, the bird is looking for food again*/
    protected int timerToEat=1000;


    /**Constructor **/
    public Animal() {
        x = 0;
        y = 0;
        z = 50;
        energy = (float) (50 + Math.random() * 200.0f);
        this.color = Color.BLACK;
        velX = velY = velZ = 1.0f;
        setLocation(Math.round(x), Math.round(y));
        setSize(getPreferredSize());

    }

    /**
     * Generates a Animal at a specific Position.
     * @param x x-Position of the Animal
     * @param y y-Position of the Animal
     * @param z z-Position of the Animal
     */
    public Animal(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
        energy = (float) (50 + Math.random() * 200.0);
        isResting = false;
        this.color = new Color((int) (2.5 * z), 0, (int) (2.5 * z));


        velX = velY = 1.0f;
        velZ = 0.0f;

        setLocation(Math.round(x), Math.round(y));
        setSize(getPreferredSize());
    }

    public float getXCoor() {
        return this.x;
    }

    public void setXCoor(float x) {
        this.x = x;
    }

    public float getYCoor() {
        return this.y;
    }

    public void setYCoor(float y) {
        this.y = y;
    }

    public float getVelX() {
        return velX;
    }

    public void setVelX(float velX) {
        this.velX = velX;
    }

    public float getVelY() {
        return velY;
    }

    public void setVelY(float velY) {
        this.velY = velY;
    }

    public Color getColor() {
        return this.color;
    }

    public void setColor(Color c) {
        this.color = c;
    }

    public float getZCoor() {
        return z;
    }

    public void setZCoor(float z) {
        this.z = z;
    }

    public float getVelZ() {
        return velZ;
    }

    public void setVelZ(float velZ) {
        this.velZ = velZ;
    }

    public boolean isResting() {
        return isResting;
    }

    public float getEnergy() {
        return energy;
    }

    public boolean getHasEaten(){
        return hasEaten;
    }

    public void setHasEaten(boolean hasEaten) {
        this.hasEaten = hasEaten;
    }
    public void resetTimerToEat(){timerToEat=1000;}
    public void decreaseTimerToEat(){timerToEat--;}


    /**draws the animal as a colored rectangle
     * This method should only be called by the Java Swing Library
     **/
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(color);
        g.fillRect(0, 0, animalSize, animalSize);
    }

    /**
     * Sets the size of the animal depending on its hieght, so it can be drawn correctly with Swing.
     * @return Dimension of the Animal
     */
    @Override
    public Dimension getPreferredSize() {
        //ANMERKUNG:calculates the size of the animal in regards to how far away it is (default value=10, can be changed by altering the factor z is multiplied by in line 107)
        animalSize = (int) (((float) maxAnimalSize / 100) * z);
        if (animalSize < minAnimalSize) {
            animalSize = minAnimalSize;
        } else if (animalSize > maxAnimalSize) {
            animalSize = maxAnimalSize;
        }
        return new Dimension(animalSize, animalSize);
    }


    /**
     * GUT: Die Methode ist essentiell (hoher Klassenzusammenhalt) für die Klasse, da ohne dieser Methode keine
     * neue Position basierend auf den Richtungsvektor berechnet werden kann.
     * Sets the new postion of the Animal depending on the direction of the velocity vector.
     */
    public void updatePos() {
        //energy management
        //no energy left, wants to rest
        if (energy <= 0 && (this.z > 1) && !isResting) {
            isResting = false;
            this.velY = 0.0f;
            this.velX = 0.0f;
            this.velZ = -0.75f;

            //no energy left, "landed" (z=0)
        } else if (energy <= 0 && this.z <= 1 && !isResting) {
            isResting = true;
            this.velY = this.velX = this.velZ = 0.0f;
            //is resting at the moment
        } else if (isResting) {
            energy += chargingFactor;
            this.velY = this.velX = this.velZ = 0.0f;
            //normal case (not resting)
        } else {
            energy -= 0.15f;

        }

        //case, in which the bird stops resting
        //(50.0f+Math.random()*200.0f) determines an energy level between 50(inclusive) and 250(exclusive), at which the bird feels rested
        if (isResting && energy > (50.0f+Math.random()*200.0f)) {
            isResting = false;
            this.velY = this.velX = 1.0f;
            this.velZ = 1.0f;
        }

        //checks whether the Z-Velocity is too big/small. Limits it to a value between -1.0f and 1.0f
        if (velZ < -1.0f) {
            velZ = -1.0f;
        } else if (velZ > 1.0f) {
            velZ = 1.0f;
        }
        //calculates new values for the coordinates
        this.x += this.velX;
        this.y += this.velY;

        //checks whether the Z-Coordinate is too big/small. Limits it to a value between 0 and 100;
        this.z += this.velZ;
        if (this.z <= 0) {
            this.z = 0;
        } else if (this.z > 100.0f) {
            this.z = 100;
        }
       // System.out.println("z: " + this.z + " / zVel: " + this.velZ + " // ENERGY:" + energy);

        setLocation(Math.round(this.x), Math.round(this.y));
        setSize(getPreferredSize());

        //casts the value of z (Range: 0-100) onto the right range for the color calculation (Range: 0-255)
        //float colorZ = (((z) * 255.0f) / 100.0f);
        //sets the color of the bird in regard to its Z-Coordinate.
        //setColor(new Color((int) (colorZ), 0, (int) (colorZ)));

    }
    @Deprecated
    /**
     * Sets the new postion of the Animal depending on the direction of the velocity vector.
     */
    public void updatePosold() {

        this.x += this.velX;
        this.y += this.velY;
        this.z += this.velZ;

        if (this.z < 0) {
            this.z = 0;
            this.velZ = 0;
        }
        System.out.println("z: " + this.z + " / zVel: " + this.velZ);
        setLocation(Math.round(this.x), Math.round(this.y));
        setSize(getPreferredSize());
        setColor(new Color((int) (2.5 * z), 0, (int) (2.5 * z)));
    }

    /**
     * checks, if the animal is near the border of the window
     * @param borderSize
     * @param x length of the window
     * @param y width of the window
     * @param z height of the window
     * @return true if the Animal is in BorderRange
     */
    //ANMERKUNG: Grenze wurde ausgeschalten. NOT USED. Javadoc for this code should say: returns false
    public boolean isInBorderRange(float borderSize, int x, int y, int z) {
        return false;
        /*boolean result = false;
        if (this.getXCoor() < borderSize || this.getXCoor() > x - borderSize - 25.0f ||
                this.getYCoor() < borderSize || this.getYCoor() > y - borderSize - 40.0f ||
                this.getZCoor() < 0.0f || this.getZCoor() > x) {
            result = true;
        }
        return result;*/

    }

    /** Generates a new Animal at coordinates x, y, z **/
    public Animal generateNew(float x, float y, float z){
        return new Animal(x, y, z);
    }

    @Override
    public String toString() {
        return "Animal{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", velX=" + velX +
                ", velY=" + velY +
                ", velZ=" + velZ +
                '}';
    }
}
