
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


public class Password_Change_Form extends JFrame {

    /**
     * variables
     */
    private String          username, Password;
    private Account_Type    account_type;
    private JPanel          panel;
    private BufferedImage   passPicture;
    private JLabel          picLB, lastpass, newPass, newPass2, titleLB;
    private JTextField      LPassTxt, NPassTxt, NPassTxt2;
    private JButton         Button;
    private User[]          users;

    /**
     * constructor
     * @param username
     * @param password
     * @param account_type
     */
    public Password_Change_Form(String username, String password, Account_Type account_type) {
        this.username = username;
        this.Password = password;
        this.account_type = account_type;

        try {
            passPicture = ImageIO.read(this.getClass().getResource("Images/password_icon.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.setSize(700, 320);
        this.setLayout(null);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("Change Password");
        this.setIconImage(passPicture);

        /**
         * make panel
         */
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(142, 36, 170));
        panel.setBounds(0, 0, this.getWidth(), this.getHeight());

        /**
         * make label
         */
        titleLB = new JLabel("Change Password Form");
        titleLB.setFont(new Font("Arial", Font.BOLD, 26));
        titleLB.setBounds(170, 10, 300, 50);
        titleLB.setForeground(Color.white);
        panel.add(titleLB);

        picLB = new JLabel(new ImageIcon(passPicture));
        picLB.setBounds(20, 60, 200, 200);
        panel.add(picLB);

        lastpass = new JLabel("Current Password:");
        lastpass.setForeground(Color.white);
        lastpass.setBounds(260, 70, 170, 40);
        lastpass.setFont(new Font("Arial", Font.CENTER_BASELINE, 14));
        lastpass.setHorizontalAlignment(JLabel.RIGHT);
        panel.add(lastpass);

        /**
         * make text field
         */
        LPassTxt = new JTextField();
        LPassTxt.setBorder(null);
        LPassTxt.setBackground(new Color(0, 172, 193));
        LPassTxt.setBounds(450, 75, 180, 30);
        panel.add(LPassTxt);

        newPass = new JLabel("New Password:");
        newPass.setForeground(Color.white);
        newPass.setBounds(260, 110, 170, 40);
        newPass.setFont(new Font("Arial", Font.CENTER_BASELINE, 16));
        newPass.setHorizontalAlignment(JLabel.RIGHT);
        panel.add(newPass);

        NPassTxt = new JTextField();
        NPassTxt.setBounds(450, 115, 180, 30);
        NPassTxt.setBorder(null);
        NPassTxt.setBackground(new Color(0, 172, 193));
        NPassTxt.setFont(new Font("Arial", Font.CENTER_BASELINE, 14));
        panel.add(NPassTxt);

        newPass2 = new JLabel("Confirm New Password:");
        newPass2.setForeground(Color.white);
        newPass2.setBounds(260, 150, 170, 40);
        newPass2.setFont(new Font("Arial", Font.CENTER_BASELINE, 14));
        newPass2.setHorizontalAlignment(JLabel.RIGHT);
        panel.add(newPass2);

        NPassTxt2 = new JTextField();
        NPassTxt2.setBorder(null);
        NPassTxt2.setBounds(450, 155, 180, 30);
        NPassTxt2.setBackground(new Color(0, 172, 193));
        NPassTxt2.setFont(new Font("Arial", Font.CENTER_BASELINE, 14));
        panel.add(NPassTxt2);

        /**
         * make button
         */
        Button = new JButton("Change");
        Button.setBorder(null);
        Button.setBounds(340, 210, 120, 40);
        Button.setBackground(new Color(244, 143, 177));
        Button.addActionListener(new ActionListener() {
            /**
             * @param e
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                Action();
            }
        });
        panel.add(Button);

        this.add(panel);
        this.setVisible(true);
    }

    /**
     * base on type of user search the specific file and if there was no problem set the changes in to files
     */
    public void Action(){

        if (NPassTxt.getText().length() < 8){
            JOptionPane.showMessageDialog(this,"Your Password length Should be at leas '8'!", "Short Password", JOptionPane.WARNING_MESSAGE);
            return;
        }

        users = new User[10000000];
        int cUsers = 0;
        boolean flag = false;

        switch (account_type){

            case Admin:

                try {

                    File myFile = new File(Path.admin);
                    Scanner myReader = new Scanner(myFile);

                    while (myReader.hasNext()){
                        int id = myReader.nextInt();
                        String user = myReader.next();
                        String password = myReader.next();

                        users[cUsers] = new Admin(id,user, password);
                        cUsers++;
                        myReader.nextLine();
                    }


                    for (int i = 0; i < cUsers; i++){

                        if (username.equals(users[i].getUserName()) && this.Password.equals(LPassTxt.getText())){
                            if (NPassTxt.getText().equals(NPassTxt2.getText())){
                                users[i].setPassword(NPassTxt.getText());
                                flag = true;
                            }else{
                                JOptionPane.showMessageDialog(this,"New Password dose not match. ", "Wrong Password", JOptionPane.WARNING_MESSAGE);
                            }
                        }

                        if (flag) {
                            break;
                        }
                    }

                    myReader.close();

                    PrintWriter writer = new PrintWriter(myFile);
                    writer.print("");

                    for (int i = 0; i < cUsers; i++) {
                        writer.println(users[i].FullInformation());
                    }

                    writer.close();


                    if (!flag) {
                        JOptionPane.showMessageDialog(this, "Please Enter your Info Correctly.", "Wrong Information", JOptionPane.WARNING_MESSAGE);
                    }else {
                        JOptionPane.showMessageDialog(this,"Password Changed Successfully.", "Password Changed", JOptionPane.INFORMATION_MESSAGE);
                        this.dispose();
                    }

                } catch (FileNotFoundException e) {
                    System.out.println("admin file NOT Find !");
                }
            break;
            case Student:

                try {

                    File myFile = new File(Path.student);
                    Scanner myReader = new Scanner(myFile);

                    while (myReader.hasNextLine()){
                        int id = myReader.nextInt();
                        String user = myReader.next();
                        String password = myReader.next();

                        users[cUsers] = new Student(id,user, password);
                        cUsers++;
                        myReader.nextLine();
                    }

                    for (int i = 0; i < cUsers; i++){

                        if (username.equals(users[i].getUserName()) && this.Password.equals(LPassTxt.getText())){
                            if (NPassTxt.getText().equals(NPassTxt2.getText())){
                                users[i].setPassword(NPassTxt.getText());
                                flag = true;
                            }else{
                                JOptionPane.showMessageDialog(this,"New Password dose not match. ", "Wrong Password", JOptionPane.WARNING_MESSAGE);
                            }
                        }

                        if (flag) {
                            break;
                        }
                    }

                    myReader.close();

                    PrintWriter writer = new PrintWriter(myFile);
                    writer.print("");

                    for (int i = 0; i < cUsers; i++) {
                        writer.println(users[i].FullInformation());
                    }

                    writer.close();


                    if (!flag) {
                        JOptionPane.showMessageDialog(this, "Please Enter your Info Correctly.", "Wrong Information", JOptionPane.WARNING_MESSAGE);
                    }else {
                        JOptionPane.showMessageDialog(this,"Password Changed Successfully.", "Password Changed", JOptionPane.INFORMATION_MESSAGE);
                        this.dispose();
                    }

                } catch (FileNotFoundException e) {
                    System.out.println("admin file NOT Find !");
                }
            break;
            case Teacher:

                try {

                    File myFile = new File(Path.teacher);
                    Scanner myReader = new Scanner(myFile);

                    while (myReader.hasNext()){
                        int id = myReader.nextInt();
                        String user = myReader.next();
                        String password = myReader.next();

                        users[cUsers] = new Teacher(id,user, password);
                        cUsers++;
                        myReader.nextLine();
                    }


                    for (int i = 0; i < cUsers; i++){

                        if (username.equals(users[i].getUserName()) && this.Password.equals(LPassTxt.getText())){
                            if (NPassTxt.getText().equals(NPassTxt2.getText())){
                                users[i].setPassword(NPassTxt.getText());
                                flag = true;
                            }else{
                                JOptionPane.showMessageDialog(this,"New Password dose not match. ", "Wrong Password", JOptionPane.WARNING_MESSAGE);
                            }
                        }

                        if (flag) {
                            break;
                        }
                    }

                    myReader.close();

                    PrintWriter writer = new PrintWriter(myFile);
                    writer.print("");

                    for (int i = 0; i < cUsers; i++) {
                        writer.println(users[i].FullInformation());
                    }

                    writer.close();


                    if (!flag) {
                        JOptionPane.showMessageDialog(this, "Please Enter your Info Correctly.", "Wrong Information", JOptionPane.WARNING_MESSAGE);
                    }else {
                        JOptionPane.showMessageDialog(this,"Password Changed Successfully.", "Password Changed", JOptionPane.INFORMATION_MESSAGE);
                        this.dispose();
                    }

                } catch (FileNotFoundException e) {
                    System.out.println("admin file NOT Find !");
                }
            break;
        }
    }



}
