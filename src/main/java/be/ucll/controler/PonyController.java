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

    @GetMapping("/all")
    public List<Pony> allPonies() {
        return ponyService.allPonies();
    }

    @GetMapping("/{name}")
    public Pony getPonyByName(@PathVariable String name) {
        return ponyService.findPonyByName(name);
    }

    @GetMapping
    public Pony getPonyByNameRequestParam(@RequestParam(name = "name", required = true) String name) {
        return ponyService.findPonyByName(name);
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
    public ResponseEntity<Void> removePony(@PathVariable String name) {
        ponyService.removePony(name);
        return ResponseEntity.noContent().build(); // HTTP 204 No Content
    }
}
