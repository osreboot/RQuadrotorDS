package com.osreboot.rquadrotords;

import java.io.IOException;
import java.io.PrintWriter;

public class Connection {

	private static PrintWriter cmd;

	public static void initialize(){
		Process p;
		try{
			p = Runtime.getRuntime().exec(new String[]{"cmd"});
			cmd = new PrintWriter(p.getOutputStream());
			cmd.println("plink -load quadrotor");
			cmd.flush();
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	public static float ping = 0, pingMax = 0;

	public static void update(float delta){
		ping += delta;
	}

	public static void send(String arg){
		if(!Main.isEmergencyStopped()){
			cmd.println(arg);
			cmd.flush();
			//System.out.println("Sent \"" + arg + "\"");
		}
	}

	public static void disconnect(){
		cmd.close();
	}

	public static int getPing(){
		return (int)(pingMax * 1000f);
	}

}
