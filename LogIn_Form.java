import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class LogIn_Form extends JFrame {

    /**
     * variables
     */
    private JPanel          panel;
    private BufferedImage   loginPicture;
    private JLabel          picLB, AccountTypeLB, usernameLB, passLB, titleLB;
    private JComboBox       accountType;
    private JTextField      userText;
    private JPasswordField  passwordField;
    private JButton         logButton;

    /**
     * constructor
     */
    public LogIn_Form(){

        try {
            loginPicture = ImageIO.read(this.getClass().getResource("Images/Login.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.setSize(600 , 320);
        this.setLayout(null);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(3);
        this.setTitle("Login");
        this.setIconImage(loginPicture);

        /**
         * make panel
         */
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(84, 110, 122));
        panel.setBounds(0 , 0 , this.getWidth() , this.getHeight());

        /**
         * make label
         */
        titleLB = new JLabel("Login Form");
        titleLB.setFont(new Font("Arial", Font.BOLD, 26));
        titleLB.setBounds(220, 10, 300, 50);
        titleLB.setForeground(Color.white);
        panel.add(titleLB);

        picLB = new JLabel(new ImageIcon(loginPicture));
        picLB.setBounds(20, 60,200,200);
        panel.add(picLB);

        AccountTypeLB = new JLabel("Account Type:");
        AccountTypeLB.setForeground(Color.white);
        AccountTypeLB.setBounds(260, 70, 100, 40);
        AccountTypeLB.setFont(new Font("Arial",Font.CENTER_BASELINE, 14));
        AccountTypeLB.setHorizontalAlignment(JLabel.RIGHT);
        panel.add(AccountTypeLB);

        accountType = new JComboBox();
        accountType.addItem("Student");
        accountType.addItem("Teacher");
        accountType.addItem("Admin");
        accountType.setBorder(null);
        accountType.setBackground(new Color(0, 172, 193));
        accountType.setBounds(380, 75 ,180,30);
        panel.add(accountType);

        usernameLB = new JLabel("User Name:");
        usernameLB.setForeground(Color.white);
        usernameLB.setBounds(260, 110, 100, 40);
        usernameLB.setFont(new Font("Arial",Font.CENTER_BASELINE, 16));
        usernameLB.setHorizontalAlignment(JLabel.RIGHT);
        panel.add(usernameLB);

        /**
         * make text field
         */
        userText = new JTextField();
        userText.setBounds(380, 115,180,30);
        userText.setBorder(null);
        userText.setBackground(new Color(0, 172, 193));
        userText.setFont(new Font("Arial",Font.CENTER_BASELINE, 14));
        panel.add(userText);

        passLB = new JLabel("Password:");
        passLB.setForeground(Color.white);
        passLB.setBounds(260, 150, 100,40);
        passLB.setFont(new Font("Arial",Font.CENTER_BASELINE, 16));
        passLB.setHorizontalAlignment(JLabel.RIGHT);
        panel.add(passLB);

        passwordField = new JPasswordField();
        passwordField.setBorder(null);
        passwordField.setBounds(380, 155, 180,30);
        passwordField.setBackground(new Color(0, 172, 193));
        passwordField.setFont(new Font("Arial",Font.CENTER_BASELINE, 14));
        panel.add(passwordField);

        /**
         * make button
         */
        logButton = new JButton("Login");
        logButton.setBorder(null);
        logButton.setBounds(340, 210, 120,40);
        logButton.setBackground(new Color(105, 240, 174));
        logButton.addActionListener(new ActionListener() {
            /**
             * @param e
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                action();
            }
        });
        panel.add(logButton);

        this.add(panel);
        this.setVisible(true);
    }

    /**
     * check if there is any user with this info if there is transfer user to main Form
     */
    private void action(){

        boolean flag = false;
        User users[] = new User[1000000];
        int cUsers = 0;

        if (accountType.getSelectedIndex() == 0 ){

            try {

                File myObj = new File(Path.student);
                Scanner myReader = new Scanner(myObj);

                while (myReader.hasNext()){

                    int id = myReader.nextInt();
                    String user = myReader.next();
                    String pass = myReader.next();

                    String inputUser = userText.getText();
                    String inputPass = new String(passwordField.getPassword());

                    if (inputUser.equals(user) && inputPass.equals(pass) ){
                        new Student_Main_Form(new Student(id,user,pass));
                        this.dispose();
                        flag = true;
                    }
                    myReader.nextLine();
                }

                if (!flag) {
                    JOptionPane.showMessageDialog(this, "Wrong information please try Again", "Failed Login", JOptionPane.WARNING_MESSAGE);
                }

            } catch (FileNotFoundException e) {
                System.out.println("admin passwords file NOT Find !");
            }
        }else if (accountType.getSelectedIndex() == 1){

            try {

                File myObj = new File(Path.teacher);
                Scanner myReader = new Scanner(myObj);


                while (myReader.hasNext()){

                    int id = myReader.nextInt();
                    String user = myReader.next();
                    String pass = myReader.next();

                    String inputUser = userText.getText();
                    String inputPass = new String(passwordField.getPassword());

                    if (inputUser.equals(user) && inputPass.equals(pass) ){
                        new Teacher_Main_Form(new Teacher(id, user, pass));
                        this.dispose();
                        flag = true;
                    }

                    myReader.nextLine();
                }
                if (!flag)
                JOptionPane.showMessageDialog(this,"Wrong information please try Again", "Failed Login", JOptionPane.WARNING_MESSAGE);


            } catch (FileNotFoundException e) {
                System.out.println(" teacher file NOT Find !");
            }

        }else if (accountType.getSelectedIndex() == 2){

            try {

                File myObj = new File(Path.admin);
                Scanner myReader = new Scanner(myObj);

                while (myReader.hasNext()){

                    int id = myReader.nextInt();
                    String user = myReader.next();
                    String pass = myReader.next();

                    String inputUser = userText.getText();
                    String inputPass = new String(passwordField.getPassword());

                    if (inputUser.equals(user) && inputPass.equals(pass) ){
                        new Admin_Main_Form(new Admin(id, user, pass));
                        this.dispose();
                        flag = true;
                    }
                    myReader.nextLine();
                }
                if (!flag) {
                    JOptionPane.showMessageDialog(this, "Wrong information please try Again", "Failed Login", JOptionPane.WARNING_MESSAGE);
                }

            } catch (FileNotFoundException e) {
                System.out.println("admin file NOT Find !");
            }
        }

    }

}
