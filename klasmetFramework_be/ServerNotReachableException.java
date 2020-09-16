/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klasmetFramework_be;

/**
 *
 * @author nami
 */
public final class ServerNotReachableException extends Exception {

    public ServerNotReachableException() {
    }

    public ServerNotReachableException(String message) {
        super(message);
    }

    public ServerNotReachableException(Throwable cause) {
        super(cause);
    }

    public ServerNotReachableException(String message, Throwable cause) {
        super(message, cause);
    }
}
