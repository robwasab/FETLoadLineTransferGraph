import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

public class OneResistorBiasedMosFETControlPanel extends Container implements MouseListener
{
	private static final long serialVersionUID = 1L;
    private JTextField rd, KN_PRIME, W_L, V_TN;  
    private JButton update;
	private OneResistorBiasedFETParameters cktModel;
    
    public OneResistorBiasedMosFETControlPanel(OneResistorBiasedFETParameters cktModel)
    {
    	super();
    	if(cktModel == null)
    		throw new NullPointerException("Passed Null Pointer into ControlPanel");
    	
    	this.cktModel = cktModel;
    	rd = new JTextField(Double.toString(cktModel.getResistor()));
        KN_PRIME = new JTextField(Double.toString(((MosFET)cktModel.getFET()).KN_PRIME));
        W_L = new JTextField(Double.toString(((MosFET)cktModel.getFET()).W_L));
        V_TN = new JTextField(Double.toString(((MosFET)cktModel.getFET()).V_TN));
        
        super.setLayout(new GridLayout(5, 2));
        super.add(new Label("Drain Resistor"));
        super.add(rd);
        super.add(new Label("KN_PRIME"));
        super.add(KN_PRIME);
        super.add(new Label("W_L"));
        super.add(W_L);
        super.add(new Label("V_TN"));
        super.add(V_TN);
        
        update = new JButton("Update");
        update.addMouseListener(this);
        
        super.add(update);
    }

    public void updateTextFields()
    {
    	rd.setText(Double.toString(cktModel.getResistor()));
        KN_PRIME.setText(Double.toString(((MosFET)cktModel.getFET()).KN_PRIME));
        W_L.setText(Double.toString(((MosFET)cktModel.getFET()).W_L));
        V_TN.setText(Double.toString(((MosFET)cktModel.getFET()).V_TN));
    }
    
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		cktModel.setResistor(new Double(rd.getText()));
		System.out.println("V_TN: " + V_TN.getText());
		((MosFET)cktModel.getFET()).KN_PRIME = (new Double(KN_PRIME.getText()));
		((MosFET)cktModel.getFET()).W_L = (new Double(W_L.getText()));
		((MosFET)cktModel.getFET()).V_TN = (new Double(V_TN.getText()));
		updateTextFields();
		System.out.println(cktModel.getFET().toString());
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
