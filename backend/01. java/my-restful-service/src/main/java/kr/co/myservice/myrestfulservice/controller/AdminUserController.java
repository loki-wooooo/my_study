package kr.co.myservice.myrestfulservice.controller;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import kr.co.myservice.myrestfulservice.bean.AdminUser;
import kr.co.myservice.myrestfulservice.bean.AdminUserV2;
import kr.co.myservice.myrestfulservice.bean.User;
import kr.co.myservice.myrestfulservice.dao.UserDaoService;
import kr.co.myservice.myrestfulservice.exception.UserNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/admin")
public class AdminUserController {

    private final UserDaoService userDaoService;

    public AdminUserController(final UserDaoService userDaoService) {
        this.userDaoService = userDaoService;
    }

    // /admin/users/{id}
    // /admin/users

    @GetMapping("/users")
    public MappingJacksonValue retrieveAllUsers4Admin() {
        List<User> users = userDaoService.findAll();

        List<AdminUser> adminUsers = new ArrayList<>();
        AdminUser adminUser = null;
        for(User user : users) {
            adminUser = new AdminUser();

            // 객체를 복사함
            BeanUtils.copyProperties(user, adminUser);
            adminUsers.add(adminUser);
        }

        /**
         * JSONFilter를 이용한 객체 전달
         * @link - https://kwonnam.pe.kr/wiki/java/jackson/jsonfilter
         * */
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "name", "joinDate", "ssn");
        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo", filter);

        // list목록을 갖고오기위해 변경
        MappingJacksonValue mapping = new MappingJacksonValue(adminUsers);
        mapping.setFilters(filters);

        return mapping;
    }


    // --> admin/v1/users/{id}
    //@link beanUtils -> https://velog.io/@kdhyo/BeanUtils.copyProperties%EB%A5%BC-%EC%82%AC%EC%9A%A9%ED%95%B4%EB%B3%B4%EC%9E%90
//    @GetMapping("/v1/users/{id}")
//    @GetMapping(value="/users/{id}", params= "version=1")
//    @GetMapping(value="/users/{id}", headers= "X-API-VERSION=1")
    @GetMapping(value="/users/{id}", produces= "application/vnd.company.appv1+json")
    public MappingJacksonValue retrieveUser4Admin(@PathVariable final Integer id) {
        User user = userDaoService.findOne(id);

        AdminUser adminUser = new AdminUser();

        // user가 없을시 exception 처리
        if (user == null) {
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        } else {
            // 원본객체, 복사객체
            // 객체를 복사함
            BeanUtils.copyProperties(user, adminUser);
        }

        /**
         * JSONFilter를 이용한 객체 전달
         * @link - https://kwonnam.pe.kr/wiki/java/jackson/jsonfilter
         * */
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "name", "joinDate", "ssn");
        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo", filter);

        MappingJacksonValue mapping = new MappingJacksonValue(adminUser);
        mapping.setFilters(filters);

        return mapping;
    }

    // --> admin/v2/users/{id}
    //@link beanUtils -> https://velog.io/@kdhyo/BeanUtils.copyProperties%EB%A5%BC-%EC%82%AC%EC%9A%A9%ED%95%B4%EB%B3%B4%EC%9E%90
//    @GetMapping("/v2/users/{id}")
//    @GetMapping(value="/users/{id}", params= "version=2")
//    @GetMapping(value="/users/{id}", headers= "X-API-VERSION=2")
    @GetMapping(value="/users/{id}", produces= "application/vnd.company.appv2+json")
    public MappingJacksonValue retrieveUser4AdminV2(@PathVariable final Integer id) {
        User user = userDaoService.findOne(id);

        AdminUserV2 adminUser = new AdminUserV2();

        // user가 없을시 exception 처리
        if (user == null) {
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        } else {
            // 원본객체, 복사객체
            // 객체를 복사함
            BeanUtils.copyProperties(user, adminUser);
            adminUser.setGrade("VIP"); // grade 신규 필드 추가
        }

        /**
         * JSONFilter를 이용한 객체 전달
         * @link - https://kwonnam.pe.kr/wiki/java/jackson/jsonfilter
         * */
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "name", "joinDate", "grade");
        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfoV2", filter);

        MappingJacksonValue mapping = new MappingJacksonValue(adminUser);
        mapping.setFilters(filters);

        return mapping;
    }
}
