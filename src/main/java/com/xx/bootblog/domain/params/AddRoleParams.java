package com.xx.bootblog.domain.params;

import lombok.Data;

import java.util.List;

@Data
public class AddRoleParams {

    private String name;
    private String desc;
    private List<Integer> authorityIds;

}
