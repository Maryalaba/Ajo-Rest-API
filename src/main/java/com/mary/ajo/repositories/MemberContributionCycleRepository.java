package com.mary.ajo.repositories;

import com.mary.ajo.models.MemberContributionCycle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberContributionCycleRepository extends JpaRepository<MemberContributionCycle, Long> {
}
