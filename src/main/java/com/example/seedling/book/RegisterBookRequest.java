package com.example.seedling.book;

public record RegisterBookRequest(
  String title,
  String author,
  String description
) {}

