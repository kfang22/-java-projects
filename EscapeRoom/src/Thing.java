
//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:       	EscapeRoom
// Files:       	core.jar, image files, room files
// Course:      	CS300, Spring, 2019
//
// Author:      	Kevin Fang
// Email:       	kfang22@wisc.edu
// Lecturer's Name: Gary Dahl
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully
// acknowledge and credit those sources of help here.  Instructors and TAs do
// not need to be credited here, but tutors, friends, relatives, room mates,
// strangers, and others do.  If you received no outside help from either type
//  of source, then please explicitly indicate NONE.
//
// Persons:     	(identify each person and describe their help in detail)
// Online Sources:  (identify each URL and describe their assistance in detail)
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
import processing.core.PApplet;
/**
 * This class creates a new Thing object, activates/deactivates object,
 * determines if object is active/deactive, and if object has correct name
 */
public class Thing {
    // the constant name identifying this object
    private final String NAME;
    // active means thing is visible and can be interacted with
    private boolean isActive;
    private static PApplet processing = null;
    /**
     * This method initialize name, and set isActive to true
     *
     * @param name is the name of the Thing object
     */
    public Thing(String name) {
        NAME = name;
        isActive = true;
    }
    /**
     * This method returns true only when contents of name equal NAME
     *
     * @param name is the name of the Thing object
     * @return true if names equal
     */
    public boolean hasName(String name) {
        if (name.equals(NAME)) {
            return true;
        }
        return false;
    }
    /**
     * This method returns true only when isActive is true
     *
     * @return true if active
     */
    public boolean isActive() {
        if (isActive == true) {
            return true;
        }
        return false;
    }
    /**
     * This method changes isActive to true
     */
    public void activate() {
        isActive = true;
    }
    /**
     * This method changes isActive to false
     */
    public void deactivate() {
        isActive = false;
    }
    /**
     * This method just returns null Subclass types will override this update()
     * method to do more interesting things
     *
     * @return null
     */
    public Action update() {
        return null;
    }
    /**
     * This method initializes processing field
     *
     * @param processing PApplet object
     */
    public static void setProcessing(PApplet processing) {
        Thing.processing = processing;
    }
    /**
     * This method is an accessor method to retrieve this static field
     *
     * @return processing
     */
    protected static PApplet getProcessing() {
        return processing;
    }
}

