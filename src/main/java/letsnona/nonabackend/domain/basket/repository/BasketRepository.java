package letsnona.nonabackend.domain.basket.repository;


import letsnona.nonabackend.domain.basket.entity.Basket;
import letsnona.nonabackend.domain.cart.entity.Cart;
import letsnona.nonabackend.global.security.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasketRepository extends JpaRepository<Basket, Long> {
   public Page<Basket> findByOwner(Member owner, Pageable pageable);


}
