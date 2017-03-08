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
	
	public Game(){
		
		init();
		loop();
		
		glfwTerminate();
		
	}
	
	private void init(){
		
		Window window = new Window();
		window.init();
		
		if (!glfwInit()) throw new IllegalStateException("Failed to Initialize");
	}
	
	private void loop(){
		GL.createCapabilities();
		
		glClearColor(0,0,0,0);
		
		float[] vertices = new float[]{
			-0.5f,	0.5f, 0,
			0.5f,	0.5f, 0,
			0.5f,	-0.5f, 0, 
			-0.5f,	-0.5f, 0,
		};
		
		float[] sprite = new float[]{
				0, 0,
				1, 0,
				1, 1,
				0, 1,
		};
		
		int[] indices = new int[]{
				0,1,2,
				2,3,0,
		};
		
		Camera camera = new Camera(window.getWidth(), window.getHeight());
		
		Model model = new Model(vertices, sprite, indices);
		Shader shader = new Shader("shader");
		
		Sprite pac = new Sprite("./res/sprites/PacMan.png");
		
		Matrix4f scale = new Matrix4f()
				.translate(new Vector3f(0, 0, 0))
				.scale(512);
		
		Matrix4f target = new Matrix4f();
		
		camera.setPosition(new Vector3f(0, 0, 0));
		
		while(!window.shouldClose()){
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			
			window.pollEvents();
			
			target = scale;
			
			shader.bind();
			shader.setUniform("sampler", 0);
			shader.setUniform("projection", camera.getProjection().mul(target));
			pac.bind(0);
			model.render();
			
			window.swapBuffers();
		}
		
	}
	
	public static void main(String[] args){
		new Game();
	}
	
}
