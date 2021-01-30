package com.priya.rightcare.DTO;

import com.priya.rightcare.Entity.Customer;
import com.priya.rightcare.Entity.PaymentInfo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentReq {
	
	private PaymentInfo  paymentInfo;

	public PaymentInfo getPaymentInfo() {
		return paymentInfo;
	}

	public void setPaymentInfo(PaymentInfo paymentInfo) {
		this.paymentInfo = paymentInfo;
	}
	
	
	

}
