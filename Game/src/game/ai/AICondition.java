package game.ai;

import game.entity.Enemy;

public interface AICondition {
	
	boolean isMet(Enemy currentCharacter);

}
