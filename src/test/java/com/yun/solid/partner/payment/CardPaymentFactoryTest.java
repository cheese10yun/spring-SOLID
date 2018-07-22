package com.yun.solid.partner.payment;

import com.yun.solid.partner.shinhan.ShinhanCardPaymentService;
import com.yun.solid.partner.woori.WooriCardPaymentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class CardPaymentFactoryTest {

    @InjectMocks
    private CardPaymentFactory cardPaymentFactory;

    @Mock
    private WooriCardPaymentService wooriCardPaymentService;

    @Mock
    private ShinhanCardPaymentService shinhanCardPaymentService;


    @Test
    public void getType_wooriCardPaymentService() {
        final CardPaymentService cardPaymentService = cardPaymentFactory.getType(CardType.WOORI);
        assertThat(cardPaymentService, is(wooriCardPaymentService));
    }

    @Test
    public void getType_shinhanCardPaymentService() {
        final CardPaymentService cardPaymentService = cardPaymentFactory.getType(CardType.SHINHAN);
        assertThat(cardPaymentService, is(shinhanCardPaymentService));
    }
}