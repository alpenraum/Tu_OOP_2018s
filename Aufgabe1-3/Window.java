import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;


public class Window extends JLabel {
    /**
     * String which saves the path to the background image
     */
    private String path;

    /**
     * Constructs a Window Object with a background image
     * @param path Path to the background image
     *             path must be relative (e.g. /image/background.jpg)
     *             path may not have any backslashes (\)
     */
    public Window(String path){
        super(new ImageIcon(path));
        this.path=path;


    }


    /**
     * resizes the background image.
     * @param x desired width of the resized background
     * @param y desired height of the resized background
     * if (x<0) or (y<0), the images keeps its original size
     */
    public void resizeBackground(int x, int y){
        ImageIcon temp=new ImageIcon(path);
        Image img = temp.getImage().getScaledInstance(x,y, Image.SCALE_SMOOTH);
        temp=new ImageIcon(img);
        this.setIcon(temp);

    }
    /**returns an array with the x and y sizes of the background image.
     * X-Coordinate is at position 0 of the returned array, Y-Coordinate at position 1 */
    public int[] getBackgroundSize(){
        ImageIcon temp=new ImageIcon(path);
        return new int[]{temp.getIconWidth(),temp.getIconHeight()};
    }



}
