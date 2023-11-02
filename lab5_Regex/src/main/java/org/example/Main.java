package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Write the text: ");
        String text = scanner.nextLine();

        if (text.isEmpty()) {
            System.out.println("The text is empty. There are no results to display.");
            return;
        }

        List<String> questionSentences = TextAnalyzer.findQuestionSentences(text);
        if (questionSentences.isEmpty()) {
            System.out.println("No question sentences found in the text.");
            return;
        }

        System.out.print("Write the length of the word: ");
        int wordLength = scanner.nextInt();

        List<String> wordsOfGivenLength = TextAnalyzer.findWordsOfGivenLength(questionSentences, wordLength);

        if (wordsOfGivenLength.isEmpty()) {
            System.out.println("No words of the given length found in question sentences.");
        } else {
            System.out.println("Found words:");
            for (String word : wordsOfGivenLength) {
                System.out.println(word);
            }
        }
    }
}