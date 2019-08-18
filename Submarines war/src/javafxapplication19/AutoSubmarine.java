/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication19;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/**
 *
 * @author Lenovo
 */
public class AutoSubmarine extends Pane{
    private double height,width,submarineheight=50,submarinewidth=100;
    private int speed=10,rate=3;
    private ImageView []images={new ImageView("file:src/submarine1.png")
            ,new ImageView("file:src/submarine2.png")
            ,new ImageView("file:src/submarine3.png")};
    Timeline submarinegenerator,submarinemotion;
    AutoSubmarine(double width,double height){
        this.width=width;
        this.height=height;
        insertsubmarine();
        movesubmarines();
       
       
    }
    
    private  void insertsubmarine(){
        submarinegenerator=new Timeline();
        submarinegenerator.getKeyFrames().add(new KeyFrame(Duration.seconds(rate),(e)->{  //insert a submarine
            int k=(int)(Math.random()*3+1);
            ImageView iv =new ImageView("file:src/submarine"+k+".png");
            iv.setFitHeight(submarineheight);
            iv.setFitWidth(submarinewidth);
            iv.setX(-submarinewidth);
            iv.setY((Math.random()*(height/2))+(height/2)-submarineheight);
            Timeline rocketsgenerator = new Timeline(new KeyFrame(Duration.seconds(Math.random()*3+1),(s)->{
            Rockets r = new Rockets(iv);
            this.getChildren().add(r);
            }));
            rocketsgenerator.setCycleCount(Timeline.INDEFINITE);
            rocketsgenerator.play();
            this.getChildren().add(iv);
        }));
        submarinegenerator.setCycleCount(Timeline.INDEFINITE);
        submarinegenerator.play();
        
    }
    private void movesubmarines() {
        submarinemotion=new Timeline();
        submarinemotion.getKeyFrames().add(new KeyFrame(Duration.millis(100),(e)->{
            for (Node n : getChildren()) {
                if(n instanceof ImageView && !(n instanceof Rockets)){
                    ImageView iv = (ImageView)n;
                    iv.setX(iv.getX()+this.speed);
                }
            }
        }));
        submarinemotion.setCycleCount(Timeline.INDEFINITE);
        submarinemotion.play();
        }
    public void stopt(){
        this.submarinegenerator.stop();
        this.submarinemotion.stop();
    }
}
class Rockets extends Pane{
    private int rocketrate=100;
    private ImageView iv;
    private Timeline t;
    public Rockets(ImageView i){
        iv=new ImageView("file:src/missile.png");
        iv.setFitHeight(20);
        iv.setFitWidth(10);
        iv.setX(i.getX());
        iv.setY(i.getY());
        this.getChildren().add(iv);
        t=new Timeline(new KeyFrame(Duration.millis(rocketrate),(e)->{
            double speed = Math.random()*20+10;
            iv.setY(iv.getY()-speed);
        }));
        t.setCycleCount(Timeline.INDEFINITE);
        t.play();
    }
    public double getPostionX(){
        return iv.getX();
    }
    public double getPositionY(){
        return iv.getY();
    }
    public void stopt(){
        t.stop();
    }
}
