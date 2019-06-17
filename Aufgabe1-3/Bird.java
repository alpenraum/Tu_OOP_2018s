import javax.swing.*;
import java.awt.*;


//ANMERKUNG:Author: Finn Zimmer
public class Bird extends Animal {


    /**
     * Default Constructor
     * Constructs a Bird Object with the X- Y- and Z-Coordinates [0,0,50]
     * Sets the color of the Bird to BLACK
     */
    public Bird() {
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
     * Constructs a Bird Object
     * @param x X-Coordinate of the Bird
     * @param y Y-Coordinate of the Bird
     * @param z Z-Coordinate of the Bird
     */
    public Bird(float x, float y, float z) {
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

    /**
     * Generates a new Bird at coordinates x, y, z
     * @param x X-Coordinate where the new Bird should be
     * @param y Y-Coordinate where the new Bird should be
     * @param z Z-Coordinate where the new Bird should be
     * @return Returns a bird with the given Coordinates
     */
    public Bird generateNew(float x, float y, float z){
        return new Bird(x, y, z);
    }
}
