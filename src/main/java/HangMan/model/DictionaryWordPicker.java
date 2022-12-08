package HangMan.model;

import org.di.annotations.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;
@Component
public class DictionaryWordPicker implements WordPicker {
    private final String DICTIONARY_PATH = "/home/felix/_Programming/Idea_Projects/OOP/HangManGame/src/main/resources/words.txt";
    private final List<String> lines;
    private final Random rnd = new Random();

    public DictionaryWordPicker() throws IOException {
        lines = Files.readAllLines(Paths.get(DICTIONARY_PATH));
    }

    @Override
    public String getWord() {
        var w = lines.get(rnd.nextInt(lines.size()));
        System.out.println(w);
        return w;
    }
}
