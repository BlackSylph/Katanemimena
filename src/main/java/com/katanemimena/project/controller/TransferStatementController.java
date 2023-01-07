package com.katanemimena.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.katanemimena.project.entity.TransferStatement;
import com.katanemimena.project.exceptions.TransferStatementNotFoundException;
import com.katanemimena.project.repository.TransferStatementRepository;

@RestController
public class TransferStatementController {

	@Autowired
	private TransferStatementRepository transferStatementRepository;

	// Get All TransferStatements
	@GetMapping("/transferStatements")
	public List<TransferStatement> getAllTransferStatements() {
	    return transferStatementRepository.findAll();
	}

	// Get a Single Note
    @GetMapping("/transferStatements/{id}")
    public TransferStatement getTransferStatementById(@PathVariable("id") Long transferStatementId) {
        return transferStatementRepository.findById(transferStatementId)
                .orElseThrow(() -> new TransferStatementNotFoundException(transferStatementId));
    }
	
	// Create a new TransferStatement
	@PostMapping("/transferStatements")
	public TransferStatement createTransferStatement(@RequestBody TransferStatement transferStatement) {
	    return transferStatementRepository.save(transferStatement);
	}


	// Update a TransferStatement
	@PutMapping("/transferStatements/{id}") TransferStatement updateTranserStatement(@RequestBody TransferStatement newTransferStatement, @PathVariable Long id) {
	    return transferStatementRepository.findById(id)
	    		.map(transferStatement -> {
	    	        transferStatement.setText(newTransferStatement.getText());
	    	        transferStatement.setFee(newTransferStatement.getFee());
	    	        transferStatement.setBuyerAccept(newTransferStatement.getBuyerAccept());
	    	        transferStatement.setSellerAccept(newTransferStatement.getSellerAccept());
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
	@DeleteMapping("/transferStatements/{id}")
	void deleteTransferStatement(@PathVariable Long id) {
		transferStatementRepository.deleteById(id);
	}
}
