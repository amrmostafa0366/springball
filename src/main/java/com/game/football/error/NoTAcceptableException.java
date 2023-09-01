package com.game.football.error;

import org.springframework.http.HttpStatus;

public class NoTAcceptableException extends ApiBaseException {
    public NoTAcceptableException(String message) {
        super(message);
    }

    @Override
    HttpStatus getStatusCode() {
        return HttpStatus.NOT_ACCEPTABLE;
    }
}
