import java.awt.GridLayout;

import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.ChartFactory;
import org.jfree.data.xy.*;

public class OneResistorBiasedFETGraphPanel extends JPanel {

	private static final long serialVersionUID = 5331696844988419787L;
    private XYSeries fet;
    private XYSeries resistor;
    
    //represents the intersection of the mosfet and resistor plots
    private XYSeries transfer;
    
    private OneResistorBiasedFETParameters circuitParams;
    
    //graphing params
    double v_min = 0, v_max = 15, v_gs = 2;
    
    private static final double TOLERANCE = 0.000002;
    
	public OneResistorBiasedFETGraphPanel(OneResistorBiasedFETParameters circuitParams)
	{
		super();
        this.circuitParams = circuitParams;
		init();
	}
	
	private void init()
	{
        XYSeriesCollection loadLineCollection = new XYSeriesCollection();
        XYSeriesCollection transferCollection = new XYSeriesCollection();
        
        fet =   new XYSeries("fet");
        resistor = new XYSeries("resistor");
        transfer = new XYSeries("transfer");
        
        loadLineCollection.addSeries(resistor);
        loadLineCollection.addSeries(fet);
        
        transferCollection.addSeries(transfer);
        
        JFreeChart loadLinePlot = ChartFactory.createXYLineChart
        		("Load Line Plot", "Drain to Source Voltage", "Drain Current", loadLineCollection);
        
        JFreeChart transferPlot = ChartFactory.createScatterPlot
        		("Transfer Plot", "Gate to Source Voltage", "Drain to Source Voltage", transferCollection);
        
        sweepingPlot(v_gs, v_max);
        
        // we put the chart into a panel
        ChartPanel loadLinePanel = new ChartPanel(loadLinePlot);
        ChartPanel transferPanel = new ChartPanel(transferPlot);
        
        super.setLayout(new GridLayout(1,2));
        super.add(loadLinePanel);
        super.add(transferPanel);
	}
	
	public void clearTransferPlot() { transfer.clear(); }
		
	public double getMaxSweepVoltage() { return v_max; }
	
	public double getGateVoltage() { return v_gs; }
	
	public void sweepingPlot(double V_GS, double V_MAX)
	{
		this.v_gs = V_GS;
		this.v_max = V_MAX;
		
		fet.clear();
		resistor.clear();
		
        for (double v_ds = 0; v_ds < V_MAX; v_ds+=0.1)
        {
        	FET transistor = circuitParams.getFET();
        	
        	double transistorCurrent = transistor.i_drain(v_ds, v_gs);
        	double resCurrent = (V_MAX-v_ds)/circuitParams.getResistor();
                	
        	fet.add  (v_ds, transistorCurrent);
        	resistor.add(v_ds, resCurrent);
        	if (Math.abs(transistorCurrent - resCurrent) < TOLERANCE) 
        	{
        		transfer.add(V_GS, v_ds);
        		//System.out.println("@ Intersection VDS: " + vd + " I: " + resCurrent);
        	}
        }
	}
}
