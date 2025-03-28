package be.ucll.service;

import be.ucll.repository.PonyRepository;
import be.ucll.model.Pony;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PonyService {

    private PonyRepository ponyRepository;

    public List<Pony> allPonies() {
        return ponyRepository.findAll();
    }

    public Pony findPonyByName(String name) {
        return ponyRepository.findByName(name);
    }

    public Pony addPony(Pony pony) {
        return ponyRepository.save(pony);
    }

    public Pony updatePony(String name, Pony newInformation) {
        Pony pony = ponyRepository.findByName(name);
        pony.updateNameAndAge(newInformation.getName(), newInformation.getAge());
        return ponyRepository.save(pony);
    }

    public void removePony(String name) {
        Pony pony = findPonyByName(name);
        ponyRepository.delete(pony);
    }

//    @Autowired
//    public PonyService(PonyRepository ponyRepository) {
//        this.ponyRepository = ponyRepository;
//    }
//
//    public List<Pony> allPonies() {
//        return ponyRepository.allPonies();
//    }
//
//    public Pony addPony(Pony pony) {
//        return ponyRepository.addPony(pony);
//    }
//
//    public Pony updatePony(String name, Pony newInformation) {
//        Pony pony = findPonyByName(name);
//        pony.updateNameAndAge(newInformation.getName(), newInformation.getAge());
//        return pony;
//    }
//
//    public void removePony(String name) {
//        Pony pony = findPonyByName(name);
//        ponyRepository.removePony(pony);
//    }
//
//    public Pony findPonyByName(String name) {
//        return ponyRepository.allPonies().stream()
//                .filter(pony -> pony.getName().equals(name))
//                .findFirst()
//                .orElseThrow(() -> new IllegalArgumentException("Pony not found"));
//    }
}