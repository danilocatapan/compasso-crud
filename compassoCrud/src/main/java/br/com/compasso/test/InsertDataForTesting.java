package br.com.compasso.test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import br.com.compasso.data.model.City;
import br.com.compasso.data.model.Client;
import br.com.compasso.repository.CityRepository;
import br.com.compasso.repository.ClientRepository;

@Component
public class InsertDataForTesting {

	@Autowired
	private CityRepository courseRepository;

	@Autowired
	private ClientRepository studentRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(InsertDataForTesting.class);

	@EventListener
	public void onApplicationEvent(ContextRefreshedEvent event) {

		 City[] cities = populateCities();

		try {
			populateClients(cities);
		} catch(Exception e) {
			logger.info("populateClients", e);
		}

	}
	
	private City[] populateCities() {
		List<City> cities = new ArrayList<>();

		City city = new City();
		city.setName("PARAGUAÇU PAULISTA");
		city.setState("SÃO PAULO");
		cities.add(city);
		courseRepository.save(city);

		city = new City();
		city.setName("MARÍLIA");
		city.setState("SÃO PAULO");
		cities.add(city);
		courseRepository.save(city);

		city = new City();
		city.setName("FLORIANOPÓLIS");
		city.setState("SANTA CATARINA");
		cities.add(city);
		courseRepository.save(city);

		City[] array = new City[cities.size()];
		return cities.toArray(array);
	}

	private void populateClients(City[] cities)  {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
		
		try {
			Client client = new Client();
			client.setFullName("DANILO");
			client.setGender("MASCULINO");
			client.setBirthDate(sdf.parse("18-03-1987"));
			client.setAge(32);
			client.setCity(cities[0]);
			studentRepository.save(client);
	
			client = new Client();
			client.setFullName("ANGELINA");
			client.setGender("FEMININO");
			client.setBirthDate(sdf.parse("19-01-2010"));
			client.setAge(10);
			client.setCity(cities[1]);
			studentRepository.save(client);
	
			client = new Client();
			client.setFullName("LUIGI");
			client.setGender("MASCULINO");
			client.setBirthDate(sdf.parse("12-08-2019"));
			client.setAge(0);
			client.setCity(cities[2]);
			studentRepository.save(client);
		} catch(Exception e) {
			logger.info("populateClients", e);
		}
	}


}
