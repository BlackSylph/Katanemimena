package com.katanemimena.project.repository;

import com.katanemimena.project.entity.UserTransferStatement;
import com.katanemimena.project.entity.UserTransferStatementId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserTransferStatementRepository extends JpaRepository<UserTransferStatement, UserTransferStatementId> {

    Optional<List<UserTransferStatement>> findAllByPkUserId(Long id);
}
