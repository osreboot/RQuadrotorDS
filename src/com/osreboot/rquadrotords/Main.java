package com.osreboot.rquadrotords;

import org.newdawn.slick.Color;

import com.osreboot.ridhvl.HvlFontUtil;
import com.osreboot.ridhvl.display.collection.HvlDisplayModeDefault;
import com.osreboot.ridhvl.input.collection.HvlCPG_Gamepad;
import com.osreboot.ridhvl.painter.painter2d.HvlFontPainter2D;
import com.osreboot.ridhvl.template.HvlTemplateInteg2D;
import com.osreboot.rquadrotords.modules.ModuleCalibration;
import com.osreboot.rquadrotords.modules.ModuleManeuverInput;

public class Main extends HvlTemplateInteg2D{

	public static void main(String args[]) {
		new Main();
	}

	public Main(){
		super(60, 1280, 720, "Quadrotor Driver Station", new HvlDisplayModeDefault());
	}

	public static HvlFontPainter2D font;

	private static boolean emergencyStopped = false;

	HvlCPG_Gamepad profile;

	@Override
	public void initialize(){
		getTextureLoader().loadResource("Font");
		font = new HvlFontPainter2D(getTexture(0), HvlFontUtil.SIMPLISTIC, HvlFontPainter2D.CHALK, 0.25f);
		profile = new HvlCPG_Gamepad();
		
		Connection.initialize();

		new ModuleManeuverInput();
		new ModuleCalibration();
	}

	@Override
	public void update(float delta){
		Connection.update(delta);
		if(profile.getValue(HvlCPG_Gamepad.BUTTON_B) == 1) emergencyStop();
		if(!emergencyStopped){
			if(profile.getValue(HvlCPG_Gamepad.TRIGGER_RIGHT) > 0.5f){
				float m1 = -profile.getValue(HvlCPG_Gamepad.JOY1Y);
				float m2 = -profile.getValue(HvlCPG_Gamepad.JOY1X);
				float m3 = profile.getValue(HvlCPG_Gamepad.JOY1Y);
				float m4 = profile.getValue(HvlCPG_Gamepad.JOY1X);
				m1 += -profile.getValue(HvlCPG_Gamepad.JOY2X)/6f;
				m2 += profile.getValue(HvlCPG_Gamepad.JOY2X)/6f;
				m3 += -profile.getValue(HvlCPG_Gamepad.JOY2X)/6f;
				m4 += profile.getValue(HvlCPG_Gamepad.JOY2X)/6f;
				m1 += profile.getValue(HvlCPG_Gamepad.JOY2Y)/6f;
				m2 += profile.getValue(HvlCPG_Gamepad.JOY2Y)/6f;
				m3 += profile.getValue(HvlCPG_Gamepad.JOY2Y)/6f;
				m4 += profile.getValue(HvlCPG_Gamepad.JOY2Y)/6f;
				Values.setM1cbtn(Values.getM1cbtn() + m1);
				Values.setM2cbtn(Values.getM2cbtn() + m2);
				Values.setM3cbtn(Values.getM3cbtn() + m3);
				Values.setM4cbtn(Values.getM4cbtn() + m4);
			}else{
				float m1 = -profile.getValue(HvlCPG_Gamepad.JOY1Y) + 1.1f;
				float m2 = -profile.getValue(HvlCPG_Gamepad.JOY1X) + 1.1f;
				float m3 = profile.getValue(HvlCPG_Gamepad.JOY1Y) + 1.1f;
				float m4 = profile.getValue(HvlCPG_Gamepad.JOY1X) + 1.1f;
				m1 += -profile.getValue(HvlCPG_Gamepad.JOY2X)/6f;
				m2 += profile.getValue(HvlCPG_Gamepad.JOY2X)/6f;
				m3 += -profile.getValue(HvlCPG_Gamepad.JOY2X)/6f;
				m4 += profile.getValue(HvlCPG_Gamepad.JOY2X)/6f;
				m1 += profile.getValue(HvlCPG_Gamepad.JOY2Y)/6f;
				m2 += profile.getValue(HvlCPG_Gamepad.JOY2Y)/6f;
				m3 += profile.getValue(HvlCPG_Gamepad.JOY2Y)/6f;
				m4 += profile.getValue(HvlCPG_Gamepad.JOY2Y)/6f;
				Values.setM1ctrl(m1);
				Values.setM2ctrl(m2);
				Values.setM3ctrl(m3);
				Values.setM4ctrl(m4);
			}
		}else{
			font.drawWord("e-stopped", 5, 5, Color.white);
		}
		font.drawWord(Connection.getPing() + "ms", getWidth() - font.getLineWidth(Connection.getPing() + "ms") - 5, 5, Color.white);
		Values.compile(delta);
		Module.globalUpdate(delta);
	}
	
	public static void emergencyStop(){
		if(!emergencyStopped){
			System.err.println("The quadrotor has been emergency stopped.");
			Connection.disconnect();
		}
		emergencyStopped = true;
	}

	public static boolean isEmergencyStopped(){
		return emergencyStopped;
	}

}
