/** **************************************************************
 * Autor............: Pedro Lucca Silva Martins
 * Matricula........: 202210183
 * Inicio...........: 16/10/2023
 * Ultima alteracao.: 29/10/2023
 * Nome.............: filosofos
 * Funcao...........: Classe dos filosofos
 *************************************************************** */
package pc_trabalho03_202210183;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import pc_trabalho03_202210183.FXMLController;

public class Filosofos extends Thread {

  private int filosofoIndex;
  private Garfos[] garfos;
  private Semaphore[] semaforos;
  private int soprasabe = 1;
  private Label label;
  private FXMLController controller;
  private volatile boolean reset = false;
  private volatile boolean pausado = false;
  private volatile boolean interrompido1 = false;

  public Filosofos(int filosofoIndex, Garfos[] garfos, Semaphore[] semaforos, Label label, FXMLController controller) {
    this.filosofoIndex = filosofoIndex;
    this.garfos = garfos;
    this.semaforos = semaforos;
    this.label = label;
    this.controller = controller;
  }

  public void run() {
    while (true) {

      if (interrompido1) {
        interromperJogo();
        break;
      }

      if (pausado) {
        Platform.runLater(() -> {
          label.setText("Pausado");
        });
        // Aguarda até que o estado de pausa mude para retomar
        try {
          Thread.sleep(100);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      } else {
        pensar();
        tentarComer();
      }
    }
  }

  private void pensar() {
    System.out.println("filosofo " + filosofoIndex + " pensando" + "\n--------------------------" + soprasabe);
    soprasabe++;

    // atualizando o label para dizer o que o filosofo esta fazendo
    Platform.runLater(() -> {
      label.setText("Pensando");
    });

    try {
      Thread.sleep(((long) controller.getValorPensar(filosofoIndex) * 100));
    } catch (InterruptedException ex) {
      Logger.getLogger(Filosofos.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  private void tentarComer() {
    try {
      if (semaforos[filosofoIndex].tryAcquire(1, 1000, TimeUnit.MILLISECONDS)) { // ifs para caso ele nao consiga pegar os 2 garfos
        // ele coloca de novo na mesa
        if (semaforos[(filosofoIndex + 1) % 5].tryAcquire(1, 1000, TimeUnit.MILLISECONDS)) {
          if (!pausado) {
            comer();
          }
          semaforos[filosofoIndex].release();
          semaforos[(filosofoIndex + 1) % 5].release();
        } else {
          semaforos[filosofoIndex].release();
          pensar(); // se não conseguir pegar o garfo da direita, pense e tente novamente
        }
      } else {
        pensar(); // se não conseguir pegar o garfo da esquerda, pense e tente novamente
      }
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }

  private void comer() {
    System.out.println("filosofo " + filosofoIndex + " comendo" + "\n--------------------------" + soprasabe);
    soprasabe++;

    // atualizando label para dizer o que o filosofo esta fazendo
    Platform.runLater(() -> {
      label.setText("Comendo");
    });

    try {
      Thread.sleep(((long) controller.getValorComer(filosofoIndex) * 100));
    } catch (InterruptedException ex) {
      Logger.getLogger(Filosofos.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public void pausarFilosofo(int filosofoIndex) {
    pausado = true;
    Platform.runLater(() -> {
      label.setText("Pausado");
    });
  }

  public void retomarFilosofo(int filosofoIndex) {
    pausado = false;
  }
  
  public void pararThreads() {
    interrompido1 = true;
  }
  
  public void reiniciarInterrompido1() {
    interrompido1 = false;
  }
  
  public void interromperJogo() {
    if (interrompido1) {
        Platform.runLater(() -> {
      label.setText("");
    });
    }
  }

}
