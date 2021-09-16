
//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           Eliza
// Files:           Eliza.java, ElizaTest.java, Config.Java, Eliza.rsp
// Course:          CS 200, Fall 2018
//
// Author:          Kevin Fang
// Email:           kfang22@wisc.edu
// Lecturer's Name: Mark Renault
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully
// acknowledge and credit those sources of help here.  Instructors and TAs do
// not need to be credited here, but tutors, friends, relatives, room mates
// strangers, etc do.  If you received no outside help from either type of
// source, then please explicitly indicate NONE.
//
// Persons:         None
// Online Sources:  None
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * The Eliza class holds the user input and response formation for a system that
 * collects user input and responds appropriately. Eliza is based off of a
 * computer program written at MIT in the 1960's by Joseph Weizenbaum. Eliza
 * uses keyword matching to respond to users in a way that displays interest in
 * the users and continues the conversation until instructed otherwise.
 */
public class Eliza {

    /**
     * This method does input and output with the user. It calls supporting methods
     * to read and write files and process each user input.
     *
     * @param args (unused)
     */
    public static void main(String[] args) {

        Scanner scnr = new Scanner(System.in);
        Random rand = new Random(Config.SEED);
        String choosenTherapist = null;
        String therapistName = "";
        ArrayList<String> savedDialoge = new ArrayList<>();
        ArrayList<ArrayList<String>> keywordsReponse = null;
        // if there is only one element in args, use that element
        if (args.length == 1) {
            String therapist = args[0] + Config.RESPONSE_FILE_EXTENSION;
            choosenTherapist = args[0];
            therapistName = therapist;
            // if there is no command line arguments, use Eliza.rsp file.
        } else if (args.length == 0) {
            choosenTherapist = "Eliza";
            therapistName = choosenTherapist + Config.RESPONSE_FILE_EXTENSION;
            // if there is more than one element in args, have user select which file they
            // wish to use.
        } else if (args.length > 1) {
            System.out.print("Would you like to speak with");
            // adds the names of the files into a string for user to pick.
            for (int i = 0; i < args.length; i++) {
                if (i == args.length - 1) {
                    System.out.println(" or " + args[i] + "?");
                } else {
                    System.out.print(" " + args[i] + ",");
                }
            }
            choosenTherapist = scnr.nextLine();
            therapistName = choosenTherapist + Config.RESPONSE_FILE_EXTENSION;
        }
        keywordsReponse = loadResponseTable(therapistName);
        // welcome user prompt
        String greeting = "Hi I'm " + choosenTherapist + ", what is your name?";
        System.out.println(greeting);
        // add user prompt to savedDialoge arraylist for later saving.
        savedDialoge.add(greeting);

        String userName = scnr.nextLine();
        // add userName to savedDialoge arraylist for later saving.
        savedDialoge.add(userName);

        String convoStarter = "Nice to meet you " + userName + ". What is on your mind?";
        System.out.println(convoStarter);

        // add convoStarter to savedDialoge arraylist for later saving.
        savedDialoge.add(convoStarter);
        // begin conversation loop until quit word is found.
        String userInput;
        boolean quit = false;

        do {
            userInput = scnr.nextLine();
            // add user input to savedDialoge arraylist for later saving.
            savedDialoge.add(userInput);

            String[] singleWordsArray = prepareInput(userInput);
            // breaks if there is a quit word.
            if (singleWordsArray == null) {
                break;
            }
            // if no quit words then prepareResponse for computer response output.
            String computerResponse = prepareResponse(singleWordsArray, rand, keywordsReponse);
            System.out.println(computerResponse);
            savedDialoge.add(computerResponse);
        } while (quit != true);
        // ending prompt
        System.out.println("Goodbye " + userName + ".");
        // add ending prompt to savedDialoge arraylist for later saving.
        savedDialoge.add("Goodbye " + userName + ".");

        boolean fileError;
        String savedFile = null;
        // loops until file successfully saves or user decides not to save.
        do {
            System.out.println("Would you like to have a record of our conversation (y/n): ");
            userInput = scnr.nextLine();
            // if user decides to save, ask user for file name to save and call saveDialog
            // method.
            if (userInput.toLowerCase().trim().charAt(0) == 'y') {
                try {
                    System.out.println("Enter filename: ");
                    savedFile = scnr.nextLine();
                    saveDialog(savedDialoge, savedFile);
                    System.out.println("Thanks again for talking! Our conversation is saved in: " + savedFile);
                    fileError = false;
                    // print error message if dialog is unable to save.
                } catch (IOException e) {
                    fileError = true;
                    System.out.println("Unable to save conversation to: " + savedFile);
                }
            }
            // else user decides not to save and breaks.
            else {
                break;
            }
        } while (fileError == true);
    }

