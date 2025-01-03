package org.gdg.zipte_gdg.domain.apt.apt;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.gdg.zipte_gdg.domain.apt.area.Area;
import org.gdg.zipte_gdg.domain.user.member.Address;
import org.gdg.zipte_gdg.domain.apt.own.SubwayOwn;

import java.util.List;
import java.util.ArrayList;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
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

//    @Builder.Default
//    @OneToMany(mappedBy = "apt")
//    private List<Review> reviews = new ArrayList<>();

//    // 평점
//    @OneToMany(mappedBy = "apt")
//    private List<Rating> ratings = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private AptCategory houseCategory;

    // 버스 시간
    private int WithTimeBus;

    // 지하철 시간
    private int withTimeSub;

    // 세대수
    private int houseHolds;

    @OneToMany
    private List<SubwayOwn> subwayOwns = new ArrayList<>();

    // 편의시설
//    private List<String> convenientFacility = new ArrayList<>();
//
//    // 교육시설
//    private List<String> educationFacility = new ArrayList<>();

//    @OneToMany(mappedBy = "apt")
//    private List<Own> ownList = new ArrayList<>();

}
