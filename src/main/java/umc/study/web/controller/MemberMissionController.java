package umc.study.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import umc.study.apiPayload.ApiResponse;
import umc.study.converter.MissionConverter;
import umc.study.mapping.MemberMission;
import umc.study.service.MemberMissionService.MemberMissionCommandService;
import umc.study.service.MemberMissionService.MemberMissionQueryService;
import umc.study.service.MemberService.MemberQueryService;
import umc.study.validation.annotation.CheckPage;
import umc.study.web.dto.MemberMissionRequestDTO;
import umc.study.web.dto.MemberMissionResponseDTO;
import umc.study.web.dto.MissionResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member-missions")
public class MemberMissionController {

    private final MemberMissionCommandService commandService;
    private final MemberMissionQueryService memberMissionQueryService;


    @PostMapping
    public ResponseEntity<MemberMissionResponseDTO.ChallengeResultDTO> challenge(
            @RequestBody @Valid MemberMissionRequestDTO.CreateChallenge request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(commandService.challengeMission(request));
    }

    @GetMapping("/members/{memberId}/missions")
    @Operation(summary = "내가 진행 중인 미션 목록 조회 API", description = "진행 중인 미션 목록을 페이징하여 조회합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "access 토큰 만료", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "access 토큰 모양이 이상함", content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
    @Parameters({
            @Parameter(name = "memberId", description = "회원 ID (Path Variable)", example = "1"),
            @Parameter(name = "page", description = "조회할 페이지 번호 (1부터 시작)", example = "1")
    })
    public ApiResponse<MissionResponseDTO.MissionPreviewListDTO> getChallengingMissions(
            @PathVariable("memberId") Long memberId,
            @CheckPage @RequestParam("page") Integer page) {

        Page<MemberMission> missions = memberMissionQueryService.getChallengingMissions(memberId, page);
        return ApiResponse.onSuccess(MissionConverter.toMissionPreviewListDTO(missions));
    }


}
