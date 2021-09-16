import java.util.ArrayList;

public class ElizaSeparateTesting {

    public static void main(String[] args) {
        ArrayList<String> separatedPhrases = new ArrayList<>();
        String input = "This is test, but I&^think it as practice. what do you\\r\\n\" +\n" +
                "                \"\t\t\t// think? ! is, er$er? 9&9. test!";

        separatedPhrases = separatePhrases(input);
        for(String str : separatedPhrases)
            System.out.println(str);


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
            // return arraylist of size 0 with empty string
            phrases.add("");
            // return userInput;
            return phrases;
        } else {
            // trim, and lowcase the original string
            userInput = userInput.trim();
            userInput = userInput.toLowerCase();
            // loop throng chars in the string,
            // replace unused chars with a space if !isLetterOrDigit(char) and (char !="?"
            // OR "!" OR ",", OR "."
            // public static boolean isLetterOrDigit(char ch)

            ///StringBuilder stBuilder = new StringBuilder(userInput);
            int inputLength = userInput.length();
            char[] inputChar = new char[inputLength];

            for (int i = 0; i < userInput.length(); i++) {
                char tempChar = userInput.charAt(i);
                inputChar[i] = tempChar;

                if (!isLetterOrDigit(tempChar) && (tempChar != '!' && tempChar != '?' && tempChar != ',' && tempChar != '.')) {
                    //stBuilder.setCharAt(i, ' ');
                    inputChar[i] = ' ';
                }

            }

            System.out.println("print char array:");
            for(int i =0; i<inputChar.length; i++) {

                System.out.print(inputChar[i]);
            }

            // loop String again to remove extra space between word

            // search for ?!,. in the string, find their indices, use subString() to create
            // substring
            // convert StringBuilder back to String
            ////String updatedUserInput = stBuilder.toString();

            String updatedUserInput = String.copyValueOf(inputChar);
            // remove extra white space between word using regular expression, them trim
            // both ends of string
            // e.g. orginal input = "This is test, but I&^think it as practice. what do you
            // think? ! is, erer? 9&9. test!";
            // updatedUserInput becomes "this is test, but i think it as practice. what do
            // you think? ! is, erer? 9 9. test!"
            // regex --- \\s is single white space, \\s+ is one or more white spaces
            updatedUserInput = (updatedUserInput.replaceAll("\\s+", " ")).trim();

            // then add substring to arraylist. again regex [.,?!]+, meaning to split when
            // seeing one or more in [.,?!]
            String delims = "[.,?!]+";

            String[] separatedStr = updatedUserInput.split(delims);

            for (int i = 0; i < separatedStr.length; i++) {
                // empty string will not be added to arraylist
                if (!separatedStr[i].equals(" ")) {
                    phrases.add(separatedStr[i].trim());
                }
            }

            System.out.println(phrases);
            return phrases;
        }

    }

    private static boolean isLetterOrDigit(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9') || (c == '\'');
    }

}