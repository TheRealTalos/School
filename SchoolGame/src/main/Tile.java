package main;

public class Tile {

	public static Tile tiles[] = new Tile[16];
	
	public static final Tile pacman = new Tile((byte) 0, "PacMan");
	
	private byte id;
	private String sprite;
	
	public Tile(byte id, String sprite){
		this.id = id;
		this.sprite = sprite;
		
		if (tiles[id] != null) throw new IllegalStateException("Tile at [" + id + "] is arleady taken");
		tiles[id] = this;
		
	}

	public byte getId() {
		return id;
	}

	public String getSprite() {
		return sprite;
	}
	
}
