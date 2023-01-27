package com.travaux.liarsdicebackend.http.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Game already exists")
public class GameExistsException extends RuntimeException{ }