package games.ai;

import games.ai.state.AIState;
import games.ai.state.Stand;
import games.ai.state.WalkToTarget;
import games.ai.state.Wander;
import games.core.Position;
import games.entity.Enemy;

public class AIManager {

	private AIState currentAIState;
	Position target;

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
			target = null;
			return;
		case "stand":
			currentAIState = new Stand();
			target = null;
			return;
		case "walkToTarget":
			currentAIState = new WalkToTarget(target);
			return;
		default:
			currentAIState = new Stand();
		}
	}

	public void setTarget(Position target) {
		this.target = target;
		transitionTo("walkToTarget");
	}
	
	public Position getTarget() {
		return target;
	}
}
