package com.osreboot.rquadrotords;

import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.*;

import org.newdawn.slick.Color;

import com.osreboot.ridhvl.HvlMath;
import com.osreboot.ridhvl.template.HvlTemplateInteg2D;

public class UI {

	public static final float TRANSIN_TIME = 0.002f,//8f,
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
			FRAME_THICKNESS = 6f,
			ROTOR_START = 0.8f,
			ROTOR_SIZE = 40,
			ROTOR_SPEED_AMPLIFIER = 10,
			HAIR_SLIDE_START = 0.5f,
			HAIR_SLIDE_END = 0.6f,
			HAIR_EXTENSION = 20,
			HAIR_SIZE = 25,
			HAIR_AMPLIFICATION = 0.5f,
			YAW_START = 0.9f,
			YAW_END = 1.0f,
			YAW_SIZE = 150,
			YAW_AMPLIFIER = 30f,
			YAW_SUB_DIST = 50,
			YAW_SUB_LENGTH = 0.15f,
			YAW_SUB_LENGTH2 = 0.05f,
			ALT_START = 0.8f,
			ALT_END = 1f,
			ALT_AMPLIFIER = 40f,
			ALT_BAR1_LENGTH = 40,
			ALT_BAR2_LENGTH = 20,
			ALT_DIVISIONS = 8f;

	private static float transIn = 0, m1r, m2r, m3r, m4r, alt;

