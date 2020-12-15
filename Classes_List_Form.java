import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Classes_List_Form extends JFrame {

    /**
     * variables
     */
    private JPanel          panel;
    private JLabel          title, picLb;
    private JTextArea       textArea;
    private BufferedImage   Picture;
    private JScrollPane     scrollPane;
    private Class[]         classes;
    private int             cClasses;

    /**
     * constructor
     */
    public Classes_List_Form(){

        try {
            Picture = ImageIO.read(this.getClass().getResource("Images/Class_icon.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.setSize(600, 700);
        this.setLayout(null);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("Users List");
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

        title = new JLabel("Classes List");
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

        this.add(panel);
        this.setVisible(true);
    }

    /**
     * read classes information and added to text area
     */
    private void addList(){

        classes = new Class[100000];
        cClasses = 0;

        try {
            File myFile = new File(Path.Class);
            Scanner myReader = new Scanner(myFile);

            while (myReader.hasNextLine()){
                classes[cClasses] = new Class(myReader.nextInt());
                cClasses++;
                myReader.nextLine();
            }

            textArea.setText("id\tname\tvahed\tteacher\tDay\tTime\tcapacity\ttaken\n");
            for (int i = 0; i < cClasses; i++){
                textArea.setText(textArea.getText() + classes[i].InfoForList() + "\n");
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("file NOT Find !");
        }
    }
}
