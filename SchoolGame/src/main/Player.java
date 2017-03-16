package main;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_A;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_D;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_S;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_W;
import static org.lwjgl.glfw.GLFW.glfwGetKey;

import org.joml.Vector3f;

public class Player {
	private Model model;
	private Sprite sprite;
	private Transform transform;
	
	public Player(){
		float[] vertices = new float[]{
				-0.5f,	0.5f, 0,
				0.5f,	0.5f, 0,
				0.5f,	-0.5f, 0, 
				-0.5f,	-0.5f, 0,
			};
			
			float[] sprites = new float[]{
					0, 0,
					1, 0,
					1, 1,
					0, 1,
			};
			
			int[] indices = new int[]{
					0,1,2,
					2,3,0,
			};
			
			model = new Model(vertices, sprites, indices);
			sprite = new Sprite("pacMan");
			
			transform = new Transform();
			transform.setScale(new Vector3f(64, 64, 1));
	}
	
	public void update(float d, Window window, Camera camera, World world){
		float delta = 1/d;
		float speed = 10 * delta;
		if (glfwGetKey(window.getWindow(), GLFW_KEY_W) == 1) transform.pos.add(new Vector3f(0, speed, 0));
		if (glfwGetKey(window.getWindow(), GLFW_KEY_A) == 1) transform.pos.add(new Vector3f(-speed, 0, 0));
		if (glfwGetKey(window.getWindow(), GLFW_KEY_S) == 1) transform.pos.add(new Vector3f(0, -speed, 0));
		if (glfwGetKey(window.getWindow(), GLFW_KEY_D) == 1) transform.pos.add(new Vector3f(speed, 0, 0));
	}
	
	public void render(Shader shader, Camera camera){
		shader.bind();
		shader.setUniform("sampler", 0);
		shader.setUniform("projection", transform.getProjection(camera.getProjection()));
		sprite.bind(0);
		model.render();
	}

}
