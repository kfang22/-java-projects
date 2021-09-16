import java.util.ArrayList;
import java.util.Arrays;

public class test {
    public static void main(String[] args) {
        char delimiter = ',';
        char qualifier = '"';
        String text = "\"a, aa\",\"b ,bb\",\"cc , c\"";
        if (text == null) {
            String[] emptyNull = new String[0];
            //return emptyNull;
        }
        if (text.isEmpty()) {
            String[] emptyString = new String[1];
            emptyString[0] = text;
            //return emptyString;
        }
        char[] singleLetters = text.toCharArray();
        int counter = 0;
        String separatedLetters = "";
        for (int i = 0; i < singleLetters.length; i++) {
            if (singleLetters[i] == delimiter) {
                counter++;
            }
        }
        System.out.println(counter + "test for counter");
        int quoteCounter = 0;
        ArrayList<Integer> quoteIndex = new ArrayList<>();
        for (int i = 0; i < singleLetters.length; i++) {
            if (singleLetters[i] == '"') {
                quoteCounter++;
                quoteIndex.add(i);
            }
        }


        int numCommas = 0;
        int minusCounter = 0;
        for (int k = 0; k < singleLetters.length; k++) {
            String subSub = "";
            if (quoteCounter > 0 && singleLetters[k] == qualifier) {
                for (int j = 0; j < quoteIndex.size(); j += 2) {
                    if (j != quoteIndex.size() - 1) {
                        subSub = text.substring(quoteIndex.get(j), quoteIndex.get(j + 1) + 1);
                        System.out.println(subSub + "'Test to see if it reads substring'");
                        if (subSub.contains(",")) {
                            System.out.println("test to see if it reads subsub");
                            --counter;
                            System.out.println(counter + " new counter");
                            text = text.replace(",", "~");//replaces all of them

                        }
                    }
                }
            }
        }
            char[] NewSingleLetters = text.toCharArray();
        System.out.println(counter + "counter");

            String[] separatedPhrase = new String[counter + 1];
            for (int i = 0; i < NewSingleLetters.length; i++) {
                String quoteSub = "";
                if (quoteCounter > 0 && NewSingleLetters[i] == qualifier) {
                    for (int j = 0; j < quoteIndex.size(); j += 2) {
                        if (j != quoteIndex.size() - 1) {
                            quoteSub = text.substring(quoteIndex.get(j), quoteIndex.get(j + 1) + 1);
                            separatedPhrase[j] = quoteSub;
                            System.out.println(quoteSub + " phrase with quotes");
                            if (quoteSub.contains(",")) {

                            }
                        }
                    }
                }


                if (NewSingleLetters[i] != delimiter) {
                    separatedLetters = separatedLetters + NewSingleLetters[i];
                }
                if (NewSingleLetters[i] == delimiter && i == NewSingleLetters.length) {
                    separatedLetters = "";
                    separatedPhrase[counter] = separatedLetters;
                }
                if (i == NewSingleLetters.length - 1 && NewSingleLetters[i] == delimiter) {
                    separatedPhrase[counter] = separatedLetters;
                }
                if (i == NewSingleLetters.length - 1 && NewSingleLetters[i] != delimiter) {
                    separatedPhrase[counter] = separatedLetters;
                }
                if (i != NewSingleLetters.length - 1) {
                    if (NewSingleLetters[i + 1] == delimiter) {
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
            System.out.println(separatedPhrase[1]);

        }
    }


