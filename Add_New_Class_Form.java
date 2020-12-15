import javax.imageio.ImageIO;
import javax.lang.model.element.NestingKind;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Scanner;

public class Add_New_Class_Form extends JFrame {

    /**
     * variables
     */
    private JPanel panel;
    private JLabel title, picLb;
    private BufferedImage Picture;
    private JLabel idLb, nameLb, vahedLb, dayLb, timeLb, capacityLb;
    private JTextField idTF, nameTF, vahedTf, capacityTF;
    private JComboBox dayCB, timeCB;
    private JButton okBTN;
    private Teacher teacher;

    /**
     * constructor
     * @param teacher
     */
    public Add_New_Class_Form(Teacher teacher) {
        this.teacher = teacher;

        try {
            Picture = ImageIO.read(this.getClass().getResource("Images/Class_icon.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        /**
         * set our window
         */
        this.setSize(500, 550);
        this.setLayout(null);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("Users List");
        this.setIconImage(Picture);

        /**
         * make window
         */
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(177, 0, 106));
        panel.setBounds(0, 0, this.getWidth(), this.getHeight());

        /**
         * add image
         */
        picLb = new JLabel(new ImageIcon(Picture));
        picLb.setBounds(60, 30, 100, 100);
        panel.add(picLb);

        /**
         * add label new class
         */
        title = new JLabel("New Class");
        title.setFont(new Font("Arial", Font.BOLD, 26));
        title.setBounds(240, 60, 300, 50);
        title.setForeground(Color.white);
        panel.add(title);

        /**
         * add label id
         */
        idLb = new JLabel("id: ");
        idLb.setForeground(Color.white);
        idLb.setFont(new Font("Arial", Font.CENTER_BASELINE, 14));
        idLb.setBounds(70, 180, 100, 30);
        idLb.setHorizontalAlignment(JLabel.RIGHT);
        panel.add(idLb);

        /**
         * add label name
         */
        nameLb = new JLabel("Name: ");
        nameLb.setForeground(Color.white);
        nameLb.setFont(new Font("Arial", Font.CENTER_BASELINE, 14));
        nameLb.setBounds(70, 220, 100, 30);
        nameLb.setHorizontalAlignment(JLabel.RIGHT);
        panel.add(nameLb);

        /**
         * add label vahed
         */
        vahedLb = new JLabel("Vahed: ");
        vahedLb.setForeground(Color.white);
        vahedLb.setFont(new Font("Arial", Font.CENTER_BASELINE, 14));
        vahedLb.setBounds(70, 260, 100, 30);
        vahedLb.setHorizontalAlignment(JLabel.RIGHT);
        panel.add(vahedLb);

        /**
         * add label day
         */
        dayLb = new JLabel("day: ");
        dayLb.setForeground(Color.white);
        dayLb.setFont(new Font("Arial", Font.CENTER_BASELINE, 14));
        dayLb.setBounds(70, 300, 100, 30);
        dayLb.setHorizontalAlignment(JLabel.RIGHT);
        panel.add(dayLb);

        /**
         * add label time
         */
        timeLb = new JLabel("time: ");
        timeLb.setForeground(Color.white);
        timeLb.setFont(new Font("Arial", Font.CENTER_BASELINE, 14));
        timeLb.setBounds(70, 340, 100, 30);
        timeLb.setHorizontalAlignment(JLabel.RIGHT);
        panel.add(timeLb);

        /**
         * add label capacity
         */
        capacityLb = new JLabel("Capacity: ");
        capacityLb.setForeground(Color.white);
        capacityLb.setFont(new Font("Arial", Font.CENTER_BASELINE, 14));
        capacityLb.setBounds(70, 380, 100, 30);
        capacityLb.setHorizontalAlignment(JLabel.RIGHT);
        panel.add(capacityLb);

        /**
         * make field id
         */
        idTF = new JTextField();
        idTF.setBorder(null);
        idTF.setBackground(new Color(92, 0, 210));
        idTF.setBounds(180, 180, 200, 30);
        idTF.setForeground(Color.white);
        panel.add(idTF);

        /**
         * make field name
         */
        nameTF = new JTextField();
        nameTF.setBorder(null);
        nameTF.setBackground(new Color(92, 0, 210));
        nameTF.setBounds(180, 220, 200, 30);
        nameTF.setForeground(Color.white);
        panel.add(nameTF);

        /**
         * make field vahed
         */
        vahedTf = new JTextField();
        vahedTf.setBorder(null);
        vahedTf.setBackground(new Color(92, 0, 210));
        vahedTf.setBounds(180, 260, 200, 30);
        vahedTf.setForeground(Color.white);
        panel.add(vahedTf);

        /**
         * make days box
         */
        dayCB = new JComboBox();
        dayCB.addItem("Saturday");
        dayCB.addItem("Sunday");
        dayCB.addItem("Monday");
        dayCB.addItem("Tuesday");
        dayCB.addItem("Wednesday");
        dayCB.setBorder(null);
        dayCB.setBackground(new Color(92, 0, 210));
        dayCB.setBounds(180, 300, 200, 30);
        dayCB.setForeground(Color.white);
        panel.add(dayCB);

        /**
         * make time box
         */
        timeCB = new JComboBox();
        timeCB.addItem("8-10");
        timeCB.addItem("10-12");
        timeCB.addItem("14-16");
        timeCB.setBorder(null);
        timeCB.setBackground(new Color(92, 0, 210));
        timeCB.setBounds(180, 340, 200, 30);
        timeCB.setForeground(Color.white);
        panel.add(timeCB);

        /**
         * make capacity field
         */
        capacityTF = new JTextField();
        capacityTF.setBorder(null);
        capacityTF.setBackground(new Color(92, 0, 210));
        capacityTF.setBounds(180, 380, 200, 30);
        capacityTF.setForeground(Color.white);
        panel.add(capacityTF);

        /**
         * make add button
         */
        okBTN = new JButton("Add");
        okBTN.setBounds(170, 440, 140, 40);
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
     * check several situations if there was no problem add new class to file
     */
    public void action() {

        if (isAllFill()) {
            JOptionPane.showMessageDialog(this, "Please FIll All Fields.", "Empty Fields", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (isItOk()) {
            JOptionPane.showMessageDialog(this, "Fields must not contain ' ' character", "two words in some Fields", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int id, capacity, vahed;
        String name = nameTF.getText();

        try {
            id = Integer.parseInt(idTF.getText());
            capacity = Integer.parseInt(capacityTF.getText());
            vahed = Integer.parseInt(vahedTf.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "You Must Enter numbers in id , capacity and vahed!", "wrong Input", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (isItClassWithTHisID(id)) {
            JOptionPane.showMessageDialog(this, "There is already a class with this id.", "duplicated id ", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int d = dayCB.getSelectedIndex();
        int t = timeCB.getSelectedIndex() + 1;


        Class c = new Class(id, name, vahed, this.teacher.getId(), d, t, capacity, 0, new int[0] );

        try {

            File myFile = new File(Path.Class);
            Writer output;
            output = new BufferedWriter(new FileWriter(myFile, true));
            output.append(c.FullInformation()+"\n");
            output.close();

            JOptionPane.showMessageDialog(this, "class added Successfully.", "Task Done",JOptionPane.WARNING_MESSAGE);
            this.dispose();
            return;

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * check inputs Emptiness
     * @return true or false
     */
    public boolean isAllFill() {
        return !idTF.getText().isEmpty() && !nameTF.getText().isEmpty() && !vahedTf.getText().isEmpty() && capacityTF.getText().isEmpty();
    }

    /**
     * check the inputs
     * @return true or false
     */
    public boolean isItOk() {
        String[] s1 = nameTF.getText().split(" ");
        String[] s2 = idTF.getText().split(" ");
        String[] s3 = vahedTf.getText().split(" ");
        String[] s4 = capacityTF.getText().split(" ");
        return s1.length > 1 || s2.length > 1 || s3.length > 1 || s4.length > 1;
    }

    /**
     * check if there is any class with this id or not
     * @param id
     * @return true or false
     */
    public static boolean isItClassWithTHisID(int id) {

        boolean flag = false;

        try {
            File myFile = new File(Path.Class);
            Scanner myReader = new Scanner(myFile);

            while (myReader.hasNext()) {

                if (myReader.nextInt() == id) {
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
