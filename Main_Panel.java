
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Main_Panel extends JPanel {
    /**
     * main panel of Main Forms for All users
     */

    private BufferedImage image = null;
    private int a,b;

    /**
     * constructor
     * @param a
     * @param b
     */
    public Main_Panel(int a , int b) {
       this.a = a;
       this.b = b;
       
       try {                
          image = ImageIO.read(new File(Path.Background));
       } catch (IOException ex) {
            // handle exception...
       }
    }

    /**
     * see javadoc for more info on the parameters
     * @param g
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0,a - 10  ,b - 10 , this);
    }

    /**
     * getter and setter method
     * @return a
     */
    public int getA() {
        return a;
    }

    /**
     * @param a
     */
    public void setA(int a) {
        this.a = a;
    }

    /**
     * @return b
     */
    public int getB() {
        return b;
    }

    /**
     * @param b
     */
    public void setB(int b) {
        this.b = b;
    }
    

}
    
    
    

