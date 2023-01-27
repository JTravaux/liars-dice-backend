package com.travaux.liarsdicebackend.http.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Missing room id")
public class MissingRoomIdException extends RuntimeException {
}
