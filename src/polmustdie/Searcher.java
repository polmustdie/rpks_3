package polmustdie;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

class ThreadTask implements Callable<List<Integer>> {
//    int count;

    //    public ThreadOut(int blockCount) {
//        count = blockCount;
//    }
    private List<String> allTextStrings;
    private int startIndex;
    private int endIndex;
    private String subString;
    private static AtomicInteger passed;
    private static AtomicInteger found;

    public ThreadTask(List<String> alTextStrings, int startIndex, int endIndex, String subString) {
        this.allTextStrings = alTextStrings;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.subString = subString;
        this.containingStringsIndex = new ArrayList<>();
        passed = new AtomicInteger(1);
        found = new AtomicInteger(0);

    }

    private List<Integer> containingStringsIndex;


    public List<Integer> call() {
        for (int i = startIndex; i < endIndex; i++) {
            if (allTextStrings.get(i).contains(subString)) {
                containingStringsIndex.add(i);
                found.getAndIncrement();
                synchronized (found) {
                    System.out.print("Strings found: " + found.get() + "\n");
                }
            }
//            passed.getAndIncrement();
//            synchronized(passed) {
//                System.out.print("Strings passed: " + passed.get() + " Strings found: " + found.get() + "\n");
//            }
        }
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }


//        printProgress(passed.get(), allTextStrings.size());
        return containingStringsIndex;
    }

//    private synchronized static void printProgress(int index, int end) {
//
//        int dotCount = (int) Math.ceil(index * 10f / end);
//        StringBuilder sb = new StringBuilder("[");
//
//        sb.append("■".repeat(Math.max(0, dotCount)));
//        sb.append(" ".repeat(Math.max(0, 10 - dotCount)));
//
//        sb.append("] ");
//        if (index % 2 == 0)
//            sb.append(String.format(" %d / %d lines", index, end));
//        else
//            sb.append(String.format(" %d \\ %d lines", index, end));
//
//        for (int count = 0; count < sb.length(); count++) {
//            System.out.print("\b");
//        }
//        System.out.print(sb);
//        if (index == end) {
//            for (int count = 0; count < sb.length(); count++) {
//                System.out.print("\b");
//            }
//        }
//    }
}

public class Searcher {
    private List<String> allTextStrings;
    private ExecutorService executorService;
    private List<Callable<List<Integer>>> taskList;
    private int before;
    private int after;

    private List<String> getAllStringsFromFile(String pathToFile) {
        List<String> stringList = new ArrayList<>();
        try (FileReader reader = new FileReader(pathToFile);
             BufferedReader bufferedReader = new BufferedReader(reader)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringList.add(line);
            }
        }
        catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return stringList;
    }

    private void printResult(List<Integer> resultList, String pathToSource, String pathToResult, String subString) {
        try (FileWriter fileWriter = new FileWriter(pathToResult);
             PrintWriter printWriter = new PrintWriter(fileWriter)) {

            int maxListIndex = allTextStrings.size() - 1;
//            System.out.println("");
            resultList.forEach( (index) -> {
                int before, after;
                before = Math.max(index - this.before, 0);
                after = Math.min(index + this.after, maxListIndex);
                printWriter.println("\n                                        ");
                for (int i = before; i <= after; i++) {
//                    System.out.println("I am working");
                    if (i == index) printWriter.printf("%d. %s\n",i, allTextStrings.get(i));
                    else printWriter.printf("%d. %s\n",i, allTextStrings.get(i));
                }
            });

        } catch (IOException exception) {
            System.err.println(exception.getMessage());
        }
    }


    public void search(String pathToFile, String pathToResultFile, String subString, int before, int after) {
        allTextStrings = getAllStringsFromFile(pathToFile);
        executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() - 1);
        this.before = before;
        this.after = after;
        taskList = new ArrayList<>();

        int startIndex = 0;
        int endIndex = 0;
        int listSize = allTextStrings.size();
        int blockSize = 100;

        while (endIndex < listSize - 1) {
            if (endIndex + blockSize >= listSize) endIndex = listSize - 1;
            else endIndex += blockSize;

            taskList.add(new ThreadTask(allTextStrings, startIndex, endIndex, subString));
            startIndex += blockSize;
        }
        List<Integer> resultIndexes = new ArrayList<>();
        try {
            List<Future<List<Integer>>> resultFutureIndexes = executorService.invokeAll(taskList);


            for (Future<List<Integer>> future :
                    resultFutureIndexes) {
                resultIndexes.addAll(future.get());
            }
            executorService.shutdown();
        } catch (InterruptedException | ExecutionException e) {
            System.err.println(e.getMessage());
            executorService.shutdown();
        }
        printResult(resultIndexes, pathToFile, pathToResultFile, subString);
        System.out.println("Strings are written to the file");

    }
}



//    public void run() {
//        double startTime = (double)System.nanoTime() / 1000000;
//        long finishCount = 0;
//        long foundCount = 0;
//        while (count != finishCount) {
//            if ((foundCount != StringFinder.foundCount.get()) || (finishCount != StringFinder.finishFinderCount.get())) {
//                finishCount = StringFinder.finishFinderCount.get();
//                foundCount = StringFinder.foundCount.get();
//                System.out.format("Time passed: %f. To do: %d. Strings found: %d\n",
//                        (double)System.nanoTime() / 1000000 - startTime, count-finishCount, foundCount);
//            }
//        }
//    }

