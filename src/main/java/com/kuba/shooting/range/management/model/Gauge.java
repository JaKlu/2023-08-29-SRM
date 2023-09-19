package com.kuba.shooting.range.management.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;


@Entity
@Table(name = "tgauge")
public enum Gauge {
    cal22LR(".22LR"),
    cal9x19mm("9x19mm"),
    cal5_56x45mm_223REM("5,56x45mm/.223REM"),
    cal7_62x39mm("7,62x39mm"),
    cal7_62x25mm("7,62x25mm"),
    cal9x18mm("9x18mm"),
    cal12("12"),
    cal5_45x39mm("5,45x39mm"),
    cal7_62x54mmR("7,62x54mmR");

    private String caliber;

    Gauge(String caliber) {
        this.caliber = caliber;
    }

    public String getGauge() {
        return caliber;
    }
}
