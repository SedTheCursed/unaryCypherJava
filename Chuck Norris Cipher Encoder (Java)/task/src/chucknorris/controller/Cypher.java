package chucknorris.controller;

import java.util.Objects;

// Commented code in from step 3 to be used in step 5
public class Cypher {
    private String message;

    public Cypher(String message) {
        this.message = message;
    }

    public String encrypt() {
        return toBinary().toUnary().message;
    }

    public String decrypt() {
        return fromUnary().fromBinary().message;
    }

    private Cypher toBinary() {
        StringBuilder binaries = new StringBuilder(message.length() * 7);

        for (int i =0; i < message.length(); i++) {
            int binary = Integer.parseInt(
                    Integer.toBinaryString(
                            Character.codePointAt(message,i)
                    )
            );
            binaries.append(String.format("%07d", binary));
        }

        message = binaries.toString();
        return this;
    }

    private Cypher toUnary() {
        StringBuilder unaries = new StringBuilder();
        unaries.append(selectFirstHeader(message.charAt(0)));

        for (int i = 1; i < message.length(); i++) {
            if (message.charAt(i) != message.charAt(i - 1)) unaries.append(selectHeader(message.charAt(i)));
            unaries.append("0");
        }

        message = unaries.toString();
        return this;
    }

    private Cypher fromUnary() {
        if (message.isEmpty()) throw new IllegalArgumentException("Empty message");
        String[] unaries = message.split(" ");
        if (unaries.length % 2 != 0) throw new IllegalArgumentException("Invalid length");

        StringBuilder binaryString = new StringBuilder();

        for (int i = 0; i < unaries.length; i += 2 ) {
            if(isInvalidKey(unaries[i])) throw new IllegalArgumentException("Invalid key");
            if(isInvalidLength(unaries[i + 1])) throw new IllegalArgumentException("Invalid characters");
            String c = Objects.equals(unaries[i], "00") ? "0":"1";
            binaryString.append(c.repeat(unaries[i + 1].length()));
        }

        message = binaryString.toString();
        return this;
    }

    private Cypher fromBinary() {
        if (message.length() % 7 != 0) throw new IllegalArgumentException("Invalid number of bytes");
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < message.length(); i += 7) {
            char character = (char) Integer.parseInt(message.substring(i, i + 7), 2);
            result.append(character);
        }

        message = result.toString();
        return this;
    }


    private String selectHeader(char number) {
        return (number == '1') ? " 0 " : " 00 ";
    }

    private String selectFirstHeader(char number) {
        return String.format("%s 0", selectHeader(number).trim());
    }

    private boolean isInvalidKey(String key){
        return !Objects.equals(key, "0") && !Objects.equals(key, "00");
    }

    private boolean isInvalidLength(String length) {
        return length.matches("[^0]");
    }
}
