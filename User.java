public class User {
    /**
     * variables
     */
    private String userName, Password;
    private int id;

    /**
     * constructor
     * @param userName
     * @param password
     * @param id
     */
    public User(String userName, String password, int id) {
        this.userName = userName;
        this.Password = password;
        this.id = id;
    }

    /**
     * setter and getter method
     * @param id
     */
    public User(int id) {

        this.id = id;
    }

    /**
     * @return username
     */
    public String getUserName() {

        return userName;
    }

    /**
     * @param userName
     */
    public void setUserName(String userName) {

        this.userName = userName;
    }

    /**
     * @return password
     */
    public String getPassword() {

        return Password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {

        Password = password;
    }

    /**
     * @return id
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
     * @return info
     */
    public String getInfo(){

        return id + " " + userName + " " + Password;
    }

    /**
     * @return full info
     */
    public String FullInformation(){

        return id + " " + userName + " " + Password;
    }

    /**
     * @return list info
     */
    public String ListInformation(){
        return String.format("%d\t%s\t%s",this.getId(), this.getUserName(), this.getPassword());
    }
}
