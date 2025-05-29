package umc.study.service.MemberMissionService;

import org.springframework.data.domain.Page;
import umc.study.mapping.MemberMission;

public interface MemberMissionQueryService {
    Page<MemberMission> getChallengingMissions(Long memberId, Integer page);
}