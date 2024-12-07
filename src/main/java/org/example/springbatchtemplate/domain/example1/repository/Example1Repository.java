package org.example.springbatchtemplate.domain.example1.repository;

import java.util.Optional;

import org.example.springbatchtemplate.domain.example1.entity.Example1Entity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Example1Repository extends JpaRepository<Example1Entity, Long> {

	Optional<Example1Entity> findByNameAndValue(String name, int value);
}
