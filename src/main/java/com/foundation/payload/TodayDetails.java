package com.foundation.payload;

public class TodayDetails {
    private Integer orders;
    private Double revenue;
    private Integer newCustomers;
    private Integer totalCustomers;
    private Integer totalMenuItems;
    
    public Integer getOrders() {
        return orders;
    }
    
    public void setOrders(Integer orders) {
        this.orders = orders;
    }
    
    public Double getRevenue() {
        return revenue;
    }
    
    public void setRevenue(Double revenue) {
        this.revenue = revenue;
    }
    
    public Integer getNewCustomers() {
        return newCustomers;
    }
    
    public void setNewCustomers(Integer newCustomers) {
        this.newCustomers = newCustomers;
    }
    
    public Integer getTotalCustomers() {
        return totalCustomers;
    }
    
    public void setTotalCustomers(Integer totalCustomers) {
        this.totalCustomers = totalCustomers;
    }
    
    public Integer getTotalMenuItems() {
        return totalMenuItems;
    }
    
    public void setTotalMenuItems(Integer totalMenuItems) {
        this.totalMenuItems = totalMenuItems;
    }
    
    @Override
    public String toString() {
        return "TodayDetails{" +
                "orders=" + orders +
                ", revenue=" + revenue +
                ", newCustomers=" + newCustomers +
                ", totalMenuItems=" + totalMenuItems +
                '}';
    }
}
