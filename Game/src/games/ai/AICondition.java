package games.ai;

import games.entity.Enemy;

public interface AICondition {
	
	boolean isMet(Enemy currentCharacter);

}
