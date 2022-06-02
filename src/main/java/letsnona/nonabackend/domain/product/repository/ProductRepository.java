package letsnona.nonabackend.domain.product.repository;

import letsnona.nonabackend.domain.cataegory.entity.Category;
import letsnona.nonabackend.domain.product.entity.Product;
import letsnona.nonabackend.domain.product.enums.ProductState;
import letsnona.nonabackend.global.security.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByOwner(Member owner);

    Optional<Product> findById(Long id);

    //@Query("select p from Post p left join fetch p.owner")
    @EntityGraph("PostWithMemberAndCategory")
    Page<Product> findAll(Pageable pageable);

    @EntityGraph("PostWithMemberAndCategory")
//@EntityGraph(attributePaths = {"owner","images","reviews"})
    Page<Product> findAllByProductState(Pageable pageable, ProductState productState);

    @EntityGraph("PostWithMemberAndCategory")
    Page<Product> findByTitleContaining(Pageable pageable, String title);

    @EntityGraph("PostWithMemberAndCategory")
    Page<Product> findByOwner(Pageable pageable, Member owner);

    @EntityGraph("PostWithMemberAndCategory")
    Page<Product> findByCategoryAndProductState(Pageable pageable, Category category, ProductState productState);


    int countProductByProductState(ProductState productState);

    int countProductByCreatedDateLike(LocalDate localDate);

    int countAllBy();
}
