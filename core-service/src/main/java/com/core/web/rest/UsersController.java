package com.core.web.rest;

import com.core.domain.Address;
import com.core.domain.User;
import com.core.web.rest.dto.modelDtos.AddressDto;
import com.core.web.rest.dto.modelDtos.UserDto;
import com.core.web.rest.dto.postDtos.users.PostUserBody;
import com.core.web.rest.dto.responseDtos.GetUsersResponseDto;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;


@RestController
public class UsersController {

    @GetMapping("/users")
    public ResponseEntity<GetUsersResponseDto> getAllUsers(@RequestParam(name = "search", required = false) String search, @RequestParam(name = "start", required = false) String start, @RequestParam(name = "limit", required = false) String limit) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8083/users";
        ResponseEntity<GetUsersResponseDto> response = restTemplate.exchange(
            url,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<GetUsersResponseDto>() {
            });
        GetUsersResponseDto users = response.getBody();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/me")
    public ResponseEntity<UserDto> getCredentials() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8083/me";
        ResponseEntity<User> response = restTemplate.exchange(
            url,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<User>() {
            });
        Optional<User> user = Optional.ofNullable(response.getBody());
        User current_user = user.get();
        AddressDto address = new AddressDto(current_user.getAddress());
        return new ResponseEntity<>(new UserDto(current_user, address), HttpStatus.OK);
    }

    @PutMapping("/me")
    public void editCredentials(@RequestBody PostUserBody newUser) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8083/me";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<PostUserBody> requestEntity = new HttpEntity<>(newUser, headers);

        ResponseEntity<User> response = restTemplate.exchange(
            url,
            HttpMethod.PUT,
            requestEntity,
            new ParameterizedTypeReference<>() {
            });
    }
}
