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
public final class PresenterAlreadyRunningException extends Exception {

    public PresenterAlreadyRunningException() {
    }

    public PresenterAlreadyRunningException(String message) {
        super(message);
    }

    public PresenterAlreadyRunningException(Throwable cause) {
        super(cause);
    }

    public PresenterAlreadyRunningException(String message, Throwable cause) {
        super(message, cause);
    }
}
