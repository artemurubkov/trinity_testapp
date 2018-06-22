package com.auru.trinity.interview;

import java.io.IOException;
import java.util.stream.Stream;

public class Main {

    public static final String filePath = "src/com/resources/words.txt";

    public static void main(String[] args) {
        Processor s = new Processor(Main.filePath);
        long t = System.currentTimeMillis();

        CurrentWordsAndSimpleWordsCollections subResult = null;
        try {
            Stream<Word> str = s.readFile();
            subResult = s.findSimpleWords(str);
        } catch (NoDataProvidedException e) {
            System.out.println("The data file is either empty or corrupted");
            return;
        } catch (IOException e1) {
            System.out.println("Can't read file by path: " + filePath);
            return;
        }

        long t1 = System.currentTimeMillis();
        System.out.println("Simple words count: " + subResult.getSimpleWords().size() + ", time: " + (t1 - t));

        Statistics stats = s.countLongestWords(subResult);

        t = t1;
        t1 = System.currentTimeMillis();
        System.out.println(stats + ", time: " + (t1 - t));
    }

}
