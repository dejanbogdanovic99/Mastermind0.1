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

	private static final String SOUNDS_PASS = "sounds";
	private static final String REPEAT_SIGNS_PASS = "repeatSigns";
	private static final String AMOUNT_OF_COLUMNS = "amountOfColumns";
	private static final String AMOUNT_OF_SIGNS_PASS = "amountOfSigns";
	private static final String TIMER_VALUE_PASS = "timer";

	private DataBundle bundle;

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();

		//sklanjanje tastera
		config.useImmersiveMode = true;

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
					file.getBoolean(SOUNDS_PASS, true),
					file.getBoolean(REPEAT_SIGNS_PASS, true),
					file.getInt(AMOUNT_OF_COLUMNS, 4),
					file.getInt(AMOUNT_OF_SIGNS_PASS, 6),
					file.getInt(TIMER_VALUE_PASS, 0)
			);

		}catch(Exception e){
			bundle = new DataBundle(
					true,
					true,
					4,
					6,
					0
			);
		}
	}

	private void saveConfig(){

		try {

			SharedPreferences prefFile = getSharedPreferences(
					FILE_NAME,
					Context.MODE_PRIVATE
			);

			SharedPreferences.Editor editor = prefFile.edit();

			editor.putBoolean(SOUNDS_PASS, bundle.isSounds());
			editor.putBoolean(REPEAT_SIGNS_PASS, bundle.isRepeatSigns());
			editor.putInt(AMOUNT_OF_COLUMNS, bundle.getAmountOfColumns());
			editor.putInt(AMOUNT_OF_SIGNS_PASS, bundle.getAmountOfSigns());
			editor.putInt(TIMER_VALUE_PASS, bundle.getTimerValue());

			editor.apply();

		}catch(Exception e){}

	}

}
