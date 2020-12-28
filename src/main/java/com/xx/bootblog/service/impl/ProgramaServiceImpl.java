package com.xx.bootblog.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xx.bootblog.domain.PageListResult;
import com.xx.bootblog.domain.Programa;
import com.xx.bootblog.domain.QueryVo;
import com.xx.bootblog.mapper.ArticleMapper;
import com.xx.bootblog.mapper.ProgramaMapper;
import com.xx.bootblog.service.ProgramaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class ProgramaServiceImpl implements ProgramaService {

    @Autowired
    private ProgramaMapper programaMapper;


    @Override
    public List<Programa> programaList() {
        List<Programa> programas = programaMapper.selectAll();
        return programas;
    }

    @Override
    public Programa getProgramaByPid(Integer pid) {
        Programa programa = programaMapper.selectByPrimaryKey(pid);
        return programa;
    }

    @Override
    public void savePrograma(Programa programa) {
        programaMapper.insert(programa);
    }

    @Override
    public void deletePrograma(Integer pid) {
        programaMapper.deleteByPrimaryKey(pid);
    }

    @Override
    public void updatePrograma(Programa programa) {
        programaMapper.updateByPrimaryKey(programa);
    }

    @Override
    public PageListResult programaPageList(QueryVo queryVo) {
        PageListResult pageListResult = new PageListResult();
        Page<Programa> page = PageHelper.startPage(queryVo.getPage(), queryVo.getRows());
        List<Programa> programas = programaMapper.selectAll();
        pageListResult.setRows(programas);
        pageListResult.setTotal(page.getTotal());
        return pageListResult;
    }

}
