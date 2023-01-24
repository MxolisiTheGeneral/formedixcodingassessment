package com.formedix.coding.exercise.model;

import lombok.Data;
import java.io.Serializable;

@Data
public class Response implements Serializable {

    private static final long serialVersionUID = -8155353421151783337L;
    private String message;
}