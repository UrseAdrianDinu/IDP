package com.core.web.rest;

import com.core.domain.User;
import com.core.web.rest.dto.modelDtos.ItemDto;
import com.core.web.rest.dto.postDtos.items.PutItemBuyersBody;
import com.core.web.rest.dto.postDtos.users.PostUserBody;
import com.core.web.rest.dto.responseDtos.GetItemsResponseDto;
import com.core.web.rest.dto.responseDtos.GetWishlistsResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import com.core.web.rest.dto.postDtos.wishlists.PostWishlistBody;

import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class WishlistsController {
    @GetMapping("/wishlists")
    public ResponseEntity<GetWishlistsResponseDto> getAllNotifications(@RequestParam(name="search", required=false) String search, @RequestParam(name="start", required=false) String start, @RequestParam(name="limit", required=false) String limit) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8083/wishlists";
        ResponseEntity<GetWishlistsResponseDto> response = restTemplate.exchange(
            url,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<GetWishlistsResponseDto>(){});
        GetWishlistsResponseDto wishlists = response.getBody();
        return new ResponseEntity<>(wishlists, HttpStatus.OK);
    }

    @PostMapping("/wishlists")
    public PostWishlistBody newWishlist(@RequestBody PostWishlistBody newWishlist) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8083/wishlists";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<PostWishlistBody> requestEntity = new HttpEntity<>(newWishlist, headers);

        ResponseEntity<PostWishlistBody> response = restTemplate.exchange(
            url,
            HttpMethod.POST,
            requestEntity,
            new ParameterizedTypeReference<>() {
            });

        return response.getBody();
    }

    @PutMapping("/wishlists/{id}")
    public void editWishlist(@PathVariable("id") int id, @RequestBody PostWishlistBody newWishlist) {RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8083/wishlists/" + id;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<PostWishlistBody> requestEntity = new HttpEntity<>(newWishlist, headers);

        ResponseEntity<User> response = restTemplate.exchange(
            url,
            HttpMethod.PUT,
            requestEntity,
            new ParameterizedTypeReference<>() {
        });
    }

    @PutMapping("/wishlists/{wishlist_id}/items/{item_id}/buy")
    public void purchaseItem(@PathVariable("wishlist_id") int wishlist_id, @PathVariable("item_id") int item_id,@RequestBody PutItemBuyersBody buyersIds) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8083/wishlists/" + wishlist_id + "/items/" + item_id + "/buy";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<PutItemBuyersBody> requestEntity = new HttpEntity<>(buyersIds, headers);

        ResponseEntity<User> response = restTemplate.exchange(
            url,
            HttpMethod.PUT,
            requestEntity,
            new ParameterizedTypeReference<>() {
        });
    }

    @DeleteMapping("/wishlists/{id}")
    public void deleteWishlist(@PathVariable("id") int id) {
        String url = "http://localhost:8083/wishlists/" + id;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Object> response = restTemplate.exchange(
            url,
            HttpMethod.DELETE,
            null,
            new ParameterizedTypeReference<>() {
            });
    }
}
