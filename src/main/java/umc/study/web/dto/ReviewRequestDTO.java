package umc.study.web.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import umc.study.validation.annotation.ExistStore;

public class ReviewRequestDTO {

    @Getter
    public static class CreateReview {
        String body;
        @NotNull
        Float score;

        @ExistStore
        Long storeId;

        @NotNull
        Long memberId;
    }
}