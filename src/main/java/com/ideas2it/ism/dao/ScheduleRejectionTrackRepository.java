package com.ideas2it.ism.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ideas2it.ism.entity.ScheduleRejectionTrack;

@Repository
public interface ScheduleRejectionTrackRepository extends JpaRepository<ScheduleRejectionTrack, Long> {

}
