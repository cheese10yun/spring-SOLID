package com.yun.solid;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(MockitoJUnitRunner.class)
public class PartnerPaymentControllerTest {

    @InjectMocks
    private PartnerPaymentController partnerPaymentController;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(partnerPaymentController)
                .build();
    }

    @Test
    public void pay() throws Exception {
        final String json = "{\"shinhanCardNumber\":\"str\",\"cvc\":\"str\"}";
        final ResultActions request = request("shinhan", json);
        request.andExpect(status().isOk());
    }

    @Test
    public void pay1() throws Exception {
        final String json = "{\"number\":\"str\",\"CVS\":\"str\"}";
        final ResultActions request = request("woori", json);
        request.andExpect(status().isOk());
    }

    private ResultActions request(final String url, String json) throws Exception {
        return mockMvc.perform(post("/" + url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print());
    }
}