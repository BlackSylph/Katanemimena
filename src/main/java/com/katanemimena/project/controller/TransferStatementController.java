package com.katanemimena.project.controller;

import com.katanemimena.project.entity.TransferStatement;
import com.katanemimena.project.entity.UserTransferStatement;
import com.katanemimena.project.entity.UserTransferStatementId;
import com.katanemimena.project.exceptions.TransferStatementNotFoundException;
import com.katanemimena.project.exceptions.UserNotFoundException;
import com.katanemimena.project.repository.TransferStatementRepository;
import com.katanemimena.project.repository.UserTransferStatementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/transferStatements")
public class TransferStatementController {

	@Autowired
	private TransferStatementRepository transferStatementRepository;

	@Autowired
	private UserTransferStatementRepository userTransferStatementRepository;

	// Get All TransferStatements
	@GetMapping("")
	public List<TransferStatement> getAllTransferStatements() {
	    return transferStatementRepository.findAll();
	}

	// Get a Single TransferStatements
    @GetMapping("/{id}")
    public TransferStatement getTransferStatementById(@PathVariable("id") Long transferStatementId) {
        return transferStatementRepository.findById(transferStatementId)
                .orElseThrow(() -> new TransferStatementNotFoundException(transferStatementId));
    }
	
	// Create a new TransferStatement
	@PostMapping("")
	public TransferStatement createTransferStatement(@RequestBody TransferStatement transferStatement) {
	    return transferStatementRepository.save(transferStatement);
	}


	// Update a TransferStatement
	@PutMapping("/{id}") TransferStatement updateTransferStatement(@RequestBody TransferStatement newTransferStatement,
																   @PathVariable Long id) {
	    return transferStatementRepository.findById(id)
	    		.map(transferStatement -> {
	    	        transferStatement.setText(newTransferStatement.getText());
	    	        transferStatement.setFee(newTransferStatement.getFee());
	    	        transferStatement.setFeePaid(newTransferStatement.isFeePaid());
	    	        transferStatement.setCompleted(newTransferStatement.isFeePaid());
	    	        return transferStatementRepository.save(transferStatement);
	    	      })
	    	      .orElseGet(() -> {
	    	        newTransferStatement.setId(id);
	    	        return transferStatementRepository.save(newTransferStatement);
	    	      });
	    	  }

	// Delete a TransferStatement
	@DeleteMapping("/{id}")
	void deleteTransferStatement(@PathVariable("id") Long id) {
		transferStatementRepository.deleteById(id);
	}

	@GetMapping("/{userId}")
	public List<TransferStatement> getTransferStatementsByUser(@PathVariable("id") Long userId) {
		return userTransferStatementRepository.findAllByPkUserId(userId)
				.orElseThrow(() -> new UserNotFoundException(userId))
				.stream()
				.map(UserTransferStatement::getTransferStatement)
				.collect(Collectors.toList());
	}
}
