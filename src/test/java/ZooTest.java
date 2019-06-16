import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ZooTest {
    @Test
    public void getPetNames_mockedPetWhichReturnsTofikName_Tofik(){
        String expected = "Tofik";
        Pet pet = mock(Pet.class);
        when(pet.getName()).thenReturn("Tofik");
        Zoo zoo = new Zoo();
        zoo.giveHomelessPet(pet);

        String actual = zoo.getPetNames();

        assertThat(expected).isEqualTo(actual);
    }

    @Test
    public void getPetNames_MockedPetWhichReturnsTofikAndFafik_TofikFafik(){
        String expected = "Tofik, Fafik";
        Pet pet = mock(Pet.class);
        when(pet.getName()).thenReturn("Tofik").thenReturn("Fafik");
        Zoo zoo = new Zoo();
        zoo.giveHomelessPet(pet);
        zoo.giveHomelessPet(pet);

        String actual = zoo.getPetNames();

        assertThat(expected).isEqualTo(actual);
    }
}
