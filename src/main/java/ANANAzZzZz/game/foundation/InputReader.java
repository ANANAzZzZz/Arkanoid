package ANANAzZzZz.game.foundation;

import ANANAzZzZz.game.entities.Input;

import static org.lwjgl.glfw.GLFW.*;

public class InputReader {
    private final Input input;

    public InputReader() {
        input = new Input();
    }

    public void initCallbacks(long windowId) {
        // Setup a key callback. It will be called every time a key is pressed, repeated or released.
        //noinspection resource
        glfwSetKeyCallback(windowId, (window, key, scancode, action, mods) -> {
            if (key == GLFW_KEY_ESCAPE && action == GLFW_PRESS) {
                glfwSetWindowShouldClose(window, true);
            } else if (key == GLFW_KEY_SPACE && action == GLFW_PRESS) {
                input.space = true;
            }
        });
    }

    public Input read() {
        input.reset();
        glfwPollEvents();

        return new Input(input);
    }
}