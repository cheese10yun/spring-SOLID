package com.yun.solid.partner.shinhan;

import com.yun.solid.partner.payment.CardPaymentDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@AllArgsConstructor
public class ShinhanCardApi {

    private RestTemplate restTemplate;

    public void pay(CardPaymentDto.PaymentRequest req) {
        final ShinhanCardDto.PaymentRequest paymentInfo = buildPayment(req);
        restTemplate.postForObject("http://localhost:8080/shinhan", paymentInfo, Void.class);
    }

//
//    public void pay(ShinhanCardDto.PaymentRequest req) {
//        restTemplate.postForObject("http://localhost:8080/shinhan", req, Void.class);
//    }

    private ShinhanCardDto.PaymentRequest buildPayment(CardPaymentDto.PaymentRequest req) {
        return ShinhanCardDto.PaymentRequest.builder()
                .shinhanCardNumber(req.getCardNumber())
                .cvc(req.getCsv())
                .build();
    }


}
