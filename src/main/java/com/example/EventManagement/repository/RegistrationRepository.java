package com.example.EventManagement.repository;

import com.example.EventManagement.entity.Registration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RegistrationRepository extends JpaRepository<Registration, Integer> {

    Optional<Registration> findByEventIdAndEmailId(Integer eventId, String emailId);

    List<Registration> findByEventId(Integer eventId);
}