package be.ucll.repository;

import be.ucll.model.Pony;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface PonyRepository extends JpaRepository<Pony, Long> {

    Pony findByName(String name);

    List<Pony> findByAgeAfter(int age);
}

//public class PonyRepository {
//
//    private List<Pony> ponies;
//
//    public PonyRepository() {
//        ponies = new ArrayList<>();
//        ponies.add(new Pony("Bella", 5));
//        ponies.add(new Pony("Luna", 7));
//        ponies.add(new Pony("Angel", 12));
//    }
//
//    public List<Pony> allPonies() {
//        return ponies;
//    }
//
//    public Pony addPony(Pony pony) {
//        ponies.add(pony);
//        return pony;
//    }
//
//    public void removePony(Pony pony) {
//        ponies.remove(pony);
//    }
//
//    public void resetRepositoryData() {
//        ponies = new ArrayList<>();
//        ponies.add(new Pony("Bella", 5));
//        ponies.add(new Pony("Luna", 7));
//        ponies.add(new Pony("Angel", 12));
//    }
//
//    public Pony findPonyByName(String name) {
//        return ponies.stream()
//                .filter(pony -> pony.getName().equals(name))
//                .findFirst()
//                .orElseThrow(() -> new IllegalArgumentException("Pony not found"));
//    }
//}
