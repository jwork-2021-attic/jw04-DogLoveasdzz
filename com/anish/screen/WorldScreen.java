package com.anish.screen;

import java.awt.Color;
import java.awt.event.KeyEvent;

import com.anish.calabashbros.Calabash;
import com.anish.calabashbros.World;
import com.anish.maze.MazeGenerator;
import com.anish.calabashbros.Wall;
import com.anish.calabashbros.Floor;
import com.anish.calabashbros.Thing;
import com.anish.maze.MazeSolver;

import asciiPanel.AsciiPanel;

public class WorldScreen implements Screen {

    private World world;
    private Thing[][] bros;
    private int[][] maze;
    Calabash calabash;
    MazeGenerator mazeGenerator;
    MazeSolver mazeSolver;
    String[] sortSteps;

    public WorldScreen() {
        world = new World();
        /*
        bros = new Calabash[7];

        bros[3] = new Calabash(new Color(204, 0, 0), 1, world);
        bros[5] = new Calabash(new Color(255, 165, 0), 2, world);
        bros[1] = new Calabash(new Color(252, 233, 79), 3, world);
        bros[0] = new Calabash(new Color(78, 154, 6), 4, world);
        bros[4] = new Calabash(new Color(50, 175, 255), 5, world);
        bros[6] = new Calabash(new Color(114, 159, 207), 6, world);
        bros[2] = new Calabash(new Color(173, 127, 168), 7, world);

        world.put(bros[0], 10, 10);
        world.put(bros[1], 12, 10);
        world.put(bros[2], 14, 10);
        world.put(bros[3], 16, 10);
        world.put(bros[4], 18, 10);
        world.put(bros[5], 20, 10);
        world.put(bros[6], 22, 10);

        BubbleSorter<Calabash> b = new BubbleSorter<>();
        b.load(bros);
        b.sort();
        */

        mazeGenerator = new MazeGenerator(40);
        mazeGenerator.generateMaze();
        maze = mazeGenerator.getMaze();
        bros = new Thing[maze.length][maze[0].length];
        for(int i = 0; i < bros.length; i++){
            for(int j = 0; j < bros[0].length; j++){
                if (maze[i][j] == 0){
                    bros[i][j] = new Wall(world);
                }
                else{
                    bros[i][j] = new Floor(world);
                }
                world.put(bros[i][j], i, j);
            }
        }

        calabash = new Calabash(new Color(255, 165, 0), 0, world);
        bros[0][0] = calabash;
        world.put(bros[0][0], 0, 0);

        mazeSolver = new MazeSolver(maze);

        sortSteps = this.parsePlan(mazeSolver.getSolution());
    }

    private String[] parsePlan(String plan) {
        return plan.split("\n");
    }

    private void execute(Thing[][] bros, String step) {
        String[] couple = step.split(",");
        int preI = calabash.getX();
        int preJ = calabash.getY();
        int nextI = Integer.parseInt(couple[0]);
        int nextJ = Integer.parseInt(couple[1]);
        calabash.moveTo(nextI, nextJ);
        bros[preI][preJ] = new Floor(world);
        world.put(bros[preI][preJ], preI, preJ);
    }

    @Override
    public void displayOutput(AsciiPanel terminal) {

        for (int x = 0; x < World.WIDTH; x++) {
            for (int y = 0; y < World.HEIGHT; y++) {

                terminal.write(world.get(x, y).getGlyph(), x, y, world.get(x, y).getColor());

            }
        }
    }

    int i = 0;

    @Override
    public Screen respondToUserInput(KeyEvent key) {

        if (i < this.sortSteps.length) {
            this.execute(bros, sortSteps[i]);
            i++;
        }

        return this;
    }

}
