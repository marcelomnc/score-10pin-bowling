package com.marcelomnc.score10pinbowling.printer;

import com.marcelomnc.score10pinbowling.common.Constants;
import com.marcelomnc.score10pinbowling.dto.FrameDTO;
import com.marcelomnc.score10pinbowling.dto.GameDTO;

class PinFallsGameOutputBuilder implements IGameOutputBuilder {
    @Override
    public String buildOutput(GameDTO game) {
        final StringBuilder sb = new StringBuilder();
        sb.append(Constants.PRINT__PINFALLS_LABEL);
        sb.append(Constants.PRINT__FIELD_SEPARATOR);

        for (int i = 0; i < game.getFrames().size(); i++) {
            final FrameDTO frame = game.getFrames().get(i);
            if (i == 9) {
                //10th frame (last)
                this.buildPinFallsOutputForLastFrame(frame, sb);
            } else {
                //Frames 1 to 9
                this.buildPinFallsOutputForRegularFrame(frame, sb);
            }
        }

        return sb.toString();
    }

    private void buildPinFallsOutputForRegularFrame(FrameDTO frame, StringBuilder sb) {
        if (frame.getPinFalls().size() == 1) {
            //It's a strike
            sb.append(Constants.PRINT__FIELD_SEPARATOR);
            sb.append(Constants.PRINT__PINFALLS_STRIKE_INDICATOR);
            sb.append(Constants.PRINT__FIELD_SEPARATOR);
        } else {
            sb.append(frame.getPinFalls().get(0).isFoul() ? Constants.PRINT__PINFALLS_FOUL_INDICATOR : String.valueOf(frame.getPinFalls().get(0).getKnockedDownPins()));
            sb.append(Constants.PRINT__FIELD_SEPARATOR);

            if ((frame.getPinFalls().get(0).getKnockedDownPins() + frame.getPinFalls().get(1).getKnockedDownPins()) == 10) {
                //It's a spare
                sb.append(Constants.PRINT__PINFALLS_SPARE_INDICATOR);
            } else {
                //It's an open
                sb.append(frame.getPinFalls().get(1).isFoul() ? Constants.PRINT__PINFALLS_FOUL_INDICATOR : String.valueOf(frame.getPinFalls().get(1).getKnockedDownPins()));
            }
            sb.append(Constants.PRINT__FIELD_SEPARATOR);
        }
    }

    private void buildPinFallsOutputForLastFrame(FrameDTO frame, StringBuilder sb) {
        if (frame.getPinFalls().get(0).isFoul()) {
            sb.append(Constants.PRINT__PINFALLS_FOUL_INDICATOR);
        } else if (frame.getPinFalls().get(0).getKnockedDownPins() == 10) {
            sb.append(Constants.PRINT__PINFALLS_STRIKE_INDICATOR);
        } else {
            sb.append(frame.getPinFalls().get(0).getKnockedDownPins());
        }
        sb.append(Constants.PRINT__FIELD_SEPARATOR);

        if (frame.getPinFalls().get(1).isFoul()) {
            sb.append(Constants.PRINT__PINFALLS_FOUL_INDICATOR);
        } else if (frame.getPinFalls().get(1).getKnockedDownPins() == 10) {
            sb.append(Constants.PRINT__PINFALLS_STRIKE_INDICATOR);
        } else if ((frame.getPinFalls().get(0).getKnockedDownPins() + frame.getPinFalls().get(1).getKnockedDownPins()) == 10) {
            sb.append(Constants.PRINT__PINFALLS_SPARE_INDICATOR);
        } else {
            sb.append(frame.getPinFalls().get(1).getKnockedDownPins());
        }

        if (frame.getPinFalls().size() == 3) {
            sb.append(Constants.PRINT__FIELD_SEPARATOR);

            if (frame.getPinFalls().get(2).isFoul()) {
                sb.append(Constants.PRINT__PINFALLS_FOUL_INDICATOR);
            } else if (frame.getPinFalls().get(2).getKnockedDownPins() == 10) {
                sb.append(Constants.PRINT__PINFALLS_STRIKE_INDICATOR);
            } else {
                sb.append(frame.getPinFalls().get(2).getKnockedDownPins());
            }
        }
    }
}
