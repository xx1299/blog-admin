package com.xx.bootblog.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    private Integer id;

    private String name;

    private String desc;

    private List<Integer> authorityIds;

}
