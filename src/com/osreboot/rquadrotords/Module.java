package com.osreboot.rquadrotords;

import java.util.ArrayList;

public abstract class Module {

	public static ArrayList<Module> modules = new ArrayList<>();
	
	public static void globalUpdate(float delta){
		for(Module m : modules) m.update(delta);
	}
	
	private float x, y;
	
	public Module(float xArg, float yArg){
		x = xArg;
		y = yArg;
		modules.add(this);
	}

	public abstract void update(float delta);

	public float getX(){
		return x;
	}

	public void setX(float x){
		this.x = x;
	}

	public float getY(){
		return y;
	}

	public void setY(float y){
		this.y = y;
	}
	
}
