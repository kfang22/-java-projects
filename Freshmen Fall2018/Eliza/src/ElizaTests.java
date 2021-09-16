//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: descriptive title of the program making use of this file
// Files: a list of all source files used by that program
// Course: course number, term, and year
//
// Author: Kevin Fang
// Email: kfang22@wisc.edu
// Lecturer's Name:Marc Renault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: name of your pair programming partner
// Partner Email: email address of your programming partner
// Lecturer's Name: name of your partner's lecturer
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// __x_ Write-up states that pair programming is allowed for this assignment.
// __x_ We have both read and understand the course Pair Programming Policy.
// __x_ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully
// acknowledge and credit those sources of help here. Instructors and TAs do
// not need to be credited here, but tutors, friends, relatives, room mates
// strangers, etc do. If you received no outside help from either type of
// source, then please explicitly indicate NONE.
//
// Persons: None
// Online Sources: None
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * This class contains a few methods for testing methods in the Eliza class as
 * they are developed. These methods are private since they are only intended
 * for use within this class.
 *
 * @author Jim Williams
 * @author Kevin Fang
 *
 */
public class ElizaTests {

    /**
     * This is the main method that runs the various tests. Uncomment the tests when
     * you are ready for them to run.
     *
     * @param args (unused)
     */
    public static void main(String[] args) {

        // Milestone 1: Process User Input
        // M1: main nothing to do
        testSeparatePhrases();
        testFoundQuitWord();
        testSelectPhrase();
        testReplaceWord();
        testAssemblePhrase();

        // Milestone 2:
        // M2: implement parts of main as described in main method comments
        testSwapWords();
        testPrepareInput();
        testLoadResponseTable();

        // Milestone 3:
        // main: implement the rest of main as described in the main method comments
        testFindKeyWordsInPhrase();
        testSelectResponse();
        testInputAndResponse();
        testSaveDialog();
    }

    /**
     * This runs some tests on the separatePhrases method.
     * 1. Makes sure the length of userInput is the same as the expected length of output.
     * 2. Makes sure the content of userInput is the same as the expected content of output.
     * 3. ""What? This isn't the 4th messy-sentence!" should return "what" and
     *    "this isn't the 4th messy sentence".
     * 4."NO" should return "no".
     * 5."this tab" should return "this tab".
     * 6."What?" should return "what".
     * 7."Thank you, but no, I have to go. Goodbye!!! should return "thank you" and "but no"
     *    and "i have to go" and "goodbye"
     */
    private static void testSeparatePhrases() {
        boolean error = false;

        // 1.
        ArrayList<String> phrases = Eliza.separatePhrases("No.  I'm talking  to my dog! Bye.");
        ArrayList<String> expected = new ArrayList<>();
        expected.add("no");
        expected.add("i'm talking to my dog");
        expected.add("bye");

        if (phrases.size() != expected.size()) {
            error = true;
            System.out.println(
                    "testSeparatePhrases: expected " + expected.size() + " but found " + phrases.size() + " phrases.");
        }

        // 2.
        for (int i = 0; i < expected.size(); i++) {
            if (!expected.get(i).equals(phrases.get(i))) {
                error = true;
                System.out.println("testSeparatePhrases: phrases not the same");
                System.out.println("  " + expected.get(i));
                System.out.println("  " + phrases.get(i));
            }
        }

        // 3.
        ArrayList<String> phrases3 = Eliza.separatePhrases("What? This isn't the 4th messy-sentence!");
        ArrayList<String> expected3 = new ArrayList<>();
        expected3.add("what");
        expected3.add("this isn't the 4th messy sentence");

        if (phrases3.size() != expected3.size()) {
            error = true;
            System.out.println("testSeparatePhrases2: expected " + expected3.size() + " but found " + phrases3.size()
                    + " phrases.");
        }

        // 4.
        ArrayList<String> phrases4 = Eliza.separatePhrases("NO");
        ArrayList<String> expected4 = new ArrayList<>();
        expected4.add("no");

        if (phrases4.size() != expected4.size()) {
            error = true;
            System.out.println("testSeparatePhrases3: expected " + expected4.size() + " but found " + phrases4.size()
                    + " phrases.");
        }

        // 5.
        ArrayList<String> phrases5 = Eliza.separatePhrases("this tab");
        ArrayList<String> expected5 = new ArrayList<>();
        expected5.add("this tab");

        if (phrases5.size() != expected5.size()) {
            error = true;
            System.out.println("testSeparatePhrases4: expected " + expected5.size() + " but found " + phrases5.size()
                    + " phrases.");
        }

        // 6.
        ArrayList<String> phrases6 = Eliza.separatePhrases("What?");
        ArrayList<String> expected6 = new ArrayList<>();
        expected6.add("what");

        if (phrases6.size() != expected6.size()) {
            error = true;
            System.out.println("testSeparatePhrases5: expected " + expected6.size() + " but found " + phrases6.size()
                    + " phrases.");
        }

        // 7.
        ArrayList<String> phrases7 = Eliza.separatePhrases("Thank you, but no, I have to go. Goodbye!!!");
        ArrayList<String> expected7 = new ArrayList<>();
        expected7.add("thank you");
        expected7.add("but no");
        expected7.add("i have to go");
        expected7.add("goodbye");

        if (phrases7.size() != expected7.size()) {
            error = true;
            System.out.println("testSeparatePhrases6: expected " + expected7.size() + " but found " + phrases7.size()
                    + " phrases.");
        }

        if (error) {
            System.out.println("testSeparatePhrases failed");
        } else {
            System.out.println("testSeparatePhrases passed");
        }
    }

