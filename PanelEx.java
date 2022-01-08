import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.image.BufferedImage;

public class PanelEx extends JPanel{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static int WIDTH = 800;          //Fixed Panel Width
    public static int HEIGHT = 800;         //Fixed Panel Height
    protected static BufferedImage buffer = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
    
    protected void plotFractal( String Name ){
        JFrame frame = new JFrame( Name );	//constructing new frame
	frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	frame.setResizable(false);
        frame.setContentPane(this);
        frame.setPreferredSize( new Dimension( WIDTH , HEIGHT ) );
        frame.setSize( WIDTH, HEIGHT );
        frame.pack();
        frame.setVisible(true);     
    }
    
    @Override
    protected void paintComponent(Graphics r){
        r.drawImage(buffer,0,0,this); 
    }

    protected void Set( int x, int y ){//for points in Fractal Set                  
        buffer.setRGB(x,y,0);//set color to black
        repaint();
    }

    protected void NotSet( int x, int y, int Iteration ){//for points not in Fractal Set
        buffer.setRGB(x,y,Color.HSBtoRGB(Iteration/800f,1,Iteration/(Iteration+10f)));
        repaint();
    }
}

