package letsnona.nonabackend.domain.post.dto.read;

import letsnona.nonabackend.domain.post.entity.Post;
import letsnona.nonabackend.global.security.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;


@AllArgsConstructor
@Data
public class PostReadResDTO {
    private long id;
    private Member owner;
    private List<PostResImgDTO> images;
    private double averageReviewGrade = 0 ;
    private List<PostResReviewDTO> reviews;
    private String title;
    private String content;
    private String category;
    private String tradePlace;
    private int price;
    private String hashTag;
    private int hit;
    private boolean flagCourierFee;

    double getAverageReviewsGrade(List<PostResReviewDTO> reivewDTOList){
        double sum = 0 ;
        for (PostResReviewDTO dto : reivewDTOList
             ) {
            sum += dto.getGrade();
        }
        return sum / reivewDTOList.size();
    }

    public PostReadResDTO(Post post, List<PostResImgDTO> imgDTOList, List<PostResReviewDTO> reivewDTOList) {
        this.id = post.getId();
        this.owner = post.getOwner();
        this.images = imgDTOList;
        this.reviews = reivewDTOList;
        this.title = post.getTitle();
        this.content = post.getContent();
        this.category = post.getCategory();
        this.tradePlace = post.getTradePlace();
        this.price = post.getPrice();
        this.hashTag = post.getHashTag();
        this.hit = post.getHit();
        this.flagCourierFee = post.isFlagCourierFee();
        this.averageReviewGrade = getAverageReviewsGrade(reivewDTOList);
    }
}
