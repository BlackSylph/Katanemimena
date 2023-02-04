package com.katanemimena.project.repository;

import com.katanemimena.project.entity.TransferStatement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferStatementRepository extends JpaRepository<TransferStatement, Long> {
}
