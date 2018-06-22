package com.auru.trinity.interview;

public class Statistics {

    private Word theLongestWord;
    private Word the2ndLongestWord;
    private int concatenatedWordsCount;


    public Word getTheLongestWord() {
        return theLongestWord;
    }

    public void setTheLongestWord(Word theLongestWord) {
        this.theLongestWord = theLongestWord;
    }

    public Word getThe2ndLongestWord() {
        return the2ndLongestWord;
    }

    public void setThe2ndLongestWord(Word the2ndLongestWord) {
        this.the2ndLongestWord = new Word(the2ndLongestWord);
    }

    public int getConcatenatedWordsCount() {
        return concatenatedWordsCount;
    }

    public void setConcatenatedWordsCount(int concatenatedWordsCount) {
        this.concatenatedWordsCount = concatenatedWordsCount;
    }

    @Override
    public String toString() {
        return "Statistics{" +
                "theLongestWord=" + theLongestWord +
                ", the2ndLongestWord=" + the2ndLongestWord +
                ", concatenatedWordsCount=" + concatenatedWordsCount +
                '}';
    }
}
