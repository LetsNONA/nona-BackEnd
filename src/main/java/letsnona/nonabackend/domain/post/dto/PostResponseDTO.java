package letsnona.nonabackend.domain.post.dto;

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
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostResponseDTO {
    private long id;
    private Member owner;
    private String title;
    private String content;
    private String category;
    private String tradePlace;
    private int price;
    private String hashTag;
    private boolean flagCourierFee;
    private List<PostImgRequestDTO> images = new ArrayList<>(); // 임시 이미지 아이디, join 필요

    public PostResponseDTO(Post post) {
        this.id = post.getId();
        this.owner = post.getOwner();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.category = post.getCategory();
        this.tradePlace = post.getTradePlace();
        this.price = post.getPrice();
        this.hashTag = post.getHashTag();
        this.flagCourierFee = post.isFlagCourierFee();
    }
}
