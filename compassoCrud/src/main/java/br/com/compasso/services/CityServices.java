package br.com.compasso.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.compasso.converter.DozerConverter;
import br.com.compasso.data.model.City;
import br.com.compasso.data.v1.vo.CityVO;
import br.com.compasso.exception.ResourceNotFoundException;
import br.com.compasso.repository.CityRepository;

@Service
public class CityServices {
	
	private static final String MSG_RECORD_NOT_FOUND = "Não encontrou registro com este ID";

	@Autowired
	CityRepository repository;

	public CityVO create(CityVO city) {
		return DozerConverter.parseObject(repository.save(DozerConverter.parseObject(city, City.class)), CityVO.class);
	}

	public List<CityVO> findAll() {
		return DozerConverter.parseListObjects(repository.findAll(), CityVO.class);
	}

	public CityVO findById(Long id) {
		var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(MSG_RECORD_NOT_FOUND));
		return DozerConverter.parseObject(entity, CityVO.class);
	}

	public CityVO update(CityVO city) {
		var entity = repository.findById(city.getKey())
				.orElseThrow(() -> new ResourceNotFoundException(MSG_RECORD_NOT_FOUND));

		entity.setName(city.getName());
		entity.setState(city.getState());

		return DozerConverter.parseObject(repository.save(entity), CityVO.class);
	}

	public void delete(Long id) {
		City entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(MSG_RECORD_NOT_FOUND));
		repository.delete(entity);
	}

	public CityVO findByName(String name) {
		var entity = repository.findByName(name);
		return DozerConverter.parseObject(entity, CityVO.class);
	}
	
	public List<CityVO> findByState(String state) {
		return DozerConverter.parseListObjects(repository.findByState(state), CityVO.class);
	}

}
