package kr.co.myservice.myrestfulservice.controller;

import kr.co.myservice.myrestfulservice.bean.User;
import kr.co.myservice.myrestfulservice.dao.UserDaoService;
import kr.co.myservice.myrestfulservice.exception.UserNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserController {

    private final UserDaoService userDaoService;

    public UserController(final UserDaoService userDaoService) {
        this.userDaoService = userDaoService;
    }

    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return userDaoService.findAll();
    }

    @GetMapping("/users/{id}")
    public User retrieveUser(@PathVariable final Integer id) {
        User user = userDaoService.findOne(id);

        // user가 없을시 exception 처리
        if (user == null) {
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }

        return user;
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody final User user) {
        User savedUser = userDaoService.save(user);

        // return할 URI 정보를 추가함
        // users -> {id}로 path를 반환
        // userDaoService.save 등록한 id를 return
        // uri를 만들어줌
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity deleteUser(@PathVariable final Integer id) {
        User deletedUser = userDaoService.deleteById(id);

        if (deletedUser == null) {
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }
    
        //httpstatus 204 no content 반환
        return ResponseEntity.noContent().build();
    }

}
