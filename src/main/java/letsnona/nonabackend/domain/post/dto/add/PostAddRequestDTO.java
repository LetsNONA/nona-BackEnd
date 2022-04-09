package letsnona.nonabackend.domain.post.dto.add;

import letsnona.nonabackend.domain.file.dto.PostImgRequestDTO;
import letsnona.nonabackend.domain.post.entity.Post;
import letsnona.nonabackend.global.security.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostAddRequestDTO {

    private long id;
    private Member owner;
    private String title;
    private String content;
    private String category;
    private String tradePlace;
    private int price;
    private String hashTag;
    private boolean flagCourierFee;
    //private List<PostImgRequestDTO> images = new ArrayList<>(); // 임시 이미지 아이디, join 필요


    public Post toEntity(){
        return Post.builder()
                .id(id)
                .owner(owner)
                .title(title)
                .content(content)
                .category(category)
                .price(price)
                .hashTag(hashTag)
                .flagCourierFee(flagCourierFee)
                .build();
    }
}
