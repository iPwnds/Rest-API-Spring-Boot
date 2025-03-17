package be.ucll;

import be.ucll.model.Pony;
import be.ucll.repository.PonyRepository;
import be.ucll.service.PonyService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UnitTest {

    @Test
    public void givenFindPonyByName_whenPonyExists_thenItIsReturned() {
        PonyRepository repository = new PonyRepository();
        PonyService service = new PonyService(repository);
        Pony foundPony = service.findPonyByName("Bella");
        Assertions.assertEquals ("Bella", foundPony.getName());
    }

}
