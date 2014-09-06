package com.example.PhysicsSolver;

import java.text.DecimalFormat;


public class CalcSelector {
	//Used to get the units of the answer
	public static String units;
	
	//Format answers to two significant figures
	private static DecimalFormat df = new DecimalFormat("#.##");
	
	//Should not be able to make an instance of CalcSelector
	private CalcSelector(){}
	
	public static String calc(double v1, double v2, double v3, double v4, int selectNum, int varNum){		
		//Depending on which equation, pick appropriate method
		switch(selectNum){
		case 1:
			return calc1(v1, v2, v3, v4, varNum);
		case 2:
			return calc2(v1, v2, v3, v4, varNum);
		case 3:
			return calc3(v1, v2, v3, v4, varNum);
		case 4:
			return calc4(v1, v2, v3, v4, varNum);
		default:
			return "";
		}
	}
	
	/**
	 * Calculates equation 1 variables
	 * @param d
	 * @param v0
	 * @param t
	 * @param a
	 * @param varNum
	 * @return res
	 */
	private static String calc1(double d, double v0, double t, double a, int varNum){
		double res = 0;
		
		//Solve for appropriate variable
		if(varNum == 1){
			res = v0 * t + .5 * a * Math.pow(t, 2);
			units = "meters";
		}
		else if(varNum == 2){
			res = (d / t) - (.5 * a * t);
			units = "m/s";
		}
		else if(varNum == 3){
			double quadA = ((-1 * v0) + Math.sqrt(Math.pow(v0, 2) - (4 * .5 * a * (-1 * d)))) /(2 * .5 * a);
			double quadB = ((-1 * v0) - Math.sqrt(Math.pow(v0, 2) - (4 * .5 * a * (-1 * d)))) /(2 * .5 * a);
			
			if(quadA >= 0)
				res = quadA;
			else
				res = quadB;
			units = "seconds";
		}
		else if(varNum == 4){
			res = ((d/t) - v0) / .5*t;
			units = "m/s^2";
		}
		
		return df.format(res);
	}
	
	/**
	 * Calculates equation 2 variables
	 * @param vf
	 * @param v0
	 * @param a
	 * @param t
	 * @param varNum
	 * @return res
	 */
	private static String calc2(double vf, double v0, double a, double t, int varNum){
		double res = 0;
		
		//Solve for appropriate variable
		if(varNum == 1){
			res = v0 + a*t;
			units = "m/s";
		}
		else if(varNum == 2){
			res = vf - a*t;
			units = "m/s";
		}
		else if(varNum == 3){
			res = (v0 - vf) / t;
			units = "m/s^2";
		}
		else if(varNum == 4){
			res = (v0 - vf) / a;
			units = "seconds";
		}
		
		return df.format(res);
	}
	
	/**
	 * Calculates equation 3 variables
	 * @param vf
	 * @param v0
	 * @param a
	 * @param d
	 * @param varNum
	 * @return res
	 */
	private static String calc3(double vf, double v0, double a, double d, int varNum){
		double res = 0;
		
		//Solve for appropriate variable
		if(varNum == 1){
			res = Math.pow(v0, 2) + 2*a*d;
			units = "m/s";
		}
		else if(varNum == 2){
			res = Math.sqrt(Math.pow(vf, 2) - 2 * a * d);
			units = "m/s";
		}
		else if(varNum == 3){
			res = (Math.pow(vf, 2) - Math.pow(v0, 2)) / (2*d);
			units = "m/s^2";
		}
		else if(varNum == 4){
			res = (Math.pow(vf, 2) - Math.pow(v0, 2)) / (2*a);
			units = "meters";
		}
		
		return df.format(res);
	}
	
	/**
	 * Calculates equation 4 variables
	 * @param d
	 * @param v0
	 * @param vf
	 * @param t
	 * @param varNum
	 * @return res
	 */
	
	private static String calc4(double d, double v0, double vf, double t, int varNum){
		double res = 0;
		
		//Solve for appropriate variable
		if(varNum == 1){
			res = (v0 + vf) / (2*t);
			units = "meters";
		}
		else if(varNum == 2){
			res = d * 2 * t - vf;
			units = "m/s";
		}
		else if(varNum == 3){
			res = d * 2 * t - v0;
			units = "m/s";
		}
		else if(varNum == 4){
			res = (v0 + vf) / (2*d);;
			units = "seconds";
		}
		
		return df.format(res);
	}
	
	/**
	 * Retrieves the units
	 * @return units
	 */
	
	public static String getUnits(){
		return units;
	}

}
