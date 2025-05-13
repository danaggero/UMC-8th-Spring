package umc.study.service.StoreService;

import com.querydsl.jpa.impl.JPAQueryFactory;
import umc.study.domain.Mission;
import umc.study.mapping.MissionStatus;
import umc.study.mapping.QMemberMission;
import umc.study.domain.QMission;

import java.util.List;

public class Query {

    private final JPAQueryFactory queryFactory;
    private final QMemberMission mm = QMemberMission.memberMission;
    private final QMission m = QMission.mission;

    public Query(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    public List<Mission> findByMemberIdAndMissionStatus(Long memberId, MissionStatus missionStatus) {
        return queryFactory
                .select(m)
                .from(mm)
                .join(mm.mission, m)
                .where(
                        mm.member.id.eq(memberId),
                        mm.status.in(MissionStatus.CHALLENGING, MissionStatus.COMPLETE)
                )
                .offset(2 * 10)
                .limit(10)
                .fetch();

    }
}
