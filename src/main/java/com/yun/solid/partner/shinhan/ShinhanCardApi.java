package com.yun.solid.partner.shinhan;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@AllArgsConstructor
public class ShinhanCardApi {

    private RestTemplate restTemplate;

    public void pay(ShinhanCardDto.PaymentRequest req) {
        restTemplate.postForObject("http://localhost:8080/shinhan", req, Void.class);
    }

//    public void pay(ShinhanCardDto.PaymentRequest req) {
//        restTemplate.postForObject("http://localhost:8080/shinhan", req, Void.class);
//    }




}
