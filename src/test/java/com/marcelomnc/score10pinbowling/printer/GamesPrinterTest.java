package com.marcelomnc.score10pinbowling.printer;

import com.marcelomnc.score10pinbowling.dto.GameDTO;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class GamesPrinterTest {
    private IGamePrinter validGamePrinterMock;
    private IGamePrinter invalidGamePrinterMock;
    private GamesPrinter toTest;

    @Before
    public void before() {
        this.validGamePrinterMock = Mockito.mock(IGamePrinter.class);
        this.invalidGamePrinterMock = Mockito.mock(IGamePrinter.class);
        this.toTest = new GamesPrinter(validGamePrinterMock, invalidGamePrinterMock);
    }

    @Test
    public void testSuccessfulPrintWhenAllGamesAreValid() {
        final GameDTO gameMock1 = Mockito.mock(GameDTO.class);
        final GameDTO gameMock2 = Mockito.mock(GameDTO.class);
        Mockito.when(gameMock1.isValid()).thenReturn(true);
        Mockito.when(gameMock2.isValid()).thenReturn(true);

        final List<GameDTO> gamesList = new ArrayList<>();
        gamesList.add(gameMock1);
        gamesList.add(gameMock2);

        this.toTest.printGames(gamesList);

        Mockito.verify(this.validGamePrinterMock, Mockito.times(2)).printGame(ArgumentMatchers.any(GameDTO.class));
        Mockito.verify(this.invalidGamePrinterMock, Mockito.never()).printGame(ArgumentMatchers.any(GameDTO.class));
    }

    @Test
    public void testSuccessfulPrintWhenAllGamesAreInvalid() {
        final GameDTO gameMock1 = Mockito.mock(GameDTO.class);
        final GameDTO gameMock2 = Mockito.mock(GameDTO.class);
        Mockito.when(gameMock1.isValid()).thenReturn(false);
        Mockito.when(gameMock2.isValid()).thenReturn(false);

        final List<GameDTO> gamesList = new ArrayList<>();
        gamesList.add(gameMock1);
        gamesList.add(gameMock2);

        this.toTest.printGames(gamesList);

        Mockito.verify(this.validGamePrinterMock, Mockito.never()).printGame(ArgumentMatchers.any(GameDTO.class));
        Mockito.verify(this.invalidGamePrinterMock, Mockito.times(2)).printGame(ArgumentMatchers.any(GameDTO.class));
    }

    @Test
    public void testSuccessfulPrintWhenOneGameValidAndOneGameInvalid() {
        final GameDTO gameMock1 = Mockito.mock(GameDTO.class);
        final GameDTO gameMock2 = Mockito.mock(GameDTO.class);
        Mockito.when(gameMock1.isValid()).thenReturn(false);
        Mockito.when(gameMock2.isValid()).thenReturn(true);

        final List<GameDTO> gamesList = new ArrayList<>();
        gamesList.add(gameMock1);
        gamesList.add(gameMock2);

        this.toTest.printGames(gamesList);

        Mockito.verify(this.validGamePrinterMock, Mockito.times(1)).printGame(ArgumentMatchers.any(GameDTO.class));
        Mockito.verify(this.invalidGamePrinterMock, Mockito.times(1)).printGame(ArgumentMatchers.any(GameDTO.class));
    }
}