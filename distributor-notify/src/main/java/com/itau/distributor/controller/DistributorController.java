package com.itau.distributor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.itau.distributor.service.DistributorService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author luis
 */
@Slf4j
@RestController
public class DistributorController {

	@Autowired private DistributorService distributorService;

	/**
	 * Method responsible for, as soon as triggered from webservice, start the process of
	 * distribution of the sending of the messages of debts in threads.
	 * @return Just a warning that running on Threads started.
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/distributor/start")
	@ResponseBody
	public ResponseEntity<String> startDistribution() {
		try {
			distributorService.doDistribution();
		} catch (final Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return new ResponseEntity<>("Started", HttpStatus.OK);
	}
}