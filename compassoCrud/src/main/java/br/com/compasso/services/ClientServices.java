package br.com.compasso.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.compasso.converter.DozerConverter;
import br.com.compasso.data.model.Client;
import br.com.compasso.data.v1.vo.ClientVO;
import br.com.compasso.exception.ResourceNotFoundException;
import br.com.compasso.repository.ClientRepository;

@Service
public class ClientServices {

	private static final String MSG_RECORD_NOT_FOUND = "Não encontrou registro com este ID";

	@Autowired
	ClientRepository repository;

	public ClientVO createVO(ClientVO client) {
		return DozerConverter.parseObject(repository.save(DozerConverter.parseObject(client, Client.class)),
				ClientVO.class);
	}
	
	public Client save(Client client) {
		return repository.save(client);
	}

	public List<Client> findAll() {
		return repository.findAll();
	}
	
	public List<ClientVO> findAllVO() {
		return DozerConverter.parseListObjects(repository.findAll(), ClientVO.class);
	}

	public ClientVO findByIdVO(Long id) {
		var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(MSG_RECORD_NOT_FOUND));
		return DozerConverter.parseObject(entity, ClientVO.class);
	}
	
	public Client myFindById(Long id) {
		return repository.myFindById(id);
	}

	public ClientVO update(ClientVO client) {
		var entity = repository.findById(client.getKey())
				.orElseThrow(() -> new ResourceNotFoundException(MSG_RECORD_NOT_FOUND));

		entity.setFullName(client.getFullName());
		entity.setGender(client.getGender());
		entity.setBirthDate(client.getBirthDate());
		entity.setAge(client.getAge());
		entity.setCity(client.getCity());

		return DozerConverter.parseObject(repository.save(entity), ClientVO.class);
	}

	public void delete(Long id) {
		Client entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(MSG_RECORD_NOT_FOUND));
		repository.delete(entity);
	}
	
	public void deleteById(Long id) {
		repository.deleteById(id);
	}

	public List<ClientVO> findByName(String name) {
		return DozerConverter.parseListObjects(repository.findByName(name), ClientVO.class);
	}

}
