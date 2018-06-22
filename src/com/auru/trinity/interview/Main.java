package com.auru.trinity.interview;

import java.util.stream.Stream;

public class Main {

    public static final String filePath = "src/com/resources/words.txt";

    public static void main(String[] args) {
        Processor s = new Processor(Main.filePath);
        long t = System.currentTimeMillis();
        Stream<Word> str = s.readFile();

        if (str.count() == 0) {
            System.out.println("The file has no data");
            return;
        }

        CurrentWordsAndSimpleWordsCollections subResult = s.findSimpleWords(str);

        long t1 = System.currentTimeMillis();
        System.out.println("Simple words count: " + subResult.getSimpleWords().size() + ", time: " + (t1 - t));

        Statistics stats = s.countLongestWords(subResult);

        t = t1;
        t1 = System.currentTimeMillis();
        System.out.println(stats + ", time: " + (t1 - t));
    }

}
