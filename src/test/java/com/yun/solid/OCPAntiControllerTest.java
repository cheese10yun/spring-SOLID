package com.yun.solid;

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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class OCPAntiControllerTest {

    @InjectMocks
    private OCPAntiController ocpAntiController;

    @Mock
    private ShinhanCardPaymentService shinhanCardPaymentService;

    @Mock
    private WooriCardPaymentService wooriCardPaymentService;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(ocpAntiController)
                .build();
    }

    @Test
    public void pay_woor() throws Exception {
        final String json = "{\n" +
                "  \"cardNumber\":\"str\",\n" +
                "  \"csv\":\"str\",\n" +
                "  \"type\": \"WOORI\"\n" +
                "}";
        final ResultActions request = request("ocp/anti/payment", json);
        request.andExpect(status().isOk());
    }

    @Test
    public void pay_shinhan() throws Exception {
        final String json = "{\n" +
                "  \"cardNumber\":\"str\",\n" +
                "  \"csv\":\"str\",\n" +
                "  \"type\": \"SHINHAN\"\n" +
                "}";
        final ResultActions request = request("ocp/anti/payment", json);
        request.andExpect(status().isOk());
    }

    private ResultActions request(final String url, String json) throws Exception {
        return mockMvc.perform(post("/" + url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print());
    }
}