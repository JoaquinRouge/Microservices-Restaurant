package com.restarurant.microservice_table.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restarurant.microservice_table.model.Table;

@Repository
public interface ITableRepository extends JpaRepository<Table, Long>{
	Optional<Table> findByUser(String user);
}
