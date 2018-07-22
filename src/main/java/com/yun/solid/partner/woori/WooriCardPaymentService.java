package com.yun.solid.partner.woori;

import com.yun.solid.partner.payment.CardPaymentDto;
import com.yun.solid.partner.payment.CardPaymentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class WooriCardPaymentService implements CardPaymentService {

    private WooriCardApi wooriCardApi;

    @Override
    public void pay(CardPaymentDto.PaymentRequest req) {
        wooriCardApi.pay(req);
    }
}
