package com.osreboot.rquadrotords;

import com.osreboot.ridhvl.HvlFontUtil;
import com.osreboot.ridhvl.display.collection.HvlDisplayModeDefault;
import com.osreboot.ridhvl.input.collection.HvlCPG_Gamepad;
import com.osreboot.ridhvl.painter.painter2d.HvlFontPainter2D;
import com.osreboot.ridhvl.template.HvlTemplateInteg2D;

public class Main extends HvlTemplateInteg2D{

	public static void main(String args[]) {
		new Main();
	}

	public Main(){
		super(60, 1280, 720, "Quadrotor Driver Station", new HvlDisplayModeDefault());
	}

	public static HvlFontPainter2D font;

	HvlCPG_Gamepad profile;
	
	@Override
	public void initialize(){
		getTextureLoader().loadResource("Font");
		font = new HvlFontPainter2D(getTexture(0), HvlFontUtil.SIMPLISTIC, HvlFontPainter2D.CHALK);
		profile = new HvlCPG_Gamepad();
	}

	@Override
	public void update(float delta){
		UI.draw(delta);
		float m1 = -profile.getValue(HvlCPG_Gamepad.JOY1Y) + 1.1f;
		float m2 = -profile.getValue(HvlCPG_Gamepad.JOY1X) + 1.1f;
		float m3 = profile.getValue(HvlCPG_Gamepad.JOY1Y) + 1.1f;
		float m4 = profile.getValue(HvlCPG_Gamepad.JOY1X) + 1.1f;
		m1 += -profile.getValue(HvlCPG_Gamepad.JOY2X)/6f;
		m2 += profile.getValue(HvlCPG_Gamepad.JOY2X)/6f;
		m3 += -profile.getValue(HvlCPG_Gamepad.JOY2X)/6f;
		m4 += profile.getValue(HvlCPG_Gamepad.JOY2X)/6f;
		Values.setM1(m1);
		Values.setM2(m2);
		Values.setM3(m3);
		Values.setM4(m4);
//		Values.setM1((float)Math.cos(getTimer().getTotalTime()) + 1f);
//		Values.setM2((float)Math.sin(getTimer().getTotalTime()) + 1f);
//		Values.setM3(2f - (float)Math.cos(getTimer().getTotalTime()) - 1f);
//		Values.setM4(2f - (float)Math.sin(getTimer().getTotalTime()) - 1f);
	}

}
