package fr.fms.dao;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.fms.entities.Article;
import fr.fms.entities.Category; 

public interface ArticleRepository extends JpaRepository<Article, Long>{
	
	
	public List<Article> findByBrand(String brand);
	public List<Article> findByBrandContains(String brand);
	public List<Article> findByBrandAndPrice(String brand, double price);
	public List<Article> findByBrandAndPriceGreaterThan(String brand, double price);
	public List<Article> findByCategoryId(Long categoryId);
	public List<Article> findAll();
	public Optional<Article> findById(Long id);
	//Page <Article> FindAll(); 
	//public void displayMenu();

	
//
//	@Query("select A from Article A where A.brand like %:x% and A.price > :y")
//	public List<Article> searchArticles(@Param("x") String kw, @Param("y")double price);
//
//	Exercice1.2
//	@Query("select A from Article A  WHERE A.category_Id c WHERE c.id = :category_Id")
//	public List<Article> findByCategoryById(@Param ("category_Id") Long categoryId);
	
	//Exercice1.3
	@Query("SELECT a FROM Article a WHERE a.brand LIKE %:x% AND a.description LIKE %:y%")
	public List<Article> findByDescriptionAndMarque(@Param("x") String brand, @Param("y") String description);
	
	//Exercice1.4
	@Modifying
	@Transactional
	@Query("DELETE FROM Article a WHERE a.id = :id")
	public void deleteById(@Param("id") Long id);
	
	//Exercice1.5
	@Modifying
	@Transactional
	@Query("UPDATE Article a SET a.brand = :brand, a.description = :description, a.price = :price WHERE a.id = :id")
	public void updateArticleById(@Param("id") Long id, 
 								@Param("brand") String brand, 
								@Param("description") String description,
								@Param("price") double price);
	
	//Exercice1.7
	
	
	
	
	
}
