package com.auru.trinity.interview;

import java.util.List;
import java.util.Set;

public class CurrentWordsAndSimpleWordsCollections {

    private Set<String> simpleWords;

    private List<Word> currentWords;

    public CurrentWordsAndSimpleWordsCollections(Set<String> simpleWords, List<Word> currentWords) {
        this.simpleWords = simpleWords;
        this.currentWords = currentWords;
    }

    public Set<String> getSimpleWords() {
        return simpleWords;
    }

    public void setSimpleWords(Set<String> simpleWords) {
        this.simpleWords = simpleWords;
    }

    public List<Word> getCurrentWords() {
        return currentWords;
    }

    public void setCurrentWords(List<Word> currentWords) {
        this.currentWords = currentWords;
    }
}
