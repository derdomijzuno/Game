package games.ai.state;

import java.util.ArrayList;
import java.util.List;

import games.ai.AITransition;
import games.controller.EnemyController;
import games.core.Position;
import games.entity.Enemy;
import games.map.Pathfinder;
import games.states.GameState;

public class Wander extends AIState {

	private List<Position> path;
	private Position target;

	public Wander() {
		super();
		path = new ArrayList<>();
	}

	@Override
	protected AITransition initializeTransition() {
		return new AITransition("stand", ((currentCharacter) -> arrived(currentCharacter)));
	}

	@Override
	public void tick(Enemy currentCharacter) {
		if (target == null) {
			List<Position> path = Pathfinder.findPath(currentCharacter.getPos(), GameState.map.getRandomPosition(),
					GameState.map);
			if (!path.isEmpty()) {
				target = path.get(path.size() - 1);
				this.path.addAll(path);
			}
		}

		EnemyController controller = (EnemyController) currentCharacter.getController();

		if (arrived(currentCharacter)) {
			controller.stop();
		}

		if (!path.isEmpty() && currentCharacter.getPos().isInRangeOf(path.get(0))) {
			path.remove(0);
		}

		if (!path.isEmpty()) {
			controller.moveToTarget(path.get(0), currentCharacter.getPos());
		}

	}

	private boolean arrived(Enemy currentCharacter) {
		return target != null && currentCharacter.getPos().isInRangeOf(target);
	}
}
