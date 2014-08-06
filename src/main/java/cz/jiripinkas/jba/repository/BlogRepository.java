package cz.jiripinkas.jba.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cz.jiripinkas.jba.entity.Blog;
import cz.jiripinkas.jba.entity.User;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Integer> {
 
	List<Blog> findBlogsByUser(User user);
}
