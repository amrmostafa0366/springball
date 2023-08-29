package com.game.football.error;

import org.springframework.http.HttpStatus;

public class NotFoundException extends ApiBaseException{

    public NotFoundException(String message) {
        super(message);
    }

    @Override
    HttpStatus getStatusCode() {
        return HttpStatus.NOT_FOUND;
    }
}
