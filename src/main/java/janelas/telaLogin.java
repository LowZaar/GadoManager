package janelas;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;

public class telaLogin extends Scene {

	public telaLogin(Parent root, double width, double height) {
		super(root, width, height);
		URL fxmlfile = getClass().getResource("/fxml/telaLogin");
		String cssFile = getClass().getResource("/css/telaLogin").toExternalForm();
		try
		{
			GridPane mainGrid = FXMLLoader.load(fxmlfile);
		}catch(
				IOException e)
		{
			e.printStackTrace();
		}
	}


}
