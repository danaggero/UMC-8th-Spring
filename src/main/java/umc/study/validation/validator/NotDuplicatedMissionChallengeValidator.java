package umc.study.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.study.repository.MemberMissionRepository.MemberMissionRepository;
import umc.study.validation.annotation.NotDuplicatedMissionChallenge;

@Component
@RequiredArgsConstructor
public class NotDuplicatedMissionChallengeValidator implements ConstraintValidator<NotDuplicatedMissionChallenge, Long> {

    private final MemberMissionRepository memberMissionRepository;

    @Override
    public boolean isValid(Long missionId, ConstraintValidatorContext context) {

        return true;
    }
}
