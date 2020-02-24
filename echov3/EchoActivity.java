package com.madpie.echov3;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.gesture.GestureOverlayView;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Vibrator;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.TextView;

public class EchoActivity extends Activity implements OnTouchListener, OnInitListener {
	
	GestureOverlayView gestureOverlayView1;
	TextView textView1;
	ImageView imageView1;
	
	boolean touched = false;
	// point (x0,y0) is gesture start and (x1,y1) is gesture end
	// currentX, currentY are updated whenever there's a finger on the screen
	// anchorX, anchorY are the values in each 'junction'
	int x0, x1, y0, y1, displayW, displayH, gestureLength, anchorX, anchorY, currentX, currentY = 0;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_echo);
		
		gestureOverlayView1 = (GestureOverlayView)findViewById(R.id.gestureOverlayView1);
		gestureOverlayView1.setOnTouchListener(this);
		
		textView1 = (TextView)findViewById(R.id.textView1);
		imageView1 = (ImageView)findViewById(R.id.imageView1);

		//calculate gesture length for this particular device
		Display display = getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		displayW = size.x;
		displayH = size.y;
		
		gestureLength = displayH;
		if (displayW < gestureLength) gestureLength = displayW;
		gestureLength = gestureLength / 10;
		Log.d("debbie", "displayW="+String.valueOf(displayW)+" displayH="+String.valueOf(displayH)+" gestureLength="+String.valueOf(gestureLength));

		vibrate(500);
		imageView1.setImageAlpha(0);
	}

	@Override
	public void onInit(int i) {
		// TODO Auto-generated method stub
	}
	
	public void vibrate(int ms) {
		Vibrator vibe = (Vibrator)this.getSystemService(Context.VIBRATOR_SERVICE);
		vibe.vibrate(ms);
	}

	@SuppressLint("ClickableViewAccessibility")
	@Override
	public boolean onTouch(View v, MotionEvent e) {
		switch(e.getAction()) {
		case MotionEvent.ACTION_DOWN:
			if (touched == false) {
				x0 = (int)e.getX();
				y0 = (int)e.getY();
				anchorX = x0;
				anchorY = y0;
				touched = true;
				//vibrate(100);
			}
			break;
		case MotionEvent.ACTION_UP:
			x1 = (int)e.getX();
			y1 = (int)e.getY();
			if (touched) {
				Log.d("debbie",String.valueOf(GestureProcessing.getGestureDirection(x0, y0, x1, y1)));
				touched = false;
				//vibrate(100);
			}
			break;
		}
		currentX = (int)e.getX();
		currentY = (int)e.getY();
		if (Math.abs(anchorX-currentX)>gestureLength || Math.abs(anchorY-currentY)>gestureLength) {
			//Log.d("debbie","x="+String.valueOf(currentX)+" y="+String.valueOf(currentY));
			Log.d("debbie",String.valueOf(GestureProcessing.getGestureDirection(anchorX, anchorY, currentX, currentY)));
			anchorX = currentX;
			anchorY = currentY;
			vibrate(50);
		}
		return false;
	}
}
