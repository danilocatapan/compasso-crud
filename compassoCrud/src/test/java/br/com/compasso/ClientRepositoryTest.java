package br.com.compasso;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

import javax.persistence.EntityManager;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.compasso.data.model.City;
import br.com.compasso.data.model.Client;
import br.com.compasso.repository.ClientRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientRepositoryTest {
	
	@Autowired
	private ClientRepository repository;
	
	@Autowired
	EntityManager entityManager;
	
	@Rule
    public ExpectedException thrown = ExpectedException.none();
	
    @Test
    public void createShouldPersistData()  {
		Client client = new Client(1L, "Danilo Catapan", "Masculino", new Date(), 32 , new City(1L, "Marilia", "Sao Paulo"));
		this.repository.save(client);
		//
		assertThat(client.getId()).isNotNull();
        assertThat(client.getFullName()).isEqualTo("Danilo Catapan");
        assertThat(client.getGender()).isEqualTo("Masculino");
    }
    
    @Test
    public void deleteShouldRemoveData() {
        Client client = new Client(1L, "Danilo Catapan", "Masculino", new Date(), 32 , new City(1L, "Marilia", "Sao Paulo"));
        repository.save(client);
        repository.delete(client);
        ResponseEntity.ok().build();
        //
        assertThat(repository.findById(client.getId())).isEmpty();
    }
    
    @Test
    public void updateShouldChangeAndPersistData() {
        Client client = new Client(1L, "Danilo Catapan", "Masculino", new Date(), 32 , new City(1L, "Marilia", "Sao Paulo"));
        repository.save(client);
        
        client.setFullName("Catapan");
        client.setAge(30);
        repository.save(client);
        
        client = repository.findById(client.getId()).orElseThrow();
        assertThat(client.getFullName()).isEqualTo("Catapan");
        assertThat(client.getAge()).isEqualTo(30);
    }

}
