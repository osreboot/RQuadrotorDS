package com.osreboot.rquadrotords;

import com.osreboot.ridhvl.display.collection.HvlDisplayModeDefault;
import com.osreboot.ridhvl.template.HvlTemplateInteg2D;

public class Main extends HvlTemplateInteg2D{

	public Main(){
		super(60, 1280, 720, "RQuadrotor Driver Station", new HvlDisplayModeDefault());
	}

	@Override
	public void initialize(){
		getTextureLoader().loadResource("Font");
	}

	@Override
	public void update(float delta){
		
	}
	
}
