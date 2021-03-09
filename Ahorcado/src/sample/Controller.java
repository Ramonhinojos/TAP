package sample;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.util.Random;

public class Controller {
    @FXML
    HBox contenedor;
    @FXML AnchorPane padre;

    String [] palabras={"MANO","PERRO","CORONA","COMPUTADORA","CELULAR","ESCALERA","MARTILLO"};
    TextField[] arrayTxt= null;
    @FXML protected void initialize(){
        Random random=new Random();
        int aleatorio= random.nextInt(6);
        String palabra=palabras[aleatorio].toLowerCase();
        System.out.println(palabra);
        arrayTxt=new TextField[palabra.length()];
        int ayuda=1;//estas son las palabras de ayuda

        for(int x=0;x<palabra.length();x++){
            arrayTxt[x]=new TextField();
            arrayTxt[x].setPrefWidth(50);
            arrayTxt[x].setPrefHeight(50);
            arrayTxt[x].setId("txt-"+x+"-"+palabra.charAt(x));
            arrayTxt[x].setOnKeyReleased(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {
                    TextField textField=(TextField) event.getTarget();//OBTENER LO QUE DISPARA EL EVENTO
                    String[] nombre=textField.getId().split("-");
                    if(nombre[2].equals(textField.getText().toLowerCase())){
                        System.out.println("CORRECTO"+textField.getId());
                        textField.setDisable(true);
                    }else{
                        System.out.println("INCORRECTO"+textField.getId());
                        textField.setText("");
                        pintarCuerpo();

                    }
                   // System.out.println(arrayTxt[x].getText());
                }
            });
            contenedor.getChildren().add(arrayTxt[x]);
        }
    }
public void pintarCuerpo() {
    ImageView cabeza = new ImageView(new Image("sample/img/cabeza.png"));
    cabeza.setFitWidth(70);
    cabeza.setFitHeight(70);
    cabeza.setLayoutX(200);
    cabeza.setLayoutY(200);

    ImageView cuerpo = new ImageView(new Image("sample/img/cuerpo.png"));
    cuerpo.setFitWidth(70);
    cuerpo.setFitHeight(70);
    cuerpo.setLayoutX(200);
    cuerpo.setLayoutY(265);

    ImageView manoiz=new ImageView(new Image("sample/img/manoiz.png"));
    manoiz.setFitWidth(70);
    manoiz.setFitHeight(70);
    manoiz.setLayoutX(160);
    manoiz.setLayoutY(270);

    ImageView manode=new ImageView(new Image("sample/img/manode.png"));
    manode.setFitWidth(70);
    manode.setFitHeight(70);
    manode.setLayoutX(240);
    manode.setLayoutY(270);

    ImageView pieiz=new ImageView(new Image("sample/img/pieiz.png"));
    pieiz.setFitWidth(70);
    pieiz.setFitHeight(70);
    pieiz.setLayoutX(160);
    pieiz.setLayoutY(330);

    ImageView piede=new ImageView(new Image("sample/img/piede.png"));
    piede.setFitWidth(70);
    piede.setFitHeight(70);
    piede.setLayoutX(240);
    piede.setLayoutY(330);

    padre.getChildren().addAll(cabeza,cuerpo,manoiz,manode,pieiz,piede);
}

}
