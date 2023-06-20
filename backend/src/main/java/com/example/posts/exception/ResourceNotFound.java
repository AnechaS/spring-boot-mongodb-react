package com.example.posts.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="The resource does not exist.")
public class ResourceNotFound extends RuntimeException {}
