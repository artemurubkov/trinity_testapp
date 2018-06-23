package com.auru.trinity.interview;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Processor {

    private String filePath;

    private Statistics result = new Statistics();


    public Processor(String filePath) {
        this.filePath = filePath;
    }

    public Stream<Word> readFile() throws IOException {
        Stream<Word> words = Files.lines(Paths.get(filePath)).map(s -> new Word(s));

        return words;
    }


    public CurrentWordsAndSimpleWordsCollections findSimpleWords(Stream<Word> words) throws NoDataProvidedException {
        ArrayList<Word> wordsList = new ArrayList<>(words.collect(Collectors.toList()));
        if (wordsList == null || wordsList.size() == 0) {
            throw new NoDataProvidedException();
        }
        Collections.sort(wordsList);
        //TODO why row below works slower???
//        List<Word> wordsList = words.collect(Collectors.toList());
        Set<String> simpleWords = new HashSet<>();

//        for (int i = 0; i < 200; i++) {
        for (int i = 0; i < wordsList.size(); i++) {
            Word curr = wordsList.get(i);
            int length = curr.getCurrentLength();
            boolean isSimple = true;

            Iterator<String> it = simpleWords.iterator();
            while (it.hasNext()) {
                String simpleEl = it.next();
                if (curr.contains(simpleEl)) {
//                    curr.excludeMatch(simpleEl);
                    isSimple = false;
                    break;
                }
            }

//            for (int k = 0; k < 200; k++) {
            if (isSimple) {
                for (int k = i /*all previous simple elements are stored in simpleWords*/; k < wordsList.size(); k++) {
                    Word s = wordsList.get(k);
                    if (s.getCurrentLength() >= length) {
                        break;
                    }
                    if (curr == s) {
                        continue;
                    }
                    if (curr.contains(s.getInitialWord())) {
//                        curr.excludeMatch(s.getInitialWord());
                        isSimple = false;
                        break;
                    }
                }
            }
            if (isSimple) {
                simpleWords.add(curr.getInitialWord());
//                System.out.println(curr.getInitialWord());
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
