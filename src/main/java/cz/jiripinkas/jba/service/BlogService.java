package cz.jiripinkas.jba.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import cz.jiripinkas.jba.entity.Blog;
import cz.jiripinkas.jba.entity.Item;
import cz.jiripinkas.jba.entity.User;
import cz.jiripinkas.jba.exception.RssException;
import cz.jiripinkas.jba.repository.BlogRepository;
import cz.jiripinkas.jba.repository.ItemRepository;
import cz.jiripinkas.jba.repository.UserRepository;

@Service
public class BlogService {
	
	@Autowired
	private BlogRepository blogRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RssService rssService;
	
	@Autowired
	private ItemRepository itemRepository;
	

	public void saveItems(Blog blog) throws RssException{
		List<Item> items = rssService.getItems(blog.getUrl());
		for (Item item : items) {
			Item savedItem= itemRepository.findByBlogAndLink(blog, item.getLink());
			if(savedItem ==null){
				item.setBlog(blog);
				itemRepository.save(item);
			}
		}
		
		
	}
	public void saveBlog(Blog blog, String name) throws RssException {
		
		User user = userRepository.findUserByName(name);
		blog.setUser(user);
		blogRepository.save(blog);
		saveItems(blog);
		// TODO Auto-generated method stub
		
	}
	
	@Scheduled(fixedDelay=3600000)
	public void reloadBlogs() throws RssException{
		List<Blog> blogs =blogRepository.findAll();
		for (Blog blog : blogs) {
			saveItems(blog);
		}
	}

	@PreAuthorize("#blog.user.name == authentication.name or hasRole('ROLE_ADMIN')")
	public void deleteBlog(@P("blog")Blog blog) {
	blogRepository.delete(blog);
		
	}


	public Blog findOne(int id) {
	
		return blogRepository.findOne(id);
	}
	
	

}
