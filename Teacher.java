import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Teacher extends User{

    /**
     * variables
     */
    private String[]    vahedHa;
    private Student[]   student;
    private int         cVahed, cStudent;

    /**
     * constructor
     * @param id
     * @param userName
     * @param password
     */
    public Teacher(int id, String userName, String password) {//for existing Teacher
        super(userName, password, id);
        fillInformation();
    }

    /**
     * constructor
     * @param id
     * @param userName
     * @param password
     * @param vahedHa
     * @param student
     */
    public Teacher(int id,String userName, String password, String[] vahedHa, Student[] student) {
        super(userName, password, id);
        this.vahedHa = vahedHa;
        this.student = student;
        this.cStudent = student.length;
        this.cVahed = vahedHa.length;
    }

    /**
     * setter and getter method
     * @return vahedha
     */
    public String[] getVahedHa() {

        return vahedHa;
    }

    /**
     * @param vahedHa
     */
    public void setVahedHa(String[] vahedHa) {

        this.vahedHa = vahedHa;
    }

    /**
     * @return student
     */
    public Student[] getStudent() {

        return student;
    }

    /**
     * @param student
     */
    public void setStudent(Student[] student) {

        this.student = student;
    }

    /**
     * @return cVahed
     */
    public int getcVahed() {

        return cVahed;
    }

    /**
     * @param cVahed
     */
    public void setcVahed(int cVahed) {

        this.cVahed = cVahed;
    }

    /**
     * @return cStudent
     */
    public int getcStudent() {

        return cStudent;
    }

    /**
     * @param cStudent
     */
    public void setcStudent(int cStudent) {

        this.cStudent = cStudent;
    }

    /**
     * fill info
     */
    private void fillInformation() {
        try {
            File myObj = new File(Path.teacher);
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNext()) {
                int i = myReader.nextInt();
                String un = myReader.next();
                String pass = myReader.next();
                if (this.getId() == i && un.equals(this.getUserName()) && pass.equals(this.getPassword())) {
                    break;
                }else{
                    myReader.nextLine();
                }
            }

            String[] s = myReader.nextLine().split(" ");
            vahedHa = new String[100000];
            student = new Student[100000];
            cStudent = cVahed = 0;

            for (int i = 0; i < s.length; i++){
                char[] c = s[i].toCharArray();
                boolean f = true;

                for (char v : c){
                    if (!Character.isDigit(v)){
                        f = false;
                    }
                }

                if (f && !s[i].isEmpty()){
                    student[cStudent] = new Student(Integer.parseInt(s[i]));
                    cStudent++;
                }else if (!f && !s[i].isEmpty()){
                    vahedHa[cVahed] = s[i];
                    cVahed++;
                }

            }

        } catch (FileNotFoundException e) {
            System.out.println("admin passwords file NOT Find !");
        }
    }

    /**
     * @return full info
     */
    @Override
    public String FullInformation() {

        String temp = String.format("%d %s %s ", this.getId(),this.getUserName(), this.getPassword());
        for (int i = 0; i < cVahed; i++){
            temp += vahedHa[i] +" ";
        }

        for (int i = 0; i < cStudent; i++){
            temp += student[i].getId()+" ";
        }
        return temp;
    }

    /**
     * @return info
     */
    @Override
    public String ListInformation() {
        return String.format("%d\t%s\t%s", this.getId(), this.getUserName(), this.getPassword());
    }

    /**
     * add student
     * @param id
     */
    public void addNewStudent(int id){
        this.getStudent()[this.getcStudent()] = new Student(id);
        this.cStudent++;
    }

    /**
     * remove student
     * @param id
     */
    public void removeStudent(int id){
        if (!hasThisStudent(id)){
            return;
        }
        Student s[] = new Student[100000];
        boolean f = true;
        int c = 0;

        for (int i = 0; i < cStudent; i++){
            if (student[i].getId() == id && f){
                f = false;
            }else{
                s[c] = new Student(student[i].getId());
                c++;
            }
        }
        this.setStudent(s);
        this.cStudent--;
    }

    /**
     * @param id
     * @return ture or false
     */
    public boolean hasThisStudent(int id){
        for (int i = 0; i < cStudent; i++) {
            if (student[i].getId() == id) {
                return true;
            }
        }
        return false;
    }
}
