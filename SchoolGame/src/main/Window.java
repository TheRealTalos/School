package main;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.system.MemoryStack.stackPush;
import static org.lwjgl.system.MemoryUtil.NULL;

import java.nio.IntBuffer;

import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.system.MemoryStack;

public class Window {

	private long window;

	private int width = 960;
	private int height = 540;

	public Window() {

	}

	public void init() {

		window = glfwCreateWindow(960, 540, "Pac-Trump", NULL, NULL);
		if (window == NULL)
			throw new RuntimeException("Failed to create Window");
		
		glfwWindowHint(GLFW_VISIBLE, GL_FALSE);
		glfwWindowHint(GLFW_RESIZABLE, GL_FALSE);

		try (MemoryStack stack = stackPush()) {
			IntBuffer pWidth = stack.mallocInt(1);
			IntBuffer pHeight = stack.mallocInt(1);

			glfwGetWindowSize(window, pWidth, pHeight);

			GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

			glfwSetWindowPos(window, (vidmode.width() - pWidth.get(0)) / 2, (vidmode.height() - pHeight.get(0)) / 2);
		}

		glfwMakeContextCurrent(window);
		glfwSwapInterval(1);
		glfwShowWindow(window);

	}

	public boolean shouldClose() {
		return glfwWindowShouldClose(window) != 0;
	}
	
	public void pollEvents(){
		glfwPollEvents();
	}
	
	public void swapBuffers(){
		glfwSwapBuffers(window);
	}
	
	public void setSize(int width, int height){
		width = width;
		height = height;
	}
	
	public int getWidth() { return width; }
	public int getHeight() { return height; }

}
