package com.anik.flappybirds.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.anik.flappybirds.FlappyBirds;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = FlappyBirds.width;
		config.height = FlappyBirds.height;
		config.title = FlappyBirds.title;
		new LwjglApplication(new FlappyBirds(), config);
	}
}
