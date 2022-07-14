package letsnona.nonabackend.domain.product.service;

import letsnona.nonabackend.domain.cataegory.entity.Category;
import letsnona.nonabackend.domain.cataegory.repository.CategoryRepository;
import letsnona.nonabackend.domain.cataegory.service.CategoryService;
import letsnona.nonabackend.domain.file.entity.PostImg;
import letsnona.nonabackend.domain.file.repository.PostImgRepository;
import letsnona.nonabackend.domain.file.service.FileService;
import letsnona.nonabackend.domain.product.dto.add.ProductAddRequestDTO;
import letsnona.nonabackend.domain.product.dto.add.ProductAddResponseDTO;
import letsnona.nonabackend.domain.product.dto.update.ProductUpdateRequestDTO;
import letsnona.nonabackend.domain.product.entity.Product;
import letsnona.nonabackend.domain.product.repository.ProductRepository;
import letsnona.nonabackend.global.security.entity.Member;
import letsnona.nonabackend.global.security.service.MemberService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class ProductServiceImplTest {

    @InjectMocks
    @Spy // 실제인스턴스에 함수를 커스텀할수 있어요
    ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;
    @Mock
    private PostImgRepository imgRepository;
    @Mock
    private FileService fileService;
    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private CategoryService categoryService;
    @Mock
    private MemberService memberService;


    @BeforeAll
    static void setup() {

    }


    @Test
    void 제품등록() {
        //given
        Member mockRequestUser = Member.builder()
                .id(1L)
                .username("username")
                .build();

        Category mockCategory = new Category(1L, "cg001", "과일");

        ProductAddRequestDTO requestPostDTO = ProductAddRequestDTO.builder()
                .title("제목입니다")
                .content("본문입니다")
                .tradePlace("장소")
                .categoryCode("cg001")
                .price(500)
                .hashTag("해쉬태그")
                .flagCourierFee(false)
                .build();

        Product mockSavedProduct = Product.builder()
                .id(1L)
                .title("제목입니다")
                .content("본문입니다")
                .tradePlace("장소")
                .category(mockCategory)
                .price(500)
                .hashTag("해쉬태그")
                .flagCourierFee(false)
                .build();

        PostImg savedProductImg1 = new PostImg(1L, requestPostDTO.toEntity(), "orinalSrc1", "thumbSrc1", "사진명1");
        PostImg savedProductImg2 = new PostImg(2L, requestPostDTO.toEntity(), "orinalSrc2", "thumbSrc2", "사진명2");

        List<PostImg> mockSavedImgList = new ArrayList<>();
        mockSavedImgList.add(savedProductImg1);
        mockSavedImgList.add(savedProductImg2);


        when(categoryService.existCategory(anyString())).thenReturn(Boolean.TRUE);
        when(categoryService.getCategory(anyString())).thenReturn(mockCategory);
        when(productRepository.save(any())).thenReturn(mockSavedProduct);
        when(fileService.saveImage(any(), any())).thenReturn(mockSavedImgList);
        ProductAddResponseDTO productAddResponseDTO = productService.saveProduct(mockRequestUser, requestPostDTO);

        //then
        assertThat(productAddResponseDTO.getId()).isNotNull();
        assertThat(productAddResponseDTO.getTitle()).isEqualTo(mockSavedProduct.getTitle());
        assertThat(productAddResponseDTO.getImages()).hasSize(2);
        assertThat(productAddResponseDTO).isInstanceOf(ProductAddResponseDTO.class);

    }

    @Test
    @DisplayName("제품_업데이트_성공")
        // 어떻것ㅇ ㅣ 반환된다
    void 제품_업데이트_성공() {
        //given te
        Member member = Member.builder()
                .id(1)
                .username("kangheon")
                .build();

        Optional<Product> originalProduct = Optional.of(Product.builder()
                .id(1)
                .title("original_title")
                .content("original_content")
                .owner(member)
                .build());

        ProductUpdateRequestDTO afterUpdateProduct = ProductUpdateRequestDTO.builder()
                .id(1)
                .title("after_title")
                .content("after_content")
                .build();


        //when
        when(productRepository.findById(anyLong())).thenReturn(originalProduct); // 있는지 제품 포스트
        when(memberService.getRequestUser()).thenReturn(member); // 요청 유저반환
        doReturn(Boolean.TRUE).when(productService).isPostOwner(any(Product.class), any(Member.class));
        //when(productService.isPostOwner(any(Product.class),any(Member.class))).thenReturn(Boolean.TRUE);
        ProductAddResponseDTO productAddResponseDTO = productService.updateProduct(afterUpdateProduct);

        //then


        assertThat(productAddResponseDTO.getTitle()).isEqualTo(originalProduct.get().getTitle());
        assertThat(productAddResponseDTO.getContent()).isEqualTo(originalProduct.get().getContent());

    }

    @Test
    void getProductReadResDTOS() {
    }

    @Test
    void getAllProduct() {
    }

    @Test
    void getSearchProduct() {
    }
}