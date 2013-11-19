import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.File;
import java.io.IOException;

public class Main 
{	
   static BufferedImage img;
   
   public static void main(String[] args) 
   {
       JFrame demo = new JFrame("MOSFET Load Line and Transfer Characteristics Graph");
       demo.setLayout(new FlowLayout());
       
       OneResistorBiasedMosFETParameters circuitParams = new OneResistorBiasedMosFETParameters();
       
       OneResistorBiasedFETGraphPanel graph = new OneResistorBiasedFETGraphPanel(circuitParams);
  
       //Add a the VGS and VDS slider controls to the GUI
       //VgsVdsControlPanel needs a reference to the graph so it can modify it
       demo.add(new VgsVdsControlPanel(graph));
       
       //Add a Control Panel that controls the circuit parameters to the GUI
       //it needs a reference to the original circuit params, so we pass that along as well
       demo.add(new OneResistorBiasedMosFETControlPanel(circuitParams));
       
       //this next bit of code is ugly, but all it does is search for the image
       //in two places src/ and ./
       img = null;
       String file = "commonSource.png"; 
       do
       {
          try 
          {
              img = ImageIO.read(new File(file));
              System.out.println("loaded " + file);
              JFrame diagram = new JFrame("Circuit Diagram");
              diagram.add(new JLabel(new ImageIcon(img)));
              diagram.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
              diagram.setSize(new Dimension(img.getWidth(), img.getHeight()));
              diagram.setVisible(true);
              break;
          } 
          catch (IOException e) 
          {
        	  if (file.equals("src/commonSource.png")) { break; }
    	      System.out.println("Unable to load " + file);
    	      file = "src/commonSource.png";   	      
    	      continue;
          }    
          
       } while (true);
       
       demo.add(graph);
       
	   demo.pack();
       demo.setVisible(true);

       demo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }
}
   