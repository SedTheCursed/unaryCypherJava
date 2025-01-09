package chucknorris;

import chucknorris.controller.Cypher;
import chucknorris.enums.Action;
import chucknorris.view.UI;

public class Main {
    public static void main(String[] args) {
        Action currentAction;
        do {
            currentAction = UI.askAction();
            switch (currentAction) {
                case ENCODE -> encode();
                case DECODE -> decode();
                default -> {}
            }
        } while (currentAction != Action.EXIT);
        UI.close();
    }

    private static void encode() {
        String result = new Cypher(UI.askPlainString()).encrypt();
        UI.printEncodedResult(result);
    }

    private static void decode() {
        try {
            String result = new Cypher(UI.askEncodedString()).decrypt();
            UI.printDecipherResult(result);
        } catch (Exception e) {
            UI.printDecipherError(e.getMessage());
        }
    }
}
