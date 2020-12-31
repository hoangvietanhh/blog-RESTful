package controllers;

import model.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;
import services.BlogService;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@RestController
//@RequestMapping("/blogs")
@CrossOrigin("*")
public class BlogController {

    @Autowired
    BlogService blogService;

//    Response response = new Response();

//    @GetMapping("/blog")
//    public ModelAndView showBlogList(){
//        List<Blog> blogList = blogService.findAll();
//        ModelAndView modelAndView = new ModelAndView("list");
//        modelAndView.addObject("blogList",blogList);
//        return modelAndView;
//    }
    @RequestMapping(value = "/blog/", method = RequestMethod.GET)
    public ResponseEntity<List<Blog>> showBlogList() {
        List<Blog> blogs = blogService.findAll();
        if (blogs.isEmpty()) {
            return new ResponseEntity<List<Blog>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Blog>>(blogs, HttpStatus.OK);
    }

    @RequestMapping(value = "/blog/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Blog> getCustomer(@PathVariable("id") long id) {
        System.out.println("Blog with id " + id);
        Blog blog = blogService.findById(id);
        if (blog == null) {
            System.out.println("Blog with id " + id + " not found");
            return new ResponseEntity<Blog>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Blog>(blog, HttpStatus.OK);
    }

    @RequestMapping(value = "/blog/", method = RequestMethod.POST)
    public ResponseEntity<Void> createCustomer(@RequestBody Blog blog, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Blog " + blog.getPost());
        blogService.save(blog);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/customers/{id}").buildAndExpand(blog.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/blog/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Blog> updateCustomer(@PathVariable("id") long id, @RequestBody Blog blog) {
        System.out.println("Updating Post " + id);

        Blog currentBlog = blogService.findById(id);

        if (currentBlog == null) {
            System.out.println("Post with id " + id + " not found");
            return new ResponseEntity<Blog>(HttpStatus.NOT_FOUND);
        }

        currentBlog.setPost(blog.getPost());
        currentBlog.setAuthor(blog.getAuthor());
        currentBlog.setId(blog.getId());

        blogService.save(currentBlog);
        return new ResponseEntity<Blog>(currentBlog, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/blog/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Blog> deleteCustomer(@PathVariable("id") long id) {

        Blog customer = blogService.findById(id);
        if (customer == null) {
            return new ResponseEntity<Blog>(HttpStatus.NOT_FOUND);
        }

        blogService.remove(id);
        return new ResponseEntity<Blog>(HttpStatus.NO_CONTENT);
    }


//    @GetMapping
//    public Response get() {
//        List<Blog> blogs = blogService.findAll();
//        response.data = blogs;
//        response.status = 1;
//        response.message = "Success";
//        return response;
//    }
//
//    @GetMapping("/{id}")
//    public Response getOne(@PathVariable("id") long id){
//        Blog blog =blogService.findById(id);
//        response.data = blog;
//        response.status = 1;
//        response.message = "Success";
//        return response;
//    }
//
//    @PostMapping
//    public Response save(@RequestBody Blog blog){
//        Blog blog1 = blogService.save(blog);
//        response.data= blog1;
//        response.status=1;
//        response.message= "Success";
//        return response;
//    }
//
//    @PutMapping("/{id}")
//    public Response update(@PathVariable("id") long id,@RequestBody Blog blog){
//        Blog blog1 = blogService.findById(id);
//        blog1.setPost(blog.getPost());
//        blog1.setDatePost(blog.getDatePost());
//        blog1.setAuthor(blog.getAuthor());
//        blogService.save(blog1);
//        response.data = blog1;
//        response.status = 1;
//        response.message="Success";
//        return response;
//    }
//
//    @DeleteMapping("/{id}")
//    public Response delete(@PathVariable("id") long id){
//        response.data = blogService.remove(id);
//        response.status = 1;
//        response.message = "Success";
//        return response;
//    }



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
//
//    @PostMapping("/edit")
//    public ModelAndView updateCustomer(@ModelAttribute("blog") Blog blog){
//        blogService.save(blog);
//        ModelAndView modelAndView = new ModelAndView("edit");
//        modelAndView.addObject("blog", blog);
//        modelAndView.addObject("message", "Post updated successfully");
//        return showBlogList();
//    }
//
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
