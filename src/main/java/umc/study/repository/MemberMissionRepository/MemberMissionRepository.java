package umc.study.repository.MemberMissionRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.study.domain.Member;
import umc.study.domain.Mission;
import umc.study.mapping.MemberMission;
import umc.study.mapping.MissionStatus;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {
    boolean existsByMemberAndMission(Member member, Mission mission);
    Page<MemberMission> findByMemberIdAndStatusChallenging(Long memberId, Pageable pageable);
    Page<MemberMission> findAllByMemberAndStatus(Member member, MissionStatus status, Pageable pageable);


}
