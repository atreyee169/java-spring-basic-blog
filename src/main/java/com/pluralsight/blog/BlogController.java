package com.pluralsight.blog;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pluralsight.blog.data.PostRepository;
import com.pluralsight.blog.model.Post;

@Controller
public class BlogController {

	@Autowired
	PostRepository postRepository;
	
	public BlogController(PostRepository postRepository) {
		super();
		this.postRepository = postRepository;
	}


	@RequestMapping("/")
	public String listPosts(ModelMap modelMap)
	{
		List<Post> allposts=postRepository.getAllPosts();
		modelMap.put("title", "Blog Post");
		modelMap.put("posts", allposts);
		return "home";
	}
	
	@RequestMapping("/post/{id}")
	public String postDetails(@PathVariable Long id,ModelMap modelMap)
	{
		modelMap.put("post", postRepository.findById(id));
		return "post-details";
	}
}
