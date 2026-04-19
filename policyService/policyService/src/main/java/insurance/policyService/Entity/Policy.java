package insurance.policyService.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "policy-table")
public class Policy extends BaseEntity {

    private Integer policyNumber;
    private Double prim;
    private Integer customerNumber;
    private String branchCode;
    private String status;
    private Integer remainderTime;
    private LocalDate tanzimDate;
    private LocalDate startDate;
    private LocalDate finishDate;


    private UUID customerId;

    private UUID userId;

    private UUID paymentId;

    public Integer getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(Integer policyNumber) {
        this.policyNumber = policyNumber;
    }

    public Double getPrim() {
        return prim;
    }

    public void setPrim(Double prim) {
        this.prim = prim;
    }

    public Integer getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(Integer customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getRemainderTime() {
        return remainderTime;
    }

    public void setRemainderTime(Integer remainderTime) {
        this.remainderTime = remainderTime;
    }

    public LocalDate getTanzimDate() {
        return tanzimDate;
    }

    public void setTanzimDate(LocalDate tanzimDate) {
        this.tanzimDate = tanzimDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(LocalDate finishDate) {
        this.finishDate = finishDate;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(UUID paymentId) {
        this.paymentId = paymentId;
    }
}