package com.example.practice.Product;
import java.util.List;

public class PagingResponse {

    public class metaData {
        private int current_page;
        private long total_datas;
        private int total_pages;

        metaData() {}

        metaData(int current_page, long totalData, int total_pages) {
            this.current_page = current_page;
            total_datas = totalData;
            this.total_pages = total_pages;
        }
        public int getCurrent_page() {
            return current_page;
        }

        void setCurrent_page(int current_page) {
            this.current_page = current_page;
        }

        public long getTotal_datas() {
            return total_datas;
        }

        public void setTotal_datas(long total_datas) {
            this.total_datas = total_datas;
        }

        public int getTotal_pages() {
            return total_pages;
        }

        public void setTotal_pages(int total_pages) {
            this.total_pages = total_pages;
        }
    }
    private List<Product> result;
    private PagingResponse.metaData metadata = null;

    PagingResponse() {}
    PagingResponse(List<Product> entities, int current_page, long totalData, int total_pages) {
        result = entities;
        metadata = new PagingResponse.metaData(current_page, totalData, total_pages);
    }

    public List<Product> getResult() {
        return result;
    }

    void setResult(List<Product> entities) {
        result = entities;
    }

    public metaData getMetadata() {
        return metadata;
    }

    void setMetadata(metaData metadata) {
        this.metadata = metadata;
    }
}

