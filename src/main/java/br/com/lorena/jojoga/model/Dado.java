package br.com.lorena.jojoga.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Random;

public class Dado {
    private Random random;

    public Dado() {
        this.random = new Random();
    }

    public int rolar() {
        return random.nextInt(6) + 1;
    }
}
