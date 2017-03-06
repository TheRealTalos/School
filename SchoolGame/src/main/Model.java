package main;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;

import org.lwjgl.BufferUtils;

public class Model {
	private int draw_count;
	private int v_id;
	
	public Model(float[] vertices){
		draw_count = vertices.length / 3;
		
		FloatBuffer buffer = BufferUtils.createFloatBuffer(vertices.length);
		buffer.put(vertices);
		buffer.flip();
		
		v_id = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, v_id);
		glBufferData(GL_ARRAY_BUFFER, buffer, GL_STATIC_DRAW);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
	}
	
	public void render(){
		glEnableClientState(GL_VERTEX_ARRAY);
			
			glBindBuffer(GL_VERTEX_ARRAY, v_id);
			glVertexPointer(3, GL_FLOAT, 0, 0);
			
			glDrawArrays(GL_TRIANGLES, 0, v_id);
			
			glBindBuffer(GL_VERTEX_ARRAY, v_id);
			
		glDisableClientState(GL_VERTEX_ARRAY);
	}
	
}
