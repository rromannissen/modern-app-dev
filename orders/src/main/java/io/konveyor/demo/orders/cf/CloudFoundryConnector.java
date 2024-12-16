package io.konveyor.demo.orders.cf;

import java.util.List;

import org.cloudfoundry.operations.DefaultCloudFoundryOperations;
import org.cloudfoundry.operations.applications.ApplicationSummary;
import org.cloudfoundry.reactor.client.ReactorCloudFoundryClient;
import org.springframework.beans.factory.annotation.Autowired;

import reactor.core.publisher.Flux;

public class CloudFoundryConnector {
	
	
	@Autowired
	ReactorCloudFoundryClient cloudFoundryClient;
	
	List<ApplicationSummary> getApplications(String orgName, String spacename){
		
		DefaultCloudFoundryOperations cloudFoundryOperations = DefaultCloudFoundryOperations.builder()
                .cloudFoundryClient(cloudFoundryClient)
                .organization(orgName)
                .space(spacename)
                .build();
		
		Flux<ApplicationSummary> applications = cloudFoundryOperations.applications()
                .list();	
		
		return applications.collectList().block();
	}

}
