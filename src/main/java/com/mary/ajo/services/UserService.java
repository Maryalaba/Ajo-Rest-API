package com.mary.ajo.services;

import com.mary.ajo.models.Member;
import com.mary.ajo.models.User;
import com.mary.ajo.payload.request.RegisterMemberRequest;

import java.util.Optional;

public interface UserService {
    Optional<User> findUserByEmail(String email);
    Member registration(RegisterMemberRequest registerMember);
}
