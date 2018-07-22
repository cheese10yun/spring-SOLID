//package com.yun.solid;
//
//import com.yun.solid.partner.shinhan.ShinhanCardDto;
//import lombok.AllArgsConstructor;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@AllArgsConstructor
//public class DIPAntiController {
//
//
//    @RequestMapping(value = "/dip/anti/payment", method = RequestMethod.POST)
//    public void pay(@RequestBody ShinhanCardDto.PaymentRequest req){
////        shinhanCardPaymentService.pay(req); // 안티패턴 하위 정책인 ShinhanCardPaymentService에 지나치게 의존적이다.
//    }
//
//}
