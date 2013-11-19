
public class OneResistorBiasedMosFETParameters extends OneResistorBiasedFETParameters
{
   OneResistorBiasedMosFETParameters(double KN_PRIME, double W_L, double V_TN, double drainResistor)
   {
	   super();
	   super.transistor = new MosFET(KN_PRIME, W_L, V_TN);
	   super.resistor = drainResistor;
   }
   
   OneResistorBiasedMosFETParameters() 
   { 
	   super();
	   super.transistor = new MosFET(25E-6, 1, 1);
	   super.resistor = 100E3;
   }
}
