package org.example.entity;

import lombok.Builder;

@Builder
public class ShopBook implements StorageBook {

    private Integer storageId;
    private Integer bookId;
    private Integer bookAmount;

    public Integer getStorageId() {
        return this.storageId;
    }

    public Integer getBookId() {
        return this.bookId;
    }

    public Integer getBookAmount() {
        return this.bookAmount;
    }

    public void setStorageId(Integer storageId) {
        this.storageId = storageId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public void setBookAmount(Integer bookAmount) {
        this.bookAmount = bookAmount;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof ShopBook)) return false;
        final ShopBook other = (ShopBook) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$storageId = this.getStorageId();
        final Object other$storageId = other.getStorageId();
        if (this$storageId == null ? other$storageId != null : !this$storageId.equals(other$storageId)) return false;
        final Object this$bookId = this.getBookId();
        final Object other$bookId = other.getBookId();
        if (this$bookId == null ? other$bookId != null : !this$bookId.equals(other$bookId)) return false;
        final Object this$bookAmount = this.getBookAmount();
        final Object other$bookAmount = other.getBookAmount();
        if (this$bookAmount == null ? other$bookAmount != null : !this$bookAmount.equals(other$bookAmount))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ShopBook;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $storageId = this.getStorageId();
        result = result * PRIME + ($storageId == null ? 43 : $storageId.hashCode());
        final Object $bookId = this.getBookId();
        result = result * PRIME + ($bookId == null ? 43 : $bookId.hashCode());
        final Object $bookAmount = this.getBookAmount();
        result = result * PRIME + ($bookAmount == null ? 43 : $bookAmount.hashCode());
        return result;
    }

    public String toString() {
        return "ShopBook(storageId=" + this.getStorageId() + ", bookId=" + this.getBookId() + ", bookAmount=" + this.getBookAmount() + ")";
    }
}
