package com.auru.trinity.interview;

import java.util.ArrayList;

public class Word implements Comparable<Word> {

    public static final String REPLACE_MATCHES_TO = " ";

    private String initialWord;
    private String wordAfterTrim;
    private int simpleWordsCount;

    private ArrayList<String> consistsOfSimpleWordsList = new ArrayList<>();

    public Word(String initialWord) {
        this.initialWord = initialWord;
        this.wordAfterTrim = initialWord;
    }

    public Word(Word w) {
        initialWord = w.getInitialWord();
        wordAfterTrim = w.getWordAfterTrim();
        simpleWordsCount = w.getSimpleWordsCount();
        consistsOfSimpleWordsList = new ArrayList<>(w.getConsistsOfSimpleWordsList());
    }

    @Override
    public int compareTo(Word o) {
        if (wordAfterTrim.length() > o.getWordAfterTrim().length()) {
            return 1;
        } else if (wordAfterTrim.length() < o.getWordAfterTrim().length()) {
            return -1;
        }
        return 0;
    }

    public String getInitialWord() {
        return initialWord;
    }

    public void setInitialWord(String initialWord) {
        this.initialWord = initialWord;
    }

    public String getWordAfterTrim() {
        return wordAfterTrim;
    }

    public void setWordAfterTrim(String wordAfterTrim) {
        this.wordAfterTrim = wordAfterTrim;
    }

    public int getSimpleWordsCount() {
        return simpleWordsCount;
    }

    public ArrayList<String> getConsistsOfSimpleWordsList() {
        return consistsOfSimpleWordsList;
    }

    public void setConsistsOfSimpleWordsList(ArrayList<String> consistsOfSimpleWordsList) {
        this.consistsOfSimpleWordsList = consistsOfSimpleWordsList;
    }

    public void incrementSimpleWordsCount() {
        simpleWordsCount++;
    }

    public void setSimpleWordsCount(int simpleWordsCount) {
        this.simpleWordsCount = simpleWordsCount;
    }

    public int getCurrentLength() {
        return wordAfterTrim.length();
    }

    public boolean startsWith(String what) {
        return wordAfterTrim.startsWith(what);
    }

    public boolean contains(String what) {
        return wordAfterTrim.contains(what);
    }

    public void excludeMatch(String what) {
        consistsOfSimpleWordsList.add(new String(what));
        wordAfterTrim = wordAfterTrim.replace(what, REPLACE_MATCHES_TO);
        simpleWordsCount++;
    }

    @Override
    public String toString() {
        return "word='" + initialWord + '\'' +
                ", simpleWordsCount=" + simpleWordsCount +
                ", they are=[" + consistsOfSimpleWordsList/*.stream().reduce("", (a,b) -> a + ", " + b)*/ +
                "]}";
    }
}
