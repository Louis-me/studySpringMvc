package xyz.shi.dao;

import xyz.shi.domin.User;

import java.util.List;

public interface UserDao {
    boolean login(User user);
    void save(User user);
    void delete(int id);
    void update(User user);
    User findById(int id);
    List<User> findAll();
}
