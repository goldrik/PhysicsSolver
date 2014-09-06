package com.example.PhysicsSolver;

import java.util.HashMap;

import com.example.PhysicsSolver.R.layout;
import com.example.PhysicsSolver.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

/**
 * Equation Select Activity asks user for proper equation to solve with
 * @author Ethan Shernan
 * @version 1.0
 */
public class EqSelect extends Activity {
	
	//Constants to help with data transfer
	public static final String EQ = "com.example.EQ";
	public static final String EQNUM = "com.example.EQNUM";
	public static final String VAR1 = "com.example.VAR1";
	public static final String VAR2 = "com.example.VAR2";
	public static final String VAR3 = "com.example.VAR3";
	public static final String VAR4 = "com.example.VAR4";
	
	//Enum to hold all data for each equation
	public enum Equation{
		EQ1 ("d = v0 * t + 0.5 * a * t^2", "d", "v0", "t", "a", 1),
		EQ2 ("vf = v0 + a * t", "vf", "v0", "a", "t", 2),
		EQ3 ("vf^2 = v0^2 + 2 * a * d", "vf", "v0", "a", "d", 3),
		EQ4 ("d = (v0 + vf)/ 2 * t", "d", "v0", "vf", "t", 4);
		
		public final String eq, v1, v2, v3, v4;
		public int eqNum;
		
		//Set proper data
		Equation(String eq, String var1, String var2, String var3, String var4, int num){
			this.eq = eq;
			v1 = var1;
			v2 = var2;
			v3 = var3;
			v4 = var4;
			eqNum = num;
		}
	}
	
	//In an attempt to be more OOP, gonna use a HashMap for data
	HashMap<String, Equation> eqMap = new HashMap<String, Equation>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		//Need to set up eqMap with Buttons and Equations
		//Need to get a loop here
		
		//Map the buttons and Equation Data
		eqMap.put(Equation.EQ1.eq, Equation.EQ1);
		eqMap.put(Equation.EQ2.eq, Equation.EQ2);
		eqMap.put(Equation.EQ3.eq, Equation.EQ3);
		eqMap.put(Equation.EQ4.eq, Equation.EQ4);
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_eq_select);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_eq_select, menu);
		return true;
	}
	
	/**
	 * Selects the correct equation and passes along appropriate information to calc
	 * activity
	 * @param v
	 */
	public void selectEq(View v){
		Button btn = (Button) v;
		Intent i = new Intent(this, Calc.class);	
		Equation eq = eqMap.get(btn.getText());
		
		//Prepare Data for Activity Transfer
		i.putExtra(EQ, eq.eq);
		i.putExtra(VAR1, eq.v1);
		i.putExtra(VAR2, eq.v2);
		i.putExtra(VAR3, eq.v3);
		i.putExtra(VAR4, eq.v4);
		i.putExtra(EQNUM, eq.eqNum);
		
		//Switch activities
		startActivity(i);
	
	}

}
