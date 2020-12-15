import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Scanner;

public class Add_New_Teacher_Form extends JFrame {

    /**
     * variables
     */
    private JPanel          panel;
    private JLabel          title, picLb;
    private BufferedImage   Picture;
    private JLabel          idLb, usernameLb, passwordLb,vahedHaLb;
    private JTextField      idTF, usernameTF, passwordTf;
    private JTextArea       vahedHaTA;
    private JScrollPane     scrollPane;
    private JButton         okBTN;

    /**
     * constructor
     */
    public Add_New_Teacher_Form() {

        try {
            Picture = ImageIO.read(this.getClass().getResource("Images/account_type.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        /**
         * make user fundamental
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
        panel.setBackground(new Color(66, 66, 66));
        panel.setBounds(0, 0, this.getWidth(), this.getHeight());

        /**
         * add label
         */
        picLb = new JLabel(new ImageIcon(Picture));
        picLb.setBounds(60, 30, 100, 100);
        panel.add(picLb);

        title = new JLabel("New Teacher");
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

        vahedHaLb = new JLabel("vahed ha: ");
        vahedHaLb.setForeground(Color.white);
        vahedHaLb.setFont(new Font("Arial", Font.CENTER_BASELINE, 14));
        vahedHaLb.setBounds(70, 320, 100, 30);
        vahedHaLb.setHorizontalAlignment(JLabel.RIGHT);
        panel.add(vahedHaLb);

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

        vahedHaTA = new JTextArea();
        vahedHaTA.setBorder(null);
        vahedHaTA.setBackground(new Color(94,53,177));
        vahedHaTA.setBounds(180, 320, 200, 100);
        vahedHaTA.setForeground(Color.white);

        /**
         * make scroll panel
         */
        scrollPane = new JScrollPane(vahedHaTA);
        scrollPane.setBounds(180, 320, 200,100);
        panel.add(scrollPane);

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
        String[] v;
        v = vahedHaTA.getText().split("\n");
        int id ;

        for (int i = 0; i < v.length; i++){
            System.out.println(v[i]);
        }

        try{
            id = Integer.parseInt(idTF.getText());

        }catch (Exception e){
            JOptionPane.showMessageDialog(this, "You Must Enter numbers in id !", "wrong Input", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (isItTeacherWithTHisID(id)){
            JOptionPane.showMessageDialog(this, "There is already a student with this id.", "duplicated id ", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Teacher t = new Teacher(id, user, pass, v,new Student[0] );

        try {

            File myFile = new File(Path.teacher);
            Writer output;
            output = new BufferedWriter(new FileWriter(myFile, true));
            output.append(t.FullInformation()+"\n");
            output.close();

            JOptionPane.showMessageDialog(this, "Teacher added Successfully.", "Task Done",JOptionPane.WARNING_MESSAGE);
            this.dispose();
            return;

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    /**
     * check emptiness of inputs
     * @return true or flase
     */
    public boolean isAllFill(){
        return !idTF.getText().isEmpty() && !usernameTF.getText().isEmpty() && !passwordTf.getText().isEmpty()
                && !vahedHaTA.getText().isEmpty() ;
    }

    /**
     * check the input
     * @return true or flase
     */
    public boolean isItOk(){

        String[] s1 = usernameTF.getText().split(" ");
        String[] s2 = passwordTf.getText().split(" ");
        String[] s3 = idTF.getText().split(" ");

        return s1.length > 1 || s2.length > 1 || s3.length > 1;
    }

    /**
     * check if there is any teacher with this id or not
     * @param id
     * @return true or false
     */
    public boolean isItTeacherWithTHisID(int id){

        boolean flag = false;

        try {
            File myFile = new File(Path.teacher);
            Scanner myReader = new Scanner(myFile);

            while (myReader.hasNext()){

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
