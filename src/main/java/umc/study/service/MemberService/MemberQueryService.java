package umc.study.service.MemberService;

import org.springframework.data.domain.Page;
import umc.study.domain.Review;

public interface MemberQueryService {
    boolean existsById(Long id);
    Page<Review> getMyReviews(Long memberId, Integer page);

}
