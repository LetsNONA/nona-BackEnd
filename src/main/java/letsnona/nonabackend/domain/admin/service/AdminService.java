package letsnona.nonabackend.domain.admin.service;

import letsnona.nonabackend.domain.admin.dto.BarChartDTO;
import letsnona.nonabackend.global.security.dto.GenderDTO;

import java.util.List;

public interface AdminService {
    List<BarChartDTO> getBarChatData();

    List<BarChartDTO> parseBarChart(List<GenderDTO> dtoList);
}
