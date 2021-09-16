//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           Ch 7 Program (P7), Part 1: Parsing with Delimiter
// Files:           CSVParser.java
//                  TestCSVParser.java
// Course:          CS 200 Fall 2018
//
// Author:          Kevin Fang
// Email:           kfang22@wisc.edu
// Lecturer's Name: Marc Renault
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully
// acknowledge and credit those sources of help here.  Instructors and TAs do
// not need to be credited here, but tutors, friends, relatives, room mates
// strangers, etc do.  If you received no outside help from either type of
// source, then please explicitly indicate NONE.
//
// Persons:         (identify each person and describe their help in detail)
// Online Sources:  (identify each URL and describe their assistance in detail)
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////


import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class contains methods to parse text, specifically intended to eventually parse CSV (comma separated values)
 * files.
 *
 * Definitions of Terms:
 * delimiter: The character used to specify the boundary between fields in a sequence of characters.
 *            for example, the comma would be the delimiter between the fields:
 *              name, id, section
 *            Note the field values include the spaces. In general, the field value is every character except
 *            for the delimiter.
 *
 * qualifier: If a field value contains a delimiter such as a comma with the name field:
 *              John Doe, MD, 1234567, 321
 *            then a qualifier, such as ", is placed around a field value to indicate it is the same field.
 *            for example:
 *              "John Doe, MD", 1234567, 321
 *            If the qualifier itself is a part of the field value then the qualifier is duplicated.
 *            for example for the field value:
 *              H. Jackson Brown, Jr. said "The best preparation for tomorrow is doing your best today."
 *            would be enclosed with " and internal quotes duplicated resulting in the qualified field value:
 *              "H. Jackson Brown, Jr. said ""The best preparation for tomorrow is doing your best today."""
 *
 *            A example with , as delimiter and " as qualifier with a newline \n in a field value should be
 *            processed as follows:
 *              "This is a\nline of text.",bbb,ccc
 *            would have 3 fields
 *              "This is a\nline of text."
 *              bbb
 *              ccc
 *
 * This project is derived from RFC 4180: https://tools.ietf.org/html/rfc4180
 *
 */
public class CSVParser {

    /**
     * This method returns the array of string containing the field values.
     * The field values in text are identified using , as the delimiter.
     * Qualifiers are not considered at all in the parsing of text.
     *
     * Example:
     *   aaa,bbb,ccc
     * should result in an array with 3 fields
     *   aaa
     *   bbb
     *   ccc
     *
     *   aaa,bbb,
     * should also result in an array with 3 fields with the 3rd field containing either whitespace or no spaces
     * depending on the actual characters after the last ,.
     *
     * If text is null then a zero length array is returned. If text is non-null
     * but no delimiters are found then a single length array is returned with the text as the field value.
     *
     * @param text  A sequence of characters that may include , as a delimiter.
     * @return An array of String containing each field value.  The size of the
     *    array is the number of fields found.
     */
    public static String[] separate(String text) {

        //algorithm:
        //determine the number of fields by counting the number of , in the text string.
        //create an array of String for the number of fields
        //add each field value to the corresponding array index.
        //return the array of field values
        //String text = "aaa,bbb,";
        if (text == null) {
            String[] emptyNull = new String[0];
            return emptyNull;
        }
        char[] singleLetters = text.toCharArray();
        if (text.isEmpty()) {
            String[] emptyString = new String[1];
            emptyString[0] = text;
            return emptyString;
        }
        int counter = 0;
        String separatedLetters = "";
        for (int i = 0; i < singleLetters.length; i++) {
            if (singleLetters[i] == ',') {
                counter++;
            }
        }
        String[] separatedPhrase = new String[counter + 1];
        int numCommas = 0;

            for (int i = 0; i < singleLetters.length; i++) {
                if (singleLetters[i] != ',') {
                    separatedLetters = separatedLetters + singleLetters[i];
                }
                if (singleLetters[i] == ',' && i == singleLetters.length) {
                    separatedLetters = "";
                    separatedPhrase[counter] = separatedLetters;
                }
                if (i == singleLetters.length - 1 && singleLetters[i] == ',') {
                    separatedPhrase[counter] = separatedLetters;
                }
                if (i == singleLetters.length - 1 && singleLetters[i] != ',') {
                    separatedPhrase[counter] = separatedLetters;
                }
                if(i != singleLetters.length-1){
                    if (singleLetters[i + 1] == ',') {
                        if (numCommas < counter + 1) {
                            separatedPhrase[numCommas] = separatedLetters;
                            numCommas++;
                            separatedLetters = "";
                            continue;
                        }
                        continue;
                    }
                }
            }
        System.out.println(Arrays.toString(separatedPhrase));

        return separatedPhrase;  //FIXME in Part1
    }

