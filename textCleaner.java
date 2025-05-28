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

        // Find start index
        for (int i = 0; i < lines.size(); i++) {
            if (lines.get(i).contains(startMarker)) {
                startIndex = i + 1;
                break;
            }
        }

        // Find end index
        for (int i = lines.size() - 1; i >= 0; i--) {
            if (lines.get(i).contains(endMarker)) {
                endIndex = i;
                break;
            }
        }

        if (startIndex == -1 || endIndex == -1 || startIndex >= endIndex) {
            throw new IOException("Markers not found or in the wrong order.");
        }

        List<String> coreLines = lines.subList(startIndex, endIndex);
        return String.join("\n", coreLines);
    }

    public static void main(String[] args) {
        try {
            String cleanedText = extractCoreText("Book.txt");

            // Write the cleaned text to a new file
            Path outputPath = Path.of("CleanedMetamorphosis.txt");
            Files.writeString(outputPath, cleanedText);

            System.out.println("✅ Cleaned text written to: " + outputPath.toAbsolutePath());
        } catch (IOException e) {
            System.err.println("❌ Error: " + e.getMessage());
        }
    }
}
