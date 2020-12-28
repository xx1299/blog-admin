package com.xx.bootblog.domain.params;

import lombok.Data;

@Data
public class EditAuthorityParams {

    private Integer id;
    private String name;
    private String identifier;
    private Integer parentId;

}
