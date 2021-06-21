package com.ecuex.ornekproje.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class CustomerDTO {

    private Long tckn;
    @NotBlank(message = "must not be empty")
    private String name;
    @NotBlank(message = "must not be empty")
    private String lastName;

    private Address address;

}