import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Admin_Main_Form extends JFrame {

    /**
     * variables
     */
    private Admin           user;
    private JMenuBar        MenuBar;
    private JMenu           Menu;
    private JMenuItem       passChange, foodList, classList, teacherList, studentList, addNewTeacher,addNewStudent, Exit, userChange;
    private BufferedImage   loginPicture;

    /**
     * constructor
     * @param user
     */
    public Admin_Main_Form(Admin user) {

        this.user = user;

        try {
            loginPicture = ImageIO.read(this.getClass().getResource("Images/Admin_Icon.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(3);
        this.setTitle("Admin Main Form");
        this.setSize(800, 500);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setIconImage(loginPicture);

        /**
         * make menubar
         */
        MenuBar = new JMenuBar();
        MenuBar.setBounds(0, 0, this.getWidth(), 50);

        /**
         * make menu
         */
        Menu = new JMenu("Options");

        /**
         * make item
         */
        foodList = new JMenuItem("Food List");
        foodList.addActionListener(new ActionListener() {
            /**
             * @param e
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                new Foods_Admin_Form();
            }
        });
        Menu.add(foodList);

        classList = new JMenuItem("Classes List");
        classList.addActionListener(new ActionListener() {
            /**
             * @param e
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                new Classes_List_Form();
            }
        });
        Menu.add(classList);

        studentList = new JMenuItem("Students List");
        studentList.addActionListener(new ActionListener() {
            /**
             * @param e
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                new Users_List_Form(Account_Type.Student);
            }
        });
        Menu.add(studentList);

        teacherList = new JMenuItem("Teachers List");
        teacherList.addActionListener(new ActionListener() {
            /**
             * @param e
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                new Users_List_Form(Account_Type.Teacher);
            }
        });
        Menu.add(teacherList);

        addNewTeacher = new JMenuItem("Add new Teacher");
        addNewTeacher.addActionListener(new ActionListener() {
            /**
             * @param e
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                new Add_New_Teacher_Form();
            }
        });
        Menu.add(addNewTeacher);

        addNewStudent = new JMenuItem("Add new Student");
        addNewStudent.addActionListener(new ActionListener() {
            /**
             * @param e
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                new Add_New_Student_Form();
            }
        });
        Menu.add(addNewStudent);

        passChange = new JMenuItem("Change Password");
        passChange.addActionListener(new ActionListener() {
            /**
             * @param e
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                new Password_Change_Form(user.getUserName(), user.getPassword(), Account_Type.Admin);
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
                new Change_Username_Form(user, Account_Type.Student);
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
