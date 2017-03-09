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
	
	private boolean fullScreen;

	public Window() {

	}

	public void init() {
		glfwWindowHint(GLFW_VISIBLE, GL_FALSE);
		glfwWindowHint(GLFW_RESIZABLE, GL_FALSE);
		
		window = glfwCreateWindow(width, height, "Pac-Trump", fullScreen ? glfwGetPrimaryMonitor() : 0, 0);
		if (window == NULL) throw new RuntimeException("Failed to create Window");

		glfwSetWindowPos(window, (getMonitorWidth() - width) / 2, (getMonitorHeight() - height) / 2);
		
		glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
			if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE )
				glfwSetWindowShouldClose(window, true); 
		});


		glfwMakeContextCurrent(window);
		glfwSwapInterval(1);
		glfwShowWindow(window);

	}
	
	public int getMonitorWidth(){
		GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
		return vidmode.width();
	}
	
	public int getMonitorHeight(){
		GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
		return vidmode.height();
	}
	
	public void setFullScreen(boolean full){
		fullScreen = full;
		if (full) setSize(getMonitorWidth(), getMonitorHeight());
	}
	
	public boolean getFullScreen(){
		return fullScreen;
	}

	public boolean shouldClose() {
		return glfwWindowShouldClose(window);
	}
	
	public void pollEvents(){
		glfwPollEvents();
	}
	
	public void swapBuffers(){
		glfwSwapBuffers(window);
	}
	
	public void setSize(int width, int height){
		this.width = width;
		this.height = height;
	}
	
	public int getWidth() { return width; }
	public int getHeight() { return height; }

}
