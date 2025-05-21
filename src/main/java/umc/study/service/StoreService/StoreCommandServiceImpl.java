package umc.study.service.StoreService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.study.domain.Region;
import umc.study.domain.Store;
import umc.study.repository.RegionRepository.RegionRepository;
import umc.study.repository.StoreRepository.StoreRepository;
import umc.study.converter.StoreConverter;
import umc.study.web.dto.StoreRequestDTO;
import umc.study.web.dto.StoreResponseDTO;

@Service
@RequiredArgsConstructor
public class StoreCommandServiceImpl implements StoreCommandService {

    private final StoreRepository storeRepository;
    private final RegionRepository regionRepository;

    @Transactional
    @Override
    public StoreResponseDTO.CreateStoreResultDTO createStore(StoreRequestDTO.CreateStore request) {

        Region region = regionRepository.findById(request.getRegionId())
                .orElseThrow(() -> new IllegalArgumentException("지역 정보가 존재하지 않습니다."));

        Store store = StoreConverter.toStore(request, region);
        Store saved = storeRepository.save(store);

        return StoreConverter.toCreateResultDTO(saved);
    }
}