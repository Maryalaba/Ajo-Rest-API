package com.mary.ajo.repositories;

import com.mary.ajo.models.ContributionCycle;
import com.mary.ajo.models.Member;
import com.mary.ajo.models.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
    List<Request> findAllByContributionCycle(ContributionCycle contributionCycle);
    List<Request> findAllByMember(Member member);
    boolean existsByMember(Member member);
}
