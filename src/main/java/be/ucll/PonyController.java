package be.ucll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    @GetMapping("/{name}")
    public Pony getPonyByName(@PathVariable String name) {
        return ponyService.findPonyByName(name);
    }

//    @GetMapping
//    public Object allPonies(@RequestParam(value = "name", required = false) String name) {
//        if (name == null) {
//            return ponyService.allPonies();
//        } else {
//                return ponyService.findPonyByName(name);
//        }
//    }
}
