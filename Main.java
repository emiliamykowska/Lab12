import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

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
        countWords(splitToWordsDeleteStop(cleanedList));




    }

    public static void countWords(List<String> words){
        Map<String, Integer> wordsCounter = new HashMap<>();

        words.forEach(word -> {
            wordsCounter.put(word, wordsCounter.getOrDefault(word, 0) + 1);
        });

        wordsCounter.forEach((key, value) -> {
            System.out.println(key + ": " + value);
        });

        List<Map.Entry<String, Integer>> sortedEntries = new ArrayList<>(wordsCounter.entrySet());
        sortedEntries.sort((a, b) -> b.getValue().compareTo(a.getValue()));

        sortedEntries.forEach(entry -> {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        });

        System.out.println("Most frequent words: ");
        for (int i = 0; i < 10;  i++) {
            if (i != 9){
            System.out.print(sortedEntries.get(i).getKey() + ", ");}
            else {
                System.out.print(sortedEntries.get(i).getKey());
            }
        }

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
