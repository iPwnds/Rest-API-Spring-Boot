package be.ucll.repository;

import be.ucll.model.Pony;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DbInitializer {

    private PonyRepository ponyRepository;

    @Autowired
    public DbInitializer(PonyRepository ponyRepository) {
        this.ponyRepository = ponyRepository;
    }

    @PostConstruct
    public void initialize() {
        ponyRepository.save(new Pony("Bella", 5));
        ponyRepository.save(new Pony("Luna", 7));
        ponyRepository.save(new Pony("Angel", 12));
    }
}