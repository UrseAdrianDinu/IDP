package com.core.web.rest;

import com.core.domain.User;
import com.core.web.rest.dto.modelDtos.ItemDto;
import com.core.web.rest.dto.postDtos.users.PostUserBody;
import com.core.web.rest.dto.responseDtos.GetItemsResponseDto;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class ItemsController {

    @GetMapping("/items")
    public ResponseEntity<GetItemsResponseDto> getAllItems(@RequestParam(name="search", required=false) String search, @RequestParam(name="start", required=false) String start, @RequestParam(name="limit", required=false) String limit) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8083/items";
        ResponseEntity<GetItemsResponseDto> response = restTemplate.exchange(
            url,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<GetItemsResponseDto>(){});
        GetItemsResponseDto items = response.getBody();
        return new ResponseEntity<>(items, HttpStatus.OK);
    }
    

    @PutMapping("/items/{id}")
    public void editItem(@PathVariable("id") int id, @RequestBody ItemDto newItem) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8083/items/" + id;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ItemDto> requestEntity = new HttpEntity<>(newItem, headers);

        ResponseEntity<User> response = restTemplate.exchange(
            url,
            HttpMethod.PUT,
            requestEntity,
            new ParameterizedTypeReference<>() {
        });
    }

    @PostMapping("/items")
    public ItemDto newItem(@RequestBody ItemDto newItem) {

        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8083/items";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ItemDto> requestEntity = new HttpEntity<>(newItem, headers);

        ResponseEntity<ItemDto> response = restTemplate.exchange(
            url,
            HttpMethod.POST,
            requestEntity,
            new ParameterizedTypeReference<>() {
            });

        return response.getBody();
    }


    @DeleteMapping("/items/{id}")
    public void deleteItem(@PathVariable("id") int id) {

        String url = "http://localhost:8083/items/" + id;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Object> response = restTemplate.exchange(
            url,
            HttpMethod.DELETE,
            null,
            new ParameterizedTypeReference<>() {
            });
    }
}
