package com.madpie.swrite;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.gesture.GestureOverlayView;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Vibrator;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class SWActivity extends Activity implements View.OnTouchListener, TextToSpeech.OnInitListener {

    GestureOverlayView gestureOverlayView1;
    TextView textView1;
    TextView textView2;
    ImageView imageView1;
    String textToAppend = "";

    // variables that will save values when device is rotated
    private static final String TAG = "SWriteActivity";

    public static final String KEY_TTA = "";

    boolean touched = false;
    // point (x0,y0) is gesture start and (x1,y1) is gesture end
    // currentX, currentY are updated whenever there's a finger on the screen
    // anchorX, anchorY are the values in each 'junction'
    int x0, x1, y0, y1, displayW, displayH, gestureLength, anchorX, anchorY, currentX, currentY = 0;

    public static boolean[][] charArray = {{false,false},{false,false},{false,false}};

    // native TTS
    private TextToSpeech SWriteTTS;
    private static final int MY_DATA_CHECK_CODE = 1234;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sw);

        // Fire off an intent to check if a TTS engine is installed
        Intent checkIntent = new Intent();
        checkIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        startActivityForResult(checkIntent, MY_DATA_CHECK_CODE);

        gestureOverlayView1 = (GestureOverlayView)findViewById(R.id.gestureOverlayView1);
        gestureOverlayView1.setOnTouchListener(this);

        textView1 = (TextView)findViewById(R.id.textView1);
        textView2 = (TextView)findViewById(R.id.textView2);
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
        Log.d("debbie", "displayW=" + String.valueOf(displayW) + " displayH=" + String.valueOf(displayH) + " gestureLength=" + String.valueOf(gestureLength));

        vibrate(500);
        textView1.setText(R.string.entry_message);
        textView2.setText("");
        //imageView1.getDrawable().setAlpha(0);
        //textView1.setAlpha(0);

        if (savedInstanceState != null) {
            textView2.setText(savedInstanceState.getString(KEY_TTA));
            Log.d("debbie","values loaded");
        }
    }

    public void onInit(int i) {
        //read a message when application starts
        //SWriteTTS.speak(getString(R.string.app_name), TextToSpeech.QUEUE_FLUSH, null);
    }

    // added TTS code
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == MY_DATA_CHECK_CODE)
        {
            if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS)
            {
                // success, create the TTS instance
                SWriteTTS = new TextToSpeech(this, this);
            } else
            {
                //missing data, install it
                Intent installIntent = new Intent();
                installIntent.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
                startActivity(installIntent);
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG,"onSaveInstanceState");
        savedInstanceState.putString(KEY_TTA, textView2.getText().toString());
        Log.d("debbie","values saved");
    }

    public void vibrate(int ms) {
        Vibrator vibe = (Vibrator)this.getSystemService(Context.VIBRATOR_SERVICE);
        vibe.vibrate(ms);
    }

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

                    textView1.setText(R.string.entry_message);
                    if (textToAppend == "^_^;" && textView2.getText().length() > 0) textView2.setText(textView2.getText().toString().substring(0,textView2.getText().toString().length()-1));
                    else if (textToAppend != " ﾟдﾟ" && textToAppend != "^_^;") textView2.setText(textView2.getText()+textToAppend);
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 2; j++) charArray[i][j] = false;
                    }
                    textToAppend = "";

                    if (GestureProcessing.getGestureDirection(x0, y0, x1, y1) == 0 && textView2.getText().toString() != "") {
                        SWriteTTS.speak(textView2.getText().toString(),TextToSpeech.QUEUE_FLUSH, null);
                        textView1.setText(" ﾟoﾟ");
                    }

                    //read sentence
                    //vibrate(100);
                }
                break;
        }
        currentX = (int)e.getX();
        currentY = (int)e.getY();
        if (Math.abs(anchorX-currentX)>gestureLength || Math.abs(anchorY-currentY)>gestureLength) {
            //Log.d("debbie","x="+String.valueOf(currentX)+" y="+String.valueOf(currentY));

            textView1.setAlpha(1);
            textView1.setText(String.valueOf(GestureProcessing.getGestureDirection(anchorX, anchorY, currentX, currentY)));
            imageView1.setImageResource(R.drawable.bg);

            switch(GestureProcessing.getGestureDirection(anchorX, anchorY, currentX, currentY)) {
                case 1:
                    charArray[0][0] = charArray[1][0];
                    charArray[0][1] = charArray[1][1];

                    charArray[1][0] = charArray[2][0];
                    charArray[1][1] = charArray[2][1];

                    charArray[2][0] = false;
                    charArray[2][1] = true;

                    break;
                case 2:
                    charArray[0][0] = charArray[1][0];
                    charArray[0][1] = charArray[1][1];

                    charArray[1][0] = charArray[2][0];
                    charArray[1][1] = charArray[2][1];

                    charArray[2][0] = true;
                    charArray[2][1] = true;

                    break;
                case 3:
                    charArray[0][0] = charArray[1][0];
                    charArray[0][1] = charArray[1][1];

                    charArray[1][0] = charArray[2][0];
                    charArray[1][1] = charArray[2][1];

                    charArray[2][0] = true;
                    charArray[2][1] = false;

                    break;
                case 4:
                    charArray[0][0] = charArray[1][0];
                    charArray[0][1] = charArray[1][1];

                    charArray[1][0] = charArray[2][0];
                    charArray[1][1] = charArray[2][1];

                    charArray[2][0] = false;
                    charArray[2][1] = false;

                    break;
            }

            Log.d("debbie", String.valueOf(GestureProcessing.getGestureDirection(anchorX, anchorY, currentX, currentY)));

            textToAppend = CharacterConversionEnglish.getCharacter(charArray);
            textView1.setText(textToAppend);

            anchorX = currentX;
            anchorY = currentY;
            vibrate(50);
        }
        return false;
    }
}
