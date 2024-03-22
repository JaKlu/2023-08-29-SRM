package com.kuba.shooting.range.management.model.rest;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class GunListResponseDTO {
    private List<GunResponseDTO> list = new ArrayList<>();
}
