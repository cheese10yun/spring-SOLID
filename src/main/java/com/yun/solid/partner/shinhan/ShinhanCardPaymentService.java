package com.yun.solid.partner.shinhan;

import com.yun.solid.partner.payment.CardPaymentDto;
import com.yun.solid.partner.payment.CardPaymentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ShinhanCardPaymentService implements CardPaymentService {

    private final ShinhanCardApi shinhanCardApi;

    @Override
    public void pay(CardPaymentDto.PaymentRequest req) {
        final ShinhanCardDto.PaymentRequest paymentRequest = buildPayment(req);
        shinhanCardApi.pay(paymentRequest);
    }

    private ShinhanCardDto.PaymentRequest buildPayment(CardPaymentDto.PaymentRequest req) {
        return ShinhanCardDto.PaymentRequest.builder()
                .shinhanCardNumber(req.getCardNumber())
                .cvc(req.getCsv())
                .build();
    }


//안티 패턴 shinhanCardApi 변경시 자신을 참조하는 모든곳에서 변경이 발생할 가능성이 큼
//    public void pay(ShinhanCardDto.PaymentRequest req) {
//        shinhanCardApi.pay(req);
//    }
}
