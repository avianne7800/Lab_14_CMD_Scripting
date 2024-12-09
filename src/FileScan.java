import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.CREATE;

public class FileScan {
    public static void main(String[] args) {
        JFileChooser jFile = new JFileChooser();
        File selectedFile;
        String rec = "";
        String regex = "[,\\.\\s]";
        int charCount = 0;
        int wordCount = 0;

        try {
            File workingDirectory = new File(System.getProperty("user.dir"));

            jFile.setCurrentDirectory(workingDirectory);

            if (jFile.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                selectedFile = jFile.getSelectedFile();
                Path file = selectedFile.toPath();

                InputStream in = new BufferedInputStream(Files.newInputStream(file, CREATE));
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                int line = 0;
                while (reader.ready()) {
                    rec = reader.readLine();
                    line++;
                    System.out.printf("\nLine %4d %-60s ", line, rec);
                    // character and word count functions
                    String[] myArray = rec.split(regex);
                    wordCount = wordCount + myArray.length;
                    charCount = charCount + rec.length();
                }
                // character and word count display
                System.out.println("\n\nWord count: " + wordCount);
                System.out.println("\n\nCharacter count: " + charCount);

                reader.close();
                System.out.println("\n\nData file read!");
            }
            else
            {
                System.out.println("No file selected!!! ... exiting.\nRun the program again and select a file.");
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found!!!");
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
