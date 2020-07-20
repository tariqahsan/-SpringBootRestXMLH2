
package mil.dtic.ured.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/*
 * How to change the @OneToOne shared primary key column name with JPA and Hibernate
 * 
 * Just adding the JPA @OneToOne annotation in the child entity does not render a true 
 * one-to-one table relationship since a separate Foreign Key column will be used. 
 * Only when adding the @MapsId annotation will the JPA one-to-one association map to a 
 * real one-to-one table relationship.
 * 
 * The Post entity is the parent while the PostDetails is the child entity and its 
 * associated Primary Key is also a Foreign Key to the parent table Primary Key.
 * 
 * The Post entity is rather straightforward to map since it does not contain any association.
 * 
 * https://vladmihalcea.com/change-one-to-one-primary-key-column-jpa-hibernate/
 * 
 */

@Entity(name = "PostDetails")
@Table(name = "post_details")
public class PostDetails {
 
    @Id
    private Long id;
 
    @Column(name = "created_on")
    private Date createdOn;
 
    @Column(name = "created_by")
    private String createdBy;
 
    /*
     * Notice that we are using FetchType.LAZY explicitly since, by default, JPA 
     * uses DetchType.EAGER for @OneToOne and @ManyToOne associations, and that’s very bad for performance. 
     * 
     * Notice that the Primary Key column name is called post_id in the post_details table, and this is not 
     * very nice since the Primary Key column name is called id in the post table.
	 * @JoinColumn to the rescue
	 * To fix this issue, we just have to add a @JoinColumn annotation to the @OneToOne association in the 
	 * PostDetails entity:
     */
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "id")
    private Post post;
 
    public PostDetails() {}
 
    public PostDetails(String createdBy) {
        createdOn = new Date();
        this.createdBy = createdBy;
    }

    //Getters and setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}
    
}
