package com.bezkoder.integrate.spring.react.repository;

import com.bezkoder.integrate.spring.react.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
