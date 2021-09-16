public class MyLevels {
    public static final char EMPTY_CHAR = ' '; // Empty character
    public static final char BOX_CHAR = '='; // Box character
    public static final char WALL_CHAR   = '#'; // Wall character
    public static final char WORKER_CHAR  = '@'; // Worker character
    public static final char GOAL_CHAR  = '.'; // Goal character
    public static final char BOX_GOAL_CHAR  = '*'; // Box on a goal character
    public static final char WORK_GOAL_CHAR  = '+'; // Worker on a goal character

    public static final long SEED            = 1234; // The random seed

    /**
     * Initial configuration of the levels. Note that the location of the goals are defined in the
     * GOALS array which is a parallel array to LEVELS.
     */
    public static final char[][][] LEVELS = {
            {
                    //{'#', ' ', ' ', ' '},
                    //{' ', '=', ' ', ' '},
                    //{'@', '#', ' ', ' '}, //initBoard = (0,2)
                    {WALL_CHAR, EMPTY_CHAR, EMPTY_CHAR,EMPTY_CHAR},
                    {EMPTY_CHAR, BOX_CHAR, EMPTY_CHAR,EMPTY_CHAR},
                    {WORKER_CHAR, WALL_CHAR, EMPTY_CHAR,EMPTY_CHAR}
            },
            {
                    //{' ', ' ', '#', ' ', ' ', ' ', ' ', '@'},
                    //{' ', '=', '#', '#', ' ', '#', '#', '#'},
                    //{' ', ' ', '#', ' ', ' ', ' ', ' ', ' '},
                    //{' ', ' ', '#', ' ', '#', '#', '='},
                    //{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, // initBoard( 4,7)
                    //{'#'}
                    {EMPTY_CHAR, EMPTY_CHAR, WALL_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, WORKER_CHAR},
                    {EMPTY_CHAR, BOX_CHAR, WALL_CHAR, WALL_CHAR, EMPTY_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR},
                    {EMPTY_CHAR, EMPTY_CHAR, WALL_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR},
                    {EMPTY_CHAR, EMPTY_CHAR, WALL_CHAR, EMPTY_CHAR, WALL_CHAR, WALL_CHAR, BOX_CHAR},
                    {EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR},
                    {WALL_CHAR}

            },
            {
                    //{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    //{' ', '#', '#', ' ', '#', '#', '#', ' '},
                    //{' ', '#', ' ', ' ', ' ', '#', ' ', ' '},
                    //{' ', ' ', ' ', ' ', '#', '#', ' '},
                    //{' ', '=', ' ', ' ', '#', ' ', ' ', ' ', ' '},
                    //{'#', ' ', ' ', ' ', '#', '#', '#', '#', ' '},
                    //{' ', ' ', ' ', ' ', '#', '#', '#', ' ', ' '},
                    //{' ', ' ', '@', '=', '=', ' ', ' ', ' ', ' '}, //initBoard(7,2)
                    //{' ', ' ', ' ', ' ', '#', ' ', ' ', ' ', ' '}
                    {EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR},
                    {EMPTY_CHAR, WALL_CHAR, WALL_CHAR, EMPTY_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR ,EMPTY_CHAR},
                    {EMPTY_CHAR, WALL_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, WALL_CHAR, EMPTY_CHAR, EMPTY_CHAR},
                    {EMPTY_CHAR,EMPTY_CHAR,EMPTY_CHAR,EMPTY_CHAR,WALL_CHAR,WALL_CHAR,EMPTY_CHAR,},
                    {EMPTY_CHAR,BOX_CHAR,EMPTY_CHAR,EMPTY_CHAR,WALL_CHAR, EMPTY_CHAR,EMPTY_CHAR,EMPTY_CHAR,EMPTY_CHAR,},
                    {WALL_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, WALL_CHAR,WALL_CHAR,WALL_CHAR,WALL_CHAR, EMPTY_CHAR},
                    {EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, WALL_CHAR,WALL_CHAR,WALL_CHAR, EMPTY_CHAR ,EMPTY_CHAR},
                    {EMPTY_CHAR, EMPTY_CHAR, WORKER_CHAR, BOX_CHAR, BOX_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR ,EMPTY_CHAR},
                    {EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, WALL_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR},

            }
    };


    /**
     * Position of the goals for each level. Every pair of values represents the row and column of
     * a goal. This is a parallel array to LEVELS. BOX_CHAR,
     */
    public static final int[][] GOALS = {{2,3}, {2,7, 4,7}, {0,0, 0,1, 1,0}};//FIXME //

    /**
     * The characters for the different movement directions and for quiting a game.
     */
    public static final char UP_CHAR = '8';
    public static final char DOWN_CHAR = '2';
    public static final char LEFT_CHAR = '4';
    public static final char RIGHT_CHAR = '6';
    public static final char QUIT_CHAR = 'q';
}

