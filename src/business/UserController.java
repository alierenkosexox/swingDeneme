package business;

import dao.UserDao;
import entity.User;

import java.util.ArrayList;

public class UserController {
    private UserDao userDao;

    public UserController() {
        this.userDao = new UserDao();
    }

    public ArrayList<User> findAll() {
        return this.userDao.findAll();
    }

    public User getByID(int id) {
        if (id == 0) {
            System.out.println("ID değeri 0 veya 0'dan küçük olamaz !");
            return new User();
        }
        return this.userDao.getByID(id);
    }
    public  boolean update(User user){
        User checkUser = this.getByID(user.getId());
        if (checkUser == null ||checkUser.getId() == 0){
            return false;
        }
        return this.userDao.update(user);
    }
    public boolean save(User user){
        return this.userDao.save(user);
    }
}
