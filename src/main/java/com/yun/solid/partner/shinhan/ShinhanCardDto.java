package com.yun.solid.partner.shinhan;

import lombok.*;

public class ShinhanCardDto {

    @Getter
    @ToString
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class PaymentRequest {
        private String shinhanCardNumber;
        private String cvc;

        @Builder
        public PaymentRequest(String shinhanCardNumber, String cvc) {
            this.shinhanCardNumber = shinhanCardNumber;
            this.cvc = cvc;
        }
    }
}
