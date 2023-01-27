package com.travaux.liarsdicebackend.http.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Missing owner id")
public class MissingOwnerIdException extends RuntimeException {
}
