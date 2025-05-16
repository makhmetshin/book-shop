package ru.ifellow.jschool.machmetshin.controller.utils.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestDto {

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private MockHttpServletRequestBuilder mockMvcRequestBuilder;
    private ResultMatcher expectedStatus;
}
