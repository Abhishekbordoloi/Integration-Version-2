package until.enumeration;

public enum PaymentType {
	NET_BANKING(1, "Net Banking"),
	CREDIT_OR_DEBIT_CARD(2, "Credit/Debit Card"),
	CASH(3, "Cash");
	
	private String paymentMode;
	
	PaymentType(int index, String paymentMode){
		this.paymentMode = paymentMode;
	}
	
	public String getPaymentMode() {
		return paymentMode;
	}
	
	public static String getPaymentMode(int index) {
		switch (index) {
		case 1:
			return NET_BANKING.getPaymentMode();
		case 2:
			return CREDIT_OR_DEBIT_CARD.getPaymentMode();
		case 3:
			return CASH.getPaymentMode();
		default:
			return null;
		}
	}
}
