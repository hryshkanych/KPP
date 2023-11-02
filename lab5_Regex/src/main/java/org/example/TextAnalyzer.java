package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextAnalyzer {
    public static List<String> findQuestionSentences(String text) {
        List<String> questionSentences = new ArrayList<>();

        Pattern pattern = Pattern.compile("[^.!?]*\\?");
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            String sentence = matcher.group().trim();
            questionSentences.add(sentence);
        }

        return questionSentences;
    }

    public static List<String> findWordsOfGivenLength(List<String> sentences, int length) {
        List<String> wordsOfGivenLength = new ArrayList<>();

        Pattern wordPattern = Pattern.compile("\\b\\w{" + length + "}\\b");

        for (String sentence : sentences) {
            Matcher matcher = wordPattern.matcher(sentence);
            while (matcher.find()) {
                String word = matcher.group();
                wordsOfGivenLength.add(word);
            }
        }

        return wordsOfGivenLength;
    }
}
