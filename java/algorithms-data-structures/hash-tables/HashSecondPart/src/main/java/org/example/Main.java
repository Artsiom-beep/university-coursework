package org.example;

public class Main {
    public static void main(String[] args) {

        HashTableOpenAddressing table = new HashTableOpenAddressing(10);

        // Создаём несколько студентов
        Student s1 = new Student("Ivan", "Ivanov", 1);
        Student s2 = new Student("Anna", "Petrova", 2);
        Student s3 = new Student("Oleg", "Sidorov", 3);
        Student s4 = new Student("Maria", "Smirnova", 4);

        // Добавляем студентов
        table.add(s1);
        table.add(s2);
        table.add(s3);

        System.out.println("После добавления студентов:");
        System.out.println("Ivan найден: " + table.find(s1));
        System.out.println("Anna найдена: " + table.find(s2));
        System.out.println("Oleg найден: " + table.find(s3));
        System.out.println("Maria найдена: " + table.find(s4));

        // Удаляем одного
        table.delete(s2);
        System.out.println("\nПосле удаления Анны:");
        System.out.println("Anna найдена: " + table.find(s2));

        // Добавляем нового
        table.add(s4);
        System.out.println("\nПосле добавления Марии:");
        System.out.println("Maria найдена: " + table.find(s4));

        // Проверяем, что остальные остались
        System.out.println("\nПроверяем остальных:");
        System.out.println("Ivan найден: " + table.find(s1));
        System.out.println("Oleg найден: " + table.find(s3));
    }
}