package com.marcelomnc.score10pinbowling.printer;

import com.marcelomnc.score10pinbowling.dto.GameDTO;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

public class ValidGamePrinterTest {
    private IGameOutputBuilder pinFallsOutputBuilderMock;
    private IGameOutputBuilder scoreOutputBuilderMock;
    private ValidGamePrinter toTest;

    @Before
    public void before() {
        this.pinFallsOutputBuilderMock = Mockito.mock(IGameOutputBuilder.class);
        this.scoreOutputBuilderMock = Mockito.mock(IGameOutputBuilder.class);
        this.toTest = new ValidGamePrinter(pinFallsOutputBuilderMock, scoreOutputBuilderMock);
    }

    @Test
    public void testSuccessfulValidGamePrint() {
        Mockito.when(this.pinFallsOutputBuilderMock.buildOutput(ArgumentMatchers.any(GameDTO.class))).thenReturn("Pin fall formatted output");
        Mockito.when(this.scoreOutputBuilderMock.buildOutput(ArgumentMatchers.any(GameDTO.class))).thenReturn("Score formatted output");

        final GameDTO gameMock = Mockito.mock(GameDTO.class);
        Mockito.when(gameMock.getPlayerName()).thenReturn("Carl");

        this.toTest.printGame(gameMock);

        Mockito.verify(this.pinFallsOutputBuilderMock, Mockito.times(1)).buildOutput(ArgumentMatchers.any(GameDTO.class));
        Mockito.verify(this.scoreOutputBuilderMock, Mockito.times(1)).buildOutput(ArgumentMatchers.any(GameDTO.class));
        Mockito.verify(gameMock, Mockito.times(1)).getPlayerName();
    }
}