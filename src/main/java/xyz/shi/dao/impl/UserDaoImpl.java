package xyz.shi.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import xyz.shi.dao.UserDao;
import xyz.shi.domin.User;

import java.util.List;
@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public boolean login(User user) {
        String sql = "SELECT * FROM users WHERE name = ? AND password = ?";
        List<User> users = jdbcTemplate.query(sql, new Object[]{user.getName(), user.getPassword()},
                BeanPropertyRowMapper.newInstance(User.class));
        if (!users.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void save(User user) {
        String sql = "INSERT INTO users (name, password) VALUES (?, ?)";
        jdbcTemplate.update(sql, user.getName(), user.getPassword());
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM users WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public void update(User user) {
        String sql ="update `users` set name = ?,password = ? where id = ? ";
        jdbcTemplate.update(sql, user.getName(), user.getPassword(), user.getId());
    }

    @Override
    public User findById(int id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id},
                BeanPropertyRowMapper.newInstance(User.class));
    }

    @Override
    public List<User> findAll() {
        String sql = "SELECT * FROM users";
        // 查询数据
        List<User> userList = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(User.class));
        return userList;
    }
}
