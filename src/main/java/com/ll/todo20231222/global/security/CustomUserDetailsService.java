package com.ll.todo20231222.global.security;

import com.ll.todo20231222.domain.member.member.entity.Member;
import com.ll.todo20231222.domain.member.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Member> opMember = memberRepository.findByUsername(username);

        if (opMember.isEmpty()) {
            throw new UsernameNotFoundException("사용자를 찾을수 없습니다.");
        }

        Member member = opMember.get();

        return new SecurityUser(
                member.getId(),
                member.getUsername(),
                member.getPassword(),
                member.getAuthorities()
        );
    }
}
