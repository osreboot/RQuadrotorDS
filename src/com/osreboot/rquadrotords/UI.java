package com.osreboot.rquadrotords;

import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.*;

import org.newdawn.slick.Color;

import com.osreboot.ridhvl.HvlMath;
import com.osreboot.ridhvl.template.HvlTemplateInteg2D;

public class UI {

	public static final float TRANSIN_TIME = 4f,
			CENTER_SQUARE_ROT_START = 0.1f,
			CENTER_SQUARE_ROT_END = 0.4f,
			CENTER_SQUARE_SLIDE_START = 0.5f,
			CENTER_SQUARE_SLIDE_END = 0.8f,
			CENTER_SQUARE_SIZE = 200,
			STROBE_START = 0.6f,
			STROBE_END = 1f;

	private static float transIn = 0;

	@SuppressWarnings("deprecation")
	public static void draw(float delta){
		transIn = Math.min(Main.getNewestInstance().getTimer().getTotalTime(), TRANSIN_TIME)/TRANSIN_TIME;
		float cx = ((HvlTemplateInteg2D)Main.getNewestInstance()).getWidth()/2, cy = ((HvlTemplateInteg2D)Main.getNewestInstance()).getHeight()/2;

		if(transIn > CENTER_SQUARE_ROT_START && transIn < CENTER_SQUARE_ROT_END) hvlRotate(cx, cy, HvlMath.map(transIn, CENTER_SQUARE_ROT_START, CENTER_SQUARE_ROT_END, 0f, 1f)*-180 - 90);
		float offset1 = Math.min(Math.max(HvlMath.map(transIn, CENTER_SQUARE_SLIDE_START, CENTER_SQUARE_SLIDE_END, 0f, 1f), 0f), 1f) * CENTER_SQUARE_SIZE;
		hvlDrawLine(cx - CENTER_SQUARE_SIZE, cy + offset1, cx + CENTER_SQUARE_SIZE, cy + offset1, Color.white);
		hvlDrawLine(cx - CENTER_SQUARE_SIZE, cy - offset1, cx + CENTER_SQUARE_SIZE, cy - offset1, Color.white);
		hvlDrawLine(cx + offset1, cy - CENTER_SQUARE_SIZE, cx + offset1, cy + CENTER_SQUARE_SIZE, Color.white);
		hvlDrawLine(cx - offset1, cy - CENTER_SQUARE_SIZE, cx - offset1, cy + CENTER_SQUARE_SIZE, Color.white);
		if(transIn > CENTER_SQUARE_ROT_START && transIn < CENTER_SQUARE_ROT_END) hvlResetRotation();
		
		hvlForceRefresh();
		
		float strobe = (float)Math.abs(Math.sin(Main.getNewestInstance().getTimer().getTotalTime() * 50));
		if(transIn > STROBE_START && transIn < STROBE_END && strobe < HvlMath.map(transIn, STROBE_START, STROBE_END, 0f, 5f)) Main.font.drawWord("enabled", cx - (Main.font.getLineWidth("enabled")/2*0.4f), cy - Main.font.getFontHeight()*0.2f, 0.4f, Color.white);
	}

}
