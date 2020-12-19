package game.ai.state;

import game.ai.AITransition;
import game.entity.Enemy;
import game.states.GameState;

public class Stand extends AIState {

	private int updatesAlive;

	@Override
	protected AITransition initializeTransition() {
		return new AITransition("wander",
				(currentCharacter) -> updatesAlive >= GameState.time.getUpdatesFromSeconds(3));
	}

	@Override
	public void tick(Enemy currentCharacter) {
		updatesAlive++;
	}

}
