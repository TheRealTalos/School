package main;

import static org.lwjgl.opengl.GL11.GL_NEAREST;
import static org.lwjgl.opengl.GL11.GL_RGBA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MAG_FILTER;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MIN_FILTER;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_BYTE;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glGenTextures;
import static org.lwjgl.opengl.GL11.glTexImage2D;
import static org.lwjgl.opengl.GL11.glTexParameterf;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import org.lwjgl.BufferUtils;

public class SpriteSheet {
	public SpriteSheet(String file, int size, int num){
		BufferedImage image;
		
		try { 

			image = ImageIO.read(new File("./res/spriteSheets/" + file + ".png"));
			
			int imageSize = image.getWidth();
		
			for (int k = 0; k < num; k++){
			
				int x = (k % (imageSize/size)) * size;
				int y = (int)(Math.floor(k / (imageSize/size))) * size;
			
				int[] pixels_raw = new int[imageSize * imageSize];
				pixels_raw = image.getRGB(x, y, size, size, null, 0, imageSize);
				
				//Sprite fil = new Sprite(pixels_raw, size, num);
				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
