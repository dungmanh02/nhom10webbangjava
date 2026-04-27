package com.lapzone.lapzoneweb.model.entity;

public class ProductDetail {
    private Long id;
    private String cpu;
    private String ram;
    private String storage;
    private String gpu;
    private String screen;
    private String battery;
    private String audio;
    private String ports;
    private String wireless;
    private String weight;
    private String color;
    private String os;
    private String conditionStatus;
    private String warranty;
    private String moreInfo;
    private Long productId;

    public ProductDetail() {}

    public ProductDetail(Long id, String cpu, String ram, String storage, String gpu, String screen, String battery, String audio, String ports, String wireless, String weight, String color, String os, String conditionStatus, String warranty, String moreInfo, Long productId) {
        this.id = id;
        this.cpu = cpu;
        this.ram = ram;
        this.storage = storage;
        this.gpu = gpu;
        this.screen = screen;
        this.battery = battery;
        this.audio = audio;
        this.ports = ports;
        this.wireless = wireless;
        this.weight = weight;
        this.color = color;
        this.os = os;
        this.conditionStatus = conditionStatus;
        this.warranty = warranty;
        this.moreInfo = moreInfo;
        this.productId = productId;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCpu() { return cpu; }
    public void setCpu(String cpu) { this.cpu = cpu; }
    public String getRam() { return ram; }
    public void setRam(String ram) { this.ram = ram; }
    public String getStorage() { return storage; }
    public void setStorage(String storage) { this.storage = storage; }
    public String getGpu() { return gpu; }
    public void setGpu(String gpu) { this.gpu = gpu; }
    public String getScreen() { return screen; }
    public void setScreen(String screen) { this.screen = screen; }
    public String getBattery() { return battery; }
    public void setBattery(String battery) { this.battery = battery; }
    public String getAudio() { return audio; }
    public void setAudio(String audio) { this.audio = audio; }
    public String getPorts() { return ports; }
    public void setPorts(String ports) { this.ports = ports; }
    public String getWireless() { return wireless; }
    public void setWireless(String wireless) { this.wireless = wireless; }
    public String getWeight() { return weight; }
    public void setWeight(String weight) { this.weight = weight; }
    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
    public String getOs() { return os; }
    public void setOs(String os) { this.os = os; }
    public String getConditionStatus() { return conditionStatus; }
    public void setConditionStatus(String conditionStatus) { this.conditionStatus = conditionStatus; }
    public String getWarranty() { return warranty; }
    public void setWarranty(String warranty) { this.warranty = warranty; }
    public String getMoreInfo() { return moreInfo; }
    public void setMoreInfo(String moreInfo) { this.moreInfo = moreInfo; }
    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }
}