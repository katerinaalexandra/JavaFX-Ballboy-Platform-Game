package ballboy.view;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import ballboy.model.Entity;
import ballboy.model.GameEngine;

import java.util.ArrayList;
import java.util.List;

public class GameWindow {
    private final int width;
    private final int height;
    private Scene scene;
    private Pane pane;
    private GameEngine model;
    private List<EntityView> entityViews;
    private BackgroundDrawer backgroundDrawer;

    private double xViewportOffset = 0.0;
    private double yViewportOffset = 0.0;
    private static final double VIEWPORT_MARGINX = 280.0;
    private static final double VIEWPORT_MARGINY = 500.0;

    public GameWindow(GameEngine model) {
        this.model = model;

        this.width = (int) model.getCurrentLevel().getLevelWidth();
        this.height = (int) model.getCurrentLevel().getLevelHeight();

        pane = new Pane();
        scene = new Scene(pane, width, height);

        entityViews = new ArrayList<>();

        KeyboardInputHandler keyboardInputHandler = new KeyboardInputHandler(model);

        scene.setOnKeyPressed(keyboardInputHandler::handlePressed);
        scene.setOnKeyReleased(keyboardInputHandler::handleReleased);

        backgroundDrawer = new BlockedBackground();

        backgroundDrawer.draw(model, pane);
    }

    public Scene getScene() {
        return scene;
    }

    public void run() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(17),
                t -> this.draw()));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void draw() {
        model.tick();

        List<Entity> entities = model.getCurrentLevel().getEntities();

        for (EntityView entityView: entityViews) {
            entityView.markForDelete();
        }

        double heroXPos = model.getCurrentLevel().getHeroX();
        //System.out.println(heroXPos);
        heroXPos -= xViewportOffset;

        double heroYPos = model.getCurrentLevel().getHeroY();
        heroYPos -= yViewportOffset;

        if (heroXPos < VIEWPORT_MARGINX) {
            if (xViewportOffset >= 0) { // Don't go further left than the start of the level
                xViewportOffset -= VIEWPORT_MARGINX - heroXPos;
                if (xViewportOffset < 0) {
                    xViewportOffset = 0;
                }
            }
        } else if (heroXPos > width - VIEWPORT_MARGINX) {
            xViewportOffset += heroXPos - (width - VIEWPORT_MARGINX);
        }

        if (heroYPos > VIEWPORT_MARGINY) {
            if (yViewportOffset >= 0) {
                yViewportOffset -= heroYPos - VIEWPORT_MARGINY;
                if (yViewportOffset < 0) {
                    yViewportOffset=0;
                }
            }
        } else if (heroYPos < height - VIEWPORT_MARGINY) {
            yViewportOffset += (height - VIEWPORT_MARGINY) - heroYPos;
        }

        System.out.println(xViewportOffset);
        System.out.println(yViewportOffset);



        // We'll never move up and down, will we?
        backgroundDrawer.update(xViewportOffset, yViewportOffset);

        for (Entity entity: entities) {
            boolean notFound = true;
            for (EntityView view: entityViews) {
                if (view.matchesEntity(entity)) {
                    notFound = false;
                    view.update(xViewportOffset, yViewportOffset);
                    break;
                }
            }
            if (notFound) {
                EntityView entityView = new EntityViewImpl(entity);
                System.out.println(entityView.getNode());
                entityViews.add(entityView);
                pane.getChildren().add(entityView.getNode());
            }
        }

        for (EntityView entityView: entityViews) {
            if (entityView.isMarkedForDelete()) {
                pane.getChildren().remove(entityView.getNode());
            }
        }
        entityViews.removeIf(EntityView::isMarkedForDelete);
    }
}
