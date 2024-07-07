package com.aditi.itemlisting.controller;


import com.aditi.itemlisting.repository.PostRepository;
import com.aditi.itemlisting.model.Post;
import com.aditi.itemlisting.repository.SearchRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

//import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController

// since request is coming from different server hence mentioned CrossOrigin. default server 8081 in appln prop.
@CrossOrigin(origins = "http://localhost:3000")// React is using this host.
public class PostController {

    @Autowired// spring will create object for me and it will map it
    PostRepository repo;// behind the scene, it creates a class(not interface)PostRepository and then repo is the object for it.
    @Autowired
    SearchRepository srepo;// object of self created searchrepo class.




    //everytime user request for a home page it should redirect it to Swagger page
    //this function is for swagger
    @ApiIgnore// ignores default api calls and only the ones created by us is displayed.
    @RequestMapping(value="/")
    public void redirect(HttpServletResponse response) throws IOException {
        response.sendRedirect("/swagger-ui.html");
    }



    // url that requests to get all the posts
    @GetMapping("/allPosts")
    @CrossOrigin
    public List<Post> getAllPosts(){
    return repo.findAll();
    }



    // example: /posts/food
    @GetMapping("/posts/{text}")// user passes the text that needs to be searched.
    @CrossOrigin
    public List<Post> search(@PathVariable String text)// text is passed to this function
    {
        return srepo.findByText(text);
    }



    @PostMapping("/postNewItem")
    @CrossOrigin
    public Post addPost(@RequestBody Post post)
    {
        return repo.save(post);
    }
}
