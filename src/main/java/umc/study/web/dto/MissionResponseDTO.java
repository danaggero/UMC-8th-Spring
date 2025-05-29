package umc.study.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

public class MissionResponseDTO {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateMissionResultDTO {
        private Long missionId;
        private LocalDateTime createdAt;
    }

    @Getter @Builder
    public static class MissionPreviewDTO {
        private Long missionId;
        private String missionSpec;
    }

    @Getter @Builder
    public static class MissionPreviewListDTO {
        private List<MissionPreviewDTO> missionList;
        private Integer totalPage;
        private Long totalElements;
        private Integer currentPage;
        private Integer size;
        private Boolean isFirst;
        private Boolean isLast;
    }
}