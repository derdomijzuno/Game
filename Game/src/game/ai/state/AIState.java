package game.ai.state;

import game.ai.AITransition;
import game.entity.Enemy;

public abstract class AIState {

	private AITransition transition;

	public AIState() {
		this.transition = initializeTransition();
	}

	protected abstract AITransition initializeTransition();

	public abstract void tick(Enemy currentCharacter);

	public boolean shouldTransition(Enemy currentCharacter) {
		return transition.shouldTransition(currentCharacter);
	}

	public String getNextState() {
		return transition.getNextState();
	}

}
