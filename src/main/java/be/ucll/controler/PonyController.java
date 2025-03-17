package be.ucll.controler;

import be.ucll.service.PonyService;
import be.ucll.model.Pony;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pony")
public class PonyController {

    private PonyService ponyService;

    @Autowired
    public PonyController(PonyService ponyService) {
        this.ponyService = ponyService;
    }

    @GetMapping
    public List<Pony> allPonies() {
        return ponyService.allPonies();
    }

    @GetMapping("/{ponyName}")
    public Pony getPonyByName(@PathVariable(value = "ponyName") String name) {
        return ponyService.findPonyByName(name);
    }

    @GetMapping
    public Object allPonies(@RequestParam(value = "name", required = false) String name) {
        if (name == null) {
            return ponyService.allPonies();
        } else {
            return ponyService.findPonyByName(name);
        }
    }

    @PostMapping
    public Pony addPony(@RequestBody Pony pony) {
        return ponyService.addPony(pony);
    }

    @PutMapping("/{name}")
    public Pony updatePony(@PathVariable String name, @RequestBody Pony pony) {
        return ponyService.updatePony(name, pony);
    }

    @DeleteMapping("/{name}")
    public void removePony(@PathVariable String name) {
        ponyService.removePony(name);
    }

}