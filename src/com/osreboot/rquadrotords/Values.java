package com.osreboot.rquadrotords;

public class Values {

	private static float m1 = 0.1f, m2 = 0.1f, m3 = 0.1f, m4 = 0.1f,
			m1ctrl = 0f, m2ctrl = 0f, m3ctrl = 0f, m4ctrl = 0f, 
			m1cbtn = 0f, m2cbtn = 0f, m3cbtn = 0f, m4cbtn = 0f;

	public static final float BASE_SPEED = 0.1f, CONTROL_WEIGHT = 0.2f, CALIBRATION_WEIGHT = 0.001f, MIN_DELAY = 0.1f;

	private static float delay = 0;

	public static void compile(float delta){
		m1 = BASE_SPEED + (m1ctrl * CONTROL_WEIGHT) + (m1cbtn * CALIBRATION_WEIGHT);
		m2 = BASE_SPEED + (m2ctrl * CONTROL_WEIGHT) + (m2cbtn * CALIBRATION_WEIGHT);
		m3 = BASE_SPEED + (m3ctrl * CONTROL_WEIGHT) + (m3cbtn * CALIBRATION_WEIGHT);
		m4 = BASE_SPEED + (m4ctrl * CONTROL_WEIGHT) + (m4cbtn * CALIBRATION_WEIGHT);
		if(m1 >= 1 || m1 <= 0 || 
				m2 >= 1 || m2 <= 0 || 
				m3 >= 1 || m3 <= 0 || 
				m4 >= 1 || m4 <= 0){
			Main.emergencyStop();
		}
		delay += delta;
		if(delay >= MIN_DELAY){
			Connection.send("{" + m1 + "," + m2 + "," + m3 + "," + m4 + "}");
			delay = 0;
		}
	}

	public static float getM1(){
		return m1;
	}

	public static void setM1(float m1Arg){
		m1 = m1Arg;
	}

	public static float getM2(){
		return m2;
	}

	public static void setM2(float m2Arg){
		m2 = m2Arg;
	}

	public static float getM3() {
		return m3;
	}

	public static void setM3(float m3Arg){
		m3 = m3Arg;
	}

	public static float getM4() {
		return m4;
	}

	public static void setM4(float m4Arg){
		m4 = m4Arg;
	}

	public static float getM1ctrl(){
		return m1ctrl;
	}

	public static void setM1ctrl(float m1ctrlArg){
		m1ctrl = m1ctrlArg;
	}

	public static float getM2ctrl(){
		return m2ctrl;
	}

	public static void setM2ctrl(float m2ctrlArg){
		m2ctrl = m2ctrlArg;
	}

	public static float getM3ctrl(){
		return m3ctrl;
	}

	public static void setM3ctrl(float m3ctrlArg){
		m3ctrl = m3ctrlArg;
	}

	public static float getM4ctrl(){
		return m4ctrl;
	}

	public static void setM4ctrl(float m4ctrlArg){
		m4ctrl = m4ctrlArg;
	}

	public static float getM1cbtn(){
		return m1cbtn;
	}

	public static void setM1cbtn(float m1cbtnArg){
		m1cbtn = m1cbtnArg;
	}

	public static float getM2cbtn(){
		return m2cbtn;
	}

	public static void setM2cbtn(float m2cbtnArg){
		m2cbtn = m2cbtnArg;
	}

	public static float getM3cbtn(){
		return m3cbtn;
	}

	public static void setM3cbtn(float m3cbtnArg){
		m3cbtn = m3cbtnArg;
	}

	public static float getM4cbtn(){
		return m4cbtn;
	}

	public static void setM4cbtn(float m4cbtnArg){
		m4cbtn = m4cbtnArg;
	}

}
