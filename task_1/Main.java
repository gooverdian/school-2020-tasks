import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

class Conversion {
    private static final long LETTERS_IN_RUSSIAN_ALPHABET = 33L;
    private final String source;
    private final String destination;
    public Conversion(String source, String destination) {
        this.source = source;
        this.destination = destination;
    }
    public boolean conversionIsPossible() {
        if (source.equals(destination)) {
            return true;
        }
        if (source.length() != destination.length() || (containsAllPossibleSymbols(source) && containsAllPossibleSymbols(destination))) {
            return false;
        }
        HashMap<Character, Character> conversionMap = new HashMap<>();
        for (int i = 0; i < source.length(); i++) {
            conversionMap.putIfAbsent(source.charAt(i), destination.charAt(i));
            if (conversionMap.get(source.charAt(i)) != destination.charAt(i)) {
                return false;
            }
        }
        return true;
    }
    private boolean containsAllPossibleSymbols(String word) {
        long distinctCharactersCount = word.chars()
                .mapToObj(c -> (char) c)
                .distinct()
                .count();
        return distinctCharactersCount == LETTERS_IN_RUSSIAN_ALPHABET;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] words = br.readLine().split(" ");
        Conversion conversion = new Conversion(words[0], words[1]);
        System.out.println(conversion.conversionIsPossible() ? 1 : 0);
    }
}
