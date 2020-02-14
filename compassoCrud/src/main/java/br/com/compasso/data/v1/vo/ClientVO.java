package br.com.compasso.data.v1.vo;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;

import br.com.compasso.data.model.City;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonPropertyOrder({ "id", "nome completo", "gênero", "aniversário", "idade", "cidade" })
public class ClientVO implements Serializable {
 
	private static final long serialVersionUID = 1L;

	@Mapping("id")
	@JsonProperty("id")
	private Long key;
	
	@JsonProperty("nome completo")
	private String fullName;
	
	@JsonProperty("gênero")
	private String gender;
	
	@JsonProperty("aniversário")
	private Date birthDate;
	
	@JsonProperty("idade")
	private int age;
	
	@JsonProperty("cidade")
	private City city;
	
	
}