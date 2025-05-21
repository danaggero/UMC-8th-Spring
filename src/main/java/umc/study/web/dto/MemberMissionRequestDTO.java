package umc.study.web.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import umc.study.validation.annotation.ExistMember;
import umc.study.validation.annotation.ExistMission;
import umc.study.validation.annotation.NotDuplicatedMissionChallenge;

public class MemberMissionRequestDTO {

    @Getter
    public static class CreateChallenge {

        @NotNull
        @ExistMember
        private Long memberId;

        @NotNull
        @ExistMission
        @NotDuplicatedMissionChallenge
        private Long missionId;
    }
}