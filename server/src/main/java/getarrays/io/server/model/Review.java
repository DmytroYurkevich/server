package getarrays.io.server.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Review {

    @Id
    private Long id;

    private String body;

    public Review(String body) {
        this.body = body;
    }
}