    /**
     * This method is the same as separate(String text) except that any character can be used as the delimiter
     * specified as a parameter. In this method, qualifiers are not considered at all in the parsing of text.
     *
     * Example:
     *   aaa,bbb,ccc\nddd,eee,fff
     * with \n as the delimiter should result in an array with 2 fields
     *   aaa,bbb,ccc
     *   ddd,eee,fff
     *
     * @param text  A sequence of characters.
     * @param delimiter The character that separates field values in text.
     * @return An array of String containing each field value.  The size of the
     *    array is the number of fields found.
     *
     */
    public static String[] separate(String text, char delimiter) {

        //revise your code from the previous method to use the character passed in the delimiter
        //parameter rather than using ','.
        if (text == null) {
            String[] emptyNull = new String[0];
            return emptyNull;
        }
        char[] singleLetters = text.toCharArray();
        if (text.isEmpty()) {
            String[] emptyString = new String[1];
            emptyString[0] = text;
            return emptyString;
        }
        int counter = 0;
        String separatedLetters = "";
        for (int i = 0; i < singleLetters.length; i++) {
            if (singleLetters[i] == delimiter) {
                counter++;
            }
        }
        int quoteCounter = 0;
        ArrayList<Integer> quoteIndex = new ArrayList<>();
        for (int i = 0; i < singleLetters.length; i++) {
            if (singleLetters[i] == '"') {
                quoteCounter++;
                quoteIndex.add(i);
            }
        }
        String[] separatedPhrase = new String[counter + 1];
        int numCommas = 0;

        for (int i = 0; i < singleLetters.length; i++) {
            if (singleLetters[i] != delimiter) {
                separatedLetters = separatedLetters + singleLetters[i];
            }
            if (singleLetters[i] == delimiter && i == singleLetters.length) {
                separatedLetters = "";
                separatedPhrase[counter] = separatedLetters;
            }
            if (i == singleLetters.length - 1 && singleLetters[i] == delimiter) {
                separatedPhrase[counter] = separatedLetters;
            }
            if (i == singleLetters.length - 1 && singleLetters[i] != delimiter) {
                separatedPhrase[counter] = separatedLetters;
            }
            if(i != singleLetters.length-1){
                if (singleLetters[i + 1] == delimiter) {
                    if (numCommas < counter + 1) {
                        separatedPhrase[numCommas] = separatedLetters;
                        numCommas++;
                        separatedLetters = "";
                        continue;
                    }
                    continue;
                }
            }
        }
        System.out.println(Arrays.toString(separatedPhrase));

        return separatedPhrase;  //FIXME in Part1

    }

    /**
     * This method extends the previous separate methods in that qualifiers are now considered. See the class
     * header comment for examples of qualifier. Qualifiers are kept as a part of the field values.
     *
     * Example:
     *    "H. Jackson Brown, Jr. said ""The best preparation for tomorrow is doing your best today.""",bbb
     * With delimiter , and qualifier " would result in the following field values:
     *    "H. Jackson Brown, Jr. said ""The best preparation for tomorrow is doing your best today."""
     *    bbb
     *
     * @param text  A sequence of characters.
     * @param delimiter The character that separates field values in text.
     * @param qualifier The character indicating the beginning and ending of the field value such
     *                  that delimiters could be within a field value.
     * @return An array of String containing each field value.  The size of the
     *    array is the number of fields found.
     *
     */
    public static String[] separate(String text, char delimiter, char qualifier) {
        if (text == null) {
            String[] emptyNull = new String[0];
            return emptyNull;
        }
        char[] singleLetters = text.toCharArray();
        if (text.isEmpty()) {
            String[] emptyString = new String[1];
            emptyString[0] = text;
            return emptyString;
        }
        int counter = 0;
        String separatedLetters = "";
        for (int i = 0; i < singleLetters.length; i++) {
            if (singleLetters[i] == delimiter) {
                counter++;
            }
        }
        String[] separatedPhrase = new String[counter + 1];
        int numCommas = 0;

        for (int i = 0; i < singleLetters.length; i++) {
            if (singleLetters[i] != delimiter) {
                separatedLetters = separatedLetters + singleLetters[i];
            }
            if (singleLetters[i] == delimiter && i == singleLetters.length) {
                separatedLetters = "";
                separatedPhrase[counter] = separatedLetters;
            }
            if (i == singleLetters.length - 1 && singleLetters[i] == delimiter) {
                separatedPhrase[counter] = separatedLetters;
            }
            if (i == singleLetters.length - 1 && singleLetters[i] != delimiter) {
                separatedPhrase[counter] = separatedLetters;
            }
            if(i != singleLetters.length-1){
                if (singleLetters[i + 1] == delimiter) {
                    if (numCommas < counter + 1) {
                        separatedPhrase[numCommas] = separatedLetters;
                        numCommas++;
                        separatedLetters = "";
                        continue;
                    }
                    continue;
                }
            }
        }
        System.out.println(Arrays.toString(separatedPhrase));

        return separatedPhrase;
    }

    /**
     * This method extends the previous by adding the parameter keepQualifiers.  If keepQualifiers is true
     * the returned field values include the qualifier characters.  If keepQualifiers is false then the
     * returned field values have the qualifier characters removed.
     *
     * Example:
     *    "H. Jackson Brown, Jr. said ""The best preparation for tomorrow is doing your best today.""", bbb
     * With delimiter , and qualifier " and keepQualifiers false would result in the following field values:
     *    H. Jackson Brown, Jr. said "The best preparation for tomorrow is doing your best today."
     *     bbb
     *
     * @param text  A sequence of characters.
     * @param delimiter The character that separates field values in text.
     * @param qualifier The character indicating the beginning and ending of the field value such
     *                  that delimiters could be within a field value.
     * @param keepQualifiers true to keep qualifiers, false to remove qualifiers
     * @return An array of String containing each field value.  The size of the
     *    array is the number of fields found.
     */
    public static String[] separate(String text, char delimiter, char qualifier, boolean keepQualifiers) {
        return null;  //FIXME in Part3
    }
}