package HangMan.model;

import java.io.IOException;
import java.util.Map;
import java.util.WeakHashMap;

public class GameProcessor {
    private final String DICTIONARY_PATH = "/home/felix/_Programming/Idea_Projects/OOP/HangManGame/src/main/resources/words.txt";
    private final Map<String, GameInstance> gameInstances;
    private final WordPicker wordPicker;

    public GameProcessor() throws IOException {
        this.gameInstances = new WeakHashMap<>();
        wordPicker = new DictionaryWordPicker(DICTIONARY_PATH);
    }

    public GameInstance createOrResetForId(String id) {
        var word = wordPicker.getWord();
        var inst = new GameInstance(word);
        gameInstances.put(id, inst);

        System.out.println("-");
        gameInstances.forEach((key, value) -> System.out.println(key + " | " + value.toJSONObjectString()));
        System.out.println("-");

        return inst;
    }

    public GameInstance pushLetterForId(String id, Character letter) {
        GameInstance inst;
        if (gameInstances.containsKey(id)) {
            inst = gameInstances.get(id);
            inst.pushLetter(letter);
        } else {
            var word = wordPicker.getWord();
            inst = new GameInstance(word);
            inst.pushLetter(letter);
            gameInstances.put(id, inst);
        }
        return inst;
    }
}
