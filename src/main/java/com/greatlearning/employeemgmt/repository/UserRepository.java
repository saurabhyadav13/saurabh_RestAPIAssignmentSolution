package com.greatlearning.employeemgmt.repository;

import com.greatlearning.employeemgmt.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User,Integer> {
    @Query("select u from User u where u.userName=?1")
    public User getUserByUserName(String userName);
    @Query("select u.id from User u where u.userName=?1")
    public Integer getUserIdByUserName(String userName);
}
