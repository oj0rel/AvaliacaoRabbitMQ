package com.avaliacao.worker_subtrator.dto;

import java.io.Serializable;

public class NumerosDTO implements Serializable { //
    public int numeroA;
    public int numeroB;

    public NumerosDTO() {
    }

    public NumerosDTO(int numeroA, int numeroB) {
        this.numeroA = numeroA;
        this.numeroB = numeroB;
    }

    public int getNumeroA() {
        return numeroA;
    }

    public void setNumeroA(int numeroA) {
        this.numeroA = numeroA;
    }

    public int getNumeroB() {
        return numeroB;
    }

    public void setNumeroB(int numeroB) {
        this.numeroB = numeroB;
    }
}
