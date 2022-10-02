package ANANAzZzZz.game.foundation;

import ANANAzZzZz.game.entities.*;
import ANANAzZzZz.game.entities.bricks.Brick;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.system.MemoryStack;

import java.nio.IntBuffer;
import java.util.ArrayList;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.stackPush;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Renderer {
    public void setup() {
        // This line is critical for LWJGL's interoperation with GLFW's
        // OpenGL context, or any context that is managed externally.
        // LWJGL detects the context that is current in the current thread,
        // creates the GLCapabilities instance and makes the OpenGL
        // bindings available for use.
        GL.createCapabilities();

        // Set the clear color
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
    }

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
        long windowId = glfwCreateWindow(768, 768, "Arkanoid", NULL, NULL);
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

    public void render(long windowId, GameState gameState) {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer

        if (!gameState.isGameOver) {
            renderBricks(gameState.bricks);
            renderPlayer(gameState.player);
            renderBall(gameState.ball);
        }

        glfwSwapBuffers(windowId); // swap the color buffers
    }

    private void renderBricks(ArrayList<Brick> bricks) {
        for (Brick brick : bricks) {
            Point coordinate = brick.getCoordinate();
            // -1 to visually separate bricks
            renderRectangle(coordinate.x, coordinate.y, brick.getWidth() - 1, brick.getHeight(), brick.getColor());
        }
    }

    private void renderPlayer(Player player) {
        Point coordinate = player.getCoordinate();
        renderRectangle(coordinate.x, coordinate.y, player.getWidth(), player.getHeight(), player.getColor());
    }

    private void renderBall(Ball ball) {
        Point coordinate = ball.getCoordinate();
        renderCircle(coordinate.x, coordinate.y, 3, Colors.white);
    }

    @SuppressWarnings("SameParameterValue")
    private void renderCircle(int x, int y, int radius, Color color) {
        float scaledX = scaleToPixel(x);
        float scaledY = scaleToPixel(y);
        float scaledRadius = scaleToPixel(radius);
        float diagonalScale = 0.75f;

        GL11.glColor3f(color.getFloatRed(), color.getFloatGreen(), color.getFloatBlue());

        glBegin(GL_TRIANGLE_FAN);
        glVertex2f(scaledX + scaledRadius, scaledY);
        glVertex2f(scaledX + diagonalScale * scaledRadius, scaledY + diagonalScale * scaledRadius);
        glVertex2f(scaledX, scaledY + scaledRadius);
        glVertex2f(scaledX - diagonalScale * scaledRadius, scaledY + diagonalScale * scaledRadius);
        glVertex2f(scaledX - scaledRadius, scaledY);
        glVertex2f(scaledX - diagonalScale * scaledRadius, scaledY - diagonalScale * scaledRadius);
        glVertex2f(scaledX, scaledY - scaledRadius);
        glVertex2f(scaledX + diagonalScale * scaledRadius, scaledY - diagonalScale * scaledRadius);
        glEnd();
    }

    @SuppressWarnings("SameParameterValue")
    private void renderRectangle(int x, int y, int width, int height, Color color) {
        float scaledX = scaleToPixel(x);
        float scaledY = scaleToPixel(y);
        float scaledHalfWidth = scaleToPixel(width) / 2;
        float scaledHalfHeight = scaleToPixel(height) / 2;

        GL11.glColor3f(color.getFloatRed(), color.getFloatGreen(), color.getFloatBlue());

        glBegin(GL_QUADS);
        glVertex2f(scaledX - scaledHalfWidth, scaledY - scaledHalfHeight);
        glVertex2f(scaledX + scaledHalfWidth, scaledY - scaledHalfHeight);
        glVertex2f(scaledX + scaledHalfWidth, scaledY + scaledHalfHeight);
        glVertex2f(scaledX - scaledHalfWidth, scaledY + scaledHalfHeight);
        glEnd();
    }

    private float scaleToPixel(int i) {
        return i / 200f;
    }
}
