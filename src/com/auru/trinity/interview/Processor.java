package com.auru.trinity.interview;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Processor {

    private String filePath;

    private Statistics result = new Statistics();


    public Processor(String filePath) {
        this.filePath = filePath;
    }

    public Stream<Word> readFile() {
        Stream<Word> words = null;
        try {
            words = Files.lines(Paths.get(filePath)).map(s -> new Word(s));
        } catch (IOException e) {
            System.out.println("Can't read file by path: " + filePath);
        }

        return words;
    }


    public CurrentWordsAndSimpleWordsCollections findSimpleWords(Stream<Word> words) {
        CopyOnWriteArrayList<Word> wordsList = new CopyOnWriteArrayList<>(words.collect(Collectors.toList()));
        //TODO why row below works slower???
//        List<Word> wordsList = words.collect(Collectors.toList());
        Set<String> simpleWords = new HashSet<>();

//        for (int i = 0; i < 200; i++) {
        for (int i = 0; i < wordsList.size(); i++) {
            Word curr = wordsList.get(i);
            int length = curr.getCurrentLength();
            boolean isSimple = true;

//            for (int k = 0; k < 200; k++) {
            for (int k = 0; k < wordsList.size(); k++) {
                Word s = wordsList.get(k);
                if (/*s == null || */curr == s || s.getCurrentLength() > length) {
                    continue;
                }
                if (curr.startsWith(s.getInitialWord()) || curr.contains(s.getInitialWord())) {
//                    curr.excludeMatch(s.getInitialWord());
                    isSimple = false;
                    break;
                }
            }
            if (isSimple) {
                simpleWords.add(curr.getInitialWord());
//                System.out.println(curr.getInitialWord());
                wordsList.remove(curr);
//                wordsList.set(i, null);
            }
        }

        result.setConcatenatedWordsCount(wordsList.size() - simpleWords.size());

        return new CurrentWordsAndSimpleWordsCollections(simpleWords, wordsList);
    }


    public Statistics countLongestWords(CurrentWordsAndSimpleWordsCollections c) {
        List<Word> words = c.getCurrentWords();
        Set<String> simpleWords = c.getSimpleWords();

        Word theLongestWord = result.getTheLongestWord();

        if (theLongestWord == null && words.size() > 0) {
            theLongestWord = words.get(0);
        }
//        for (int i = 0; i < 200; i++) {
        for (int i = 0; i < words.size(); i++) {
            Word curr = words.get(i);
//            if(curr == null){
//                continue;
//            }
            Iterator<String> it = simpleWords.iterator();
            while (it.hasNext()) {
                String s = it.next();
                while (curr.contains(s)) {
                    curr.excludeMatch(s);
                }
            }

            if (theLongestWord.getSimpleWordsCount() <= curr.getSimpleWordsCount()) {
                result.setThe2ndLongestWord(new Word(theLongestWord));
                result.setTheLongestWord(new Word(curr));
                theLongestWord = new Word(curr);
            }
        }

        return result;
    }

}
