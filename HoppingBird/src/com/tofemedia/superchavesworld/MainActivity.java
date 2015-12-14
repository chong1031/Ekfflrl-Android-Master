package com.tofemedia.superchavesworld;

import org.cocos2d.layers.CCScene;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.opengl.CCGLSurfaceView;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager.LayoutParams;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.appodeal.ads.Appodeal;
import com.google.android.gms.ads.AdRequest;
import com.google.example.games.basegameutils.BaseGameActivity;
import com.tofemedia.superchavesworld.R;

public class MainActivity extends BaseGameActivity {
	public boolean isBillingSupported = true;
	
	private CCGLSurfaceView mGLSurfaceView;
	// For Ads
	public static final String REMOVE_ADDS_SKU = "";
	private static final String REMOVE_ADDS = "remove_ads_test";
	static SharedPreferences gamePrefrence;
	
	//appodeal
	private static final String appKey = "0f8050ab42e720c29bdddc4e2618d595def7d8e0df8dd678";

	

	public static MainActivity app;
	
	public void onCreate(Bundle savedInstanceState)
	{
		
		app = this;
		
		super.onCreate(savedInstanceState);
				
		// set view
		mGLSurfaceView = new CCGLSurfaceView(this);
		RelativeLayout layout = new RelativeLayout(this);
 		layout.setLayoutParams(new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
	    
 		// Add the adView to it
 		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
 				LayoutParams.WRAP_CONTENT,
 				LayoutParams.WRAP_CONTENT);
 		params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
 		params.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
 		layout.addView(mGLSurfaceView);
 		setContentView(layout);
 		
 		
		gamePrefrence = getSharedPreferences("null", MODE_PRIVATE);
 		//-----------------------------------------------------Interstitial Add
		Appodeal.initialize(app, appKey, Appodeal.INTERSTITIAL | Appodeal.VIDEO);
		Appodeal.setTesting(false);
		
 		//----------------------
		// set director
		CCDirector director = CCDirector.sharedDirector();
		director.attachInView(mGLSurfaceView);
		director.setAnimationInterval(1/60);

		// get display info
		DisplayMetrics displayMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
		G.display_w = displayMetrics.widthPixels;
		G.display_h = displayMetrics.heightPixels;
		G.scale = Math.max(G.display_w/1280.0f, G.display_h/800.0f);
		G.width = G.display_w / G.scale;
		G.height = G.display_h / G.scale;
		
		// get data
		SharedPreferences sp = CCDirector.sharedDirector().getActivity().getSharedPreferences("GameInfo", 0);		
		G.music = sp.getBoolean("music", true);
		G.sound = sp.getBoolean("sound", true);
		
		// create sound
		G.soundMenu = MediaPlayer.create(this, R.raw.menu);
		G.soundMenu.setLooping(true);
		G.soundGame = MediaPlayer.create(this, R.raw.game);
		G.soundGame.setLooping(true);
		G.soundCollide = MediaPlayer.create(this, R.raw.collide);
		G.soundJump = MediaPlayer.create(this, R.raw.jump);
		G.soundLongJump = MediaPlayer.create(this, R.raw.long_jump);
		G.soundSpeedDown = MediaPlayer.create(this, R.raw.speed_down);
		G.soundSpeedUp = MediaPlayer.create(this, R.raw.speed_up);
		G.soundDirection = MediaPlayer.create(this, R.raw.direction_sign);
		G.soundClick = MediaPlayer.create(this, R.raw.menu_click);
		G.soundCollect = MediaPlayer.create(this, R.raw.collect);
		G.bgSound = G.soundMenu;
             
	beginUserInitiatedSignIn();
		// show menu
        CCScene scene = CCScene.node();
        scene.addChild(new MenuLayer(true));
        director.runWithScene(scene);
	}  
	
		 
	 public void setAdsFreeVersion(boolean isEnabled) {
			Editor editor = gamePrefrence.edit();
			editor.putBoolean(REMOVE_ADDS, isEnabled);
			editor.commit();
		}
		
		public boolean isAdsFreeVersion(){
			return gamePrefrence.getBoolean(REMOVE_ADDS, false);
		}
    @Override
    public void onPause()
    {
    	
    	
        super.onPause();
        G.bgSound.pause();
        CCDirector.sharedDirector().onPause();
    }
    

    @Override
    public void onResume()
    {
        super.onResume();
        
        
        
        if( G.music ) G.bgSound.start();
        
        CCDirector.sharedDirector().onResume();
    }

    @Override
    public void onDestroy()
    {
    	
	    
        super.onDestroy();
        G.bgSound.pause();
        CCDirector.sharedDirector().end();
    }
    
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
    	if( keyCode == KeyEvent.KEYCODE_BACK )
    	{
    		CCDirector.sharedDirector().onKeyDown(event);
    		return true;
    	}
		return super.onKeyDown(keyCode, event);
    }
    
    public void showInterstitialAds()
	{
    	if(!isAdsFreeVersion()){
    			runOnUiThread(new Runnable() {
    				public void run() {
		    		
    					Appodeal.show(app, Appodeal.INTERSTITIAL | Appodeal.VIDEO);
    					//Toast.makeText(getApplicationContext(), "call ads", Toast.LENGTH_LONG).show();
    				
		    	
		    }
		});
   	}
	}

	@Override
	public void onSignInFailed() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSignInSucceeded() {
		// TODO Auto-generated method stub
		
	}
	
	public void openMoreGame(){
		Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/dev?id=8917697813638894541"));
		startActivity(browserIntent);
	}

	public void superchaves2(){
		Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.tofemedia.superpigworld"));
		startActivity(browserIntent);
	}
}
