package com.yun.solid.partner.shinhan;

import com.yun.solid.partner.payment.CardPaymentDto;
import com.yun.solid.partner.payment.CardType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ShinhanCardPaymentServiceTest {

    @InjectMocks
    private ShinhanCardPaymentService shinhanCardPaymentService;

    @Mock
    private  ShinhanCardApi shinhanCardApi;

    @Test
    public void pay() {

        final CardPaymentDto.PaymentRequest request = CardPaymentDto.PaymentRequest.builder()
                .cardNumber("card")
                .csv("csv")
                .type(CardType.SHINHAN)
                .build();

        shinhanCardPaymentService.pay(request);

        verify(shinhanCardApi, atLeastOnce()).pay(any());
    }
}