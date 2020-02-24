package com.madpie.echo;

import android.util.Log;

public class MenuNavigation {
	public static int getModeIndex (int performedAction, int modeIndex) {
		int oldModeIndex = modeIndex;
		
		switch (modeIndex) {
		case 0:
			if (performedAction == 1) {
				modeIndex  = 10;
			} else if (performedAction == 3) {
				modeIndex = 50;
			} else if (performedAction == 4) {
				modeIndex = 1;
				// now, Biff, empty the array and reset the counter.
				EchoActivity.resetInputCounter();
				EchoActivity.emptyCharArray();
			}
			break;
		case 1:
			if (performedAction == 1) {
				modeIndex = 2;
				// now, Biff, I think I told you to empty the array and reset the counter...
				EchoActivity.resetInputCounter();
				EchoActivity.emptyCharArray();
			} else if (performedAction == 2) {
				modeIndex = 0;
			} else if (performedAction == 4) {
				modeIndex = 11;
			}
			break;
		case 2:
			if (performedAction == 1) {
				modeIndex = 3;
			} else if (performedAction == 3) {
				modeIndex = 1;
			} else if (performedAction == 4) {
				modeIndex = 21;
			}
			break;
		case 3:
			if (performedAction == 3) {
				modeIndex = 2;
			} else if (performedAction == 4) {
				modeIndex = 4;
			}
			break;
		case 50:
			if (performedAction == 1) {
				modeIndex = 0;
			} else if (performedAction == 2) {
				modeIndex = 60;
			} else if (performedAction == 4) {
				modeIndex = 70;
			}
			break;
		}
		
		if (oldModeIndex != modeIndex) {
			Log.d("debbie","modeIndex "+String.valueOf(modeIndex));
			EchoActivity.actionModeOff();
		}
		
		return modeIndex;
	}
}
