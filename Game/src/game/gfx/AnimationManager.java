package game.gfx;

import java.awt.image.BufferedImage;

import game.core.Direction;
import game.main.Game;

public class AnimationManager {

	private SpriteSet spriteSet;
	private BufferedImage currentAnimationSheet;
	private int currentFrameTime;
	private int updatesPerFrame;
	private int frameIndex;
	private int directionIndex;

	public AnimationManager(SpriteSet spriteSet) {
		this.spriteSet = spriteSet;
		this.updatesPerFrame = 20;
		this.frameIndex = 0;
		this.currentFrameTime = 0;
		this.directionIndex = 0;
		playAnimation("walk");
	}

	public BufferedImage getSprite() {
		return currentAnimationSheet.getSubimage(frameIndex * Game.tileSize, directionIndex * Game.tileSize, Game.tileSize, Game.tileSize);
	}
	
	public void update(Direction direction) {
		currentFrameTime++;
		directionIndex = direction.getAnimationRow();
		
		if(currentFrameTime >= updatesPerFrame) {
			currentFrameTime = 0;
			frameIndex++;
			
			if(frameIndex >= currentAnimationSheet.getWidth() / Game.tileSize) {
				frameIndex = 0;
			}
		}
	}
	
	public void playAnimation(String name) {
		this.currentAnimationSheet = spriteSet.get(name);
	}

}
