package com.marcelomnc.score10pinbowling.common;

import com.marcelomnc.score10pinbowling.dto.FrameDTO;
import com.marcelomnc.score10pinbowling.dto.GameDTO;
import com.marcelomnc.score10pinbowling.dto.PinFallDTO;
import com.marcelomnc.score10pinbowling.dto.PlayerChanceDTO;

import java.util.HashMap;
import java.util.Map;

public class GamesMocker {

    public static Map<String, GameDTO> wrapGame(GameDTO gameDTO) {
        Map<String, GameDTO> toRet = new HashMap<>();
        toRet.put(gameDTO.getPlayerName(), gameDTO);
        return toRet;
    }

    public static GameDTO mockLackOfChancesGame() {
        String playerName = "Lack Of Chances";
        GameDTO gameDTO = new GameDTO(playerName);
        //Add not enough chances to complete 10 frames
        PlayerChanceDTO playerChanceDTO = null;
        for (int i = 0; i < 3; i++) {
            playerChanceDTO = new PlayerChanceDTO();
            playerChanceDTO.setPlayerName(playerName);
            playerChanceDTO.setKnockedDownPins(3);
            gameDTO.getPlayerChanceDTOs().add(playerChanceDTO);
        }
        return gameDTO;
    }

    public static GameDTO mockChancesExcessGame() {
        String playerName = "Chances Excess";
        GameDTO gameDTO = new GameDTO(playerName);
        //Add more than enough chances to complete 10 frames
        //Perfect game is 12 chances total, no more
        PlayerChanceDTO playerChanceDTO = null;
        for (int i = 0; i < 13; i++) {
            playerChanceDTO = new PlayerChanceDTO();
            playerChanceDTO.setPlayerName(playerName);
            playerChanceDTO.setKnockedDownPins(10);
            gameDTO.getPlayerChanceDTOs().add(playerChanceDTO);
        }
        return gameDTO;
    }

    public static GameDTO mockAllZeroesGame(boolean mockPinFalls) {
        String playerName = "All Zeroes";
        GameDTO gameDTO = new GameDTO(playerName);
        //All zeroes game needs 20 chances
        PlayerChanceDTO playerChanceDTO = null;
        for (int i = 0; i < 20; i++) {
            playerChanceDTO = new PlayerChanceDTO();
            playerChanceDTO.setPlayerName(playerName);
            playerChanceDTO.setKnockedDownPins(0);
            gameDTO.getPlayerChanceDTOs().add(playerChanceDTO);
        }

        if (mockPinFalls) {
            FrameDTO frameDTO = null;
            PinFallDTO pinFallDTO = null;
            for (int i = 0; i < 10; i++) {
                frameDTO = new FrameDTO();
                pinFallDTO = new PinFallDTO(false, 0);
                frameDTO.getPinFalls().add(pinFallDTO);
                gameDTO.getFrameDTOS().add(frameDTO);
                pinFallDTO = new PinFallDTO(false, 0);
                frameDTO.getPinFalls().add(pinFallDTO);
            }
        }

        return gameDTO;
    }

    public static GameDTO mockAllFoulsGame(boolean mockPinFalls) {
        String playerName = "All Fouls";
        GameDTO gameDTO = new GameDTO(playerName);
        //All fouls game needs 20 chances
        PlayerChanceDTO playerChanceDTO = null;
        for (int i = 0; i < 20; i++) {
            playerChanceDTO = new PlayerChanceDTO();
            playerChanceDTO.setPlayerName(playerName);
            playerChanceDTO.setKnockedDownPins(0);
            //Foul !
            playerChanceDTO.setFoul(true);
            gameDTO.getPlayerChanceDTOs().add(playerChanceDTO);
        }

        if (mockPinFalls) {
            FrameDTO frameDTO = null;
            PinFallDTO pinFallDTO = null;
            for (int i = 0; i < 10; i++) {
                frameDTO = new FrameDTO();
                pinFallDTO = new PinFallDTO(true, 0);
                frameDTO.getPinFalls().add(pinFallDTO);
                pinFallDTO = new PinFallDTO(true, 0);
                frameDTO.getPinFalls().add(pinFallDTO);
                gameDTO.getFrameDTOS().add(frameDTO);
            }
        }

        return gameDTO;
    }

