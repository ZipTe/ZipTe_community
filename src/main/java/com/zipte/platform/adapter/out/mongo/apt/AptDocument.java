package com.zipte.platform.adapter.out.mongo.apt;


import com.zipte.platform.domain.apt.AptCategory;
import com.zipte.platform.domain.apt.Apt;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "apartments")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class AptDocument {

    @Id
    private String id;

    private String aptName;

    private AptAddressDocument address;

    private AptSnippetDocument snippet;

    private AptCategory category;

    // from
    public static AptDocument from(Apt apt) {
        return AptDocument.builder()
                .id(apt.getId())
                .aptName(apt.getAptName())
                .address(AptAddressDocument.from(apt.getAptAddress()))
                .snippet(AptSnippetDocument.from(apt.getSnippet()))
                .category(apt.getCategory())
                .build();
    }

    // toDomain
    public Apt toDomain() {
        return Apt.builder()
                .id(this.getId())
                .aptName(this.getAptName())
                .aptAddress(this.getAddress().toDomain())
                .snippet(this.getSnippet().toDomain())
                .category(this.getCategory())
                .build();
    }
}

