package com.example.seedling.interfaces.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "登记书籍的参数")
public record RegisterBookRequest(
    @Schema(description="标题")
    String title,
    @Schema(description="作者")
    String author,
    @Schema(description="简介")
    String description
) {}

