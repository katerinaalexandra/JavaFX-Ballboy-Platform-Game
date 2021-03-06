package ballboy.view;

import ballboy.model.LevelImpl;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import ballboy.model.GameEngine;

import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class KeyboardInputHandler{
    private final GameEngine model;
    private boolean left = false;
    private boolean right = false;
    private boolean boost = false;
    private boolean drop = false;
    private Set<KeyCode> pressedKeys = new HashSet<>();

    private Map<String, MediaPlayer> sounds = new HashMap<>();

    KeyboardInputHandler(GameEngine model) {
        this.model = model;

        // TODO (longGoneUser): Is there a better place for this code?
        URL mediaUrl = getClass().getResource("/jump.wav");
        String jumpURL = mediaUrl.toExternalForm();

        Media sound = new Media(jumpURL);
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        sounds.put("jump", mediaPlayer);
    }

    void handlePressed(KeyEvent keyEvent) {
        if (pressedKeys.contains(keyEvent.getCode())) {
            if (keyEvent.getCode().equals(KeyCode.LEFT)) {
                model.getCurrentLevel().moveLeft();
            }
            else if (keyEvent.getCode().equals(KeyCode.RIGHT)) {
                model.getCurrentLevel().moveRight();

            } else if (keyEvent.getCode().equals(KeyCode.UP)) {
                model.getCurrentLevel().boostHeight();
            } else if (keyEvent.getCode().equals(KeyCode.DOWN)) {
                model.getCurrentLevel().dropHeight();
            }
        }
        pressedKeys.add(keyEvent.getCode());

        if (keyEvent.getCode().equals(KeyCode.UP)) {
            if (model.boostHeight()) {
                MediaPlayer jumpPlayer = sounds.get("jump");
                jumpPlayer.stop();
                jumpPlayer.play();
            }
        }

        if (keyEvent.getCode().equals(KeyCode.LEFT)) {
            left = true;
        }
        else if (keyEvent.getCode().equals(KeyCode.RIGHT)) {
            right = true;
        } else if (keyEvent.getCode().equals(KeyCode.UP)) {
            boost=true;
        } else if (keyEvent.getCode().equals(KeyCode.DOWN)){
            drop=true;
        } else {
            return;
        }

        if (left) {
            model.getCurrentLevel().moveLeft();
        } else if (right) {
            model.getCurrentLevel().moveRight();
        } else if (boost) {
            model.getCurrentLevel().boostHeight();
        } else if (drop) {
            model.getCurrentLevel().dropHeight();
        }
    }

    void handleReleased(KeyEvent keyEvent) {
        pressedKeys.remove(keyEvent.getCode());

        if (keyEvent.getCode().equals(KeyCode.LEFT)) {
            left = false;
            ((LevelImpl)model.getCurrentLevel()).resetXVel();
        }
        else if (keyEvent.getCode().equals(KeyCode.RIGHT)) {
            right = false;
            ((LevelImpl)model.getCurrentLevel()).resetXVel();
        } else if (keyEvent.getCode().equals(KeyCode.UP)){
            boost=false;
        } else if (keyEvent.getCode().equals(KeyCode.DOWN)){
            drop=false;
        }

        if (!(right || left)) {
            model.getCurrentLevel().dropHeight();

        } else if (right) {
            model.getCurrentLevel().moveRight();
        } else {
            model.getCurrentLevel().moveLeft();
        }
    }
}
