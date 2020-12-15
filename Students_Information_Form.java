import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Students_Information_Form extends JFrame {

    /**
     * variables
     */
    private JPanel          panel;
    private JLabel          title, picLb, Money;
    private JTextArea       textArea;
    private BufferedImage   Picture;
    private JScrollPane     scrollPane;
    private Student         student;

    /**
     * constructor
     * @param student
     */
    public Students_Information_Form(Student student){
        this.student = student;

        try {
            Picture = ImageIO.read(this.getClass().getResource("Images/Class_icon.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.setSize(600, 800);
        this.setLayout(null);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("Information");
        this.setIconImage(Picture);

        /**
         * make panel
         */
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(0,131,143));
        panel.setBounds(0, 0, this.getWidth(), this.getHeight());

        /**
         * make label
         */
        picLb = new JLabel(new ImageIcon(Picture));
        picLb.setBounds(60, 30, 100, 100);
        panel.add(picLb);

        title = new JLabel("Your Information");
        title.setFont(new Font("Arial", Font.BOLD, 26));
        title.setBounds(240, 60, 300, 50);
        title.setForeground(Color.white);
        panel.add(title);

        /**
         * make text area
         */
        textArea = new JTextArea();
        textArea.setEditable(false);
        addList();

        System.out.println(textArea.getRows());
        scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(20, 170, 540, 480);
        panel.add(scrollPane);

        Money = new JLabel("Average: " + student.getAverage() + "       Money: " + student.getHolding());
        Money.setFont(new Font("Arial", Font.BOLD, 14));
        Money.setBounds(40, 680, 400, 50);
        Money.setForeground(Color.white);
        panel.add(Money);

        this.add(panel);
        this.setVisible(true);

    }

    /**
     * add to list
     */
    public void addList(){

        int[] classesID = student.getClasses();

        textArea.setText("id\tName\tvahed\n");

        try {
            File myFile = new File(Path.Class);
            Scanner myReader = new Scanner(myFile);

            while (myReader.hasNext()){
                int id = myReader.nextInt();
                Class c = new Class(id);
                for (int i = 0; i < student.getcClasses(); i++){
                    if (id == classesID[i]){
                        textArea.setText(textArea.getText() + c.getId() +"\t"+c.getClassName() + "\t" + c.getVahed() +"\n");
                    }
                }

                myReader.nextLine();
            }


        } catch (FileNotFoundException e) {
            System.out.println("admin file NOT Find !");
        }

    }
}
