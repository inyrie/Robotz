package edu.hm.fuberg.se2.android.robotz;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import edu.hm.fuberg.se2.android.robotz.control.RobotzControl;
import edu.hm.fuberg.se2.android.robotz.data.Arena;
import edu.hm.fuberg.se2.android.robotz.view.RobotzView;

public class RobotzActivity extends Activity {

	@Override protected void onCreate(final Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

		final char[][] arena = new char [][]{
				{'P','.','.','.','.','.','.','.','.','R'},
				{'R','.','.','.','R','.','.','.','R','.'},
				{'R','R','.','.','.','.','.','.','.','Z'}};

		final Arena robotzData = new Arena(arena);
		final RobotzControl robotzControl = new RobotzControl(robotzData);
		final RobotzView robotzView = new RobotzView(this, robotzControl, robotzData);

		setContentView(robotzView);
	}
}
