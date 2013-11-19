
public class MosFET implements FET{

	public double KN_PRIME = 100E-6, W_L = 1, V_TN = 0.75 ;
	
	public MosFET(double KN_PRIME, double W_L, double V_TN)
	{
		this.KN_PRIME = KN_PRIME;
		this.W_L = W_L;
		this.V_TN = V_TN;
	}
	
	public MosFET() { }
	
	public String toString()
	{
		return "KN' : " + Double.toString(KN_PRIME) + " W/L: " 
	    + Double.toString(W_L) + " V_TN: " + Double.toString(V_TN);
	}
	
	public double i_drain(double V_DS, double V_GS)
	{
		if (V_GS < V_TN) { return 0; }
		
		double KN = KN_PRIME * W_L;
		
		//test which region 
		if (V_DS >= (V_GS - V_TN))
		{
			//Saturation Region
			return idSaturation(V_DS, KN, V_GS, V_TN);
		}
		else 
		{
			return idTriode(V_DS, KN, V_GS, V_TN);
		}
	}
	
	private double idSaturation(double V_DS, double KN, double V_GS, double V_TN)
	{
	    return KN / 2 * Math.pow(V_GS - V_TN, 2);
	}
	
	private double idTriode(double V_DS, double KN, double V_GS, double V_TN)
	{
		return KN * (V_GS - V_TN - V_DS/2) * V_DS;
	}
	
}
