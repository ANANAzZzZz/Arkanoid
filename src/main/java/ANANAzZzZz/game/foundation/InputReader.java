package ANANAzZzZz.game.foundation;

import ANANAzZzZz.game.entities.Input;

import static org.lwjgl.glfw.GLFW.glfwPollEvents;

public class InputReader {
    // TODO: 7/20/2022 implement
    public Input read() {
        return new Input();
    }

    public void pollEvents() {
        // Poll for window events. The key callback above will only be
        // invoked during this call.
        glfwPollEvents();
    }
}