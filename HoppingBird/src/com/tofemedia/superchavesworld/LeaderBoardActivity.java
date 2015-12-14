package com.tofemedia.superchavesworld;


import org.cocos2d.nodes.CCDirector;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.google.android.gms.games.Games;
import com.google.example.games.basegameutils.BaseGameActivity;
import com.tofemedia.superchavesworld.R;

public class LeaderBoardActivity extends BaseGameActivity {

	private int _score,level_mode;
	public static LeaderBoardActivity leader_app;
	
	private ProgressDialog progressdg;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_leader_board);
		
		SharedPreferences sp = CCDirector.sharedDirector().getActivity().getSharedPreferences("GameInfo", 0);
		_score = sp.getInt("GameScore", 0);
		level_mode=sp.getInt("mode", 0);
			
//		Toast.makeText(LeaderBoardActivity.this, String.valueOf(level_mode), Toast.LENGTH_SHORT).show();
		beginUserInitiatedSignIn();	
		
		Button leader=(Button)this.findViewById(R.id.leaderboard);
		Button archivement=(Button)this.findViewById(R.id.archivement);
		
		leader.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(getApiClient().isConnected()){
					startActivityForResult(Games.Leaderboards.getLeaderboardIntent(getApiClient(), getString(R.string.leaderboard_id)), 0);	
				}
			}
		});
		archivement.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(getApiClient().isConnected()){
					if (_score>100) {
						Games.Achievements.unlock(getApiClient(), 
								getString(R.string.coin100));
					} 
					if(_score>250) {
						Games.Achievements.unlock(getApiClient(), 
									getString(R.string.coin250));
					}
					if(_score>500) {
						Games.Achievements.unlock(getApiClient(),
								getString(R.string.coin500));
					}
					if(_score>1000) {
						Games.Achievements.unlock(getApiClient(), 
								getString(R.string.coin1000));
					}
					if(_score>2500) {
						Games.Achievements.unlock(getApiClient(), 
								getString(R.string.coin2500));
					}
					if(_score>5000) {
						Games.Achievements.unlock(getApiClient(), 
								getString(R.string.coin5000));
					}
					if(_score>10000) {
						Games.Achievements.unlock(getApiClient(), 
									getString(R.string.coin10000));
					}
					if(_score>25000) {
						Games.Achievements.unlock(getApiClient(), 
								getString(R.string.coin25000));
					}
					if(_score>50000) {
						Games.Achievements.unlock(getApiClient(), 
								getString(R.string.coin50000));
					}
					if(_score>100000) {
						Games.Achievements.unlock(getApiClient(), 
								getString(R.string.coin100000));
					}
					if (level_mode>0&&level_mode<=1) {
						Games.Achievements.unlock(getApiClient(), getString(R.string.world_2));
					}
					if(level_mode>1&&level_mode<=2){
						Games.Achievements.unlock(getApiClient(), getString(R.string.world_3));
					}
					if(level_mode>2&&level_mode<=3){
						Games.Achievements.unlock(getApiClient(), getString(R.string.world_4));
					}
					if(level_mode>3&&level_mode<=4){
						Games.Achievements.unlock(getApiClient(), getString(R.string.world_5));	
					}
					
					startActivityForResult(Games.Achievements.getAchievementsIntent(
							getApiClient()), 1);
				}
			}
		});
	}
	
	@Override
	public void onSignInFailed() {
		// TODO Auto-generated method stub
		finish();
	}

	@Override
	public void onSignInSucceeded() {
		// TODO Auto-generated method stub
		if (getApiClient().isConnected()) {
			Games.Leaderboards.submitScore(getApiClient(), getString(R.string.leaderboard_id), _score);
			startActivityForResult(Games.Leaderboards.getLeaderboardIntent(getApiClient(), getString(R.string.leaderboard_id)), 0);	
			if(getApiClient().isConnected()){
				if (_score>100) {
					Games.Achievements.unlock(getApiClient(), 
							getString(R.string.coin100));
				} 
				if(_score>250) {
					Games.Achievements.unlock(getApiClient(), 
								getString(R.string.coin250));
				}
				if(_score>500) {
					Games.Achievements.unlock(getApiClient(),
							getString(R.string.coin500));
				}
				if(_score>1000) {
					Games.Achievements.unlock(getApiClient(), 
							getString(R.string.coin1000));
				}
				if(_score>2500) {
					Games.Achievements.unlock(getApiClient(), 
							getString(R.string.coin2500));
				}
				if(_score>5000) {
					Games.Achievements.unlock(getApiClient(), 
							getString(R.string.coin5000));
				}
				if(_score>10000) {
					Games.Achievements.unlock(getApiClient(), 
								getString(R.string.coin10000));
				}
				if(_score>25000) {
					Games.Achievements.unlock(getApiClient(), 
							getString(R.string.coin25000));
				}
				if(_score>50000) {
					Games.Achievements.unlock(getApiClient(), 
							getString(R.string.coin50000));
				}
				if(_score>100000) {
					Games.Achievements.unlock(getApiClient(), 
							getString(R.string.coin100000));
				}
				if (level_mode>0&&level_mode<=1) {
					Games.Achievements.unlock(getApiClient(), getString(R.string.world_2));
				}
				if(level_mode>1&&level_mode<=2){
					Games.Achievements.unlock(getApiClient(), getString(R.string.world_3));
				}
				if(level_mode>2&&level_mode<=3){
					Games.Achievements.unlock(getApiClient(), getString(R.string.world_4));
				}
				if(level_mode>3&&level_mode<=4){
					Games.Achievements.unlock(getApiClient(), getString(R.string.world_5));	
				}
			}
		}	
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event){
		if( keyCode == KeyEvent.KEYCODE_BACK ){
			finish();
	    	return true;
	    }
		return super.onKeyDown(keyCode, event);
	}
}
	
