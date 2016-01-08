package com.osreboot.rquadrotords.modules;

import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.hvlDrawLine;
import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.hvlDrawLinec;
import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.hvlResetRotation;
import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.hvlRotate;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;

import com.osreboot.ridhvl.HvlMath;
import com.osreboot.rquadrotords.Module;
import com.osreboot.rquadrotords.Values;

public class ModuleManeuverInput extends Module{

	public static final float CENTER_SQUARE_SIZE = 200f,
			TRANSLATE_AMPLIFIER = 0.5f,
			FRAME_SIZE = 160f,
			FRAME_THICKNESS = 6f,
			ROTOR_SIZE = 40f,
			ROTOR_SPEED_AMPLIFIER = 10f,
			HAIR_EXTENSION = 20f,
			HAIR_SIZE = 25f,
			YAW_SIZE = 150f,
			YAW_AMPLIFIER = 30f,
			YAW_SUB_DIST = 50f,
			YAW_SUB_LENGTH = 0.15f,
			YAW_SUB_LENGTH2 = 0.05f,
			ALT_AMPLIFIER = 1000f,
			ALT_BAR1_LENGTH = 40f,
			ALT_BAR2_LENGTH = 20f,
			ALT_DIVISIONS = 8f;

	public ModuleManeuverInput(){
		super(Display.getWidth()/2, Display.getHeight()/2);
	}

	private static float m1r, m2r, m3r, m4r, alt = 1000000f;

