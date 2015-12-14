package com.tofemedia.superchavesworld;

import org.cocos2d.actions.ease.CCEaseElasticIn;
import org.cocos2d.actions.ease.CCEaseElasticOut;
import org.cocos2d.actions.interval.CCMoveTo;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.layers.CCLayer;
import org.cocos2d.layers.CCScene;
import org.cocos2d.menus.CCMenu;
import org.cocos2d.menus.CCMenuItemImage;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCLabelAtlas;
import org.cocos2d.nodes.CCNode;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.transitions.CCFadeTransition;
import org.cocos2d.types.CGPoint;

import android.content.SharedPreferences;
import android.util.Log;
import android.view.KeyEvent;

public class SelectLayer extends CCLayer {

	int levelMode;
	int maxstage;
	int currenstage;
	int finalstage=20;
	
	CCNode packPage;
	CCNode selectPage;
	CCSprite archive;
	CCLabelAtlas levelLabel;
	CCMenuItemImage decrease;
	CCMenuItemImage increase;
	
	public SelectLayer(boolean playMusic)
	{
		if (playMusic)
		{
			if (G.bgSound.isPlaying()) G.bgSound.pause();
			G.bgSound = G.soundMenu;
			if (G.music) G.bgSound.start();
		}
		
		setScale(G.scale);
		setAnchorPoint(0, 0);
		
		// background
		CCSprite bg = new CCSprite("background/game_bg0.png");
		bg.setPosition(G.displayCenter());
		addChild(bg);

		createPackPage();
		createSelectPage();
		addChild(packPage);
		
		setIsKeyEnabled(true);
	}
	
