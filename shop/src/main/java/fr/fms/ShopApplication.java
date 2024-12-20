package fr.fms;

import fr.fms.dao.*;
import fr.fms.entities.Article;
import fr.fms.entities.Category;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@SpringBootApplication
public class ShopApplication implements CommandLineRunner{
	
	//Varaiable  instance scanner/input to use in class
	static Scanner sc = new Scanner(System.in); 

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ArticleRepository articleRepository; 

	public static void main(String[] args) {
		SpringApplication.run(ShopApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		//		Category category = new Category("Smartphone");
		//		Article article = new Article("Samsung","s10",250, category);
		//		
		//		categoryRepository.save(category);
		//		articleRepository.save(article);
		//		
		//		for(Article artic : articleRepository.findByBrand("Samsung")) {
		//
		//			System.out.println(artic);
		//		}
		//		for(Article articBrandAndPrice : articleRepository.findByBrandAndPrice("Samsung", 250)) {
		//			
		//			System.out.println(articBrandAndPrice);
		//		}
		//		for(Article articGreaterThan : articleRepository.findByBrandAndPriceGreaterThan("Samsung", 200)) {
		//			
		//			System.out.println(articGreaterThan);
		//		}
		//		for(Article articSearch : articleRepository.searchArticles("Samsung", 300)) {
		//			
		//			System.out.println(articSearch);
		//		}
		//		Category smartPhone = categoryRepository.save(new Category("SmartPhone"));
		//		Category tablet = categoryRepository.save(new Category("Tablet"));
		//		Category pc = categoryRepository.save(new Category("PC"));
		//
		//		articleRepository.save(new Article("Samsung", "S10", 500, smartPhone));
		//		articleRepository.save(new Article("Samsung", "S9", 350, smartPhone));
		//		articleRepository.save(new Article("Xiaomi", "MI10", 100, smartPhone));
		//		
		//		articleRepository.save(new Article("Samsung", "GalaxyTab", 440, tablet));
		//		articleRepository.save(new Article("Apple", "Ipad", 350, tablet));
		//		articleRepository.save(new Article("Apple", "Ipad1000", 350, tablet));
		//
		//		
		//		articleRepository.save(new Article("Hp", "Elitebook", 600, pc));
		//		articleRepository.save(new Article("Asus", "proElite", 500, pc));
		//		articleRepository.save(new Article("Toshiba", "AgainElite", 500, pc));
		//Afficher les article par catégory
		//		for(Article articleBycategoryId : articleRepository.findByCategoryId(1L)) {
		//			System.out.println(articleBycategoryId);
		//		}
		//		
		//		//Exercice1.2
		//		for(Article articleAll: articleRepository.findAll()) {
		//			System.out.println(articleAll);
		//		}
		//		//Exercice1.2
		//		Optional<Article> article = articleRepository.findById(5L);
		//		System.out.println(article);

		//Exercice1.3
		//		for(Article articleAll: articleRepository.findByDescriptionAndMarque("Apple", "Ipad")) {
		//			System.out.println(articleAll);
		//		}
		//Exercie1.4 (delete number 11 of bdd article)
		//Optional<Article> article = articleRepository.findById(4L);
		//if(article.isPresent()) {
		//					articleRepository.deleteById(4L);
		//		System.out.println("Full success delete");
		//				}else {
		//							System.out.println("Id Non identifié en Base");
		//				}


		//Exercice1.5 update de l'article avec l'id
		//		articleRepository.updateArticleById(7L, "hp", "ElitBook", 700);
		//		System.out.println("Full success Update");

		//Exercice1.6 Allcategory by ASC
		//		for(Category categoryALL: categoryRepository.findAllCategoriesSortedAsc()) {
		//			System.out.println(categoryALL);
		//		}

		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println("Bienvenu dans notre application de gestion d'articles");
			System.out.println("1:Afficher tous les articles sans pagination");
			System.out.println("2:Afficher tous les articles avec paginations");
			System.out.println("*********************************************");
			System.out.println("3:Ajouter un article");
			System.out.println("4:Afficher un article");
			System.out.println("5:Supprimer un article");
			System.out.println("6:modifier un article");
			System.out.println("*********************************************");
			System.out.println("7:Ajouter une catégorie");
			System.out.println("8:Afficher une catégorie");
			System.out.println("9:Supprimer une catégorie");
			System.out.println("10:Mettre à jour une catégorie");
			System.out.println("11:Afficher tous les articles d'une catégorie");
			System.out.println("*********************************************");
			System.out.println("12:Sortir du programme");
			System.out.println("CHOISISSEZ UNE OPTION");

			int value = sc.nextInt();
			sc.nextLine();// toujour vider le cache
			switch(value) {
			case 1:
				toDisplayWithoutPagination();
				System.out.println("*********************************************");
				break;
			case 2:
				toDisplayWithPagination();
				break;
			case 3:
				toAddArticle();
				break;
			case 4:
				toDisplayArticle();
				break;
			case 5:
				toDeleteArticle();
				break;
			case 6:
				toModifieArticle();
				break;
			case 7:
				toAddCategorie();
				break;

			case 8:
				toDisplayCategorie();
				break;
			case 9:	
				toDeleteCategorie();
				break;

			case 10:
				toUpdateCategorie();
				break;
			case 11:
				ToAllArticleByCategorie();
				break;
			case 12 :
				System.out.println("Retour au Menu");
				break;

			}
			
		}
	}
		public void toDisplayWithoutPagination() {
			System.out.println("*********************************************");
			System.out.println("Afficher tous les articles");
			System.out.println(" IDENTIFIANT   BRAND   DESCRIPTION  PRICE CATEGORY");
			for(Article articleAll: articleRepository.findAll()) {
				System.out.println(articleAll);
			};
		}
		public void toDisplayWithPagination() {
			int pageNumber = 0;
			int PageSize = 5;
			PageRequest pageable;
			Page<Article> page;
			while(true) {
				// p inside boucle because infinity boucle
				pageable = PageRequest.of(pageNumber, PageSize);
				page = articleRepository.findAll(pageable);
				System.out.println("Page"+(pageNumber +1 ) +" of "+page.getTotalPages());
				System.out.println("total Element:"+ page.getTotalElements());
				page.getContent().forEach(article -> System.out.println(article));
				System.out.println("\nOption: ");
				System.out.println("1. Next page");
				System.out.println("2. Previous page");
				System.out.println("3. Exit");
	
	
				int choice = sc.nextInt();
				if(choice == 1 && page.hasNext()) {
					pageNumber++;
				}else if (choice == 2 && pageNumber > 0) {
					pageNumber--;
				}else if (choice == 3) {
					break;
				}else if(choice == 4) {
					System.out.println("\nOption");
					System.out.println("1. Next page");
					System.out.println("2. Previous page");
					System.out.println("3. Exit");
				}
	
				else {
					System.out.println("Error choice or no more pages");
				}
			}
	}
		public void toAddArticle() {
			Scanner sc = new Scanner(System.in);
			System.out.println("Ajouter un article");
			System.out.println("Entrez une marque/brand");
			String newBrand = sc.nextLine();
			System.out.println("Entrez uen description");
			String newDescription = sc.nextLine();
			System.out.println("Entrez un  Prix");
			double newPrice = sc.nextDouble();
			System.out.println("Entrez numero de la catégorie");
			Long newCategorie = sc.nextLong();
			Optional<Category> categorie =  categoryRepository.findById(newCategorie);
			Article newArticle = new Article(newBrand, newDescription,newPrice, categorie.get());
			articleRepository.save(newArticle);
			System.out.println("Opération Succes Full");
		}
		public void toDisplayArticle() {
			Scanner sc = new Scanner(System.in);
			System.out.println("Entrez l'Id de l'article");
			Long idValue = sc.nextLong();
			Optional<Article> article = articleRepository.findById(idValue);
			if(article.isPresent()) {
				Article ar = article.get();
				//change methode toString to dispaly allArticle
				System.out.println(ar);
			}else {
				System.out.println("Id Article Inconnu");
			}
		}
		public void toDeleteArticle() {
			System.out.println("Entrez l'Id de l'article");
			Long idValueToDel = sc.nextLong();
			articleRepository.deleteById(idValueToDel);
			System.out.println("Opération Success Full");
		}
		public void toModifieArticle() {
			System.out.println("Entrez l'Id de l'article a modifier");
			Long idValueToModifie = sc.nextLong();
			sc.nextLine();
			Optional<Article> articleToModifie = articleRepository.findById(idValueToModifie);
			if(articleToModifie.isPresent()) {
				System.out.println(articleToModifie);
				System.out.println("Entrez pour modifier marque/brand");
				String newBrandMode = sc.nextLine();
				sc.nextLine();
				System.out.println("Entrez pour modifier description");
				String newDescriptionMode = sc.nextLine();
				System.out.println("Entrez pour modifier Prix");
				double newPriceMode = sc.nextDouble();
				articleRepository.updateArticleById(idValueToModifie, newBrandMode, newDescriptionMode, newPriceMode);
				System.out.println("Opération Success Full");
				
			}else {
				System.out.println("Id Article Inconnu");
			}
		}
		public void toAddCategorie() {
			System.out.println("Ajouter une nouvelle catégorie");
			System.out.println("Entrez nom de la catégorie");
			String nameCategorie = sc.nextLine();
			categoryRepository.save(new Category(nameCategorie));
			System.out.println("Opération Success Full");
		}
		public void toDisplayCategorie() {
			System.out.println("Afficher une catégorie");
			System.out.println("Entrez ID de la catégorie");
			Long idValueCat = sc.nextLong();
			Optional<Category> category = categoryRepository.findById(idValueCat);
			if(category.isPresent()) {
				System.out.println(category);
			}else {
				System.out.println("Id category Inconnu");
			}
		}
		public void toDeleteCategorie() {
			System.out.println("Entrez l'Id de la catégory");
			Long idCatToDel = sc.nextLong();
			//to verif if category exist
			Optional<Category> categoryTodel = categoryRepository.findById(idCatToDel);
			if(categoryTodel.isPresent()) {
				System.out.println("Are you sur to delete? Oui/Non");
				sc.nextLine();

				String valDel = sc.nextLine();
				if(valDel.equalsIgnoreCase("Oui")) {
					categoryRepository.deleteById(categoryTodel.get().getId());
					System.out.println("Opération Success Full");
				}else {
					System.out.println("Retour au Menu");
				}

			}else {
				System.out.println("Id category Inconnu, Retour au Menu");
			}
		}
		public void toUpdateCategorie() {
			System.out.println("Entrez l'Id de la catégorie a modifier");
			Long idCatToModifie = sc.nextLong();
			sc.nextLine();
			Optional<Category> categoryToMode = categoryRepository.findById(idCatToModifie);
			if(categoryToMode.isPresent()) {
				System.out.println("Entrez name pour modifier la catégorie");
				String newCatName = sc.nextLine();
				categoryRepository.updateCategoryById(categoryToMode.get().getId(), newCatName);
				System.out.println("Opération Success Full");

			}else {
				System.out.println("Id category Inconnu");
			}
		}
		public void ToAllArticleByCategorie() {
			System.out.println("Entrez id de la  catégorie");
			Long IdCateArticle = sc.nextLong();
			Optional<Category> categoryToDisplay = categoryRepository.findById(IdCateArticle);
			for(Article articleBycategoryId : articleRepository.findByCategoryId(categoryToDisplay.get().getId())) {
				if(categoryToDisplay.isPresent()) {
					System.out.println(articleBycategoryId);
				}else {
					System.out.println("Id category Inconnu");
				}

			}
		}
}


