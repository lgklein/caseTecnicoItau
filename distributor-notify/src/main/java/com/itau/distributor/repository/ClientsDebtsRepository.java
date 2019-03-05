package com.itau.distributor.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.itau.distributor.entity.ClientsDebtsEntity;

/**
 * Repository for access to the CLIENTS_DEBTS table, containing only the standard methods provided by Spring Boot.
 * @author luis
 */
@Repository
public interface ClientsDebtsRepository extends PagingAndSortingRepository<ClientsDebtsEntity, Integer> {
	//
}