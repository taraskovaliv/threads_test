package dev.kovaliv.data;

import lombok.Getter;

@Getter
public class Min {
    private long value;

    public Min() {
        this.value = Long.MAX_VALUE;
    }

    public synchronized void setValue(long value) {
        if (value < this.value) {
            this.value = value;
        }
    }
}
