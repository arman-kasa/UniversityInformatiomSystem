import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Reserve_Food_Form extends JFrame {

    /**
     * variables
     */
    private JPanel          panel;
    private JLabel          title, picLb;
    private JLabel          l1, l2, l3, l4, l5, l6, l7, l8, money,lb;
    private JRadioButton    t1, t2, t3, t4, t5, t6;
    private JTextArea       textArea;
    private JButton         update, close;
    private BufferedImage   foodPicture;
    private Student         student;

    /**
     * constructor
     * @param student
     */
    public Reserve_Food_Form(Student student){

        this.student = student;

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

        title = new JLabel("Foods Reserve");
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

        t1 = new JRadioButton();
        t1.setBorder(null);
        t1.setBackground(new Color(0,131,143));
        t1.setBounds(125, 170, 130, 30);
        panel.add(t1);

        t2 = new JRadioButton();
        t2.setBorder(null);
        t2.setBackground(new Color(0,131,143));
        t2.setBounds(125, 210, 130, 30);
        panel.add(t2);

        t3 = new JRadioButton();
        t3.setBorder(null);
        t3.setBackground(new Color(0,131,143));
        t3.setBounds(125, 250, 130, 30);
        panel.add(t3);

        t4 = new JRadioButton();
        t4.setBorder(null);
        t4.setBackground(new Color(0,131,143));
        t4.setBounds(125, 290, 130, 30);
        panel.add(t4);

        t5 = new JRadioButton();
        t5.setBorder(null);
        t5.setBackground(new Color(0,131,143));
        t5.setBounds(125, 330, 130, 30);
        panel.add(t5);

        t6 = new JRadioButton();
        t6.setBorder(null);
        t6.setBackground(new Color(0,131,143));
        t6.setBounds(125, 370, 130, 30);
        panel.add(t6);

        l8 = new JLabel("Closed!");
        l8.setForeground(Color.white);
        l8.setFont(new Font("Arial", Font.BOLD, 14));
        l8.setBounds(125, 410, 130, 30);
        panel.add(l8);

        money = new JLabel("Your Money: " + student.getHolding());
        money.setForeground(Color.white);
        money.setFont(new Font("Arial", Font.CENTER_BASELINE, 14));
        money.setBounds(270, 130, 150, 30);
        panel.add(money);

        textArea = new JTextArea();
        textArea.setBounds(270 , 170, 200, 280);
        textArea.setEditable(false);
        addList();
        panel.add(textArea);

        if (student.getAverage() < 17){
            lb = new JLabel("5000T per Day");
        }else{
            lb = new JLabel("2500 per Day");
        }
        lb.setForeground(Color.white);
        lb.setFont(new Font("Arial", Font.BOLD, 14));
        lb.setBounds(270, 450, 100, 30);
        panel.add(lb);

        update = new JButton("Reserve");
        update.setBounds(125, 500, 100, 40);
        update.setBackground(new Color(142,36,170));
        update.setForeground(Color.white);
        update.setBorder(null);
        update.addActionListener(new ActionListener() {
            /**
             * @param e
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                action();
            }
        });
        panel.add(update);

        close = new JButton("Close");
        close.setBounds(230, 500, 100, 40);
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

        this.addReserved();
        this.add(panel);
        this.setVisible(true);
    }

    /**
     * add to list
     */
    private void addList(){

        try {
            File myFile = new File(Path.food);
            Scanner myReader = new Scanner(myFile);

            while (myReader.hasNextLine()){
                textArea.setText(textArea.getText() + myReader.nextLine() + "\n");
            }
        } catch (FileNotFoundException e) {
            System.out.println("food file NOT Find !");
        }
    }

    /**
     * reserve food
     */
    private void addReserved(){
        int i[] = student.getFoodReserved();

        if (i[0] == 1){
            t1.setSelected(true);
        }
        if (i[1] == 1){
            t2.setSelected(true);
        }
        if (i[2] == 1){
            t3.setSelected(true);
        }
        if (i[3] == 1){
            t4.setSelected(true);
        }
        if (i[4] == 1){
            t5.setSelected(true);
        }
        if (i[5] == 1){
            t6.setSelected(true);
        }

    }

    /**
     * base on what the student choose reserve food to his account
     */
    public void action(){

        Student s[] = new Student[100000];
        int cS = 0;

        int rBefor = howManyReserved(student.getFoodReserved());

        int x = howManySelected();

        if (student.getAverage() >= 17){
            if ((x - rBefor) * 2500 > student.getHolding()){
                JOptionPane.showMessageDialog(this, "You Dont Have Enough money!", "",JOptionPane.WARNING_MESSAGE);
                return;
            }
        }else{
            if ((x - rBefor) * 5000 > student.getHolding()){
                JOptionPane.showMessageDialog(this, "You Dont Have Enough money!", "",JOptionPane.WARNING_MESSAGE);
                return;
            }
        }


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
                if (s[i].getId() == student.getId()){
                    s[i].setFoodReserved(getReservation(s[i].getFoodReserved()));
                    student.setFoodReserved(getReservation(student.getFoodReserved()));

                    int rAfter = howManyReserved(student.getFoodReserved());
                    int count = rAfter - rBefor;

                    if (student.getAverage() >= 17){
                        student.setHolding(student.getHolding() -  (count * 2500));
                        s[i].setHolding(student.getHolding());
                    }else{
                        student.setHolding(student.getHolding() - (count * 5000));
                        s[i].setHolding(student.getHolding());
                    }
                    break;
                }
            }

            myReader.close();
            PrintWriter writer = new PrintWriter(myFile);
            writer.print("");

            for (int i = 0; i < cS; i++) {
                writer.println(s[i].FullInformation());
            }

            writer.close();

            JOptionPane.showMessageDialog(this, "Reserved Successfully", "reserved", JOptionPane.INFORMATION_MESSAGE);

        } catch (FileNotFoundException e) {
            System.out.println("admin file NOT Find !");
        }



        money.setText("Your Money: " + student.getHolding());
    }

    /**
     * get reservation
     * @param i
     * @return reservation
     */
    public int[] getReservation(int [] i){

        if (t1.isSelected()){
            i[0] = 1;
        }
        if (t2.isSelected()){
            i[1] = 1;
        }if (t3.isSelected()){
            i[2] = 1;
        }if (t4.isSelected()){
            i[3] = 1;
        }if (t5.isSelected()){
            i[4] = 1;
        }if (t6.isSelected()){
            i[5] = 1;
        }
        return i;
    }

    /**
     * check how many meal student has reserved
     * @param l
     * @return how many reserved
     */
    public int howManyReserved(int l[]){
        int t = 0;
        for ( int i = 0; i < 6; i++){
            if (l[i] == 1){
                t++;
            }
        }
        return t;
    }

    /**
     * @return how many selected
     */
    public int howManySelected(){
        int t = 0;
        if (t1.isSelected()){
            t++;
        }
        if (t2.isSelected()){
            t++;
        }if (t3.isSelected()){
            t++;
        }if (t4.isSelected()){
            t++;
        }if (t5.isSelected()){
            t++;
        }if (t6.isSelected()){
            t++;
        }
        return t;
    }

}
