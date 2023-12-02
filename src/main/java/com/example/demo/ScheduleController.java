package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ScheduleController {
    private final ScheduleService scheduleService;

    @Autowired
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @GetMapping("/schedule")
    public String viewSchedule(Model model) {
        // Fetch a list of schedules from the service (if needed)
        Iterable<Schedule> schedules = scheduleService.listAll();

        // Create a new Schedule object (or fetch it from your service)
        Schedule schedule = new Schedule();

        model.addAttribute("schedules", schedules);
        model.addAttribute("schedule", schedule);

        return "schedule";
    }

    @PostMapping("/save")
    public String saveSchedule(@ModelAttribute Schedule schedule) {
        // Проверка на наличие ID - если ID есть, это обновление существующего элемента
        if (schedule.getId() != null && scheduleService.exists(schedule.getId())) {
            // Обновление существующего расписания
            scheduleService.update(schedule);
        } else {
            // Сохранение нового расписания
            scheduleService.save(schedule);
        }
        return "redirect:/schedule";
    }

    @GetMapping("/delete/{id}")
    public String deleteSchedule(@PathVariable Long id) {
        scheduleService.delete(id);
        return "redirect:/schedule";
    }

    @GetMapping("/edit/{id}")
    public String editSchedule(@PathVariable Long id, Model model) {
        Schedule schedule = scheduleService.get(id);
        model.addAttribute("schedule", schedule);
        return "schedule";
    }
}
