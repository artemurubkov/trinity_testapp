package test;

import com.auru.trinity.interview.Main;
import com.auru.trinity.interview.Processor;

import static org.junit.jupiter.api.Assertions.assertTimeout;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.Set;
import java.util.stream.Stream;


public class ProcessorTest {

    @Test
    public void testReadFile() {
        assertTimeout(Duration.ofMillis(1000), () -> {
//            Processor s = new Processor(Main.filePath);
//            Stream<String> str = s.readFile();
//            Set<String> se = s.findSimpleWords(str);
//            System.out.println(se.size());
        });
    }
}
