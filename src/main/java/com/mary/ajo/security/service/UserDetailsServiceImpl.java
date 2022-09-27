package com.mary.ajo.security.service;

import com.mary.ajo.services.MemberService;
import com.mary.ajo.services.UserService;
import com.mary.ajo.models.Member;
import com.mary.ajo.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private MemberService memberService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) {
        Optional<User> user = userService.findUserByEmail(email);
        if (user.isPresent()) {
            return UserDetailsImpl.build(user.get());
        } else {
            Member member = memberService.findMemberByEmail(email);
            return UserDetailsImpl.build(member);
        }
    }
}