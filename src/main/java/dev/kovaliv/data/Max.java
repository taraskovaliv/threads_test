package dev.kovaliv.data;

import lombok.Getter;

@Getter
public class Max {
    private long value;

    public Max() {
        this.value = Long.MIN_VALUE;
    }

    public synchronized void setValue(long value) {
        if (value > this.value) {
            this.value = value;
        }
    }
}
