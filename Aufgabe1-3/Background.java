import javax.swing.*;
import java.awt.*;


/**draws the background of the window **/
public class Background extends JComponent {
    private int x, y;
    private Color color;
    private Settings settings;

    /**
     * Constructor
     * @param settings Properties which the Background is going to have
     */
    public Background(Settings settings) {

        this.settings = settings;
        x = 0;
        y = 0;
        color = settings.getBgColor();

        setLocation(0, 0);
        setSize(new Dimension(settings.getWindowX(), settings.getWindowY()));

    }

    /**
     * Draws a grid of 20*20 Squares. These squares are 340*340 in Size and 350 apart.
     *
     * This method should only be called by the Java Swing Library
     */
    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(color);
        /** Paints a grid of 20*20 (i,j) squares. These squares are 340*340 and 350 apart. */
        for (int i = -10; i <= 10; i++) {
            for (int j = -10; j <= 10; j++) {
                g.fillRect(x + 350 * i, y + 350 * j, 340, 340);
            }
        }

    }

    /** Moves the background in x and y direction.
     *  @param x x-direction
     *  @param y y-direction
     */
    public void setOffset(int x, int y) {

        this.x += x;
        this.y += y;
    }


}
