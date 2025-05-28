import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        System.out.println(splitUsingPunctuation("/home/emilia/Documents/Medical Informatics/II semestr/Object_Oriented_Programming/Lab12/CleanedMetamorphosis.txt"));

    }

    public static List<String> splitUsingPunctuation(String filePath) throws IOException {
        List<String> lines = Files.readAllLines(Path.of(filePath));
        List<String> result = new ArrayList<>();

        lines.forEach(line -> {
            String[] sentences = line.split("[.!?]");
            for (String sentence : sentences) {
                String trimmed = sentence.trim();
                if (!trimmed.isEmpty()) {
                    result.add(trimmed);
                }
            }
        });

        return result;

    }
}
