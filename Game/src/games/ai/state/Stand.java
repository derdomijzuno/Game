package games.ai.state;

import games.ai.AITransition;
import games.entity.Enemy;
import games.states.GameState;

public class Stand extends AIState {

	private int updatesAlive;

	@Override
	protected AITransition initializeTransition() {
		return new AITransition("wander", (currentCharacter) -> updatesAlive >= GameState.time.getUpdatesFromSeconds(3));
	}

	@Override
	public void tick(Enemy currentCharacter) {
		updatesAlive++;
	}

}