	public void createPackPage()
	{
		packPage = CCNode.node();

		CCSprite title = new CCSprite("menu/level_pack.png");
		title.setAnchorPoint(0.5f, 1);
		title.setPosition(G.width*0.5f, G.height*0.9f);
		packPage.addChild(title);

		CCMenuItemImage level1 = CCMenuItemImage.item("menu/beginning1.png", "menu/beginning2.png", this, "onBeginning");
		level1.setPosition(G.width*0.2f, G.height*0.5f);
		CCMenuItemImage level2 = CCMenuItemImage.item("menu/evolution1.png", "menu/evolution2.png","menu/evolution_disable.png", this, "onEvolution");
		level2.setPosition(G.width*0.5f, G.height*0.5f);
		CCMenuItemImage level3 = CCMenuItemImage.item("menu/experience1.png", "menu/experience2.png","menu/experience_disable.png", this, "onExperience");
		level3.setPosition(G.width*0.8f, G.height*0.5f);
		CCMenuItemImage level4		=CCMenuItemImage.item("menu/expert1.png", "menu/expert2.png","menu/expert_disable.png", this, "onExpert");
		level4.setPosition(G.width*0.3f, G.height*0.25f);
		CCMenuItemImage level5	=CCMenuItemImage.item("menu/professional1.png", "menu/professional2.png","menu/professional_disable.png", this, "onProfessional");
		level5.setPosition(G.width*0.7f, G.height*0.25f);	
		
		CCMenuItemImage back = CCMenuItemImage.item("menu/back1.png", "menu/back2.png", this, "onBack1");
		back.setPosition(G.width*0.09f, G.height*0.06f);
		
		CCMenu menu = CCMenu.menu(level1, level2, level3,level4,level5, back);
		menu.setPosition(0, 0);
		packPage.addChild(menu);
		int current_level = 0;
		SharedPreferences sp = CCDirector.sharedDirector().getActivity().getSharedPreferences("GameInfo", 0);		
		for (int i = 1; i < 5; i++) {
			maxstage = sp.getInt(String.format("GameLevel%d", i-1), 1);
			Log.e("Max Stage", String.valueOf(maxstage));
			if (maxstage>20) {
				current_level=i;
			}			
		}		
		Log.e("Current_level", String.valueOf(current_level));
		SharedPreferences.Editor et = sp.edit();
		et.putInt("mode", current_level);
		et.commit();
		int flag2=sp.getInt("flag2", 0);
		int flag3=sp.getInt("flag3", 0);
		int flag4=sp.getInt("flag4", 0);
		int flag5=sp.getInt("flag5", 0);
		switch (current_level) {
		case 0:			
			level2.setIsEnabled(false);
			level3.setIsEnabled(false);
			level4.setIsEnabled(false);
			level5.setIsEnabled(false);
			break;
		case 1:
//			if (flag2==0) {
//				archive=new CCSprite("archive/world2.png");
//				archive.setPosition(G.width*0.5f, G.height*1.6f);
//				addChild(archive,1);
//				archive.runAction(CCSequence.actions(
//						CCEaseElasticOut.action(CCMoveTo.action(1.0f,CGPoint.ccp(G.width*0.5f, G.height*0.85f)), 2.0f),
//						CCEaseElasticIn.action(CCMoveTo.action(1.0f, CGPoint.ccp(G.width*0.5f, G.height*1.5f)), 2.0f)));
//				et.putInt("flag2",1);
//				et.commit();
//			}			
			level3.setIsEnabled(false);
			level4.setIsEnabled(false);
			level5.setIsEnabled(false);					
			break;
		case 2:		
//			if (flag3==0) {
//				archive=new CCSprite("archive/world3.png");
//				archive.setPosition(G.width*0.5f, G.height*1.6f);
//				addChild(archive,1);
//				archive.runAction(CCSequence.actions(
//						CCEaseElasticOut.action(CCMoveTo.action(1.0f,CGPoint.ccp(G.width*0.5f, G.height*0.85f)), 2.0f),
//						CCEaseElasticIn.action(CCMoveTo.action(1.0f, CGPoint.ccp(G.width*0.5f, G.height*1.5f)), 2.0f)));
//				et.putInt("flag3",1);
//				et.commit();
//			}
			
			level4.setIsEnabled(false);
			level5.setIsEnabled(false);
			break;
		case 3:	
//			if (flag4==0) {
//				archive=new CCSprite("archive/world4.png");
//				archive.setPosition(G.width*0.5f, G.height*1.6f);
//				addChild(archive,1);
//				archive.runAction(CCSequence.actions(
//						CCEaseElasticOut.action(CCMoveTo.action(1.0f,CGPoint.ccp(G.width*0.5f, G.height*0.85f)), 2.0f),
//						CCEaseElasticIn.action(CCMoveTo.action(1.0f, CGPoint.ccp(G.width*0.5f, G.height*1.5f)), 2.0f)));
//				et.putInt("flag4",1);
//				et.commit();
//			}			
			level5.setIsEnabled(false);
			break;
		case 4:
//			if (flag5==0) {
//				archive=new CCSprite("archive/world5.png");
//				archive.setPosition(G.width*0.5f, G.height*1.6f);
//				addChild(archive,1);
//				archive.runAction(CCSequence.actions(
//						CCEaseElasticOut.action(CCMoveTo.action(1.0f,CGPoint.ccp(G.width*0.5f, G.height*0.85f)), 2.0f),
//						CCEaseElasticIn.action(CCMoveTo.action(1.0f, CGPoint.ccp(G.width*0.5f, G.height*1.5f)), 2.0f)));
//				et.putInt("flag5",1);
//				et.commit();
//			}			
			break;
		default:
			break;
		}
	}

	public void onBeginning(Object sender)
	{
		showSelectPage(0);
	}

	public void onEvolution(Object sender)
	{
		showSelectPage(1);
	}

	public void onExperience(Object sender)
	{
		showSelectPage(2);
	}
	public void onExpert(Object sender){
		showSelectPage(3);
	}
	public void onProfessional(Object sender) {
		showSelectPage(4);
	}

