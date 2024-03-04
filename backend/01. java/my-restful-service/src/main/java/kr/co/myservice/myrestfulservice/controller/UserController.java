package kr.co.myservice.myrestfulservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kr.co.myservice.myrestfulservice.bean.User;
import kr.co.myservice.myrestfulservice.dao.UserDaoService;
import kr.co.myservice.myrestfulservice.exception.UserNotFoundException;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
//class의 설명
@Tag(name = "user-controller", description = "일반 사용자 서비스를 위한 컨트롤러입니다.")
public class UserController {

    private final UserDaoService userDaoService;

    public UserController(final UserDaoService userDaoService) {
        this.userDaoService = userDaoService;
    }

    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return userDaoService.findAll();
    }

    //메서드의 정의
    @Operation(summary = "사용자 정보 조회 API", description = "사용자 ID를 이용해서 사용자 상세 정보 조회를 합니다.")
    //api에서 사용하는 고유코드
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST!!"),
            @ApiResponse(responseCode = "404", description = "USER NOT FOUND!!"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR!!"),
    })
    @GetMapping("/users/{id}")
    public EntityModel<User> retrieveUser(
            @Parameter(description = "사용자 ID", required = true, example = "1") @PathVariable final Integer id
    ) {
        User user = userDaoService.findOne(id);

        // user가 없을시 exception 처리
        if (user == null) {
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }

        // hateoas return model
        EntityModel entityModel = EntityModel.of(user);

        // link 작업
        WebMvcLinkBuilder linto = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        entityModel.add(linto.withRel("all-users")); // all-users -> http://localhost:8088/users

        return entityModel;
    }

    //@Valid => 사용자 데이터를 유효성 체크를 함
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody final User user) {
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
