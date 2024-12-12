package fr.fms.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.fms.entities.Category;

	public interface CategoryRepository extends JpaRepository<Category, Long> {
	
		//Exercice1.6 All by Asc and Descm
		@Query("SELECT c FROM Category c ORDER BY c.name ASC")
		List<Category> findAllCategoriesSortedAsc();
		@Query("SELECT c FROM Category c ORDER BY c.name DESC")
		List<Category> findAllCategoriesSortedDesc();
		
		@Modifying
		@Transactional
		@Query("UPDATE Category a SET a.name = :name WHERE a.id = :id")
		public void updateCategoryById(@Param("id") Long id,
								  		@Param("name") String name);

}
