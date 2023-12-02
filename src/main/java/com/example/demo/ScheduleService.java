package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    @Autowired
    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public Iterable<Schedule> listAll() {
        return scheduleRepository.findAll();
    }

    public void save(Schedule schedule) {
        scheduleRepository.save(schedule);
    }

    public Schedule get(Long id) {
        return scheduleRepository.findById(id).orElse(null);
    }

    public void delete(Long id) {
        scheduleRepository.deleteById(id);
    }

    public void update(Schedule schedule) {
        // Проверяем, существует ли расписание с таким ID в репозитории
        if (schedule.getId() != null && scheduleRepository.existsById(schedule.getId())) {
            scheduleRepository.save(schedule); // save используется и для обновления, если запись уже существует
        } else {
            throw new IllegalArgumentException("Schedule with ID " + schedule.getId() + " does not exist.");
        }
    }
    public boolean exists(Long id) {
        return scheduleRepository.existsById(id);
    }
}
