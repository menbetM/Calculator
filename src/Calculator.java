import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) throws Exception {


        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        System.out.println(calc(input));


        }


    public static String calc(String input) throws Exception {
        String[] strings = input.split(" ");
        if (strings.length != 3) {
            throw new Exception("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }
        int a;
        int b;
        String c;
        boolean thisIsArabian = false;
        try {
            a = Integer.parseInt(strings[0]);
            b = Integer.parseInt(strings[2]);
            thisIsArabian = true;
        } catch (Exception e) {
            try {
                a = Roman.valueOf(strings[0]).getArabic();
                b = Roman.valueOf(strings[2]).getArabic();
            } catch (Exception ex) {
                throw new Exception("Формат операндов не верен");
            }
        }

        c = strings[1];

        if (a > 10 || b > 10 || a < 1 || b < 1) {
            throw new Exception("Вы ввели число не от 1 до 10");
        }

        int result;
        if (c.equals("+")) {
            result = a + b;
        } else if (c.equals("-")) {
            result = a - b;
        } else if (c.equals("*")) {
            result = a * b;
        } else if (c.equals("/")) {
            result = a / b;
        } else {
            throw new Exception("Нет такого знака");
        }

        if (thisIsArabian) {
            return String.valueOf(result);
        } else {
            if (result < 1) {
                throw new Exception("Результат меньше 1");
            }
            int remainder = result % 10;
            int tenth = result - remainder;

            return getRomanByArabian(tenth) + getRomanByArabian(remainder);
        }
    }

    public static String getRomanByArabian(int arabian){
        for(Roman num : Roman.values()){
            if(arabian == num.getArabic())
                return num.name();
        }
        return "";
    }
}
