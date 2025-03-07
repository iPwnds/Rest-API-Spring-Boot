package be.ucll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PonyService {

    private PonyRepository ponyRepository;

    @Autowired
    public PonyService(PonyRepository ponyRepository) {
        this.ponyRepository = ponyRepository;
    }

    public List<Pony> allPonies() {
        return ponyRepository.allPonies();
    }

    public Pony findPonyByName(String name) {
        for (Pony pony : allPonies()) {
            if (pony.getName().equals(name)) {
                return pony;
            }
        }

        return null;
    }
}
