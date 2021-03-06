package cz.jiripinkas.jba.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import cz.jiripinkas.jba.entity.Blog;
import cz.jiripinkas.jba.entity.Item;
import cz.jiripinkas.jba.entity.Role;
import cz.jiripinkas.jba.entity.User;
import cz.jiripinkas.jba.repository.BlogRepository;
import cz.jiripinkas.jba.repository.ItemRepository;
import cz.jiripinkas.jba.repository.RoleRepository;
import cz.jiripinkas.jba.repository.UserRepository;

@Service
@Transactional
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BlogRepository blogRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	public List<User> findAll(){
		return userRepository.findAll();
		
	}

	public User findOne(int id) {
		// TODO Auto-generated method stub
		return userRepository.findOne(id);
	}

	@Transactional
	public User findOneWithBlogs(int id) {
		User user=findOne(id);
		List<Blog> blogs =blogRepository.findBlogsByUser(user);
		
		
		for(Blog blog:blogs){
			List<Item> items=itemRepository.findItemsByBlog(blog, new PageRequest(0, 10, Direction.DESC, "publishedDate"));
			//System.out.println(blog.getName());
			blog.setItems(items);
		}
		user.setBlogs(blogs);
		return user;
	}

	public void save(User user) {
		// TODO Auto-generated method stub
		user.setEnabled(true);
		BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));
		List<Role> roles = new ArrayList<Role>();
		roles.add(roleRepository.findRoleByName("ROLE_USER"));
		user.setRoles(roles);
		userRepository.save(user);
		
	}

	public User findOneWithBlogs(String userName) {
		// TODO Auto-generated method stub
		User user= userRepository.findUserByName(userName);
		
		return findOneWithBlogs(user.getId());
	}

	public void deleteUser(int id) {
		//User user =userRepository.findOne(id);
		userRepository.delete(id);;
		
	}

	public User findOne(String username) {
		// TODO Auto-generated method stub
		return userRepository.findUserByName(username);
	}

}
