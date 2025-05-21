package umc.study.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.study.service.MemberMissionService.MemberMissionCommandService;
import umc.study.web.dto.MemberMissionRequestDTO;
import umc.study.web.dto.MemberMissionResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member-missions")
public class MemberMissionController {

    private final MemberMissionCommandService commandService;

    @PostMapping
    public ResponseEntity<MemberMissionResponseDTO.ChallengeResultDTO> challenge(
            @RequestBody @Valid MemberMissionRequestDTO.CreateChallenge request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(commandService.challengeMission(request));
    }
}
