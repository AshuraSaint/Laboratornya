package com.example.demo;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@EnableJpaRepositories
public interface ScheduleRepository extends CrudRepository<Schedule, Long> {
}
