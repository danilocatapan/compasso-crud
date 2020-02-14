package br.com.compasso.converter.mocks;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.compasso.data.model.City;
import br.com.compasso.data.model.Client;
import br.com.compasso.data.v1.vo.ClientVO;

public class MockClient {


    public Client mockEntity() {
    	return mockEntity(0);
    }
    
    public ClientVO mockVO() {
    	return mockVO(0);
    }
    
    public List<Client> mockEntityList() {
        List<Client> clients = new ArrayList<Client>();
        for (int i = 0; i < 14; i++) {
            clients.add(mockEntity(i));
        }
        return clients;
    }

    public List<ClientVO> mockVOList() {
        List<ClientVO> clients = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            clients.add(mockVO(i));
        }
        return clients;
    }
    
    private Client mockEntity(Integer number) {
    	Client client = new Client();
    	client.setId(number.longValue());
        client.setFullName("Full Name Test" + number);
        client.setGender(((number % 2)==0) ? "Male" : "Female");
        client.setAge(32);
		client.setBirthDate(new Date());
		client.setCity(new City(1L, "Marilia", "Sao Paulo"));
        
        return client;
    }

    private ClientVO mockVO(Integer number) {
    	ClientVO client = new ClientVO();
    	client.setKey(number.longValue());
        client.setFullName("Full Name Test" + number);
        client.setGender(((number % 2)==0) ? "Male" : "Female");
        client.setAge(32);
		client.setBirthDate(new Date());
		client.setCity(new City(1L, "Marilia", "Sao Paulo"));
        return client;
    }

}
