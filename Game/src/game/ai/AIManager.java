package game.ai;

import game.ai.state.AIState;
import game.ai.state.Stand;
import game.ai.state.Wander;
import game.entity.Enemy;

public class AIManager {

	private AIState currentAIState;

	public AIManager() {
		transitionTo("stand");
	}

	public void tick(Enemy currentCharacter) {
		currentAIState.tick(currentCharacter);

		if (currentAIState.shouldTransition(currentCharacter)) {
			transitionTo(currentAIState.getNextState());
		}
	}

	private void transitionTo(String nextState) {
		System.out.println("Transitioning to " + nextState);
		switch (nextState) {
		case "wander":
			currentAIState = new Wander();
			return;
		case "stand":
		default:
			currentAIState = new Stand();
		}
	}
}
