package org.example;

public class HashTableOpenAddressing {
    int DEFAULT_SIZE = 1000;
    int size;
    double loadTableFactor = 0.75;
    int takenSize = 0;
    Student[] table;
    boolean[] helpTable;
    Student[] newStudents;
    boolean[] newHelpTable;




    HashTableOpenAddressing(int size){
        this.size = size;
        table = new Student[size];
        helpTable = new boolean[size];
    }

    HashTableOpenAddressing(){
        size = DEFAULT_SIZE;
        table = new Student[size];
        helpTable = new boolean[size];
    }


    public void resize(){
        size *= 2;
        newStudents = table;
        newHelpTable = helpTable;
        table = new Student[size];
        helpTable = new boolean[size];
        for(int i = 0; i < newStudents.length; i++){
            if(newHelpTable[i] && newStudents[i] != null){
                add(newStudents[i]);
            }

        }
    }

    public void add(Student o){

        if(takenSize > size * loadTableFactor){
            resize();
        }

        int i = 0;
        int index = hashFunc(o, i);
        while(table[index] != null){

            if(table[index].equals(o)){
                helpTable[index] = true;
                return;
            }
            else if(helpTable[index] == false){
                helpTable[index] = true;
                table[index] = o;
                return;
            }
            i++;
            index = hashFunc(o, i);
        }
        table[index] = o;
        helpTable[index] = true;
        takenSize++;
    }

    public void delete(Student o){
        int i = 0;
        int index = hashFunc(o, i);
        while(table[index] != null){
            if(helpTable[index]) {
                if (table[index].equals(o)) {
                    helpTable[index] = false;
                }
            }
            i++;
            index = hashFunc(o, i);
        }
    }

    public boolean find(Student o){
        int i = 0;
        int index = hashFunc(o, i);
        while(table[index] != null){
            if(helpTable[index]) {
                if (table[index].equals(o)) {
                    return true;
                }
            }
            i++;
            index = hashFunc(o, i);
        }
        return false;
    }

    private int hashFunc(Student o, int i){
        int key = o.getString().hashCode() & Integer.MAX_VALUE % size;
        int id = fun1(key, i);
        return id;
    }

    private int fun1(int a, int i){
        return (a + i) % table.length;
    }

    private int fun2(int a, int i){
        int A = 1, B = 9;
        return (a + A*i + B * (i*i)) % size;
    }

    private int fun3(int a, int i){
        return ((a%size) + i * (1 + (a%(size-1)))) % size;
    }



}
