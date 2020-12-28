package com.xx.bootblog.web.controller;

import com.xx.bootblog.domain.AjxsRst;
import com.xx.bootblog.domain.PageListResult;
import com.xx.bootblog.domain.Programa;
import com.xx.bootblog.domain.QueryVo;
import com.xx.bootblog.service.ProgramaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ProgramaController {

    @Autowired
    ProgramaService programaService;

    @RequestMapping("programa")
    public String programa(){
        return "programa";
    }

    @RequestMapping("programaList")
    @ResponseBody
    public List<Programa> programaList(){
        return programaService.programaList();
    }

    @RequestMapping("programaPageList")
    @ResponseBody
    public PageListResult programaPageList(QueryVo queryVo){
        return programaService.programaPageList(queryVo);
    }

    @RequestMapping("deletePrograma")
    @ResponseBody
    public AjxsRst deletePrograma(Integer pid){
        AjxsRst ajxsRst = new AjxsRst();
        try {
            programaService.deletePrograma(pid);
            ajxsRst.setMsg("删除成功");
            ajxsRst.setSuccess(true);
        }catch (Exception e){
            ajxsRst.setMsg("删除失败");
            ajxsRst.setSuccess(false);
        }finally {
            return ajxsRst;
        }
    }

    @RequestMapping("updatePrograma")
    @ResponseBody
    public AjxsRst updatePrograma(Programa programa){
        AjxsRst ajxsRst = new AjxsRst();
        try {
            programaService.updatePrograma(programa);
            ajxsRst.setMsg("更新成功");
            ajxsRst.setSuccess(true);
        }catch (Exception e){
            ajxsRst.setMsg("更新失败");
            ajxsRst.setSuccess(false);
        }finally {
            return ajxsRst;
        }
    }



    @RequestMapping("savePrograma")
    @ResponseBody
    public AjxsRst savePrograma(Programa programa){
        AjxsRst ajxsRst = new AjxsRst();
        try {
            programaService.savePrograma(programa);
            ajxsRst.setMsg("保存成功");
            ajxsRst.setSuccess(true);
        }catch (Exception e){
            ajxsRst.setMsg("保存失败");
            ajxsRst.setSuccess(false);
        }finally {
            return ajxsRst;
        }
    }


}
