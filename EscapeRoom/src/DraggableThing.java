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
 * This class allows an VisibleThing object to move according the the x,y
 * coordinates of the mouse
 */
public class DraggableThing extends VisibleThing {
    // similar to use in ClickableThing
    private boolean mouseWasPressed;
    // true when this object is being dragged by the user
    private boolean isDragging;
    // horizontal position of mouse during last update
    private int oldMouseX;
    // vertical position of mouse during last update
    private int oldMouseY;
    // initialize new thing
    public DraggableThing(String name, int x, int y) {
        super(name, x, y);
        isDragging = false;
        mouseWasPressed = false;
    }
    /**
     * This method calls VisibleThing update(), then moves according to mouse drag
     * each time isDragging changes from true to false, the drop() method below will
     * be called once and any action objects returned from that method should then
     * be returned from update()
     */
    @Override
    public Action update() {
        // current x coordinate of mouse
        int currentX;
        // current y coordinate of mouse
        int currentY;
        Action action = null;
        super.update();
        // if mouse hasn't been pressed yet and is over object, set mouseWasPressed true
        if (!mouseWasPressed && getProcessing().mousePressed
                && this.isOver(getProcessing().mouseX, getProcessing().mouseY)) {
            isDragging = true;
            oldMouseX = getProcessing().mouseX;
            oldMouseY = getProcessing().mouseY;
        }
        // if mouse is no longer pressed, set isDragging to false to call drop()
        else if (!getProcessing().mousePressed && isDragging) {
            isDragging = false;
            action = drop();
        }
        // if isDragging is true and mouse is pressed down, image is able to be dragged
        else if (isDragging) {
            // set current x coordinate of object to x coordinate of mouse
            currentX = getProcessing().mouseX - oldMouseX;
            // set current y coordinate of object to y coordinate of mouse
            currentY = getProcessing().mouseY - oldMouseY;
            // moves image by new x,y coordinates
            move(currentX, currentY);
            // set old x,y coordinates to current x,y coordinates of mouse
            oldMouseX = getProcessing().mouseX;
            oldMouseY = getProcessing().mouseY;
//        	isDragging = getProcessing().mousePressed;
        }
        /**
         * // if mouse isn't dragging, was clicked, and is over object, set isDragging
         * true else if(!isDragging && mouseWasPressed && getProcessing().mousePressed
         * && this.isOver(getProcessing().mouseX,getProcessing().mouseY)){ isDragging =
         * true; oldMouseX = getProcessing().mouseX; oldMouseY = getProcessing().mouseY;
         *
         * }
         */
        mouseWasPressed = getProcessing().mousePressed;
        return action;
    }
    /**
     * This method returns null subclass types will override this drop() method to
     * do more interesting things
     *
     * @return
     */
    protected Action drop() {
        return null;
    }
}

