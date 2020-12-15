import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Scanner;

public class Add_New_Student_Form extends JFrame {

    /**
     * variables
     */
    private JPanel          panel;
    private JLabel          title, picLb;
    private BufferedImage   Picture;
    private JLabel          idLb, usernameLb, passwordLb, averageLb, holdingLb;
    private JTextField      idTF, usernameTF, passwordTf, averageTF, holdingTF;
    private JButton         okBTN;

    /**
     * constructor
     */
    public Add_New_Student_Form() {

        try {
            Picture = ImageIO.read(this.getClass().getResource("Images/account_type.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        /**
         * make user list fundamental
         */
        this.setSize(500, 550);
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
        panel.setBackground(new Color(66,66,66));
        panel.setBounds(0, 0, this.getWidth(), this.getHeight());

        /**
         * make label
         */
        picLb = new JLabel(new ImageIcon(Picture));
        picLb.setBounds(60, 30, 100, 100);
        panel.add(picLb);

        title = new JLabel("New Student");
        title.setFont(new Font("Arial", Font.BOLD, 26));
        title.setBounds(240, 60, 300, 50);
        title.setForeground(Color.white);
        panel.add(title);

        idLb = new JLabel("id: ");
        idLb.setForeground(Color.white);
        idLb.setFont(new Font("Arial", Font.CENTER_BASELINE, 14));
        idLb.setBounds(70, 200, 100, 30);
        idLb.setHorizontalAlignment(JLabel.RIGHT);
        panel.add(idLb);

        usernameLb = new JLabel("Username: ");
        usernameLb.setForeground(Color.white);
        usernameLb.setFont(new Font("Arial", Font.CENTER_BASELINE, 14));
        usernameLb.setBounds(70, 240, 100, 30);
        usernameLb.setHorizontalAlignment(JLabel.RIGHT);
        panel.add(usernameLb);

        passwordLb = new JLabel("Password: ");
        passwordLb.setForeground(Color.white);
        passwordLb.setFont(new Font("Arial", Font.CENTER_BASELINE, 14));
        passwordLb.setBounds(70, 280, 100, 30);
        passwordLb.setHorizontalAlignment(JLabel.RIGHT);
        panel.add(passwordLb);

        averageLb = new JLabel("Average: ");
        averageLb.setForeground(Color.white);
        averageLb.setFont(new Font("Arial", Font.CENTER_BASELINE, 14));
        averageLb.setBounds(70, 320, 100, 30);
        averageLb.setHorizontalAlignment(JLabel.RIGHT);
        panel.add(averageLb);

        holdingLb = new JLabel("Start Money: ");
        holdingLb.setForeground(Color.white);
        holdingLb.setFont(new Font("Arial", Font.CENTER_BASELINE, 14));
        holdingLb.setBounds(70, 360, 100, 30);
        holdingLb.setHorizontalAlignment(JLabel.RIGHT);
        panel.add(holdingLb);

        /**
         * make text field
         */
        idTF = new JTextField();
        idTF.setBorder(null);
        idTF.setBackground(new Color(94,53,177));
        idTF.setBounds(180, 200, 200, 30);
        idTF.setForeground(Color.white);
        panel.add(idTF);

        usernameTF = new JTextField();
        usernameTF.setBorder(null);
        usernameTF.setBackground(new Color(94,53,177));
        usernameTF.setBounds(180, 240, 200, 30);
        usernameTF.setForeground(Color.white);
        panel.add(usernameTF);

        passwordTf = new JTextField();
        passwordTf.setBorder(null);
        passwordTf.setBackground(new Color(94,53,177));
        passwordTf.setBounds(180, 280, 200, 30);
        passwordTf.setForeground(Color.white);
        panel.add(passwordTf);

        averageTF = new JTextField();
        averageTF.setBorder(null);
        averageTF.setBackground(new Color(94,53,177));
        averageTF.setBounds(180, 320, 200, 30);
        averageTF.setForeground(Color.white);
        panel.add(averageTF);

        holdingTF = new JTextField();
        holdingTF.setBorder(null);
        holdingTF.setBackground(new Color(94,53,177));
        holdingTF.setBounds(180, 360, 200, 30);
        holdingTF.setForeground(Color.white);
        panel.add(holdingTF);

        /**
         * make button
         */
        okBTN = new JButton("Add");
        okBTN.setBounds(170, 440, 140, 40);
        okBTN.setBorder(null);
        okBTN.setBackground(new Color(216,27,96));
        okBTN.addActionListener(new ActionListener() {
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
     * check several situations if there was no problem add new class to file
     */
    public void action(){

        if (!isAllFill()){
            JOptionPane.showMessageDialog(this, "Please FIll All Fields.", "Empty Fields",JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (passwordTf.getText().length() < 8){
            JOptionPane.showMessageDialog(this, "password must be longer than 8 character", "Short password",JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (usernameTF.getText().length() < 6  ){
            JOptionPane.showMessageDialog(this, "username must be longer than 6 character", "Short username",JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (isItOk()){
            JOptionPane.showMessageDialog(this, "Fields must not contain ' ' character", "two words in some Fields",JOptionPane.WARNING_MESSAGE);
            return;
        }

        String user = usernameTF.getText();
        String pass = passwordTf.getText();
        int id ,money;
        float av;

        try{
            id = Integer.parseInt(idTF.getText());
            av = Float.parseFloat(averageTF.getText());
            money = Integer.parseInt(holdingTF.getText());
        }catch (Exception e){
            JOptionPane.showMessageDialog(this, "You Must Enter numbers in id , average and money!", "wrong Input", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (isItStudentWithTHisID(id)){
            JOptionPane.showMessageDialog(this, "There is already a student with this id.", "duplicated id ", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int[] a = {0,0,0,0,0,0};
        int[] s = {};

        Student newS = new Student(id, user, pass, av, money,0, a, s );

        try {

            File myFile = new File(Path.student);
            Writer output;
            output = new BufferedWriter(new FileWriter(myFile, true));
            output.append(newS.FullInformation()+"\n");
            output.close();

            JOptionPane.showMessageDialog(this, "Student added Successfully.", "Task Done",JOptionPane.WARNING_MESSAGE);
            this.dispose();
            return;

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    /**
     * check inputs Emptiness
     * @return true or false
     */
    public boolean isAllFill(){
        return !idTF.getText().isEmpty() && !usernameTF.getText().isEmpty() && !passwordTf.getText().isEmpty()
                && !averageTF.getText().isEmpty() && !holdingTF.getText().isEmpty();
    }

    /**
     * check the inputs
     * @return true or false
     */
    public boolean isItOk(){

        String[] s1 = usernameTF.getText().split(" ");
        String[] s2 = passwordTf.getText().split(" ");
        String[] s3 = idTF.getText().split(" ");
        String[] s4 = averageTF.getText().split(" ");
        String[] s5 = holdingTF.getText().split(" ");

        return s1.length > 1 || s2.length > 1 || s3.length > 1 || s4.length > 1 || s5.length > 1;
    }

    /**
     * check if there are any student whit this id or not
     * @param id
     * @return true or false
     */
    public boolean isItStudentWithTHisID(int id){

        boolean flag = false;

        try {
            File myFile = new File(Path.student);
            Scanner myReader = new Scanner(myFile);

            while (myReader.hasNextLine()){

                if (myReader.nextInt() == id){
                    flag = true;
                    break;
                }

                myReader.nextLine();
            }


        } catch (FileNotFoundException e) {
            System.out.println("admin file NOT Find !");
        }
        return flag;
    }


}
