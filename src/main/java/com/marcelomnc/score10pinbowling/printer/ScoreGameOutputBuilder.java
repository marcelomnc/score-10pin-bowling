package com.marcelomnc.score10pinbowling.printer;

import com.marcelomnc.score10pinbowling.common.Constants;
import com.marcelomnc.score10pinbowling.dto.FrameDTO;
import com.marcelomnc.score10pinbowling.dto.GameDTO;

class ScoreGameOutputBuilder implements IGameOutputBuilder {
    @Override
    public String buildOutput(GameDTO game) {
        final StringBuilder sb = new StringBuilder();
        sb.append(Constants.PRINT__SCORE_LABEL);
        sb.append(Constants.PRINT__FIELD_SEPARATOR);
        sb.append(Constants.PRINT__FIELD_SEPARATOR);

        for (int i = 0; i < game.getFrames().size(); i++) {
            FrameDTO frameDTO = game.getFrames().get(i);
            String strScore = String.valueOf(frameDTO.getScore());

            sb.append(strScore);
            if (i < 9) {
                sb.append(Constants.PRINT__FIELD_SEPARATOR);
                sb.append(Constants.PRINT__FIELD_SEPARATOR);
            }
        }

        return sb.toString();
    }
}
