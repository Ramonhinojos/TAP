package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class Controller {
    @FXML Canvas lienzo;
    GraphicsContext context;
    @FXML ColorPicker colorPicker;
    @FXML Slider slider;
    @FXML Color brushColor;
    @FXML ComboBox comboOpciones;
    Color colorPincel=Color.BLACK;
    @FXML protected void initialize(){
        context=lienzo.getGraphicsContext2D();
        slider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                pintarDibujos (newValue.intValue());
            }
        });

        comboOpciones.getItems().addAll("Cuadricula","Ajedrez","Estrella","Estrella Doble");

        //traer pixteles para manipularlos
        /*
        context.setFill(Color.BLACK);//FILL ES RELLENO
        context.fillRect(10,20,100,50);
        context.setFill(Color.RED);
        context.strokeRect(200,150,200,100);
        context.strokeRect(400,250,200,100);
        context.setFill(Color.BLUE);
        context.fillOval(375,375,50,50);
        context.strokeLine(0,0,lienzo.getWidth(),lienzo.getHeight());*/
    }
    public void pintarDibujos(int valor) {
        context.setFill(Color.WHITESMOKE);
        context.fillRect(0, 0, lienzo.getWidth(), lienzo.getHeight());
        context.setFill(colorPincel);
        System.out.println(valor);


        if (comboOpciones.getSelectionModel().getSelectedIndex() == 0) {
//cuadricula
            for (int x = 1; x < valor+1; x++) {
                int divisiones = (int) lienzo.getWidth() / valor;
                context.strokeLine(0, divisiones*x, lienzo.getWidth(), divisiones*x);//linea horizontal
                context.strokeLine(divisiones*x, 0,divisiones*x , lienzo.getWidth());//linea vertical
            }

        } else if (comboOpciones.getSelectionModel().getSelectedIndex() == 1) {
            //ajedrez

                context.setFill(Color.WHITESMOKE);
                context.fillRect(0, 0, lienzo.getWidth(), lienzo.getHeight());
                for (int x = 1; x < valor+1; x++) {
                int divisiones = (int) lienzo.getWidth() / valor;
                context.strokeLine(0, divisiones * x, lienzo.getWidth(), divisiones * x);//linea horizontal
                context.strokeLine(divisiones * x, 0, divisiones * x, lienzo.getWidth());//linea vertical
            }
        } else if (comboOpciones.getSelectionModel().getSelectedIndex() == 2) {
            //estrella
            int mitadAncho = ((int) lienzo.getWidth()) / 2;
            int mitadAlto = ((int) lienzo.getHeight()) / 2;
            context.strokeLine(mitadAncho, 0, mitadAncho, lienzo.getHeight());
            context.strokeLine(0, mitadAlto, lienzo.getWidth(), mitadAlto);
            int divisiones = mitadAncho / valor;
            for (int j = 1; j < valor+1; j++) {
                context.strokeLine(mitadAncho,divisiones*j,mitadAncho+(divisiones*j),mitadAlto);
                context.strokeLine(divisiones*j,mitadAlto,mitadAncho,mitadAlto+(divisiones*j));
                context.strokeLine(mitadAncho,divisiones*j,mitadAncho-(divisiones*j),mitadAlto);
                context.strokeLine((lienzo.getWidth())-(divisiones*j),lienzo.getHeight()/2,lienzo.getWidth()/2,(lienzo.getHeight()/2+(divisiones*j)));

            }
        } else if (comboOpciones.getSelectionModel().getSelectedIndex() == 3) {
            //estrella doble
        }
    }

    public void cambiarColor(ActionEvent event){
        colorPincel=colorPicker.getValue();
    }
    public void borrar(ActionEvent event){
        context.setFill(Color.WHITESMOKE);
        context.fillRect(0,0,lienzo.getWidth(),lienzo.getHeight());
        //colorPincel=Color.WHITESMOKE;

    }
    public void arrastrar(MouseEvent event){
        context.setFill(colorPincel);
        context.fillOval(event.getX(),event.getY(),10,10);
    }

}
