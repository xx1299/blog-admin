package com.xx.bootblog.domain.params;

import lombok.Data;

import java.util.List;

@Data
public class EditRoleParams {

    private Integer id;
    private String name;
    private String desc;
    private List<Integer> authorityIds;

}
