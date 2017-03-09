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
	TileRenderer tiles;
	Shader shader;
	Sprite pac;
	
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
		
		camera = new Camera(window.getWidth(), window.getHeight());
		tiles = new TileRenderer();
		shader = new Shader("shader");
		pac = new Sprite("./res/sprites/PacMan.png");
		
		camera.setPosition(new Vector3f(0, 0, 0));
		
	}
	
	private void loop(){
		GL.createCapabilities();
		
		glClearColor(0,0,0,0);
		
		Matrix4f scale = new Matrix4f()
				.translate(new Vector3f(0, 0, 0))
				.scale(512);
		
		while(!window.shouldClose()){
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			
			window.pollEvents();
			
			for (int i = 0; i < 8; i++){
				tiles.renderTile(0, i, 0, shader, scale, camera);
			}
			
//			shader.bind();
//			shader.setUniform("sampler", 0);
//			shader.setUniform("projection", camera.getProjection().mul(scale));
//			pac.bind(0);
//			model.render();
			
			window.swapBuffers();
		}
	}
	
	public static void main(String[] args){
		new Game();
	}
	
}