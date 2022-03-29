package letsnona.nonabackend.domain.review.service;

import letsnona.nonabackend.domain.review.dto.ReviewRequestDTO;
import letsnona.nonabackend.global.security.entity.Member;
import org.springframework.transaction.annotation.Transactional;

public interface ReviewService {
    @Transactional
    void saveReview(Member user, ReviewRequestDTO requestDTO);

}
