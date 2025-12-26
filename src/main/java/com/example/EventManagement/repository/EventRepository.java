package com.example.EventManagement.repository;

import com.example.EventManagement.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Integer> {

    List<Event> findByStartDateAfter(LocalDate date);

    List<Event> findByStartDateLessThanEqualAndEndDateGreaterThanEqual(
            LocalDate start, LocalDate end
    );
}
