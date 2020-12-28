package com.xx.bootblog.service;

import com.xx.bootblog.domain.PageListResult;
import com.xx.bootblog.domain.Programa;
import com.xx.bootblog.domain.QueryVo;

import java.util.List;

public interface ProgramaService {

    public List<Programa> programaList();

    Programa getProgramaByPid(Integer pid);

    void savePrograma(Programa programa);

    void deletePrograma(Integer pid);

    void updatePrograma(Programa programa);

    PageListResult programaPageList(QueryVo queryVo);

}
