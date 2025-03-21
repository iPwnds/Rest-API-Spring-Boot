package be.ucll.controler;

import be.ucll.service.PonyService;
import be.ucll.model.Pony;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/pony")
public class PonyController {

    private PonyService ponyService;

    @Autowired
    public PonyController(PonyService ponyService) {
        this.ponyService = ponyService;
    }

//    @GetMapping
//    public List<Pony> allPonies() {
//        return ponyService.allPonies();
//    }

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
    public Pony addPony(@Valid @RequestBody Pony pony) {
        return ponyService.addPony(pony);
    }

    @PutMapping("/{name}")
    public Pony updatePony(@PathVariable String name, @Valid @RequestBody Pony pony) {
        return ponyService.updatePony(name, pony);
    }

    @DeleteMapping("/{name}")
    public void removePony(@PathVariable String name) {
        ponyService.removePony(name);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST )
    @ExceptionHandler({RuntimeException.class})
    public Map<String, String> handleRuntimeException(RuntimeException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("error", ex.getMessage());
        return errors;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST )
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : ex.getFieldErrors()) {
            String fieldName = error.getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        }
        return errors;
    }

}