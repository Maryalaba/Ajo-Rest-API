package com.mary.ajo.services;

import com.mary.ajo.models.Member;
import com.mary.ajo.payload.request.MemberEditRequest;

import java.util.List;


public interface MemberService {
    Member saveMember(Member member);
    Member findMemberByEmail(String email);
    Member editMember(Member member, MemberEditRequest memberRequest);
    List<Member> getAllMembers();
    Member findMemberById(Long memberId);
}
