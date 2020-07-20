package mil.dtic.ured.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/*
 *  Best way to map the JPA and Hibernate ManyToMany relationship
 *  
 *  https://vladmihalcea.com/the-best-way-to-use-the-manytomany-annotation-with-jpa-and-hibernate/
 *  
 */

@Entity(name = "Post")
@Table(name = "post")
public class Post {

	@Id
	@GeneratedValue
	private Long id;

	private String title;

	public Post() {}

	public Post(String title) {
		this.title = title;
	}

	/*
	 * 
	 * First of all, the tags association in the Post entity only defines the PERSIST and MERGE cascade types. 
	 * As explained in this article, the REMOVE entity state transition doesn’t make any sense for a 
	 * @ManyToMany JPA association since it could trigger a chain deletion that would ultimately wipe both 
	 * sides of the association.
	 */
	@ManyToMany(cascade = {
			CascadeType.PERSIST,
			CascadeType.MERGE
	})


	//private List<Tag> tags = new ArrayList<>();
	private Set<Tag> tags = new HashSet<>();
	
	/*
	 * Unidirectional @OneToMany with @JoinColumn:
	 * To fix the aforementioned extra join table issue, we just need to add the @JoinColumn in the mix.
	 */
//	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
//	@JoinColumn(name = "post_id")
//	private List<PostComment> comments = new ArrayList<>();
	/*
	 * Bidirectional @OneToMany:
	 * The best way to map a @OneToMany association is to rely on the @ManyToOne 
	 * side to propagate all entity state changes:
	 */
	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<PostComment> comments = new ArrayList<>();
	
	//Getters and setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

//	public List<Tag> getTags() {
//		return tags;
//	}
//
//	public void setTags(List<Tag> tags) {
//		this.tags = tags;
//	}
	
	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}
	
	public void addTag(Tag tag) {
		tags.add(tag);
		tag.getPosts().add(this);
	}

	public void removeTag(Tag tag) {
		tags.remove(tag);
		tag.getPosts().remove(this);
	}
	
	public List<PostComment> getComments() {
		return comments;
	}

	public void setComments(List<PostComment> comments) {
		this.comments = comments;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Post)) return false;
		return id != null && id.equals(((Post) o).getId());
	}

	@Override
	public int hashCode() {
		return 31;
	}

}