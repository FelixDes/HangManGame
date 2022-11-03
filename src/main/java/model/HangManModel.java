package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class HangManModel {
    private final int MAX_STATE = 5;

    enum WinStatus {
        FAIL,
        WIN,
        IN_PROCESS
    }

    private String word;
    private Character[] currentLetters;
    private int state = 0;
    private WinStatus winFlag = WinStatus.IN_PROCESS;

    private final List<Character> alreadyTried = new ArrayList<>();

    public void reset() {
        word = "abcd";
        currentLetters = new Character[word.length()];
        state = 0;
        winFlag = WinStatus.IN_PROCESS;
        alreadyTried.clear();
//        System.out.println(word);
    }

    public void pushLetter(Character letter) {
        if (word.indexOf(letter) == -1 && !alreadyTried.contains(letter)) {
            state++;
            alreadyTried.add(letter);

            if (state >= MAX_STATE) {
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

    public int getState() {
        return state;
    }

    public Character[] getCurrentLetters() {
        return currentLetters;
    }

    public WinStatus isWinFlag() {
        return winFlag;
    }

    public List<Character> getAlreadyTried() {
        return alreadyTried;
    }
}
