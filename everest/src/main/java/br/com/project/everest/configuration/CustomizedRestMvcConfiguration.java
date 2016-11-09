package br.com.project.everest.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;

import br.com.project.everest.domain.City;
import br.com.project.everest.domain.State;

@Configuration
public class CustomizedRestMvcConfiguration extends RepositoryRestMvcConfiguration {

	@Override
	public RepositoryRestConfiguration config() {
		RepositoryRestConfiguration config = super.config();
		config.setBasePath("/api");
		config.exposeIdsFor(State.class, City.class);
		return config;
	}

}