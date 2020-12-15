import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Teacher_Main_Form extends JFrame {

    /**
     * variables
     */
    private Teacher           user;
    private JMenuBar        MenuBar;
    private JMenu           Menu;
    private JMenuItem       passChange,addClass, removeClass,Students, Exit, userChange;
    private BufferedImage loginPicture;

    /**
     * constructor
     * @param user
     */
    public Teacher_Main_Form(Teacher user) {

        this.user = user;

        try {
            loginPicture = ImageIO.read(this.getClass().getResource("Images/teacher_icon.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(3);
        this.setTitle("Teacher Main Form");
        this.setSize(800, 500);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setIconImage(loginPicture);

        /**
         * make menu
         */
        MenuBar = new JMenuBar();
        MenuBar.setBounds(0, 0, this.getWidth(), 50);

        Menu = new JMenu("Options");

        addClass = new JMenuItem("Add new Class");
        addClass.addActionListener(new ActionListener() {
            /**
             * @param e
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                new Add_New_Class_Form(user);
            }
        });
        Menu.add(addClass);

        removeClass = new JMenuItem("remove Class");
        removeClass.addActionListener(new ActionListener() {
            /**
             * @param e
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                new Remove_Class_Form(user);
            }
        });
        Menu.add(removeClass);

        Students = new JMenuItem("Students");
        Students.addActionListener(new ActionListener() {
            /**
             * @param e
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                new Teacher_Students_List_Form(user);
            }
        });
        Menu.add(Students);

        passChange = new JMenuItem("Change Password");
        passChange.addActionListener(new ActionListener() {
            /**
             * @param e
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                new Password_Change_Form(user.getUserName(), user.getPassword(), Account_Type.Teacher);
            }
        });
        Menu.add(passChange);

        userChange = new JMenuItem("Change Username");
        userChange.addActionListener(new ActionListener() {
            /**
             * @param e
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                new Change_Username_Form(user, Account_Type.Teacher);
            }
        });
        Menu.add(userChange);

        Exit = new JMenuItem("Exit");
        Exit.addActionListener(new ActionListener() {
            /**
             * @param e
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                exitAction();
            }
        });
        Menu.add(Exit);

        MenuBar.add(Menu);
        this.setJMenuBar(MenuBar);
        this.add(new Main_Panel(this.getWidth(), this.getHeight()));
        this.setVisible(true);
    }

    /**
     * exit function
     */
    private void exitAction(){
        int value = JOptionPane.showConfirmDialog(this, "Do you Want to Exit From your account?" , "Exit", JOptionPane.YES_NO_OPTION);

        if (value == JOptionPane.YES_OPTION){
            new LogIn_Form();
            this.dispose();
        }
    }
}
