//package com.yun.solid.partner.payment;
//
//import lombok.AllArgsConstructor;
//import org.springframework.stereotype.Component;
//
//@Component
//@AllArgsConstructor
//public class CardPaymentManager {
//
//    private final CardPaymentFactory cardPaymentFactory;
//
//    public void pay(CardPaymentDto.PaymentRequest req){
//        final CardPaymentService cardPayment = cardPaymentFactory.getType(req.getType());
//        cardPayment.pay(req);
//    }
//
//
//}