	public void onBack1(Object sender)
	{
		if( G.sound ) G.soundClick.start();
	
		CCScene s = CCScene.node();
		s.addChild(new MenuLayer(false));
		CCDirector.sharedDirector().replaceScene(CCFadeTransition.transition(0.7f, s));
	}

	public void showSelectPage(int mode)
	{
		if( G.sound ) G.soundClick.start();

		levelMode = mode;
		SharedPreferences sp = CCDirector.sharedDirector().getActivity().getSharedPreferences("GameInfo", 0);
		maxstage = sp.getInt(String.format("GameLevel%d", levelMode), 1);
		if( maxstage>20 )
			maxstage = 20;
		if (maxstage > 1)
		{
			removeChild(packPage, false);
			addChild(selectPage);
			setLevel(maxstage);
		}
		else
		{
			currenstage = 1;
			onStart(null);
		}
	}

	// ===================================== select level ==============================================

	public void createSelectPage()
	{
		selectPage = CCNode.node();

		CCSprite title = new CCSprite("menu/level_select.png");
		title.setAnchorPoint(0.5f, 1);
		title.setPosition(G.width*0.5f, G.height*0.9f);
		selectPage.addChild(title);

		levelLabel = CCLabelAtlas.label("0", "font.png", 34, 43, '0');
		levelLabel.setAnchorPoint(0.5f, 0.5f);
		levelLabel.setPosition(G.width*0.5f, G.height*0.5f);
		selectPage.addChild(levelLabel);

		decrease = CCMenuItemImage.item("menu/arrow1.png", "menu/arrow2.png", this, "onDecrease");
		decrease.setPosition(G.width*0.5f-150, G.height*0.5f);
		increase = CCMenuItemImage.item("menu/arrow1.png", "menu/arrow2.png", this, "onIncrease");
		increase.setScaleX(-1);
		increase.setPosition(G.width*0.5f+150, G.height*0.5f);
		CCMenuItemImage start = CCMenuItemImage.item("menu/start1.png", "menu/start2.png", this, "onStart");
		start.setPosition(G.width*0.5f, G.height*0.3f);

		CCMenuItemImage back = CCMenuItemImage.item("menu/back1.png", "menu/back2.png", this, "onBack2");
		back.setPosition(G.width*0.09f, G.height*0.06f);

		CCMenu menu = CCMenu.menu(decrease, increase, start, back);
		menu.setPosition(0, 0);
		selectPage.addChild(menu);
	}

	public void setLevel(int stage)
	{
		currenstage = stage;
		decrease.setOpacity(currenstage>1?255:128);
		decrease.setIsEnabled(currenstage>1);
		increase.setOpacity(currenstage<maxstage?255:128);
		increase.setIsEnabled(currenstage<maxstage);
		increase.setIsEnabled(currenstage!=finalstage);

		levelLabel.setString(String.format("%02d", currenstage));
	}

	public void onDecrease(Object sender)
	{
		if( G.sound ) G.soundClick.start();
		
		if (currenstage > 1)
		{
			setLevel(currenstage-1);
		}
	}

	public void onIncrease(Object sender)
	{
		if( G.sound ) G.soundClick.start();
		
		if (currenstage < maxstage)
		{
			setLevel(currenstage+1);
		}
	}

	public void onStart(Object sender)
	{
		if( G.sound ) G.soundClick.start();

		CCScene s = CCScene.node();
		s.addChild(new GameLayer(levelMode, currenstage, true));
		CCDirector.sharedDirector().replaceScene(CCFadeTransition.transition(0.7f, s));
	}

	public void onBack2(Object sender)
	{
		if( G.sound ) G.soundClick.start();				
		removeChild(selectPage, false);
		addChild(packPage);
	}

	public boolean ccKeyDown(int keyCode, KeyEvent event)
	{
		if (selectPage.getParent() == null)
		{
			onBack1(null);
		}
		else
		{
			onBack2(null);
		}
		return true;
	}
	
}
