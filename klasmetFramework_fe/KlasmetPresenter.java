/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klasmetFramework_fe;

import java.util.Iterator;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author nami
 */
public abstract class KlasmetPresenter {

    /**
     * @param stage
     * @param presenterClassObject
     * @param presenterClassName
     * @throws klasmetFramework_fe.PresenterAlreadyRunningException
     */
    public  void klasmetPresenterConstructor(Stage stage, Object presenterClassObject, String presenterClassName) throws PresenterAlreadyRunningException {
        //String presenterClassName = UUID.randomUUID().toString();
        KlasmetPresenterModel klasmetPresenterModel = new KlasmetPresenterModel(presenterClassObject, presenterClassName);

        if (KlasmetPresenterManager.getPresenterModelByPresenterClassName(presenterClassName) == null) {
            KlasmetPresenterManager.getPRESENTER_LIST().add(klasmetPresenterModel);
            stage.setOnHidden(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent e) {
                    klasmetPresenterDestructor(presenterClassName);
                    klasmetDestroyer();
                }
            });
        } else {
            throw new PresenterAlreadyRunningException("Duplicate inmemory presenter");
        }

    }

    public  void klasmetPresenterDestructor(String presenterClassName) {
        Iterator klasmetPresenterModelItr = KlasmetPresenterManager.getPRESENTER_LIST().iterator();
        while (klasmetPresenterModelItr.hasNext()) {
            KlasmetPresenterModel nextKlasmetPresenterModel = (KlasmetPresenterModel) klasmetPresenterModelItr.next();
            if (nextKlasmetPresenterModel.getPresenterClassName().equals(presenterClassName)) {
                klasmetPresenterModelItr.remove();
                break;
            }
        }
    }

    public abstract void klasmetDestroyer();

}
