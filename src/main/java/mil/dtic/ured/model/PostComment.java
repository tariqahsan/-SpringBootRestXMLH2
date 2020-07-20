package mil.dtic.ured.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/*
 * The best way to map a @OneToMany relationship with JPA and Hibernate
 * 
 * https://vladmihalcea.com/the-best-way-to-map-a-onetomany-association-with-jpa-and-hibernate/
 * 
 * Conclusion:
 * As you will see in a future article, bidirectional collections are way better 
 * than unidirectional ones because they rely on the @ManyToOne association, which 
 * is always efficient in terms of generated SQL statements.
 * But then, even if they are very convenient, you don’t always have to use collections. 
 * The @ManyToOne association is the most natural and also efficient way of mapping a 
 * one-to-many database relationship.
 */

@Entity(name = "PostComment")
@Table(name = "post_comment")
public class PostComment {

	@Id
	@GeneratedValue
	private Long id;

	private String review;

	/*
	 * a unidirectional @OneToMany association
     * a bidirectional @OneToMany association
	 * The bidirectional association requires the child entity mapping to provide 
	 * a @ManyToOne annotation, which is responsible for controlling the association.
	 * 
	 * Bidirectional @OneToMany:
	 * The best way to map a @OneToMany association is to rely on the @ManyToOne 
	 * side to propagate all entity state changes:
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "POST_ID")
	private Post post;

	public PostComment() {}

	public PostComment(Long id, String review) {
		super();
		this.id = id;
		this.review = review;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof PostComment )) return false;
		return id != null && id.equals(((PostComment) o).getId());
	}

	@Override
	public int hashCode() {
		return 31;
	}

}