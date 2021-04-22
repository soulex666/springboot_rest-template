package com.andreev;

import com.andreev.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class Communication {
    private static final String GET_USERS_ENDPOINT_URL = "http://91.241.64.178:7081/api/users";
    private static final String CREATE_USER_ENDPOINT_URL = "http://91.241.64.178:7081/api/users";
    private static final String UPDATE_USER_ENDPOINT_URL = "http://91.241.64.178:7081/api/users";
    private static final String DELETE_USER_ENDPOINT_URL = "http://91.241.64.178:7081/api/users/{id}";

    @Autowired
    private RestTemplate restTemplate;

    HttpHeaders headers = new HttpHeaders();

    public List<User> getUsers() {

        ResponseEntity<List<User>> responseEntity =
                restTemplate.exchange(GET_USERS_ENDPOINT_URL, HttpMethod.GET, null, new ParameterizedTypeReference<>() {});

        ResponseEntity<String> forEntity = restTemplate.getForEntity(GET_USERS_ENDPOINT_URL, String.class);
        String header = forEntity.getHeaders().getFirst("Set-Cookie");
        headers.add("Cookie", header);

        return responseEntity.getBody();
    }

    public void saveUser(User user) {

        HttpEntity<User> request = new HttpEntity<>(user, headers);

        ResponseEntity<String> response = restTemplate.exchange(CREATE_USER_ENDPOINT_URL, HttpMethod.POST, request, String.class);
        System.out.print(response.getBody());

    }

    public void editUser(User user) {

        HttpEntity<User> request = new HttpEntity<>(user, headers);

        ResponseEntity<String> response = restTemplate.exchange(UPDATE_USER_ENDPOINT_URL, HttpMethod.PUT, request, String.class);

        System.out.print(response.getBody());
    }

    public void deleteUser() {
        Map < String, String > params = new HashMap <> ();
        params.put("id", "3");

        HttpEntity<Void> request = new HttpEntity<>(null, headers);

        ResponseEntity<String> response =
                restTemplate.exchange(DELETE_USER_ENDPOINT_URL, HttpMethod.DELETE, request, String.class, params);

        System.out.print(response.getBody());
    }
}
