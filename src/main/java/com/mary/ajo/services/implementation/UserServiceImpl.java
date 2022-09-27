package com.mary.ajo.services.implementation;

import com.mary.ajo.exceptions.BadRequestException;
import com.mary.ajo.repositories.MemberRepository;
import com.mary.ajo.repositories.UserRepository;
import com.mary.ajo.utils.DateUtils;
import com.mary.ajo.models.Member;
import com.mary.ajo.models.User;
import com.mary.ajo.payload.request.RegisterMemberRequest;
import com.mary.ajo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    MemberRepository memberRepository;

    @Override
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmailAddress(email);
    }

    @Override
    public Member registration(RegisterMemberRequest registerMember) {
        if (memberRepository.existsByEmailAddress(registerMember.getEmailAddress())) {
            throw new BadRequestException("Error: Email is already taken!");
        }
        if (!(registerMember.getPassword().equals(registerMember.getConfirmPassword()))) {
            throw new BadRequestException("Error: Password does not match");
        }
        if (!isValidPassword(registerMember.getPassword())) {
            throw new BadRequestException("Error: Password must be between 8 and 20, must be an Alphabet or Number");
        }

        return new Member(
                registerMember.getLastName(),
                registerMember.getFirstName(),
                passwordEncoder.encode(registerMember.getPassword()),
                registerMember.getEmailAddress(),
                registerMember.getPhoneNumber(),
                DateUtils.getCurrentTime()
        );
    }

    private boolean isValidPassword(String password) {
        String regex = "^(([0-9]|[a-z]|[A-Z]|[@])*){8,20}$";

        // Compile the ReGex
        Pattern p = Pattern.compile(regex);
        if (password == null) {
            throw new BadRequestException("Error: Password cannot be null");
        }
        Matcher m = p.matcher(password);
        return m.matches();
    }
}
