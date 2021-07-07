package com.vaccine.tracker.repository;

import org.springframework.data.repository.CrudRepository;

import com.vaccine.tracker.dto.StatusCheckResponse;

public interface StatusRepository extends CrudRepository<StatusCheckResponse, Integer> {

}