    /**
     * This runs some tests on the foundQuitWord method.
     * 1. Test to see if method returns true when there is a QUIT_WORD string within the phrases
     * 2. Test to see if method returns false when a QUIT_WORD string is included in the phrase.
     * 3. Test to see if method returns true when there is only a QUIT_WORD string.
     * 4. Test to see if method returns false when there is no QUIT_WORD string within the phrase
     */
    private static void testFoundQuitWord() {
        boolean error = false;

        // 1.
        ArrayList<String> phrases = new ArrayList<>();
        phrases.add("thank you for talking");
        phrases.add("bye");

        boolean quit = Eliza.foundQuitWord(phrases);
        if (!quit) {
            error = true;
            System.out.println("testFoundQuitWord 1: failed");
        }

        // 2.
        ArrayList<String> phrases2 = new ArrayList<>();
        phrases2.add("thank you bye");

        boolean quit2 = Eliza.foundQuitWord(phrases2);
        if (quit2) {
            error = true;
            System.out.println("testFoundQuitWord 2: failed");
        }

        // 3.
        ArrayList<String> phrases3 = new ArrayList<>();
        phrases3.add("bye");

        boolean quit3 = Eliza.foundQuitWord(phrases3);
        if (!quit3) {
            error = true;
            System.out.println("testFoundQuitWord 3: failed");
        }

        // 4.
        ArrayList<String> onlyQuit = new ArrayList<>();
        onlyQuit.add("");
        boolean fourthQuit = Eliza.foundQuitWord(onlyQuit);

        if (fourthQuit == true) {
            error = true;
        }

        if (error) {
            System.err.println("testFoundQuitWord failed");
        } else {
            System.out.println("testFoundQuitWord passed");
        }
    }

    /**
     * This runs some tests on the selectPhrase method.
     * 1. test to see if method returns the longest phrase
     * 2. test to see if elements in phrases are same word length, chooses lower index
     * 3. test to see when ArrayList size is 0, returns ""
     */
    private static void testSelectPhrase() {
        boolean error = false;

        //1. test to see if method returns the longest phrase
        ArrayList<String> phrases = new ArrayList<>();
        phrases.add("yeah");
        phrases.add("sometimes I remember being on a boat");
        phrases.add("not often");
        String choice = Eliza.selectPhrase(phrases);
        if (!choice.equals("sometimes I remember being on a boat")) {
            error = true;
            System.out.println("testSelectPhrase 1: failed");
        }
        phrases.removeAll(phrases);

        //2. test to see if elements in phrases are same word length, chooses lower index
        phrases.add("haha");
        phrases.add("hehe");
        phrases.add("ha ha haha");
        phrases.add("he he hehe");
        choice = Eliza.selectPhrase(phrases);
        if (!choice.equals("ha ha haha")) {
            error = true;
            System.out.println("testSelectPhrase 2: failed");
        }
        phrases.removeAll(phrases);

        //3. test to see when ArrayList size is 0
        ArrayList<String> nullPhrase = new ArrayList<>();
        choice = Eliza.selectPhrase(nullPhrase);
        if (!choice.equals("")) {
            error = true;
            System.out.println("testSelectPhrase 3: failed");
        }

        if (error) {
            System.err.println("testSelectPhrase failed");
        } else {
            System.out.println("testSelectPhrase passed");
        }
    }

