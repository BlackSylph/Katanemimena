package com.katanemimena.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.katanemimena.project.entity.TransferStatement;

public interface TransferStatementRepository extends JpaRepository<TransferStatement, Long> {

}
