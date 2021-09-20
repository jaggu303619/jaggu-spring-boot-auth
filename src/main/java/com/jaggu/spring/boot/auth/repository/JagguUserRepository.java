package com.jaggu.spring.boot.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jaggu.spring.boot.auth.model.JagguUser;

public interface JagguUserRepository  extends JpaRepository<JagguUser, Long>  {

}
