/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klasmetFramework_fe;

/**
 *
 * @author nami
 */
public class KlasmetPresenterModel {

    private String presenterClassName;
    private Object presenterClassObject;

    public KlasmetPresenterModel(Object presenterClassObject, String presenterClassName) {
        this.presenterClassObject = presenterClassObject;
        this.presenterClassName = presenterClassName;
    }

    /**
     * @return the presenterClassName
     */
    public String getPresenterClassName() {
        return presenterClassName;
    }

    /**
     * @param presenterClassName the presenterClassName to set
     */
    public void setPresenterClassName(String presenterClassName) {
        this.presenterClassName = presenterClassName;
    }

    /**
     * @return the presenterClassObject
     */
    public Object getPresenterClassObject() {
        return presenterClassObject;
    }

    /**
     * @param presenterClassObject the presenterClassObject to set
     */
    public void setPresenterClassObject(Object presenterClassObject) {
        this.presenterClassObject = presenterClassObject;
    }

}
