package org.example.entity;

import lombok.Builder;
import lombok.Data;


public interface StorageBook {
    Integer getStorageId();
    Integer getBookId();
    Integer getBookAmount();

    void setStorageId(Integer storageId);
    void setBookId(Integer bookId);
    void setBookAmount(Integer bookAmount);
}
