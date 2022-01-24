package io.github.davipccunha.dexample.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Note {
    private final String author, content;
}
