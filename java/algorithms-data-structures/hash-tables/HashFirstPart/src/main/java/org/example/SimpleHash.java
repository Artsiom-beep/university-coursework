package org.example;

import java.util.LinkedList;

public class SimpleHash {

    LinkedList<Pair>[] ourList;
    int size;

    public SimpleHash(int size){
        this.size = size;
        ourList = new LinkedList[size];

        for(int i = 0; i < size; i++){
            ourList[i] = new LinkedList<>();
        }
    }


    private int getIndex(String key){
        return key.hashCode() & Integer.MAX_VALUE % size;
        //int A = 1/2;
        //return size * ((key.hashCode() & Integer.MAX_VALUE * A) % 1);
    }

    public void put(String key, int value){
        int index = getIndex(key);
        for(Pair p : ourList[index]){
            if(p.key.equals(key)){
                p.value = value;
                return;
            }
        }
        ourList[index].add(new Pair(key, value));
    }

    public Integer get(String key){
        int index = getIndex(key);
        for(Pair p : ourList[index]){
            if(p.key.equals(key)){
                return p.value;
            }
        }
        return null;
    }

    public boolean remove(String key){
        int index = getIndex(key);
        for(Pair p : ourList[index]){
            if(p.key.equals(key)){
                ourList[index].remove(p);
                return true;
            }
        }
        return false;
    }

}
