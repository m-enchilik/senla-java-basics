package ru.enchilik.password;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Generator {
    private String password;
    private Random random = new Random();
    private final String[] alphabet = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q",
            "r", "s", "t", "u", "v", "w", "x", "y", "z"};
    private final String[] upperCaseAlphabet = Arrays.copyOf(Arrays.stream(alphabet)
                                        .map(a -> a.toUpperCase())
                                        .toArray(String[]::new),
                                        alphabet.length);

    private final String[] digits = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};
    private final String[] specialSymbols = {"!", "@", "#", "$", "%", "^", "&", "*", "/", "\\", "|", "?", "'", "-", "_", "=", "+"};
    private Map<Integer, String[]> symbols;

    public Generator() {
        password = "";
        symbols = new HashMap<>();
        initMap();
    }

    private Map<Integer, String[]> initMap() {
        symbols.put(1, alphabet);
        symbols.put(2, upperCaseAlphabet);
        symbols.put(3, digits);
        symbols.put(4, specialSymbols);
        return symbols;
    }

    public String createPass(int passLength) {
        for (int i = 0; i < passLength; i++) {
            String[] temp = symbols.get(random.nextInt(1, symbols.size() + 1));
            password = password.concat(temp[random.nextInt(temp.length)]);
        }
        return password;
    }
}