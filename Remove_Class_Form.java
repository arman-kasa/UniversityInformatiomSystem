import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Scanner;

public class Remove_Class_Form extends JFrame {

    /**
     * variables
     */
    private JPanel          panel;
    private JLabel          title, picLb;
    private BufferedImage   Picture;
    private JLabel          idLb;
    private JTextField      idTF;
    private JTextArea       textArea;
    private JScrollPane     scrollPane;
    private JButton         okBTN;
    private Teacher         teacher;

    /**
     * constructor
     * @param teacher
     */
    public Remove_Class_Form(Teacher teacher){
        this.teacher = teacher;

        try {
            Picture = ImageIO.read(this.getClass().getResource("Images/Class_icon.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.setSize(500, 600);
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
        panel.setBackground(new Color(177, 0, 106));
        panel.setBounds(0, 0, this.getWidth(), this.getHeight());

        /**
         * make label
         */
        picLb = new JLabel(new ImageIcon(Picture));
        picLb.setBounds(60, 30, 100, 100);
        panel.add(picLb);

        title = new JLabel("Remove Class");
        title.setFont(new Font("Arial", Font.BOLD, 26));
        title.setBounds(240, 60, 300, 50);
        title.setForeground(Color.white);
        panel.add(title);

        /**
         * make text area
         */
        textArea = new JTextArea();
        textArea.setBackground(new Color(92, 0, 210));
        textArea.setForeground(Color.white);
        textArea.setEditable(false);
        addList();

        /**
         * make scroll
         */
        scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(20, 170, 440, 250);
        panel.add(scrollPane);

        idLb = new JLabel("id: ");
        idLb.setForeground(Color.white);
        idLb.setFont(new Font("Arial", Font.CENTER_BASELINE, 14));
        idLb.setBounds(10, 440, 40, 30);
        idLb.setHorizontalAlignment(JLabel.RIGHT);
        panel.add(idLb);

        idTF = new JTextField();
        idTF.setBorder(null);
        idTF.setBackground(new Color(92, 0, 210));
        idTF.setBounds(60, 440, 100, 30);
        idTF.setForeground(Color.white);
        panel.add(idTF);

        /**
         * make button
         */
        okBTN = new JButton("remove");
        okBTN.setBounds(170, 490, 140, 40);
        okBTN.setBorder(null);
        okBTN.setBackground(new Color(0, 131, 143));
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
     * remove class info from classes file
     */
    public void action(){

        if (idTF.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Please FIll All Fields.", "Empty Fields", JOptionPane.WARNING_MESSAGE);
            return;
        }


        if (isItOk()) {
            JOptionPane.showMessageDialog(this, "Fields must not contain ' ' character", "two words in some Fields", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int id;

        try {
            id = Integer.parseInt(idTF.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "You Must Enter numbers in id!", "wrong Input", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Class[] classes = new Class[10000000];
        int cClasses = 0;

        try {
            File myFile = new File(Path.Class);
            Scanner myReader = new Scanner(myFile);

            while (myReader.hasNextLine()){
                classes[cClasses] = new Class(myReader.nextInt());
                cClasses++;
                myReader.nextLine();
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("file NOT Find !");
        }


        for (int i = 0; i < cClasses; i++){
            if (classes[i].getId() == id ){
                if (teacherHasClass(classes[i])) {
                    classes[i] = null;
                }else{
                    JOptionPane.showMessageDialog(this, "You have not a class with this id!", "invalid id", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }
        }

        try {

            File myFile = new File(Path.Class);
            Writer output;
            output = new BufferedWriter(new FileWriter(myFile));


            for (int i = 0; i < cClasses; i++){
                if (classes[i] != null){
                    output.append(classes[i].FullInformation()+"\n");
                }
            }

            output.close();

            JOptionPane.showMessageDialog(this, "class removed Successfully.", "Task Done",JOptionPane.WARNING_MESSAGE);
            textArea.setText("");
            addList();
            removeStudentsFromTeachers(id);
            removeClassFromStudents(id);

            return;

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    /**
     * @return true or false
     */
    public boolean isItOk() {

        String[] s1 = idTF.getText().split(" ");

        return s1.length > 1 ;
    }

    /**
     * add class to list
     */
    public void addList(){
        Class[] classes = new Class[10000000];
        int cClasses = 0;

        try {
            File myFile = new File(Path.Class);
            Scanner myReader = new Scanner(myFile);

            while (myReader.hasNextLine()){
                classes[cClasses] = new Class(myReader.nextInt());
                cClasses++;
                myReader.nextLine();
            }

            textArea.setText("id\tname\tvahed\tteacher\tDay\tTime\tcapacity\ttaken\n");
            for (int i = 0; i < cClasses; i++){
                if (classes[i].getTeacherId() == this.teacher.getId()) {
                    textArea.setText(textArea.getText() + classes[i].InfoForList() + "\n");
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("file NOT Find !");
        }

    }

    /**
     * check if teacher has class with this id
     * @param c
     * @return true or false
     */
    public boolean teacherHasClass(Class c){
        boolean flag = false;

        if (c.getTeacherId() == teacher.getId()){
            flag = true;
        }
        return flag;
    }

    /**
     * remove class id from student account
     * @param id
     */
    public void removeClassFromStudents(int id){
        Student[] s = new Student[100000];
        int cS = 0 ;

        try {
            File myFile = new File(Path.student);
            Scanner myReader = new Scanner(myFile);

            while (myReader.hasNext()){
                s[cS] = new Student(myReader.nextInt());
                cS++;
                myReader.nextLine();
            }
            myReader.close();

            for (int i = 0; i < cS; i++){
                s[i].removeClass(id);
            }

            Writer output;
            output = new BufferedWriter(new FileWriter(myFile));

            for (int i = 0; i < cS; i++){
                output.append(s[i].FullInformation()+"\n");
            }
            output.close();

        } catch (FileNotFoundException e) {
            System.out.println("file NOT Find !");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * remove student id from teachers account
     * @param id
     */
    public void removeStudentsFromTeachers(int id){

        Student[] s = new Student[100000];
        Teacher[] t = new Teacher[100000];
        int cS = 0 ,cT = 0;

        try {
            File myFile = new File(Path.student);
            Scanner myReader = new Scanner(myFile);

            while (myReader.hasNext()) {
                s[cS] = new Student(myReader.nextInt());
                cS++;
                myReader.nextLine();
            }
            myReader.close();

            myFile = new File(Path.teacher);
            myReader = new Scanner(myFile);

            while (myReader.hasNext()) {
                t[cT] = new Teacher(myReader.nextInt(), myReader.next(), myReader.next());
                cT++;
                myReader.nextLine();
            }
            myReader.close();

        } catch (FileNotFoundException e) {
                System.out.println("file NOT Find !");
        }

        int x = 0;
        for (int i = 0; i < cT; i++) {
            if (t[i].getId() == teacher.getId()) {
                x = i;
                break;
            }
        }

        for (int i = 0; i < cS; i++){
            if (s[i].hasThisClass(id))
                t[x].removeStudent(s[i].getId());
        }

        try {
            File myFile = new File(Path.teacher);
            Writer output;
            output = new BufferedWriter(new FileWriter(myFile));

            for (int i = 0; i < cT; i++){
                output.append(t[i].FullInformation()+"\n");
            }
            output.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
