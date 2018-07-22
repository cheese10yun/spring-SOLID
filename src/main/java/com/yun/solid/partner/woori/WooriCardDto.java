package com.yun.solid.partner.woori;

import lombok.*;

public class WooriCardDto {

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @Getter
    @ToString
    public static class PaymentRequest {
        private String number;
        private String CVS;

        @Builder
        public PaymentRequest(String number, String CVS) {
            this.number = number;
            this.CVS = CVS;
        }
    }
}
