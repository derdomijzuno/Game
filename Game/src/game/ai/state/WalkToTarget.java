package game.ai.state;

import java.util.ArrayList;
import java.util.List;

import game.ai.AITransition;
import game.controller.EnemyController;
import game.core.Position;
import game.entity.Enemy;
import game.map.Pathfinder;
import game.states.GameState;

public class WalkToTarget extends AIState {

	private List<Position> path;
	private Position target;
	private Position givenTarget;

	public WalkToTarget(Position target) {
////		this.target = target;
		this.path = new ArrayList<Position>();
		this.givenTarget = target;
	}

	@Override
	protected AITransition initializeTransition() {
		return new AITransition("stand", ((currentCharacter) -> arrived(currentCharacter)));
	}

	@Override
	public void tick(Enemy currentCharacter) {
		if (target == null) {
			List<Position> path = Pathfinder.findPath(currentCharacter.getPos(), givenTarget, GameState.map);
			if (!path.isEmpty()) { 
				target = path.get(path.size() - 1);
				this.path.addAll(path);
			}
		}

		EnemyController controller = (EnemyController) currentCharacter.getController();

		if (arrived(currentCharacter)) {
			currentCharacter.setTarget(null);
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
