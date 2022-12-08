package HangMan.model;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class GameInstance {
    private final int MAX_DEATH_STATE = 6;

    public enum WinStatus {
        FAIL (-1),
        WIN (1),
        IN_PROCESS (0);

        private final int val;

        private WinStatus(int val) {
            this.val = val;
        }

        public int getVal() {
            return val;
        }
    }

    private String word;
    private Character[] currentLetters;
    private int deathState;
    private WinStatus winFlag;
    private List<Character> alreadyTried;

    public GameInstance(String word) {
        this.word = word;
        this.currentLetters = new Character[word.length()];
        winFlag = WinStatus.IN_PROCESS;
        alreadyTried = new ArrayList<>();
        deathState = 0;
    }

    public GameInstance(String word, Character[] currentLetters, int deathState, int winFlag, List<Character> alreadyTried) {
        this.word = word;
        this.currentLetters = currentLetters;
        this.deathState = deathState;
        this.alreadyTried = alreadyTried;
        switch (winFlag) {
            case 0 -> this.winFlag = WinStatus.IN_PROCESS;
            case 1 -> this.winFlag = WinStatus.WIN;
            case -1 -> this.winFlag = WinStatus.FAIL;
        }
    }

    public void pushLetter(Character letter) {
        if (word.indexOf(letter) == -1 && !alreadyTried.contains(letter)) {
            deathState++;
            alreadyTried.add(letter);
            if (deathState >= MAX_DEATH_STATE) {
                winFlag = WinStatus.FAIL;
            }
            return;
        }
        if (!Arrays.asList(currentLetters).contains(letter)) {
            for (int index = word.indexOf(letter); index >= 0; index = word.indexOf(letter, index + 1)) {
                currentLetters[index] = letter;
            }
        }
        if (Arrays.stream(currentLetters).filter(Objects::nonNull).count() == word.length()) {
            winFlag = WinStatus.WIN;
        }
    }

    public Character[] getCurrentLetters() {
        return currentLetters;
    }

    public WinStatus getWinFlag() {
        return winFlag;
    }

    public List<Character> getAlreadyTried() {
        return alreadyTried;
    }

    public String getWord() {
        return word;
    }

    public int getDeathState() {
        return deathState;
    }

    public String toJSONObjectString() {
        JSONObject obj = new JSONObject();
        obj.put("letters", currentLetters);
        obj.put("deathState", deathState);
        obj.put("winFlag", winFlag);
        obj.put("alreadyTried", alreadyTried);
        return obj.toString();
    }
}
