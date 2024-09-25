package com.example.userservice.service;

import com.example.userservice.client.OrderServiceClient;
import com.example.userservice.dto.UserDto;
import com.example.userservice.jpa.UserEntity;
import com.example.userservice.jpa.UserRepository;
import com.example.userservice.vo.ResponseOrder;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    UserRepository userRepository;
    BCryptPasswordEncoder bCryptPasswordEncoder;

    Environment env;
    RestTemplate restTemplate;
    OrderServiceClient orderServiceClient;


    @Autowired
    public UserServiceImpl(
            final UserRepository userRepository
            , final BCryptPasswordEncoder bCryptPasswordEncoder
            , final Environment env
            , final RestTemplate restTemplate
            , final OrderServiceClient orderServiceClient
    ) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.env = env;
        this.restTemplate = restTemplate;
        this.orderServiceClient = orderServiceClient;
    }

    @Override
    public UserDto getUserByUserId(final String userId) throws Exception {
        UserEntity userEntity = userRepository.findByUserId(userId);

        if (userEntity == null) {
            // custom exception 정의해서 사용해도 괜찮음
            throw new UsernameNotFoundException("User not found");
        }

        UserDto userDto = new ModelMapper().map(userEntity, UserDto.class);

//        List<ResponseOrder> orders = new ArrayList<ResponseOrder>();

        /**
         * ParameterizedTypeReference는 Spring Framework 에서 제네릭 타입 정보를 런타임에 유지하기 위해 사용되는 클래스
         * 제네릭 타입 정보 보존: Java의 제네릭 타입은 런타임에 소거되기 때문에, 제네릭 타입 정보를 동적으로 추출하기 어렵습니다. ParameterizedTypeReference를 사용하면 이 정보를 런타임에도 유지할 수 있습니다.
         * HTTP 응답 처리: RestTemplate이나 WebClient에서 API 호출 시, 응답을 제네릭 타입으로 받을 때 유용합니다. 예를 들어, List<MyObject>와 같은 복잡한 제네릭 타입을 처리할 수 있습니다.
         * 익명 클래스 사용: ParameterizedTypeReference는 익명 클래스로 생성하여 사용합니다. 예를
         * */
        // using as rest template
        // 변경에 따른 변경이 필요함
//        String orderUrl = String.format(Objects.requireNonNull(env.getProperty("order_service.url")), userId);
//        ResponseEntity<List<ResponseOrder>> orderListResponse = restTemplate.exchange(
//                orderUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<ResponseOrder>>() {
//                }
//        );
//        List<ResponseOrder> orderList = orderListResponse.getBody();

        // open-feign 사용
        // 예외처리 open-feign exception handling
//        List<ResponseOrder> orderList = null;
//        try{
//            orderList = orderServiceClient.getOrders(userId);
//        } catch (FeignException e) {
//            log.error(e.getMessage());
//        }


        //open-feign error decode로 변경
        List<ResponseOrder> orderList = orderServiceClient.getOrders(userId);
        userDto.setOrders(orderList);

        return userDto;
    }

    @Override
    public Iterable<UserEntity> getUserByAll() throws Exception {
        return userRepository.findAll();
    }

    @Override
    public UserDto createUser(final UserDto userDto) throws Exception {
        userDto.setUserId(UUID.randomUUID().toString());

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
        userEntity.setEncryptedPwd(bCryptPasswordEncoder.encode(userDto.getPwd()));
        userRepository.save(userEntity);

        UserDto returnUserDto = modelMapper.map(userEntity, UserDto.class);
        return returnUserDto;
    }

    @Override
    public UserDto getUserDetailsByEmail(final String userName) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(userName);

        // 사용자가 없을때 Exception 처리
        if (userEntity == null) {
            throw new UsernameNotFoundException("User not found");
        }

        UserDto userDto = new ModelMapper().map(userEntity, UserDto.class);
        return userDto;
    }

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(username);

        if (userEntity == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return new User(userEntity.getEmail(), userEntity.getEncryptedPwd(),
                true, true, true, true, new ArrayList<>());
    }
}
