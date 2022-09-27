package com.mary.ajo.services;

import com.mary.ajo.dto.ContributionCycleDTO;
import com.mary.ajo.models.ContributionCycle;

public interface ContributionCycleService {

    ContributionCycle createCycle(ContributionCycleDTO contributionCycleRequest);

    ContributionCycle editCycle(ContributionCycleDTO contributionCycleRequest, Long cycleId);
}
