package laatikkopeli.domain;

import java.util.TimerTask;
import javafx.application.Platform;
import javafx.scene.control.Label;


public class TimerHandler extends TimerTask {

    private Label timer;
    int seconds, minutes;
    
    public TimerHandler(Label timer) {
        this.timer = timer;
    }
    
    @Override
    public void run() {
        Platform.runLater(() -> {
            if (this.seconds++ > 59) {
                this.seconds = 0; 
                this.minutes++;
            }
            String time;
            if (this.minutes < 10) {
                time = "0" + this.minutes + ":";
            } else {
                time = "" + this.minutes + ":";
            }
            if (this.seconds < 10) {
                time += "0" + this.seconds;
            } else {
                time += this.seconds;
            }
            this.timer.setText(time);
        });
    }
}
