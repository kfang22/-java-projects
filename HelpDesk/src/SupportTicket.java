//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           HelpDesk
// Files:           SupportTicket.java, HelpDesk.java, HelpDeskTestSuite.java
//                  HelpDeskInterface.java
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

/**
 * This class creates SupportTicket object that will added into HelpDesk.
 * It allows to compare other SupportTickets as well as return their message.
 */
public class SupportTicket implements Comparable<SupportTicket> {
    // String message of SupportTicket
    private String message;

    /**
     * This method is the constructor of SupportTicket
     * Throws NullPointerException if message is null
     *
     * @param message String message of SupportTicket
     */
    public SupportTicket(String message){
        // throw exception if message is null
        if(message == null){
            throw new NullPointerException();
        }
        this.message = message;
    }

    /**
     * This method returns the message of SupportTicket
     *
     * @return the message of SupportTicket
     */
    public String toString(){
        return message;
    }

    /**
     * This method compares this SupportTicket with another SupportTicket
     *
     * @param otherMessage other SupportTicket that will be compared
     * @return 1 if this SupportTicket is larger than other SupportTicket
     *         -1 of this SupportTicket is smaller than other SupportTicket
     *         0 if this SupportTicket is same size as other SupportTicket
     */
    public int compareTo(SupportTicket otherMessage){
        // if this SupportTicket is larger length, return 1
        if(message.length() > otherMessage.toString().length()){
            return 1;
        }
        // if this SupportTicket is shorter length, return -1
        else if(message.length() < otherMessage.toString().length()){
            return -1;
        }
        // if this SupportTicket has same length
        else{
            // compare values of String messages within these SupportTickets
            int compare = message.compareTo(otherMessage.toString());
            // if this SupportTicket message value is larger, return 1
            if(compare >= 1){
                return 1;
            // if this SupportTicket message value is smaller, return -1
            }else if(compare <= -1){
                return -1;
            }
            // if value of both SupportTicket messages are same, return 0
            return 0;
        }
    }
}
