package chucknorris.view;

import chucknorris.enums.Action;

import java.util.Scanner;

public class UI {
    private static final Scanner sc = new Scanner(System.in);

    public static Action askAction() {
        println("Please input operation (encode/decode/exit):");

        String input = sc.nextLine();

        try {
            return Action.valueOf(input.toUpperCase());
        } catch (IllegalArgumentException e) {
            println(String.format("There is no '%s' operation", input));
            return Action.ERROR;
        }
    }

    public static String askPlainString() {
        println("Input string:");
        return sc.nextLine();
    }

    public static String askEncodedString() {
        println("Input encoded string:");
        return sc.nextLine().trim();
    }

    public static void printEncodedResult(String result) {
        println(String.format("Encoded string:\n%s", result));
    }

    public static void printDecipherResult(String result) {
        println(String.format("Decoded string:\n%s", result));
    }

    public static void printDecipherError(String msg) {
        println(String.format("Encoded string is not valid: %s", msg));
    }

    public static void close() {
        println("Bye!");
        sc.close();
    }

    private static void println(String msg) {
        System.out.println(msg);
    }
}
