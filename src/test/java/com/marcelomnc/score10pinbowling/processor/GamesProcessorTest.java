package com.marcelomnc.score10pinbowling.processor;

import com.marcelomnc.score10pinbowling.dto.GameDTO;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class GamesProcessorTest {
    private IGameProcessor pinFallsProcessorMock;
    private IGameProcessor scoreProcessorMock;
    private GamesProcessor toTest;

    @Before
    public void before() {
        this.pinFallsProcessorMock = Mockito.mock(IGameProcessor.class);
        this.scoreProcessorMock = Mockito.mock(IGameProcessor.class);
        this.toTest = new GamesProcessor(pinFallsProcessorMock, scoreProcessorMock);
    }

    @Test
    public void testSuccessfulGamesProcessingWhenGamesAreNotInvalidated() {
        final GameDTO gameMock1 = Mockito.mock(GameDTO.class);
        final GameDTO gameMock2 = Mockito.mock(GameDTO.class);
        Mockito.when(gameMock1.isValid()).thenReturn(true);
        Mockito.when(gameMock2.isValid()).thenReturn(true);

        final List<GameDTO> gamesList = new ArrayList<>();
        gamesList.add(gameMock1);
        gamesList.add(gameMock2);

        this.toTest.processGames(gamesList);

        Mockito.verify(gameMock1, Mockito.times(1)).isValid();
        Mockito.verify(gameMock2, Mockito.times(1)).isValid();
        Mockito.verify(this.pinFallsProcessorMock, Mockito.times(2)).processGame(ArgumentMatchers.any(GameDTO.class));
        Mockito.verify(this.scoreProcessorMock, Mockito.times(2)).processGame(ArgumentMatchers.any(GameDTO.class));
    }

    @Test
    public void testSuccessfulGamesProcessingWhenOneGameIsInvalidated() {
        final GameDTO gameMock1 = Mockito.mock(GameDTO.class);
        final GameDTO gameMock2 = Mockito.mock(GameDTO.class);
        Mockito.when(gameMock1.isValid()).thenReturn(false);
        Mockito.when(gameMock2.isValid()).thenReturn(true);

        final List<GameDTO> gamesList = new ArrayList<>();
        gamesList.add(gameMock1);
        gamesList.add(gameMock2);

        this.toTest.processGames(gamesList);

        Mockito.verify(gameMock1, Mockito.times(1)).isValid();
        Mockito.verify(gameMock2, Mockito.times(1)).isValid();
        Mockito.verify(this.pinFallsProcessorMock, Mockito.times(2)).processGame(ArgumentMatchers.any(GameDTO.class));
        Mockito.verify(this.scoreProcessorMock, Mockito.times(1)).processGame(ArgumentMatchers.any(GameDTO.class));
    }

    @Test
    public void testSuccessfulGamesProcessingWhenAllGamesAreInvalidated() {
        final GameDTO gameMock1 = Mockito.mock(GameDTO.class);
        final GameDTO gameMock2 = Mockito.mock(GameDTO.class);
        Mockito.when(gameMock1.isValid()).thenReturn(false);
        Mockito.when(gameMock2.isValid()).thenReturn(false);

        final List<GameDTO> gamesList = new ArrayList<>();
        gamesList.add(gameMock1);
        gamesList.add(gameMock2);

        this.toTest.processGames(gamesList);

        Mockito.verify(gameMock1, Mockito.times(1)).isValid();
        Mockito.verify(gameMock2, Mockito.times(1)).isValid();
        Mockito.verify(this.pinFallsProcessorMock, Mockito.times(2)).processGame(ArgumentMatchers.any(GameDTO.class));
        Mockito.verify(this.scoreProcessorMock, Mockito.never()).processGame(ArgumentMatchers.any(GameDTO.class));
    }
}