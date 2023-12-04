import entity.Nutrient;
import static org.junit.Assert.*;
import org.junit.Test;

public class NutrientTest {
    private Nutrient generateNutrient() {
        Nutrient n1 = new Nutrient("n1", 1.0f, "Macro");
        return n1;
    }
    @org.junit.Test
    public void testNutrientGetName() {
        Nutrient n1 = generateNutrient();
        assert (n1.getName().equals("n1"));
    }
    @org.junit.Test
    public void testNutrientGetAmount() {
        Nutrient n1 = generateNutrient();
        assert (n1.getAmount().equals(1.0f));
    }
    @org.junit.Test
    public void testNutrientGetType() {
        Nutrient n1 = generateNutrient();
        assert (n1.getType().equals("Macro"));
    }
}
