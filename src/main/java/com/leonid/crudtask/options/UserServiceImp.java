package com.leonid.crudtask.options;

import java.util.List;
import com.leonid.crudtask.model.User;
import com.leonid.crudtask.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserServiceImp implements UserService {

    private static final int PAGE_SIZE = 7;
    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void add(User user) {
        userRepository.save(user);
    }

    @Transactional
    public void edit(User user) {
        User userBuffer = userRepository.findById(user.getId());
        userBuffer = user;
        userRepository.save(userBuffer);
    }

    @Transactional
    public void delete(int id) {
        userRepository.delete(id);
    }

    @Transactional
    public User getUser(int id) {
        return userRepository.findById(id);
    }

    @Transactional
    public List getAllUser() {
        return userRepository.findAll();
    }

    @Transactional
    public Page<User> getAllUser(Integer pageNumber) {
        return userRepository.findAll(new PageRequest(pageNumber-1, PAGE_SIZE, Sort.Direction.ASC, "id"));
    }

    @Override
    public Page<User> findByNameContaining(String name, Integer pageNumber) {
        return userRepository.findByNameContaining(name, new PageRequest(pageNumber - 1, PAGE_SIZE, Sort.Direction.ASC, "id"));
    }
}
