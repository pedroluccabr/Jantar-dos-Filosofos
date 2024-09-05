/** **************************************************************
 * Autor............: Pedro Lucca Silva Martins
 * Matricula........: 202210183
 * Inicio...........: 16/10/2023
 * Ultima alteracao.: 29/10/2023
 * Nome.............: Jantar dos filósofos
 * Funcao...........: Controlador da GUI
 *************************************************************** */
package pc_trabalho03_202210183;

import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import java.util.concurrent.Semaphore;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;

public class FXMLController implements Initializable {

  @FXML
  private Label label1;
  @FXML
  private Label label2;
  @FXML
  private Label label3;
  @FXML
  private Label label4;
  @FXML
  private Label label5;
  @FXML
  private ImageView garfo1;
  @FXML
  private ImageView garfo2;
  @FXML
  private ImageView garfo3;
  @FXML
  private ImageView garfo4;
  @FXML
  private ImageView garfo5;
  @FXML
  private Slider pensar0;
  @FXML
  private Slider comer0;
  @FXML
  private Slider pensar1;
  @FXML
  private Slider comer1;
  @FXML
  private Slider pensar2;
  @FXML
  private Slider comer2;
  @FXML
  private Slider pensar3;
  @FXML
  private Slider comer3;
  @FXML
  private Slider pensar4;
  @FXML
  private Slider comer4;
  @FXML
  private Button resetGeral;
  @FXML
  private Button pauseSocrates;
  @FXML
  private Button pauseNietzsche;
  @FXML
  private Button pauseKant;
  @FXML
  private Button pausePlatao;
  @FXML
  private Button pauseAristoteles;
  @FXML
  private Button retomarSocrates;
  @FXML
  private Button retomarNietzsche;
  @FXML
  private Button retomarKant;
  @FXML
  private Button retomarPlatao;
  @FXML
  private Button retomarAristoteles;

  private Garfos[] garfos; // declara um array de objetos Garfos

  private Semaphore[] semaforos = new Semaphore[5]; // declaro os objetos do tipo semaforo

  private Filosofos filosofo1;
  private Filosofos filosofo2;
  private Filosofos filosofo3;
  private Filosofos filosofo4;
  private Filosofos filosofo5;

  @FXML
  private void handleButtonAction(ActionEvent event) {

    garfos = new Garfos[]{new Garfos(garfo1, semaforos[0]), new Garfos(garfo2, semaforos[1]), new Garfos(garfo3, semaforos[2]), new Garfos(garfo4, semaforos[3]), new Garfos(garfo5, semaforos[4])};

    filosofo1 = new Filosofos(0, garfos, semaforos, label1, this);
    filosofo2 = new Filosofos(1, garfos, semaforos, label2, this);
    filosofo3 = new Filosofos(2, garfos, semaforos, label3, this);
    filosofo4 = new Filosofos(3, garfos, semaforos, label4, this);
    filosofo5 = new Filosofos(4, garfos, semaforos, label5, this);

    filosofo1.start();
    filosofo2.start();
    filosofo3.start();
    filosofo4.start();
    filosofo5.start();
  }

  @Override
  public void initialize(URL url, ResourceBundle rb) {
    for (int i = 0; i < 5; i++) {
      semaforos[i] = new Semaphore(1); // inicializando os semaforos com 1, para dizer que estao disponiveis
    }
    pensar0.setValue(10);
    pensar1.setValue(10);
    pensar2.setValue(10);
    pensar3.setValue(10);
    pensar4.setValue(10);

    comer0.setValue(10);
    comer1.setValue(10);
    comer2.setValue(10);
    comer3.setValue(10);
    comer4.setValue(10);
  }

  public double getValorPensar(int id) {
    switch (id) {
      case 0:
        return pensar0.getValue();
      case 1:
        return pensar1.getValue();
      case 2:
        return pensar2.getValue();
      case 3:
        return pensar3.getValue();
      case 4:
        return pensar4.getValue();
    }
    return 0;
  }

  public double getValorComer(int id) {
    switch (id) {
      case 0:
        return comer0.getValue();
      case 1:
        return comer1.getValue();
      case 2:
        return comer2.getValue();
      case 3:
        return comer3.getValue();
      case 4:
        return comer4.getValue();
    }
    return 0;
  }

  public void resetGeral(ActionEvent event) {

    if (filosofo1 != null) {

      filosofo1.pararThreads();
      try {
        filosofo1.join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      filosofo1.interrupt();
    } else if (filosofo1 != null) {
      filosofo1.reiniciarInterrompido1();
    }
    if (filosofo2 != null) {

      filosofo2.pararThreads();
      try {
        filosofo2.join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      filosofo2.interrupt();
    } else if (filosofo2 != null) {
      filosofo2.reiniciarInterrompido1();
    }
    if (filosofo3 != null) {

      filosofo3.pararThreads();
      try {
        filosofo3.join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      filosofo3.interrupt();
    } else if (filosofo3 != null) {
      filosofo3.reiniciarInterrompido1();
    }
    if (filosofo4 != null) {

      filosofo4.pararThreads();
      try {
        filosofo4.join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      filosofo4.interrupt();
    } else if (filosofo4 != null) {
      filosofo4.reiniciarInterrompido1();
    }
    if (filosofo5 != null) {

      filosofo5.pararThreads();
      try {
        filosofo5.join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      filosofo5.interrupt();
    } else if (filosofo5 != null) {
      filosofo5.reiniciarInterrompido1();
    }

    // reseto o valor dos sliders
    pensar0.setValue(10);
    comer0.setValue(10);
    pensar1.setValue(10);
    comer1.setValue(10);
    pensar2.setValue(10);
    comer2.setValue(10);
    pensar3.setValue(10);
    comer3.setValue(10);
    pensar4.setValue(10);
    comer4.setValue(10);

    // configura filosofos como null para permitir que sejam reiniciados no play
    filosofo1 = null;
    filosofo2 = null;
    filosofo3 = null;
    filosofo4 = null;
    filosofo5 = null;
  }

  public void pauseSocrates(ActionEvent event) {
    if (filosofo1 != null) {
      filosofo1.pausarFilosofo(0);
    }
  }

  public void pauseNietzsche(ActionEvent event) {
    if (filosofo2 != null) {
      filosofo2.pausarFilosofo(1);
    }
  }

  public void pauseKant(ActionEvent event) {
    if (filosofo3 != null) {
      filosofo3.pausarFilosofo(2);
    }
  }

  public void pausePlatao(ActionEvent event) {
    if (filosofo4 != null) {
      filosofo4.pausarFilosofo(3);
    }
  }

  public void pauseAristoteles(ActionEvent event) {
    if (filosofo5 != null) {
      filosofo5.pausarFilosofo(4);
    }
  }

  public void retomarSocrates(ActionEvent event) {
    if (filosofo1 != null) {
      filosofo1.retomarFilosofo(0);
    }
  }

  public void retomarNietzsche(ActionEvent event) {
    if (filosofo2 != null) {
      filosofo2.retomarFilosofo(1);
    }
  }

  public void retomarKant(ActionEvent event) {
    if (filosofo3 != null) {
      filosofo3.retomarFilosofo(2);
    }
  }

  public void retomarPlatao(ActionEvent event) {
    if (filosofo4 != null) {
      filosofo4.retomarFilosofo(3);
    }
  }

  public void retomarAristoteles(ActionEvent event) {
    if (filosofo5 != null) {
      filosofo5.retomarFilosofo(4);
    }
  }
}
