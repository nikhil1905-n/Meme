package com.nikhiln.meme.dto;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemeDto {

    @NotBlank(message = "UserName cannot be empty")
    private String name;

    @NotBlank(message = "URL cannot be empty")
    private String url;

    @NotBlank(message = "Caption cannot be empty")
    private String caption;
    
}
