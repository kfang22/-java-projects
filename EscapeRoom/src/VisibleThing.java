
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
import processing.core.PImage;
import java.awt.*;
import java.io.File;
/**
 * This class outputs image of specific VisibleThing object
 */
public class VisibleThing extends Thing {
    // the graphical representation of this thing
    private PImage image;
    // the horizontal position (in pixels of this thing's left side)
    private int x;
    // the vertical position (in pixels of this thing's top side)
    private int y;
    /**
     * This method initializes new VisibleThing object
     *
     * @param name is the name of the object
     * @param x	is the x coordinate of the object
     * @param y	is the y coordinate of the object
     */
    public VisibleThing(String name, int x, int y) {
        super(name);
        this.image = getProcessing().loadImage("images" + File.separator + name + ".png");
        this.x = x;
        this.y = y;
    }
    /**
     * This method draws image at its position before returning null
     */
    @Override
    public Action update() {
        getProcessing().image(image, x, y);
        // System.out.println("test1");
        return null;
    }
    /**
     * This method changes x by adding dx to it (and y by dy)
     *
     * @param dx old position of x coordinate
     * @param dy old position of y coordinate
     */
    public void move(int dx, int dy) {
        x += dx;
        y += dy;
    }
    /**
     * This method returns true only when point x,y is over image
     *
     * @param x is the x coordinate of the mouse
     * @param y is the y coordinate of the mouse
     * @return true only when mouse is over image
     */
    public boolean isOver(int x, int y) {
        // creates new point at the x,y coordinates of the mouse
        Point p = new Point(x, y);
        // returns true if the point is within the parameters of the image
        return (this.x < p.getX() && this.y < p.getY() && this.x + image.width > p.getX()
                && this.y + image.height > p.getY());
    }
    /**
     * This method returns true only when other's image overlaps this one's
     *
     * @param other is the object of another VisibleThing
     * @return true only when both images overlap each other
     */
    public boolean isOver(VisibleThing other) {
        // returns true if one of the points of this image overlaps the parameter of
        // other image
        return (other.isOver(this.x, this.y) || other.isOver(this.x + image.width, this.y)
                || other.isOver(this.x, this.y + image.height)
                || other.isOver(this.x + image.width, this.y + image.height));
    }
}

