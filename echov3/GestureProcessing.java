package com.madpie.echov3;

import android.util.Log;

@SuppressWarnings("unused")
public class GestureProcessing {
	public static int getGestureDirection(int x0, int y0, int x1, int y1) {
		
		int tapThreshold = 20;
		int res = 1000;
		
		int xDif = Math.abs(x1-x0);
		int yDif = Math.abs(y1-y0);
		int tDif = 0;
		
		if (xDif > tDif) tDif = xDif;
		if (yDif > tDif) tDif = yDif;
		
		if (tDif < tapThreshold) res = 0; //Log.d("debbie","x1="+String.valueOf(x1)+" x0="+String.valueOf(x0)+" y1="+String.valueOf(y1)+" y0="+String.valueOf(y0));
		else {
			if (Math.abs(x1-x0)>Math.abs(y1-y0)) {
				if (x1-x0 > 0) res = 1;
				else res = 3;
			} else {
				if (y1-y0 > 0) res = 4;
				else res = 2;
			}
		}
		return res;
	}
}
