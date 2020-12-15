import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Student_Main_Form extends JFrame {

    /**
     * variables
     */
    private Student         user;
    private JMenuBar        MenuBar;
    private JMenu           Menu;
    private JMenuItem       passChange,getClass, reduceHolding,reserveFood, Exit, userChange , info;
    private BufferedImage   Picture;

    /**
     * constructor
     * @param user
     */
    public Student_Main_Form(Student user) {

        this.user = user;

        try {
            Picture = ImageIO.read(this.getClass().getResource("Images/Student_icon.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(3);
        this.setTitle("Teacher Main Form");
        this.setSize(800, 500);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setIconImage(Picture);

        MenuBar = new JMenuBar();
        MenuBar.setBounds(0, 0, this.getWidth(), 50);

        Menu = new JMenu("Options");

        getClass = new JMenuItem("get new Class");
        getClass.addActionListener(new ActionListener() {
            /**
             * @param e
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                new Get_Class_Form(user);
            }
        });
        Menu.add(getClass);

        reduceHolding = new JMenuItem("Reduce Money");
        reduceHolding.addActionListener(new ActionListener() {
            /**
             * @param e
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                new Reduce_Money_Form(user);
            }
        });
        Menu.add(reduceHolding);

        reserveFood = new JMenuItem("Reserve Food");
        reserveFood.addActionListener(new ActionListener() {
            /**
             * @param e
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                new Reserve_Food_Form(user);
            }
        });
        Menu.add(reserveFood);

        passChange = new JMenuItem("Change Password");
        passChange.addActionListener(new ActionListener() {
            /**
             * @param e
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                new Password_Change_Form(user.getUserName(), user.getPassword(), Account_Type.Student);
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

        info = new JMenuItem("Your Info");
        info.addActionListener(new ActionListener() {
            /**
             * @param e
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                new Students_Information_Form(user);
            }
        });
        Menu.add(info);

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
