package br.com.compasso;

import static java.util.Arrays.asList;
import static org.springframework.http.HttpMethod.DELETE;

import java.util.Date;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.compasso.data.model.City;
import br.com.compasso.data.model.Client;
import br.com.compasso.repository.ClientRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ClienteControllerTest {
	
    @Autowired
    private TestRestTemplate restTemplate;
    
    @LocalServerPort
    private int port;
    
    @MockBean
    private ClientRepository clientRepository;
    
    @Before
    public void setup() {
    	Client client = new Client(1L, "Danilo Catapan", "Masculino", new Date(), 32 , new City(1L, "Marilia", "Sao Paulo"));
        BDDMockito.when(clientRepository.myFindById(client.getId())).thenReturn(client);
    }
    
    @Test
    public void listClientsWhenEndPointIsOkShouldReturnStatusCode200() {
    	List<Client> client = asList(new Client(1L, "Danilo Catapan", "Masculino", new Date(), 32 , new City(1L, "Marilia", "Sao Paulo")),
										new Client(2L, "Danilo Catapan2", "Masculino", new Date(), 32 , new City(1L, "Marilia", "Sao Paulo")));
    	BDDMockito.when(clientRepository.findAll()).thenReturn(client);
	    ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:" + port + "/api/v1/client/findAll/", String.class);
	    Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }
    
    @Test
    public void getStudentsByIdWhenIdOkShouldReturnStatusCode200() {
        ResponseEntity<Client> response = restTemplate.getForEntity("http://localhost:" + port + "/api/v1/client/myFindById/{id}", Client.class, 1L);
        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }
    
    @Test
    public void getStudentsByIdWhenClientDoesNotExistShouldReturnIsNull() {
        ResponseEntity<Client> response = restTemplate.getForEntity("http://localhost:" + port + "/api/v1/client/myFindById/{id}", Client.class, 99L);
        Assertions.assertThat(response.getBody()).isNull();
    }
    
    @Test
    public void deleteWhenClientExistsShouldReturnStatusCode200() {
        BDDMockito.doNothing().when(clientRepository).deleteById(1L);
        ResponseEntity<String> exchange = restTemplate.exchange("/api/v1/client/deleteById/{id}", DELETE, null, String.class, 1L);
        Assertions.assertThat(exchange.getStatusCodeValue()).isEqualTo(200);
    }
    
}