    /**
     * This method processes the user input, returning an ArrayList containing
     * Strings, where each String is a phrase from the user's input. This is done by
     * removing leading and trailing whitespace, making the user's input all lower
     * case, then going through each character of the user's input. When going
     * through each character this keeps all digits, alphabetic characters and '
     * (single quote). The characters ? ! , . signal the end of a phrase, and
     * possibly the beginning of the next phrase, but are not included in the
     * result. All other characters such as ( ) - " ] etc. should be replaced with a
     * space. This method makes sure that every phrase has some visible characters
     * but no leading or trailing whitespace and only a single space between words
     * of a phrase. If userInput is null then return null, if no characters then
     * return a 0 length list, otherwise return a list of phrases. Empty phrases and
     * phrases with just invalid/whitespace characters should NOT be added to the
     * list.
     *
     * Example userInput: "Hi, I am! a big-fun robot!!!" Example returned: "hi", "i
     * am", "a big fun robot"
     *
     * @param userInput text the user typed
     * @return the phrases from the user's input
     */
    public static ArrayList<String> separatePhrases(String userInput) {

        ArrayList<String> phrases = new ArrayList<>();

        if (userInput == null) {
            return null;
        } else if (userInput == "") {
            phrases.add("");
            return phrases;
        } else {
            userInput = userInput.trim();
            userInput = userInput.toLowerCase();
            int inputLength = userInput.length();
            char[] inputChar = new char[inputLength];
            // loop through each character in userInput and replaces any character that
            // isn't a digit, alphabetic characters, or single quote.
            for (int i = 0; i < userInput.length(); i++) {
                char tempChar = userInput.charAt(i);
                inputChar[i] = tempChar;
                if (!isLetterOrDigit(tempChar)
                        && (tempChar != '!' && tempChar != '?' && tempChar != ',' && tempChar != '.')) {
                    inputChar[i] = ' ';
                }
            }
            // converts char array back into updatedUserInput string.
            String updatedUserInput = String.copyValueOf(inputChar);
            // remove extra white space between word using regular expression, them trim
            // both ends of string
            updatedUserInput = (updatedUserInput.replaceAll("\\s+", " ")).trim();
            // split when there is one or more (.,?!)
            String delims = "[.,?!]+";
            String[] separatedStr = updatedUserInput.split(delims);
            // loop String again to remove extra space between word
            // search for ?!,. in the string, find their indices
            for (int i = 0; i < separatedStr.length; i++) {
                // empty string will not be added to arraylist
                if (!separatedStr[i].equals(" ")) {
                    phrases.add(separatedStr[i].trim());
                }
            }
            return phrases;
        }
    }

