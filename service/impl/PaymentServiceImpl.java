package service.impl;

import service.PaymentService;
import until.enumeration.PaymentType;

public class PaymentServiceImpl implements PaymentService {

	@Override
	public String doPayment(int classifiedId, int paymentTypeId) {
		String response = null;
		// *check if entered classified is present in DB and unsold
		String paymentMode = PaymentType.getPaymentMode(paymentTypeId);
		if (paymentMode == null) {
			response = "Please enter valid payment option";
		} else {
			response = String.format("Payment successfull with %s", paymentMode);
		}
		System.out.println(String.format("doPayment message : %s for classifiedId : %d", response, classifiedId));
		return response;
	}
}
