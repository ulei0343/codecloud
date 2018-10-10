public enum ProductStatusEnum {

    //在售
    ON_SALES(1);


    private int status;

    public int getStatus() {
        return status;
    }

    ProductStatusEnum(int status) {
        this.status = status;
    }

    public static ProductStatusEnum parse(int status) {
        for (ProductStatusEnum productStatus : values()) {
            if (productStatus.getStatus() == status) {
                return productStatus;
            }
        }
        throw new IllegalStateException();
    }
}
