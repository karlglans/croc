package se.purple.croc.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@Entity
public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	private String email;


//	@ManyToMany(fetch = FetchType.EAGER)
//	@JoinTable(
//			name = "user_roles",
//			joinColumns = @JoinColumn(name = "user_id"),
//			inverseJoinColumns = @JoinColumn(name = "role_id"))
//	private Set<Roles> roles;

	@ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
	@CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
	@Enumerated(EnumType.STRING)
	private Set<Role> roles;

	@JsonIgnore
	@ManyToMany
	@JoinTable(
		name = "grouped_users",
		joinColumns = @JoinColumn(name = "user_id"),
		inverseJoinColumns = @JoinColumn(name = "group_id"))
	private List<UserGroup> group;

	@OneToMany(mappedBy="survey")
	private List<SurveyParticipant> surveys;
}
