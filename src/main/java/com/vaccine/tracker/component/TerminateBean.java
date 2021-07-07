package com.vaccine.tracker.component;

import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaccine.tracker.service.SaveRetrieve;
import com.vaccine.tracker.util.CentreUtil;

public class TerminateBean {
	
	@Autowired
	SaveRetrieve saveRetrieve;

	@PreDestroy
	public void onDestroy() throws Exception {
		System.out.println("Saving current status before exiting . . .");
		if(saveRetrieve.save(CentreUtil.getCenters(),CentreUtil.getCenters86(), CentreUtil.getCenters77())) {
			System.out.println("Status is saved successfully, will exit now");
		}else {
			System.out.println("Unale to save status, exiting normally");
		}
		
	}
}
