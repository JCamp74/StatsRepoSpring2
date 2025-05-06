import java.util.LinkedList;
/**
 * JacksonDummyHash class. Contains a public method to put, and helper methods
 * resize, simpleHash, and containsKey to calculate the hash index, dynamic resizing and key collisions.
 * Only a quick implementation of the HashMap class in Java done during class, and looked for accuracy and speed.
 * 
 * @author Jackson Campbell
 * @version 1.0.0
 */
public class JacksonDummyHash<E, Integer> {
    
    private LinkedList<E> key[];
    private LinkedList<Integer> value[];

    /**
    * Constructor for the HashMap.
    * Intializes the list for the key and value LinkedLists.
    */
    public JacksonDummyHash() {
        key = new LinkedList[30];
        for(int i = 0; i < key.length; i++) {
            key[i] = new LinkedList<>();
        }
        value = new LinkedList[30];
        for(int i = 0; i < key.length; i++) {
            value[i] = new LinkedList<>();
        }
    }

    /**
    * simpleHash helper function.
    * Calculates the hash based on the string length of the data input.
    * @param data The generic data input for retrieving the hashing index.
    * @return the length of the string for the hash index.
    */
    public int simpleHash(E data) {
        String temp = data + "";
        return temp.length();
    }

    /**
    * Put method. 
    * Takes the data and value, and stores them within the HashMap based off of the created key.
    * Updates the map properly if a collision occurs.
    * @param data The generic data to add to the list.
    * @param val The value to map to data.
    */
    public void put(E data, Integer val) {
        int index = simpleHash(data);
        if(index > key.length && index > value.length) {
            resize();
        }

        if(containsKey(data)) {
            if(key[index].indexOf(data) != -1) {
                value[index].set(key[index].indexOf(data), val);
            }
            return;
        }     
        key[index].add(data);
        value[index].add(val);
    }

    /**
    * Resize helper method.
    * Dynamically resizes the array if the size is exceeded when placing values.
    */
    private void resize() {
        LinkedList<E> temp1[] = new LinkedList[key.length * 2];
        LinkedList<Integer> temp2[] = new LinkedList[key.length * 2];

        for(int i = 0; i < key.length; i++) {
            for(int j = 0; j < key[i].size() - 1; j++) {
                temp1[i].add(key[i].get(j));
            }
        }
        key = temp1;

        for(int i = 0; i < value.length; i++) {
            for(int j = 0; j < value[i].size() - 1; j++) {
                temp2[i].add(value[i].get(j));
            }
        }
        value = temp2;
    }

    /**
    * ContainsKey helper method.
    * Looks to see if the map contains a key within the key array.
    * If so, returns true. Otherwise is false.
    * @param existingKey The key to search for in the map.
    * @return the boolean value if the key is found.
    */
    private boolean containsKey(E existingKey) {
        for(int i = 0; i < key.length; i++) {
            for(int j = 0; j < key[i].size(); j++) {
                if(existingKey.equals(key[i].get(j))) {
                    return true;
                }
            }
            
        }
        
        return false;
    }

    /**
    * toString method.
    * Prints the list properly with the key and values paired together.
    * @return the properly arranged string of the map.
    */
    @Override
    public String toString() {
        String temp = "";

        for(int i = 0; i < key.length; i++) {
            for(int j = 0; j < key[i].size(); j++) {
                if(key[i].isEmpty()) {
                    break;
                }
                temp += "{" + key[i].get(j) + ", " + value[i].get(j) + "}\n";
            }
        }
        
        
        return temp;
    }
}
