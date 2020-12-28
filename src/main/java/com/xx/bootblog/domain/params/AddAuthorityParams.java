package com.xx.bootblog.domain.params;

import lombok.Data;

@Data
public class AddAuthorityParams {

    private String name;
    private String identifier;
    private Integer parentId;

}
