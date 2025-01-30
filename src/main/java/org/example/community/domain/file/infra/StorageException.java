package org.example.community.domain.file.infra;

public class StorageException extends RuntimeException{
  public StorageException(String message, Throwable cause) {
    super(message, cause);
  }
}
