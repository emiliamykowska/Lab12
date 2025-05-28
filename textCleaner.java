import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class textCleaner {

    public static String extractCoreText(String filePath) throws IOException {
        List<String> lines = Files.readAllLines(Path.of(filePath));

        String startMarker = "*** START OF THIS PROJECT GUTENBERG EBOOK METAMORPHOSIS ***";
        String endMarker = "*** END OF THIS PROJECT GUTENBERG EBOOK METAMORPHOSIS ***";

        int startIndex = -1;
        int endIndex = -1;

        // Find start index (first occurrence)
        for (int i = 0; i < lines.size(); i++) {
            if (lines.get(i).contains(startMarker)) {
                startIndex = i + 1; // content starts after this line
                break;
            }
        }

        // Find end index (last occurrence)
        for (int i = lines.size() - 1; i >= 0; i--) {
            if (lines.get(i).contains(endMarker)) {
                endIndex = i;
                break;
            }
        }

        if (startIndex == -1 || endIndex == -1 || startIndex >= endIndex) {
            throw new IOException("Could not find proper start and end markers.");
        }

        List<String> coreLines = lines.subList(startIndex, endIndex);

        return String.join("\n", coreLines);
    }

    public static void main(String[] args) {
        try {
            String cleanedText = extractCoreText("Book.txt");
            System.out.println("Text length: " + cleanedText.length());
            System.out.println(cleanedText.substring(0, Math.min(1000, cleanedText.length())));
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
