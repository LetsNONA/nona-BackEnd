package letsnona.nonabackend.domain.file.dto;

import letsnona.nonabackend.domain.file.entity.PostImg;
import letsnona.nonabackend.domain.post.entity.Post;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostImgDTO {
    private long id;
    private Post post;
    private String originalImgSrc;
    private String thumbImgSrc;


    public PostImg toEntity(PostImgDTO postImgDTO) {

        return PostImg.builder()
                .post(postImgDTO.post)
                .originalImgSrc(postImgDTO.originalImgSrc)
                .thumbImgSrc(postImgDTO.thumbImgSrc)
                .build();
    }

}
