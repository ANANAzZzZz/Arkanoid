package ANANAzZzZz.game;

import ANANAzZzZz.game.entities.GameState;
import ANANAzZzZz.game.entities.Input;
import ANANAzZzZz.game.foundation.GameStateProcessor;
import ANANAzZzZz.game.foundation.InputReader;
import ANANAzZzZz.game.foundation.Renderer;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;

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

        // Setup a key callback. It will be called every time a key is pressed, repeated or released.
        //noinspection resource
        glfwSetKeyCallback(windowId, (window, key, scancode, action, mods) -> {
            if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE) {
                glfwSetWindowShouldClose(window, true); // We will detect this in the rendering loop
            }
        });

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
            inputReader.pollEvents();
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
