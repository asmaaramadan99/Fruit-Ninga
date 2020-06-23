package Models;

import Models.Interfaces.EquationGenerator;

public class HardEquationGenerator implements EquationGenerator {
	private int screenWidth;
	private int screenHeight;
	
	public HardEquationGenerator(int screenWidth, int screenHeight) {
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
	}
	
	@Override
	public Equation generateEquation() {
		int initialSpeed = (int) MiscUtils.rand(150, 300);
		int startPoint = (int) MiscUtils.rand(0, screenWidth/2);
		double maxAngle = Math.asin(Math.sqrt((2*9.8*screenHeight)/initialSpeed*initialSpeed));
		double projectionAngle = MiscUtils.rand(Math.PI/12, maxAngle);
		Equation equation = new Equation(this.screenHeight, initialSpeed, projectionAngle, startPoint);
		return equation;
	}
	
}
