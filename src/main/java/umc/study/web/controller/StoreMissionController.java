package umc.study.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import umc.study.apiPayload.ApiResponse;
import umc.study.converter.MissionConverter;
import umc.study.domain.Mission;
import umc.study.service.MissionService.MissionQueryService;
import umc.study.validation.annotation.CheckPage;
import umc.study.web.dto.MissionResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreMissionController {

    private final MissionQueryService missionQueryService;

    @GetMapping("/{storeId}/missions")
    @Operation(summary = "특정 가게의 미션 목록 조회 API", description = "가게 ID를 기준으로 미션 목록을 페이징하여 조회합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "STORE404", description = "해당 가게를 찾을 수 없습니다.")
    })
    @Parameters({
            @Parameter(name = "storeId", description = "가게 ID", example = "1"),
            @Parameter(name = "page", description = "조회할 페이지 번호 (1부터 시작)", example = "1")
    })
    public ApiResponse<MissionResponseDTO.MissionPreviewListDTO> getStoreMissions(
            @PathVariable("storeId") Long storeId,
            @CheckPage @RequestParam("page") Integer page) {

        Page<Mission> missions = missionQueryService.getStoreMissions(storeId, page);
        return ApiResponse.onSuccess(MissionConverter.toMyMissionPreviewListDTO(missions));
    }
}
