package application.macrophage_test;

import application.CameraController;
import application.macrophage_test.BystanderCell;
import engine.ApplicationEntryPoint;
import engine.Constants;
import engine.Engine;
import engine.EngineLoop;

import java.util.Random;

/**
 * Macrophage samples the cells in which it comes in contact with. If the protein signature of the cell
 * is not on the Macrophage's whitelist, then the macrophage eats the cell.
 */
public class MacrophageTest implements ApplicationEntryPoint {
    @Override
    public void init() {
        // Add the camera first
        new CameraController().enableMouseInputComponent();
        // Get the world width/height which are separate from the screen width/height
        int worldWidth = Engine.getConsoleVariables().find(Constants.WORLD_WIDTH).getcvarAsInt();
        int worldHeight = Engine.getConsoleVariables().find(Constants.WORLD_HEIGHT).getcvarAsInt();
        // Put the B-cell in the center of the world
        new Macrophage(worldWidth / 2, worldHeight / 2).addToWorld();
        Random rng = new Random();
        // Create 100 cytokines
        for (int i = 0; i < 100; ++i) {
            new BystanderCell(rng.nextDouble() * worldWidth, rng.nextDouble() * worldHeight).addToWorld();
        }
    }

    @Override
    public void shutdown() {

    }

    public static void main(String ... args) {
        EngineLoop.start(new MacrophageTest(), args);
    }
}
