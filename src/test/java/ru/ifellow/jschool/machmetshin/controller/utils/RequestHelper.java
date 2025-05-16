package ru.ifellow.jschool.machmetshin.controller.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import ru.ifellow.jschool.machmetshin.controller.utils.dto.RequestDto;

import java.util.List;


public class RequestHelper {

    public static void setAuthentication(String username, String role) {
        SecurityContextHolder.getContext().setAuthentication(
                new TestingAuthenticationToken(username, null, List.of(new SimpleGrantedAuthority("ROLE_" + role)))
        );
    }

    public static void send(RequestDto requestDto, Object dto) throws Exception {

        ObjectMapper objectMapper = requestDto.getObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = requestDto.getMockMvcRequestBuilder();

        requestBuilder = requestBuilder
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto));

        perform(requestDto, requestBuilder);
    }

    public static void send(RequestDto requestDto) throws Exception {
        perform(requestDto, requestDto.getMockMvcRequestBuilder());
    }

    public static <T> T send(RequestDto requestDto, Object dto, Class<T> responseClass) throws Exception {
        return readResponse(requestDto, responseClass, null, dto);
    }

    public static <T> T send(RequestDto requestDto, Object dto, TypeReference<T> typeReference) throws Exception {
        return readResponse(requestDto, null, typeReference, dto);
    }

    public static <T> T send(RequestDto requestDto, Class<T> responseClass) throws Exception {
        return readResponse(requestDto, responseClass, null, null);
    }

    public static <T> T send(RequestDto requestDto, TypeReference<T> typeReference) throws Exception {
        return readResponse(requestDto, null, typeReference, null);
    }

    private static <T> T readResponse(RequestDto requestDto, Class<T> responseClass, TypeReference<T> typeReference, Object sendDto) throws Exception {
        ObjectMapper objectMapper = requestDto.getObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = requestDto.getMockMvcRequestBuilder();

        if(sendDto != null )
            requestBuilder = requestBuilder
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(sendDto));

        MvcResult result = perform(requestDto, requestBuilder).andReturn();

        String responseJson = result.getResponse().getContentAsString();

        if (responseClass != null)
            return objectMapper.readValue(responseJson, responseClass);

        else if (typeReference != null)
            return objectMapper.readValue(responseJson, typeReference);

        else
            throw new IllegalArgumentException("There should be not null responseClass or typeReference");
    }

    private static ResultActions perform(RequestDto requestDto, MockHttpServletRequestBuilder requestBuilder) throws Exception {
        MockMvc mockMvc = requestDto.getMockMvc();
        ResultMatcher expectedStatus = requestDto.getExpectedStatus();

        return mockMvc.perform(requestBuilder)
                .andExpect(expectedStatus);
    }
}
