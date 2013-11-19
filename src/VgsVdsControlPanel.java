import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

   public class VgsVdsControlPanel extends Container
   {
	private static final long serialVersionUID = 1L;
	
	final OneResistorBiasedFETGraphPanel graph;
	   double v_ds_max, v_gs_min = 0, v_gs_max = 7;
	   
	   public VgsVdsControlPanel(final OneResistorBiasedFETGraphPanel graph) 
	   { 
		   super();
		   //todo check for null pointer
		   if (graph == null)
			   throw new NullPointerException("Passed Null Pointer into VgsVdsControlPanel");
		   this.graph = graph; 
		   
		   v_ds_max = graph.getMaxSweepVoltage();
		   super.setLayout(new FlowLayout());
	       JSlider vdsCntrl = new JSlider(0, 100);
	               vdsCntrl.setValue(100);
	       vdsCntrl.addChangeListener(new ChangeListener() {
	    	   
	    	   public void stateChanged(ChangeEvent arg0)
	    	   {    	         
	    		 Object o = arg0.getSource();
		         if (o.getClass() == JSlider.class)
		         {
		        	 JSlider s = (JSlider) o;
		        	 double val = s.getValue();
		        	 //make a fraction
		        	 val /= 100.0;
		        	 
		        	 val *= v_ds_max;
		        	 graph.sweepingPlot(graph.getGateVoltage(), val);
		         }  
	    	   }
	       });
	       super.add(new Label("Drain Source Voltage: "));
	       super.add(vdsCntrl);
	       
	       JButton clear = new JButton("Clear");
	       clear.addActionListener(new ActionListener()
	       {
	    	   public void actionPerformed(ActionEvent e)
	    	   {
	    	      graph.clearTransferPlot();
	    	   }
	       });
	       super.add(clear);
	       
	       JSlider vgsCntrl = new JSlider(0, 100);
	               vgsCntrl.setValue((int)graph.getGateVoltage());
	               
	       vgsCntrl.addChangeListener(new ChangeListener(){
	          public void stateChanged(ChangeEvent arg0) 
	    		  {
	    	         Object o = arg0.getSource();
	    	         if (o.getClass() == JSlider.class)
	    	         {
	    	        	 JSlider s = (JSlider) o;
	    	        	 double gate = s.getValue();
	    	        	 //make a fraction
	    	        	 gate /= 100.0;
	    	        	 
	    	        	 //slider is out of 10V
	    	        	 gate *= v_gs_max - v_gs_min;
	    	        	 
	    	        	 System.out.println("Gate " + gate);
	    	        	 graph.sweepingPlot(v_gs_min + gate, graph.getMaxSweepVoltage());
	    	         }
	    		  }   
	       }
	       );
	       super.add(new Label("Gate to Source Voltage: "));
	       super.add(vgsCntrl);
	   }
   }