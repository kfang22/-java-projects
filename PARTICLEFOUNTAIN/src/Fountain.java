//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           Particle Fountain
// Files:           P02Fountain.jar
// Course:          CS300, Spring, 2019
//
// Author:          Oat Sukcharoenyingyong
// Email:           sukcharoenyi@wisc.edu
// Lecturer's Name: Gary Dahl
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    Kevin Fang
// Partner Email:   kfang22@wisc.edu
// Partner Lecturer's Name: Gary Dahl
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   _x__ Write-up states that pair programming is allowed for this assignment.
//   _x__ We have both read and understand the course Pair Programming Policy.
//   _x__ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully
// acknowledge and credit those sources of help here.  Instructors and TAs do
// not need to be credited here, but tutors, friends, relatives, room mates,
// strangers, and others do.  If you received no outside help from either type
//  of source, then please explicitly indicate NONE.
//
// Persons:         (identify each person and describe their help in detail)
// Online Sources:  (identify each URL and describe their assistance in detail)
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

import java.util.Random;

/**
 * This class creates an animation of a water fountain. As time goes on, this class updates
 * the particles in the particle array and also creates new particles. Particles can only stay
 * for a certain amount of time before they are removed from the particle array, and then they
 * are replaced by new particles. You can change the starting point of the particles by clicking
 * your mouse on the screen where you want the particles to start,
 * and you can also take a screenshot of the water fountain.
 * @author oatsukcharoenyingyong
 *
 */
public class Fountain {
    private static Random randGen = new Random();
    // creates a Particle object array to store individual particles
    private static Particle[] particles;
    // middle of the screen (left to right): 400
    private static int positionX = 400;
    // middle of the screen (top to bottom): 300
    private static int positionY = 300;
    // blue: Utility.color(23,141,235)
    private static int startColor;
    // lighter blue: Utility.color(23,200,255)
    private static int endColor;

    /**
     * This method tests methods updateParticle and RemoveOldParticle as well as
     * initializes particle array
     */
    public static void setup() {
        // tests updateParticle and removeOldParticle methods before program main
        // initializes
        if (!testUpdateParticle()) {
            System.out.print("FAILED1");
        }
        if (!testRemoveOldParticles()) {
            System.out.print("FAILED2");
        }
        // creates/initializes specified particle array length
        particles = new Particle[800];
    }

    /**
     * This method gets/updates the conditions of each particle object within
     * particle array.
     *
     * @param index represents each individual particle within particle array
     */
    private static void updateParticle(int index) {
        // adds gravity acceleration to the particle
        float gravity = 0.3f;
        // represent the x and y velocity of each particle along with the effects of
        // gravity
        float velocityX = particles[index].getVelocityX();
        particles[index].setVelocityY(particles[index].getVelocityY() + gravity);
        float velocityY = particles[index].getVelocityY();
        // represents the starting x and y coordinates of each particle
        float positionX = particles[index].getPositionX();
        float positionY = particles[index].getPositionY();
        // updates/fills the color of each particle to random shades of blue
        particles[index].getColor();
        Utility.fill(particles[index].getColor());
        // sets physical shape/representation of each particle
        Utility.circle(positionX, positionY, particles[index].getSize());
        // updates new position x and y coordinates of particles after particle moves
        particles[index].setPositionX(velocityX + positionX);
        particles[index].setPositionY(velocityY + positionY);
        // increases/set the age of each particle by one increment each time method is
        // called
        int age = particles[index].getAge();
        age++;
        particles[index].setAge(age);
    }

    /**
     * Creates new particles and places them in the empty spaces of the particles
     * array.
     *
     * @param numParticles
     */
    private static void createNewParticles(int numParticles) {
        // these two are colors of the circle that are mixed in random amounts
        startColor = Utility.color(23, 141, 235);
        endColor = Utility.color(23, 200, 255);
        // loops through the indexes of the particle array
        for (int i = 0; i < particles.length; i++) {
            // if the space is empty at the index i of the particles array, then we create
            // a new particle
            if (particles[i] == null) {
                // these are the variables that need to be random every time we try
                // to create a particle
                int randPosition = randGen.nextInt(13) - 6;
                float randSize = randGen.nextFloat() * 7 + 4;
                float randColor = randGen.nextFloat();
                float randvelocityX = randGen.nextFloat() * 2 - 1;
                float randvelocityY = randGen.nextFloat() * 15 - 5;
                int randAge = randGen.nextInt(41);
                int randTransparency = randGen.nextInt(96) + 32;
                // creates a new particle at index i
                particles[i] = new Particle();
                // sets the x and y position for the new particle
                particles[i].setPositionX(randPosition + positionX);
                particles[i].setPositionY(randPosition + positionY);
                // sets the random size of the new particle
                particles[i].setSize(randSize);
                // sets a color of the particle with a random mix between the
                // startColor and the endColor
                particles[i].setColor(Utility.lerpColor(startColor, endColor, randColor));
                // sets the x and y velocity of the new particle
                particles[i].setVelocityX(randvelocityX);
                particles[i].setVelocityY(randvelocityY);
                // sets a random age for the new particle
                particles[i].setAge(randAge);
                // sets the random transparency for the new particle
                particles[i].setTransparency(randTransparency);
                // decrement numParticles every time a particle is created, this way we know
                numParticles--;
                // when we have succeeded in creating numParticles number of particles,
                // we break from the for loop
                if (numParticles == 0) {
                    break;
                }
            }
        }
    }

