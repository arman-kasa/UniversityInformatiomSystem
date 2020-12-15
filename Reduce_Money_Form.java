import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Scanner;

public class Reduce_Money_Form extends JFrame {

    /**
     * variables
     */
    private JPanel          panel;
    private JLabel          title, picLb;
    private BufferedImage   Picture;
    private JLabel          cardNumberLb, cardPassLb, moneyLb;
    private JTextField      cardNumberTF, cardPassTF, moneyTf;
    private JButton         okBTN;
    private Student          student;

    /**
     * constructor
     * @param student
     */
    public Reduce_Money_Form(Student student) {
        this.student = student;

        try {
            Picture = ImageIO.read(this.getClass().getResource("Images/account_type.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.setSize(500, 450);
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
        panel.setBackground(new Color(238,238,2));
        panel.setBounds(0, 0, this.getWidth(), this.getHeight());

        /**
         * make label
         */
        picLb = new JLabel(new ImageIcon(Picture));
        picLb.setBounds(60, 30, 100, 100);
        panel.add(picLb);

        title = new JLabel("Add Money");
        title.setFont(new Font("Arial", Font.BOLD, 26));
        title.setBounds(240, 60, 300, 50);
        title.setForeground(Color.BLACK);
        panel.add(title);

        cardNumberLb = new JLabel("Card Number: ");
        cardNumberLb.setForeground(Color.BLACK);
        cardNumberLb.setFont(new Font("Arial", Font.CENTER_BASELINE, 14));
        cardNumberLb.setBounds(70, 200, 100, 30);
        cardNumberLb.setHorizontalAlignment(JLabel.RIGHT);
        panel.add(cardNumberLb);

        cardPassLb = new JLabel("Card Pass: ");
        cardPassLb.setForeground(Color.BLACK);
        cardPassLb.setFont(new Font("Arial", Font.CENTER_BASELINE, 14));
        cardPassLb.setBounds(70, 240, 100, 30);
        cardPassLb.setHorizontalAlignment(JLabel.RIGHT);
        panel.add(cardPassLb);

        moneyLb = new JLabel("Money: ");
        moneyLb.setForeground(Color.BLACK);
        moneyLb.setFont(new Font("Arial", Font.CENTER_BASELINE, 14));
        moneyLb.setBounds(70, 280, 100, 30);
        moneyLb.setHorizontalAlignment(JLabel.RIGHT);
        panel.add(moneyLb);

        /**
         * make text field
         */
        cardNumberTF = new JTextField();
        cardNumberTF.setBorder(null);
        cardNumberTF.setBackground(new Color(75,20,252));
        cardNumberTF.setBounds(180, 200, 200, 30);
        cardNumberTF.setForeground(Color.white);
        panel.add(cardNumberTF);

        cardPassTF = new JTextField();
        cardPassTF.setBorder(null);
        cardPassTF.setBackground(new Color(75,20,252));
        cardPassTF.setBounds(180, 240, 200, 30);
        cardPassTF.setForeground(Color.white);
        panel.add(cardPassTF);

        moneyTf = new JTextField();
        moneyTf.setBorder(null);
        moneyTf.setBackground(new Color(75,20,252));
        moneyTf.setBounds(180, 280, 200, 30);
        moneyTf.setForeground(Color.white);
        panel.add(moneyTf);

        /**
         * make button
         */
        okBTN = new JButton("Add Money");
        okBTN.setBounds(170, 350, 140, 40);
        okBTN.setBorder(null);
        okBTN.setBackground(new Color(2,238,238));
        okBTN.addActionListener(new ActionListener() {
            /**
             * @param e
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                action();
            }
        });
        panel.add(okBTN);


        this.add(panel);
        this.setVisible(true);
    }

    /**
     * add money to student account
     */
    public void action(){

        if (!isAllFill()){
            JOptionPane.showMessageDialog(this, "Please FIll All Fields.", "Empty Fields",JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (cardNumberTF.getText().length() != 16 || cardPassTF.getText().length() != 4  ){
            JOptionPane.showMessageDialog(this, "Card Number or Card Password is not correct", "Wrong Input",JOptionPane.WARNING_MESSAGE);
            return;
        }

        int m ;
        try{
            m = Integer.parseInt(moneyTf.getText());
        }catch (Exception e){
            JOptionPane.showMessageDialog(this, "You Must Enter numbers in Money !", "wrong Input", JOptionPane.WARNING_MESSAGE);
            return;
        }


        Student[] s = new Student[100000000];
        int cS = 0;

        try {
            File myFile = new File(Path.student);
            Scanner myReader = new Scanner(myFile);

            while (myReader.hasNext()){

                s[cS] = new Student(myReader.nextInt());
                cS++;
                myReader.nextLine();
            }



            for (int i = 0; i < cS; i++){
                if (s[i].getId() == this.student.getId()){
                    s[i].setHolding(s[i].getHolding() + m);
                    this.student.setHolding(s[i].getHolding());
                    break;
                }
            }

            Writer output;
            output = new BufferedWriter(new FileWriter(myFile));

            for (int i = 0; i < cS; i++){
                output.append(s[i].FullInformation()+"\n");
            }
            output.close();

            JOptionPane.showMessageDialog(this, "Money added Successfully.", "Task Done",JOptionPane.WARNING_MESSAGE);
            this.dispose();
            return;


        } catch (FileNotFoundException e) {
            System.out.println("admin file NOT Find !");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    /**
     * check that is all fill or not
     * @return true or false
     */
    public boolean isAllFill(){
        return !cardPassTF.getText().isEmpty() && !cardNumberTF.getText().isEmpty() && !moneyTf.getText().isEmpty();
    }

}
