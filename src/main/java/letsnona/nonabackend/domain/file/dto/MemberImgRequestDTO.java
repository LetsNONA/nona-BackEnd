package letsnona.nonabackend.domain.file.dto;

import letsnona.nonabackend.domain.file.entity.MemberImg;
import letsnona.nonabackend.domain.file.entity.PostImg;
import letsnona.nonabackend.domain.product.entity.Product;
import letsnona.nonabackend.global.security.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberImgRequestDTO {
    private long id;
    private Member member;
    private String originalImgSrc;
    private String thumbImgSrc;
    private String originalName;

    public MemberImg toEntity() {
        MemberImg memberImg = MemberImg.builder()
                .id(id)
               // .member(member)
                .originalImgSrc(originalImgSrc)
                .thumbImgSrc(thumbImgSrc)
                .originalName(originalName)
                .build();

        //postImg.setPost(post);
        return memberImg;
    }
}