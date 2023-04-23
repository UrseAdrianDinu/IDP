package com.ioservice.web.rest;

import com.ioservice.IoserviceApp;
import com.ioservice.domain.Group;
import com.ioservice.domain.Notification;
import com.ioservice.domain.User;
import com.ioservice.domain.Wishlist;
import com.ioservice.repository.GroupsRepository;
import com.ioservice.repository.NotificationsRepository;
import com.ioservice.repository.UsersRepository;
import com.ioservice.repository.WishlistsRepository;
import com.ioservice.web.rest.dto.modelDtos.GroupDto;
import com.ioservice.web.rest.dto.modelDtos.ItemDto;
import com.ioservice.web.rest.dto.modelDtos.UserDto;
import com.ioservice.web.rest.dto.modelDtos.WishlistDto;
import com.ioservice.web.rest.dto.postDtos.groups.PostGroupBody;
import com.ioservice.web.rest.dto.postDtos.groups.PutGroupMembersBody;
import com.ioservice.web.rest.dto.postDtos.groups.PutGroupWishlistsBody;
import com.ioservice.web.rest.dto.responseDtos.GetGroupsResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class GroupsController {

    private static final Logger log = LoggerFactory.getLogger(GroupsController.class);
    @Autowired
    GroupsRepository groupsRepository;
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    WishlistsRepository wishlistsRepository;
    @Autowired
    NotificationsRepository notificationsRepository;

    @GetMapping("/groups")
    public ResponseEntity<GetGroupsResponseDto> getAllGroups(@RequestParam(name="search", required=false) String search, @RequestParam(name="start", required=false) String start, @RequestParam(name="limit", required=false) String limit) {

        try {
            log.error(
                "You have misconfigured your application! It should not run " + "with both the 'dev' and 'prod' profiles at the same time."
            );
            Optional<User> user = usersRepository.findById(1L);
            log.error("DADADADA");
            log.error("User {}", user.isPresent());
            List<Group> groups = null;
            log.error("NAME {}", user.get().getName());

            groups = new ArrayList<Group>(user.get().getCreated_groups());
            log.error("ASDADSASDAS");
            groups.sort(Comparator.comparing(Group::getGroup_id).reversed());
            log.error("ASDADSASDAS");
            if (groups.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            log.error(
                "find by id"
            );
            List<Group> wantedGroups = new ArrayList<>();
            if (search != null && start != null && limit != null) {
                List<Group> matchingGroups = new ArrayList<>();
                if(search != "") {
                    for(Group group : groups) {
                        if(group.getName().toLowerCase().contains(search.toLowerCase())) {
                            matchingGroups.add(group);
                        }
                    }
                } else {
                    matchingGroups = groups;
                }

                if (matchingGroups.isEmpty()) {
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }

                int end = Math.min(Integer.parseInt(start) + Integer.parseInt(limit), matchingGroups.size());
                for(int i = Integer.parseInt(start); i < end; i++) {
                    wantedGroups.add(matchingGroups.get(i));
                }
            } else {
                wantedGroups = groups;
            }
            log.error(
                "AICI"
            );
            List<GroupDto> groupDtos = new ArrayList<>();
            for(Group group : wantedGroups) {
                List<UserDto> userDtos = new ArrayList<>();
                List<WishlistDto> wishlistDtos = new ArrayList<>();

                log.error("grouuupss {}",  group.getMembers());
                group.getMembers().forEach(member -> userDtos.add(new UserDto(member)));

                for(Wishlist wishlist : group.getWishlists()) {
                    List<ItemDto> itemsDtos = new ArrayList<>();
                    wishlist.getItems().forEach(item -> itemsDtos.add(new ItemDto(item)));

                    wishlistDtos.add(new WishlistDto(wishlist, itemsDtos));
                }

                groupDtos.add(new GroupDto(group, wishlistDtos, userDtos));
            }
            return new ResponseEntity<>(new GetGroupsResponseDto(groups.size(), groupDtos), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/groups/shared")
    public ResponseEntity<GetGroupsResponseDto> getAllSharedGroups(@RequestParam(name="search", required=false) String search, @RequestParam(name="start", required=false) String start, @RequestParam(name="limit", required=false) String limit) {

        try {
            Optional<User> user = usersRepository.findById(1L);

            List<Group> groups = new ArrayList<Group>(user.get().getShared_groups());
            groups.sort(Comparator.comparing(Group::getGroup_id).reversed());

            if (groups.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            List<Group> wantedGroups = new ArrayList<>();
            if (search != null && start != null && limit != null) {
                List<Group> matchingGroups = new ArrayList<>();
                if(search != "") {
                    for(Group group : groups) {
                        if(group.getName().contains(search)) {
                            matchingGroups.add(group);
                        }
                    }
                } else {
                    matchingGroups = groups;
                }

                if (matchingGroups.isEmpty()) {
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }

                int end = Math.min(Integer.parseInt(start) + Integer.parseInt(limit), matchingGroups.size());
                for(int i = Integer.parseInt(start); i < end; i++) {
                    wantedGroups.add(matchingGroups.get(i));
                }
            } else {
                wantedGroups = groups;
            }

            List<GroupDto> groupDtos = new ArrayList<>();
            for(Group group : wantedGroups) {
                List<UserDto> userDtos = new ArrayList<>();
                List<WishlistDto> wishlistDtos = new ArrayList<>();

                group.getMembers().forEach(member -> userDtos.add(new UserDto(member)));

                for(Wishlist wishlist : group.getWishlists()) {
                    List<ItemDto> itemsDtos = new ArrayList<>();
                    wishlist.getItems().forEach(item -> itemsDtos.add(new ItemDto(item)));

                    wishlistDtos.add(new WishlistDto(wishlist, itemsDtos));
                }

                groupDtos.add(new GroupDto(group, wishlistDtos, userDtos));
            }
            return new ResponseEntity<>(new GetGroupsResponseDto(groups.size(), groupDtos), HttpStatus.OK);


        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/groups")
    public PostGroupBody newItem(@RequestBody PostGroupBody newGroup) {
        System.out.println(newGroup);
        Optional<User> user = usersRepository.findById(1L);

        if(user.isPresent()) {
            Set<User> members = new HashSet<>();
            User current_user = user.get();
            members.add(user.get());
            Group newCreatedGroup = new Group(newGroup.getName(), newGroup.getDetails(), members, user.get(), Collections.<Wishlist>emptySet());
            groupsRepository.save(newCreatedGroup);

            Set<Group> created_groups = new HashSet<>(current_user.getCreated_groups());
            created_groups.add(newCreatedGroup);

            Set<Group> shared_groups = new HashSet<>(current_user.getShared_groups());
            shared_groups.add(newCreatedGroup);

            current_user.setCreated_groups(created_groups);
            current_user.setShared_groups(shared_groups);
            usersRepository.save(current_user);
        }
        return newGroup;
    }

    @PutMapping("/groups/{groupId}/users")
    public void editGroupMembers(@PathVariable("groupId") int id, @RequestBody PutGroupMembersBody userIds) {
        System.out.println(userIds);
        Optional<Group> group = groupsRepository.findById(Long.valueOf(id));
        if(group.isPresent()) {
            Group wanted_group = group.get();
            Set<User> new_members = new HashSet<>(wanted_group.getMembers());
            for(long i : userIds.getUserIds()) {
                Optional<User> possibleNewMember = usersRepository.findById(i);
                if(possibleNewMember.isPresent()) {
                    User newMember = possibleNewMember.get();

                    Set<Group> created_groups = new HashSet<>(newMember.getCreated_groups());
                    created_groups.add(wanted_group);

                    Set<Group> shared_groups = new HashSet<>(newMember.getShared_groups());
                    shared_groups.add(wanted_group);

                    newMember.setCreated_groups(created_groups);
                    newMember.setShared_groups(shared_groups);
                    usersRepository.save(newMember);

                    notificationsRepository.save(new Notification("Added to group", "You have been added to group " + wanted_group.getName() + " by " + newMember.getName() + ".", newMember));

                    new_members.add(newMember);
                }
            }
            wanted_group.setMembers(new_members);
            groupsRepository.save(wanted_group);
        }
    }

    @PutMapping("/groups/{groupId}/wishlists")
    public void editGroupWishlists(@PathVariable("groupId") int id, @RequestBody PutGroupWishlistsBody wishlistIds) {
        System.out.println(id);
        System.out.println(wishlistIds);
        Optional<Group> group = groupsRepository.findById(Long.valueOf(id));
        if(group.isPresent()) {
            Group wanted_group = group.get();
            Set<Wishlist> new_wishlists = new HashSet<>(wanted_group.getWishlists());
            for(long i : wishlistIds.getWishlistIds()) {
                Optional<Wishlist> possibleNewWishlist = wishlistsRepository.findById(i);
                if(possibleNewWishlist.isPresent()) {
                    Wishlist newWishlist = possibleNewWishlist.get();

                    Set<Group> wishlist_groups = new HashSet<>(newWishlist.getGroups());
                    wishlist_groups.add(wanted_group);

                    newWishlist.setGroups(wishlist_groups);
                    wishlistsRepository.save(newWishlist);

                    new_wishlists.add(newWishlist);
                }
            }
            wanted_group.setWishlists(new_wishlists);
            groupsRepository.save(wanted_group);
        }
    }

    @PutMapping("/groups/{id}")
    public void editGroupDetails(@PathVariable("id") int id, @RequestBody PostGroupBody edittedGroup) {
        Optional<Group> group = groupsRepository.findById(Long.valueOf(id));
        if(group.isPresent()) {
            Group wanted_group = group.get();
            wanted_group.setName(edittedGroup.getName());
            wanted_group.setDetails(edittedGroup.getDetails());
            groupsRepository.save(wanted_group);
        }
    }

    @DeleteMapping("/groups/{id}")
    public void deleteGroup(@PathVariable("id") int id) {
        Optional<Group> poss_group = groupsRepository.findById((long) id);
        if(poss_group.isPresent()) {
            Group group = poss_group.get();

            for(User user : group.getMembers()) {
                Set<Group> shared_group = new HashSet<>();
                for(Group ex_group : user.getShared_groups()) {
                    if(group.getGroup_id() != ex_group.getGroup_id()) {
                        shared_group.add(ex_group);
                    }
                }
                user.setShared_groups(shared_group);
                usersRepository.save(user);
            }

            Set<User> members = new HashSet<>();
            group.setMembers(members);
            groupsRepository.save(group);
            groupsRepository.deleteById((long) id);
        }
    }
}
