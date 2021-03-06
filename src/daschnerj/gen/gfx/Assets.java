package daschnerj.gen.gfx;

import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;

import daschnerj.gen.data.Data;
import daschnerj.gen.data.DataObjects;
import daschnerj.gen.data.sounds.GameAudio;
import daschnerj.gen.data.sounds.GameAudio.LocationSound;
import daschnerj.gen.data.sounds.SoundVector;
import daschnerj.gen.utils.Utils;

public class Assets {

	private static final int width = 32, height = 32;

	public static Data data;
	public static Font font28;

	public static BufferedImage dirt, grass, stone, tree, rock;
	public static BufferedImage wood;
	public static BufferedImage[] player_down, player_up, player_left, player_right;
	public static BufferedImage[] zombie_down, zombie_up, zombie_left, zombie_right;
	public static BufferedImage[] btn_start;
	public static BufferedImage inventoryScreen;

	public static void init() {
		data = new Data();
		final File file = new File(Utils.getDirectory() + "\\Gen\\Audio\\windy.wav");
		System.out.println("Exists: " + file.exists());
		final GameAudio audio = new GameAudio(file);
		final LocationSound sound = audio.playLocationSound(1f, 0f, true, new SoundVector(0, 0.5f),
				new SoundVector(0, 0), 1);
		new Thread() {
			float x;
			boolean isRightward = true;

			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(2);
					} catch (final InterruptedException e) {
						e.printStackTrace();
					}
					if (isRightward) {
						if (x >= 1.0f) {
							isRightward = false;
							x += 0.0001f;
						} else {
							x += 0.0001f;
						}
					} else {
						if (x <= -1.0f) {
							isRightward = true;
							x -= 0.0001f;
						} else {
							x -= 0.0001f;
						}
					}
					sound.setLocation(new SoundVector(x, 0.5f));
					sound.adjustSoundToListener(new SoundVector(0, 0));
				}
			}
		}.start();

		font28 = DataObjects.fonts.get("slkscr").loadFont(28);
		final SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("Gen\\Textures", "sheet.png"));

		inventoryScreen = ImageLoader.loadImage("Gen\\Textures", "inventoryScreen.png");

		wood = sheet.crop(width, height, width, height);

		btn_start = new BufferedImage[2];
		btn_start[0] = DataObjects.textures.get("button_create-new-world").getImage();
		btn_start[1] = DataObjects.textures.get("button_create-new-world").getImage();

		player_down = new BufferedImage[2];
		player_up = new BufferedImage[2];
		player_left = new BufferedImage[2];
		player_right = new BufferedImage[2];

		player_down[0] = sheet.crop(width * 4, 0, width, height);
		player_down[1] = sheet.crop(width * 5, 0, width, height);
		player_up[0] = sheet.crop(width * 6, 0, width, height);
		player_up[1] = sheet.crop(width * 7, 0, width, height);
		player_right[0] = sheet.crop(width * 4, height, width, height);
		player_right[1] = sheet.crop(width * 5, height, width, height);
		player_left[0] = sheet.crop(width * 6, height, width, height);
		player_left[1] = sheet.crop(width * 7, height, width, height);

		zombie_down = new BufferedImage[2];
		zombie_up = new BufferedImage[2];
		zombie_left = new BufferedImage[2];
		zombie_right = new BufferedImage[2];

		zombie_down[0] = sheet.crop(width * 4, height * 2, width, height);
		zombie_down[1] = sheet.crop(width * 5, height * 2, width, height);
		zombie_up[0] = sheet.crop(width * 6, height * 2, width, height);
		zombie_up[1] = sheet.crop(width * 7, height * 2, width, height);
		zombie_right[0] = sheet.crop(width * 4, height * 3, width, height);
		zombie_right[1] = sheet.crop(width * 5, height * 3, width, height);
		zombie_left[0] = sheet.crop(width * 6, height * 3, width, height);
		zombie_left[1] = sheet.crop(width * 7, height * 3, width, height);

		dirt = sheet.crop(width, 0, width, height);
		grass = sheet.crop(width * 2, 0, width, height);
		stone = sheet.crop(width * 3, 0, width, height);
		tree = sheet.crop(0, 0, width, height * 2);
		rock = sheet.crop(0, height * 2, width, height);

	}

}
