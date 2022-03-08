package com.spring.upwork.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import com.spring.model.location.Country;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;



@Entity
@Table(name = "job")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Job {
    @Id
    private Long uid;

    private String title;

    private Date createdOn;
    private Date jobTs;

    private String description;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Collection<Skill> attrs;

    private String duration;
    private String shortDuration;
    private String engagement;
    private String shortEngagement;

    @Transient
    private Currency amount;
    @Transient
    private Currency weeklyBudget;
    private Double weaklyBudgetNo;
    private String hourlyBudget;
    private Double amountNo;
    private String currencyCode;


    private Integer freelancersToHire;
    private Boolean enterpriseJob;
    private String tierText;
    private String tier;
    private String tierLabel;
    private String proposalsTier;

    private Boolean prefFreelancerLocationMandatory;

    @Transient
    private Collection<String> prefFreelancerLocation;

    private Boolean premium;
    private Date publishedOn;
    private Date renewedOn;


//    private Category category;
//    private Collection<Category> subcategories;
//    private Speciality oservice;



    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getJobTs() {
        return jobTs;
    }

    public void setJobTs(Date jobTs) {
        this.jobTs = jobTs;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Collection<Skill> getAttrs() {
        return attrs;
    }

    public void setAttrs(Collection<Skill> attrs) {
        this.attrs = attrs;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getShortDuration() {
        return shortDuration;
    }

    public void setShortDuration(String shortDuration) {
        this.shortDuration = shortDuration;
    }

    public String getEngagement() {
        return engagement;
    }

    public void setEngagement(String engagement) {
        this.engagement = engagement;
    }

    public String getShortEngagement() {
        return shortEngagement;
    }

    public void setShortEngagement(String shortEngagement) {
        this.shortEngagement = shortEngagement;
    }

    public Currency getAmount() {
        return amount;
    }

    public void setAmount(Currency amount) {
        this.amount = amount;
        this.currencyCode = amount.getCurrencyCode();
        this.amountNo = amount.getAmount();
    }

    public Currency getWeeklyBudget() {
        return weeklyBudget;
    }

    public void setWeeklyBudget(Currency weeklyBudget) {
        this.weeklyBudget = weeklyBudget;
        this.weaklyBudgetNo = weeklyBudget.getAmount();
    }

    public Double getWeaklyBudgetNo() {
        return weaklyBudgetNo;
    }

    public void setWeaklyBudgetNo(Double weaklyBudgetNo) {
        this.weaklyBudgetNo = weaklyBudgetNo;
    }

    public Double getAmountNo() {
        return amountNo;
    }

    public void setAmountNo(Double amountNo) {
        this.amountNo = amountNo;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }




    public Integer getFreelancersToHire() {
        return freelancersToHire;
    }

    public void setFreelancersToHire(Integer freelancersToHire) {
        this.freelancersToHire = freelancersToHire;
    }

    public Boolean getEnterpriseJob() {
        return enterpriseJob;
    }

    public void setEnterpriseJob(Boolean enterpriseJob) {
        this.enterpriseJob = enterpriseJob;
    }

    public String getTierText() {
        return tierText;
    }

    public void setTierText(String tierText) {
        this.tierText = tierText;
    }

    public String getTier() {
        return tier;
    }

    public void setTier(String tier) {
        this.tier = tier;
    }

    public String getTierLabel() {
        return tierLabel;
    }

    public void setTierLabel(String tierLabel) {
        this.tierLabel = tierLabel;
    }

    public String getProposalsTier() {
        return proposalsTier;
    }

    public void setProposalsTier(String proposalsTier) {
        this.proposalsTier = proposalsTier;
    }

    public Boolean getPrefFreelancerLocationMandatory() {
        return prefFreelancerLocationMandatory;
    }

    public void setPrefFreelancerLocationMandatory(Boolean prefFreelancerLocationMandatory) {
        this.prefFreelancerLocationMandatory = prefFreelancerLocationMandatory;
    }

    public Collection<String> getPrefFreelancerLocation() {
        return prefFreelancerLocation;
    }

    public void setPrefFreelancerLocation(Collection<String> prefFreelancerLocation) {
        this.prefFreelancerLocation = prefFreelancerLocation;
    }

    public Boolean getPremium() {
        return premium;
    }

    public void setPremium(Boolean premium) {
        this.premium = premium;
    }

    public Date getPublishedOn() {
        return publishedOn;
    }

    public void setPublishedOn(Date publishedOn) {
        this.publishedOn = publishedOn;
    }

    public Date getRenewedOn() {
        return renewedOn;
    }

    public void setRenewedOn(Date renewedOn) {
        this.renewedOn = renewedOn;
    }

//    public Category getCategory() {
//        return category;
//    }
//
//    public void setCategory(Category category) {
//        this.category = category;
//    }

//    public Collection<Category> getSubcategories() {
//        return subcategories;
//    }
//
//    public void setSubcategories(Collection<Category> subcategories) {
//        this.subcategories = subcategories;
//    }

//    public Speciality getOservice() {
//        return oservice;
//    }
//
//    public void setOservice(Speciality oservice) {
//        this.oservice = oservice;
//    }


    public String getHourlyBudget() {
        return hourlyBudget;
    }

    public void setHourlyBudget(String hourlyBudget) {
        this.hourlyBudget = hourlyBudget;
    }

    @Override
    public String toString() {
        return "Job{" +
                "uid=" + uid +
                ", title='" + title + '\'' +
                ", createdOn=" + createdOn +
                ", jobTs=" + jobTs +
                ", description='" + description + '\'' +
                ", attrs=" + attrs +
                ", duration='" + duration + '\'' +
                ", shortDuration='" + shortDuration + '\'' +
                ", engagement='" + engagement + '\'' +
                ", shortEngagement='" + shortEngagement + '\'' +
                ", amount=" + amount +
                ", weeklyBudget=" + weeklyBudget +
                ", hourlyBudget='" + hourlyBudget + '\'' +
                ", freelancersToHire=" + freelancersToHire +
                ", enterpriseJob=" + enterpriseJob +
                ", tierText='" + tierText + '\'' +
                ", tier='" + tier + '\'' +
                ", tierLabel='" + tierLabel + '\'' +
                ", proposalsTier='" + proposalsTier + '\'' +
                ", prefFreelancerLocationMandatory=" + prefFreelancerLocationMandatory +
                ", prefFreelancerLocation=" + prefFreelancerLocation +
                ", premium=" + premium +
                ", publishedOn=" + publishedOn +
                ", renewedOn=" + renewedOn +
                '}';
    }
}
