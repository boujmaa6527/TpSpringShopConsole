package fr.fms.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Category  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name; 

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + "]";
	}

	
	// pour supprimer en cascade les articles liés a la catégorie.
	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)// ESCLAVE
	private List<Article> articles;// une catégorie est liée à plusieurs articles
	
	public Category() {}
	
	public Category(String name) {this.name = name;}

	public Long getId() {
		return id;
	}

	public Category(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	

}

