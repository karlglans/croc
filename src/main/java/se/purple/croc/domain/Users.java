package se.purple.croc.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@Entity
@EqualsAndHashCode
public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	private String email;

//	@ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
//	@CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
//	@Enumerated(EnumType.STRING)
//	private Set<Role> roles;

//	@ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
//	@CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
	@Enumerated(EnumType.STRING)
	private Role role;

	@JsonIgnore
	@ManyToMany
	@JoinTable(
		name = "grouped_users",
		joinColumns = @JoinColumn(name = "user_id"),
		inverseJoinColumns = @JoinColumn(name = "group_id"))
	private Set<UserGroup> group;

	@OneToMany(mappedBy="survey")
	private List<SurveyParticipant> surveys;

	public AuthProvider getProvider() {
		return AuthProvider.google;
	}
}
