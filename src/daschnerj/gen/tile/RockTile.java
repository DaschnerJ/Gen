package daschnerj.gen.tile;

import daschnerj.gen.gfx.Assets;

public class RockTile extends Tile {

	public RockTile(final String id) {
		super(Assets.stone, id);
	}

	@Override
	public boolean isSolid() {
		return true;
	}

}
