package letsnona.nonabackend.domain.post.dto;

import letsnona.nonabackend.domain.file.entity.PostImg;
import letsnona.nonabackend.domain.post.entity.Post;
import letsnona.nonabackend.global.security.entity.Member;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PostDTO {

    private long id;
    private Member owner;
    private List<PostImg> images = new ArrayList<>(); // 임시 이미지 아이디, join 필요
    private String title;
    private String content;
    private String category;
    private String tradePlace;
    private int price;
    private String hashTag;
    private boolean flagCourierFee;

    public Post toEntity(PostDTO postDTO) {
        return Post.builder()
                .owner(postDTO.owner)
                .title(postDTO.title)
                .content(postDTO.content)
                .category(postDTO.category)
                .tradePlace(postDTO.tradePlace)
                .price(postDTO.price)
                .hashTag(postDTO.hashTag)
                .build();
    }

}
