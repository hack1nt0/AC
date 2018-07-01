package main;

public class SimpleCalculator {
    public int calculate(String input) {
        int i = 0;
        int a = 0, b = 0;
        while (i < input.length() && Character.isDigit(input.charAt(i)))
            a = a * 10 + input.charAt(i++) - '0';
        char op = input.charAt(i++);
        while (i < input.length() && Character.isDigit(input.charAt(i)))
            b = b * 10 + input.charAt(i++) - '0';
        int ans = 0;
        switch (op) {
            case '+' :
                ans = a + b;
                break;
            case '-' :
                ans = a - b;
                break;
            case '*' :
                ans = a * b;
                break;
            case '/' :
                ans = a / b;
                break;
            default :
                throw new RuntimeException();
        }
        return ans;
    }
}
