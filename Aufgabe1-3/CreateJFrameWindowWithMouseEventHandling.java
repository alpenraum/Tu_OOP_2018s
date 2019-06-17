import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JFrame;


/** Creates a JFrame window incl. Mouselistener, this frame knows settings, swarms, foodsources and background
 *
 * BAD: starke Objektkopplung, beim Verschieben des Fensters werden alle Objekte verschoben. Diese müssen beim Erstellen des Fensters übergeben werden.
 * Wie eine bessere Lösung aussehen könnte: alle Objekte mit in die Klasse Settings aufnehmen.
 *
 * */
public class CreateJFrameWindowWithMouseEventHandling extends JFrame implements MouseListener {

    private static final long serialVersionUID = 1L;
    private Settings settings;
    private ArrayList<Swarm> allSwarms;
    private Background bg;
    private FoodSource food;

    /**Constructor */
    public CreateJFrameWindowWithMouseEventHandling(Settings settings,ArrayList<Swarm> allSwarms, Background bg, FoodSource food) {
        setTitle("Schwarm Simulator");
        addMouseListener(this);
        this.allSwarms = allSwarms;
        this.bg = bg;
        this.settings = settings;
        this.food = food;

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

    }

    @Override
    public void mouseExited(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

    }

    /**Calculates the difference between the position, where the mouse was released, and calls the method moveEverything */
    @Override
    public void mouseReleased(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        int xMov;
        int yMov;


        if (x < settings.getWindowX()/2) {
            xMov = settings.getWindowX()/2 - x;
        } else {
            xMov = -(x- settings.getWindowX()/2);
        }

        if (y < settings.getWindowY()/2) {
            yMov = settings.getWindowY()/2 - y;
        } else {
            yMov = -(y- settings.getWindowY()/2);
        }


        moveEverything(xMov, yMov);

    }

    /**ANMERKUNG: OLD CODE, NOT USED. moves all elements */
    public void moveEverything(ArrayList<Swarm> allSwarms, FoodSource food, Background bg, float x, float y) {


        for (Swarm s : allSwarms) {
        s.moveAllBirds(x,y);
        s.updateVelocity();
        }

        bg.setOffset((int)x,(int)y);
        bg.repaint();
    }

    /** Moves every Swarm, Background and FoodSource in the JFrame window by x and y coordinates.
     *
     * BAD: Diese Methode könnte in der Methode mouseReleased realisiert werden.
     *      Entstanden ist dieser Code, weil die zu bewegenden Objekte ursprünglich nicht
     *      im JFrame Fenster vorhanden waren.
     *      @param x x-direction
     *      @param y y-direction
     *
     */
    public void moveEverything(float x, float y) {
        for (Swarm s : this.allSwarms ) {
            s.moveAllBirds(x,y);
            s.updateVelocity();
        }
        this.bg.setOffset((int) x, (int) y);
        this.bg.repaint();

        this.food.setCurrX(this.food.getCurrX()+x);
        this.food.setCurrY(this.food.getCurrY()+y);
        this.food.repaint();
    }

    /**
     * Creates and initializes a window
     * @param settings Properties which the window is going to have
     * @param allSwarms ArrayList of all Swarms which are going to be displayed in this Window
     * @param bg Background image of the Window
     * @param food FoodSource which is going to be displayed in this window
     * @return Initialized and setup Window
     *
     * createAndShowGUI must be called RIGHT AFTER the Constructor and BEFORE any other Methods
     *
     * BAD: Starke Objektkopplung, es müssen viele verschiedene Objekte übergeben werden.
     * Besser lösen könnte man es, indem Schwärme, backgrounds und foodsources in die Settings integriert werden.
     */
    public static JFrame createAndShowGUI(Settings settings,ArrayList<Swarm> allSwarms,Background bg, FoodSource food) {

        JFrame frame = new CreateJFrameWindowWithMouseEventHandling(settings,allSwarms,bg, food);

        frame.setSize(settings.getWindowX(),settings.getWindowY());
        frame.setLayout(null);
        frame.setVisible(true);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        return frame;
    }





}