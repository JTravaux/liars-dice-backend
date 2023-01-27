package com.travaux.liarsdicebackend.http.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Game not found")
public class GameNotFoundException extends RuntimeException{ }