package io.github.lokiwooooo.userdetail;

import io.github.lokiwooooo.domain.user.dto.UserDto;
import io.github.lokiwooooo.domain.user.entity.User;
import io.github.lokiwooooo.domain.user.repository.UserMapper;
import io.github.lokiwooooo.domain.user.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;


@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Slf4j
@Transactional
public class CustomUserDetailsService implements UserDetailsService {

    UserRepository userRepository;
    UserMapper userMapper;

    @Override
    public CustomUserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {

        UserDto userDto = null;
        List<GrantedAuthority> authorities = null;
        try {
            //Step1 객체 관련 내용 확인
            User user = userRepository.findByUserName(username).orElse(null);
            if (user == null) {
                throw new UsernameNotFoundException("### user is not found  username :: " + username + " ###");
            } else {

                // 사용자 상태 체크
                userDto = userMapper.toDto(user);

                // 하드코딩된 ROLE_USER 권한 부여
                authorities = Collections.singletonList(
                        new SimpleGrantedAuthority("ROLE_USER")
                );
                // 사용자 권한
//                authorities = convertRolesToAuthorities(userDto.getUserRoleDtoList());
            }
        } catch (Exception exception) {
            throw new IllegalArgumentException(exception);
        }

        return new CustomUserDetails(userDto, authorities);
    }

//    // 사용자 권한 체크
//    private List<GrantedAuthority> convertRolesToAuthorities(final List<UserRoleDto> userRoleDtoList) {
//        return userRoleDtoList.stream()
//                .map(userRoleDto -> userRoleDto.getRoleDto())
//                .map(roleDto -> new SimpleGrantedAuthority(roleDto.getRoleType().toString()))
//                .collect(Collectors.toList());
//    }

}
