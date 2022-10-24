package com.example.exampleapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.exampleapp.model.ExampleModel;
import org.springframework.stereotype.Repository;

@Repository
public interface ExampleRepository extends JpaRepository<ExampleModel, Long> { }