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
	
	private long window;
	
	private static final int WIDTH = 960;
	private static final int HEIGHT = 540;
	
	public Game(){
		
		init();
		loop();
		
		glfwTerminate();
		
	}
	
	private void init(){
		
		if (!glfwInit()) throw new IllegalStateException("Failed to Initialize");
		
		glfwWindowHint(GLFW_VISIBLE, GL_FALSE);
		glfwWindowHint(GLFW_RESIZABLE, GL_FALSE);
		
		window = glfwCreateWindow(960, 540, "Pac-Trump", NULL, NULL);
		if (window == NULL) throw new RuntimeException("Failed to create Window");
		
		glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
			
			if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE) glfwSetWindowShouldClose(window, true);
			
		});
		
		try (MemoryStack stack = stackPush()){
			IntBuffer pWidth = stack.mallocInt(1);
			IntBuffer pHeight = stack.mallocInt(1);

			glfwGetWindowSize(window, pWidth, pHeight);

			GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
			
			glfwSetWindowPos(window, (vidmode.width() - pWidth.get(0))/2, (vidmode.height() - pHeight.get(0))/2);
		}
		
		glfwMakeContextCurrent(window);
		glfwSwapInterval(1);
		glfwShowWindow(window);
		
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
		
		Camera camera = new Camera(WIDTH, HEIGHT);
		
		Model model = new Model(vertices, sprite, indices);
		Shader shader = new Shader("shader");
		
		Sprite pac = new Sprite("./res/sprites/PacMan.png");
		
		Matrix4f scale = new Matrix4f()
				.translate(new Vector3f(0, 0, 0))
				.scale(512);
		
		Matrix4f target = new Matrix4f();
		
		camera.setPosition(new Vector3f(0, 0, 0));
		
		while(!glfwWindowShouldClose(window)){
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			
			glfwPollEvents();
			
			target = scale;
			
			shader.bind();
			shader.setUniform("sampler", 0);
			shader.setUniform("projection", camera.getProjection().mul(target));
			pac.bind(0);
			model.render();
			
			glfwSwapBuffers(window);
		}
		
	}
	
	public static void main(String[] args){
		
		new Game();
		
	}
	
}
