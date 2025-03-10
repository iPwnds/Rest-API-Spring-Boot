package be.ucll.service;

import be.ucll.repository.PonyRepository;
import be.ucll.model.Pony;
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

    public Pony addPony(Pony pony) {
        Pony existingPony = findPonyByName(pony.getName());

        if (existingPony != null) {
            throw new RuntimeException("Pony already exists");
        }

        return ponyRepository.addPony(pony);
    }

    public Pony updatePony(String name, Pony newInformation) {
        Pony pony = findPonyByName(name);

        if (pony == null) {
            throw new RuntimeException("Pony not found");
        }

        pony.setAge(newInformation.getAge());
        pony.setName(newInformation.getName());

        return pony;
    }

    public Pony removePony(String name) {
        Pony pony = findPonyByName(name);

        if (pony == null) {
            throw new RuntimeException("Pony not found");
        }

        ponyRepository.removePony(pony);
        return pony;
    }
}
