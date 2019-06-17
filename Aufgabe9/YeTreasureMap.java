
import java.util.HashMap;

/**
 * Ahoy, me fellow sailors. Ye found me pride and joy, me Treasure map. This Treasure map be a Hashmap wit' a size limit.
 * If ye put somethin' in this map, th' map exceeds 'tis limit 'n th' key ain't already in th' map, th' tuple (key,value) will nah be added
 */
@WrittenBy(nameOfCreator = "Finn Zimmer", date ="14.12.2018")
public class YeTreasureMap extends HashMap<Integer, Double> {
    /**
     * Hashmap with the Data (Don't show it to anyone. It is your treasure)
     */
    private HashMap<Integer, Double> map;
    /**
     * maximum size this Treasure map be supposed t' 'ave.
     */
    private int maxSize;


    /**
     * Tells yer first mate t' loot yer Treasure Map
     * @return Hashmap (carefully kept by yer quartermaster)
     */
    public HashMap<Integer, Double> getMap() {
        return map;
    }

    /**
     * Tells th' map drawer t' create a new one or he will go o'er board
     * @param maxSize  maximum size this Treasure map be supposed t' 'ave. ('twould be a shame, if it could nah fit under yer coat)
     */
    public YeTreasureMap(int maxSize) {
        map = new HashMap<>();
        this.maxSize = maxSize;

    }

    /**
     * Only puts th' value into th' map, if th' hashmap ain't larger than th' maxSize;
     *@param key Where th' value be stored at
     *@param value Th' value that best be stored
     *@return 3 cases:
     *     1: th' ole value at this key, IF th' key was occupied 'n th' hashmap be weeer than maxSize
     *     2: null, if th' new value was stored 'n th' galleon was nah occupied by another value
     *     3: null, if th' hashMap be bigger than th' @param maxSize
     */
    public Double put(Integer key, Double value) {
        if (map.size() < maxSize && !map.containsKey(key)) {

            map.put(key, value);
            return null;

        }else if(map.containsKey(key)){
            Double temp = map.get(key);
            map.put(key,value);
            return temp;
        }

        return null;
    }

    /**
     * Returns the strength of the wish "wish", when the wish is not in the map,
     * the method will return 0.0;
     * @param wish wish which should be found
     * @return strength of the wish
     */
    public double getWishStrength(int wish){
        Double value = map.get(wish);

        if(value == null){
            value = 0.0;
        }

        return value;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return map.toString();
    }

}
