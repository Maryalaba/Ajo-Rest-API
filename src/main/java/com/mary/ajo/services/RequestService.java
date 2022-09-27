package com.mary.ajo.services;

import com.mary.ajo.dto.RequestDTO;
import com.mary.ajo.models.Request;
import java.util.List;

public interface RequestService {
    Request makeRequest(RequestDTO request, Long cycleId, String email);
    List<Request> getAllCycleRequests(Long cycleId);
    List<Request> getAllMemberRequests(String email);
    void approveRequest(Long requestId);
    void declineRequest(Long requestId);
}
