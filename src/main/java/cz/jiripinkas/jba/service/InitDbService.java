package cz.jiripinkas.jba.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
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

@Transactional
@Service

public class InitDbService {

	

@Autowired
private UserRepository userRepository;

@Autowired
private BlogRepository blogRepository;

@Autowired
private ItemRepository itemRepository;

@Autowired
private RoleRepository roleRepository;

@PostConstruct
public void init(){
	
	if(roleRepository.findRoleByName("ROLE_ADMIN")==null){
	Role roleUser = new  Role();
	roleUser.setName("ROLE_USER");
	roleRepository.save(roleUser);
	
	Role roleAdmin = new  Role();
	roleAdmin.setName("ROLE_ADMIN");
	roleRepository.save(roleAdmin);
	
	User userAdmin = new  User();
	userAdmin.setEnabled(true);
	userAdmin.setName("admin");
	BCryptPasswordEncoder encoder =new BCryptPasswordEncoder();
	
	userAdmin.setPassword(encoder.encode("123456"));
	List<Role> roles = new ArrayList<Role>();
	roles.add(roleUser);
	roles.add(roleAdmin);
	userAdmin.setRoles(roles);
	userRepository.save(userAdmin);
	
	Blog timeOfIndia = new Blog();
	timeOfIndia.setName("Java vids");
	timeOfIndia.setUrl("http://feeds.feedburner.com/javavids?format=xml");
	timeOfIndia.setUser(userAdmin);
	blogRepository.save(timeOfIndia);
	
//	Item item1 = new Item();
//	item1.setTitle("Times of India 1");
//	item1.setBlog(timeOfIndia);
//	item1.setLink("http://timesofindia.indiatimes.com");
//	item1.setPublishedDate(new Date());
//	item1.setGuid("http://timesofindia.feedsportal.com/c/33039/f/533974/s/3cf04048/sc/13/l/0Ltimesofindia0Bindiatimes0N0Carticleshow0C391555390Bcms/story01.htm");
//	
//	
//	Item item2 = new Item();
//	item2.setTitle("Times of India 2");
//	item2.setBlog(timeOfIndia);
//	item2.setLink("http://timesofindia.indiatimes.com");
//	item2.setPublishedDate(new Date());
//	item2.setGuid("http://timesofindia.feedsportal.com/c/33039/f/533974/s/3cf04048/sc/13/l/0Ltimesofindia0Bindiatimes0N0Carticleshow0C391555390Bcms/story01.htm");
//	
//	itemRepository.save(item1);
//	itemRepository.save(item2);
	}

}

}
