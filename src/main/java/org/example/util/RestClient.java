package org.example.util;

import org.example.dto.request.UserDTO;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class RestClient<T> {
    private String server = "http://localhost:8081/";
    private RestTemplate template;
    private HttpHeaders headers;
    private HttpStatusCode status;

    public RestClient() {
        template = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
        headers = new HttpHeaders();
        headers.add("Accept", "*/*");
        headers.add("content-type", "application/json");
        headers.add("Authorization", TokenStorage.getToken());
    }
    public T post(String uri, UserDTO userDTO, Class<T> type) {
        HttpEntity<UserDTO> requestEntity = new HttpEntity<>(userDTO, headers);
        ResponseEntity<T> responseEntity = template.exchange(server + uri, HttpMethod.POST, requestEntity,type);
        status = responseEntity.getStatusCode();
        return responseEntity.getBody();
    }
    public T get(String uri, Class<T> type) {
        HttpEntity<String> requestEntity = new HttpEntity<>("", headers);
        ResponseEntity<T> responseEntity = template.exchange(server + uri, HttpMethod.GET, requestEntity, type);
        status = responseEntity.getStatusCode();
        return responseEntity.getBody();
    }

    public String delete(String uri, Class<T> type) {
        HttpEntity<String> requestEntity = new HttpEntity<>("", headers);
        ResponseEntity<T> responseEntity = template.exchange(server + uri, HttpMethod.DELETE, requestEntity, type);
        return (String) responseEntity.getBody();
    }

}
