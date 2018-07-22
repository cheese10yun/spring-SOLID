package com.yun.solid.partner.payment;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class CardPaymentDto {

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @Getter
    public static class PaymentRequest {
        private String cardNumber;
        private String csv;
        private CardType type;

        @Builder
        public PaymentRequest(String cardNumber, String csv, CardType type) {
            this.cardNumber = cardNumber;
            this.csv = csv;
            this.type = type;
        }
    }

}
