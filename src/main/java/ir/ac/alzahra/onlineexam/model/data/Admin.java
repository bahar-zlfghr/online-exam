package ir.ac.alzahra.onlineexam.model.data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author : Bahar Zolfaghari
 **/
@Entity
@DiscriminatorValue(value = "admin")
public class Admin extends User {

}
