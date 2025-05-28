import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        //System.out.println(splitUsingPunctuation("CleanedMetamorphosis.txt"));
        List<String> sentences = splitUsingPunctuation("CleanedMetamorphosis.txt");
        List<String> cleanedList = new ArrayList<>();
        sentences.forEach(s -> {
            String cleaned = s.replaceAll("[\\p{Punct}]", "")
                    .toLowerCase()
                    .replaceAll("\\s+", " ")
                    .trim();
            if (!cleaned.isEmpty()) {
                cleanedList.add(cleaned);
            }
        });
//        cleanedList.forEach(System.out::println);
        System.out.println(splitToWordsDeleteStop(cleanedList));




    }

    public static List<String> splitToWordsDeleteStop(List<String> sentences){
        List<String> stopWords = Arrays.asList("and", "the", "he", "she", "a");
        List<String> result = new ArrayList<>();

        sentences.forEach(sentence -> {
            String[] words = sentence.split("\\s+");
            Arrays.asList(words).forEach(word -> {
                if (!stopWords.contains(word) && !word.isEmpty()) {
                    result.add(word);
                }
            });
        });

        return result;
    }

    public static List<String> splitUsingPunctuation(String filePath) throws IOException {
        String content = Files.readString(Path.of(filePath));
        String[] sentences = content.split("[.!?]");
        List<String> result = new ArrayList<>();

        Arrays.asList(sentences).forEach(sentence -> {
            String trimmed = sentence.trim();
            if (!trimmed.isEmpty()) {
                result.add(trimmed);
            }
        });

        return result;

    }



}
