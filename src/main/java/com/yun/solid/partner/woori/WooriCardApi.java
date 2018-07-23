package com.yun.solid.partner.woori;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@AllArgsConstructor
public class WooriCardApi {

    private final RestTemplate restTemplate;

    public void pay(WooriCardDto.PaymentRequest req){
        restTemplate.postForObject("http://localhost:8080/woori", req, Void.class);
    }

}
