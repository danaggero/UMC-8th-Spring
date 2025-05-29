package umc.study.converter;

import org.springframework.data.domain.Page;
import umc.study.domain.Region;
import umc.study.domain.Review;
import umc.study.domain.Store;
import umc.study.web.dto.StoreRequestDTO;
import umc.study.web.dto.StoreResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

public class StoreConverter {

    public static Store toStore(StoreRequestDTO.CreateStore request, Region region) {
        return Store.builder()
                .name(request.getName())
                .address(request.getAddress())
                .region(region)
                .score(0.0f)
                .build();
    }

    public static StoreResponseDTO.CreateStoreResultDTO toCreateResultDTO(Store store) {
        return StoreResponseDTO.CreateStoreResultDTO.builder()
                .storeId(store.getId())
                .createdAt(store.getCreatedAt())
                .build();
    }

    public static StoreResponseDTO.ReviewPreviewDTO reviewPreViewDTO(Review review){
        return StoreResponseDTO.ReviewPreviewDTO.builder()
                .ownerNickname(review.getMember().getName())
                .score(review.getScore())
                .createdAt(review.getCreatedAt().toLocalDate())
                .body(review.getBody())
                .build();
    }
    public static StoreResponseDTO.ReviewPreviewListDTO toReviewPreviewListDTO(Page<Review> reviewPage) {
        List<StoreResponseDTO.ReviewPreviewDTO> reviewList = reviewPage.getContent().stream()
                .map(review -> StoreResponseDTO.ReviewPreviewDTO.builder()
                        .reviewId(review.getId())
                        .ownerNickname(review.getMember().getName())
                        .body(review.getBody())
                        .score(review.getScore())
                        .createdAt(review.getCreatedAt().toLocalDate())
                        .build())
                .toList();

        return StoreResponseDTO.ReviewPreviewListDTO.builder()
                .reviewList(reviewList)
                .totalPage(reviewPage.getTotalPages())
                .totalElements(reviewPage.getTotalElements())
                .currentPage(reviewPage.getNumber() + 1)
                .listSize(reviewPage.getSize())
                .isFirst(reviewPage.isFirst())
                .isLast(reviewPage.isLast())
                .build();
    }
}