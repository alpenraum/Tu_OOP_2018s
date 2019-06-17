import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


/**
 * GOOD: Schwache Objektkopplung, FoodSources funktionieren unabhängig vom restlichen Programm
 * */
public class FoodSource extends JComponent {
    /**
     * Arraylist of the coordinates
     */
    private ArrayList xCoor, yCoor;
    /**
     * the Coordinates of the current Location of the food source
     */
    private float currX = 0.0f, currY = 0.0f;


    /**
     * decisive variable, whether a foodsource is visible
     */
    private boolean visible;
    /**
     * decisive Variable, whether a foodsource is only used once, or endless times
     */
    private boolean isDeleting = false;
    /**
     * A Pointer, that points to the current Coordinates if isDeleting is set to false
     */
    private int pointer = 0;
    /**
     * Saves the size and Color of a food source (for Java Swing)
     */
    private int size = 20;
    private Color color = new Color(0, 100, 0, 160);
//////////////////////////////////////

    /**
     * Default Constructor
     *
     * @param count the amount of food Sources there should be
     * Constructs a FoodSource object with random coordinates in the range [o;1000)
     */
    public FoodSource(int count) {
        xCoor = new ArrayList();
        yCoor = new ArrayList();
        for (int i = 0; i < count; i++) {
            float randX = ((float) Math.random() * 1000.0f);
            float randY = ((float) Math.random() * 1000.0f);

            xCoor.add(randX);
            yCoor.add(randY);

        }
        setSize(getPreferredSize());
        setLocation(Math.round(currX), Math.round(currY));
    }

    /**
     * Constructor for already defined coordinates.
     * Checks if one array of coordinates is longer than the other one and adds random values accordingly
     *
     * @param x Array of predefined x-Coordinates
     * @param y Array of predefined y-Coordinates
     */
    public FoodSource(float[] x, float[] y) {
        xCoor = new ArrayList();
        yCoor = new ArrayList();
        for (int i = 0; i < x.length; i++) {

            xCoor.add(x[i]);
            yCoor.add(y[i]);
        }
        if (xCoor.size() != yCoor.size()) {
            int diff = Math.abs(xCoor.size() - yCoor.size());
            if (xCoor.size() > yCoor.size()) {

                for (int k = 0; k < diff; k++) {
                    yCoor.add((float) Math.random() * 1000);
                }
            } else {
                for (int k = 0; k < diff; k++) {
                    xCoor.add((float) Math.random() * 1000);
                }
            }

        }
        setSize(getPreferredSize());
        setLocation(Math.round(currX), Math.round(currY));
    }

    /**
     * GETTER AND SETTER
     */
    public float getCurrX() {
        return currX;
    }

    public void setCurrX(float currX) {
        this.currX = currX;
    }

    public float getCurrY() {
        return currY;
    }

    public void setCurrY(float currY) {
        this.currY = currY;
    }

    public boolean isVisible() {
        return visible;
    }

    public boolean isDeleting() {
        return isDeleting;
    }

    public void setDeleting(boolean deleting) {
        isDeleting = deleting;
    }

    /**
     * sets the visibility according to [@param visible] and loads the next coordinates
     *
     * @param visible parameter which decides whether the foodsource is visible or not
     */
    public void setVisibility(boolean visible) {
        this.visible = visible;
        if (visible == true) {
            /**
             * if isDeleting==true, then the coordinates of the current food source are deleted,
             * otherwise they are being kept and used again
             */
            if (isDeleting) {
                currX = (float) xCoor.get(0);
                currY = (float) yCoor.get(0);
                xCoor.remove(0);
                yCoor.remove(0);
            } else {
                currX = (float) xCoor.get(pointer);
                currY = (float) yCoor.get(pointer);
                pointer = (pointer + 1) % xCoor.size();
            }
        }


    }
    /////////////////////////////////////

    public ArrayList getxCoor() {
        return xCoor;
    }

    public ArrayList getyCoor() {
        return yCoor;
    }

    @Override
    /**
     * Draws a filled Rectangle which represents the FoodSource
     * This method should only be called by the Java Swing Library
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setSize(getPreferredSize());
        setLocation(Math.round(currX), Math.round(currY));
        g.setColor(color);
        g.fillRect(0, 0, size, size);
    }

    @Override
    /**
     * returns a Dimension with the sizes defined in the object variable size
     */
    public Dimension getPreferredSize() {
        return new Dimension(size, size);
    }
}
