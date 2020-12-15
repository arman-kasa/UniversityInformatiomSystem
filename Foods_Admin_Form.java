import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Scanner;

public class Foods_Admin_Form extends JFrame {

    /**
     * variables
     */
    private JPanel          panel;
    private JLabel          title, picLb;
    private JLabel          l1, l2, l3, l4, l5, l6, l7, l8;
    private JTextField      t1, t2, t3, t4, t5, t6;
    private JTextArea       textArea;
    private JButton         update, close;
    private BufferedImage   foodPicture;

    /**
     * constructor
     */
    public Foods_Admin_Form(){

        try {
            foodPicture = ImageIO.read(this.getClass().getResource("Images/Food_icon.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.setSize(500, 600);
        this.setLayout(null);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("Change Password");
        this.setIconImage(foodPicture);

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
        picLb = new JLabel(new ImageIcon(foodPicture));
        picLb.setBounds(60, 30, 100, 100);
        panel.add(picLb);

        title = new JLabel("Foods List");
        title.setFont(new Font("Arial", Font.BOLD, 26));
        title.setBounds(240, 60, 300, 50);
        title.setForeground(Color.white);
        panel.add(title);

        l1 = new JLabel("Saturday: ");
        l1.setForeground(Color.white);
        l1.setFont(new Font("Arial", Font.CENTER_BASELINE, 14));
        l1.setBounds(20, 170, 100, 30);
        l1.setHorizontalAlignment(JLabel.RIGHT);
        panel.add(l1);

        l2 = new JLabel("Sunday: ");
        l2.setForeground(Color.white);
        l2.setFont(new Font("Arial", Font.CENTER_BASELINE, 14));
        l2.setBounds(20, 210, 100, 30);
        l2.setHorizontalAlignment(JLabel.RIGHT);
        panel.add(l2);

        l3 = new JLabel("Monday: ");
        l3.setForeground(Color.white);
        l3.setFont(new Font("Arial", Font.CENTER_BASELINE, 14));
        l3.setBounds(20, 250, 100, 30);
        l3.setHorizontalAlignment(JLabel.RIGHT);
        panel.add(l3);

        l4 = new JLabel("Tuesday: ");
        l4.setForeground(Color.white);
        l4.setFont(new Font("Arial", Font.CENTER_BASELINE, 14));
        l4.setBounds(20, 290, 100, 30);
        l4.setHorizontalAlignment(JLabel.RIGHT);
        panel.add(l4);

        l5 = new JLabel("Wednesday: ");
        l5.setForeground(Color.white);
        l5.setFont(new Font("Arial", Font.CENTER_BASELINE, 14));
        l5.setBounds(20, 330, 100, 30);
        l5.setHorizontalAlignment(JLabel.RIGHT);
        panel.add(l5);

        l6 = new JLabel("Thursday: ");
        l6.setForeground(Color.white);
        l6.setFont(new Font("Arial", Font.CENTER_BASELINE, 14));
        l6.setBounds(20, 370, 100, 30);
        l6.setHorizontalAlignment(JLabel.RIGHT);
        panel.add(l6);

        l7 = new JLabel("Friday: ");
        l7.setForeground(Color.white);
        l7.setFont(new Font("Arial", Font.CENTER_BASELINE, 14));
        l7.setBounds(20, 410, 100, 30);
        l7.setHorizontalAlignment(JLabel.RIGHT);
        panel.add(l7);

        /**
         * make text field
         */
        t1 = new JTextField();
        t1.setBorder(null);
        t1.setBackground(new Color(129,212,250));
        t1.setBounds(125, 170, 130, 30);
        panel.add(t1);

        t2 = new JTextField();
        t2.setBorder(null);
        t2.setBackground(new Color(129,212,250));
        t2.setBounds(125, 210, 130, 30);
        panel.add(t2);

        t3 = new JTextField();
        t3.setBorder(null);
        t3.setBackground(new Color(129,212,250));
        t3.setBounds(125, 250, 130, 30);
        panel.add(t3);

        t4 = new JTextField();
        t4.setBorder(null);
        t4.setBackground(new Color(129,212,250));
        t4.setBounds(125, 290, 130, 30);
        panel.add(t4);

        t5 = new JTextField();
        t5.setBorder(null);
        t5.setBackground(new Color(129,212,250));
        t5.setBounds(125, 330, 130, 30);
        panel.add(t5);

        t6 = new JTextField();
        t6.setBorder(null);
        t6.setBackground(new Color(129,212,250));
        t6.setBounds(125, 370, 130, 30);
        panel.add(t6);

        l8 = new JLabel("Closed!");
        l8.setForeground(Color.white);
        l8.setFont(new Font("Arial", Font.BOLD, 14));
        l8.setBounds(125, 410, 130, 30);
        panel.add(l8);

        textArea = new JTextArea();
        textArea.setBounds(270 , 170, 200, 280);
        textArea.setEditable(false);
        addList();
        panel.add(textArea);

        /**
         * make button
         */
        update = new JButton("Update");
        update.setBounds(125, 460, 100, 40);
        update.setBackground(new Color(142,36,170));
        update.setForeground(Color.white);
        update.setBorder(null);
        update.addActionListener(new ActionListener() {
            /**
             * @param e
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                updateAction();
            }
        });
        panel.add(update);

        close = new JButton("Close");
        close.setBounds(230, 460, 100, 40);
        close.setBackground(new Color(142,36,170));
        close.setForeground(Color.white);
        close.setBorder(null);
        close.addActionListener(new ActionListener() {
            /**
             * @param e
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        panel.add(close);

        this.add(panel);
        this.setVisible(true);
    }

    /**
     * read food list and added to textarea
     */
    private void addList(){

        try {
            File myFile = new File(Path.food);
            Scanner myReader = new Scanner(myFile);

            while (myReader.hasNextLine()){
                textArea.setText(textArea.getText() + myReader.nextLine() + "\n");
            }
        } catch (FileNotFoundException e) {
            System.out.println("admin file NOT Find !");
        }
    }

    /**
     * check the blank
     * @return true or false
     */
    private boolean CheckForBlanks(){
        return t1.getText().isEmpty() || t2.getText().isEmpty() || t3.getText().isEmpty() || t4.getText().isEmpty()
                || t5.getText().isEmpty() || t6.getText().isEmpty();
    }

    /**
     * update the action
     */
    private void updateAction(){

        if (CheckForBlanks()){
            JOptionPane.showMessageDialog(this,"You must Fill All Fields", "Empty Field(s)", JOptionPane.WARNING_MESSAGE);
            return;
        }


        try {
            File myFile = new File(Path.food);
            PrintWriter writer = new PrintWriter(myFile);
            writer.print("");

            String temp = l1.getText() + t1.getText() + "\n";
            temp += l2.getText() + t2.getText() + "\n";
            temp += l3.getText() + t3.getText() +"\n";
            temp += l4.getText() + t4.getText() + "\n";
            temp += l5.getText() + t5.getText() + "\n";
            temp += l6.getText() + t6.getText() + "\n";

            writer.print(temp);
            writer.close();

            JOptionPane.showMessageDialog(this,"List Updated Successfully.", "Updated", JOptionPane.WARNING_MESSAGE);
            textArea.setText("");
            addList();
            setStudentsReserveToZero();

        } catch (FileNotFoundException e) {
            System.out.println("admin file NOT Find !");
        }
    }

    /**
     * when admin change the food list the students food reserve will expires this method do that
     */
    public void setStudentsReserveToZero(){

        Student s[] = new Student[100000];
        int cS = 0;

        try {

            File myFile = new File(Path.student);
            Scanner myReader = new Scanner(myFile);

            while (myReader.hasNext()){
                int id = myReader.nextInt();
                String user = myReader.next();
                String password = myReader.next();

                s[cS] = new Student(id,user, password);
                cS++;
                myReader.nextLine();
            }

            for (int i = 0; i < cS; i++){
                    s[i].setFoodReserved(new int[6]);
            }

            myReader.close();
            PrintWriter writer = new PrintWriter(myFile);
            writer.print("");

            for (int i = 0; i < cS; i++) {
                writer.println(s[i].FullInformation());
            }

            writer.close();

        } catch (FileNotFoundException e) {
            System.out.println("admin file NOT Find !");
        }


    }
}