    /**
     * Remove old particles with ages that exceed maxAge.
     *
     * @param maxAge - the maximum age that a particle can be before getting removed
     */
    private static void removeOldParticles(int maxAge) {
        // loops through the particles array
        for (int i = 0; i < particles.length; i++) {
            // checks to see if particles at index i has a particle object and is not empty
            if (particles[i] != null) {
                // if the particle's age at index i is greater than maxAge then
                // the particles at index i becomes null
                if (particles[i].getAge() > maxAge) {
                    particles[i] = null;
                }
            }
        }
    }

    /**
     * Gives the x and y coordinates of the position where the mouse is pressed.
     *
     * @param x - x coordinate of the position where mouse is pressed
     * @param y - y coordinate of the position where mouse is pressed
     */
    public static void mousePressed(int x, int y) {
        // assigns position x and position y to global variables
        positionX = x;
        positionY = y;
    }

    /**
     * This methods saves a screenshot of the particle fountain on "screenshot.png"
     * when user inputs 'p'.
     *
     * @param save represents user input of char on keyboard
     */
    public static void keyPressed(char save) {
        // saves a picture of the particle fountain if user inputs 'p' to
        // "screenshot.png"
        if (save == 'p') {
            Utility.save("screenshot.png");
        }
    }

    /**
     * This method constantly calls methods updateParticle, createNewParticles, and
     * removeOldParticle as well as refresh the background color.
     */
    public static void update() {
        // constantly updates background color
        Utility.background(Utility.color(235, 213, 186));
        // goes through the entire particles array to update, create, or remove
        // particles
        for (int i = 0; i < particles.length; i++) {
            // if index space is not empty, the update particle in that index space
            if (particles[i] != null) {
                updateParticle(i);
            }
        }
        // creates new particles when index spaces are empty or when old particles are
        // removed
        createNewParticles(10);
        // removes old particles that have an age greater than 80
        removeOldParticles(80);
    }

    /**
     * Creates a single particle at position (3,3) with velocity (-1,-2). Then
     * checks whether calling updateParticle() on this particle's index correctly
     * results in changing its position to (2,1.3).
     *
     * @return true when no defect is found, and false otherwise
     */
    private static boolean testUpdateParticle() {
        // creates a particles array with a single particle
        particles = new Particle[1];
        particles[0] = new Particle();
        // sets the positions x and y of the particle
        particles[0].setPositionX(3f);
        particles[0].setPositionY(3f);
        // sets the velocities in the x direction and in the y direction of the particle
        particles[0].setVelocityX(-1f);
        particles[0].setVelocityY(-2f);
        // calls updateParticle at index 0
        updateParticle(0);
        // if the x and y position of the particle is correct return true, otherwise,
        // return false
        if (Float.compare(particles[0].getPositionX(), 2f) == 0
                && Float.compare(particles[0].getPositionY(), 1.3f) == 0) {
            return true;
        }
        return false;
    }

    /**
     * Calls removeOldParticles(6) on an array with three particles (two of which
     * have ages over six and another that does not). Then checks whether the old
     * particles were removed and the young particle was left alone.
     *
     * @return true when no defect is found, and false otherwise
     */
    private static boolean testRemoveOldParticles() {
        // creates the particles array with three particles in it
        particles = new Particle[3];
        particles[0] = new Particle();
        particles[1] = new Particle();
        particles[2] = new Particle();
        // sets the age for each of the three particles
        particles[0].setAge(7);
        particles[1].setAge(7);
        particles[2].setAge(4);
        // remove particles with ages greater than 6
        removeOldParticles(6);
        // if the 0th particle and the 1st particle were removed and the 2nd particle
        // was not removed return true, otherwise, return false
        if (particles[0] == null && particles[1] == null && particles[2] != null) {
            return true;
        }
        return false;
    }

    /**
     * Runs particle fountain using the Utility and Particle class.
     *
     * @param args
     */
    public static void main(String[] args) {
        Utility.runApplication();
    }
}

