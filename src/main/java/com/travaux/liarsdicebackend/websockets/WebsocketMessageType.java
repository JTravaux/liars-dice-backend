package com.travaux.liarsdicebackend.websockets;

public enum WebsocketMessageType {
    GAME_SETTINGS,
    GAME_STATUS,
    GAME_START,
    GAME_END,
    GAME_PLAYER_JOIN,
    GAME_PLAYER_READY,
    GAME_PLAYER_BID,
    GAME_PLAYER_CHALLENGE,
    GAME_PLAYER_ELIMINATED,
    GAME_PLAYER_CHAT
}
