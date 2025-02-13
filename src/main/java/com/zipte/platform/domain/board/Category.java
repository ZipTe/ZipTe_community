package com.zipte.platform.domain.board;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    private Long id;
    private String name;
    private String code;
    private Category parent;
    private List<Category> children;

    // 생성자

}
