package umc.study.service.MemberMissionService;

import umc.study.web.dto.MemberMissionRequestDTO;
import umc.study.web.dto.MemberMissionResponseDTO;

public interface MemberMissionCommandService {
    MemberMissionResponseDTO.ChallengeResultDTO challengeMission(MemberMissionRequestDTO.CreateChallenge request);
    void completeMission(Long memberId, Long missionId);

}