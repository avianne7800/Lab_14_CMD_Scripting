import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        File file;

        // Check if a command-line argument is provided
        if (args.length > 0) {
            file = new File(args[0]);
            if (!file.exists() || !file.isFile()) {
                System.err.println("File not found: " + args[0]);
                return;
            }
        } else {
            // If no argument, open JFileChooser
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                file = fileChooser.getSelectedFile();
            } else {
                System.out.println("No file selected.");
                return;
            }
        }

        System.out.println("File chosen: " + file.getName());

        int lineCount = 0;
        int wordCount = 0;
        int charCount = 0;

        // Process the file
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                System.out.println(line); // Print each line for visibility
                lineCount++;
                wordCount += line.split("\\s+").length; // Count words
                charCount += line.length(); // Count characters
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error reading file: " + file.getPath());
            return;
        }

        // Print summary
        System.out.println("\nSummary Report:");
        System.out.println("File Name: " + file.getName());
        System.out.println("Number of lines: " + lineCount);
        System.out.println("Number of words: " + wordCount);
        System.out.println("Number of characters: " + charCount);
    }
}