import java.util.LinkedList;
public class JacksonDummyHash<E, Integer> {
    
    private LinkedList<E> key[];
    private LinkedList<Integer> value[];

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

    public int simpleHash(E data) {
        String temp = data + "";
        return temp.length();
    }

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
