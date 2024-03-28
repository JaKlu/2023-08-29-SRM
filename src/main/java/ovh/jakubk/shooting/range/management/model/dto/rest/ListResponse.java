package ovh.jakubk.shooting.range.management.model.dto.rest;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class ListResponse<T> {
    private final List<T> list;
}
