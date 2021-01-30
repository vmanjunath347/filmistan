package com.priya.rightcare.Entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "payment_details")
public class PaymentInfo {
	@Id
	@GeneratedValue
	@Column(name="payment_id")
    private int paymentId;
	
	@Column(name="card_type")
	private String cardType;
	
	@Column(name="nameon_card")
	private String nameOnCard;
	
	@Column(name="card_num")
	private long cardNum;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-YYYY")
	@Column(name="exp_date")
	private Date expiryDate;
	
	@Column(name="cvv")
	private long cvv;
	
	@Column(name="payment_amt")
	private double paymentAmount;
	
	@OneToOne
	@JoinColumn(name = "customerId_fk", referencedColumnName = "customer_id")
	private Customer customer;

	
	public PaymentInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PaymentInfo(int paymentId, String cardType, String nameOnCard, long cardNum, Date expiryDate, long cvv,
			double paymentAmount, Customer customer) {
		super();
		this.paymentId = paymentId;
		this.cardType = cardType;
		this.nameOnCard = nameOnCard;
		this.cardNum = cardNum;
		this.expiryDate = expiryDate;
		this.cvv = cvv;
		this.paymentAmount = paymentAmount;
		
	}

	public int getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getNameOnCard() {
		return nameOnCard;
	}

	public void setNameOnCard(String nameOnCard) {
		this.nameOnCard = nameOnCard;
	}

	public long getCardNum() {
		return cardNum;
	}

	public void setCardNum(long cardNum) {
		this.cardNum = cardNum;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public long getCvv() {
		return cvv;
	}

	public void setCvv(long cvv) {
		this.cvv = cvv;
	}

	public double getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(double paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	
}
