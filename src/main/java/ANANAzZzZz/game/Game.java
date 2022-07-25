package ANANAzZzZz.game;

import ANANAzZzZz.game.entities.GameState;
import ANANAzZzZz.game.entities.Input;
import ANANAzZzZz.game.foundation.GameStateProcessor;
import ANANAzZzZz.game.foundation.InputReader;
import ANANAzZzZz.game.foundation.Renderer;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.glfwTerminate;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;

public class Game {
    private final InputReader inputReader;
    private final GameStateProcessor gameStateProcessor;
    private final Renderer renderer;

    private GameState gameState;
    private long windowId;

    public Game() {
        inputReader = new InputReader();
        gameStateProcessor = new GameStateProcessor();
        renderer = new Renderer();
    }

    public void run() {
        init();
        loop();
        terminate();
    }

    private void init() {
        gameState = gameStateProcessor.init();
        windowId = renderer.createWindow();
        inputReader.initCallbacks(windowId);
        renderer.showWindow(windowId);
    }

    private void loop() {
        renderer.setup();

        // Run the rendering loop until the user has attempted to close
        // the window or has pressed the ESCAPE key.
        while (!glfwWindowShouldClose(windowId)) {
            Input input = inputReader.read();
            gameState = gameStateProcessor.update(input, gameState);
            renderer.render(windowId, gameState);
        }
    }

    private void terminate() {
        // Free the window callbacks and destroy the window
        glfwFreeCallbacks(windowId);

        renderer.destroyWindow(windowId);

        // Terminate GLFW and free the error callback
        glfwTerminate();
    }
}
