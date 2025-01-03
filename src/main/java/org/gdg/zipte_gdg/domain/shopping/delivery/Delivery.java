package org.gdg.zipte_gdg.domain.shopping.delivery;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.gdg.zipte_gdg.domain.user.member.Address;
import org.gdg.zipte_gdg.domain.shopping.order.Order;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter

public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne
    private Order order;

    @Embedded
    private Address address;

    private String orderDesc;

    private String deliveryDesc;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;


    public static Delivery of(Address address, String orderDesc, String deliveryDesc) {
        return Delivery.builder()
                .address(address)
                .orderDesc(orderDesc)
                .deliveryDesc(deliveryDesc)
                .status(DeliveryStatus.READY)
                .build();
    }

    // 로직
    public void update(Address update_address) {
        this.address = update_address;
    }

    public void deliveryIng() {
        this.status = DeliveryStatus.DELIVERING;
    }

    public void deliveryComplete() {
        this.status = DeliveryStatus.DELIVERY;
    }

    public void deliveryCancel() {
        this.status = DeliveryStatus.CANCEL;
    }

    public void changeDeliveryDesc(String deliveryDesc) {
        this.deliveryDesc = deliveryDesc;
    }

    public void changeOrderDesc(String orderDesc) {
        this.orderDesc = orderDesc;
    }

    public void changeAddress(Address address) {
        this.address = address;
    }

    // 비즈니스 로직 위한
    public void setOrder(Order order) {
        this.order = order;
    }


}
