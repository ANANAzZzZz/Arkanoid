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
            }

            if (key == GLFW_KEY_Z && action == GLFW_PRESS) {
                input.z = true;
            }
            if (key == GLFW_KEY_X && action == GLFW_PRESS) {
                input.x = true;
            }
            if (key == GLFW_KEY_C && action == GLFW_PRESS) {
                input.c = true;
            }
            if (key == GLFW_KEY_W && action == GLFW_PRESS) {
                input.w = true;
            }
            if (key == GLFW_KEY_S && action == GLFW_PRESS) {
                input.s = true;
            }

            if (key == GLFW_KEY_LEFT && action == GLFW_PRESS) {
                input.leftArrow = true;
            }
            if (key == GLFW_KEY_RIGHT && action == GLFW_PRESS) {
                input.rightArrow = true;
            }
            if (key == GLFW_KEY_LEFT && action == GLFW_RELEASE) {
                input.leftArrow = false;
            }
            if (key == GLFW_KEY_RIGHT && action == GLFW_RELEASE) {
                input.rightArrow = false;
            }
        });
    }

    public Input read() {
        input.reset();
        glfwPollEvents();

        return new Input(input);
    }
}