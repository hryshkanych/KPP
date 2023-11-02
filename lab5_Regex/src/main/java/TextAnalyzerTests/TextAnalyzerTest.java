package TextAnalyzerTests;

import org.example.TextAnalyzer;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TextAnalyzerTest {

    @Test
    public void testFindQuestionSentences() {
        String text = "Is this a question? Yes, it is. How about this? No, it's not.";

        List<String> questionSentences = TextAnalyzer.findQuestionSentences(text);

        assertEquals(2, questionSentences.size());
        assertEquals("Is this a question?", questionSentences.get(0));
        assertEquals("How about this?", questionSentences.get(1));
    }

    @Test
    public void testNoQuestionSentences() {
        String text = "This is not a question. It's a statement.";

        List<String> questionSentences = TextAnalyzer.findQuestionSentences(text);

        assertTrue(questionSentences.isEmpty());
    }

    @Test
    public void testEmptyText() {
        String text = "";

        List<String> questionSentences = TextAnalyzer.findQuestionSentences(text);
        assertTrue(questionSentences.isEmpty());
    }

    @Test
    public void testFindWordsOfGivenLength() {
        List<String> questionSentences = new ArrayList<>();
        questionSentences.add("What is your name?");
        questionSentences.add("How are you?");
        questionSentences.add("Do you like black or green tea?");

        List<String> wordsOfLength = TextAnalyzer.findWordsOfGivenLength(questionSentences, 4);

        assertEquals(4, wordsOfLength.size());
        assertTrue(wordsOfLength.contains("What"));
        assertTrue(wordsOfLength.contains("your"));
        assertTrue(wordsOfLength.contains("name"));
        assertTrue(wordsOfLength.contains("like"));
    }


    @Test
    public void testNoFoundWordsOfGivenLength() {
        List<String> questionSentences = new ArrayList<>();
        questionSentences.add("What is your name?");
        questionSentences.add("How are you?");
        questionSentences.add("Do you like black or green tea?");

        List<String> wordsOfLength = TextAnalyzer.findWordsOfGivenLength(questionSentences, 10);
        assertTrue(wordsOfLength.isEmpty());
    }
}