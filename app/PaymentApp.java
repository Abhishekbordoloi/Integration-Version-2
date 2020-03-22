package app;
import java.util.*;

import service.PaymentService;
import service.impl.PaymentServiceImpl;
import until.enumeration.PaymentType;

public class PaymentApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner (System.in);
		System.out.println("Welcome to payment Service");
		PaymentService paymentService= new PaymentServiceImpl();
		System.out.println("Please enter classifiedID");
		int cID = scan.nextInt();
		System.out.println("Please select payment options: ");
		for (PaymentType type : PaymentType.values()) {
			System.out.println(String.format("Press %d for payement using %s", type.ordinal()+1, type.getPaymentMode()));
			}
		int pID = scan.nextInt();
		String msg= paymentService.doPayment(cID, pID);
		System.out.println(msg);
		scan.close();
	}

}
