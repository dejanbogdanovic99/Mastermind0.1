package com.sts.mastermind;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.WindowManager;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.sts.mastermind.bundelPackage.DataBundle;

public class AndroidLauncher extends AndroidApplication {

	private static final String FILE_NAME = "mastermind_settings";

	private static final String VOLUME_PASS = "volume";
	private static final String REPEAT_COLORS_PASS = "repeatColors";
	private static final String AMOUNT_OF_ROWS_PASS = "amountOfRows";
	private static final String AMOUNT_OF_COLORS_PASS = "amountOfColors";

	private DataBundle bundle;

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		loadConfig();

		Main game = new Main(bundle);

		initialize(game, config);
	}

	@Override
	protected void onStop() {
		saveConfig();
		super.onStop();
	}

	private void loadConfig(){
		try{

			SharedPreferences file = getSharedPreferences(
					FILE_NAME,
					Context.MODE_PRIVATE
			);


			bundle = new DataBundle(
					file.getBoolean(VOLUME_PASS, false),
					file.getBoolean(REPEAT_COLORS_PASS, false),
					file.getInt(AMOUNT_OF_ROWS_PASS, 4),
					file.getInt(AMOUNT_OF_COLORS_PASS, 6)
			);

		}catch(Exception e){
			bundle = new DataBundle(
					false,
					false,
					4,
					6
			);
		}
	}

	private void saveConfig(){

		SharedPreferences prefFile = getSharedPreferences(
				FILE_NAME,
				Context.MODE_PRIVATE
		);

		SharedPreferences.Editor editor = prefFile.edit();

		editor.putBoolean(VOLUME_PASS, bundle.getVolume());
		editor.putBoolean(REPEAT_COLORS_PASS, bundle.getRepeatColors());
		editor.putInt(AMOUNT_OF_ROWS_PASS, bundle.getAmountOfRows());
		editor.putInt(AMOUNT_OF_COLORS_PASS, bundle.getAmountOfColors());

		editor.apply();

	}

}
