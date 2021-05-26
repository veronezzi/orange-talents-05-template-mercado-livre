package br.com.zupacademy.guilherme.mercadolivre.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

@Entity
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Email(message = "E-mail invalido")
	@NotBlank
	private String email;
	@NotBlank
	@Length(min = 6)
	private String senha;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDateTime dataCadastro = LocalDateTime.now();

	public Cliente(@Email String email, @Length(min = 6) String senha) {
		this.email = email;
		this.senha = new BCryptPasswordEncoder().encode(senha);
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", email=" + email + ", senha=" + senha + ", dataCadastro=" + dataCadastro + "]";
	}

	public Cliente() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getEmail() {
		return email;
	}

}
