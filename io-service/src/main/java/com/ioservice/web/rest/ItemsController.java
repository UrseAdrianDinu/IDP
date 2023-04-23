package com.ioservice.web.rest;

import com.ioservice.domain.Item;
import com.ioservice.domain.User;
import com.ioservice.domain.Wishlist;
import com.ioservice.repository.ItemsRepository;
import com.ioservice.repository.UsersRepository;
import com.ioservice.web.rest.dto.modelDtos.ItemDto;
import com.ioservice.web.rest.dto.responseDtos.GetItemsResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class ItemsController {

    @Autowired
    ItemsRepository itemsRepository;
    @Autowired
    UsersRepository usersRepository;

    @GetMapping("/items")
    public ResponseEntity<GetItemsResponseDto> getAllItems(@RequestParam(name="search", required=false) String search, @RequestParam(name="start", required=false) String start, @RequestParam(name="limit", required=false) String limit) {
        try {

            List<Item> items = new ArrayList<Item>();
            Optional<User> possible_current_user = usersRepository.findById(1L);
            if(!possible_current_user.isPresent()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            User current_user = possible_current_user.get();

            items.addAll(current_user.getItems());
            items.sort(Comparator.comparing(Item::getItem_id).reversed());

            if (items.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            List<Item> wantedItems = new ArrayList<>();
            if (search != null && start != null && limit != null) {
                List<Item> matchingItems = new ArrayList<>();
                if(!search.equals("")) {
                    for(Item item : items) {
                        if(item.getName().toLowerCase().contains(search.toLowerCase())) {
                            matchingItems.add(item);
                        }
                    }
                } else {
                    matchingItems = items;
                }

                int end = Math.min(Integer.parseInt(start) + Integer.parseInt(limit), matchingItems.size());
                for (int i = Integer.parseInt(start); i < end; i++) {
                    wantedItems.add(matchingItems.get(i));
                }

                if (wantedItems.isEmpty()) {
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }
            } else {
                wantedItems = items;
            }

            List<ItemDto> itemsDtos = new ArrayList<ItemDto>();
            for(Item item : wantedItems) {
                itemsDtos.add(new ItemDto(item));
            }
            return new ResponseEntity<>(new GetItemsResponseDto(items.size(), itemsDtos), HttpStatus.OK);


        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/items")
    public ItemDto newItem(@RequestBody ItemDto newItem) {
        //System.out.println(newItem);
        Optional<User> user = usersRepository.findById(1L);
        if(user.isPresent()) {
            itemsRepository.save(new Item(newItem.getName(), newItem.getDetails(), newItem.getQuantity(), newItem.getSize(), newItem.getMaker(), newItem.getModel(), newItem.getLink(), user.get(), Collections.<Wishlist>emptySet(), Collections.<User>emptySet()));
        }
        return newItem;
    }

    @PutMapping("/items/{id}")
    public void editItem(@PathVariable("id") int id, @RequestBody ItemDto newItem) {
        Optional<Item> item = itemsRepository.findById(Long.valueOf(id));
        if(item.isPresent()) {
            Item our_item = item.get();
            our_item.setName(newItem.getName());
            our_item.setDetails(newItem.getDetails());
            our_item.setQuantity(newItem.getQuantity());
            our_item.setSize(newItem.getSize());
            our_item.setMaker(newItem.getMaker());
            our_item.setModel(newItem.getModel());
            our_item.setLink(newItem.getLink());
            itemsRepository.save(our_item);
        }
    }

    @DeleteMapping("/items/{id}")
    public void deleteItem(@PathVariable("id") int id) {
        itemsRepository.deleteById(Long.valueOf(id));
    }
}
