package org.example;

public class Student {
    String name;
    String secName;
    int index;

    Student(String name, String secName, int index){
        this.name = name;
        this.secName = secName;
        this.index = index;
    }

    public String getString(){
        return name + secName + index;
    }

}
