package com.ioservice.web.rest;

import com.ioservice.domain.Group;
import com.ioservice.domain.Item;
import com.ioservice.domain.User;
import com.ioservice.domain.Wishlist;
import com.ioservice.repository.ItemsRepository;
import com.ioservice.repository.UsersRepository;
import com.ioservice.repository.WishlistsRepository;
import com.ioservice.web.rest.dto.modelDtos.ItemDto;
import com.ioservice.web.rest.dto.modelDtos.WishlistDto;
import com.ioservice.web.rest.dto.postDtos.items.PutItemBuyersBody;
import com.ioservice.web.rest.dto.postDtos.wishlists.PostWishlistBody;
import com.ioservice.web.rest.dto.responseDtos.GetWishlistsResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class WishlistsController {
    @Autowired
    WishlistsRepository wishlistsRepository;
    @Autowired
    UsersRepository usersRepository;

    @Autowired
    ItemsRepository itemsRepository;
    @GetMapping("/wishlists")
    public ResponseEntity<GetWishlistsResponseDto> getAllNotifications(@RequestParam(name="search", required=false) String search, @RequestParam(name="start", required=false) String start, @RequestParam(name="limit", required=false) String limit) {

        try {
            List<Wishlist> wishlists = new ArrayList<Wishlist>();
            Optional<User> possible_current_user = usersRepository.findById(1L);
            if(!possible_current_user.isPresent()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            User current_user = possible_current_user.get();

            wishlists.addAll(current_user.getWishlists());
            wishlists.sort(Comparator.comparing(Wishlist::getWishlist_id).reversed());

            if (wishlists.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            List<Wishlist> wantedWishlist = new ArrayList<>();
            if (search != null && start != null && limit != null) {
                List<Wishlist> matchingWishlists = new ArrayList<>();
                if(!search.equals("")) {
                    for(Wishlist wishlist : wishlists) {
                        if(wishlist.getName().toLowerCase().contains(search.toLowerCase())) {
                            matchingWishlists.add(wishlist);
                        }
                    }
                } else {
                    matchingWishlists = wishlists;
                }

                if (matchingWishlists.isEmpty()) {
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }

                int end = Math.min(Integer.parseInt(start) + Integer.parseInt(limit), matchingWishlists.size());
                for (int i = Integer.parseInt(start); i < end; i++) {
                    wantedWishlist.add(matchingWishlists.get(i));
                }

                if (wantedWishlist.isEmpty()) {
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }
            } else {
                wantedWishlist = wishlists;
            }


            List<WishlistDto> wishlistDtos = new ArrayList<>();
            for(Wishlist wishlist : wantedWishlist) {
                List<ItemDto> itemsDtos = new ArrayList<>();
                wishlist.getItems().forEach(item -> itemsDtos.add(new ItemDto(item)));

                wishlistDtos.add(new WishlistDto(wishlist, itemsDtos));
            }

            return new ResponseEntity<>(new GetWishlistsResponseDto(wishlists.size(), wishlistDtos), HttpStatus.OK);


        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/wishlists")
    public PostWishlistBody newWishlist(@RequestBody PostWishlistBody newWishlist) {
        System.out.println(newWishlist);
        Optional<User> user = usersRepository.findById(1L);
        Set<Item> items = new HashSet<>();
        for(long id : newWishlist.getItemIds()) {
            if (itemsRepository.findById(id).isPresent()) {
                items.add(itemsRepository.findById(id).get());
            }
        }

        if(user.isPresent()) {
            wishlistsRepository.save(new Wishlist(newWishlist.getWishlist().getName(), newWishlist.getWishlist().getDetails(), user.get(), Collections.<Group>emptySet(), items));
        }
        return newWishlist;
    }

    @PutMapping("/wishlists/{id}")
    public void editWishlist(@PathVariable("id") int id, @RequestBody PostWishlistBody newWishlist) {
        Optional<Wishlist> wishlist = wishlistsRepository.findById(Long.valueOf(id));
        if(wishlist.isPresent()) {
            Wishlist our_wishlist = wishlist.get();
            our_wishlist.setName(newWishlist.getWishlist().getName());
            our_wishlist.setDetails(newWishlist.getWishlist().getDetails());
            Set<Item> items = new HashSet<>();
            for(long index : newWishlist.getItemIds()) {
                if (itemsRepository.findById(index).isPresent()) {
                    items.add(itemsRepository.findById(index).get());
                }
            }
            our_wishlist.setItems(items);
            wishlistsRepository.save(our_wishlist);
        }
    }

    @PutMapping("/wishlists/{wishlist_id}/items/{item_id}/buy")
    public void purchaseItem(@PathVariable("wishlist_id") int wishlist_id, @PathVariable("item_id") int item_id,@RequestBody PutItemBuyersBody buyersIds) {
        Optional<Item> possible_bought_item = itemsRepository.findById((long) item_id);
        if(possible_bought_item.isPresent()) {
            System.out.println("Item Found");
            Item baught_item = possible_bought_item.get();
            Set<User> buyers = new HashSet<>(baught_item.getBuyers());
            for(long i : buyersIds.getBuyersIds()) {
                Optional<User> possible_buyer = usersRepository.findById(i);
                if(possible_buyer.isPresent()) {
                    System.out.println("User found");
                    User buyer = possible_buyer.get();
                    Set<Item> bought_items = new HashSet<>(buyer.getBaught_items());
                    bought_items.add(baught_item);
                    buyer.setBaught_items(bought_items);
                    usersRepository.save(buyer);
                    buyers.add(buyer);
                }
            }
            baught_item.setBuyers(buyers);
            itemsRepository.save(baught_item);
        }

    }

    @DeleteMapping("/wishlists/{id}")
    public void deleteWishlist(@PathVariable("id") int id) {
        wishlistsRepository.deleteById(Long.valueOf(id));
    }
}
