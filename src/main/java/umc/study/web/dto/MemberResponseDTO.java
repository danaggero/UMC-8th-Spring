package umc.study.web.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

public class MemberResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class JoinResultDTO{
        Long memberId;
        LocalDateTime createdAt;
    }
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MyReviewPreviewDTO {
        private Long reviewId;
        private String body;
        private Float score;
        private String storeName;
        private LocalDateTime createdAt;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MyReviewPreviewListDTO {
        private List<MyReviewPreviewDTO> reviewList;
        private Integer totalPage;
        private Long totalElements;
        private Integer currentPage;
        private Integer size;
        private Boolean isFirst;
        private Boolean isLast;
    }


}