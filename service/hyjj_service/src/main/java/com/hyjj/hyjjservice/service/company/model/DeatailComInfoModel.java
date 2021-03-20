package com.hyjj.hyjjservice.service.company.model;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.util.List;

public class DeatailComInfoModel {

    private Long id;


    private String comName;

    private String comImgurl;

    private String comCode;


    private String comDelegate;


    private String comComtype;


    private String comOrgtype;



    private Integer industryId;


    private String comIndustrycode;

    private List<String> bussiness;

    private String comRegtype;

    private String comBusinessstatus;


    private String comCreateyear;

    private String comCreatemonth;

    private String comStatisticsorg;

    private String comRegaddrSheng;

    private String comRegaddrDi;


    private String comRegaddrXian;

    private String comRegaddrXiang;

    private String comRegaddrJie;

    private String comRegaddrShe;

    private String comRegaddr;

    private String comRegareacode;

    private String comFax;

    private String comEmail;

    private String comHttp;

    private String comZipcode;

    private String comOrglevelBus;

    private String comRegcodeBus;

    private String comOrglevelForm;

    private String comRegcodeForm;

    private String comOrglevelCivil;

    private String comRegcodeCivil;

    private String comOrglevelNattax;

    private String comRegcodeNattax;

    private String comOrglevelTax;

    private String comRegcodeTax;

    private String comOrglevelOth;

    private String comRegcodeOth;

    private String comStock;

    private String comSubrel;

    private String comImpaccclass;

    private String comIsimprule;

    private String comComclass;

    private String comDelcomcode;


    private String comDelcomname;


    private String comDelcomaddr;

    private String comDelcomareacode;

    private Double comIndustrycount;

    private String comComscale;

    private String comCitycode;

    private String comRegcitycode;

    private String comTelext;

    private String comFaxext;

    private String comZone;

    private String comTel;

    private String comMobile;

    private String comIndustrytype;

    private String comContacttel;



    private String comAddressSheng;


    private String comAddressDi;


    private String comAddressXian;


    private String comAddressXiang;


    private String comAddressJie;


    private String comAddressShe;


    private String comAddress;



    private String comComprincipal;


    private String comStaprincipal;


    private String comFillperson;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getComName() {
        return comName;
    }

    public void setComName(String comName) {
        this.comName = comName;
    }

    public String getComCode() {
        return comCode;
    }

    public void setComCode(String comCode) {
        this.comCode = comCode;
    }

    public String getComDelegate() {
        return comDelegate;
    }

    public void setComDelegate(String comDelegate) {
        this.comDelegate = comDelegate;
    }

    public String getComComtype() {
        return comComtype;
    }

    public void setComComtype(String comComtype) {
        this.comComtype = comComtype;
    }

    public String getComImgurl() {
        return comImgurl;
    }

    public void setComImgurl(String comImgurl) {
        this.comImgurl = comImgurl;
    }

    public String getComOrgtype() {
        return comOrgtype;
    }

    public void setComOrgtype(String comOrgtype) {
        this.comOrgtype = comOrgtype;
    }



    public Integer getIndustryId() {
        return industryId;
    }

    public void setIndustryId(Integer industryId) {
        this.industryId = industryId;
    }

    public String getComIndustrycode() {
        return comIndustrycode;
    }

    public void setComIndustrycode(String comIndustrycode) {
        this.comIndustrycode = comIndustrycode;
    }

    public List<String> getBussiness() {
        return bussiness;
    }

    public void setBussiness(List<String> bussiness) {
        this.bussiness = bussiness;
    }

    public String getComRegtype() {
        return comRegtype;
    }

    public void setComRegtype(String comRegtype) {
        this.comRegtype = comRegtype;
    }

    public String getComBusinessstatus() {
        return comBusinessstatus;
    }

    public void setComBusinessstatus(String comBusinessstatus) {
        this.comBusinessstatus = comBusinessstatus;
    }

    public String getComCreateyear() {
        return comCreateyear;
    }

    public void setComCreateyear(String comCreateyear) {
        this.comCreateyear = comCreateyear;
    }

    public String getComCreatemonth() {
        return comCreatemonth;
    }

    public void setComCreatemonth(String comCreatemonth) {
        this.comCreatemonth = comCreatemonth;
    }

    public String getComStatisticsorg() {
        return comStatisticsorg;
    }

    public void setComStatisticsorg(String comStatisticsorg) {
        this.comStatisticsorg = comStatisticsorg;
    }

    public String getComRegaddrSheng() {
        return comRegaddrSheng;
    }

    public void setComRegaddrSheng(String comRegaddrSheng) {
        this.comRegaddrSheng = comRegaddrSheng;
    }