    public static GameDTO mockPerfectGame(boolean mockPinFalls) {
        String playerName = "Perfect";
        GameDTO gameDTO = new GameDTO(playerName);
        //Add perfect game, 12 chances of 10 pins
        PlayerChanceDTO playerChanceDTO = null;
        for (int i = 0; i < 12; i++) {
            playerChanceDTO = new PlayerChanceDTO();
            playerChanceDTO.setPlayerName(playerName);
            playerChanceDTO.setKnockedDownPins(10);
            gameDTO.getPlayerChanceDTOs().add(playerChanceDTO);
        }

        if (mockPinFalls) {
            FrameDTO frameDTO = null;
            PinFallDTO pinFallDTO = null;
            for (int i = 0; i < 9; i++) {
                frameDTO = new FrameDTO();
                pinFallDTO = new PinFallDTO(false, 10);
                frameDTO.getPinFalls().add(pinFallDTO);
                gameDTO.getFrameDTOS().add(frameDTO);
            }

            //Frame 10
            frameDTO = new FrameDTO();
            pinFallDTO = new PinFallDTO(false, 10);
            frameDTO.getPinFalls().add(pinFallDTO);
            pinFallDTO = new PinFallDTO(false, 10);
            frameDTO.getPinFalls().add(pinFallDTO);
            //Extra chance on frame 10
            pinFallDTO = new PinFallDTO(false, 10);
            frameDTO.getPinFalls().add(pinFallDTO);
            gameDTO.getFrameDTOS().add(frameDTO);
        }

        return gameDTO;
    }

