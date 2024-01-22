import entity.Ingredient;
import static org.junit.Assert.*;
import org.junit.Test;


public class IngredientTest {
    private Ingredient generateIngredient() {
        Ingredient i1 = new Ingredient("i1", 1.0f, "c1", "g");
        return i1;
    }

    @org.junit.Test
    public void testSetNameIngredient() {
        Ingredient i1 = generateIngredient();
        i1.setName("newi1");
        assert (i1.getName().equals("newi1"));
    }

    @org.junit.Test
    public void testSetMeasurementIngredient() {
        Ingredient i1 = generateIngredient();
        i1.setMeasurement("newg");
        assert (i1.getMeasurement().equals("newg"));
    }

    @org.junit.Test
    public void testSetCategoryIngredient() {
        Ingredient i1 = generateIngredient();
        i1.setCategory("newc1");
        assert (i1.getCategory().equals("newc1"));
    }
}
