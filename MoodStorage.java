package Mental_Health_Simulation;

import java.io.*;
import java.util.*;

public class MoodStorage {
    private String filePath;

    public MoodStorage(String filePath) {
        this.filePath = filePath;
    }

    public void saveMood(String mood) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(mood);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Failed to save mood history.");
            e.printStackTrace();
        }
    }

    public String loadHistory() {
        StringBuilder history = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                history.append(line).append("\n");
            }
        } catch (IOException e) {
            history.append("No history available.");
        }
        return history.toString();
    }
}