package com.itau.distributor.service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.itau.distributor.repository.ClientsDebtsRepository;

import lombok.SneakyThrows;

/**
 * @author luis
 */
@Service
public class DistributorService {

	@Autowired private ClientsDebtsRepository clientsDebtsRepository;
	@Autowired private JmsTemplate jmsTemplate;

	/**
	 * Method responsible for defining in how many pages the consumption of the data will be done and start the consumer threads.
	 * It uses the maximum number of threads available in the system, validating which ones are free and allocating them if necessary, thus ensuring that resources will not be extrapolated.
	 */
	@SneakyThrows
	public void doDistribution() {
		final long total = clientsDebtsRepository.count();
		final int amountPerPage = DistributorServiceThread.AMOUNT_PER_PAGE;
		final long totalOfPages = total / amountPerPage < 1 ? 1 : total / amountPerPage;
		final ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		for (int page = 0; page < totalOfPages; page++) {
			executorService.submit(new DistributorServiceThread(clientsDebtsRepository, jmsTemplate, page));
		}
	}
}