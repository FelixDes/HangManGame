package model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class HangManModel {
    private final int MAX_STATE = 6;
    private final String DICTIONARY_PATH = "/home/felix/_Programming/Idea_Projects/OOP/HangManGame/src/main/resources/words.txt";

    enum WinStatus {
        FAIL,
        WIN,
        IN_PROCESS
    }

    private String word;
    private Character[] currentLetters;
    private int state = 0;
    private WinStatus winFlag = WinStatus.IN_PROCESS;
    private final Random rnd = new Random();
    private final List<Character> alreadyTried = new ArrayList<>();

    public void reset() throws IOException {
        word = getRandomWord();
        currentLetters = new Character[word.length()];
        state = 0;
        winFlag = WinStatus.IN_PROCESS;
        alreadyTried.clear();

        System.out.println(word);
    }

    private String getRandomWord() throws IOException {
        var lines = Files.readAllLines(Paths.get(DICTIONARY_PATH));
        return lines.get(rnd.nextInt(lines.size()));
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
