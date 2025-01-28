package com.eedo.project.zipte.domain.order;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.eedo.project.zipte.domain.user.Member;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<CartItem> items = new ArrayList<>();

    // 생성 로직
    public static Cart of(Member member) {
        return Cart.builder()
                .member(member)
                .items(new ArrayList<>()) // 최초 장바구니는 비워져있어야한다.
                .build();
    }

    // 비즈니스 로직 구성
    // 아이템 추가
    public void addItem(CartItem item) {
        items.add(item);
    }

    // 아이템 제거
    public void removeItem(CartItem item) {
        // 아이템 추가
        items.remove(item);
    }
}