    /**
     * This runs some tests on the assemblePhrase method.
     * 1. Test to see if method returns a single completed phrase.
     * 2. Test to see if method returns a single word if there are no spaces present in phrase.
     * 3. Test to see if method returns a empty string if no string is passed in.
     */
    private static void testAssemblePhrase() {
        boolean error = false;

        // 1.
        String[] words = { "This", "is a", "sentence" };
        String sentence = Eliza.assemblePhrase(words);
        String expectedSentence = "This is a sentence";

        if (!sentence.equals(expectedSentence)) {
            error = true;
            System.out.println("testAssembleSentence 1 failed '" + sentence + "'");
        }

        // 2.
        String[] secondWords = { "Hithisisweird" };
        sentence = Eliza.assemblePhrase(secondWords);
        expectedSentence = "Hithisisweird";

        if (!sentence.equals(expectedSentence)) {
            error = true;
            System.out.println("testAssembleSentence 1 failed '" + sentence + "'");
        }

        // 3.
        String[] words2 = {};
        String sentence2 = Eliza.assemblePhrase(words2);
        String expectedSentence2 = "";

        if (!sentence2.equals(expectedSentence2)) {
            error = true;
            System.out.println("testAssembleSentence 3 failed '" + sentence + "'");
        }
    }

    /**
     * This runs some tests on the findKeyWordsInPhrase method.
     * 1.returns correct String array for a single keyword.
     * 2.returns correct String array for a single keyword if keyword is last word in sentence.
     * 3.returns correct String array for a single keyword if keyword is middle word in sentence.
     * 4.returns correct String array for multiple keywords.
     * 5.returns null if there are no keywords.
     */
    private static void testFindKeyWordsInPhrase() {
        boolean error = false;

        {
            // 1.
            ArrayList<String> keywords = new ArrayList<String>();
            keywords.add("computer");
            String[] phrase = { "are", "you", "a", "computer" };

            String[] matches = Eliza.findKeyWordsInPhrase(keywords, phrase);
            if (matches == null || matches.length != 2 || !matches[0].equals("are you a") || !matches[1].equals("")) {
                error = true;
                System.out.println("testFindKeyWordsInPhrase 1 failed.");
                System.out.println(Arrays.toString(matches));
            }
        }

        {
            // 2.
            ArrayList<String> keywords = new ArrayList<String>();
            keywords.add("computer");
            String[] phrase = { "computer", "are", "you" };

            String[] matches = Eliza.findKeyWordsInPhrase(keywords, phrase);
            if (matches == null || matches.length != 2 || !matches[0].equals("") || !matches[1].equals("are you")) {
                error = true;
                System.out.println("testFindKeyWordsInPhrase 2 failed.");
                System.out.println(Arrays.toString(matches));
                System.out.println("0 = " + matches[0]);
                System.out.println("1 = " + matches[1]);
            }
        }

        {
            // 3.
            ArrayList<String> keywords = new ArrayList<String>();
            keywords.add("computer");
            String[] phrase = { "does", "that", "computer", "on", "your", "desk", "work" };
            String[] matches = Eliza.findKeyWordsInPhrase(keywords, phrase);
            if (matches == null || matches.length != 2 || !matches[0].equals("does that")
                    || !matches[1].equals("on your desk work")) {
                error = true;
                System.out.println("testFindKeyWordsInPhrase 3 failed.");
                System.out.println(Arrays.toString(matches));
            }
        }

        {
            // 4.
            ArrayList<String> keywords = new ArrayList<String>();
            keywords.add("you");
            keywords.add("me");
            String[] phrase = { "why", "don't", "you", "like", "me" };
            String[] matches = Eliza.findKeyWordsInPhrase(keywords, phrase);
            if (matches == null || matches.length != 3 || !matches[0].equals("why don't") || !matches[1].equals("like")
                    || !matches[2].equals("")) {
                error = true;
                System.out.println("testFindKeyWordsInPhrase 4 failed.");
                System.out.println(Arrays.toString(matches));
            }
        }

        {
            // 5.
            ArrayList<String> keywords = new ArrayList<String>();
            keywords.add("you");
            keywords.add("me");
            String[] sentence = { "me", "don't", "like", "you" };
            String[] matches = Eliza.findKeyWordsInPhrase(keywords, sentence);
            if (matches != null) {
                error = true;
                System.out.println("testFindKeyWordsInPhrase 5 failed.");
                System.out.println(Arrays.toString(matches));
            }
        }

        if (error) {
            System.err.println("testFindKeyWordsInPhrase failed");
        } else {
            System.out.println("testFindKeyWordsInPhrase passed");
        }
    }

