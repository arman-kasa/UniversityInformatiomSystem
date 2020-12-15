import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Student extends User{

    /**
     * variables
     */
    private int     holding, cClasses, TakenVaheds;
    private float   average;
    private int[]   foodReserved;
    private int[]   classes;

    /**
     * constructor
     * @param id
     * @param userName
     * @param password
     */
    public Student(int id, String userName, String password) {//for existing Student
        super(userName, password, id);
        fillInformation();
    }

    /**
     * constructor also for existing student
     * @param id
     */
    public Student(int id) {
        super(id);
        fillInformation();
    }

    /**
     * constructor
     * @param id
     * @param userName
     * @param password
     * @param average
     * @param holding
     * @param takenVaheds
     * @param foodReserved
     * @param classes
     */
    public Student(int id, String userName, String password,float average,  int holding,int takenVaheds, int[] foodReserved, int[] classes) {//for new Student;
        super(userName, password, id);
        this.holding = holding;
        this.cClasses = 0;
        this.average = average;
        this.foodReserved = foodReserved;
        this.classes = classes;
        this.TakenVaheds = takenVaheds;
    }

    /**
     * getter and setter method
     * @return holding
     */
    public int getHolding() {
        return holding;
    }

    /**
     * @param holding
     */
    public void setHolding(int holding) {
        this.holding = holding;
    }

    /**
     * @return cClasses
     */
    public int getcClasses() {
        return cClasses;
    }

    /**
     * @param cClasses
     */
    public void setcClasses(int cClasses) {
        this.cClasses = cClasses;
    }

    /**
     * @return average
     */
    public float getAverage() {
        return average;
    }

    /**
     * @param average
     */
    public void setAverage(float average) {
        this.average = average;
    }

    /**
     * @return food reservation
     */
    public int[] getFoodReserved() {
        return foodReserved;
    }

    /**
     * @param foodReserved
     */
    public void setFoodReserved(int[] foodReserved) {
        this.foodReserved = foodReserved;
    }

    /**
     * @return classes
     */
    public int[] getClasses() {
        return classes;
    }

    /**
     * set class
     */
    public void setClasses(int[] classes) {
        this.classes = classes;
    }

    /**
     * @return taken vahed
     */
    public int getTakenVaheds() {
        return TakenVaheds;
    }

    /**
     * @param takenVaheds
     */
    public void setTakenVaheds(int takenVaheds) {
        TakenVaheds = takenVaheds;
    }

    /**
     * fill info
     */
    private void fillInformation(){

        try {
            File myObj = new File(Path.student);
            Scanner myReader = new Scanner(myObj);



            while (true) {
                int i = myReader.nextInt();
                String un = myReader.next();
                String pass = myReader.next();
                if (this.getId() == i) {
                    this.setUserName(un);
                    this.setPassword(pass);
                    break;
                }else{
                    myReader.nextLine();
                }
            }

            this.average = myReader.nextFloat();
            this.holding = myReader.nextInt();
            this.TakenVaheds = myReader.nextInt();

            foodReserved = new int[6];
            for (int j = 0; j < 6; j++){
                foodReserved[j] = myReader.nextInt();
            }

            String[] s = myReader.nextLine().split(" ");


            int x = 0;
            for (int i = 0; i < s.length; i++) {
                if (s[i].isEmpty()){
                    x++;
                }
            }

            classes = new int[1000000];
            cClasses = 0;
            for (int i = 0; i < s.length; i++){
                if (!s[i].isEmpty()) {
                    classes[cClasses] = Integer.parseInt(s[i]);
                    cClasses++;
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("admin passwords file NOT Find !");
        }
    }

    /**
     * @return full info
     */
    public String FullInformation(){
        String temp = String.format("%d %s %s %f %d %d %d %d %d %d %d %d ", this.getId(), this.getUserName(), this.getPassword(), this.average, this.holding, this.getTakenVaheds(), foodReserved[0],foodReserved[1],foodReserved[2],foodReserved[3],foodReserved[4],foodReserved[5]);
        for (int i = 0; i < cClasses; i++){
            temp += classes[i] + " ";
        }
        return temp ;
    }

    /**
     * @return info
     */
    public String ListInformation(){
        return String.format("%d\t%s\t%s\t%f\t%d\t%d",this.getId(), this.getUserName(), this.getPassword(), this.getAverage(), this.getHolding(),getTakenVaheds());
    }

    /**
     * @param id
     */
    public void addNewClass(int id){
        this.getClasses()[this.getcClasses()] = id;
        cClasses++;
    }

    /**
     * @param id
     * @return true or false
     */
    public boolean hasThisClass(int id){
        for (int i = 0; i < cClasses; i++){
            if (classes[i] == id){
                return true;
            }
        }
        return false;
    }

    /**
     * @param id
     */
    public void removeClass(int id){
        if (!hasThisClass(id)){
            return;
        }
        int[] temp = new int[10000000];
        int x = 0;
        for (int i = 0; i < cClasses; i++){
            if (classes[i] != id){
                temp[x] = classes[i];
                x++;
            }
        }
        this.cClasses--;
        this.setClasses(temp);
    }
}