    public static GameDTO mockJeffGame(boolean mockPinFalls) {
        String playerName = "Jeff";
        GameDTO gameDTO = new GameDTO(playerName);
        PlayerChanceDTO playerChanceDTO = null;
        FrameDTO frameDTO = null;
        PinFallDTO pinFallDTO = null;

        //Frame 1
        playerChanceDTO = new PlayerChanceDTO();
        playerChanceDTO.setPlayerName(playerName);
        playerChanceDTO.setKnockedDownPins(10);
        gameDTO.getPlayerChanceDTOs().add(playerChanceDTO);

        if (mockPinFalls) {
            frameDTO = new FrameDTO();
            pinFallDTO = new PinFallDTO(false, 10);
            frameDTO.getPinFalls().add(pinFallDTO);
            gameDTO.getFrameDTOS().add(frameDTO);
        }

        //Frame 2
        playerChanceDTO = new PlayerChanceDTO();
        playerChanceDTO.setPlayerName(playerName);
        playerChanceDTO.setKnockedDownPins(7);
        gameDTO.getPlayerChanceDTOs().add(playerChanceDTO);

        playerChanceDTO = new PlayerChanceDTO();
        playerChanceDTO.setPlayerName(playerName);
        playerChanceDTO.setKnockedDownPins(3);
        gameDTO.getPlayerChanceDTOs().add(playerChanceDTO);

        if (mockPinFalls) {
            frameDTO = new FrameDTO();
            pinFallDTO = new PinFallDTO(false, 7);
            frameDTO.getPinFalls().add(pinFallDTO);
            pinFallDTO = new PinFallDTO(false, 3);
            frameDTO.getPinFalls().add(pinFallDTO);
            gameDTO.getFrameDTOS().add(frameDTO);
        }

        //Frame 3
        playerChanceDTO = new PlayerChanceDTO();
        playerChanceDTO.setPlayerName(playerName);
        playerChanceDTO.setKnockedDownPins(9);
        gameDTO.getPlayerChanceDTOs().add(playerChanceDTO);

        playerChanceDTO = new PlayerChanceDTO();
        playerChanceDTO.setPlayerName(playerName);
        playerChanceDTO.setKnockedDownPins(0);
        gameDTO.getPlayerChanceDTOs().add(playerChanceDTO);

        if (mockPinFalls) {
            frameDTO = new FrameDTO();
            pinFallDTO = new PinFallDTO(false, 9);
            frameDTO.getPinFalls().add(pinFallDTO);
            pinFallDTO = new PinFallDTO(false, 0);
            frameDTO.getPinFalls().add(pinFallDTO);
            gameDTO.getFrameDTOS().add(frameDTO);
        }

        //Frame 4
        playerChanceDTO = new PlayerChanceDTO();
        playerChanceDTO.setPlayerName(playerName);
        playerChanceDTO.setKnockedDownPins(10);
        gameDTO.getPlayerChanceDTOs().add(playerChanceDTO);

        if (mockPinFalls) {
            frameDTO = new FrameDTO();
            pinFallDTO = new PinFallDTO(false, 10);
            frameDTO.getPinFalls().add(pinFallDTO);
            gameDTO.getFrameDTOS().add(frameDTO);
        }

        //Frame 5
        playerChanceDTO = new PlayerChanceDTO();
        playerChanceDTO.setPlayerName(playerName);
        playerChanceDTO.setKnockedDownPins(0);
        gameDTO.getPlayerChanceDTOs().add(playerChanceDTO);

        playerChanceDTO = new PlayerChanceDTO();
        playerChanceDTO.setPlayerName(playerName);
        playerChanceDTO.setKnockedDownPins(8);
        gameDTO.getPlayerChanceDTOs().add(playerChanceDTO);

        if (mockPinFalls) {
            frameDTO = new FrameDTO();
            pinFallDTO = new PinFallDTO(false, 0);
            frameDTO.getPinFalls().add(pinFallDTO);
            pinFallDTO = new PinFallDTO(false, 8);
            frameDTO.getPinFalls().add(pinFallDTO);
            gameDTO.getFrameDTOS().add(frameDTO);
        }

        //Frame 6
        playerChanceDTO = new PlayerChanceDTO();
        playerChanceDTO.setPlayerName(playerName);
        playerChanceDTO.setKnockedDownPins(8);
        gameDTO.getPlayerChanceDTOs().add(playerChanceDTO);

        playerChanceDTO = new PlayerChanceDTO();
        playerChanceDTO.setPlayerName(playerName);
        playerChanceDTO.setKnockedDownPins(2);
        gameDTO.getPlayerChanceDTOs().add(playerChanceDTO);

        if (mockPinFalls) {
            frameDTO = new FrameDTO();
            pinFallDTO = new PinFallDTO(false, 8);
            frameDTO.getPinFalls().add(pinFallDTO);
            pinFallDTO = new PinFallDTO(false, 2);
            frameDTO.getPinFalls().add(pinFallDTO);
            gameDTO.getFrameDTOS().add(frameDTO);
        }

        //Frame 7
        playerChanceDTO = new PlayerChanceDTO();
        playerChanceDTO.setPlayerName(playerName);
        playerChanceDTO.setKnockedDownPins(0);
        playerChanceDTO.setFoul(true);
        gameDTO.getPlayerChanceDTOs().add(playerChanceDTO);

        playerChanceDTO = new PlayerChanceDTO();
        playerChanceDTO.setPlayerName(playerName);
        playerChanceDTO.setKnockedDownPins(6);
        gameDTO.getPlayerChanceDTOs().add(playerChanceDTO);

        if (mockPinFalls) {
            frameDTO = new FrameDTO();
            pinFallDTO = new PinFallDTO(true, 0);
            frameDTO.getPinFalls().add(pinFallDTO);
            pinFallDTO = new PinFallDTO(false, 6);
            frameDTO.getPinFalls().add(pinFallDTO);
            gameDTO.getFrameDTOS().add(frameDTO);
        }

        //Frame 8
        playerChanceDTO = new PlayerChanceDTO();
        playerChanceDTO.setPlayerName(playerName);
        playerChanceDTO.setKnockedDownPins(10);
        gameDTO.getPlayerChanceDTOs().add(playerChanceDTO);

        if (mockPinFalls) {
            frameDTO = new FrameDTO();
            pinFallDTO = new PinFallDTO(false, 10);
            frameDTO.getPinFalls().add(pinFallDTO);
            gameDTO.getFrameDTOS().add(frameDTO);
        }

        //Frame 9
        playerChanceDTO = new PlayerChanceDTO();
        playerChanceDTO.setPlayerName(playerName);
        playerChanceDTO.setKnockedDownPins(10);
        gameDTO.getPlayerChanceDTOs().add(playerChanceDTO);

        if (mockPinFalls) {
            frameDTO = new FrameDTO();
            pinFallDTO = new PinFallDTO(false, 10);
            frameDTO.getPinFalls().add(pinFallDTO);
            gameDTO.getFrameDTOS().add(frameDTO);
        }

        //Frame 10
        playerChanceDTO = new PlayerChanceDTO();
        playerChanceDTO.setPlayerName(playerName);
        playerChanceDTO.setKnockedDownPins(10);
        gameDTO.getPlayerChanceDTOs().add(playerChanceDTO);

        playerChanceDTO = new PlayerChanceDTO();
        playerChanceDTO.setPlayerName(playerName);
        playerChanceDTO.setKnockedDownPins(8);
        gameDTO.getPlayerChanceDTOs().add(playerChanceDTO);

        playerChanceDTO = new PlayerChanceDTO();
        playerChanceDTO.setPlayerName(playerName);
        playerChanceDTO.setKnockedDownPins(1);
        gameDTO.getPlayerChanceDTOs().add(playerChanceDTO);

        if (mockPinFalls) {
            frameDTO = new FrameDTO();
            pinFallDTO = new PinFallDTO(false, 10);
            frameDTO.getPinFalls().add(pinFallDTO);
            pinFallDTO = new PinFallDTO(false, 8);
            frameDTO.getPinFalls().add(pinFallDTO);
            pinFallDTO = new PinFallDTO(false, 1);
            frameDTO.getPinFalls().add(pinFallDTO);
            gameDTO.getFrameDTOS().add(frameDTO);
        }

        return gameDTO;
    }

