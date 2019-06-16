import org.junit.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class PetTest {
    private static Pet pet;

    @BeforeClass
    public static void setup(){
        pet = mock(Pet.class);

        System.out.println("BeforeClass");
    }

    @Before
    public void setupTest(){
        System.out.println("Before");
    }

    @After
    public void cleanupTest(){
        System.out.println("After");
    }

    @AfterClass
    public static void cleanup(){
        pet = null;
        System.out.println("AfterClass");
    }

    @Test
    public void getName_mockedPet_callVerification(){

        pet.getName();

        verify(pet).getName();
    }

    @Test
    public void setName_mockedPet_callVerification(){

        pet.setName("Fafik");

        verify(pet).setName("Fafik");
    }

    @Test
    public void getName_mocketPetWhichReturnsFafikName_Fafik(){
        String expected = "Fafik";
        when(pet.getName()).thenReturn("Fafik");

        String actual = pet.getName();

        assertThat(expected).isEqualTo(actual);
    }
}
