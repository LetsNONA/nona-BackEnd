package letsnona.nonabackend.domain.post.dto.read;

import letsnona.nonabackend.domain.cataegory.dto.PostReadResCategoryDTO;
import letsnona.nonabackend.domain.post.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;


@AllArgsConstructor
@Data
public class PostReadResDTO {
    private long id;
//    private PostReadResUserDTO owner;
   private String owner;
    private List<PostReadResImgDTO> images;
    private double averageReviewGrade;
    private List<PostReadResReviewDTO> reviews;
    private String title;
    private String content;
    private PostReadResCategoryDTO category;
    private String tradePlace;
    private int price;
    private String hashTag;
    private int hit;
    private boolean flagCourierFee;

    double getAverageReviewsGrade(List<PostReadResReviewDTO> reviewDTOList){
        double sum = 0 ;
        for (PostReadResReviewDTO dto : reviewDTOList
             ) {
            sum += dto.getGrade();
        }
        return sum / reviewDTOList.size();
    }

    public PostReadResDTO(Post post, List<PostReadResImgDTO> imgDTOList, List<PostReadResReviewDTO> resReviewDTOS) {
        this.id = post.getId();
//        this.owner = new PostReadResUserDTO(post.getOwner());
        this.owner = post.getOwner().getUsername();
        this.images = imgDTOList;
        this.reviews = resReviewDTOS;
        this.title = post.getTitle();
        this.content = post.getContent();
        this.category = new PostReadResCategoryDTO(post.getCategory());
        this.tradePlace = post.getTradePlace();
        this.price = post.getPrice();
        this.hashTag = post.getHashTag();
        this.hit = post.getHit();
        this.flagCourierFee = post.isFlagCourierFee();
        this.averageReviewGrade = getAverageReviewsGrade(resReviewDTOS);
    }
}
