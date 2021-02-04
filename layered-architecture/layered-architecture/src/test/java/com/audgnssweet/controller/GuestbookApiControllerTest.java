package com.audgnssweet.controller;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.audgnssweet.dto.Guestbook;
import com.audgnssweet.service.GuestbookService;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


/*
MockMvc와 Mockito를 이용한 WEB API 단위 테스트
 */
@ExtendWith(MockitoExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
public class GuestbookApiControllerTest {

    @InjectMocks
    private GuestbookApiController guestbookApiController;

    @Mock
    private GuestbookService guestbookService;

    private MockMvc mockMvc;

    /*
    test ignored 오류.
    1번째 시도 - static method로 바꾸기 - 실패
    2번쨰 시도 - springjunitwebconfig 어노테이션 붙이기 - 실패
    3번째 시도 - TestInstance의 lifecycle을 바꿔주기 - 성공
     */
    @BeforeAll
    public void createController() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(guestbookApiController).build();
    }

    @Test
    void 전체다받아오기() throws Exception {
        Guestbook guestbook = new Guestbook();
        guestbook.setId(1);
        guestbook.setName("정명훈");
        guestbook.setContent("으아악");
        guestbook.setRegdate(new Date());

        List<Guestbook> guestbooks = Collections.singletonList(guestbook);
        when(guestbookService.getGuestbooksOnePage(0)).thenReturn(guestbooks);

        RequestBuilder builder = MockMvcRequestBuilders.get("/guestbooks")
            .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(builder).andExpect(status().isOk()).andDo(print());

        verify(guestbookService).getGuestbooksOnePage(0);
    }

    @Test
    void 삭제() throws Exception {
        Integer id = 1;

        when(guestbookService.deleteGuestbook(id, "127.0.0.1")).thenReturn(1);

        RequestBuilder builder = MockMvcRequestBuilders.delete("/guestbooks/" + id)
            .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(builder).andExpect(status().isOk()).andDo(print());

        verify(guestbookService).deleteGuestbook(id, "127.0.0.1");
    }

}
