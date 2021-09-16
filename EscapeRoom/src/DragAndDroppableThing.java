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
 * This class allows an action to happen when DraggableThing object is dropped
 * over target object
 */
public class DragAndDroppableThing extends DraggableThing {
    // object over which this object can be dropped
    private VisibleThing target;
    // action that results from dropping this object over target
    private Action action;
    /**
     * This method initializes new object DragandDroppableThing
     *
     * @param name   is the name of the object
     * @param x  	is the x coordinate of the object
     * @param y  	is the y coordinate of the object
     * @param target is the object over which this object can be dropped
     * @param action is that action that results from dropping this object over
     *           	target
     */
    public DragAndDroppableThing(String name, int x, int y, VisibleThing target, Action action) {
        super(name, x, y);
        this.target = target;
        this.action = action;
    }
    /**
     * This method returns action and deactivates objects in response to successful
     * drop When this object is over its target and its target is active: deactivate
     * both this object and the target object, and return action, otherwise return
     * null.
     */
    @Override
    protected Action drop() {
        // de-activates target and this object when target is over this object
        // and target is active
        if (this.isOver(target) && target.isActive()) {
            this.deactivate();
            target.deactivate();
            return action;
        }
        return null;
    }
}

