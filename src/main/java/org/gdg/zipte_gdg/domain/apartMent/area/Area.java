package org.gdg.zipte_gdg.domain.apartMent.area;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.gdg.zipte_gdg.domain.apartMent.apt.Apt;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Area {

    @Id
    private Long id;
    private String areaName;

    @OneToMany(mappedBy = "area")
    @Builder.Default
    private List<Apt> apts = new ArrayList<>();

}
