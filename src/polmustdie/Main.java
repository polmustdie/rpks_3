package polmustdie;

public class Main {
    public static void main(String[] args) {
        Searcher searcher = new Searcher();
        searcher.search("/Users/soulfade/Desktop/text2.txt",
                "/Users/soulfade/Desktop/out_3.txt",
                "surprise", 1, 2);
    }
}
//import java.io.*;
//import java.util.*;
//import java.util.concurrent.*;
//
//public class Main {
//
//    public static void main(String[] args) {
//        ExecutorService executorService = Executors.newCachedThreadPool();
//        int before = 0, after = 0;
//        final int numberStringForReading = 20;
//        String substring = null;
//        ArrayList<Future<Set<Integer>>>  lineIndexForPrinting = new ArrayList<>();
//        ArrayList<String> allText = new ArrayList<>();
//
//        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("/Users/soulfade/Desktop/text2.txt"))) {
//            ArrayList<String> partOfText = new ArrayList<>();
//            String text;
//            int start = 0;
//            int i;
//            while (bufferedReader.ready()) {
//                System.out.print("I am reading and analyzing file.\r");
//                i = 0;
//                text = bufferedReader.readLine();
//                while (i < numberStringForReading && text != null) {
//                    partOfText.add(text);
//                    allText.add(text);
//                    text = bufferedReader.readLine();
//                    i++;
//                }
//                System.out.print("I am reading and analyzing file..\r");
//                lineIndexForPrinting.add(executorService.submit(new StringFinder(partOfText, substring, start, before, after)));
//                start += numberStringForReading;
//                partOfText.clear();
//                System.out.print("I am reading and analyzing file...\r");
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter("resultFromLab_3.txt"))) {
//            System.out.print("I am analyzing information and writing to file.\r");
//            for (Future<Set<Integer>> setFuture : lineIndexForPrinting) {
//                setFuture.get();
//            }
//            SortedSet<Integer> resultSet = new TreeSet<>();
//            System.out.print("I am analyzing information and writing to file..\r");
//            for (Future<Set<Integer>> setFuture : lineIndexForPrinting) {
//                resultSet.addAll(setFuture.get());
//            }
//            System.out.print("I am analyzing information and writing to file...\r");
//            for (var index : resultSet) {
//                if (index >= 0 && index < allText.size()){
//                    fileWriter.write(allText.get(index) + '\n');
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ExecutionException | InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        System.out.println("All is ready. Result in 'resultFromLab_3.txt' file");
//        executorService.shutdown();
//    }
//
//}