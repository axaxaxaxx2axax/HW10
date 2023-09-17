package org.example.task3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class WordFrequencyCounter {
    public static void countWordFrequency(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            StringBuilder sb = new StringBuilder();

            while ((line = br.readLine()) != null) {
                sb.append(line).append(" ");
            }

            String[] words = sb.toString().split("\\s+");

            List<WordCount> wordCounts = new ArrayList<>();

            for (String word : words) {
                word = word.replaceAll("[^a-zA-Z]", "").toLowerCase();

                if (!word.isEmpty()) {
                    WordCount existingWord = null;
                    for (WordCount wc : wordCounts) {
                        if (wc.getWord().equals(word)) {
                            existingWord = wc;
                            break;
                        }
                    }

                    if (existingWord != null) {
                        existingWord.incrementCount();
                    } else {
                        wordCounts.add(new WordCount(word));
                    }
                }
            }

            wordCounts.sort(new Comparator<WordCount>() {
                @Override
                public int compare(WordCount wc1, WordCount wc2) {
                    return Integer.compare(wc2.getCount(), wc1.getCount());
                }
            });

            for (WordCount wc : wordCounts) {
                System.out.println(wc.getWord() + " " + wc.getCount());
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    static class WordCount {
        private String word;
        private int count;

        public WordCount(String word) {
            this.word = word;
            this.count = 1;
        }

        public String getWord() {
            return word;
        }

        public int getCount() {
            return count;
        }

        public void incrementCount() {
            count++;
        }
    }
}

