import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
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
        cleanedList.forEach(System.out::println);



    }

    public static List<String> splitUsingPunctuation(String filePath) throws IOException {
        String content = Files.readString(Path.of(filePath));
        String[] sentences = content.split("[.!?]");
        List<String> result = new ArrayList<>();

        for (String sentence : sentences) {
            String trimmed = sentence.trim();
            if (!trimmed.isEmpty()) {
                result.add(trimmed);
            }
        }

        return result;

    }



}
