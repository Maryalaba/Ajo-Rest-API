package com.mary.ajo.controllers;

import com.mary.ajo.dto.RequestDTO;
import com.mary.ajo.models.Member;
import com.mary.ajo.models.Request;
import com.mary.ajo.payload.request.MemberEditRequest;
import com.mary.ajo.payload.response.MemberResponse;
import com.mary.ajo.security.jwt.JwtUtils;
import com.mary.ajo.services.MemberService;
import com.mary.ajo.services.RequestService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
public class MemberController {

    private final MemberService memberService;
    private final RequestService requestService;
    private final JwtUtils jwtUtils;

    public MemberController(MemberService memberService, RequestService requestService, JwtUtils jwtUtils) {
        this.memberService = memberService;
        this.requestService = requestService;
        this.jwtUtils = jwtUtils;
    }

    @Secured({"ADMIN","MEMBER"})
    @PutMapping("/member/edit-member-details")
    @ApiOperation(value = "Member can edit their details")
    public ResponseEntity<MemberResponse> editMemberDetails(@Valid @RequestBody MemberEditRequest memberRequest,
                                                            HttpServletRequest httpServletRequest) {
        String jwt = jwtUtils.parseJwt(httpServletRequest);
        String email = jwtUtils.extractUserName(jwt);
        Member member = memberService.findMemberByEmail(email);

        Member editedMember = memberService.editMember(member, memberRequest);
        MemberResponse memberResponse = MemberResponse.build(editedMember);
        memberResponse.setMessage("Member details edited successfully");
        return new ResponseEntity<>(memberResponse, HttpStatus.OK);
    }

    @Secured({"ADMIN","MEMBER"})
    @PostMapping("/member/request/{cycleId}")
    @ApiOperation(value = "Member can request to join a contribution cycle")
    public ResponseEntity<Request> cycleRequest (@Valid @RequestBody RequestDTO requestDTO,
                                                     @PathVariable Long cycleId,
                                                     HttpServletRequest httpServletRequest) {
        String jwt = jwtUtils.parseJwt(httpServletRequest);
        String email = jwtUtils.extractUserName(jwt);
        Request newRequest = requestService.makeRequest(requestDTO, cycleId, email);
        return new ResponseEntity<>(newRequest, HttpStatus.OK);
    }

    @Secured({"ADMIN","MEMBER"})
    @GetMapping("/member/request/")
    @ApiOperation(value = "Member can view all requests he/she has made")
    public ResponseEntity<List<Request>> viewRequestsByMember(HttpServletRequest httpServletRequest) {
        String jwt = jwtUtils.parseJwt(httpServletRequest);
        String email = jwtUtils.extractUserName(jwt);
        List<Request> memberRequests = requestService.getAllMemberRequests(email);
        return new ResponseEntity<>(memberRequests, HttpStatus.OK);
    }


    // TODO - Endpoint for member to view details of contribution cycle.


    // TODO - Endpoint for member to view list of members in contribution cycle.


    // TODO - Endpoint for member to make monthly contribution.


}
