package com.osreboot.rquadrotords;

import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.*;

import org.newdawn.slick.Color;

import com.osreboot.ridhvl.HvlMath;
import com.osreboot.ridhvl.template.HvlTemplateInteg2D;

public class UI {

	public static final float TRANSIN_TIME = 8f,
			CENTER_SQUARE_ROT_START = 0.05f,
			CENTER_SQUARE_ROT_END = 0.2f,
			CENTER_SQUARE_SLIDE_START = 0.25f,
			CENTER_SQUARE_SLIDE_END = 0.4f,
			CENTER_SQUARE_SIZE = 200,
			STROBE_START = 0.3f,
			STROBE_END = 0.5f,
			FRAME_SLIDE_START = 0.5f,
			FRAME_SLIDE_END = 0.7f,
			FRAME_SIZE = 160,
			ROTOR_START = 0.8f,
			ROTOR_SIZE = 40,
			ROTOR_SPEED_AMPLIFIER = 100f;

	private static float transIn = 0, m1r, m2r, m3r, m4r;

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
	
		float offset2 = Math.min(Math.max(HvlMath.map(transIn, FRAME_SLIDE_START, FRAME_SLIDE_END, 0f, 1f), 0f), 1f) * FRAME_SIZE;
		hvlDrawLine(cx - offset2, cy, cx + offset2, cy, Color.white, 4f);
		hvlDrawLine(cx, cy - offset2, cx, cy + offset2, Color.white, 4f);
		
		if(transIn >= ROTOR_START){
			m1r += Values.getM1() * ROTOR_SPEED_AMPLIFIER * ((transIn - ROTOR_START)/(1f - ROTOR_START));
			m2r -= Values.getM2() * ROTOR_SPEED_AMPLIFIER * ((transIn - ROTOR_START)/(1f - ROTOR_START));
			m3r += Values.getM3() * ROTOR_SPEED_AMPLIFIER * ((transIn - ROTOR_START)/(1f - ROTOR_START));
			m4r -= Values.getM4() * ROTOR_SPEED_AMPLIFIER * ((transIn - ROTOR_START)/(1f - ROTOR_START));
//			hvlRotate(cx + FRAME_SIZE - ROTOR_SIZE, cy, m1r);
//			hvlDrawLine(cx + FRAME_SIZE - ROTOR_SIZE, cy - ROTOR_SIZE, cx + FRAME_SIZE - ROTOR_SIZE, cy + ROTOR_SIZE, Color.white, 4f);
//			hvlResetRotation();
//			hvlRotate(cx, cy + FRAME_SIZE - ROTOR_SIZE, m2r);
//			hvlDrawLine(cx - ROTOR_SIZE, cy + FRAME_SIZE - ROTOR_SIZE, cx - ROTOR_SIZE, cy + FRAME_SIZE + ROTOR_SIZE, Color.white, 4f);
//			hvlResetRotation();
//			hvlRotate(cx + FRAME_SIZE - ROTOR_SIZE, cy, m3r);
//			hvlDrawLine(cx - FRAME_SIZE + ROTOR_SIZE, cy - ROTOR_SIZE, cx - FRAME_SIZE + ROTOR_SIZE, cy + ROTOR_SIZE, Color.white, 4f);
//			hvlResetRotation();
//			hvlRotate(cx, cy + FRAME_SIZE - ROTOR_SIZE, m4r);
//			hvlDrawLine(cx - ROTOR_SIZE, cy - FRAME_SIZE + ROTOR_SIZE, cx - ROTOR_SIZE, cy - FRAME_SIZE + ROTOR_SIZE, Color.white, 4f);
//			hvlResetRotation();
		}
	}

}
