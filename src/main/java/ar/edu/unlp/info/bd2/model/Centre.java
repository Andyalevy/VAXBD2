package ar.edu.unlp.info.bd2.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Centre")
public class Centre {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    public Centre() {
    }
}
