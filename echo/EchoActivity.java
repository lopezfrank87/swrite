package com.madpie.echo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.AssetManager;
import android.gesture.GestureOverlayView;
import android.os.AsyncTask;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EchoActivity extends Activity implements OnTouchListener, OnInitListener {
    /** Called when the activity is first created. */
	
	private static final String username = "francho6807@gmail.com";
    private static final String password = "DUilS6H78BF61HC";
	
	GestureOverlayView mGestureOverlayView1;
	TextView mTextView1;
	int xDown = 0;
	int yDown = 0;
	// xInter and yInter values are used for the actual difference calculation
	int xInter = 0;
	int yInter = 0;
	int xUp = 0;
	int yUp = 0;
	
	static boolean actionMode = false;
	// character array to be translated at modeIndex 1 and 2
	public static boolean[][] charArray = {{false,false},{false,false},{false,false}};
	
	int tapCounter = 0;
	public static int deletionCounter = 0;
	public static int inputCounter = 0;
	int modeIndex = 0;
	static int inputLanguage = 0;
	static String charToAppend = "";
	static boolean deleteChar = false;
	
	// string variables to send
	String emailBody = "";
	String emailAddress = "";
	
	//performed action is initialized in 100 to avoid confusing 0 with a tap
	int performedAction = 100;
	
	// variables that will save values when device is rotated
	private static final String TAG = "EchoActivity";
	public static final String KEY_INDEX = "index";
	public static final String KEY_BODY = "body";
	public static final String KEY_ADDRESS = "address";
	
	// asset manager
	AssetManager assetManager;
	
	// native TTS
	private TextToSpeech echoTTS;
	private static final int MY_DATA_CHECK_CODE = 1234;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_echo);
        
        // Fire off an intent to check if a TTS engine is installed
        Intent checkIntent = new Intent();
        checkIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        startActivityForResult(checkIntent, MY_DATA_CHECK_CODE);
        
        // link elements to id
        mGestureOverlayView1 = (GestureOverlayView)findViewById(R.id.mGestureOverlayView1);
        mGestureOverlayView1.setOnTouchListener(this);
        
        mTextView1 = (TextView)findViewById(R.id.mTextView1);
        
        if (savedInstanceState != null) {
        	modeIndex = savedInstanceState.getInt(KEY_INDEX);
        	emailBody = savedInstanceState.getString(KEY_BODY);
        	emailAddress = savedInstanceState.getString(KEY_ADDRESS);
        	Log.d("debbie","values loaded");
        }
    }
    
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
    	super.onSaveInstanceState(savedInstanceState);
    	Log.i(TAG,"onSaveInstanceState");
    	savedInstanceState.putInt(KEY_INDEX, modeIndex);
    	savedInstanceState.putString(KEY_BODY, emailBody);
    	savedInstanceState.putString(KEY_ADDRESS, emailAddress);
    	Log.d("debbie","values saved");
    }

    // added TTS code
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
    	if (requestCode == MY_DATA_CHECK_CODE)
    	{
    		if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS)
    		{
    			// success, create the TTS instance
    			echoTTS = new TextToSpeech(this, this);
    		} else
    		{
    			//missing data, install it
    			Intent installIntent = new Intent();
    			installIntent.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
    			startActivity(installIntent);
    		}
    	}
    }
    
    // added TTS code
    public void onInit(int i)
    {
    	//read a message when application starts
        echoTTS.speak(getString(R.string.welcome_message), TextToSpeech.QUEUE_FLUSH, null);
    	Log.d("debbie",getString(R.string.welcome_message));
    	mTextView1.setText(getString(R.string.welcome_message));
    }
    
    // methods required by MenuNavigation and BrailleInput
    public static void actionModeOff() {
    	actionMode = false;
    	Log.d("debbie","actionMode false");
    }
    
    public static void emptyCharArray() {
    	for (int i = 0; i < 3; i++)
    		for (int j = 0; j < 2; j++)
    			charArray[i][j] = false;
    }
    
    public static void resetInputCounter() {
    	inputCounter = 0;
    }
    
    public static void evaluateCharArray() {
    	if (inputCounter == 2) {
    		// try to do things with charArray
    		// Log.d("debbie","this array was ready");
    		resetInputCounter();
    		switch (inputLanguage) {
    		case 0:
    			charToAppend = CharacterConversionEnglish.getCharacter(charArray);
    			break;
    		case 1:
    			charToAppend = CharacterConversionJapanese.getCharacter(charArray);
    			break;
    		}
    	} else inputCounter++;
    }
    
    public static void incrementDeletionCounter() {
    	if (deletionCounter == 1) {
    		deleteChar = true;
    		resetDeletionCounter();
    	} else deletionCounter++;
    }
    
    public static void resetDeletionCounter() {
    	deletionCounter = 0;
    }
    
	@SuppressLint("ClickableViewAccessibility")
	public boolean onTouch(View v, MotionEvent e) {

		switch (e.getAction()) {
			case MotionEvent.ACTION_DOWN:
				mTextView1.setText("");
				if (xDown == 0 && yDown == 0) {
					xDown = (int)e.getX();
					yDown = (int)e.getY();
					xInter = xDown;
					yInter = yDown;
				}
			case MotionEvent.ACTION_UP:
				xUp = (int)e.getX();
				yUp = (int)e.getY();
				if (xUp != xDown && yUp != yDown) {
					// START HERE
					
					// count taps to enter action mode
					if (SlideDetection.getDirection(xInter, yInter, xUp, yUp) == 0) {
						performedAction = 0;
						if (tapCounter < 3) {
							tapCounter++;
						} else {
							// resets tap counter with one more tap
							tapCounter = 0;
						}
						if (tapCounter == 3) {
							actionMode = true;
							Log.d("debbie","actionMode true");
							mTextView1.setText(getString(R.string.action_mode)+ " " + String.valueOf(modeIndex));
							echoTTS.speak(getString(R.string.action_mode), TextToSpeech.QUEUE_FLUSH, null);
						}
					} else {
						tapCounter = 0;
						switch (SlideDetection.getDirection(xInter, yInter, xUp, yUp)) {
						case 1:
							performedAction = 1;
							break;
						case 2:
							performedAction = 2;
							break;
						case 3:
							performedAction = 3;
							break;
						case 4:
							performedAction = 4;
							break;
						}
					}
					
					// fillin' up that charArray
					// PLEASE note that this following code's condition uses tapCounter instead
					// of actionMode, which is activated in the next code block
						
					if (tapCounter != 3) {
						// Log.d("debbie","Now we're talking!");
						BrailleInput.evaluateAction(inputCounter, performedAction);
						switch (modeIndex) {
						case 1:
							// do something to emailBody
							if (deleteChar) {
								emailBody = emailBody.substring(0,emailBody.length()-1); // deletes one character
								Log.d("debbie","Delete");
								echoTTS.speak(getString(R.string.deleted), TextToSpeech.QUEUE_FLUSH, null);
								mTextView1.setText(emailBody);
							}
							else {
								Log.d("debbie",charToAppend);
								echoTTS.speak(charToAppend, TextToSpeech.QUEUE_FLUSH, null);
								mTextView1.setText(charToAppend);
								emailBody = emailBody.concat(charToAppend);
								emailBody = BrailleInput.getNormalizedText(emailBody);
							}
							break;
						case 2:
							// do something to emailAddress
							if (deleteChar) {
								emailAddress = emailAddress.substring(0,emailAddress.length()-1); // deletes one character
								Log.d("debbie","Delete");
								echoTTS.speak(getString(R.string.deleted), TextToSpeech.QUEUE_FLUSH, null);
								mTextView1.setText(emailAddress);
							}
							else {
								Log.d("debbie",charToAppend);
								echoTTS.speak(charToAppend, TextToSpeech.QUEUE_FLUSH, null);
								mTextView1.setText(charToAppend);
								emailAddress = emailAddress.concat(charToAppend);
								emailAddress = BrailleInput.getNormalizedText(emailAddress);
							}
							break;
						}
					} else {
						resetInputCounter();
						emptyCharArray();
					}
					
					// don't mess with this order
					if (actionMode == true) {
						modeIndex = MenuNavigation.getModeIndex(performedAction, modeIndex);
					}
					
					// the next part of the code sets specific input modes and actions depending on the value of modeIndex
					switch (modeIndex) {
					case 4:
						modeIndex = 0;
						sendMail(emailAddress, getString(R.string.sent_from), emailBody);
						echoTTS.speak(getString(R.string.index_4), TextToSpeech.QUEUE_FLUSH, null);
						break;
					case 10:
						modeIndex = 0;
						break;
					case 11:
						Log.d("debbie", emailBody);
						mTextView1.setText(emailBody);
						echoTTS.speak(emailBody, TextToSpeech.QUEUE_FLUSH, null);
						modeIndex = 1;
						break;
					case 21:
						Log.d("debbie", emailAddress);
						mTextView1.setText(emailAddress);
						// --------------------------
						// FOR TEST PURPOSES ONLY
						// DISABLE FOR REAL WORLD USE
						// --------------------------
						emailAddress = "francho6807@gmail.com";
						
						echoTTS.speak(emailAddress, TextToSpeech.QUEUE_FLUSH, null);
						modeIndex = 2;
						break;

					// sets input method to Japanese (inputLanguage value is 1)
					case 60:
						modeIndex = 0;
						inputLanguage = 1;
						echoTTS.speak(getString(R.string.language_japanese), TextToSpeech.QUEUE_FLUSH, null);
						break;

					// sets input method to English (inputLanguage value is 0)
					case 70:
						modeIndex = 0;
						inputLanguage = 0;
						echoTTS.speak(getString(R.string.language_english), TextToSpeech.QUEUE_FLUSH, null);
						break;
					}
					
					// set text on mTextView1 and empty charToAppend
					//mTextView1.setText(charToAppend);
					charToAppend = "";
					// reset deleteChar boolean
					deleteChar = false;
					// empty performedAction variable
					performedAction = 100;
				}
				xDown = 0;
				yDown = 0;
		}
		return false;
	}
	
	private void sendMail(String email, String subject, String messageBody) {
        Session session = createSessionObject();

        try {
            Message message = createMessage(email, subject, messageBody, session);
            new SendMailTask().execute(message);
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private Message createMessage(String email, String subject, String messageBody, Session session) throws MessagingException, UnsupportedEncodingException {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("francho6807@gmail.com", "Lopez Frank"));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(email, email));
        message.setSubject(subject);
        message.setText(messageBody);
        return message;
    }

    private Session createSessionObject() {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        return Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
    }

    private class SendMailTask extends AsyncTask<Message, Void, Void> {
        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(EchoActivity.this, "Please wait", "Sending mail", true, false);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressDialog.dismiss();
        }

        @Override
        protected Void doInBackground(Message... messages) {
            try {
                Transport.send(messages[0]);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}