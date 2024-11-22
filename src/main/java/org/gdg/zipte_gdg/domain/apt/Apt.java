package org.gdg.zipte_gdg.domain.apt;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.gdg.zipte_gdg.domain.area.Area;
import org.gdg.zipte_gdg.domain.member.Address;
import org.gdg.zipte_gdg.domain.own.SubwayOwn;
import org.gdg.zipte_gdg.domain.review.Review;

import java.util.List;
import java.util.ArrayList;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Apt {

    @Id
    @Column(name = "apt_id")
    private String id;

    private String aptName;

    @Embedded
    private Address address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "area_id")
    private Area area;

    @Builder.Default
    @OneToMany(mappedBy = "apt")
    private List<Review> reviews = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private AptCategory houseCategory;

    private List<String> convenientFacility = new ArrayList<>();
    private List<String> educationFacility = new ArrayList<>();

    private int WithTimeBus;

    private int withTimeSub;

    private int houseHolds;

    @OneToMany
    private List<SubwayOwn> subwayOwns = new ArrayList<>();

//    @OneToMany(mappedBy = "apt")
//    private List<Own> ownList = new ArrayList<>();

}
