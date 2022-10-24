package com.example.exampleapp.controller;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.example.exampleapp.service.ExampleService;
import com.example.exampleapp.model.ExampleModelDTO;
import org.springframework.web.bind.annotation.*;
import com.example.exampleapp.model.ExampleModel;
import org.springframework.http.ResponseEntity;
import java.util.Optional;

@RestController
@RequestMapping("api/example")
public class ExampleController {

    private final ExampleService exampleService;

    public ExampleController(ExampleService exampleService) { this.exampleService = exampleService; }

    // GET
    @GetMapping
    public ResponseEntity<Iterable<ExampleModel>> exampleModels(){
        return ResponseEntity.ok(this.exampleService.findAll());
    }

    // GET BY ID
    @GetMapping("{id}")
    public ResponseEntity<ExampleModel> findById(@PathVariable long id) {
        Optional<ExampleModel> model = this.exampleService.findById(id);
        return model.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST
    @PostMapping
    public ResponseEntity<ExampleModel> create(@RequestBody ExampleModel exampleModel){
        ExampleModel result = this.exampleService.save(exampleModel);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/" +
                result.getId()).buildAndExpand(result).toUri()).body(result);
    }

    // PUT
    @PutMapping("{id}")
    public ResponseEntity<ExampleModel> updateById(@PathVariable long id, @RequestBody ExampleModelDTO exampleModelDTO) {
        Optional<ExampleModel> optionalModel = this.exampleService.update(exampleModelDTO, id);

        return optionalModel.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // DELETE
    @DeleteMapping("{id}")
    public ResponseEntity<ExampleModel> delete(@PathVariable long id){
        Optional<ExampleModel> updatedPlant = this.exampleService.findById(id);

        if(updatedPlant.isPresent()) {
            this.exampleService.deleteById(id);

            return ResponseEntity.noContent().build();
        }
        else { return ResponseEntity.notFound().build(); }
    }
//    // -----------------------------------------------------------------------------------------------------------------
//
//    // GET BY NAME
//    @GetMapping("name/{name}")
//    public ResponseEntity<List<Plant>> findByName(@PathVariable ("name") String name) {
//        return ResponseEntity.ok(this.plantService.findByName(name));
//    }
//
//    // GET BY NAME AND TIME TO WATER
//    @GetMapping("groupby/{name}/and/{timetowater}")
//    public ResponseEntity<List<Plant>> findByNameAndTimeToWater(
//            @PathVariable String name, @PathVariable Integer timetowater) {
//        return ResponseEntity.ok(this.plantService.findByNameAndTimeToWater(name, timetowater));
//    }
//
//    // GET BY NAME AND SIZE
//    @GetMapping("group/{name}/and/{size}")
//    public ResponseEntity<List<Plant>> findByNameAndSize(@PathVariable String name, @PathVariable String size) {
//        return ResponseEntity.ok(this.plantService.findByNameAndSize(name, size));
//    }
}
