package umc.study.converter;

import org.springframework.data.domain.Page;
import umc.study.domain.Review;
import umc.study.web.dto.MemberRequestDTO;
import umc.study.web.dto.MemberResponseDTO;
import umc.study.domain.Gender;
import umc.study.domain.Member;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.nio.file.Files.size;

public class MemberConverter {

    public static MemberResponseDTO.JoinResultDTO toJoinResultDTO(Member member){
        return MemberResponseDTO.JoinResultDTO.builder()
                .memberId(member.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }
    public static Member toMember(MemberRequestDTO.JoinDto request){

        Gender gender = null;

        switch (request.getGender()){
            case 1:
                gender = Gender.MALE;
                break;
            case 2:
                gender = Gender.FEMALE;
                break;
            case 3:
                gender = Gender.NONE;
                break;
        }

        return Member.builder()
                .address(request.getAddress())
                .specAddress(request.getSpecAddress())
                .gender(gender)
                .name(request.getName())
                .memberPreferList(new ArrayList<>())

                .build();
    }
    public static MemberResponseDTO.MyReviewPreviewListDTO toMyReviewListDTO(Page<Review> reviewPage) {

        List<MemberResponseDTO.MyReviewPreviewDTO> reviewList = reviewPage.getContent().stream()
                .map(review -> MemberResponseDTO.MyReviewPreviewDTO.builder()
                        .reviewId(review.getId())
                        .body(review.getBody())
                        .score(review.getScore())
                        .storeName(review.getStore().getName())
                        .createdAt(review.getCreatedAt())
                        .build())
                .collect(Collectors.toList());

        return MemberResponseDTO.MyReviewPreviewListDTO.builder()
                .reviewList(reviewList)
                .totalPage(reviewPage.getTotalPages())
                .totalElements(reviewPage.getTotalElements())
                .currentPage(reviewPage.getNumber() + 1)
                .size(reviewPage.getSize())
                .isFirst(reviewPage.isFirst())
                .isLast(reviewPage.isLast())
                .build();
    }
}