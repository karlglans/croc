package se.purple.croc.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class UserGroup {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;

	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
		name = "grouped_users",
		joinColumns = @JoinColumn(name = "group_id"),
		inverseJoinColumns = @JoinColumn(name = "user_id"))
	private List<Users> users;

}
