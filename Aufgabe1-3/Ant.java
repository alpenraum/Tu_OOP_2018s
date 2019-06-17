import javax.swing.*;
import java.awt.*;

public class Ant extends Animal {
    public Ant() {
        x = 0;
        y = 0;
        z = 0;
        energy = (float) (50 + Math.random() * 200.0f);
        this.color = Color.BLACK;
        velX = velY = velZ = 1.0f;
        setLocation(Math.round(x), Math.round(y));
        setSize(getPreferredSize());
    }

    public Ant(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
        energy = (float) (50 + Math.random() * 200.0);
        isResting = false;
        this.color = new Color(0, 0, 0);


        velX = velY = 1.0f;
        velZ = 0.0f;

        setLocation(Math.round(x), Math.round(y));
        setSize(getPreferredSize());
    }

    /** Generates a new Ant at coordinates x, y, z **/
    public Ant generateNew(float x, float y, float z){
        return new Ant(x, y, 0);
    }
}
