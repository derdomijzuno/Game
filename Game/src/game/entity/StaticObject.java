package game.entity;

import game.core.Position;
import game.core.Size;

public abstract class StaticObject extends GameObject{

	public StaticObject(Position pos, Size size, ID id) {
		super(pos, size, id);
	}

}
