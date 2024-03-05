import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Привет я калькулятор!");

        while (true) {

            System.out.print("ВВедите нужное вам выражение: ");
            String expression = sc.nextLine();
            if (expression.equals("exit")) {
                System.out.println("Прощайте");
                break;
            }
            String[] parts = expression.split(" ");
            if (parts.length != 3) {
                throw new IllegalArgumentException ("Формат математической операции не удовлетворяет задание!");
               }
            String operator = parts[1];
                int num1;
                int num2;
                if (isRoman(parts[0]) && isRoman (parts[2])) {
                num1 = convertToArabic(parts[0]);
                num2 = convertToArabic(parts[2]);
                }
                else if (!isRoman(parts[0]) && !isRoman(parts[2])){
                    num1 = Integer.parseInt(parts[0]);
                    num2 = Integer.parseInt(parts[2]);
                }
                else {
                    throw new IllegalArgumentException("Нельзя проводить операцию между римскими и арабскими числами");
                }
                int result = calculate(num1, operator, num2);
                if (result<0){
                    throw new IllegalArgumentException("Число не может быть отрицательным");
                }
                if (isRoman(parts[0]) && isRoman(parts[2])) {
                    System.out.println("Результат: " + convertToRoman(result));
                } else {
                    System.out.println("Результат: " + result);
                }

        }
    }

        public static String convertToRoman(int number) {

            StringBuilder roman = new StringBuilder();

            while (number >= 100) {
                roman.append("C");
                number -= 100;
            }
            while (number >= 90) {
                roman.append("XC");
                number -= 90;
            }
            while (number >= 50) {
                roman.append("L");
                number -= 50;
            }
            while (number >= 40) {
                roman.append("XL");
                number -= 40;
            }
            while (number >= 10) {
                roman.append("X");
                number -= 10;
            }
            while (number >= 9) {
                roman.append("IX");
                number -= 9;
            }
            while (number >= 5) {
                roman.append("V");
                number -= 5;
            }
            while (number >= 4) {
                roman.append("IV");
                number -= 4;
            }
            while (number >= 1) {
                roman.append("I");
                number -= 1;
            }

            return roman.toString();
        }

        public static int convertToArabic(String roman) {
            int result = 0;

            // Проходим по символам римского числа справа налево
            for (int i = roman.length() - 1; i >= 0; i--) {
                char currentChar = roman.charAt(i);

                // Получаем значение текущего символа
                int currentValue = romanCharToInt(currentChar);

                // Если значение предыдущего символа меньше текущего, вычитаем его
                if (i < roman.length() - 1 && romanCharToInt(roman.charAt(i + 1)) > currentValue) {
                    result -= currentValue;
                } else {
                    // Иначе, прибавляем значение текущего символа
                    result += currentValue;
                }
            }

            return result;
        }

        // Метод для преобразования символа римской цифры в число
        private static int romanCharToInt(char c) {
            switch (c) {
                case 'I': return 1;
                case 'V': return 5;
                case 'X': return 10;
                case 'L': return 50;
                case 'C': return 100;
                default: throw new IllegalArgumentException("Недопустимый символ римской цифры: " + c);
            }
        }

    public static boolean isRoman (String input) {
        return  input.matches("[IVXLC]+");
    }

    public static int calculate(int num1, String operator, int num2) {
       if (num1<1 || num1>10 || num2<1 || num2>10) throw new RuntimeException("Вводимые числа должны быть от 1 до 10");
       int result;
       switch (operator) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            case "/":
                return num1 / num2;
            default:
                System.out.println("Недопустимая опперация!");
        }
        return 0;
    }
}