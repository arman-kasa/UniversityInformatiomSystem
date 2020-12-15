import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Scanner;

public class Teacher_Students_List_Form extends JFrame {

    /**
     * variables
     */
    private JPanel          panel;
    private JLabel          title, picLb;
    private BufferedImage   Picture;
    private JLabel          idLb, averageLb;
    private JTextField      idTF, averageTF;
    private JTextArea       textArea;
    private JScrollPane     scrollPane;
    private JButton         okBTN;
    private Teacher         teacher;

    /**
     * constructor
     * @param teacher
     */
    public Teacher_Students_List_Form(Teacher teacher){
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
         * maek panel
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

        title = new JLabel("Students List");
        title.setFont(new Font("Arial", Font.BOLD, 26));
        title.setBounds(240, 60, 300, 50);
        title.setForeground(Color.white);
        panel.add(title);

        textArea = new JTextArea();
        textArea.setBackground(new Color(92, 0, 210));
        textArea.setForeground(Color.white);
        textArea.setEditable(false);
        addList();

        scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(20, 170, 440, 250);
        panel.add(scrollPane);


        idLb = new JLabel("id: ");
        idLb.setForeground(Color.white);
        idLb.setFont(new Font("Arial", Font.CENTER_BASELINE, 14));
        idLb.setBounds(10, 440, 40, 30);
        idLb.setHorizontalAlignment(JLabel.RIGHT);
        panel.add(idLb);

        /**
         * make text field
         */
        idTF = new JTextField();
        idTF.setBorder(null);
        idTF.setBackground(new Color(92, 0, 210));
        idTF.setBounds(60, 440, 100, 30);
        idTF.setForeground(Color.white);
        panel.add(idTF);

        averageLb = new JLabel("Score: ");
        averageLb.setBorder(null);
        averageLb.setBackground(new Color(92, 0, 210));
        averageLb.setBounds(190, 440, 100, 30);
        averageLb.setForeground(Color.white);
        averageLb.setHorizontalAlignment(JLabel.RIGHT);
        panel.add(averageLb);

        averageTF = new JTextField();
        averageTF.setBorder(null);
        averageTF.setBackground(new Color(92, 0, 210));
        averageTF.setBounds(300, 440, 100, 30);
        averageTF.setForeground(Color.white);
        panel.add(averageTF);

        /**
         * make button
         */
        okBTN = new JButton("set score");
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
        float score;

        try {
            id = Integer.parseInt(idTF.getText());
            score = Float.parseFloat(averageTF.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "You Must Enter numbers in id and Score!", "wrong Input", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!isHasTheStudent(id)){
            JOptionPane.showMessageDialog(this, "You dont have a Student with this id!!", "invalid id", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Student[] s = new Student[10000000];
        int cS = 0;

        try {
            File myFile = new File(Path.student);
            Scanner myReader = new Scanner(myFile);

            while (myReader.hasNext()){
                s[cS] = new Student(myReader.nextInt(), myReader.next(), myReader.next());
                cS++;
                myReader.nextLine();
            }

            for (int i = 0; i < cS; i++){
                if (s[i].getId() == id){
                    s[i].setAverage((s[i].getAverage() + score) /2);
                    break;
                }
            }


            Writer output;
            output = new BufferedWriter(new FileWriter(myFile));
            for (int i = 0; i < cS; i++){
                output.append(s[i].FullInformation()+"\n");
            }

            output.close();

        } catch (FileNotFoundException e) {
            System.out.println("admin file NOT Find !");
            return;
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        updateList(s,cS);
        JOptionPane.showMessageDialog(this, "Score adedd Successfully!", "progress Done", JOptionPane.WARNING_MESSAGE);

    }

    /**
     * @return ture or flase
     */
    public boolean isItOk() {

        String[] s1 = idTF.getText().split(" ");

        return s1.length > 1 ;
    }

    /**
     * add to list
     */
    public void addList(){

        Student[] s = teacher.getStudent();
        int c = teacher.getcStudent();

        textArea.setText("id\tusername\taverage\n");
        for (int i = 0; i < c; i++){
            textArea.setText(textArea.getText() + s[i].getId()+"\t"+s[i].getUserName()+"\t"+s[i].getAverage()+"\n");
        }

    }

    /**
     * update the list
     * @param s
     * @param cS
     */
    public void updateList(Student[] s, int cS){
        textArea.setText("id\tusername\taverage\n");
        for (int i = 0; i < cS; i++){
            if (isHasTheStudent(s[i].getId())){
                textArea.setText(textArea.getText() + s[i].getId()+"\t"+s[i].getUserName()+"\t"+s[i].getAverage()+"\n");
            }
        }

    }

    public boolean isHasTheStudent(int id){
        boolean f = false;
        for (int i = 0; i < teacher.getcStudent(); i++){
            if (teacher.getStudent()[i].getId() == id){
                f = true;
                break;
            }
        }
        return f;
    }
}
