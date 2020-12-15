import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Class {

    /**
     * variables
     */
    private int         id, Capacity, teacherId, takenNumbers, vahed, day, time;
    private String      className;
    private int[]       studentsID;

    /**
     * constructor
     */
    public Class(){
    }

    /**
     * constructor for existing class an read anyInformation from file
     * @param id
     */
    public Class(int id){
        this.id = id;
        readInfoFromFile();
    }

    /**
     * constructor
     * @param id
     * @param className
     * @param vahed
     * @param teacherId
     * @param day
     * @param time
     * @param capacity
     * @param takenNumbers
     * @param studentsID
     */
    public Class(int id, String className,int vahed, int teacherId, int day, int time, int capacity, int takenNumbers,   int[] studentsID) {
        this.id = id;
        this.Capacity = capacity;
        this.teacherId = teacherId;
        this.takenNumbers = takenNumbers;
        this.vahed = vahed;
        this.className = className;
        this.studentsID = studentsID;
        this.day = day;
        this.time = time;
    }

    /**
     * getter and setter method
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return capacity
     */
    public int getCapacity() {
        return Capacity;
    }

    /**
     * @param capacity
     */
    public void setCapacity(int capacity) {
        this.Capacity = capacity;
    }

    /**
     * @return teacher id
     */
    public int getTeacherId() {
        return teacherId;
    }

    /**
     * @param teacherId
     */
    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    /**
     * @return taken number
     */
    public int getTakenNumbers() {
        return takenNumbers;
    }

    /**
     * @param takenNumbers
     */
    public void setTakenNumbers(int takenNumbers) {
        this.takenNumbers = takenNumbers;
    }

    /**
     * @return vahed
     */
    public int getVahed() {
        return vahed;
    }

    /**
     * @param vahed
     */
    public void setVahed(int vahed) {
        this.vahed = vahed;
    }

    /**
     * @return class name
     */
    public String getClassName() {
        return className;
    }

    /**
     * @param className
     */
    public void setClassName(String className) {
        this.className = className;
    }

    /**
     * @return day
     */
    public int getDay() {
        return day;
    }

    /**
     * @param day
     */
    public void setDay(int day) {
        this.day = day;
    }

    /**
     * @return time
     */
    public int getTime() {
        return time;
    }

    /**
     * @param time
     */
    public void setTime(int time) {
        this.time = time;
    }

    /**
     * @return student id
     */
    public int[] getStudentsID() {
        return studentsID;
    }

    /**
     * @param studentsID
     */
    public void setStudentsID(int[] studentsID) {
        this.studentsID = studentsID;
    }

    /**
     * read all information of user and put info into variables
     */
    public void readInfoFromFile(){
        try {
            File myObj = new File(Path.Class);
            Scanner myReader = new Scanner(myObj);

            while (true) {
                int i = myReader.nextInt();

                if (this.getId() == i) {
                    break;
                }else{
                    myReader.nextLine();
                }
            }

            this.className = myReader.next();
            this.vahed = myReader.nextInt();
            this.teacherId = myReader.nextInt();
            this.day = myReader.nextInt();
            this.time = myReader.nextInt();
            this.Capacity = myReader.nextInt();
            this.takenNumbers = myReader.nextInt();

            studentsID = new int[Capacity];
            for (int j = 0; j < takenNumbers; j++){
                studentsID[j] = myReader.nextInt();
            }

        } catch (FileNotFoundException e) {
            System.out.println("admin passwords file NOT Find !");
        }
    }

    /**
     * for when you want to print info to file
     * @return full info
     */
    public String FullInformation(){
        String temp = String.format("%d %s %d %d %d %d %d %d ",this.getId(), this.getClassName(),this.getVahed(), this.getTeacherId(),this.getDay(),this.getTime(), this.getCapacity(), this.getTakenNumbers());
        for (int j = 0; j < takenNumbers; j++){
            temp += studentsID[j]+" ";
        }
        return temp;
    }

    /**
     * for when you want to get info for list
     * @return info
     */
    public String InfoForList(){

        String temp = String.format("%d\t%s\t%d\t%d\t",this.getId(), this.getClassName(),this.getVahed(), this.getTeacherId());

        switch (day){
            case 0:
                temp += "saturday\t";
                break;
            case 1:
                temp += "Sunday\t";
                break;
            case 2:
                temp += "Monday\t";
                break;
            case 3:
                temp += "Tuesday\t";
                break;
            case 4:
                temp += "Wednesday\t";
                break;
        }

        switch (time){
            case 1:
                temp += "8-10\t";
                break;
            case 2:
                temp += "10-12\t";
                break;
            case 3:
                temp += "14-16\t";
                break;
        }

        temp += this.getCapacity()+"\t"+this.getTakenNumbers();

        return temp;
    }

    /**
     * add new student to this class
     * @param id
     */
    public void addNewStudent(int id){
        this.getStudentsID()[this.getTakenNumbers()] = id;
        this.takenNumbers++;
    }
}
