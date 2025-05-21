package umc.study.web.dto;

import lombok.Getter;
import umc.study.validation.annotation.ExistStore;

import java.time.LocalDate;

public class MissionRequestDTO {

    @Getter
    public static class CreateMission {

        private Integer reward;

        private LocalDate deadline;

        private String missionSpec;

        @ExistStore
        private Long storeId;
    }
}
