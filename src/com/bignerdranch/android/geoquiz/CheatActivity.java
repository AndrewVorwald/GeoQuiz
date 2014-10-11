package com.bignerdranch.android.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends Activity {
	
	public static final String EXTRA_ANSWER_IS_TRUE = "com.bignerdranch.android.geoquiz.answer_is_true";
	public static final String EXTRA_ANSWER_SHOWN = "com.bignerdranch.android.geoquiz.answer_shown";
	private static final String TAG = "CheatActivity";
	private static final String DID_CHEAT = "unkown";
	private boolean mAnswerIsTrue;
	private TextView mAnswerTextView;
	private Button mShowAnswer;
	private boolean mUserCheated;
	
	
	private void setAnswerShownResult(boolean isAnswerShown){
		Intent data = new Intent();
		data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
		setResult(RESULT_OK, data);
		mUserCheated = isAnswerShown;
	}
	
     @Override
     protected void onCreate(Bundle savedInstanceState){
    	 
    	 // This makes sure that answer_shown does not pass true unless the user presses the button
    	 if (savedInstanceState != null) {
    		 mUserCheated = savedInstanceState.getBoolean(DID_CHEAT);
    	 }
    	 else
    	 setAnswerShownResult(false);
    	 
    	 
    	 super.onCreate(savedInstanceState);
    	 setContentView(R.layout.activity_cheat);
    	 mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);
    	 
    	 
    	 mAnswerTextView = (TextView)findViewById(R.id.answerTextView);
    	 
    	 mShowAnswer=(Button)findViewById(R.id.showAnswerButton);
    	 mShowAnswer.setOnClickListener(new View.OnClickListener(){
    		 @Override
    		 public void onClick(View v){
    			 if (mAnswerIsTrue){
    				 mAnswerTextView.setText(R.string.true_button);
    			 }
    			 else{
    				 mAnswerTextView.setText(R.string.false_button);
    			 }
    			 setAnswerShownResult(true);
    		 }
    	 });
    	 
    	
    		
    	}
    	
@Override
public void onSaveInstanceState(Bundle savedInstanceState){
super.onSaveInstanceState(savedInstanceState);
Log.i(TAG, "onSaveInstanceState");
savedInstanceState.putBoolean(DID_CHEAT, mUserCheated);
     }
}