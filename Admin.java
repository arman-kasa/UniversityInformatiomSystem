public class Admin extends User{

    /**
     * constructor
     * @param id
     * @param userName
     * @param password
     */
    public Admin(int id, String userName, String password) {

        super(userName, password, id);
    }

    /**
     * get info from user class
     * @return
     */
    @Override
    public String getInfo() {

        return super.getInfo();
    }
}
