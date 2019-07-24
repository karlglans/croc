package se.purple.croc.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;

@Data
@Entity
@EqualsAndHashCode
public class UserGroup {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;

	@JsonIgnore
	@ManyToMany(mappedBy = "group")
	private Set<Users> users;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof UserGroup)) return false;
		UserGroup book = (UserGroup) o;
		return Objects.equals(getId(), book.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId());
	}

}