	@Override
	public void update(float delta){
		hvlDrawLinec(getX(), getY() + CENTER_SQUARE_SIZE, CENTER_SQUARE_SIZE * 2, 0, Color.white);
		hvlDrawLinec(getX(), getY() - CENTER_SQUARE_SIZE, CENTER_SQUARE_SIZE * 2, 0, Color.white);
		hvlDrawLinec(getX() + CENTER_SQUARE_SIZE, getY(), 0, CENTER_SQUARE_SIZE * 2, Color.white);
		hvlDrawLinec(getX() - CENTER_SQUARE_SIZE, getY(), 0, CENTER_SQUARE_SIZE * 2, Color.white);

		hvlDrawLine(getX() - FRAME_SIZE, getY(), getX() + FRAME_SIZE, getY(), Color.white, FRAME_THICKNESS);
		hvlDrawLine(getX(), getY() - FRAME_SIZE, getX(), getY() + FRAME_SIZE, Color.white, FRAME_THICKNESS);

		m1r += Values.getM1ctrl() * ROTOR_SPEED_AMPLIFIER;
		m2r -= Values.getM2ctrl() * ROTOR_SPEED_AMPLIFIER;
		m3r += Values.getM3ctrl() * ROTOR_SPEED_AMPLIFIER;
		m4r -= Values.getM4ctrl() * ROTOR_SPEED_AMPLIFIER;
		hvlRotate(getX(), getY() - FRAME_SIZE + ROTOR_SIZE, m1r);
		hvlDrawLine(getX(), getY() - FRAME_SIZE, getX(), getY() - FRAME_SIZE + (ROTOR_SIZE*2), Color.white, FRAME_THICKNESS);
		hvlResetRotation();
		hvlRotate(getX() + FRAME_SIZE - ROTOR_SIZE, getY(), m2r);
		hvlDrawLine(getX() + FRAME_SIZE, getY(), getX() + FRAME_SIZE - (ROTOR_SIZE*2), getY(), Color.white, FRAME_THICKNESS);
		hvlResetRotation();
		hvlRotate(getX(), getY() + FRAME_SIZE - ROTOR_SIZE, m3r);
		hvlDrawLine(getX(), getY() + FRAME_SIZE, getX(), getY() + FRAME_SIZE - (ROTOR_SIZE*2), Color.white, FRAME_THICKNESS);
		hvlResetRotation();
		hvlRotate(getX() - FRAME_SIZE + ROTOR_SIZE, getY(), m4r);
		hvlDrawLine(getX() - FRAME_SIZE, getY(), getX() - FRAME_SIZE + (ROTOR_SIZE*2), getY(), Color.white, FRAME_THICKNESS);
		hvlResetRotation();

		float wx = (float)Math.max(Math.min(((Values.getM4ctrl() - Values.getM2ctrl())) * TRANSLATE_AMPLIFIER * CENTER_SQUARE_SIZE, CENTER_SQUARE_SIZE), -CENTER_SQUARE_SIZE);
		float wy = (float)Math.max(Math.min(((Values.getM1ctrl() - Values.getM3ctrl())) * TRANSLATE_AMPLIFIER * CENTER_SQUARE_SIZE, CENTER_SQUARE_SIZE), -CENTER_SQUARE_SIZE);
		hvlDrawLine(getX() + wx, getY() - CENTER_SQUARE_SIZE - HAIR_EXTENSION, getX() + wx, getY() - CENTER_SQUARE_SIZE - HAIR_EXTENSION + ((CENTER_SQUARE_SIZE*2) + HAIR_EXTENSION), Color.white, 1f);
		hvlDrawLine(getX() - CENTER_SQUARE_SIZE - HAIR_EXTENSION, getY() + wy, getX() - CENTER_SQUARE_SIZE - HAIR_EXTENSION + ((CENTER_SQUARE_SIZE*2) + HAIR_EXTENSION), getY() + wy, Color.white, 1f);
		hvlDrawLine(getX() + wx - HAIR_SIZE, getY() - CENTER_SQUARE_SIZE - HAIR_EXTENSION, getX() + wx + HAIR_SIZE, getY() - CENTER_SQUARE_SIZE - HAIR_EXTENSION, Color.white);
		hvlDrawLine(getX() - CENTER_SQUARE_SIZE - HAIR_EXTENSION, getY() + wy - HAIR_SIZE, getX() - CENTER_SQUARE_SIZE - HAIR_EXTENSION, getY() + wy + HAIR_SIZE, Color.white);

		hvlRotate(getX(), getY(), HvlMath.limit(-((Values.getM1ctrl() + Values.getM3ctrl()) - (Values.getM2ctrl() + Values.getM4ctrl())) * YAW_AMPLIFIER, -90, 90));
		hvlDrawLine(getX() - YAW_SIZE, getY(), getX() + YAW_SIZE, getY(), Color.white);
		hvlDrawLine(getX() - (YAW_SIZE * YAW_SUB_LENGTH), getY() + YAW_SUB_DIST, getX() + (YAW_SIZE * YAW_SUB_LENGTH), getY() + YAW_SUB_DIST, Color.white);
		hvlDrawLine(getX() - (YAW_SIZE * YAW_SUB_LENGTH), getY() - YAW_SUB_DIST, getX() + (YAW_SIZE * YAW_SUB_LENGTH), getY() - YAW_SUB_DIST, Color.white);
		hvlDrawLine(getX() - (YAW_SIZE * YAW_SUB_LENGTH2), getY() + (YAW_SUB_DIST * 2), getX() + (YAW_SIZE * YAW_SUB_LENGTH2), getY() + (YAW_SUB_DIST * 2), Color.white);
		hvlDrawLine(getX() - (YAW_SIZE * YAW_SUB_LENGTH2), getY() - (YAW_SUB_DIST * 2), getX() + (YAW_SIZE * YAW_SUB_LENGTH2), getY() - (YAW_SUB_DIST * 2), Color.white);
		hvlResetRotation();

		alt += ((Values.getM1ctrl() + Values.getM2ctrl() + Values.getM3ctrl() + Values.getM4ctrl())/4f - 1.1f) * ALT_AMPLIFIER * delta;
		for(float f = 0; f <= 1f; f += 1f/ALT_DIVISIONS){
			float offset1 = HvlMath.limit(getY() - CENTER_SQUARE_SIZE + ((alt + (CENTER_SQUARE_SIZE*f)) % (CENTER_SQUARE_SIZE*2)), getY() - CENTER_SQUARE_SIZE, getY() + CENTER_SQUARE_SIZE);
			float offset2 = HvlMath.limit(getY() - CENTER_SQUARE_SIZE + ((alt - (CENTER_SQUARE_SIZE*f)) % (CENTER_SQUARE_SIZE*2)), getY() - CENTER_SQUARE_SIZE, getY() + CENTER_SQUARE_SIZE);
			hvlDrawLine(getX() - CENTER_SQUARE_SIZE, offset1, getX() - CENTER_SQUARE_SIZE + (f == 0.5f ? (ALT_BAR1_LENGTH) : (ALT_BAR2_LENGTH)), offset1, Color.white);
			hvlDrawLine(getX() + CENTER_SQUARE_SIZE, offset1, getX() + CENTER_SQUARE_SIZE - (f == 0.5f ? (ALT_BAR1_LENGTH) : (ALT_BAR2_LENGTH)), offset1, Color.white);
			hvlDrawLine(getX() - CENTER_SQUARE_SIZE, offset2, getX() - CENTER_SQUARE_SIZE + (f == 0.5f ? (ALT_BAR1_LENGTH) : (ALT_BAR2_LENGTH)), offset2, Color.white);
			hvlDrawLine(getX() + CENTER_SQUARE_SIZE, offset2, getX() + CENTER_SQUARE_SIZE - (f == 0.5f ? (ALT_BAR1_LENGTH) : (ALT_BAR2_LENGTH)), offset2, Color.white);
		}
	}

}
