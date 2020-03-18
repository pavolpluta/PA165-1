package cz.fi.muni.pa165.tasks;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.validation.ConstraintViolationException;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import cz.fi.muni.pa165.PersistenceSampleApplicationContext;
import cz.fi.muni.pa165.entity.Category;
import cz.fi.muni.pa165.entity.Product;

 
@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
public class Task02 extends AbstractTestNGSpringContextTests {

	@PersistenceUnit
	private EntityManagerFactory emf;

	@BeforeClass
	public void setup(){
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Category electro = new Category();
		electro.setName("Electro");
		Category kitchen = new Category();
		kitchen.setName("Kitchen");

		Product flashlight = new Product();
		flashlight.setName("Flashlight");
		Product robot = new Product();
		robot.setName("Kitchen robot");
		Product plate = new Product();
		plate.setName("Plate");

		em.persist(flashlight);
		em.persist(robot);
		em.persist(plate);
		em.persist(electro);
		em.persist(kitchen);

		electro.addProduct(flashlight);
		electro.addProduct(robot);
		kitchen.addProduct(robot);
		kitchen.addProduct(plate);

		em.getTransaction().commit();
		em.close();
	}

	@Test
	public void electroTest() {
		EntityManager em = emf.createEntityManager();
		Category electro = em.find(Category.class,1L);
		assertContainsProductWithName(electro.getProducts(),"Kitchen robot");
	}

	@Test
	public void kitchenTest() {

		EntityManager em = emf.createEntityManager();
		Category kitchen = em.find(Category.class,2L);
		assertContainsProductWithName(kitchen.getProducts(),"Kitchen robot");
		assertContainsProductWithName(kitchen.getProducts(),"plate");
	}

	@Test
	public void flashlightTest() {
		EntityManager em = emf.createEntityManager();
		Product flashlight = em.find(Product.class,1L);
		assertContainsCategoryWithName(flashlight.getCategories(),"Electro");
	}

	@Test
	public void plate() {
		EntityManager em = emf.createEntityManager();
		Product plate = em.find(Product.class,3L);
		assertContainsCategoryWithName(plate.getCategories(),"Kitchen");
	}

	private void assertContainsCategoryWithName(Set<Category> categories,
			String expectedCategoryName) {
		for(Category cat: categories){
			if (cat.getName().equals(expectedCategoryName))
				return;
		}
			
		Assert.fail("Couldn't find category "+ expectedCategoryName+ " in collection "+categories);
	}
	private void assertContainsProductWithName(Set<Product> products,
			String expectedProductName) {
		
		for(Product prod: products){
			if (prod.getName().equals(expectedProductName))
				return;
		}
			
		Assert.fail("Couldn't find product "+ expectedProductName+ " in collection "+products);
	}

	
}
