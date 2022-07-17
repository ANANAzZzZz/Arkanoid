package ANANAzZzZz.game.foundation;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.system.MemoryStack;

import java.nio.IntBuffer;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.stackPush;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Renderer {
    private final int screenLength = 768;
    private final float multiplier = 0.05f;

    public long createWindow() {
        // Setup an error callback. The default implementation
        // will print the error message in System.err.
        GLFWErrorCallback.createPrint(System.err).set();

        // Initialize GLFW. Most GLFW functions will not work before doing this.
        if (!glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }

        // Configure GLFW
        glfwDefaultWindowHints(); // optional, the current window hints are already the default
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden after creation

        // Create the window
        long windowId = glfwCreateWindow(screenLength, screenLength, "Arkanoid", NULL, NULL);
        if (windowId == NULL) {
            throw new RuntimeException("Failed to create the GLFW window");
        }

        // Get the thread stack and push a new frame
        try (MemoryStack stack = stackPush()) {
            IntBuffer pWidth = stack.mallocInt(1); // int*
            IntBuffer pHeight = stack.mallocInt(1); // int*

            // Get the window size passed to glfwCreateWindow
            glfwGetWindowSize(windowId, pWidth, pHeight);

            // Get the resolution of the primary monitor
            GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

            // Center the window
            //noinspection ConstantConditions
            glfwSetWindowPos(
                    windowId,
                    (vidmode.width() - pWidth.get(0)) / 2,
                    (vidmode.height() - pHeight.get(0)) / 2
            );
        } // the stack frame is popped automatically

        // Make the OpenGL context current
        glfwMakeContextCurrent(windowId);
        // Enable v-sync
        glfwSwapInterval(1);

        return windowId;
    }

    public void showWindow(long windowId) {
        glfwShowWindow(windowId);
    }

    public void destroyWindow(long windowId) {
        glfwDestroyWindow(windowId);

        //noinspection ConstantConditions,resource
        glfwSetErrorCallback(null).free();
    }

    // TODO: 7/17/2022 add game state to signature
    public void render(long windowId) {
        renderAxis();

        renderRectangle(0, 0, 1, 1);
        renderRectangle(1, 1, 1, 1);
        renderRectangle(0, 4, 3, 1);

        glfwSwapBuffers(windowId); // swap the color buffers
    }

    private void renderAxis() {
        renderLine(0, screenLength / 2, 0, -screenLength / 2);
        renderLine(-screenLength / 2, 0, screenLength / 2, 0);
    }

    // TODO: 7/15/2022 Move to renderer class
    // TODO: 7/15/2022 Add colour to signature
    public void renderRectangle(int x, int y, int width, int height) {
        float scaledX = x * multiplier;
        float scaledY = y * multiplier;
        float scaledHalfWidth = width * multiplier / 2;
        float scaledHalfHeight = height * multiplier / 2;

        GL11.glColor3f(255, 255, 255);

        glBegin(GL_QUADS);
        glVertex2f(scaledX - scaledHalfWidth, scaledY - scaledHalfHeight);
        glVertex2f(scaledX + scaledHalfWidth, scaledY - scaledHalfHeight);
        glVertex2f(scaledX + scaledHalfWidth, scaledY + scaledHalfHeight);
        glVertex2f(scaledX - scaledHalfWidth, scaledY + scaledHalfHeight);
        glEnd();
    }

    public void renderLine(int x1, int y1, int x2, int y2) {
        float scaledX1 = x1 * multiplier;
        float scaledY1 = y1 * multiplier;
        float scaledX2 = x2 * multiplier;
        float scaledY2 = y2 * multiplier;

        GL11.glColor3f(255, 255, 0);

        glBegin(GL_LINES);
        glVertex2f(scaledX1, scaledY1);
        glVertex2f(scaledX2, scaledY2);
        glEnd();
    }
}
