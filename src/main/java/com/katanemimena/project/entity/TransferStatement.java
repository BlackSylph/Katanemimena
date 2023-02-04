package com.katanemimena.project.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "transferStatements")
public class TransferStatement implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Long id;
	
	@Column(name = "text")
	private String text;
	
	@Column(name = "fee")
	private Float fee;
	
	@Column(name = "feePaid")
	private boolean feePaid;

	@Column(name = "completed")
	private boolean completed;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.transferStatement")
	private Set<UserTransferStatement> userTransferStatements;
    
	public TransferStatement() {
	}

	public TransferStatement(Long id, String text, Float fee, boolean feePaid, boolean completed) {
		this.id = id;
		this.text = text;
		this.fee = fee;
		this.feePaid = feePaid;
		this.completed = completed;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Float getFee() {
		return fee;
	}

	public void setFee(Float fee) {
		this.fee = fee;
	}

	public boolean isFeePaid() {
		return feePaid;
	}

	public void setFeePaid(boolean feePaid) {
		this.feePaid = feePaid;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
}
