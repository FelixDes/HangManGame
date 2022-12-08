package HangMan.model;

import HangMan.db.dao.GameInstanceDAO;
import org.di.annotations.Component;
import org.di.annotations.Inject;
import org.di.annotations.Singleton;

@Singleton
@Component
public class GameProcessor {
    @Inject
    private GameInstanceDAO gameInstanceDAO;
    @Inject
    private DictionaryWordPicker wordPicker;

    public GameInstance createOrResetForId(String id) {
        var word = wordPicker.getWord();
        var inst = new GameInstance(word);
        gameInstanceDAO.update(id, inst);

        System.out.println("-");
        gameInstanceDAO.getAll().forEach((key, value) -> System.out.println(key + " | " + value.toJSONObjectString()));
        System.out.println("-");

        return inst;
    }

    public GameInstance pushLetterForId(String id, Character letter) {
        GameInstance inst;
        if (gameInstanceDAO.getAll().containsKey(id)) {
            inst = gameInstanceDAO.getForId(id);
            inst.pushLetter(letter);
        } else {
            var word = wordPicker.getWord();
            inst = new GameInstance(word);
            inst.pushLetter(letter);
        }
        gameInstanceDAO.update(id, inst);
        return inst;
    }
}
