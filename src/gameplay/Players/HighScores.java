package gameplay.Players;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HighScores {

    private List<Integer> highScores;

    private void loadHighScores() {
        try {
            File file = new File("high_scores.txt");
            if (file.exists()) {
                List<String> lines = Files.readAllLines(Path.of("high_scores.txt"));
                highScores = new ArrayList<>();
                for (String line : lines) {
                    highScores.add(Integer.parseInt(line.trim()));
                }
                Collections.sort(highScores, Collections.reverseOrder());
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }

    private void saveHighScores() {
        try {
            Path filePath = Paths.get("high_scores.txt");
            Files.write(filePath, Collections.singleton(""), StandardOpenOption.CREATE);

            for (int score : highScores) {
                Files.writeString(filePath, Integer.toString(score) + System.lineSeparator(), StandardOpenOption.APPEND);
            }
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }

    public void updateHighScores(int newScore) {
        highScores.add(newScore);
        Collections.sort(highScores, Collections.reverseOrder());
        highScores = highScores.subList(0, Math.min(highScores.size(), 8));
        saveHighScores();
    }
}
