package xyz.shi.service;

import xyz.shi.domin.User;

import java.util.List;

;
public interface UserService {
    boolean login(User user);
    void save(User user);
    void delete(int id);
    void update(User user);
    User findById(int id);
    List<User> findAll();
}