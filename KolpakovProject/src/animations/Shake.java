package animations;

import javafx.scene.*;
import javafx.util.Duration;
import javafx.animation.*;

public class Shake {
	private TranslateTransition tt;
	
	public Shake(Node node) {
		tt = new TranslateTransition(Duration.millis(100),node);
		tt.setFromX(0f);
		tt.setByX(10f);
		tt.setCycleCount(3);
		tt.setAutoReverse(true);
	}

	public void playAnim() {
		tt.playFromStart();
	}
}
