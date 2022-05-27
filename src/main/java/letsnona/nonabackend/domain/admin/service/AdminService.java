package letsnona.nonabackend.domain.admin.service;

import letsnona.nonabackend.domain.admin.dto.BarChartDTO;
import letsnona.nonabackend.domain.admin.dto.PieChartDTO;
import letsnona.nonabackend.domain.product.dto.read.ProductReadResDTO;
import letsnona.nonabackend.global.security.dto.chart.AgeRatioDTO;
import letsnona.nonabackend.global.security.dto.chart.GenderRatioDTO;
import letsnona.nonabackend.global.security.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AdminService {
    List<PieChartDTO> getPieChartData();
    List<Member> findByLockedMember();
    @Transactional
    Member changeMemberState(String username);
    List<PieChartDTO> parseRespPieChartDTO(List<GenderRatioDTO> dtoList);
    List<BarChartDTO> getBarChartData();
    List<BarChartDTO> parseRespBarChartDTO(List<AgeRatioDTO> dtoList);




}