    /**
     * This runs some tests on the saveDialog method.
     * 1. Test to see if contents saved in TEST_FILENAME are correct.
     * 2. Tests if lines of output are written to the saved file.
     */
    private static void testSaveDialog()  {
        boolean error = false;
        final String TEST_FILENAME = "testing.txt";
        { // 1.
            ArrayList<String> list = new ArrayList<>();
            list.add("this is a single line.");
            try {
                Eliza.saveDialog( list, TEST_FILENAME);
                String readFromFile = readFile( TEST_FILENAME);
                if ( !readFromFile.equals(list.get(0) + "\n")) {
                    error = true;
                    System.out.println("testSaveDialog 1 failed.");
                    System.out.println("content read: " + readFromFile);
                }
                removeFile( TEST_FILENAME);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        { // 2.
            ArrayList<String> list = new ArrayList<>();
            list.add("this is a single line.");
            list.add("this is a another line.");
            try {
                Eliza.saveDialog(list, TEST_FILENAME);
                String readFromFile = readFile(TEST_FILENAME);
                if (!readFromFile.equals(list.get(0) + "\n" + list.get(1) + "\n")) {
                    error = true;
                    System.out.println("testSaveDialog 2 failed.");
                    System.out.println("content read: " + readFromFile);
                }
                removeFile(TEST_FILENAME);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        if (error) {
            System.err.println("testSaveDialog failed");
        } else {
            System.out.println("testSaveDialog passed");
        }
    }

    /**
     * Supporting method for testSaveDialog
     *
     * @param fileName name of the file to read
     * @return the contents of the file
     */
    private static String readFile(String fileName) {
        StringBuffer buf = new StringBuffer();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName));) {
            String line;
            while ((line = reader.readLine()) != null) {
                buf.append(line);
                buf.append("\n");
            }
            return buf.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Supporting method for testSaveDialog.
     *
     * @param filename file to be removed.
     */
    private static void removeFile(String filename) {
        File file = new File(filename);
        try {
            if (file.exists())
                file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This runs some tests on the replaceword method.
     * 1. Tests to see if the keyword is replaced with the replacement word in the wordMap.
     * 2. Tests to see if the keyword is replaced with the replacement word in the wordMap.
     * 3. Tests to see if a null wordMap returns the word parameter.
     */
    private static void testReplaceWord()  {
        boolean error = false;

        {    //1.
            String word = "machine";
            String expected = "computer";
            String result = Eliza.replaceWord(word, Config.INPUT_WORD_MAP);
            if (result == null || !result.equals(expected)) {
                error = true;
                System.out.println("testReplaceWord 1 failed. result:'" + result + "' expected:'" + expected + "'");
            }
        }

        {    //2.
            String word = "yourself";
            String expected = "myself";
            String result = Eliza.replaceWord(word, Config.PRONOUN_MAP);
            if (result == null || !result.equals(expected)) {
                error = true;
                System.out.println("testReplaceWord 2 failed. result:'" + result + "' expected:'" + expected + "'");
            }
        }

        {    //3.
            String word = "yourself";
            String expected = "yourself";
            String result = Eliza.replaceWord(word, null);
            if (result == null || !result.equals(expected)) {
                error = true;
                System.out.println("testReplaceWord 3 failed. result:'" + result + "' expected:'" + expected + "'");
            }
        }

        if (error) {
            System.err.println("testReplaceWord failed");
        } else {
            System.out.println("testReplaceWord passed");
        }
    }

    /**
     * This runs some tests on the swapWords method.
     * 1. Test to see if substrings "i'm" and "cant" is replaced by "i am" and "cannot" from wordMap.
     * 2. Test to see if substring "i'm" is replaced by "you are" from wordMap.
     * 3. Test to see if substring "my" is replaced by "your" from wordMap.
     */
    private static void testSwapWords() {
        boolean error = false;

        { // 1.
            String someWords = "i'm cant recollect";
            String expected = "i am cannot remember";
            String result = Eliza.swapWords(someWords, Config.INPUT_WORD_MAP);
            if (result == null || !result.equals(expected)) {
                error = true;
                System.out.println("testSwapWords 1 failed. result:'" + result + "' expected:'" + expected + "'");
            }
        }

        { // 2.
            String someWords = "i'm happy";
            String expected = "you are happy";
            String result = Eliza.swapWords(someWords, Config.PRONOUN_MAP);
            if (result == null || !result.equals(expected)) {
                error = true;
                System.out.println("testSwapWords 2 failed. result:'" + result + "' expected:'" + expected + "'");
            }
        }

        { // 3.
            String someWords = "about my dog";
            String expected = "about your dog";
            String result = Eliza.swapWords(someWords, Config.PRONOUN_MAP);
            if (result == null || !result.equals(expected)) {
                error = true;
                System.out.println("testSwapWords 3 failed. result:'" + result + "' expected:'" + expected + "'");
            }
        }

        if (error) {
            System.err.println("testSwapWords failed");
        } else {
            System.out.println("testSwapWords passed");
        }
    }

    /**
     * This runs some tests on the selectResponse method.
     * 1. Test to see if the correct "random" response is returned.
     * 2. Test to see if response is approximately random if called 1000 times.
     * 3. Test to see if the correct "random" response is returned for a single string.
     * 4. Test to see if null is returned if string is null.
     */
    private static void testSelectResponse() {
        boolean error = false;

        { // 1.
            // is one of the 3 strings chosen?
            Random randGen = new Random(434);
            ArrayList<String> strList = new ArrayList<>();
            strList.add("The");
            strList.add("happy");
            strList.add("cat");
            String choice = Eliza.selectResponse(randGen, strList);

            if (!(choice.equals("The") || choice.equals("happy") || choice.equals("cat"))) {
                error = true;
                System.out.println("testSelectResponse 1 failed.");
            }
        }

        { // 2.
            // if called 1000 times, are the choices approximately random?
            Random randGen = new Random(765);
            ArrayList<String> strList = new ArrayList<>();
            strList.add("this");
            strList.add("is");
            strList.add("a");
            strList.add("list");
            strList.add("to");
            strList.add("choose");
            strList.add("from");
            int[] actualCount = new int[strList.size()];
            int[] expectedCount = new int[] { 156, 146, 142, 138, 160, 130, 128 };
            for (int iterationIndex = 0; iterationIndex < 1000; iterationIndex++) {
                String choice = Eliza.selectResponse(randGen, strList);
                for (int wordIndex = 0; wordIndex < strList.size(); wordIndex++) {
                    if (choice.equals(strList.get(wordIndex))) {
                        actualCount[wordIndex]++;
                    }
                }
            }
            // since we set a seed on the random number generator we should know the
            // expected
            // outcome
            for (int countIndex = 0; countIndex < actualCount.length; countIndex++) {
                if (actualCount[countIndex] != expectedCount[countIndex]) {
                    error = true;
                    System.out.println("testSelectResponse 2 failed.");
                    System.out.println("  expectedCount: " + Arrays.toString(expectedCount));
                    System.out.println("  actualCount: " + Arrays.toString(actualCount));
                }
            }

        }

        { // 3.

            Random randGen = new Random(434);
            ArrayList<String> strList = new ArrayList<>();
            strList.add("The");
            String choice = Eliza.selectResponse(randGen, strList);

            if (!(choice.equals("The"))) {
                error = true;
                System.out.println("testSelectResponse 3 failed.");
            }
        }

        { // 4.

            Random randGen = new Random(434);
            ArrayList<String> strList = null;
            String choice = Eliza.selectResponse(randGen, strList);

            if (!(choice == null)) {
                error = true;
                System.out.println("testSelectResponse 3 failed.");
            }
        }

        if (error) {
            System.err.println("testSelectResponse failed");
        } else {
            System.out.println("testSelectResponse passed");
        }
    }

    /**
     * This runs some tests on the prepareInput method.
     * 1."bye" should return null.
     * 2."I said goodbye" should return "i", "said", "goodbye".
     * 3."I can't do that" should return "i", "cannot", "do", "that".
     */
    private static void testPrepareInput() {
        boolean error = false;

        { // 1.
            String input = "bye";
            String[] result = null;
            result = Eliza.prepareInput("bye");
            if (result != null) {
                error = true;
                System.out.println("testPrepareInput 1: failed");
                System.out.println("  input: " + input);
                System.out.println("  result: " + Arrays.toString(result));
            }
        }

        { // 2.
            String input = "I said goodbye";
            String[] result = Eliza.prepareInput(input);
            String[] expected = { "i", "said", "goodbye" };
            for (int i = 0; i < result.length; i++) {
                for (int j = 0; j < expected.length; j++) {
                    if (!(result[i].equals(expected[i]))) {
                        error = true;
                        System.out.println("testPrepareInput 2: failed");
                        System.out.println("  input: " + input);
                        System.out.println("  result: " + Arrays.toString(result));
                    }
                }
            }
        }

        { // 3.
            String input = "I can't do that";
            String[] result = Eliza.prepareInput(input);
            String[] expected = { "i", "cannot", "do", "that" };
            for (int i = 0; i < result.length; i++) {
                for (int j = 0; j < expected.length; j++) {
                    if (!(result[i].equals(expected[i]))) {
                        error = true;
                        System.out.println("testPrepareInput 3: failed");
                        System.out.println("  input: " + input);
                        System.out.println("  result: " + Arrays.toString(result));
                    }
                }
            }
        }

        if (error) {
            System.err.println("testPrepareInput failed");
        } else {
            System.out.println("testPrepareInput passed");
        }
    }

    /**
     * This runs some tests on the loadResponseTable method.
     * 1.Test to see if responses for the keyword "computer" are correct.
     * 2.Test to see if there is an even amount of elements in the ArrayList<ArrayList<String>>.
     * 3.Checks that the right number of keywords and responses is read in for each keyword.
     * 4.Checks that the right number of keywords and responses is read in for each keyword.
     * 5.This test checks that the correct number of rows are read.
     */
    private static void testLoadResponseTable() {
        boolean error = false;

        { // 1.
            ArrayList<String> expected1stRow = new ArrayList<String>();
            expected1stRow.add("computer");
            ArrayList<ArrayList<String>> table = Eliza.loadResponseTable("Eliza.rsp");
            if (!table.get(0).equals(expected1stRow)) {
                error = true;
                System.out.println("testLoadResponseTable 1: failed");
                System.out.println("  expected1stRow: " + expected1stRow);
                System.out.println("  table1stRow: " + table.get(0));
            }

            // 2.
            if (table.size() % 2 != 0) {
                error = true;
                System.out.println("testLoadResponseTable 2: failed");
                System.out.println("  expected an even number of elements in table but found: " + table.size());
            }
        }

        { // 3.
            ArrayList<ArrayList<String>> table = Eliza.loadResponseTable("Eliza.rsp");
            if (table.get(0).size() != 1 || (table.get(1).size() != 6)) {
                error = true;
                System.out.println("testLoadResponseTable 3: failed");
                System.out.println("ExpectedKeywordSize: 1; actual returned: " + table.get(0).size());
                System.out.println("ExpectedResponseSize: 1; actual returned: " + table.get(1).size());
            }
        }

        { // 4.
            ArrayList<ArrayList<String>> table = Eliza.loadResponseTable("Eliza.rsp");
            for (int i = 0; i < table.size(); i++) {
                if (table.get(i).size() == 0) {
                    continue;
                } else if (table.get(i).size() == 0) {
                    error = true;
                    System.out.println("TestLoadResponseTable 4: failed");
                    System.out.println("There is a line error here");
                }
            }
        }

        { // 5.
            ArrayList<ArrayList<String>> table = Eliza.loadResponseTable("Eliza.rsp");
            if (table.size() != 132) {
                error = true;
                System.out.println("testLoadResponseTable 6: failed");
                System.out.println("Expected number of rows: 132; Actual returned: " + table.size());
            }
        }

        if (error) {
            System.err.println("testLoadResponseTable failed");
        } else {
            System.out.println("testLoadResponseTable passed");
        }
    }

    /*
     * Supporting method for testInputAndResponse. Returns 1 if the test passed and
     * 0 if the test failed.
     */
    private static int checkResponse(String input, String expectedResponse, Random rand,
                                     ArrayList<ArrayList<String>> table) {

        String[] words = Eliza.prepareInput(input);
        if (words == null) {
            if (expectedResponse == null) {
                return 1;
            } else {
                System.out.println("testInputLines  checkResponse error");
                System.out.println("  input='" + input + "'");
                System.out.println("  response=null");
                System.out.println("  expected='" + expectedResponse + "'");
                return 0;
            }
        }

        String response = Eliza.prepareResponse(words, rand, table);
        if (!response.equals(expectedResponse)) {
            System.out.println("testPrepareResponse  checkResponse error");
            System.out.println("  input='" + input + "'");
            System.out.println("  response='" + response + "'");
            System.out.println("  expected='" + expectedResponse + "'");
            return 0;
        } else {
            return 1;
        }
    }

    /*
     * Runs tests on a bunch of example sentences using a Random number generator
     * with a seed.
     */
    private static void testInputAndResponse() {
        int numPassed = 0;
        // since we are using a seed in the random number generator, the responses
        // should be fixed
        // for the provided response table.
        Random randGen = new Random(923);
        ArrayList<ArrayList<String>> responseTable = Eliza.loadResponseTable("Eliza" + Config.RESPONSE_FILE_EXTENSION);

        numPassed += checkResponse("I like computers.", "What do you think machines have to do with your problem?",
                randGen, responseTable);

        numPassed += checkResponse("What is your name?", "I don't care about names -- please continue.", randGen,
                responseTable);
        numPassed += checkResponse("You remind me of a grumpy uncle.", "What other connections do you see?", randGen,
                responseTable);
        numPassed += checkResponse("You don't seem to say much.", "What are your feelings now?", randGen,
                responseTable);
        numPassed += checkResponse("You reflect me.", "What makes you think I reflect you?", randGen, responseTable);
        numPassed += checkResponse("I wish to believe you.", "Why do you think you wish to believe me?", randGen,
                responseTable);
        numPassed += checkResponse("I dreamed of going into space.",
                "Have you ever dreamed of going into space before?", randGen, responseTable);
        numPassed += checkResponse("I apologize for boring you.", "Why do you think you apologize for boring me?",
                randGen, responseTable);
        numPassed += checkResponse("Because it seems the polite thing to do.",
                "Does that reason seem to explain anything else?", randGen, responseTable);
        numPassed += checkResponse("Yes.", "You are sure.", randGen, responseTable);
        numPassed += checkResponse("I remember being in 1st grade.",
                "What is the connection between me and being in 1st grade?", randGen, responseTable);
        numPassed += checkResponse("No, not often. Sometimes I remember being on a boat.",
                "Why do you recollect being on a boat just now?", randGen, responseTable);
        numPassed += checkResponse("My family liked to play cards.", "Tell me more about your family.", randGen,
                responseTable);
        numPassed += checkResponse("Do you remember growing up?", "What about growing up?", randGen, responseTable);
        numPassed += checkResponse("Are you a Packers fan?", "Perhaps I am a packers fan in your fantasies.", randGen,
                responseTable);
        numPassed += checkResponse("I am sad to hear that.", "Can you explain what made you sad?", randGen,
                responseTable);
        numPassed += checkResponse("I cannot explain this.", "Do you really want to be able to explain this?", randGen,
                responseTable);
        numPassed += checkResponse("You seem to have a different perspective than many.",
                "You're not really talking about me -- are you?", randGen, responseTable);
        numPassed += checkResponse("I'm talking to my dog.", "How long have you been talking to your dog?", randGen,
                responseTable);
        numPassed += checkResponse("goodbye", null, randGen, responseTable);

        numPassed += checkResponse("", "I'm not sure I understand you fully.", randGen, responseTable);

        int expected = 21;
        if (numPassed == expected) {
            System.out.println("testInputAndResponse passed ");
        } else {
            System.err.println("testInputAndResponse failed " + (expected - numPassed));
        }
    }

}


