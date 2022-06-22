package letsnona.nonabackend.domain.cart.repository;


import letsnona.nonabackend.domain.cart.entity.Cart;
import letsnona.nonabackend.domain.cataegory.entity.Category;
import letsnona.nonabackend.global.security.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
   public Page<Cart> findByOwner(Member owner, Pageable pageable);


}
