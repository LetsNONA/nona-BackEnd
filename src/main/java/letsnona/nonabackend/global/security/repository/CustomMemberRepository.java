package letsnona.nonabackend.global.security.repository;

import letsnona.nonabackend.global.security.dto.GenderDTO;

import java.util.List;

public interface CustomMemberRepository {
    List<GenderDTO> countMemberGender();
}