    /**
     * Returns true or false depending on if the character within the userInput is
     * a digit, letter, or single quote.
     *
     * @param c Each character in the inputChar array
     * @return true if there are any characters that aren't digits, letters, or
     *         single quotes.
     */
    public static boolean isLetterOrDigit(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9') || (c == '\'');
    }

    /**
     * Checks whether any of the phrases in the parameter match a quit word from
     * Config.QUIT_WORDS. Note: complete phrases are matched, not individual words
     * within a phrase.
     *
     * @param phrases List of user phrases
     * @return true if any phrase matches a quit word, otherwise false
     */
    public static boolean foundQuitWord(ArrayList<String> phrases) {
        // goes through phrases to determine if there is a quit word.
        for (int i = 0; i < phrases.size(); i++) {
            String ifQuitWord = phrases.get(i);
            for (String quitWords : Config.QUIT_WORDS) {
                // if quit word is found, return quit words, else return false
                if (ifQuitWord.equals((quitWords))) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Iterates through the phrases of the user's input, finding the longest phrase
     * to which to respond. If two phrases are the same length, returns whichever
     * has the lower index in the list. If phrases parameter is null or size 0 then
     * return "" (Update 11/15/18).
     *
     * @param phrases List of user phrases
     * @return the selected phrase
     */
    public static String selectPhrase(ArrayList<String> phrases) {

        if (phrases.size() == 0 || phrases == null) {
            return "";
        }

        int[] wordCount = new int[phrases.size()];
        int maxNum = -1;
        int tempNum = 0;
        int phraseIndex = 0;
        String longestString;

        for (int i = 0; i < phrases.size(); i++) {
            String stringTemp = phrases.get(i);
            // counts how many characters of string in index i of phrases.
            for (int j = 0; j < stringTemp.length(); j++) {
                tempNum++;
            }
            wordCount[i] = tempNum;
            tempNum = 0;
            // Sets maxNum to a new value if new value is bigger.
            if (wordCount[i] > maxNum) {
                maxNum = wordCount[i];
                // locates the index of longest phrase.
                phraseIndex = i;
            }
        }
        // determines which phrase is largest depending on the index of the longest
        // phrase.
        longestString = phrases.get(phraseIndex);
        return longestString;
    }

    /**
     * Looks for a replacement word for the word parameter and if found, returns the
     * replacement word. Otherwise if the word parameter is not found then the word
     * parameter itself is returned. The wordMap parameter contains rows of match
     * and replacement strings. On a row, the element at the 0 index is the word to
     * match and if it matches return the string at index 1 in the same row. Some
     * example word maps that will be passed in are Config.INPUT_WORD_MAP and
     * Config.PRONOUN_MAP.
     *
     * If word is null return null. If wordMap is null or wordMap length is 0 simply
     * return word parameter. For this implementation it is reasonable to assume
     * that if wordMap length is >= 1 then the number of elements in each row is at
     * least 2.
     *
     * @param word    The word to look for in the map
     * @param wordMap The map of words to look in
     * @return the replacement string if the word parameter is found in the wordMap
     *         otherwise the word parameter itself.
     */
    public static String replaceWord(String word, String[][] wordMap) {

        String replaceWord = " ";
        String matchWord = word;

        if (word == null) {
            return null;
        }
        if (wordMap == null || wordMap.length == 0) {
            return word;
        } else {
            boolean foundMatch = false;
            // loop through wordMap
            for (int i = 0; i < wordMap.length; i++) {
                for (int k = 0; k < wordMap[i].length; k++) {
                    // if replace word is found, replace it with the replacement word.
                    if (word.equals(wordMap[i][0])) {
                        replaceWord = wordMap[i][k];
                        foundMatch = true;
                    }
                }
            }
            // determines if there is a replacement word or not.
            if (foundMatch)
                return replaceWord;
            else
                return matchWord;
        }
    }

    /**
     * Concatenates the elements in words parameter into a string with a single
     * space between each array element. Does not change any of the strings in the
     * words array. There are no leading or trailing spaces in the returned string.
     *
     * @param words a list of words
     * @return a string containing all the words with a space between each.
     */
    public static String assemblePhrase(String[] words) {

        String completeString = "";
        // assembles a string through the words array and adds spaces.
        for (int i = 0; i < words.length; i++) {
            completeString = completeString + words[i] + " ";
        }
        // removes the last "space" at the end of completeString from loop.
        completeString = completeString.trim();
        return completeString;
    }

    /**
     * Replaces words in phrase parameter if matching words are found in the mapWord
     * parameter. A word at a time from phrase parameter is looked for in wordMap
     * which may result in more than one word. For example: i'm => i am Uses the
     * replaceWord and assemblePhrase methods. Example wordMaps are
     * Config.PRONOUN_MAP and Config.INPUT_WORD_MAP. If wordMap is null then phrase
     * parameter is returned. Note: there will Not be a case where a mapping will
     * itself be a key to another entry. In other words, only one pass through
     * swapWords will ever be necessary.
     *
     * @param phrase  The given phrase which contains words to swap
     * @param wordMap Pairs of corresponding match & replacement words
     * @return The reassembled phrase
     */
    public static String swapWords(String phrase, String[][] wordMap) {

        if (wordMap == null) {
            return phrase;
        }
        // formats the elements of separateWords to lower case and trimmed.
        ArrayList<String> separateWords = new ArrayList<>();
        for (String validString : phrase.split(" ")) {
            separateWords.add(validString.trim().toLowerCase());
        }
        // goes through separateWords and replaces string with new string with replaced
        // words.
        for (int i = 0; i < separateWords.size(); i++) {
            String singleWord = separateWords.get(i);
            String correctedWord = replaceWord(singleWord, wordMap);
            separateWords.set(i, correctedWord);
        }
        // converts arraylist to array and assembles contents into completedPhrase
        // string.
        String[] wordCollection = new String[separateWords.size()];
        wordCollection = separateWords.toArray(wordCollection);
        String completedPhrase = assemblePhrase(wordCollection);

        return completedPhrase;
    }

    /**
     * This prepares the user input. First, it separates input into phrases (using
     * separatePhrases). If a phrase is a quit word (foundQuitWord) then return
     * null. Otherwise, select a phrase (selectPhrase), swap input words (swapWords
     * with Config.INPUT_WORD_MAP) and return an array with each word its own
     * element in the array.
     *
     * @param input The input from the user
     * @return words from the selected phrase
     */
    public static String[] prepareInput(String input) {

        ArrayList<String> separatedInput = separatePhrases(input);
        boolean isQuitWord = foundQuitWord(separatedInput);

        if (isQuitWord == true) {
            return null;
        }
        // assembles input so that longest phrase is chosen, all words that need to be
        // swapped are replaced,
        // and each word is it's own element.
        else {
            String choosenPhrase = selectPhrase(separatedInput);
            String completePhrase = swapWords(choosenPhrase, Config.INPUT_WORD_MAP);
            String[] singleWords = completePhrase.split(" ");
            return singleWords;
        }
    }

    /**
     * Reads a file that contains keywords and responses. A line contains either a
     * list of keywords or response, any blank lines are ignored. All leading and
     * trailing whitespace on a line is ignored. A keyword line begins with
     * "keywords" with all the following tokens on the line, the keywords. Each line
     * that follows a keyword line that is not blank is a possible response for the
     * keywords. For example (the numbers are for our description purposes here and
     * are not in the file):
     *
     * 1 keywords computer 2 Do computers worry you? 3 Why do you mention computers?
     * 4 5 keywords i dreamed 6 Really, <3>? 7 Have you ever fantasized <3> while
     * you were awake? 8 9 Have you ever dreamed <3> before?
     *
     * In line 1 is a single keyword "computer" followed by two possible responses
     * on lines 2 and 3. Line 4 and 8 are ignored since they are blank (contain only
     * whitespace). Line 5 begins new keywords that are the words "i" and "dreamed".
     * This keyword list is followed by three possible responses on lines 6, 7 and
     * 9.
     *
     * The keywords and associated responses are each stored in their own ArrayList.
     * The response table is an ArrayList of the keyword and responses lists. For
     * every keywords list there is an associated response list. They are added in
     * pairs into the list that is returned. There will always be an even number of
     * items in the returned list.
     *
     * Note that in the event an IOException occurs when trying to read the file
     * then an error message "Error reading <fileName>", where <fileName> is the
     * parameter, is printed and a non-null reference is returned, which may or may
     * not have any elements in it.
     *
     * @param fileName The name of the file to read
     * @return The response table
     */
    public static ArrayList<ArrayList<String>> loadResponseTable(String fileName) {

        ArrayList<ArrayList<String>> keywordResponseList = new ArrayList<>();
        String[] emptyArray = {};
        ArrayList<String> keywordList = new ArrayList<>(Arrays.asList(emptyArray));
        ArrayList<String> responseList = new ArrayList<>();
        File elizaFile = new File(fileName);
        Scanner sc = null;

        try {
            sc = new Scanner(elizaFile);
            ArrayList<String> lines = new ArrayList<>();
            // adds all of the texts inside the file into an array list.
            // each new line is an element.
            while (sc.hasNextLine()) {
                lines.add(sc.nextLine());
            }
            sc.close();

            for (int i = 0; i < lines.size(); i++) {
                // ignores empty lines
                if (lines.get(i).isEmpty()) {
                    continue;
                } // if first word is keyword, then adds to keywordsList array list.
                if (lines.get(i).trim().substring(0, lines.get(i).indexOf("s") + 1).contains("keywords")) {
                    String keywordLine = lines.get(i);
                    keywordLine = keywordLine.replace("keywords", "").trim();
                    // splits keywords so that each word is its own element.
                    String[] separateKeyWords = keywordLine.split(" ");
                    // converts array to array list.
                    keywordList = new ArrayList<>(Arrays.asList(separateKeyWords));
                    // if ArrayList<ArrayList<String>> has at least one keyword, add response.
                    // guarantees that keywords have odd indexes and responses have even indexes.
                    if (keywordResponseList.size() > 0) {
                        keywordResponseList.add(new ArrayList<>(responseList));
                    }
                    keywordResponseList.add(new ArrayList<>(keywordList));
                    // clears keyword and response array lists in order to store next
                    // keywords and responses.
                    keywordList.clear();
                    responseList.clear();
                    // if a response, adds to responseList array list.
                } else {
                    responseList.add(lines.get(i));
                }
            }
            keywordResponseList.add(responseList);
        } catch (IOException e) {
            System.err.println("Error reading " + fileName);
        }
        return keywordResponseList;
    }

    /**
     * Checks to see if the keywords match the sentence. In other words, checks to
     * see that all the words in the keyword list are in the sentence and in the
     * same order. If all the keywords match then this method returns an array with
     * the unmatched words before, between and after the keywords. If the keywords
     * do not match then null is returned.
     *
     * When the phrase contains elements before, between, and after the keywords,
     * each set of the three is returned in its own element String[] keywords =
     * {"i", "dreamed"}; String[] phrase = {"do", "you", "know", that", "i", "have",
     * "dreamed", "of", "being", "an", "astronaut"};
     *
     * toReturn[0] = "do you know that" toReturn[1] = "have" toReturn[2] = "of being
     * an astronaut"
     *
     * In an example where there is a single keyword, the resulting List's first
     * element will be the the pre-sequence element and the second element will be
     * everything after the keyword, in the phrase String[] keywords = {"always"};
     * String[] phrase = {"I", "always", "knew"};
     *
     * toReturn[0] = "I" toReturn[1] = "knew"
     *
     * In an example where a keyword is not in the phrase in the correct order, null
     * is returned. String[] keywords = {"computer"}; String[] phrase = {"My","dog",
     * "is", "lost"};
     *
     * return null
     *
     * @param keywords The words to match, in order, in the sentence.
     * @param phrase   Each word in the sentence.
     * @return The unmatched words before, between and after the keywords or null if
     *         the keywords are not all matched in order in the phrase.
     */
    public static String[] findKeyWordsInPhrase(ArrayList<String> keywords, String[] phrase) {

        ArrayList<String> unmatchedList = new ArrayList<>();
        String[] unmatchedPhrase;
        int index = 0;
        boolean foundKeyWord = false;

        for (int j = 0; j < keywords.size(); j++) {
            String keyword = keywords.get(j);
            foundKeyWord = false;
            String unmatchedWords = "";
            for (int k = index; k < phrase.length; k++) {
                if (keyword.equals(phrase[k])) {
                    foundKeyWord = true;
                    index = k + 1;
                    // adds unmatchedWords "sentence" to unmatchedList.
                    unmatchedWords = unmatchedWords.trim();
                    unmatchedList.add(unmatchedWords);
                    // if keyword is next to another keyword, set index to next element in phrase.
                    if (k != phrase.length - 1 && j != keywords.size() - 1
                            && keywords.get(j + 1).equals(phrase[k + 1])) {
                        index = k + 1;
                    }
                    // if keyword is the last keyword and last word of phrase, adds "" to
                    // unmatchedList.
                    if (keyword.equalsIgnoreCase(keywords.get(keywords.size() - 1)) && k == phrase.length - 1) {
                        unmatchedList.add("");
                        // if keyword is the last keyword but not the last word of phrase, add rest of
                        // phrase to unmatchedList.
                    }
                    if (keyword.equalsIgnoreCase(keywords.get(keywords.size() - 1)) && k != phrase.length - 1) {
                        String lastPhrase = "";
                        // for loop to add rest of phrase into unmatchedList.
                        for (int i = k + 1; i < phrase.length; i++) {
                            lastPhrase += phrase[i] + " ";
                        }
                        lastPhrase = lastPhrase.trim();
                        unmatchedList.add(lastPhrase);
                    }
                    // if word at index k is not equal to keyword, keep word into unmatchedWord
                    // "sentence".
                    // keeps adding words until word at index k is a keyword.
                    break;
                } else {
                    unmatchedWords += phrase[k] + " ";
                }
            }
            // if no keywords are found in phrases, breaks from outer loop.
            if (foundKeyWord == false) {
                break;
            }
        }
        // if there is no keywords in phrases, then returns null. Otherwise, returns
        // unmatchedPhrase array.
        if (foundKeyWord) {
            unmatchedPhrase = unmatchedList.toArray(new String[unmatchedList.size()]);
            return unmatchedPhrase;
        } else {
            return null;
        }
    }

    /**
     * Selects a randomly generated response within the list of possible responses
     * using the provided random number generator where the number generated
     * corresponds to the index of the selected response. Use Random nextInt(
     * responseList.size()) to generate the random number. If responseList is null
     * or 0 length then return null.
     *
     * @param rand         A random number generator.
     * @param responseList A list of responses to choose from.
     * @return A randomly selected response
     */
    public static String selectResponse(Random rand, ArrayList<String> responseList) {

        if (responseList == null || responseList.size() == 0) {
            return null;
        }
        // selects a random index based on the responseList size.
        int randomInt = rand.nextInt(responseList.size());
        String randomResponse = responseList.get(randomInt);
        return randomResponse;
    }

    /**
     * This method takes processed user input and forms a response. This looks
     * through the response table in order checking to see if each keyword pattern
     * matches the userWords. The first matching keyword pattern found determines
     * the list of responses to choose from. A keyword pattern matches the
     * userWords, if all the keywords are found, in order, but not necessarily
     * contiguous. This keyword matching is done by findKeyWordsInPhrase method. See
     * the findKeyWordsInPhrase algorithm in the Eliza.pdf.
     *
     * If no keyword pattern matches then Config.NO_MATCH_RESPONSE is returned.
     * Otherwise one of possible responses for the matched keywords is selected with
     * selectResponse method. The response selected is checked for the replacement
     * symbol <n> where n is 1 to the length of unmatchedWords array returned by
     * findKeyWordsInPhrase. For each replacement symbol the corresponding unmatched
     * words element (index 0 for <1>, 1 for <2> etc.) has its pronouns swapped with
     * swapWords using Config.PRONOUN_MAP and then replaces the replacement symbol
     * in the response.
     *
     * @param userWords     using input after preparing.
     * @param rand          A random number generator.
     * @param responseTable A table containing a list of keywords and response
     *                      pairs.
     * @return The generated response
     */
    public static String prepareResponse(String[] userWords, Random rand, ArrayList<ArrayList<String>> responseTable) {
        // unmatched words that don't include keywords
        String[] cleanedInput = new String[responseTable.size()];
        boolean hasKeyWord = false;
        String randomResponse = "";
        String insideBracket = "";
        String choosenInput = "";
        int leftBracIndex;
        int rightBracIndex;
        int intPosition;
        int cleanedInputIndex;
        int bracketCounter = 0;
        // iterates through each keyword to see if any matches in userWords
        for (int i = 0; i < responseTable.size(); i += 2) {
            hasKeyWord = false;
            cleanedInput = findKeyWordsInPhrase(responseTable.get(i), userWords);
            // if keyword doesn't match any elements in userWords, continue on next keyword
            if (cleanedInput == null) {
                continue;
            } else {
                hasKeyWord = true;
                randomResponse = selectResponse(rand, responseTable.get(i + 1));
                break;
            }
        }
        // if none of the keywords match, then return Config.NO_MATCH_RESPONSE.
        if (hasKeyWord == false) {
            return Config.NO_MATCH_RESPONSE;
        }
        // counts how many input phrases are required for the responses.
        for (int i = 0; i < randomResponse.length(); i++) {
            char c = randomResponse.charAt(i);
            if (c == '<' || c == '>') {
                bracketCounter++;
            }
        }
        // if there are no input phrases needed, add untouched response.
        if (bracketCounter == 0) {
            return randomResponse;
        }
        // divided by 2 because brackets are pair
        bracketCounter = bracketCounter / 2;
        // loops until all <n> are replaced.
        for (int j = 0; j < bracketCounter; j++) {
            if (randomResponse.contains("<") && randomResponse.contains(">")) {
                leftBracIndex = randomResponse.indexOf("<");
                rightBracIndex = randomResponse.indexOf(">");
                // substrings only n of <n>.
                insideBracket = randomResponse.substring(leftBracIndex + 1, rightBracIndex);
                intPosition = Integer.parseInt(insideBracket);
                // selects index n of cleanedInput array to replaced <n>
                cleanedInputIndex = intPosition - 1;
                choosenInput = cleanedInput[cleanedInputIndex];
                choosenInput = swapWords(choosenInput, Config.PRONOUN_MAP);
                // substring only <n>.
                insideBracket = randomResponse.substring(leftBracIndex, rightBracIndex + 1);
                randomResponse = randomResponse.replace(insideBracket, choosenInput);
            }
        }
        return randomResponse;
    }

    /**
     * Creates a file with the given name, and fills that file line-by-line with the
     * tracked conversation. Every line ends with a newline. Throws an IOException
     * if a writing error occurs.
     *
     * @param dialog   the complete conversation
     * @param fileName The file in which to write the conversation
     * @throws IOException
     */
    public static void saveDialog(ArrayList<String> dialog, String fileName) throws IOException {

        PrintWriter writer = new PrintWriter(fileName);
        // Adds each element of dialog into a file using PrintWriter.
        for (String responseLine : dialog) {
            writer.println(responseLine);
        }
        writer.close();
    }
}


