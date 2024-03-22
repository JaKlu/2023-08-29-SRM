package com.kuba.shooting.range.management.model.rest;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class ListResponse<T> {
    private final List<T> list;
}
