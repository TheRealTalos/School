package main;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import java.io.FileReader;
import java.nio.*;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

public class Game {
	
	Window window;
	Camera camera;
	World world;
	TileRenderer tileRenderer;
	Shader shader;
	Player player;
	
	public Game(){
		
		init();
		loop();
		
		glfwTerminate();
		
	}
	
	private void init(){
		GLFWErrorCallback.createPrint(System.err).set();
		
		if (!glfwInit()) throw new IllegalStateException("Failed to Initialize");
		
		window = new Window();
		
		window.setSize(960, 540);
		window.setFullScreen(false);
		window.init();
	}
	
	private void loop(){
		GL.createCapabilities();
		
		glClearColor(0,0,0,0);
		
		world = new World(128, 128, 64);
		
		tileRenderer = new TileRenderer();
		shader = new Shader("shader");
		player = new Player();
		camera = new Camera(window.getWidth(), window.getHeight());
		camera.setPosition(new Vector3f(0, 0, 0));
		
		glfwSetKeyCallback(window.getWindow(), (window, key, scancode, action, mods) -> {
			if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE ) glfwSetWindowShouldClose(window, true);
			
		});
		
		while(!window.shouldClose()){
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			
			player.update(144, window, camera, world);
			
			window.pollEvents();
			
			world.correctCamera(window, camera);
			
			world.render(tileRenderer, shader, camera, window);
			
			player.render(shader, camera);
			
			world.setTile(Tile.feelsBad, 0, 0);
			world.setTile(Tile.feelsBad, 63, 63);
			
			window.swapBuffers();
		}
	}
	
	public static void main(String[] args){
		new Game();
	}
	
}