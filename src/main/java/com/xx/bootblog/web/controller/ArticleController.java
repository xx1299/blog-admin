package com.xx.bootblog.web.controller;

import com.xx.bootblog.domain.*;
import com.xx.bootblog.service.ArticleService;
import com.xx.bootblog.service.BlogrollService;
import com.xx.bootblog.service.InformationService;
import com.xx.bootblog.service.ProgramaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class ArticleController {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private BlogrollService blogrollService;
    @Autowired
    private ProgramaService programaService;
    @Autowired
    private InformationService informationService;

    @RequestMapping(value={"index","/"})
    public String index(Model model){
        List<Article> articles = articleService.simpleArticleDetail();
        List<Blogroll> blogrolls = blogrollService.blogrollList();
        List<Programa> programas = programaService.programaList();
        Information information = informationService.getInformation();
        model.addAttribute("articles",articles);
        model.addAttribute("blogrolls",blogrolls);
        model.addAttribute("programas",programas);
        model.addAttribute("information",information);
        return "index";
    }

    @RequestMapping("release")
    public String release(){
        return "release";
    }

    @RequestMapping("section/{pid}")
    public String articleListByPid(Model model,@PathVariable("pid") Integer pid){
        List<Article> articles = articleService.articleListByPid(pid);
        List<Blogroll> blogrolls = blogrollService.blogrollList();
        List<Programa> programas = programaService.programaList();
        Information information = informationService.getInformation();
        Programa programa = programaService.getProgramaByPid(pid);
        Long pages = articleService.getPages(pid);
        model.addAttribute("articles",articles);
        model.addAttribute("blogrolls",blogrolls);
        model.addAttribute("programas",programas);
        model.addAttribute("currentPrograma",programa);
        model.addAttribute("information",information);
        model.addAttribute("pages",pages);
        return "section";
    }

    @RequestMapping("article/{aid}")
    public String articleDetail(Model model,@PathVariable("aid") Integer aid){
        List<Blogroll> blogrolls = blogrollService.blogrollList();
        List<Programa> programas = programaService.programaList();
        Information information = informationService.getInformation();
        Article article = articleService.getArticle(aid);
        Article preArticle = articleService.getPreArticle(aid,article.getPrograma().getPid());
        Article sufArticle = articleService.getSufArticle(aid,article.getPrograma().getPid());
        model.addAttribute("blogrolls",blogrolls);
        model.addAttribute("programas",programas);
        model.addAttribute("information",information);
        model.addAttribute("article",article);
        model.addAttribute("preArticle",preArticle);
        model.addAttribute("sufArticle",sufArticle);
        return "article";
    }

    @RequestMapping("articlePageListByPid")
    @ResponseBody
    public List<Article> articlePageListByPid(@RequestParam("pid") Integer pid,@RequestParam("pagenum") Integer pagenum){
        return articleService.articlePageListByPid(pid,pagenum);
    }

    @RequestMapping("getArticleByPid/{pid}")
    @ResponseBody
    public List<Article> getArticleByPid(@PathVariable("pid") Integer pid){
        return articleService.getArticleByPid(pid);
    }

    @GetMapping("adminLogin")
    public String loginAddress(){
        return "adminLogin";
    }

//    @PostMapping("adminLogin")
//    public String login(Model model,String username, String password, HttpSession session){
//      if ("xx".equals(username)){
//          if ("az123za129".equals(password)){
//              session.setAttribute("admin",username);
//              return "redirect:admin";
//          }else{
//              model.addAttribute("error","用户名或密码错误");
//              return "adminLogin";
//          }
//      }else{
//          model.addAttribute("error","用户名或密码错误");
//          return "adminLogin";
//      }
//
//    }

    @RequestMapping("uploadimg")
    @ResponseBody
    public ImgResult uploadimg(@RequestParam("img") List<MultipartFile> list)
    {
        ImgResult imgResult = new ImgResult();
        imgResult.setErrno(0);
        String data[] = new String[5];
        String realPath = null;
        realPath = "/usr/java/upload";
        System.out.println(realPath);
        File uplpadPath = new File(realPath+"/articleimg/");
        System.out.println(uplpadPath);
        int index = 0;
        if (!uplpadPath.exists()){
            /*判断文件夹是否存在 不存在新建一个*/
            uplpadPath.mkdirs();
        }
        for (MultipartFile file : list) {
            System.out.println(System.currentTimeMillis());
            String filename = System.currentTimeMillis() + Math.round(Math.random() * 1000)+file.getOriginalFilename();
            data[index] = "/articleimg/"+filename;
            uplpadPath = new File(uplpadPath+"/"+filename);
            System.out.println(uplpadPath);
            try {
                file.transferTo(uplpadPath);
            } catch (Exception e) {
                imgResult.setErrno(imgResult.getErrno()+1);
                e.printStackTrace();
            }
            index++;
        }
        imgResult.setData(data);
        System.out.println(imgResult);
        return imgResult;
    }

    @RequestMapping("addArticle")
    @ResponseBody
    public AjxsRst addArticle(
            Article article,
            @RequestParam("file") MultipartFile file
    )
    {
        AjxsRst ajxsRst = new AjxsRst();
        try {
            File path = new File("/usr/java/upload");
            File uploadPath = new File(path,"articleimg/");
            if (!uploadPath.exists()){
                /*判断文件夹是否存在 不存在新建一个*/
                uploadPath.mkdirs();
            }
            String filename = System.currentTimeMillis() + Math.round(Math.random() * 1000)+file.getOriginalFilename();
            String relativePath = "/articleimg/"+filename;
            uploadPath = new File(uploadPath+"/"+filename);
            System.out.println(uploadPath);
            System.out.println(relativePath);
            file.transferTo(uploadPath);
            article.setImg(relativePath);
            article.setReleasetime(new Date());
            System.out.println(article);
            articleService.addArticle(article);
            ajxsRst.setMsg("发布成功");
            ajxsRst.setSuccess(true);
        } catch (Exception e) {
            ajxsRst.setMsg("发布失败");
            ajxsRst.setSuccess(false);
            e.printStackTrace();
        }

        return ajxsRst;
    }

    @RequestMapping("updatePraise/{aid}")
    @ResponseBody
    public int updatePraise(@PathVariable Integer aid, HttpSession session){
        List<Integer> like = (List)session.getAttribute("like");
        Article article = articleService.getArticle(aid);
        int nowlike = article.getPraise();
        if (like==null){
            like = new ArrayList();
            like.add(aid);
            articleService.updatePraise(aid);
            session.setAttribute("like",like);
            nowlike = nowlike+1;
        }else{
            int haslike = 0;
            for (Integer l : like) {
                if (l.equals(aid)){
                    haslike = 1;
                }
            }
            if (haslike == 0){
                like.add(aid);
                articleService.updatePraise(aid);
                session.setAttribute("like",like);
                nowlike = nowlike+1;
            }
        }
        return nowlike;
    }

    @RequestMapping("search")
    public String search(Model model,String keyWord,String searchType){
        List<Article> articles = null;
        if ("all".equals(searchType)){
            articles = articleService.searchByContent(keyWord);
        }else if ("title".equals(searchType)){
            articles = articleService.searchByTitle(keyWord);
        }
        Long total = articleService.getTotal();
        model.addAttribute("articles",articles);
        model.addAttribute("keyWord",keyWord);
        model.addAttribute("total",total);
        model.addAttribute("searchType",searchType);
        return "search";
    }
}
