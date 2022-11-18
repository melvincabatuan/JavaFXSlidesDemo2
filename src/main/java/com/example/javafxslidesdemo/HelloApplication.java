package com.example.javafxslidesdemo;

// Author: @MKC

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class HelloApplication extends Application {
    StackPane slideShow;
    Group[] slides;
    final int NUM_SLIDES = 5;
    int currentSlide;

    private Font pokemonFont;

    @Override
    public void start(Stage primaryStage) {
        initFont();
        createSlideArray();

        // Timeline creates the slide animation:
        // Doc: https://openjfx.io/javadoc/19/javafx.graphics/javafx/animation/Timeline.html
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> nextSlide()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        slideShow = new StackPane();
        pauseOnClick(timeline);
        slideShow.getChildren().add(slides[currentSlide++]);

        primaryStage.setTitle("Slide Show");
        primaryStage.setScene(new Scene(slideShow, 640, 480));
        primaryStage.show();
    }

    /**
     * Initializes the font to be used
     */
    private void initFont() {
        pokemonFont = Font.loadFont(getClass()
                .getResourceAsStream("/font/Pokemon Solid.ttf"), 24);
    }

    /**
     * Creates the array of elements for the slide show
     */
    private void createSlideArray() {
        String[] names = new String[]{"Arcanine", "Blastoise", "Bulbasaur", "Charmander", "Charmeleon"};
        slides = new Group[NUM_SLIDES];

        for (int i = 0; i < NUM_SLIDES; i++) {
            ImageView image = new ImageView(names[i] + ".png");
            Label name = new Label(names[i]);
            name.setAlignment(Pos.TOP_LEFT);
            name.setFont(pokemonFont);
            Group item = new Group(image, name);
            slides[i] = item;
        }
    }

    /**
     * Pauses the slideshow on click
     *
     * @param timeline animation for slideshow
     */
    private void pauseOnClick(Timeline timeline) {
        if (slideShow != null) {
            slideShow.setOnMouseClicked(e -> {
                if (timeline.getStatus() == Animation.Status.RUNNING) {
                    timeline.pause();
                } else {
                    timeline.play();
                }
            });
        }
    }

    /**
     * Transition method to next slide
     */
    private void nextSlide() {
        slideShow.getChildren().clear();
        slideShow.getChildren().add(slides[(currentSlide++) % slides.length]);
    }

    /**
     * Main method for launching the app
     *
     * @param args
     */
    public static void main(String[] args) {
        launch();
    }
}