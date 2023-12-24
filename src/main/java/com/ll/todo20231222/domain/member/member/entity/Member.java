package com.ll.todo20231222.domain.member.member.entity;

import com.ll.todo20231222.global.jpa.BaseTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Transient;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@AllArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@Getter
@Setter
public class Member extends BaseTime {
    private String username;
    private String password;
    @Column(unique = true)
    private String refreshToken;

    @Transient
    public List<String> getAuthoritiesAsStringList() {
        return List.of("ROLE_MEMBER");
    }

    @Transient
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getAuthoritiesAsStringList()
                .stream()
                .map(SimpleGrantedAuthority::new)
                .toList();
    }
}
