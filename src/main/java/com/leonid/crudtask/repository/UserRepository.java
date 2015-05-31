package com.leonid.crudtask.repository;

import com.leonid.crudtask.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    public User save(User user);

    @Override
    public void delete(Integer id);

    public User findById(int id);

    public Page<User> findAll(Pageable pageable);

    public List<User> findAll();

    public Page<User> findByNameContaining(String name, Pageable pageable);

    public Page<User> findByAge(int age, Pageable pageable);
}
