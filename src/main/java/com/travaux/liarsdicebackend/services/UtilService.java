package com.travaux.liarsdicebackend.services;

import org.springframework.stereotype.Service;

@Service
public class UtilService {
    public String generateGameId() {
        return new StringBuilder()
                .append((char) (Math.random() * 26 + 'a'))
                .append((char) (Math.random() * 26 + 'a'))
                .append((char) (Math.random() * 26 + 'a'))
                .append((char) (Math.random() * 26 + 'a'))
                .toString().toUpperCase();
    }
}
