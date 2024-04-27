package com.powernode.usermgt.Dao;

import com.powernode.usermgt.Bean.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDao {

    private static List<User> users = new ArrayList<>();

    static {
        User user1 = new User(1, "孙悟空", 1, "wukong@com.powernode");
        User user2 = new User(2, "张三", 1, "zhangsan@com.powernode");
        User user3 = new User(3, "猪八戒", 1, "bajie@com.powernode");
        User user4 = new User(4, "白骨精", 0, "bgj@com.powernode");
        User user5 = new User(5, "武松", 1, "ws@com.powernode");

        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        users.add(user5);
    }

    public int generateId(){
        // 使用Stream API
        Integer maxId = users.stream().map(user -> user.getId()).reduce((id1, id2) -> id1 > id2 ? id1 : id2).get();
        return maxId + 1;
    }

    public List<User> selectAll(){
        return users;
    }

    public void insert(User user){
        Integer id = generateId();
        user.setId(id);
        users.add(user);
    }

    public User selectById(Integer id){
        return users.stream().filter(user -> user.getId().equals(id)).findFirst().get();
    }

    public void update(User user){
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(user.getId())) {
                users.set(i, user);
            }
        }
    }

    public void delete(Integer id){
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(id)){
                users.remove(i);
            }
        }
    }
}
