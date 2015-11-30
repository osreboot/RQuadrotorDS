package com.osreboot.rquadrotords;

import com.osreboot.ridhvl.HvlFontUtil;
import com.osreboot.ridhvl.display.collection.HvlDisplayModeDefault;
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

	@Override
	public void initialize(){
		getTextureLoader().loadResource("Font");
		font = new HvlFontPainter2D(getTexture(0), HvlFontUtil.SIMPLISTIC, HvlFontPainter2D.CHALK);
	}

	@Override
	public void update(float delta){
		UI.draw(delta);
		Values.setM1((float)Math.cos(getTimer().getTotalTime()) + 1f);
		Values.setM2((float)Math.sin(getTimer().getTotalTime()) + 1f);
		Values.setM3(2f - (float)Math.cos(getTimer().getTotalTime()) - 1f);
		Values.setM4(2f - (float)Math.sin(getTimer().getTotalTime()) - 1f);
	}

}
