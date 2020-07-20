package mil.dtic.ured.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

/*
 *  Best way to map the JPA and Hibernate ManyToMany relationship
 *  
 *  https://vladmihalcea.com/the-best-way-to-use-the-manytomany-annotation-with-jpa-and-hibernate/
 *  
 */


@Entity(name = "Tag")
@Table(name = "tag")
public class Tag {

	@Id
	@GeneratedValue
	private Long id;

	/*
	 * The Tag entity has a unique business key which is marked with the Hibernate-specific @NaturalId annotation. 
	 * When that’s the case, the unique business key is the best candidate for equality checks.
	 */
	@NaturalId
	private String name;

	/*
	 * The mappedBy attribute of the posts association in the Tag entity marks that, in this bidirectional 
	 * relationship, the Post entity owns the association. This is needed since only one side can own a 
	 * relationship, and changes are only propagated to the database from this particular side.
	 */
	@ManyToMany(mappedBy = "tags")
	/*
	 * When removing a Tag entity from a Post. Instead of deleting just one post_tag entry, Hibernate 
	 * removes all post_tag rows associated to the given post_id and reinserts the remaining ones back 
	 * afterward. This is not efficient at all because it’s extra work for the database, especially for 
	 * recreating indexes associated with the underlying Foreign Keys.
	 * For this reason, it’s not a good idea to use the java.util.List for @ManyToMany JPA associations.
	 * Instead of a List, we can use a Set.
	 */
	//private List<Post> posts = new ArrayList<>();
	private Set<Post> posts = new HashSet<>();

	public Tag() {}

	public Tag(String name) {
		this.name = name;
	}
	
	//Getters and setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

//	public List<Post> getPosts() {
//		return posts;
//	}
//
//	public void setPosts(List<Post> posts) {
//		this.posts = posts;
//	}
	
	public Set<Post> getPosts() {
		return posts;
	}

	public void setPosts(Set<Post> posts) {
		this.posts = posts;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Tag tag = (Tag) o;
		return Objects.equals(name, tag.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}
} 
