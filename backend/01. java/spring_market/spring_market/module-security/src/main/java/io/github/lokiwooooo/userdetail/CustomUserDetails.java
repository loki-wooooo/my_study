package io.github.lokiwooooo.userdetail;

import io.github.lokiwooooo.domain.user.dto.UserDto;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(makeFinal = false, level = AccessLevel.PRIVATE)
public class CustomUserDetails implements UserDetails {

    String userId;
    String password;
    String username;
    String email;
    List<GrantedAuthority> authorities;

    public CustomUserDetails(final UserDto userDto, List<GrantedAuthority> authorities) {
        this.userId = userDto.getId();
        this.email = userDto.getEmail();
        this.username = userDto.getName();
        this.password = userDto.getPassword();
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // 계정 만료 여부 (항상 만료로 처리됨 true: 만료X / false: 만료O)
    }

    @Override
    public boolean isAccountNonLocked() {
        return false; // 계정 잠금 여부 (항상 잠금으로 처리됨)
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // 비밀번호 만료 여부 (항상 만료로 처리됨 true: 만료X / false: 만료O)
    }

    @Override
    public boolean isEnabled() {
        return true; // 계정 활성화 여부 (항상 비활성화로 처리됨)
    }

}
