public abstract class OneResistorBiasedFETParameters{

	protected FET transistor;
	protected double resistor;
	
	public FET getFET() { return transistor; }
	
	public double getResistor() { return resistor; }
	public void setResistor(double r) { resistor = r; }
}
