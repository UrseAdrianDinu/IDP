package com.core.web.rest;

import com.core.domain.User;
import com.core.web.rest.dto.postDtos.groups.PutGroupMembersBody;
import com.core.web.rest.dto.postDtos.groups.PutGroupWishlistsBody;
import com.core.web.rest.dto.postDtos.users.PostUserBody;
import com.core.web.rest.dto.responseDtos.GetGroupsResponseDto;
import com.core.web.rest.dto.postDtos.groups.PostGroupBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class GroupsController {

    private static final Logger log = LoggerFactory.getLogger(GroupsController.class);


    @GetMapping("/groups")
    public ResponseEntity<GetGroupsResponseDto> getAllGroups(@RequestParam(name = "search", required = false) String search, @RequestParam(name = "start", required = false) String start, @RequestParam(name = "limit", required = false) String limit) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8083/groups";
        ResponseEntity<GetGroupsResponseDto> response = restTemplate.exchange(
            url,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<GetGroupsResponseDto>() {
            });
        GetGroupsResponseDto groups = response.getBody();
        return new ResponseEntity<>(groups, HttpStatus.OK);
    }

    @GetMapping("/groups/shared")
    public ResponseEntity<GetGroupsResponseDto> getAllSharedGroups(@RequestParam(name = "search", required = false) String search, @RequestParam(name = "start", required = false) String start, @RequestParam(name = "limit", required = false) String limit) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8083/groups/shared";
        ResponseEntity<GetGroupsResponseDto> response = restTemplate.exchange(
            url,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<GetGroupsResponseDto>() {
            });
        GetGroupsResponseDto groups = response.getBody();
        return new ResponseEntity<>(groups, HttpStatus.OK);
    }

    @PostMapping("/groups")
    public PostGroupBody newItem(@RequestBody PostGroupBody newGroup) {

        String url = "http://localhost:8083/groups";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<PostGroupBody> requestEntity = new HttpEntity<>(newGroup, headers);

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<PostGroupBody> response = restTemplate.exchange(
            url,
            HttpMethod.POST,
            requestEntity,
            new ParameterizedTypeReference<>() {
            });

        return response.getBody();
    }

    @PutMapping("/groups/{groupId}/users")
    public void editGroupMembers(@PathVariable("groupId") int id, @RequestBody PutGroupMembersBody userIds) {
        String url = "http://localhost:8083/groups/" + id + "/users";
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<PutGroupMembersBody> requestEntity = new HttpEntity<>(userIds, headers);

        ResponseEntity<Object> response = restTemplate.exchange(
            url,
            HttpMethod.PUT,
            requestEntity,
            new ParameterizedTypeReference<>() {
            });

    }

    @PutMapping("/groups/{groupId}/wishlists")
    public void editGroupWishlists(@PathVariable("groupId") int id, @RequestBody PutGroupWishlistsBody wishlistIds) {
        String url = "http://localhost:8083/groups/" + id + "/wishlists";
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<PutGroupWishlistsBody> requestEntity = new HttpEntity<>(wishlistIds, headers);

        ResponseEntity<Object> response = restTemplate.exchange(
            url,
            HttpMethod.PUT,
            requestEntity,
            new ParameterizedTypeReference<>() {
            });


    }

    @PutMapping("/groups/{id}")
    public void editGroupDetails(@PathVariable("id") int id, @RequestBody PostGroupBody edittedGroup) {
        String url = "http://localhost:8083/groups/" + id;
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<PostGroupBody> requestEntity = new HttpEntity<>(edittedGroup, headers);

        ResponseEntity<Object> response = restTemplate.exchange(
            url,
            HttpMethod.PUT,
            requestEntity,
            new ParameterizedTypeReference<>() {
            });
    }

    @DeleteMapping("/groups/{id}")
    public void deleteGroup(@PathVariable("id") int id) {
        String url = "http://localhost:8083/groups/" + id;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Object> response = restTemplate.exchange(
            url,
            HttpMethod.DELETE,
            null,
            new ParameterizedTypeReference<>() {
            });
    }
}
