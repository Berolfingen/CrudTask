package com.leonid.crudtask.options;

import com.leonid.crudtask.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface UserService {
    void add(User user);
    void edit(User user);
    void delete(int id);
    User getUser(int id);
    List getAllUser();
    Page<User> getAllUser(Integer pageNumber);
    Page<User> findByNameContaining(String name, Integer pageNumber);
}
