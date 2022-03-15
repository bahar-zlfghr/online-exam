package ir.ac.alzahra.onlineexam.model.data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author : Bahar Zolfaghari
 **/
@Entity
public class ConfirmationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;

    @Column(name = "created_date")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date createdDate;

    private Boolean clickable;

    @OneToOne(targetEntity = EducationalUser.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "educational_user_id")
    private EducationalUser educationalUser;

    public Long getId() {
        return id;
    }

    public ConfirmationToken setId(Long id) {
        this.id = id;
        return this;
    }

    public String getToken() {
        return token;
    }

    public ConfirmationToken setToken(String token) {
        this.token = token;
        return this;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public ConfirmationToken setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public Boolean isClickable() {
        return clickable;
    }

    public ConfirmationToken setClickable(Boolean clickable) {
        this.clickable = clickable;
        return this;
    }

    public EducationalUser getEducationalUser() {
        return educationalUser;
    }

    public ConfirmationToken setEducationalUser(EducationalUser educationalUser) {
        this.educationalUser = educationalUser;
        return this;
    }
}
