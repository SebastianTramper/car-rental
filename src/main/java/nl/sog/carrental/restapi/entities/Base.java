package nl.sog.carrental.restapi.entities;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class Base {
    /**
     * The creationDate is set every time an instance is created that is using the Base class.
     */
    @CreationTimestamp
    private LocalDateTime creationDate;

    /**
     * The lastUpdated timestamp is set every time an instance of a class is changed.
     */
    @UpdateTimestamp
    private LocalDateTime lastUpdated;

    public Base() {
        // https://stackoverflow.com/questions/23068676/how-to-get-current-timestamp-in-string-format-in-java-yyyy-mm-dd-hh-mm-ss
       // this.creationDate = new Timestamp(System.currentTimeMillis());
    }

}
