package umc.study.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import umc.study.validation.annotation.ExistRegion;

public class StoreRequestDTO {

    @Getter
    public static class CreateStore {

        @NotBlank(message = "가게 이름은 필수입니다.")
        private String name;

        @NotBlank(message = "주소는 필수입니다.")
        private String address;

        @NotNull(message = "지역 ID는 필수입니다.")
        @ExistRegion
        private Long regionId;
    }
}
