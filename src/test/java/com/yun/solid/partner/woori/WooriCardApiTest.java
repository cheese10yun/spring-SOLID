package com.yun.solid.partner.woori;

import com.yun.solid.partner.payment.CardPaymentDto;
import com.yun.solid.partner.payment.CardType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class WooriCardApiTest {

    @InjectMocks
    private WooriCardApi wooriCardApi;

    @Mock
    private RestTemplate restTemplate;

    @Test
    public void pay() {

        //given

        final WooriCardDto.PaymentRequest paymentRequest = WooriCardDto.PaymentRequest.builder()
                .number("card..")
                .CVS("CSV")
                .build();


        //when
        wooriCardApi.pay(paymentRequest);

        //then
        verify(restTemplate, atLeastOnce()).postForObject(anyString(), any(), any());


    }
}