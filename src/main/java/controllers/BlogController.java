package controllers;

import model.Blog;
import model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import services.BlogService;
import sun.net.www.http.HttpClient;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/blogs")
@CrossOrigin("*")
public class BlogController {

    @Autowired
    BlogService blogService;
    Response response = new Response();

//    @GetMapping("/blog")
//    public ModelAndView showBlogList(){
//        List<Blog> blogList = blogService.findAll();
//        ModelAndView modelAndView = new ModelAndView("list");
//        modelAndView.addObject("blogList",blogList);
//        return modelAndView;
//    }

    @GetMapping
    public Response get() {
        List<Blog> blogs = blogService.findAll();
        response.data = blogs;
        response.status = 1;
        response.message = "Success";
        return response;
    }

    @GetMapping("/{id}")
    public Response getOne(@PathVariable("id") long id){
        Blog blog =blogService.findById(id);
        response.data = blog;
        response.status = 1;
        response.message = "Success";
        return response;
    }

    @PostMapping
    public Response save(@RequestBody Blog blog){
        Blog blog1 = blogService.save(blog);
        response.data= blog1;
        response.status=1;
        response.message= "Success";
        return response;
    }

    @PutMapping("/{id}")
    public Response update(@PathVariable("id") long id,@RequestBody Blog blog){
        Blog blog1 = blogService.findById(id);
        blog1.setPost(blog.getPost());
        blog1.setDatePost(blog.getDatePost());
        blog1.setAuthor(blog.getAuthor());
        blogService.save(blog1);
        response.data = blog1;
        response.status = 1;
        response.message="Success";
        return response;
    }

    @DeleteMapping("/{id}")
    public Response delete(@PathVariable("id") long id){
        response.data = blogService.remove(id);
        response.status = 1;
        response.message = "Success";
        return response;
    }



//    @GetMapping("/create")
//    public ModelAndView showCreateForm(){
//        ModelAndView modelAndView = new ModelAndView("create");
//        modelAndView.addObject("blog", new Blog());
//        return modelAndView;
//    }
//
//    @PostMapping("/create")
//    public ModelAndView saveCustomer(@ModelAttribute("blog") Blog blog){
//        blog.setDatePost(Date.valueOf(LocalDate.now()));
//        blogService.save(blog);
//        ModelAndView modelAndView = new ModelAndView("create");
////        modelAndView.addObject("date", LocalDate.now());
//        modelAndView.addObject("blog", new Blog());
//        modelAndView.addObject("message", "New post created successfully");
//        return modelAndView;
//    }
//
//    @GetMapping("/edit/{id}")
//    public ModelAndView showEditForm(@PathVariable Long id){
//        Blog blog = blogService.findById(id);
//        if(blog != null) {
//            ModelAndView modelAndView = new ModelAndView("edit");
//            modelAndView.addObject("blog", blog);
//            return modelAndView;
//
//        }else {
//            ModelAndView modelAndView = new ModelAndView("error.404");
//            return modelAndView;
//        }
//    }

//    @PostMapping("/edit")
//    public ModelAndView updateCustomer(@ModelAttribute("blog") Blog blog){
//        blogService.save(blog);
//        ModelAndView modelAndView = new ModelAndView("edit");
//        modelAndView.addObject("blog", blog);
//        modelAndView.addObject("message", "Post updated successfully");
//        return showBlogList();
//    }

//    @GetMapping("/delete/{id}")
//    public ModelAndView showDeleteForm(@PathVariable Long id){
//        Blog blog = blogService.findById(id);
//        if(blog != null) {
//            ModelAndView modelAndView = new ModelAndView("delete");
//            modelAndView.addObject("blog", blog);
//            return modelAndView;
//
//        }else {
//            ModelAndView modelAndView = new ModelAndView("error.404");
//            return modelAndView;
//        }
//    }
//
//    @PostMapping("/delete")
//    public String deleteCustomer(@ModelAttribute("blog") Blog blog){
//        blogService.remove(blog.getId());
//        return "redirect:blog";
//    }
}
