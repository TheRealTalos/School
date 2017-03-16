package main;

import org.joml.Matrix4f;
import org.joml.Vector3f;

public class World {
	private final int view = 50;
	
	private byte[] tiles;
	
	private int worldWidth, worldHeight, worldScale = 16;
	
	private Matrix4f world;
	
	public World(int worldWidth, int worldHeight, int worldScale){
		this.worldWidth = worldWidth;
		this.worldHeight = worldHeight;
		this.worldScale = worldScale;
		
		tiles = new byte[worldWidth*worldHeight];
		
		world = new Matrix4f().setTranslation(new Vector3f(0));
		world.scale(worldScale);
	}
	
	public void render(TileRenderer tileRenderer, Shader shader, Camera camera, Window window){
		int posX = ((int)camera.getPosition().x + (window.getWidth()/2)) / (worldScale);
		int posY = ((int)camera.getPosition().y - (window.getHeight()/2)) / (worldScale);
		
		for (int i = 0; i < view; i++){
			for (int j = 0; j < view; j++){
				Tile t = getTile(i-posX, j+posY);
				if (t!=null){
					tileRenderer.renderTile(Tile.feelsBad, i-posX, -j-posY, shader, world, camera);
				}
			}
		}
		
	}
	
	public void correctCamera(Window window, Camera camera){
		Vector3f pos = camera.getPosition();
		
		int w = -worldWidth * worldScale;
		int h = worldHeight * worldScale;
		
		if(pos.x > -(window.getWidth()/2)+worldScale/2) 
			pos.x = -(window.getWidth()/2)+worldScale/2;
		if(pos.x < (w + (window.getWidth()/2) + worldScale/2))
			pos.x = (w + (window.getWidth()/2) + worldScale/2);
		
		if(pos.y < (window.getHeight()/2)-worldScale/2)
			pos.y = (window.getHeight()/2)-worldScale/2;
		if(pos.y > h-(window.getHeight()/2)-worldScale/2)
			pos.y = h-(window.getHeight()/2)-worldScale/2;
	}
	
	public void setTile(Tile tile, int x, int y){
		tiles[x + y * worldWidth] = tile.getId();
	}
	
	public Tile getTile(int x, int y){
		try {
			return Tile.tiles[tiles[x + y * worldWidth]];
		} catch (ArrayIndexOutOfBoundsException e) {
			return null;
		}
	}
	
}