    public static GameDTO mockJohnGame(boolean mockPinFalls) {
        String playerName = "Jeff";
        GameDTO gameDTO = new GameDTO(playerName);
        PlayerChanceDTO playerChanceDTO = null;
        FrameDTO frameDTO = null;
        PinFallDTO pinFallDTO = null;

        //Frame 1
        playerChanceDTO = new PlayerChanceDTO();
        playerChanceDTO.setPlayerName(playerName);
        playerChanceDTO.setKnockedDownPins(3);
        gameDTO.getPlayerChanceDTOs().add(playerChanceDTO);

        playerChanceDTO = new PlayerChanceDTO();
        playerChanceDTO.setPlayerName(playerName);
        playerChanceDTO.setKnockedDownPins(7);
        gameDTO.getPlayerChanceDTOs().add(playerChanceDTO);

        if (mockPinFalls) {
            frameDTO = new FrameDTO();
            pinFallDTO = new PinFallDTO(false, 3);
            frameDTO.getPinFalls().add(pinFallDTO);
            pinFallDTO = new PinFallDTO(false, 7);
            frameDTO.getPinFalls().add(pinFallDTO);
            gameDTO.getFrameDTOS().add(frameDTO);
        }

        //Frame 2
        playerChanceDTO = new PlayerChanceDTO();
        playerChanceDTO.setPlayerName(playerName);
        playerChanceDTO.setKnockedDownPins(6);
        gameDTO.getPlayerChanceDTOs().add(playerChanceDTO);

        playerChanceDTO = new PlayerChanceDTO();
        playerChanceDTO.setPlayerName(playerName);
        playerChanceDTO.setKnockedDownPins(3);
        gameDTO.getPlayerChanceDTOs().add(playerChanceDTO);

        if (mockPinFalls) {
            frameDTO = new FrameDTO();
            pinFallDTO = new PinFallDTO(false, 6);
            frameDTO.getPinFalls().add(pinFallDTO);
            pinFallDTO = new PinFallDTO(false, 3);
            frameDTO.getPinFalls().add(pinFallDTO);
            gameDTO.getFrameDTOS().add(frameDTO);
        }

        //Frame 3
        playerChanceDTO = new PlayerChanceDTO();
        playerChanceDTO.setPlayerName(playerName);
        playerChanceDTO.setKnockedDownPins(10);
        gameDTO.getPlayerChanceDTOs().add(playerChanceDTO);

        if (mockPinFalls) {
            frameDTO = new FrameDTO();
            pinFallDTO = new PinFallDTO(false, 10);
            frameDTO.getPinFalls().add(pinFallDTO);
            gameDTO.getFrameDTOS().add(frameDTO);
        }

        //Frame 4
        playerChanceDTO = new PlayerChanceDTO();
        playerChanceDTO.setPlayerName(playerName);
        playerChanceDTO.setKnockedDownPins(8);
        gameDTO.getPlayerChanceDTOs().add(playerChanceDTO);

        playerChanceDTO = new PlayerChanceDTO();
        playerChanceDTO.setPlayerName(playerName);
        playerChanceDTO.setKnockedDownPins(1);
        gameDTO.getPlayerChanceDTOs().add(playerChanceDTO);

        if (mockPinFalls) {
            frameDTO = new FrameDTO();
            pinFallDTO = new PinFallDTO(false, 8);
            frameDTO.getPinFalls().add(pinFallDTO);
            pinFallDTO = new PinFallDTO(false, 1);
            frameDTO.getPinFalls().add(pinFallDTO);
            gameDTO.getFrameDTOS().add(frameDTO);
        }

        //Frame 5
        playerChanceDTO = new PlayerChanceDTO();
        playerChanceDTO.setPlayerName(playerName);
        playerChanceDTO.setKnockedDownPins(10);
        gameDTO.getPlayerChanceDTOs().add(playerChanceDTO);

        if (mockPinFalls) {
            frameDTO = new FrameDTO();
            pinFallDTO = new PinFallDTO(false, 10);
            frameDTO.getPinFalls().add(pinFallDTO);
            gameDTO.getFrameDTOS().add(frameDTO);
        }

        //Frame 6
        playerChanceDTO = new PlayerChanceDTO();
        playerChanceDTO.setPlayerName(playerName);
        playerChanceDTO.setKnockedDownPins(10);
        gameDTO.getPlayerChanceDTOs().add(playerChanceDTO);

        if (mockPinFalls) {
            frameDTO = new FrameDTO();
            pinFallDTO = new PinFallDTO(false, 10);
            frameDTO.getPinFalls().add(pinFallDTO);
            gameDTO.getFrameDTOS().add(frameDTO);
        }

        //Frame 7
        playerChanceDTO = new PlayerChanceDTO();
        playerChanceDTO.setPlayerName(playerName);
        playerChanceDTO.setKnockedDownPins(9);
        gameDTO.getPlayerChanceDTOs().add(playerChanceDTO);

        playerChanceDTO = new PlayerChanceDTO();
        playerChanceDTO.setPlayerName(playerName);
        playerChanceDTO.setKnockedDownPins(0);
        gameDTO.getPlayerChanceDTOs().add(playerChanceDTO);

        if (mockPinFalls) {
            frameDTO = new FrameDTO();
            pinFallDTO = new PinFallDTO(false, 9);
            frameDTO.getPinFalls().add(pinFallDTO);
            pinFallDTO = new PinFallDTO(false, 0);
            frameDTO.getPinFalls().add(pinFallDTO);
            gameDTO.getFrameDTOS().add(frameDTO);
        }

        //Frame 8
        playerChanceDTO = new PlayerChanceDTO();
        playerChanceDTO.setPlayerName(playerName);
        playerChanceDTO.setKnockedDownPins(7);
        gameDTO.getPlayerChanceDTOs().add(playerChanceDTO);

        playerChanceDTO = new PlayerChanceDTO();
        playerChanceDTO.setPlayerName(playerName);
        playerChanceDTO.setKnockedDownPins(3);
        gameDTO.getPlayerChanceDTOs().add(playerChanceDTO);

        if (mockPinFalls) {
            frameDTO = new FrameDTO();
            pinFallDTO = new PinFallDTO(false, 7);
            frameDTO.getPinFalls().add(pinFallDTO);
            pinFallDTO = new PinFallDTO(false, 3);
            frameDTO.getPinFalls().add(pinFallDTO);
            gameDTO.getFrameDTOS().add(frameDTO);
        }

        //Frame 9
        playerChanceDTO = new PlayerChanceDTO();
        playerChanceDTO.setPlayerName(playerName);
        playerChanceDTO.setKnockedDownPins(4);
        gameDTO.getPlayerChanceDTOs().add(playerChanceDTO);

        playerChanceDTO = new PlayerChanceDTO();
        playerChanceDTO.setPlayerName(playerName);
        playerChanceDTO.setKnockedDownPins(4);
        gameDTO.getPlayerChanceDTOs().add(playerChanceDTO);

        if (mockPinFalls) {
            frameDTO = new FrameDTO();
            pinFallDTO = new PinFallDTO(false, 4);
            frameDTO.getPinFalls().add(pinFallDTO);
            pinFallDTO = new PinFallDTO(false, 4);
            frameDTO.getPinFalls().add(pinFallDTO);
            gameDTO.getFrameDTOS().add(frameDTO);
        }

        //Frame 10
        playerChanceDTO = new PlayerChanceDTO();
        playerChanceDTO.setPlayerName(playerName);
        playerChanceDTO.setKnockedDownPins(10);
        gameDTO.getPlayerChanceDTOs().add(playerChanceDTO);

        playerChanceDTO = new PlayerChanceDTO();
        playerChanceDTO.setPlayerName(playerName);
        playerChanceDTO.setKnockedDownPins(9);
        gameDTO.getPlayerChanceDTOs().add(playerChanceDTO);

        playerChanceDTO = new PlayerChanceDTO();
        playerChanceDTO.setPlayerName(playerName);
        playerChanceDTO.setKnockedDownPins(0);
        gameDTO.getPlayerChanceDTOs().add(playerChanceDTO);

        if (mockPinFalls) {
            frameDTO = new FrameDTO();
            pinFallDTO = new PinFallDTO(false, 10);
            frameDTO.getPinFalls().add(pinFallDTO);
            pinFallDTO = new PinFallDTO(false, 9);
            frameDTO.getPinFalls().add(pinFallDTO);
            pinFallDTO = new PinFallDTO(false, 0);
            frameDTO.getPinFalls().add(pinFallDTO);
            gameDTO.getFrameDTOS().add(frameDTO);
        }

        return gameDTO;
    }
}
