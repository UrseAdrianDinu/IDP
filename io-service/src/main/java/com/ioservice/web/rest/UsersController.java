package com.ioservice.web.rest;

import com.ioservice.domain.Address;
import com.ioservice.domain.User;
import com.ioservice.repository.AddresesRepository;
import com.ioservice.repository.UsersRepository;
import com.ioservice.web.rest.dto.modelDtos.AddressDto;
import com.ioservice.web.rest.dto.modelDtos.UserDto;
import com.ioservice.web.rest.dto.postDtos.users.PostUserBody;
import com.ioservice.web.rest.dto.responseDtos.GetUsersResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class UsersController {

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    AddresesRepository addresesRepository;

    @GetMapping("/users")
    public ResponseEntity<GetUsersResponseDto> getAllUsers(@RequestParam(name="search", required=false) String search, @RequestParam(name="start", required=false) String start, @RequestParam(name="limit", required=false) String limit) {

        try {

            List<User> users = new ArrayList<User>();

            if (search != null) {
                users.addAll(usersRepository.findByNameContaining(search));
            } else {
                users.addAll(usersRepository.findAll());
            }
            if (users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            List<User> wantedUsers = new ArrayList<>();
            if (search != null && start != null && limit != null) {
                int end = Math.min(Integer.parseInt(start) + Integer.parseInt(limit), users.size());

                for (int i = Integer.parseInt(start); i < end; i++) {
                    wantedUsers.add(users.get(i));
                }
            } else {
                wantedUsers = users;
            }

            if (wantedUsers.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            List<UserDto> userDtos = new ArrayList<UserDto>();

            for(User user : wantedUsers) {
                AddressDto addressDto = null;
                if(user.getAddress() != null) {
                    addressDto = new AddressDto(user.getAddress());
                }
                userDtos.add(new UserDto(user, addressDto));
            }

            return new ResponseEntity<>(new GetUsersResponseDto(userDtos.size(), userDtos), HttpStatus.OK);


        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/me")
    public ResponseEntity<UserDto> getCredentials() {
        Optional<User> user = usersRepository.findById(1L);
        User current_user = user.get();
        AddressDto address = new AddressDto(current_user.getAddress());
        return new ResponseEntity<>(new UserDto(current_user, address), HttpStatus.OK);
    }

    @PutMapping("/me")
    public void editCredentials(@RequestBody PostUserBody newUser) {
        Optional<User> user = usersRepository.findById(1L);

        if(user.isPresent()) {
            User current_user = user.get();
//            System.out.println(current_user);
            current_user.setName(newUser.getName());
            current_user.setPhone(newUser.getPhone());
            if(newUser.getDob() != null) {
                try {
                    current_user.setDob(new SimpleDateFormat("yyyy-MM-dd").parse(newUser.getDob()));
                } catch (java.text.ParseException e) {
                    e.printStackTrace();
                }
            }
            usersRepository.save(current_user);

            Address current_user_address = current_user.getAddress();
            current_user_address.setCity(newUser.getAddress().getCity());
            current_user_address.setStreet(newUser.getAddress().getStreet());
            current_user_address.setZip(newUser.getAddress().getZip());
            current_user_address.setCountry(newUser.getAddress().getCountry());
            addresesRepository.save(current_user_address);
        }
    }

}
