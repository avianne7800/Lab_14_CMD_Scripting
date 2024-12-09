import javax.swing.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.CREATE;

public class DataSaver {
    public static final Scanner in = new Scanner(System.in);
    public static final JFileChooser jFile = new JFileChooser();
    private static final ArrayList<String> ArrList = new ArrayList<>();

    public static void main(String[] args) {
        String firstName = "";
        String lastName = "";
        int ID = 0;
        String email = "";
        int yearOfBirth = 0;
        boolean done1 = false;
        boolean active = true;
        String fileName = "";

        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\data.txt");

        while(active) {
            done1 = false;
            while (!done1) {
                firstName = SafeInput.getNonZeroLenString(in, "First name");
                lastName = SafeInput.getNonZeroLenString(in, "Last name");
                ID = SafeInput.getRangedInt(in, "ID", 0, 999999);
                email = SafeInput.getNonZeroLenString(in, "Email");
                yearOfBirth = SafeInput.getRangedInt(in, "Year of Birth", 0, 9999);
                System.out.println(firstName + ", " + lastName + ", " + ID + ", " + email + ", " + yearOfBirth);
                done1 = SafeInput.getYNConfirm(in, "Is this correct?");
            }
            ArrList.add(firstName + ", " + lastName + ", " + ID + ", " + email + ", " + yearOfBirth);

            try {
                OutputStream out = new BufferedOutputStream(Files.newOutputStream(file, CREATE));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));

                for (String rec : ArrList) {
                    writer.write(rec, 0, rec.length());  // stupid syntax for write rec
                    // 0 is where to start (1st char) the write
                    // rec. length() is how many chars to write (all)
                    writer.newLine();  // adds the new line
                }
                writer.close(); // must close the file to seal it and flush buffer
                System.out.println("Data file written!");
            } catch (IOException e) {
                e.printStackTrace();
            }

            active = SafeInput.getYNConfirm(in,"Add more data?");
        }
    }
}
