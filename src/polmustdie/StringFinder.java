package polmustdie;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;


public class StringFinder implements Callable<Set<Integer>> {
    static AtomicLong finishFinderCount = new AtomicLong(0);
    static AtomicLong foundCount = new AtomicLong(0);
    ArrayList<String> textForSearch = new ArrayList<>();
    static String substring;
    Set<Integer> numberStringToPrint;

    int before = 0;
    int after = 0;
    int startLine = 0;
    static String outFile;

    public StringFinder(ArrayList<String> text, String substring, int startLine, int before, int after){
        this.textForSearch.addAll(text);
        StringFinder.substring = substring;
        this.numberStringToPrint = new HashSet<>();
        this.startLine = startLine;
        this.after = after;
        this.before = before;
    }
    public void searchSubstring() {
        int sizeText = textForSearch.size();
        for (int line = 0; line < sizeText; line++) {
            if (this.textForSearch.get(line).contains(substring)) {
                for (int i = startLine + line - before; i <= line + startLine + after; i++) {
                    numberStringToPrint.add(i);
                }
                foundCount.getAndIncrement();
            }
        }
        finishFinderCount.getAndIncrement();
    }
    @Override
    public Set<Integer> call() {
        searchSubstring();
//        finishFinderCount.getAndIncrement();
        return numberStringToPrint;
    }
}

//public class StringFinder implements Runnable {
//    static AtomicLong foundCount = new AtomicLong(0);
//    static AtomicLong finishFinderCount = new AtomicLong(0);
//    static String findWord, outFile;
//    private final String[] inputString;
////    private int startPos, endPos;
//
//    public StringFinder(String[] inputStr) {
//        inputString = inputStr;
////        this.startPos = startPos;
////        this.endPos = endPos;
//    }
//
//    public void run() {
//        Set<Integer> result = new HashSet<>();
//        int len = inputString.length;
//
//        for (int i = 0; i < len; i++) {
//            if (Arrays.asList(inputString[i].split(" ")).contains(findWord)) {
//                result.add(i); // adds unique indices
//                foundCount.getAndIncrement();
//            }
//        }
//
//        if (!result.isEmpty()) {
//            try (FileWriter writer = new FileWriter(outFile, true)) {
//                synchronized (this) {
//                    for(Object object : result) {
////                        writer.write(inputString[(int) object - startPos] + '\n');
//                        writer.write(inputString[(int) object] + '\n');
////                        writer.write(inputString[(int) object + endPos] + '\n');
//                    }
//                    writer.flush();
//                }
//            }
//            catch(IOException ex) {
//                System.out.println(ex.getMessage());
//            }
//        }
//        finishFinderCount.getAndIncrement();
//    }
//}