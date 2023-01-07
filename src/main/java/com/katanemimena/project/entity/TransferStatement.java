package com.katanemimena.project.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "transferStatements")
public class TransferStatement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Long id;
	
	@Column(name = "text")
	private String text;
	
	@Column(name = "fee")
	private Float fee;

    @Enumerated(EnumType.STRING)
	@Column(name = "buyerAccept", length = 20)
	private EStatus buyerAccept;

    @Enumerated(EnumType.STRING)
	@Column(name = "sellerAccept", length = 20)
	private EStatus sellerAccept;
	
	@Column(name = "feePaid")
	private boolean feePaid;

	@Column(name = "completed")
	private boolean completed;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH
    })
    @JoinTable(
            name = "user_transferStatements",
            joinColumns = @JoinColumn(name = "transferStatementId"),
            inverseJoinColumns = @JoinColumn(name = "userId")
    )
    @JsonIdentityInfo(generator = ObjectIdGenerators.UUIDGenerator.class, property="@UUID")
    private List<User> users;
    
	public TransferStatement() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TransferStatement(Long id, String text, Float fee, EStatus buyerAccept, EStatus sellerAccept,
			boolean feePaid, boolean completed) {
		super();
		this.id = id;
		this.text = text;
		this.fee = fee;
		this.buyerAccept = buyerAccept;
		this.sellerAccept = sellerAccept;
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

	public EStatus getBuyerAccept() {
		return buyerAccept;
	}

	public void setBuyerAccept(EStatus buyerAccept) {
		this.buyerAccept = buyerAccept;
	}

	public EStatus getSellerAccept() {
		return sellerAccept;
	}

	public void setSellerAccept(EStatus sellerAccept) {
		this.sellerAccept = sellerAccept;
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

    public void addUser(User user) {
        if (users == null) {
            users = new ArrayList<>();
        }

        users.add(user);
    }
    
	@Override
	public String toString() {
		return "TransferStatement [id=" + id + ", text=" + text + ", fee=" + fee + ", buyerAccept=" + buyerAccept
				+ ", sellerAccept=" + sellerAccept + ", feePaid=" + feePaid + ", completed=" + completed + "]";
	}
}
