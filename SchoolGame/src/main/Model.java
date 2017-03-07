package main;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;

import org.lwjgl.BufferUtils;

public class Model {
	private int draw_count;
	private int v_id, t_id, i_id;
	
	public Model(float[] vertices, float[] sprite_coords, int[] indices){
		draw_count = indices.length;
		
		v_id = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, v_id);
		glBufferData(GL_ARRAY_BUFFER, createFloatBuffer(vertices), GL_STATIC_DRAW);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		
		t_id = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, t_id);
		glBufferData(GL_ARRAY_BUFFER, createFloatBuffer(sprite_coords), GL_STATIC_DRAW);
		glBindBuffer(GL_ARRAY_BUFFER, 0);

		i_id = glGenBuffers();
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, i_id);
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, createIntBuffer(indices), GL_STATIC_DRAW);
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);

	}
	
	public void render(){
		glEnableClientState(GL_VERTEX_ARRAY);
		glEnableClientState(GL_TEXTURE_COORD_ARRAY);
			
			glBindBuffer(GL_ARRAY_BUFFER, v_id);
			glVertexPointer(2, GL_FLOAT, 0, 0);
			
			glBindBuffer(GL_ARRAY_BUFFER, t_id);
			glTexCoordPointer(2, GL_FLOAT, 0, 0);
			
			glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, i_id);
			glDrawElements(GL_TRIANGLES, draw_count, GL_UNSIGNED_INT, 0);
			
			glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
			glBindBuffer(GL_ARRAY_BUFFER, 0);
			
		glDisableClientState(GL_VERTEX_ARRAY);
		glDisableClientState(GL_TEXTURE_COORD_ARRAY);
	}
	
	private FloatBuffer createFloatBuffer(float[] data){
		FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		return buffer;
	}
	private IntBuffer createIntBuffer(int[] data){
		IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		return buffer;
	}
}
