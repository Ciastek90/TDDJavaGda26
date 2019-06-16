import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.assertj.core.data.Offset;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@RunWith(JUnitParamsRunner.class)
public class CalculatorTest {
    @Test
    public void sum_a2b5_7(){
        double expected = 7;

        double actual = Calculator.sum(2,5);

        Assert.assertEquals(expected, actual, 0.001);
    }

    @Test
    public void sum_a5b6_11(){
        int expected = 11;

        int actual = Calculator.sum(5,6);

        Assert.assertEquals(expected,actual);
    }

    @Test
    public void sum_a0_2b0_1_expected0_3(){
        double expected = 0.3;

        double actual = Calculator.sum(0.2, 0.1);

        Assert.assertEquals(expected,actual,0.1);
    }

    @Test
    public void substraction_a19b13_6(){
        double expected = 6;

        double actual = Calculator.substraction(19,13);

        Assert.assertEquals(expected,actual,0.1);
    }

    @Test
    public void multiply_a5b10_50(){
        double expected = 50;

        double actual = Calculator.multiply(5,10);

        Assert.assertEquals(expected,actual, 0.1);
    }

    @Test
    public void divide_a10b5_2(){
        double expected = 2;

        double actual = Calculator.divide(10,5);

        Assert.assertEquals(expected,actual, 0.1);
    }

    @Test
    public void divide_a10b0_IllegalArgumentExceptionTryCatch(){
        try{
            checkException();
        } catch (Exception e){
            return;
        }

        Assert.fail("Niepowiodło się wyrzucanie wyjątku");
    }

    public void checkException(){
        Calculator.divide(10,0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void divide_a10b0_IllegalArgumentExceptionExpected(){
        Calculator.divide(10,0);
    }

    @Rule
    public ExpectedException rule = ExpectedException.none();

    @Test
    public void divide_a10b0_IllegalArgumentExceptionRule(){
        rule.expect(IllegalArgumentException.class);
        Calculator.divide(10,0);
    }

    @Test
    public void log_a2x4_2(){
        double expected = 2;

        double actual = Calculator.log(2,4);

        Assert.assertEquals(expected, actual, 0.01);
    }

    @Test(expected = IllegalArgumentException.class)
    public void log_a_1b10_IllegalArgumentException(){
        Calculator.log(-1,10);
    }

    @Test
    public void log_a_2b10_IllegalArgumentException(){
        rule.expect(IllegalArgumentException.class);
        Calculator.log(-2,10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void log_a2b1_IllegalArgumentException(){
        Calculator.log(2,-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void log_a1b10_IllegalArgumentException(){
        Calculator.log(1,10);
    }

    @Test
    public void log_a_3b10_IllegalArgumentExceptionMessageBaseOfLogarithmShouldBeGreaterThan0() {
        assertThatThrownBy(() -> {
            Calculator.log(-3, 10);
        }).hasMessage("Podstawa logarytmu musi być większa od 0");
    }

    @Test
    public void log_a2b4_2(){
        double expected = 2;

        double actual = Calculator.log(2,4);

        assertThat(expected).isEqualTo(actual, Offset.strictOffset(0.01));
    }

    @Test
    public void draftassertjassert(){
        try{
            Calculator.log(-3,10);
            Assert.fail();
        }catch (Exception e){
            String message = e.getMessage();
            Assert.assertEquals(message, "Podstawa logarytmu musi być większa od 0");
        }
    }

    /**
     * Przykłąd testu ze złożoną assercją
     */
    @Test
    public void PetTest(){
        Pet p = new Pet();

        p.setAge(2);
        p.setWeight(10);

        complexAssert(p);
    }

    /**
     * złożona assercja dla obiektów
     * @param p
     */
    private void complexAssert(Pet p){
        Assert.assertEquals(p.getAge(),2);
        Assert.assertEquals(p.getWeight(),10,0.1);
    }

    @Test
    @Parameters({"1,2,3","23,17,40","1,2,3"})
    public void sum_parametrized(double a, double b, double expected){
        double actual = Calculator.sum(a,b);

        assertThat(expected).isEqualTo(actual, Offset.strictOffset(0.01));
    }

    @Test
    @Parameters({"0,0","1,1","2,1","3,2","4,3","5,5","6,8","7,13","8,21","9,34","10,55"})
    public void getFibonacciNumber_parametrized(int n, int expected){
        int actual = Calculator.getFibonaciNumber(n);

        assertThat(expected).isEqualTo(actual);
    }

    @Test
    @Parameters(method = "getFibonacciData")
    public void getFibonacciNumber_parametrizedByMethod(int n, int expected){
        int actual = Calculator.getFibonaciNumber(n);

        assertThat(expected).isEqualTo(actual);
    }

    private Object[] getFibonacciData(){
        return new Object[]{
                new Object[]{0,0},
                new Object[]{1,1},
                new Object[]{2,1},
                new Object[]{3,2},
                new Object[]{4,3},
                new Object[]{5,5},
                new Object[]{6,8},
                new Object[]{7,13},
                new Object[]{8,21},
                new Object[]{9,34},
                new Object[]{10,55}
        };
    }

    @Test
    @Parameters(source = DifferenceDataProvider.class)
    public void substraction_parametrizedByClass(double a, double b, double expected){
        double actual = Calculator.substraction(a, b);

        assertThat(expected).isEqualTo(actual);
    }
}
