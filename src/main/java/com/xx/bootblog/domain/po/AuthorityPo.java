package com.xx.bootblog.domain.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Alias("AuthorityPo")
public class AuthorityPo {

    private Integer id;

    private String name;

    private Boolean status;

    private String identifier;

    private Integer parentId;
}
