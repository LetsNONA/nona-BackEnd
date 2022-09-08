package letsnona.nonabackend.global.security.repository;

import letsnona.nonabackend.global.security.dto.MemberRecommendProductDTO;
import letsnona.nonabackend.global.security.dto.chart.AgeRatioDTO;
import letsnona.nonabackend.global.security.dto.chart.GenderRatioDTO;

import java.util.List;

public interface CustomMemberRepository {
    List<GenderRatioDTO> getGenderRatio();
    List<AgeRatioDTO> getAgeRatio();

     List<MemberRecommendProductDTO> getRecommendProduct(int memberSeq);

}
