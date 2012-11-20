package com.ddimitroff.projects.dwallet.rest.response;

import java.io.Serializable;

/**
 * A DTO class for object/JSON mapping response. It has the following
 * structure:<br>
 * {"success":"true/false"}
 * 
 * @author Dimitar Dimitrov
 * 
 */
public class Response implements Serializable, Responsable {

    /** Serial version UID constant */
    private static final long serialVersionUID = 1L;
    
    /** {@code true} if response is successful, {@code false} otherwise */
    private boolean success;
    
    /**
     * Default {@link Response} constructor
     */
    public Response() {
    }

    /**
     * Constructor for {@link Response} objects with parameter
     * 
     * @param success - {@code true} if response should be successful, {@code false} otherwise
     */
    public Response(boolean success) {
        this.success = success;
    }

    /**
     * @return the success
     */
    public boolean isSuccess() {
        return success;
    }
    
    /**
     * @param successArg the success to set
     */
    public void setSuccess(boolean successArg) {
        success = successArg;
    }
    
}
