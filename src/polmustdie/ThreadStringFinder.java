//package polmustdie;
//
//import java.io.*;
//import java.nio.file.Files;
//import java.util.*;
//import java.util.concurrent.*;
//
//import static polmustdie.StringFinder.substring;
//
//
//public class ThreadStringFinder {
//    private final String inputFile, outputFile, searchingWord;
//    private final int before;
//    private final int after;
//
//    public ThreadStringFinder(String inputFile, String outputFile, String searchingWord, int before, int after) {
//        this.inputFile = inputFile;
//        this.outputFile = outputFile;
//        this.searchingWord = searchingWord;
//        this.before = before;
//        this.after = after;
//    }
//
//    public void searchString() throws InterruptedException {
//        long lineCount = 0;
//        int skipByteSize = 0;
//
//        try (Scanner scanner = new Scanner(new File(inputFile))) {
//            while (scanner.hasNextLine()) {
//                lineCount++;
////                if (lineCount >= before)
//                scanner.nextLine();
////                else
////                    skipByteSize += scanner.nextLine().length();
//            }
//        } catch (Exception exception) {
//            System.out.print("Oops, error: ");
//            exception.printStackTrace();
//            return;
//        }
//
////        if (after < before) {
////            after = lineCount;
////            if (lineCount < before) {
////                before = 1;
////                skipByteSize = 0;
////            }
////        }
////        else {
////            lineCount = after - before + 1;
////        }
//        substring = searchingWord;
//        StringFinder.outFile = outputFile;
//        int blockLength = 20, index = 0;
//        long blockCount = (long) Math.ceil((double)lineCount / blockLength)-4;
//        ArrayList<String> allText = new ArrayList<>();
//        ArrayList<Future<Set<Integer>>>  lineIndexForPrinting = new ArrayList<>();
//        ArrayList<String> stringArr = new ArrayList<>();
//
//        try {
//            Files.deleteIfExists(new File(outputFile).toPath());
//        }
//        catch (Exception er) {
//            er.printStackTrace();
//        }
//
////        try (RandomAccessFile reader = new RandomAccessFile(inputFile, "r")) {
////            reader.skipBytes(skipByteSize);
////
////            Thread thread = new Thread(new ThreadOut((int) blockCount));
////            thread.start();
////            ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() - 1);
////            for (long i = 0; i <= lineCount; i++) {
////                stringArr.add(reader.readLine());
//////                stringArr[index] = reader.readLine();
//////                index++;
//////
//////                if (index == blockLength) {
//////                    executorService.execute(new StringFinder(stringArr));
//////
//////                    index = 0;
//////                    stringArr = new String[blockLength];
//////                }
////            }
//////            if (index != 0) {
//////                String[] newStringArr = new String[index];
//////                System.arraycopy(stringArr, 0, newStringArr,0, index);
//////                executorService.execute(new StringFinder(newStringArr));
//////            }
////            thread.join();
////            executorService.shutdown();
////        } catch (IOException exception) {
////            System.out.print("Oops, error: ");
////            exception.printStackTrace();
////        }
//        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("/Users/soulfade/Desktop/text2.txt"))) {
//            Thread thread = new Thread(new ThreadOut((int) blockCount));
//            thread.start();
//            ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() - 1);
//            ArrayList<String> partOfText = new ArrayList<>();
//            String text;
//            int start = 0;
//            int i;
//            while (bufferedReader.ready()) {
//
//                i = 0;
//                text = bufferedReader.readLine();
//                while (i < blockLength && text != null) {
//                    partOfText.add(text);
//                    allText.add(text);
//                    text = bufferedReader.readLine();
//                    i++;
//                }
//                lineIndexForPrinting.add(executorService.submit(new StringFinder(partOfText, substring, start, before, after)));
//                start += blockLength;
//                partOfText.clear();
//
//            }
//            thread.join();
//            executorService.shutdown();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter("/Users/soulfade/Desktop/out_3.txt"))) {
////            for (Future<Set<Integer>> setFuture : lineIndexForPrinting) {
////                setFuture.get();
////            }
//            SortedSet<Integer> resultSet = new TreeSet<>();
//
//            for (Future<Set<Integer>> setFuture : lineIndexForPrinting) {
//                resultSet.addAll(setFuture.get());
//            }
//
//            for (var ind : resultSet) {
//                if (ind >= 0 && ind < allText.size()){
//                    fileWriter.write(allText.get(ind) + '\n');
//                }
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ExecutionException | InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//
//    }
//}