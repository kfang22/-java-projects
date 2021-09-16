
//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           IteratingToPhilosophy
// Files:           jsoup-1.10.3.jar
// Course:          CS300 Spring 2019
//
// Author:          Kevin Fang
// Email:           kfang22@wisc.edu
// Lecturer's Name: Gary Dalh
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully
// acknowledge and credit those sources of help here.  Instructors and TAs do
// not need to be credited here, but tutors, friends, relatives, room mates,
// strangers, and others do.  If you received no outside help from either type
//  of source, then please explicitly indicate NONE.
//
// Persons:         None
// Online Sources:  None
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Scanner;
import java.util.function.Function;

public class NextWikiLink implements Function<String, String> {
    @Override
    public String apply(String t) {
        try {
            // Download a Wikipedia page, using t in their internal link format:
            // /wiki/Some_Subject
            Document doc = Jsoup.connect("https://en.wikipedia.org" + t).get();
            // Use .css selector to retrieve a collection of links from this page's
            // description
            // "p a" selects links within paragraphs
            // ":not([title^='Help'])" skips pronunciations
            // ":not(sup a)" skips citations
            Elements links = doc.select("p a:not([title^='Help']):not(sup a)");
            // return the link attribute from the first element of this list
            return links.get(0).attr("href");
            // Otherwise return an appropriate error message:
        } catch (IOException | IllegalArgumentException e) {
            return "FAILED to find wikipedia page: " + t;
        } catch (IndexOutOfBoundsException e) {
            return "FAILED to find a link in wikipedia page: " + t;
        }
    }

    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        // desired user search for Wikipedia
        String userSearch;
        // desired amount of iterations for searches
        int numIterations;

        // prompt the user to enter a topic name
        System.out.print("Enter a wikipedia page topic: ");
        userSearch = scnr.nextLine();
        System.out.println(userSearch);
        userSearch = "/wiki/" + userSearch.replaceAll(" ", "_");
        // prompt the user to enter a number of iterations to follow
        System.out.print("Enter the number of pages you'd like to step through: ");
        numIterations = scnr.nextInt();
        System.out.println(numIterations);
        // creates a Generator object to iterate for next Wikipedia search
        Generator wikiPhil = new Generator(userSearch, new NextWikiLink(), numIterations);
        // loops through the entirety of the Generator object depending on numIterations
        for (Object wikiName : wikiPhil) {
            // prints out every iteration of the search
            System.out.println(wikiName);
            // breaks from for-each loop if failed to find userSearch in Wikipedia
            if (wikiName.equals("FAILED to find wikipedia page: " + userSearch)) {
                break;
            }
        }
    }
}



