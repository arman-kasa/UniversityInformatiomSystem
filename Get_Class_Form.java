import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Scanner;

public class Get_Class_Form extends JFrame {

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
    private Student         student;
    private Class[]         classes;
    private int             cClasses;

    /**
     * constructor
     * @param student
     */
    public Get_Class_Form(Student student){

        this.student = student;

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
        panel.setBackground(new Color(238,238,2));
        panel.setBounds(0, 0, this.getWidth(), this.getHeight());

        /**
         * make label
         */
        picLb = new JLabel(new ImageIcon(Picture));
        picLb.setBounds(60, 30, 100, 100);
        panel.add(picLb);

        title = new JLabel("Get Class");
        title.setFont(new Font("Arial", Font.BOLD, 26));
        title.setBounds(240, 60, 300, 50);
        title.setForeground(Color.BLACK);
        panel.add(title);

        /**
         * make text area
         */
        textArea = new JTextArea();
        textArea.setBackground(new Color(75,20,252));
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
        idLb.setForeground(Color.BLACK);
        idLb.setFont(new Font("Arial", Font.CENTER_BASELINE, 14));
        idLb.setBounds(10, 440, 40, 30);
        idLb.setHorizontalAlignment(JLabel.RIGHT);
        panel.add(idLb);

        idTF = new JTextField();
        idTF.setBorder(null);
        idTF.setBackground(new Color(75,20,252));
        idTF.setBounds(60, 440, 100, 30);
        idTF.setForeground(Color.white);
        panel.add(idTF);

        /**
         * make button
         */
        okBTN = new JButton("Get");
        okBTN.setBounds(170, 490, 140, 40);
        okBTN.setBorder(null);
        okBTN.setBackground(new Color(2,238,238));
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
     * read classes info and add them to textarea
     */
    public void addList(){

        classes = new Class[100000];
        cClasses = 0;

        try {
            File myFile = new File(Path.Class);
            Scanner myReader = new Scanner(myFile);

            while (myReader.hasNext()){
                classes[cClasses] = new Class(myReader.nextInt());
                cClasses++;
                myReader.nextLine();
            }

            textArea.setText("id\tname\tvahed\tteacher\tDay\tTime\tcapacity\ttaken\n");
            for (int i = 0; i < cClasses; i++){
                textArea.setText(textArea.getText() + classes[i].InfoForList() + "\n");
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("file NOT Find !");
        }

    }

    /**
     * add class id to student file
     * @param id
     */
    public void setUPStudentFile(int id){

        Student[] u = new Student[10000000];
        int cU = 0;

        try {
            File myFile = new File(Path.student);
            Scanner myReader = new Scanner(myFile);

            while (myReader.hasNext()){
                u[cU] = new Student(myReader.nextInt());
                cU++;
                myReader.nextLine();
            }
            myReader.close();

            for (int i = 0; i < cU; i++){
                if (u[i].getId() == this.student.getId()){
                    u[i].addNewClass(id);
                }
            }

            Writer output;
            output = new BufferedWriter(new FileWriter(myFile));
            for (int i = 0; i < cU; i++){
                output.append(u[i].FullInformation()+"\n");
            }
            output.close();

        } catch (FileNotFoundException e) {
            System.out.println("file NOT Find !");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * add student id to teachers file
     * @param id
     */
    public void setUpTeacherFile(int id){

        Teacher[] u = new Teacher[10000000];
        int cU = 0;

        Class c = new Class(id);

        try {
            File myFile = new File(Path.teacher);
            Scanner myReader = new Scanner(myFile);

            while (myReader.hasNext()){
                u[cU] = new Teacher(myReader.nextInt(), myReader.next(), myReader.next());
                cU++;
                myReader.nextLine();
            }
            myReader.close();

            for (int i = 0; i < cU; i++){
                if (u[i].getId() == c.getTeacherId()){
                    u[i].addNewStudent(this.student.getId());
                }
            }

            Writer output;
            output = new BufferedWriter(new FileWriter(myFile));
            for (int i = 0; i < cU; i++){
                output.append(u[i].FullInformation()+"\n");
            }
            output.close();

        } catch (FileNotFoundException e) {
            System.out.println("file NOT Find !");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * add student id to class files
     * @param id
     */
    public void setUpClassFile(int id){

        try {
            File myFile = new File(Path.Class);


            for (int i = 0; i < cClasses; i++){
                if (classes[i].getId() == id){
                    classes[i].addNewStudent(this.student.getId());
                }
            }

            Writer output;
            output = new BufferedWriter(new FileWriter(myFile));
            for (int i = 0; i < cClasses; i++){
                output.append(classes[i].FullInformation()+"\n");
            }
            output.close();

        } catch (FileNotFoundException e) {
            System.out.println("file NOT Find !");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * check class has capacity
     * @param id
     * @return true or false
     */
    public boolean hasCapacity(int id){
        boolean flag = false;

        for (int i = 0; i < cClasses; i++){
            if (classes[i].getId() == id && classes[i].getCapacity() > classes[i].getTakenNumbers()){
                flag = true;
                break;
            }
        }
        return flag;
    }

    /**
     * check student can add any classes or not
     * @return true or false
     */
    public boolean canTakeAnyClasses(){
        boolean flag = false;
        if (student.getAverage() >= 17 && student.getTakenVaheds() < 24){
            flag = true;
        }else if (student.getAverage() < 17 && student.getTakenVaheds() < 20){
            flag = true;
        }
        return flag;
    }

    /**
     * check how many vahed has to add
     * @return vahed number
     */
    public int howManyVahedCanTake(){
        if (student.getAverage() >= 17){
            return 24 - student.getTakenVaheds();
        }else{
            return 20 - student.getTakenVaheds();
        }
    }

    /**
     * @return true or false
     */
    public boolean isAllFill(){

        return !idTF.getText().isEmpty() ;
    }

    /**
     * @return true or false
     */
    public boolean isItOk(){

        String[] s1 = idTF.getText().split(" ");

        return  s1.length > 1;
    }

    /**
     * check class existing
     * @param id
     * @return true or false
     */
    public boolean isThereClassWithId(int id){
        boolean flag = false;

        for (int i = 0; i < cClasses; i++){
            if (classes[i].getId() == id){
                flag = true;
                break;
            }
        }
        return flag;
    }

    /**
     * check student has the class already or not
     * @param id
     * @return true or false
     */
    public boolean hasItAlready(int id){
        for (int i = 0; i < student.getcClasses(); i++){
            if (student.getClasses()[i] == id)
                return true;
        }
        return false;
    }

    /**
     * check several situations if there was no problem add new class to file
     */
    public void action(){

        if (!isAllFill()){
            JOptionPane.showMessageDialog(this,"You must Fill All Fields", "Empty Field(s)", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (isItOk()) {
            JOptionPane.showMessageDialog(this, "Fields must not contain ' ' character", "two words in some Fields", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int id;
        try{
            id = Integer.parseInt(idTF.getText());
        }catch (Exception e){
            JOptionPane.showMessageDialog(this, "You Must Enter numbers in id !", "wrong Input", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!isThereClassWithId(id)){
            JOptionPane.showMessageDialog(this, "There is no class with this id !", "wrong id", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!canTakeAnyClasses()){
            JOptionPane.showMessageDialog(this, "You cant pick any more classes!", "full vaheds", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Class c = new Class(id);
        if (howManyVahedCanTake() < c.getVahed()){
            JOptionPane.showMessageDialog(this, "Class vahed is more than yours!", "full vaheds", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!hasCapacity(id)){
            JOptionPane.showMessageDialog(this, "Class Hase No Capacity!", "full capacity", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (hasItAlready(id)){
            JOptionPane.showMessageDialog(this, "You class Already!", "have it Already", JOptionPane.WARNING_MESSAGE);
            return;
        }

        setUpClassFile(id);
        setUPStudentFile(id);
        setUpTeacherFile(id);
        student.addNewClass(id);

        JOptionPane.showMessageDialog(this, "class added Successfully!", "task done", JOptionPane.WARNING_MESSAGE);
        return;

    }
}
