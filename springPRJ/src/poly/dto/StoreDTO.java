package poly.dto;

public class StoreDTO {
    private String store_seq; // seq
    private String store_id; // id
    private String store_password; // password
    private String store_address; // 주소
    private String store_address2; // 상세 주소
    private String exists_yn; // 중복 확인

    public String getStore_seq() {
        return store_seq;
    }

    public void setStore_seq(String store_seq) {
        this.store_seq = store_seq;
    }

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }

    public String getStore_password() {
        return store_password;
    }

    public void setStore_password(String store_password) {
        this.store_password = store_password;
    }

    public String getStore_address() {
        return store_address;
    }

    public void setStore_address(String store_address) {
        this.store_address = store_address;
    }

    public String getStore_address2() {
        return store_address2;
    }

    public void setStore_address2(String store_address2) {
        this.store_address2 = store_address2;
    }

    public String getExists_yn() {
        return exists_yn;
    }

    public void setExists_yn(String exists_yn) {
        this.exists_yn = exists_yn;
    }
}
