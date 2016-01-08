package com.osreboot.rquadrotords.modules;

import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.hvlDrawLinec;
import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.hvlDrawQuadc;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;

import com.osreboot.ridhvl.HvlMath;
import com.osreboot.rquadrotords.Module;
import com.osreboot.rquadrotords.Values;

public class ModuleCalibration extends Module{

	public static final float CENTER_SQUARE_SIZE = 180f,
			CENTER_SQUARE_DIVISIONS = 4f,
			CURSOR_AMPLIFIER = 1f,
			CURSOR_SIZE = 10f;
	
	public ModuleCalibration(){
		super(Display.getWidth()/64*53, Display.getHeight()/2);
	}

	@Override
	public void update(float delta){
		hvlDrawLinec(getX(), getY() + CENTER_SQUARE_SIZE, CENTER_SQUARE_SIZE * 2, 0, Color.white);
		hvlDrawLinec(getX(), getY() - CENTER_SQUARE_SIZE, CENTER_SQUARE_SIZE * 2, 0, Color.white);
		hvlDrawLinec(getX() + CENTER_SQUARE_SIZE, getY(), 0, CENTER_SQUARE_SIZE * 2, Color.white);
		hvlDrawLinec(getX() - CENTER_SQUARE_SIZE, getY(), 0, CENTER_SQUARE_SIZE * 2, Color.white);
		for(float f = 0; f <= 1f; f += 1f/CENTER_SQUARE_DIVISIONS){
			hvlDrawLinec(getX(), getY() + CENTER_SQUARE_SIZE * f, CENTER_SQUARE_SIZE * 2, 0, Color.white, f == 0 ? 2 : 1);
			hvlDrawLinec(getX(), getY() - CENTER_SQUARE_SIZE * f, CENTER_SQUARE_SIZE * 2, 0, Color.white, f == 0 ? 2 : 1);
			hvlDrawLinec(getX() + CENTER_SQUARE_SIZE * f, getY(), 0, CENTER_SQUARE_SIZE * 2, Color.white, f == 0 ? 2 : 1);
			hvlDrawLinec(getX() - CENTER_SQUARE_SIZE * f, getY(), 0, CENTER_SQUARE_SIZE * 2, Color.white, f == 0 ? 2 : 1);
		}
		hvlDrawQuadc(getX() + HvlMath.limit(Values.getM4cbtn() - Values.getM2cbtn() * CURSOR_AMPLIFIER, -CENTER_SQUARE_SIZE, CENTER_SQUARE_SIZE), getY() + HvlMath.limit(Values.getM1cbtn() - Values.getM3cbtn() * CURSOR_AMPLIFIER, -CENTER_SQUARE_SIZE, CENTER_SQUARE_SIZE), CURSOR_SIZE, CURSOR_SIZE, Color.white);
	}
	
}
