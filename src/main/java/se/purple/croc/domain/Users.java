package se.purple.croc.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	private String email;
}
