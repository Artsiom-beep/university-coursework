public class Main1 {

    public static int flipBit(int value, int nitIndex) {
        nitIndex--; // Преобразуем индекс в 0-базированный
        char[] truevalue = Integer.toBinaryString(value).toCharArray(); // Преобразуем число в двоичную строку
        truevalue[nitIndex] = (truevalue[nitIndex] == '0') ? '1' : '0'; // Переключаем бит
        int result = Integer.parseInt(new String(truevalue), 2); // Преобразуем обратно в число
        System.out.println("Binary: " + new String(truevalue)); // Вывод двоичного представления
        return result;
    }

    public static void main(String[] args) {
        int value = 0b1001101; // Двоичное число
        int nitIndex = 3; // Индекс бита для переключения
        int result = flipBit(value, nitIndex);
        System.out.println("Result: " + result); // Вывод результата
    }
}