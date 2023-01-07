package com.katanemimena.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.katanemimena.project.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}