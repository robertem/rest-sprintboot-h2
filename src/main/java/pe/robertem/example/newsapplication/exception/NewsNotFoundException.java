package pe.robertem.example.newsapplication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NewsNotFoundException extends RuntimeException {

    public NewsNotFoundException(final Long id) {
        super("Could not find news with id " + id);
    }
}
