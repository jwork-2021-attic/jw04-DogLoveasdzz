package com.anish.maze;

public class MazeSolver {
    private int[][] maze;
    private int[][] DFSGraph;
    private String plan;
    
    public MazeSolver(int [][] initMaze){
        maze = initMaze;
        DFSGraph = new int[maze.length][maze[0].length];
        for(int i = 0; i < maze.length; i++){
            for (int j = 0; j < maze[0].length; j++){
                if(maze[i][j] == 0){
                    DFSGraph[i][j] = -1;
                }
                else{
                    DFSGraph[i][j] = maze.length * maze[0].length;
                }
            }
        }
        plan = "";
    }

    private void traverseDFS(int i, int j){
        if (i > 0 && DFSGraph[i - 1][j] != -1 && DFSGraph[i-1][j] > DFSGraph[i][j] + 1){
            DFSGraph[i-1][j] = DFSGraph[i][j] + 1;
            traverseDFS(i - 1, j);
        }
        if (i < maze.length - 1 && DFSGraph[i + 1][j] != -1 && DFSGraph[i+1][j] > DFSGraph[i][j] + 1){
            DFSGraph[i+1][j] = DFSGraph[i][j] + 1;
            traverseDFS(i + 1, j);
        }
        if (j > 0 && DFSGraph[i][j-1] != -1 && DFSGraph[i][j-1] > DFSGraph[i][j] + 1){
            DFSGraph[i][j-1] = DFSGraph[i][j] + 1;
            traverseDFS(i, j-1);
        }
        if (j < maze[0].length - 1 && DFSGraph[i][j+1] != -1 && DFSGraph[i][j+1] > DFSGraph[i][j] + 1){
            DFSGraph[i][j+1] = DFSGraph[i][j] + 1;
            traverseDFS(i, j+1);
        }
    }

    private void getPathDFS(int i, int j){
        if (i > 0 && DFSGraph[i - 1][j] != -1 && DFSGraph[i-1][j] == DFSGraph[i][j] - 1){
            plan = Integer.toString(i) + "," + Integer.toString(j) + "\n" + plan;
            getPathDFS(i - 1, j);
            return;
        }
        if (i < maze.length - 1 && DFSGraph[i + 1][j] != -1 && DFSGraph[i+1][j] == DFSGraph[i][j] - 1){
            plan = Integer.toString(i) + "," + Integer.toString(j) + "\n" + plan;
            getPathDFS(i + 1, j);
            return;
        }
        if (j > 0 && DFSGraph[i][j-1] != -1 && DFSGraph[i][j-1] == DFSGraph[i][j] - 1){
            plan = Integer.toString(i) + "," + Integer.toString(j) + "\n" + plan;
            getPathDFS(i, j-1);
            return;
        }
        if (j < maze[0].length - 1 && DFSGraph[i][j+1] != -1 && DFSGraph[i][j+1] == DFSGraph[i][j] - 1){
            plan = Integer.toString(i) + "," + Integer.toString(j) + "\n" + plan;
            getPathDFS(i, j+1);
            return;
        }
    }

    public String getSolution(){
        DFSGraph[0][0] = 0;
        traverseDFS(0, 0);
        getPathDFS(maze.length - 1, maze[0].length - 1);
        return plan;
    }
}
