package com.osreboot.rquadrotords.modules;

import org.lwjgl.opengl.Display;

import com.osreboot.rquadrotords.Module;

public class ModuleManeuverInput extends Module{

	public ModuleManeuverInput(){
		super(Display.getWidth()/2, Display.getHeight()/2);
	}

	@Override
	public void update(float delta){
		
	}
	
}
