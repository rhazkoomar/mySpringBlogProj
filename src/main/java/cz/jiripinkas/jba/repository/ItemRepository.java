package cz.jiripinkas.jba.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cz.jiripinkas.jba.entity.Blog;
import cz.jiripinkas.jba.entity.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

		List<Item> findItemsByBlog(Blog blog,Pageable pageable);
		
		Item findByBlogAndLink(Blog blog,String link);
}
