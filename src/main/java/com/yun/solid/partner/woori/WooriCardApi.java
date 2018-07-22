package com.yun.solid.partner.woori;

import com.yun.solid.partner.payment.CardPaymentDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@AllArgsConstructor
public class WooriCardApi {

    private final RestTemplate restTemplate;

    public void pay(CardPaymentDto.PaymentRequest req){
        final WooriCardDto.PaymentRequest paymentInfo = buildPayment(req);
        restTemplate.postForObject("http://localhost:8080/woori", paymentInfo, Void.class);
    }

    private WooriCardDto.PaymentRequest buildPayment(CardPaymentDto.PaymentRequest req) {
        return WooriCardDto.PaymentRequest.builder()
                .number(req.getCardNumber())
                .CVS(req.getCsv())
                .build();
    }

}
