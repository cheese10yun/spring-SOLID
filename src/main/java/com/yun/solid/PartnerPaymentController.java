package com.yun.solid;

import com.yun.solid.partner.shinhan.ShinhanCardDto;
import com.yun.solid.partner.woori.WooriCardDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Slf4j
public class PartnerPaymentController {

    // 외부 파트너를 호출했을 때 나오는 임시값
    @RequestMapping(value = "/shinhan", method = RequestMethod.POST)
    public void pay(@RequestBody ShinhanCardDto.PaymentRequest req) {
        log.error(req.toString());
    }

    @RequestMapping(value = "/woori", method = RequestMethod.POST)
    public void pay(@RequestBody WooriCardDto.PaymentRequest req) {
        log.error(req.toString());
    }

}
