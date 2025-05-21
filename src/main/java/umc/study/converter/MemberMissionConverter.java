package umc.study.converter;

import umc.study.domain.Member;
import umc.study.domain.Mission;
import umc.study.mapping.MemberMission;
import umc.study.mapping.MissionStatus;
import umc.study.web.dto.MemberMissionResponseDTO;

public class MemberMissionConverter {

    public static MemberMission toMemberMission(Member member, Mission mission) {
        return MemberMission.builder()
                .member(member)
                .mission(mission)
                .status(MissionStatus.CHALLENGING)
                .build();
    }

    public static MemberMissionResponseDTO.ChallengeResultDTO toChallengeResultDTO(MemberMission memberMission) {
        return MemberMissionResponseDTO.ChallengeResultDTO.builder()
                .memberMissionId(memberMission.getId())
                .challengedAt(memberMission.getCreatedAt())
                .build();
    }
}