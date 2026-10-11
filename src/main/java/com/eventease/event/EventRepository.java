package com.eventease.event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    @Query("select e from Event e where lower(e.name) like lower(concat('%', :term, '%')) or lower(e.category) like lower(concat('%', :term, '%'))")
    List<Event> search(@Param("term") String term);
}
