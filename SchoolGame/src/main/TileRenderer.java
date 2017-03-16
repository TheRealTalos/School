package main;

import java.util.HashMap;

import org.joml.Matrix4f;
import org.joml.Vector3f;

public class TileRenderer {
	private HashMap<String, Sprite> tile_sprites;
	private Model model;
	
	public TileRenderer(){
		tile_sprites = new HashMap<String, Sprite>();
		
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
		
		for (int i = 0; i < Tile.tiles.length; i++){
			if (Tile.tiles[i] != null){
				if (!tile_sprites.containsKey(Tile.tiles[i].getSprite()))
					tile_sprites.put(Tile.tiles[i].getSprite(), new Sprite(Tile.tiles[i].getSprite()));	
			}
		}
	}
	
	public void renderTile(Tile tile, int x, int y, Shader shader, Matrix4f world, Camera camera){
		shader.bind();
		if (tile_sprites.containsKey(tile.getSprite())) 
			tile_sprites.get(tile.getSprite()).bind(0);
	
		Matrix4f tile_pos = new Matrix4f().translate(new Vector3f(x, y, 0));
		Matrix4f target = new Matrix4f();
		
		camera.getProjection().mul(world, target);
		target.mul(tile_pos);
		
		shader.setUniform("sampler", 0);
		shader.setUniform("projection", target);
		
		model.render();
	}
	
}