    public String getComRegaddrDi() {
        return comRegaddrDi;
    }

    public void setComRegaddrDi(String comRegaddrDi) {
        this.comRegaddrDi = comRegaddrDi;
    }

    public String getComRegaddrXian() {
        return comRegaddrXian;
    }

    public void setComRegaddrXian(String comRegaddrXian) {
        this.comRegaddrXian = comRegaddrXian;
    }

    public String getComRegaddrXiang() {
        return comRegaddrXiang;
    }

    public void setComRegaddrXiang(String comRegaddrXiang) {
        this.comRegaddrXiang = comRegaddrXiang;
    }

    public String getComRegaddrJie() {
        return comRegaddrJie;
    }

    public void setComRegaddrJie(String comRegaddrJie) {
        this.comRegaddrJie = comRegaddrJie;
    }

    public String getComRegaddrShe() {
        return comRegaddrShe;
    }

    public void setComRegaddrShe(String comRegaddrShe) {
        this.comRegaddrShe = comRegaddrShe;
    }

    public String getComRegaddr() {
        return comRegaddr;
    }

    public void setComRegaddr(String comRegaddr) {
        this.comRegaddr = comRegaddr;
    }

    public String getComRegareacode() {
        return comRegareacode;
    }

    public void setComRegareacode(String comRegareacode) {
        this.comRegareacode = comRegareacode;
    }

    public String getComFax() {
        return comFax;
    }

    public void setComFax(String comFax) {
        this.comFax = comFax;
    }

    public String getComEmail() {
        return comEmail;
    }

    public void setComEmail(String comEmail) {
        this.comEmail = comEmail;
    }

    public String getComHttp() {
        return comHttp;
    }

    public void setComHttp(String comHttp) {
        this.comHttp = comHttp;
    }

    public String getComZipcode() {
        return comZipcode;
    }

    public void setComZipcode(String comZipcode) {
        this.comZipcode = comZipcode;
    }

    public String getComOrglevelBus() {
        return comOrglevelBus;
    }

    public void setComOrglevelBus(String comOrglevelBus) {
        this.comOrglevelBus = comOrglevelBus;
    }

    public String getComRegcodeBus() {
        return comRegcodeBus;
    }

    public void setComRegcodeBus(String comRegcodeBus) {
        this.comRegcodeBus = comRegcodeBus;
    }

    public String getComOrglevelForm() {
        return comOrglevelForm;
    }

    public void setComOrglevelForm(String comOrglevelForm) {
        this.comOrglevelForm = comOrglevelForm;
    }

    public String getComRegcodeForm() {
        return comRegcodeForm;
    }

    public void setComRegcodeForm(String comRegcodeForm) {
        this.comRegcodeForm = comRegcodeForm;
    }

    public String getComOrglevelCivil() {
        return comOrglevelCivil;
    }

    public void setComOrglevelCivil(String comOrglevelCivil) {
        this.comOrglevelCivil = comOrglevelCivil;
    }

    public String getComRegcodeCivil() {
        return comRegcodeCivil;
    }

    public void setComRegcodeCivil(String comRegcodeCivil) {
        this.comRegcodeCivil = comRegcodeCivil;
    }

    public String getComOrglevelNattax() {
        return comOrglevelNattax;
    }

    public void setComOrglevelNattax(String comOrglevelNattax) {
        this.comOrglevelNattax = comOrglevelNattax;
    }

    public String getComRegcodeNattax() {
        return comRegcodeNattax;
    }

    public void setComRegcodeNattax(String comRegcodeNattax) {
        this.comRegcodeNattax = comRegcodeNattax;
    }

    public String getComOrglevelTax() {
        return comOrglevelTax;
    }

    public void setComOrglevelTax(String comOrglevelTax) {
        this.comOrglevelTax = comOrglevelTax;
    }

    public String getComRegcodeTax() {
        return comRegcodeTax;
    }

    public void setComRegcodeTax(String comRegcodeTax) {
        this.comRegcodeTax = comRegcodeTax;
    }

    public String getComOrglevelOth() {
        return comOrglevelOth;
    }

    public void setComOrglevelOth(String comOrglevelOth) {
        this.comOrglevelOth = comOrglevelOth;
    }

    public String getComRegcodeOth() {
        return comRegcodeOth;
    }

    public void setComRegcodeOth(String comRegcodeOth) {
        this.comRegcodeOth = comRegcodeOth;
    }

    public String getComStock() {
        return comStock;
    }

    public void setComStock(String comStock) {
        this.comStock = comStock;
    }

    public String getComSubrel() {
        return comSubrel;
    }

    public void setComSubrel(String comSubrel) {
        this.comSubrel = comSubrel;
    }

