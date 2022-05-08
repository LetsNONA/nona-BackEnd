package letsnona.nonabackend.domain.product.dto.read;

import letsnona.nonabackend.domain.cataegory.dto.PostReadResCategoryDTO;
import letsnona.nonabackend.domain.product.entity.Product;
import letsnona.nonabackend.domain.product.enums.ProductState;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;


@AllArgsConstructor
@Data
public class ProductReadResDTO {
    private long id;
//    private PostReadResUserDTO owner;
   private String owner;
    private List<ProductReadResImgDTO> images;
    private double averageReviewGrade;
    private List<ProductReadResReviewDTO> reviews;
    private String title;
    private String content;
    private PostReadResCategoryDTO category;
    private String tradePlace;
    private int price;
    private String hashTag;
    private int hit;
    private boolean flagCourierFee;
    private ProductState productState;

    double getAverageReviewsGrade(List<ProductReadResReviewDTO> reviewDTOList){
        double sum = 0 ;
        for (ProductReadResReviewDTO dto : reviewDTOList
             ) {
            sum += dto.getGrade();
        }
        return sum / reviewDTOList.size();
    }

    public ProductReadResDTO(Product product, List<ProductReadResImgDTO> imgDTOList, List<ProductReadResReviewDTO> resReviewDTOS) {
        this.id = product.getId();
//        this.owner = new PostReadResUserDTO(post.getOwner());
        this.owner = product.getOwner().getUsername();
        this.images = imgDTOList;
        this.reviews = resReviewDTOS;
        this.title = product.getTitle();
        this.content = product.getContent();
        this.category = new PostReadResCategoryDTO(product.getCategory());
        this.tradePlace = product.getTradePlace();
        this.price = product.getPrice();
        this.hashTag = product.getHashTag();
        this.hit = product.getHit();
        this.flagCourierFee = product.isFlagCourierFee();
        this.averageReviewGrade = getAverageReviewsGrade(resReviewDTOS);
        this.productState = product.getProductState();
    }
}
