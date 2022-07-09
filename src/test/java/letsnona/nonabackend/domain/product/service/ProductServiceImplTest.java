package letsnona.nonabackend.domain.product.service;

import letsnona.nonabackend.domain.cataegory.repository.CategoryRepository;
import letsnona.nonabackend.domain.cataegory.service.CategoryService;
import letsnona.nonabackend.domain.file.repository.PostImgRepository;
import letsnona.nonabackend.domain.file.service.FileService;
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

    }

    @Test
    @DisplayName("제품_업데이트_성공")
        // 어떻것ㅇ ㅣ 반환된다
    void 제품_업데이트_성공() {
        //given 
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