package com.example.exampleapp.service;

import com.example.exampleapp.model.ExampleModelDTO;
import org.springframework.transaction.annotation.Transactional;
import com.example.exampleapp.repository.ExampleRepository;
import org.springframework.web.bind.annotation.PutMapping;
import com.example.exampleapp.model.ExampleModel;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.List;

@Service
public class ExampleService {

    private final ExampleRepository exampleRepository;

    public ExampleService(ExampleRepository exampleRepository) { this.exampleRepository = exampleRepository; }

    // GET ALL
    public List<ExampleModel> findAll() {
        return exampleRepository.findAll();
    }

    // GET BY ID
    public Optional<ExampleModel> findById(long id) {
        return exampleRepository.findById(id);
    }

    // POST
    @Transactional
    public ExampleModel save(ExampleModel exampleModel) { return exampleRepository.save(exampleModel); }

    // PUT
    @PutMapping
    public Optional<ExampleModel> update(ExampleModelDTO exampleModelDTO, long id) {
        Optional<ExampleModel> optionalModel = this.findById(id);

        if (optionalModel.isPresent()) {
            ExampleModel target = optionalModel.get();
            target.setFirstName(exampleModelDTO.firstName());
            target.setLastName(exampleModelDTO.lastName());
            target.setAge(exampleModelDTO.age());
            return Optional.of(exampleRepository.save(target));
        }
        return Optional.empty();
    }

    // DELETE
    public void deleteById(long id) {
        exampleRepository.deleteById(id);
    }
}
