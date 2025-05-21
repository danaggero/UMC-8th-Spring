package umc.study.service.MemberMissionService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.study.converter.MemberMissionConverter;
import umc.study.domain.Member;
import umc.study.domain.Mission;
import umc.study.mapping.MemberMission;
import umc.study.repository.MemberMissionRepository.MemberMissionRepository;
import umc.study.repository.MemberRepository;
import umc.study.repository.MissionRepository.MissionRepository;
import umc.study.web.dto.MemberMissionRequestDTO;
import umc.study.web.dto.MemberMissionResponseDTO;

@Service
@RequiredArgsConstructor
public class MemberMissionCommandServiceImpl implements MemberMissionCommandService {

    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;

    @Transactional
    @Override
    public MemberMissionResponseDTO.ChallengeResultDTO challengeMission(MemberMissionRequestDTO.CreateChallenge request) {

        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
        Mission mission = missionRepository.findById(request.getMissionId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 미션입니다."));

        boolean alreadyChallenged = memberMissionRepository.existsByMemberAndMission(member, mission);
        if (alreadyChallenged) {
            throw new IllegalStateException("이미 해당 미션에 도전 중입니다.");
        }

        MemberMission newChallenge = MemberMissionConverter.toMemberMission(member, mission);
        MemberMission saved = memberMissionRepository.save(newChallenge);

        return MemberMissionConverter.toChallengeResultDTO(saved);
    }
}