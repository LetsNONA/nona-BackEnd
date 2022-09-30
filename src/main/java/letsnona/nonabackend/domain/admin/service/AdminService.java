package letsnona.nonabackend.domain.admin.service;

import letsnona.nonabackend.domain.admin.dto.BarChartDTO;
import letsnona.nonabackend.domain.admin.dto.BrokenChartDTO;
import letsnona.nonabackend.domain.admin.dto.PieChartDTO;
import letsnona.nonabackend.global.security.dto.chart.AgeRatioDTO;
import letsnona.nonabackend.global.security.dto.chart.GenderRatioDTO;
import letsnona.nonabackend.global.security.entity.Member;
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
    @Transactional
    List<Member> findAllMember();
    @Transactional
    List<Member> findAllByUsernameAndMemberState(String username);
    @Transactional
Member findByUsername(String username);

    @Transactional
    List<BrokenChartDTO> getBrokenChartData();
    void IncreasePoint(String fee,String targetName);



}
