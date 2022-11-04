package HangMan.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

public class DictionaryWordPicker implements WordPicker {
    private final List<String> lines;
    private final Random rnd = new Random();

    public DictionaryWordPicker(String dictionaryPath) throws IOException {
        lines = Files.readAllLines(Paths.get(dictionaryPath));
    }

    @Override
    public String getWord() {
        var w = lines.get(rnd.nextInt(lines.size()));
        System.out.println(w);
        return w;
    }
}
