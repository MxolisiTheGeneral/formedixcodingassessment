package com.formedix.coding.exercise.model;


import java.io.Serializable;

public class GenericResponse<T> implements Serializable {

    private static final long serialVersionUID = -2009657402718117058L;
    /**
     * The Successful.
     */

    private final Boolean successful;
    /**
     * The Error message.
     */
    private final String message;
    /**
     * The Payload.
     */

    private transient T payload;

    /**
     * Instantiates a new Generic response.
     *
     * @param aSuccessIndicator the success indicator
     * @param aResponseMessage  the response message
     */
    public GenericResponse(final Boolean aSuccessIndicator, final String aResponseMessage) {
        successful = aSuccessIndicator;
        message = aResponseMessage;
    }

    /**
     * Instantiates a new Generic response.
     *
     * @param aSuccessIndicator the success indicator
     * @param aResponseMessage  the response message
     * @param aPayload          the payload
     */

    public GenericResponse(final Boolean aSuccessIndicator,
                            final String aResponseMessage,
                            T aPayload) {
        successful = aSuccessIndicator;
        message = aResponseMessage;
        payload = aPayload;
    }

    /**
     * Gets successful.
     *
     * @return the successful
     */
    public Boolean getSuccessful() {
        return successful;
    }

    /**
     * Gets message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Gets payload.
     *
     * @return the payload
     */
    public T getPayload() {
        return payload;
    }

    /**
     * Sets payload.
     *
     * @param aPayload the payload
     */
    public void setPayload(T aPayload) {
        payload = aPayload;
    }

    @Override
    public String toString() {
        return "GenericResponse{" + "successful=" + successful +
                ", message='" + message + '\'' +
                ", payload=" + payload +
                '}';
    }
}

