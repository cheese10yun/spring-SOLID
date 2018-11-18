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
        //우리 카드 결제 API...
        final WooriCardDto.PaymentRequest paymentRequest = buildPayment(req);
        wooriCardApi.pay(paymentRequest);
    }

    @Override
    public void payOverseas(CardPaymentDto.PaymentRequest req) {
        // 우리 카드 결제는 해외 결제 기능이 없음...
    }

    private WooriCardDto.PaymentRequest buildPayment(CardPaymentDto.PaymentRequest req) {
        return WooriCardDto.PaymentRequest.builder()
                .number(req.getCardNumber())
                .CVS(req.getCsv())
                .build();
    }
}
