package main;

public class Tile {

	public static Tile tiles[] = new Tile[16];
	public static byte numTiles = 0;
	
	public static final Tile pacman = new Tile(Sprite.pacMan);
	public static final Tile feelsBad = new Tile(Sprite.feelsBad);
	
	private byte id;
	private Sprite sprite;
	
	public Tile(Sprite sprite){
		id = numTiles;
		numTiles++;
		this.sprite = sprite;
		
		if (tiles[id] != null) throw new IllegalStateException("Tile at [" + id + "] is arleady taken");
		tiles[id] = this;
		
	}

	public byte getId() {
		return id;
	}

	public String getSprite() {
		return sprite.getFile();
	}
	
}
