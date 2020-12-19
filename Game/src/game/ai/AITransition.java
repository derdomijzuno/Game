package game.ai;

import game.entity.Enemy;

public class AITransition {

	private String nextState;
	private AICondition condition;

	public AITransition(String nextState, AICondition condtion) {
		this.nextState = nextState;
		this.condition = condtion;
	}

	public boolean shouldTransition(Enemy currentCharacter) {
		return condition.isMet(currentCharacter);
	}

	public String getNextState() {
		return nextState;
	}

}
