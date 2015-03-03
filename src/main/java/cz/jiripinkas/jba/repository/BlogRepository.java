package cz.jiripinkas.jba.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import cz.jiripinkas.jba.entity.Blog;
import cz.jiripinkas.jba.entity.User;

public interface BlogRepository extends JpaRepository<Blog, Integer>{

	@Query("select b from Blog b where user = ?1 order by b.id")
	List<Blog> findByUser(User user);

	Blog findByUrl(String url);

	@Query("select b from Blog b join fetch b.user where b.id = ?1")
	Blog findOneFetchUser(int id);

	@Query("select b from Blog b left join fetch b.category where b.shortName = ?1")
	Blog findByShortName(String shortName);

	@Query("select b from Blog b join fetch b.user left join fetch b.category order by b.shortName")
	List<Blog> findAllFetchUser();

	Blog findByIdAndUserName(int id, String username);
}
