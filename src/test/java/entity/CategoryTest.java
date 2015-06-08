package entity;

import crud.EntityService;
import org.junit.AfterClass;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Tests for the app
 */
public class CategoryTest {

    private static EntityService es = new EntityService();

    @Test
    public void getAllCategoriesTest() {
        List<Category> all = es.getAll(Category.class);
        System.out.println(all.size());
        for (Category c : all) {
            System.out.println(c);
        }

        assertTrue(all.size() > 0);
    }

    @Test
    public void getCategoryByIdTest() {
        Category c = es.getById(1, Category.class);
        assertNotNull(c);
    }

    @Test
    public void addCategoryTest() {
        int beforeCount = es.getAll(Category.class).size();

        Category category = new Category("Second", "Category");
        es.add(category);
        int afterCount = es.getAll(Category.class).size();

        assertEquals(beforeCount + 1, afterCount);
        es.delete(category);
    }

    @Test
    public void updateCategoryTest() {
        Category c = es.getById(2, Category.class);
        String newName = "Updated Name";
        String newDesc = "Updated Description";
        c.setName(newName);
        c.setDescription(newDesc);
        es.update(c);
        Category c2 = es.getById(2, Category.class);
        assertEquals(newName, c2.getName());
        assertEquals(newDesc, c2.getDescription());
    }

    @Test
    public void deleteCategoryTest() {
        Category category = new Category("It'll", "be deleted");
        es.add(category);
        int beforeCount = es.getAll(Category.class).size();

        es.delete(category);
        int afterCount = es.getAll(Category.class).size();

        assertEquals(beforeCount-1, afterCount);
    }

    @AfterClass
    public static void closeResources() {
        es.close();
    }

}
