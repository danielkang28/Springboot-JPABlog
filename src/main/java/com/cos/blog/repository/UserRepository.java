package com.cos.blog.repository;

import com.cos.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

//DAO
//자동으로 bean으로 등록이 됨
//@Repository //생락 가능.

public interface UserRepository extends JpaRepository<User, Integer> {

}
