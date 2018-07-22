package com.yun.solid;

import com.yun.solid.partner.payment.CardPaymentFactory;
import com.yun.solid.partner.shinhan.ShinhanCardPaymentService;
import com.yun.solid.partner.woori.WooriCardPaymentService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class PaymentControllerTest {

    @InjectMocks
    private PaymentController paymentController;

    @Mock
    private CardPaymentFactory cardPaymentFactory;

    @Mock
    private WooriCardPaymentService wooriCardPaymentService;

    @Mock
    private ShinhanCardPaymentService shinhanCardPaymentService;



    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(paymentController)
                .build();
    }

    @Test
    public void payment_Woori() throws Exception {
        given(cardPaymentFactory.getType(any())).willReturn(wooriCardPaymentService);
        final String json = "{\n" +
                "  \"cardNumber\":\"str\",\n" +
                "  \"csv\":\"str\",\n" +
                "  \"type\": \"WOORI\"\n" +
                "}";

        final ResultActions request = request("payment", json);
        request.andExpect(status().isOk());
    }

    @Test
    public void payment_Shinhan() throws Exception {
        given(cardPaymentFactory.getType(any())).willReturn(shinhanCardPaymentService);
        final String json = "{\n" +
                "  \"cardNumber\":\"str\",\n" +
                "  \"csv\":\"str\",\n" +
                "  \"type\": \"SHINHAN\"\n" +
                "}";

        final ResultActions request = request("payment", json);
        request.andExpect(status().isOk());
    }

    private ResultActions request(final String url, String json) throws Exception {
        return mockMvc.perform(post("/" + url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print());
    }

}