    public String getComImpaccclass() {
        return comImpaccclass;
    }

    public void setComImpaccclass(String comImpaccclass) {
        this.comImpaccclass = comImpaccclass;
    }

    public String getComIsimprule() {
        return comIsimprule;
    }

    public void setComIsimprule(String comIsimprule) {
        this.comIsimprule = comIsimprule;
    }

    public String getComComclass() {
        return comComclass;
    }

    public void setComComclass(String comComclass) {
        this.comComclass = comComclass;
    }

    public String getComDelcomcode() {
        return comDelcomcode;
    }

    public void setComDelcomcode(String comDelcomcode) {
        this.comDelcomcode = comDelcomcode;
    }

    public String getComDelcomname() {
        return comDelcomname;
    }

    public void setComDelcomname(String comDelcomname) {
        this.comDelcomname = comDelcomname;
    }

    public String getComDelcomaddr() {
        return comDelcomaddr;
    }

    public void setComDelcomaddr(String comDelcomaddr) {
        this.comDelcomaddr = comDelcomaddr;
    }

    public String getComDelcomareacode() {
        return comDelcomareacode;
    }

    public void setComDelcomareacode(String comDelcomareacode) {
        this.comDelcomareacode = comDelcomareacode;
    }

    public Double getComIndustrycount() {
        return comIndustrycount;
    }

    public void setComIndustrycount(Double comIndustrycount) {
        this.comIndustrycount = comIndustrycount;
    }

    public String getComComscale() {
        return comComscale;
    }

    public void setComComscale(String comComscale) {
        this.comComscale = comComscale;
    }

    public String getComCitycode() {
        return comCitycode;
    }

    public void setComCitycode(String comCitycode) {
        this.comCitycode = comCitycode;
    }

    public String getComRegcitycode() {
        return comRegcitycode;
    }

    public void setComRegcitycode(String comRegcitycode) {
        this.comRegcitycode = comRegcitycode;
    }

    public String getComTelext() {
        return comTelext;
    }

    public void setComTelext(String comTelext) {
        this.comTelext = comTelext;
    }

    public String getComFaxext() {
        return comFaxext;
    }

    public void setComFaxext(String comFaxext) {
        this.comFaxext = comFaxext;
    }

    public String getComZone() {
        return comZone;
    }

    public void setComZone(String comZone) {
        this.comZone = comZone;
    }

    public String getComTel() {
        return comTel;
    }

    public void setComTel(String comTel) {
        this.comTel = comTel;
    }

    public String getComMobile() {
        return comMobile;
    }

    public void setComMobile(String comMobile) {
        this.comMobile = comMobile;
    }

    public String getComIndustrytype() {
        return comIndustrytype;
    }

    public void setComIndustrytype(String comIndustrytype) {
        this.comIndustrytype = comIndustrytype;
    }

    public String getComContacttel() {
        return comContacttel;
    }

    public void setComContacttel(String comContacttel) {
        this.comContacttel = comContacttel;
    }

    public String getComAddressSheng() {
        return comAddressSheng;
    }

    public void setComAddressSheng(String comAddressSheng) {
        this.comAddressSheng = comAddressSheng;
    }

    public String getComAddressDi() {
        return comAddressDi;
    }

    public void setComAddressDi(String comAddressDi) {
        this.comAddressDi = comAddressDi;
    }

    public String getComAddressXian() {
        return comAddressXian;
    }

    public void setComAddressXian(String comAddressXian) {
        this.comAddressXian = comAddressXian;
    }

    public String getComAddressXiang() {
        return comAddressXiang;
    }

    public void setComAddressXiang(String comAddressXiang) {
        this.comAddressXiang = comAddressXiang;
    }

    public String getComAddressJie() {
        return comAddressJie;
    }

    public void setComAddressJie(String comAddressJie) {
        this.comAddressJie = comAddressJie;
    }

    public String getComAddressShe() {
        return comAddressShe;
    }

    public void setComAddressShe(String comAddressShe) {
        this.comAddressShe = comAddressShe;
    }

    public String getComAddress() {
        return comAddress;
    }

    public void setComAddress(String comAddress) {
        this.comAddress = comAddress;
    }

    public String getComComprincipal() {
        return comComprincipal;
    }

    public void setComComprincipal(String comComprincipal) {
        this.comComprincipal = comComprincipal;
    }

    public String getComStaprincipal() {
        return comStaprincipal;
    }

    public void setComStaprincipal(String comStaprincipal) {
        this.comStaprincipal = comStaprincipal;
    }

    public String getComFillperson() {
        return comFillperson;
    }

    public void setComFillperson(String comFillperson) {
        this.comFillperson = comFillperson;
    }


}
