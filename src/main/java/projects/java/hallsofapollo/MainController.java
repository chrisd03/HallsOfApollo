package projects.java.hallsofapollo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import projects.java.hallsofapollo.business.QueryCaller;
import projects.java.hallsofapollo.business.ResultReader;

import javafx.scene.image.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.IOException;
import java.nio.IntBuffer;

public class MainController {
    QueryCaller qc = QueryCaller.getInstance();
    ResultReader rr = ResultReader.getInstance();
    private static String search;
    @FXML
    TextField searchBox;
    @FXML
    ImageView imageView;

    @FXML
    private void getSearch(){
        search = searchBox.getText().strip();
    }
    @FXML
    private void sendSearch(ActionEvent ae){
        getSearch();
        qc.setSearch(search);
        qc.performSearchQuery();
        showImage(convertImage());
    }

    private Image convertImage(){
        Image displayImage;
        try{
            BufferedImage image = rr.findImage();
            displayImage = (getImage(image));
        }catch (IOException e){
            throw new RuntimeException();
        }
        return displayImage;
    }

    @FXML
    private void showImage(Image image){
        imageView.setImage(image);
    }

    private Image getImage(BufferedImage img){
        //converting to a good type, read about types here: https://openjfx.io/javadoc/13/javafx.graphics/javafx/scene/image/PixelBuffer.html
        BufferedImage newImg = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB_PRE);
        newImg.createGraphics().drawImage(img, 0, 0, img.getWidth(), img.getHeight(), null);

        //converting the BufferedImage to an IntBuffer
        int[] type_int_agrb = ((DataBufferInt) newImg.getRaster().getDataBuffer()).getData();
        IntBuffer buffer = IntBuffer.wrap(type_int_agrb);

        //converting the IntBuffer to an Image, read more about it here: https://openjfx.io/javadoc/13/javafx.graphics/javafx/scene/image/PixelBuffer.html
        PixelFormat<IntBuffer> pixelFormat = PixelFormat.getIntArgbPreInstance();
        PixelBuffer<IntBuffer> pixelBuffer = new PixelBuffer(newImg.getWidth(), newImg.getHeight(), buffer, pixelFormat);
        return new WritableImage(pixelBuffer);
    }
}