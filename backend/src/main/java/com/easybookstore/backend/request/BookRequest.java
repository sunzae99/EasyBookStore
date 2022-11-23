package com.easybookstore.backend.request;

import java.util.List;

import com.easybookstore.backend.enums.genre;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookRequest {
    private String name;
    private List<genre> genre;

}
