package org.example;


public class Main {
    static void main() {

        SimpleHash hashTable = new SimpleHash(10);
        hashTable.put("Kot", 10);
        hashTable.put("Drug", 100);
        hashTable.put("Krot", 20);

        System.out.println(hashTable.get("Kot"));
        System.out.println(hashTable.get("Drug"));
        System.out.println(hashTable.get("Krot"));

        hashTable.remove("Krot");
        System.out.println(hashTable.get("Krot"));
    }
}
