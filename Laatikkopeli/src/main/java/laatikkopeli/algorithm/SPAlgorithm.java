package laatikkopeli.algorithm;

//This is my implementation of 'laatikko'-algorithm familiar from tira2-course

import java.util.ArrayDeque;

/**
 * Algorithm finding shortest path for player in given situation (state).
 * @author lasse
 */
public class SPAlgorithm {
    
    public int[] p;
    public int[] b;
    public int[] g;
    public int h; 
    public int w;
    public char[][] r;
    int[][][][] usedStates;
    int[][][][] distances;
    int[][] moves = {{-1,0},{+1,0},{0,-1},{0,+1}};
    
    //New SPA ==================================================================
    /**
     * Takes char[][] as representation of a current state of a game
     * @param r
     * @param width
     * @param height 
     */
    public SPAlgorithm(char[][] r, int width, int height) {                     //Instantiate algorthm for game that is being played
        h = height; w = width;                                                  //Set area limits
        this.r = r;                                                             //Remember obstacles (depicted in char[][])
        findActors();
    }
    
    //Move player/box and count ================================================
    public int count(char[][] r, int gI, int gJ) {                              //Move player and calculate shortest path (allowance have been checked ex-SPA)
        this.r = r;
        findActors();
        if (b[0] == gI && b[1] == gJ) return 0;                                 //Box is in the goal
        //======== START CALCULATING =======================
        usedStates = new int[h][w][h][w];                                       //All states that have been checked in form: [Xy][Xx][By][Bx]
        distances = new int[h][w][h][w];                                        //Mark down how many steps it takes to get into each state
        usedStates [p[0]][p[1]][b[0]][b[1]] = 1;                                //Mark all states that have already been previewed
        ArrayDeque<int[]> queue = new ArrayDeque<>();                           //Use this queue to go through all possible states (until goal have been found)
        int[] state = {p[0],p[1],b[0],b[1]};                                    //This is the current state (current lccations of player and box)
        queue.add(state);                                                       //Add first state into queue
        while (!queue.isEmpty()) {
            int[] now = queue.pollFirst();                                      //Poll (pop) first state in queue
            for (int m = 0; m < 4; m++) {                                       //Have to four directions from this state
                int dist = distances[now[0]][now[1]][now[2]][now[3]];           //Get moves that are needed to get into previewed state
                int newY = now[0]+moves[m][0];                                  //Coordinates where player is trying to move (from state 'now')
                int newX = now[1]+moves[m][1];                                  
                if (newY < 0 || newY >= h || newX < 0 || newX >= w) continue;   //If move is directed outside gamearea, just skip current try (discard new possible state)
                if (usedStates [newY][newX][now[2]][now[3]] != 0) continue;     //If this new state is already been checked, skip
                char c = r[newY][newX];                                         //Read the char in new location (player)
                if (c == '#') continue;                                         //If that char stands for wall, just skip the try
                
                if (newY == now[2] && newX == now[3]) {                         //If it is box, check next if box is allowed to move
                    int newBY = newY+moves[m][0];                               //new coordinates for box
                    int newBX = newX+moves[m][1];
                    if (newBY < 0 || newBX < 0
                            || newBY >= h || newBX >= w) continue;              //Skip states that would push box out of the gamearea
                    char cc = r[newBY][newBX];                                  //Check the char in new postion (box)
                    if (cc == '#') continue;                                    //If that char is wall, skip the try
                    if (newBY == gI && newBX == gJ) return dist+1;              //If that char marks as the goal of player 1, return the distance to that state
                    int[] newState = {newY, newX, newBY, newBX};                //Create new 4-tuple presenting the new state (of player AND box)
                    distances [newY][newX][newBY][newBX] = dist+1;              //Distance to that new state is one more than into current state
                    usedStates [newY][newX][newBY][newBX] = 1;                  //Mark this new state as checked
                    queue.add(newState);                                        //Add new state into queue
 
                } else {
                    int[] newState = {newY, newX, now[2], now[3]};              //Create new state where only player has moved
                    queue.add(newState);                                        //Add this new state into queue
                    distances [newY][newX][now[2]][now[3]] = dist+1;            //Mark the distance to that new state
                    usedStates [newY][newX][now[2]][now[3]] = 1;                //...and mark it as checked
                }  
            }
        }
        return -1;                                                              //If algorithm couldn't find the goal, return -1 to signal current state as unsolvable
    }
    
    private void findActors() {
        for (int i = 0; i < h; i++) {                                           //Search player and box
            for (int j = 0; j < w; j++) {
                char c = r[i][j];
                if (c == '1') {
                    p = new int[]{i,j};                                         //Location of player 1
                }                               
                if (c == '3') {
                    b = new int[]{i,j};                                         //Location of player 1's box
                }                              
            }
        }
    }
}
