package main;

public class Animation {
	private Sprite[] frames;
	private int pointer;
	
	double elapsedTime, currentTime, lastTime;
	
	public Animation(int amount, String file){
		this.pointer = 0;
		this.elapsedTime = 0;
		this.currentTime = 0;
		this.lastTime = (double)System.nanoTime() / (double)1000000000L;
		
		this.frames = new Sprite[amount];
		for (int i = 0; i < amount; i++){
			this.frames[i] = new Sprite(file + "_" + i);
		}
	}
}
