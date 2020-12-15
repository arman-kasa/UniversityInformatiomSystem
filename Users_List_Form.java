import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Users_List_Form extends JFrame {

    /**
     * variables
     */
    private JPanel          panel;
    private JLabel          title, picLb;
    private JTextArea       textArea;
    private BufferedImage   Picture;
    private Account_Type    account_type;
    private JScrollPane     scrollPane;
    private User[]          users;
    private int             cUsers;

    /**
     * constructor
     * @param account_type
     */
    public Users_List_Form(Account_Type account_type){
        this.account_type = account_type;

        try {
            Picture = ImageIO.read(this.getClass().getResource("Images/account_type.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.setSize(600, 700);
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
        panel.setBackground(new Color(0,131,143));
        panel.setBounds(0, 0, this.getWidth(), this.getHeight());

        /**
         * make label
         */
        picLb = new JLabel(new ImageIcon(Picture));
        picLb.setBounds(60, 30, 100, 100);
        panel.add(picLb);

        if (account_type == Account_Type.Student){
            title = new JLabel("Students List");
        }else if (account_type == Account_Type.Teacher){
            title = new JLabel("Teachers List");
        }
        title.setFont(new Font("Arial", Font.BOLD, 26));
        title.setBounds(240, 60, 300, 50);
        title.setForeground(Color.white);
        panel.add(title);

        /**
         * make text area
         */
        textArea = new JTextArea();
        textArea.setEditable(false);
        addList();

        System.out.println(textArea.getRows());
        scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(20, 170, 540, 480);
        panel.add(scrollPane);

        this.add(panel);
        this.setVisible(true);
    }

    /**
     * add to list
     */
    private void addList(){
        users = new User[100000];
        cUsers = 0;

        switch (account_type){
            case Student ->{

                try {
                    File myFile = new File(Path.student);
                    Scanner myReader = new Scanner(myFile);

                    while (myReader.hasNext()){
                        users[cUsers] = new Student(myReader.nextInt(), myReader.next(), myReader.next());
                        cUsers++;
                        myReader.nextLine();
                    }
                    textArea.setText("id\tusername\tpassword\tAverage\tMoney\tvahed\n");
                    for (int i = 0; i < cUsers; i++){
                        textArea.setText(textArea.getText() + users[i].ListInformation() +"\n");
                    }

                } catch (FileNotFoundException e) {
                    System.out.println("admin file NOT Find !");
                }
                break;
            }
            case Teacher -> {

                try {
                    File myFile = new File(Path.teacher);
                    Scanner myReader = new Scanner(myFile);

                    while (myReader.hasNext()){
                        users[cUsers] = new Teacher(myReader.nextInt(), myReader.next(),myReader.next());
                        cUsers++;
                        myReader.nextLine();
                    }

                    textArea.setText("id\tusername\tpassword\n");
                    for (int i = 0; i < cUsers; i++){
                        textArea.setText(textArea.getText() + users[i].ListInformation() +"\n");
                    }

                } catch (FileNotFoundException e) {
                    System.out.println("admin file NOT Find !");
                }
                break;
            }
            }
        }
    }

