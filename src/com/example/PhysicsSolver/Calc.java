package com.example.PhysicsSolver;

import com.example.PhysicsSolver.R.id;
import com.example.PhysicsSolver.R.layout;
import com.example.PhysicsSolver.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.support.v4.app.NavUtils;
import android.widget.TextView;
import android.widget.EditText;

/**
 * The calculation activity polls the user for known information and then actually solves
 * for unknown variable
 * @author Ethan Shernan
 * @version 1.0
 */
public class Calc extends Activity {
	//A number to signify which equation we have
	private int eqNum;
	
	//Variable fields
	private EditText var1, var2, var3, var4;
	
	//A number to determine which variable needs to be solved for
	private int varNum;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calc);
		// Show the Up button in the action bar.
		//getActionBar().setDisplayHomeAsUpEnabled(true);
		
		var1 = ((EditText)findViewById(R.id.varfield1));
		var2 = ((EditText)findViewById(R.id.varfield2));
		var3 = ((EditText)findViewById(R.id.varfield3));
		var4 = ((EditText)findViewById(R.id.varfield4));
		
		//Need to receive data from previous activity
		Intent i = getIntent();
		
		//Setting text to appropriate fields
		((TextView)findViewById(R.id.equation)).setText(i.getStringExtra(EqSelect.EQ));
		
		//Setting hints to variable names
		var1.setHint(i.getStringExtra(EqSelect.VAR1));
		var2.setHint(i.getStringExtra(EqSelect.VAR2));
		var3.setHint(i.getStringExtra(EqSelect.VAR3));
		var4.setHint(i.getStringExtra(EqSelect.VAR4));
		
		//Need to get Equation Number
		eqNum = i.getIntExtra(EqSelect.EQNUM, 0);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_calc, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	/**
	 * Begins the calculation by getting proper variable to solve for and using CalcSelector
	 * to calculate the value. It then puts the solved value in the answer box
	 * @param v
	 */

	public void startCalc(View v){
		double v1, v2, v3, v4;
		
		//Reset any non-inputted strings to 0.0, get varNum
		stringToZero();
		
		//If user input numbers for all boxes, jump out
		if(varNum == 0)
			return;
		
		//Gathering all variables
		try{
			
			v1 = Double.parseDouble(var1.getText().toString());
			v2 = Double.parseDouble(var2.getText().toString());
			v3 = Double.parseDouble(var3.getText().toString());
			v4 = Double.parseDouble(var4.getText() .toString());
			
		} catch(NumberFormatException e){
			//Builds and displays a dialog
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage("You did not enter a number in one of the text fields");
		    builder.setTitle("Error");
		    
		    AlertDialog dialog = builder.create();
		    dialog.show();
		    
		    //Clean up solved variable text box
			if(varNum == 1)
				((TextView)findViewById(R.id.varfield1)).setText("");
			else if(varNum == 2)
				((TextView)findViewById(R.id.varfield2)).setText("");
			else if(varNum == 3)
				((TextView)findViewById(R.id.varfield3)).setText("");
			else if(varNum == 4)
				((TextView)findViewById(R.id.varfield4)).setText("");
		    return;
		}
		
		//Use CalcSelector class
		String result = CalcSelector.calc(v1, v2, v3, v4, eqNum, varNum);
		
		//Fix divide by zero weirdness
		if(result.equals("NaN")){
			
			//Builds and displays a dialog
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage("Uh-Oh you divided by zero! Check your input again.");
		    builder.setTitle("Error");
		    
		    AlertDialog dialog = builder.create();
		    dialog.show();
		    
		    //Set answer and units to 0 and N/A
			((TextView)findViewById(R.id.ans)).setText("0");
			((TextView)findViewById(R.id.units)).setText("N/A");
		}
		else{
			//Set answer and Units
			((TextView)findViewById(R.id.ans)).setText(result);
			((TextView)findViewById(R.id.units)).setText(CalcSelector.getUnits());
			
			//Clean up solved variable text box
			if(varNum == 1)
				((TextView)findViewById(R.id.varfield1)).setText("");
			else if(varNum == 2)
				((TextView)findViewById(R.id.varfield2)).setText("");
			else if(varNum == 3)
				((TextView)findViewById(R.id.varfield3)).setText("");
			else if(varNum == 4)
				((TextView)findViewById(R.id.varfield4)).setText("");
		}
	}
	
	/**
	 * Gets the variable to be solved for and sets the text box to 0 to avoid
	 * parsing errors
	 */
	
	public void stringToZero(){
		if(var1.getText().toString().equals("")){
			var1.setText("0");
			varNum = 1;
		}
		
		else if(var2.getText().toString().equals("")){
			var2.setText("0");
			varNum = 2;
		}
		
		else if(var3.getText().toString().equals("")){
			var3.setText("0");
			varNum = 3;
		}
		
		else if(var4.getText().toString().equals("")){
			var4.setText("0");
			varNum = 4;
		}
		else{
			varNum = 0;
			
			//Builds and displays a dialog
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage("Leave your unknown variable blank");
		    builder.setTitle("Error");
		    
		    AlertDialog dialog = builder.create();
		    dialog.show();
		}
		
	}

}
