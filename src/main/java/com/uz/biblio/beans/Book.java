package com.uz.biblio.beans;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import javax.validation.constraints.NotEmpty;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Book {
    private Integer id;
    @NotEmpty
    private String title;
    @NotEmpty
    private String author;
    private String description;
}
