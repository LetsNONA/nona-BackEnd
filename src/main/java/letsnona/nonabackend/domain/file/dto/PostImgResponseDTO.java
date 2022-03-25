package letsnona.nonabackend.domain.file.dto;

import letsnona.nonabackend.domain.file.entity.PostImg;
import letsnona.nonabackend.domain.post.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostImgResponseDTO {
    private long id;
    private Post post;
    private String originalImgSrc;
    private String thumbImgSrc;
    private String originalName;

    public PostImg toEntity() {
        PostImg postImg = PostImg.builder()
                .id(id)
                .post(post)
                .originalImgSrc(originalImgSrc)
                .thumbImgSrc(thumbImgSrc)
                .originalName(originalName)
                .build();

        return postImg;
    }
}