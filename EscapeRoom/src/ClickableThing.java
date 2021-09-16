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
/**
 * This class allows an action to happen when mouse is over ClickableThing
 * object
 */
public class ClickableThing extends VisibleThing {
    // action returned from update when this object is clicked
    private Action action;
    // tracks whether the mouse was pressed during the last update()
    private boolean mouseWasPressed;
    /**
     * This method initializes the ClickableThing object
     *
     * @param name   is the name of the object
     * @param x  	is the x coordinate of the object
     * @param y  	is the y coordinate of the object
     * @param action is the action the occurs when object is clicked
     */
    public ClickableThing(String name, int x, int y, Action action) {
        super(name, x, y);
        this.action = action;
        mouseWasPressed = false;
    }
    /**
     * This method calls VisibleThing update, then returns action only when mouse is
     * first clicked
     */
    @Override
    public Action update() {
        // calls update() of VisibleThing
        super.update();
        // return action only if mouse is over object and mouse was first clicked
        if (!mouseWasPressed && getProcessing().mousePressed
                && this.isOver(getProcessing().mouseX, getProcessing().mouseY)) {
//            mouseWasPressed = true;
            mouseWasPressed = getProcessing().mousePressed;
            return action;
        }
        mouseWasPressed = getProcessing().mousePressed;
        return null;
    }
}

