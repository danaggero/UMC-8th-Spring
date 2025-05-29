package umc.study.converter;

import umc.study.domain.Mission;
import umc.study.domain.Store;
import umc.study.mapping.MemberMission;
import umc.study.web.dto.MissionRequestDTO;
import umc.study.web.dto.MissionResponseDTO;
import org.springframework.data.domain.Page;

import java.util.List;


public class MissionConverter {

    public static Mission toMission(MissionRequestDTO.CreateMission request, Store store) {
        return Mission.builder()
                .reward(request.getReward())
                .deadline(request.getDeadline())
                .missionSpec(request.getMissionSpec())
                .store(store)
                .build();
    }

    public static MissionResponseDTO.CreateMissionResultDTO toCreateResultDTO(Mission mission) {
        return MissionResponseDTO.CreateMissionResultDTO.builder()
                .missionId(mission.getId())
                .createdAt(mission.getCreatedAt())
                .build();
    }

    public static MissionResponseDTO.MissionPreviewListDTO toMyMissionPreviewListDTO(Page<Mission> missionPage) {
        List<MissionResponseDTO.MissionPreviewDTO> missionList = missionPage.getContent().stream()
                .map(mission -> MissionResponseDTO.MissionPreviewDTO.builder()
                        .missionId(mission.getId())
                        .missionSpec(mission.getMissionSpec())
                        .build())
                .toList();

        return MissionResponseDTO.MissionPreviewListDTO.builder()
                .missionList(missionList)
                .totalPage(missionPage.getTotalPages())
                .totalElements(missionPage.getTotalElements())
                .currentPage(missionPage.getNumber() + 1)
                .size(missionPage.getSize())
                .isFirst(missionPage.isFirst())
                .isLast(missionPage.isLast())
                .build();
    }

    public static MissionResponseDTO.MissionPreviewListDTO toMissionPreviewListDTO(Page<MemberMission> memberMissionPage) {
        List<MissionResponseDTO.MissionPreviewDTO> missionList = memberMissionPage.getContent().stream()
                .map(mm -> {
                    Mission mission = mm.getMission();
                    return MissionResponseDTO.MissionPreviewDTO.builder()
                            .missionId(mission.getId())
                            .missionSpec(mission.getMissionSpec())
                            .build();
                }).toList();

        return MissionResponseDTO.MissionPreviewListDTO.builder()
                .missionList(missionList)
                .totalPage(memberMissionPage.getTotalPages())
                .totalElements(memberMissionPage.getTotalElements())
                .currentPage(memberMissionPage.getNumber() + 1)
                .size(memberMissionPage.getSize())
                .isFirst(memberMissionPage.isFirst())
                .isLast(memberMissionPage.isLast())
                .build();
    }
}