	@SuppressWarnings("deprecation")
	public static void draw(float delta){
		transIn = Math.min(Main.getNewestInstance().getTimer().getTotalTime(), TRANSIN_TIME)/TRANSIN_TIME;
		float cx = ((HvlTemplateInteg2D)Main.getNewestInstance()).getWidth()/2, cy = ((HvlTemplateInteg2D)Main.getNewestInstance()).getHeight()/2;

		if(transIn > CENTER_SQUARE_ROT_START && transIn < CENTER_SQUARE_ROT_END) hvlRotate(cx, cy, HvlMath.map(transIn, CENTER_SQUARE_ROT_START, CENTER_SQUARE_ROT_END, 0f, 1f)*-180 - 90);
		float offset1 = HvlMath.mapl(transIn, CENTER_SQUARE_SLIDE_START, CENTER_SQUARE_SLIDE_END, 0f, 1f) * CENTER_SQUARE_SIZE;
		hvlDrawLinec(cx, cy + offset1, CENTER_SQUARE_SIZE * 2, 0, Color.white);
		hvlDrawLinec(cx, cy - offset1, CENTER_SQUARE_SIZE * 2, 0, Color.white);
		hvlDrawLinec(cx + offset1, cy, 0, CENTER_SQUARE_SIZE * 2, Color.white);
		hvlDrawLinec(cx - offset1, cy, 0, CENTER_SQUARE_SIZE * 2, Color.white);
		if(transIn > CENTER_SQUARE_ROT_START && transIn < CENTER_SQUARE_ROT_END) hvlResetRotation();
		
		hvlForceRefresh();
		
		float strobe = (float)Math.abs(Math.sin(Main.getNewestInstance().getTimer().getTotalTime() * 50));
		if(transIn > STROBE_START && transIn < STROBE_END && strobe < HvlMath.map(transIn, STROBE_START, STROBE_END, 0f, 5f)) Main.font.drawWord("enabled", cx - (Main.font.getLineWidth("enabled")/2*0.4f), cy - Main.font.getFontHeight()*0.2f, 0.4f, Color.white);
	
		float offset2 = HvlMath.mapl(transIn, FRAME_SLIDE_START, FRAME_SLIDE_END, 0f, 1f) * FRAME_SIZE;
		hvlDrawLine(cx - offset2, cy, cx + offset2, cy, Color.white, FRAME_THICKNESS);
		hvlDrawLine(cx, cy - offset2, cx, cy + offset2, Color.white, FRAME_THICKNESS);
		
		if(transIn >= ROTOR_START){
			m1r += Values.getM1ctrl() * ROTOR_SPEED_AMPLIFIER * ((transIn - ROTOR_START)/(1f - ROTOR_START));
			m2r -= Values.getM2ctrl() * ROTOR_SPEED_AMPLIFIER * ((transIn - ROTOR_START)/(1f - ROTOR_START));
			m3r += Values.getM3ctrl() * ROTOR_SPEED_AMPLIFIER * ((transIn - ROTOR_START)/(1f - ROTOR_START));
			m4r -= Values.getM4ctrl() * ROTOR_SPEED_AMPLIFIER * ((transIn - ROTOR_START)/(1f - ROTOR_START));
			hvlRotate(cx, cy - FRAME_SIZE + ROTOR_SIZE, m1r);
			hvlDrawLine(cx, cy - FRAME_SIZE, cx, cy - FRAME_SIZE + (ROTOR_SIZE*2), Color.white, FRAME_THICKNESS);
			hvlResetRotation();
			hvlRotate(cx + FRAME_SIZE - ROTOR_SIZE, cy, m2r);
			hvlDrawLine(cx + FRAME_SIZE, cy, cx + FRAME_SIZE - (ROTOR_SIZE*2), cy, Color.white, FRAME_THICKNESS);
			hvlResetRotation();
			hvlRotate(cx, cy + FRAME_SIZE - ROTOR_SIZE, m3r);
			hvlDrawLine(cx, cy + FRAME_SIZE, cx, cy + FRAME_SIZE - (ROTOR_SIZE*2), Color.white, FRAME_THICKNESS);
			hvlResetRotation();
			hvlRotate(cx - FRAME_SIZE + ROTOR_SIZE, cy, m4r);
			hvlDrawLine(cx - FRAME_SIZE, cy, cx - FRAME_SIZE + (ROTOR_SIZE*2), cy, Color.white, FRAME_THICKNESS);
			hvlResetRotation();
		}
		
		float offset3 = HvlMath.mapl(transIn, HAIR_SLIDE_START, HAIR_SLIDE_END, 0f, 1f) * ((CENTER_SQUARE_SIZE*2) + HAIR_EXTENSION);
		float wx = (float)Math.max(Math.min(((Values.getM4ctrl() - Values.getM2ctrl()) * HAIR_AMPLIFICATION) * CENTER_SQUARE_SIZE, CENTER_SQUARE_SIZE), -CENTER_SQUARE_SIZE);
		float wy = (float)Math.max(Math.min(((Values.getM1ctrl() - Values.getM3ctrl()) * HAIR_AMPLIFICATION) * CENTER_SQUARE_SIZE, CENTER_SQUARE_SIZE), -CENTER_SQUARE_SIZE);
		hvlDrawLine(cx + wx, cy - CENTER_SQUARE_SIZE - HAIR_EXTENSION, cx + wx, cy - CENTER_SQUARE_SIZE - HAIR_EXTENSION + offset3, Color.white, 1f);
		hvlDrawLine(cx - CENTER_SQUARE_SIZE - HAIR_EXTENSION, cy + wy, cx - CENTER_SQUARE_SIZE - HAIR_EXTENSION + offset3, cy + wy, Color.white, 1f);
		float offset4 = HvlMath.mapl(transIn, HAIR_SLIDE_START, HAIR_SLIDE_END, 0f, 1f) * HAIR_SIZE;
		hvlDrawLine(cx + wx - offset4, cy - CENTER_SQUARE_SIZE - HAIR_EXTENSION, cx + wx + offset4, cy - CENTER_SQUARE_SIZE - HAIR_EXTENSION, Color.white);
		hvlDrawLine(cx - CENTER_SQUARE_SIZE - HAIR_EXTENSION, cy + wy - offset4, cx - CENTER_SQUARE_SIZE - HAIR_EXTENSION, cy + wy + offset4, Color.white);
		
		float offset5 = HvlMath.mapl(transIn, YAW_START, YAW_END, 0f, 1f) * YAW_SIZE;
		hvlRotate(cx, cy, HvlMath.limit(-((Values.getM1ctrl() + Values.getM3ctrl()) - (Values.getM2ctrl() + Values.getM4ctrl())) * YAW_AMPLIFIER, -90, 90));
		hvlDrawLine(cx - offset5, cy, cx + offset5, cy, Color.white);
		hvlDrawLine(cx - (offset5 * YAW_SUB_LENGTH), cy + YAW_SUB_DIST, cx + (offset5 * YAW_SUB_LENGTH), cy + YAW_SUB_DIST, Color.white);
		hvlDrawLine(cx - (offset5 * YAW_SUB_LENGTH), cy - YAW_SUB_DIST, cx + (offset5 * YAW_SUB_LENGTH), cy - YAW_SUB_DIST, Color.white);
		hvlDrawLine(cx - (offset5 * YAW_SUB_LENGTH2), cy + (YAW_SUB_DIST * 2), cx + (offset5 * YAW_SUB_LENGTH2), cy + (YAW_SUB_DIST * 2), Color.white);
		hvlDrawLine(cx - (offset5 * YAW_SUB_LENGTH2), cy - (YAW_SUB_DIST * 2), cx + (offset5 * YAW_SUB_LENGTH2), cy - (YAW_SUB_DIST * 2), Color.white);
		hvlResetRotation();
		
		if(transIn >= 1) alt += ((Values.getM1ctrl() + Values.getM2ctrl() + Values.getM3ctrl() + Values.getM4ctrl())/4f - 1.1f) * ALT_AMPLIFIER;
		float offset6 = HvlMath.mapl(transIn, ALT_START, ALT_END, 0f, 1f);
		for(float f = 0; f <= 1f; f += 1f/ALT_DIVISIONS){
			float offset7 = HvlMath.limit(cy - CENTER_SQUARE_SIZE + ((alt + (CENTER_SQUARE_SIZE*f)) % (CENTER_SQUARE_SIZE*2)), cy - CENTER_SQUARE_SIZE, cy + CENTER_SQUARE_SIZE);
			float offset8 = HvlMath.limit(cy - CENTER_SQUARE_SIZE + ((alt - (CENTER_SQUARE_SIZE*f)) % (CENTER_SQUARE_SIZE*2)), cy - CENTER_SQUARE_SIZE, cy + CENTER_SQUARE_SIZE);
			hvlDrawLine(cx - CENTER_SQUARE_SIZE, offset7, cx - CENTER_SQUARE_SIZE + (f == 0.5f ? (ALT_BAR1_LENGTH*offset6) : (ALT_BAR2_LENGTH*offset6)), offset7, Color.white);
			hvlDrawLine(cx + CENTER_SQUARE_SIZE, offset7, cx + CENTER_SQUARE_SIZE - (f == 0.5f ? (ALT_BAR1_LENGTH*offset6) : (ALT_BAR2_LENGTH*offset6)), offset7, Color.white);
			hvlDrawLine(cx - CENTER_SQUARE_SIZE, offset8, cx - CENTER_SQUARE_SIZE + (f == 0.5f ? (ALT_BAR1_LENGTH*offset6) : (ALT_BAR2_LENGTH*offset6)), offset8, Color.white);
			hvlDrawLine(cx + CENTER_SQUARE_SIZE, offset8, cx + CENTER_SQUARE_SIZE - (f == 0.5f ? (ALT_BAR1_LENGTH*offset6) : (ALT_BAR2_LENGTH*offset6)), offset8, Color.white);
		}
	}
	
	public static boolean isReady(){
		return transIn >= 1;
	}

}
