import java.util.ArrayList;
import java.util.Iterator;

/**
 * A Labor stores vivariums which are not being used (meaning: no swarm lives in this vivarium) and the swarms that currently occupy one of the
 * vivariums
 */
public class Labor {
    /**
     * Contains all vivariums of the labor, which are not assigned to a swarm.
     */
    private ArrayList<Vivarium> Inventarliste;
    /**
     * Contains all swarms of the labor which live in a vivarium.
     * A Vivarium cannot simultaneously be in Inventarliste and assigned to a Swarm in Swarmliste
     */
    private ArrayList<Swarm> Swarmliste;

    /**
     * Constructor
     */
    public Labor() {
        this.Inventarliste = new ArrayList<>();
        this.Swarmliste = new ArrayList<>();
    }

    /**
     * Adds a new vivarium to the Inventarliste.
     *
     * @param input new Vivarium which should be added
     */
    public void neu(Vivarium input) {
        this.Inventarliste.add(input);
    }

    /**
     * Removes a vivarium from the Inventarliste. Only vivariums which are not assinged
     * to any Swarm will be deleted!
     *
     * @param input Vivarium which should be deleted
     */
    public void defekt(Vivarium input) {
        this.Inventarliste.remove(input);
    }

    /**
     * Returns a proper vivarium to the swarm and assigned it to the swarm. When a proper one is
     * found for the Swarm, the vivarium will be removed from the Inventarliste and the Swarm
     * will be added to the Swarmliste.
     * When there is no proper vivarium for the Swarm, the method will return null and
     * Inventarliste and Swarmliste are not changed by this method. This means the Swarm will not be
     * added to the Swarmliste.
     *
     * @param input Swarm, where a proper vivarium should be found
     * @return the proper vivarium for the Swarm
     */
    public Vivarium stellebereit(Swarm input) {
        boolean found = false;
        Vivarium result = null;
        String name = input.getName();
        for(Swarm s: Swarmliste){
            if (name.equals(s.getName())){return null;}
        }

        //best fitting Vivarium
        Iterator<Vivarium> iter = this.Inventarliste.iterator();
        while (iter.hasNext() && !found) {
            Vivarium v = iter.next();
            if (input.checkVivarium(v) == 1) {
                result = v;
                found = true;
            }
        }

        //fitting Vivarium
        iter = this.Inventarliste.iterator();
        while (iter.hasNext() && !found) {
            Vivarium v = iter.next();
            if (input.checkVivarium(v) == 0) {
                result = v;
                found = true;
            }
        }

        if (found) {
            input.insertIntoVivarium(result);
            this.Swarmliste.add(input);
            this.Inventarliste.remove(result);
        }

        return result;
    }

    /**
     * Deletes the assignment from a vivarium to a Swarm. The Swarm will be removed from
     * the Swarmliste and the vivarium will be added back to the Inventarliste.
     *
     * @param input Swarm, which should be removed
     */
    public void retourniere(Swarm input) {
        Vivarium v = input.getVivarium();
        Swarmliste.remove(input);
        if(v!=null) {
            input.deleteVivarium();
            Inventarliste.add(v);
        }
    }

    /**
     * Calculates the volume of all Vivariums in Invetarliste and prints it out in the console.
     */
    public void volumenfrei() {
        float volume = 0.0f;
        for (Vivarium v : Inventarliste) {
            volume += v.getVolume();
        }
        System.out.println("Summe der Volumen aller Vivarien in der Inventarliste: " + volume);
    }

    /**
     * Calculates the volume of all assigned Vivariums in Labor and prints it out in the console.
     */
    public void volumenbelegt() {
        float volume = 0.0f;

        for (Swarm s : Swarmliste) {
                volume += s.getVivarium().getVolume();
        }
        System.out.println("Summe der Volumen aller Vivarien, " +
                "die von Versuchstierschwärmen belegt sind: " + volume);
    }

    /**
     * Prints out all Information(Type, Dimension) of all Vivariums in Inventarliste.
     */
    public void inventarliste() {
        String out = "Inventarliste: \n";
        for (Vivarium v : Inventarliste) {
            out = out + "" + v.toString();
        }
        System.out.println(out);
    }

    /**
     * Prints out all Information(Type, Dimension, Assignment) of all Swarms in Schwarmliste.
     */
    public void schwarmliste() {
        String out = "Schwarmliste \n";
        for (Swarm s : Swarmliste) {
            out = out + "" + s.toString();
        }
        System.out.println(out);
    }
}
