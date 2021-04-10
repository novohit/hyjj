package com.hyjj.hyjjservice.controller.company.viewObject;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.List;


public class DetailComInfoVO {

    @ApiModelProperty("企业ID，新增的时候为空，添加的时候要传给我")
    private Long id;

    @ApiModelProperty("企业名称")
    private String comName;

    @ApiModelProperty("组织机构代码")
    private String comCode;

    @ApiModelProperty("法定负责人")
    private String comDelegate;

    @ApiModelProperty("单位类型")
    private String comComtype;

    @ApiModelProperty("机构类型")
    private String comOrgtype;

    @ApiModelProperty("企业形象图片")
    private String comImgurl;

    @ApiModelProperty("所属行业")
    @NotNull(message = "所属行业不能为空")
    private Integer industryId;

    @ApiModelProperty("行业代码")
    private String comIndustrycode;

    @ApiModelProperty("主要业务活动")
    private List<String> bussiness;

    @ApiModelProperty("登记注册的类型")
    private String comRegtype;

    @ApiModelProperty("营业状态")
    private String comBusinessstatus;


    @ApiModelProperty("开始营业时间年")
    private String comCreateyear;

    @ApiModelProperty("开始营业时间月")
    private String comCreatemonth;

    @ApiModelProperty("统计机构")
    private String comStatisticsorg;


    @ApiModelProperty("注册地区省")
    private String comRegaddrSheng;

    @ApiModelProperty("注册地区地")
    private String comRegaddrDi;

    @ApiModelProperty("注册地区县")
    @NotBlank(message = "注册地区县不能为空")
    private String comRegaddrXian;

    @ApiModelProperty("注册地区乡")
    private String comRegaddrXiang;

    @ApiModelProperty("注册地区街道办")
    private String comRegaddrJie;

    @ApiModelProperty("注册地区社区居委会")
    private String comRegaddrShe;

    @ApiModelProperty("单位注册地地址（街）【注册地详细地址】")
    private String comRegaddr;

    @ApiModelProperty("注册地区划代码")
    private String comRegareacode;

    @ApiModelProperty("传真号码")
    private String comFax;

    @ApiModelProperty("电子邮箱")
    private String comEmail;

    @ApiModelProperty("网址")
    private String comHttp;

    @ApiModelProperty("邮政编码")
    private String comZipcode;

    @ApiModelProperty("机关级别工商")
    private String comOrglevelBus;

    @ApiModelProperty("登记注册号工商")
    private String comRegcodeBus;

    @ApiModelProperty("机关级别编制")
    private String comOrglevelForm;

    @ApiModelProperty("登记注册号编制")
    private String comRegcodeForm;

    @ApiModelProperty("机关级别民政")
    private String comOrglevelCivil;

    @ApiModelProperty("登记注册号民政")
    private String comRegcodeCivil;

    @ApiModelProperty("机关级别国税")
    private String comOrglevelNattax;

    @ApiModelProperty("登记注册号国税")
    private String comRegcodeNattax;

    @ApiModelProperty("机关级别地税")
    private String comOrglevelTax;

    @ApiModelProperty("登记注册号地税")
    private String comRegcodeTax;

    @ApiModelProperty("机关级别其他")
    private String comOrglevelOth;

    @ApiModelProperty("登记注册号其他")
    private String comRegcodeOth;

    @ApiModelProperty("企业控股情况")
    private String comStock;

    @ApiModelProperty("隶属关系")
    private String comSubrel;

    @ApiModelProperty("执行会计标准类别")
    private String comImpaccclass;

    @ApiModelProperty("是否执行2006年《企业会计准则》")
    private String comIsimprule;

    @ApiModelProperty("单位类别")
    private String comComclass;

    @ApiModelProperty("法人单位组织机构代码")
    private String comDelcomcode;


    @ApiModelProperty("法人单位详细名称")
    private String comDelcomname;


    @ApiModelProperty("法人单位详细地址")
    private String comDelcomaddr;

    @ApiModelProperty("法人单位行政区划代码")
    private String comDelcomareacode;
    @ApiModelProperty("产业活动单位数")
    private Double comIndustrycount;

    @ApiModelProperty("单位规模")
    private String comComscale;

    @ApiModelProperty("所在地城乡代码")
    private String comCitycode;

    @ApiModelProperty("注册地城乡代码")
    private String comRegcitycode;

    @ApiModelProperty("电话分机号")
    private String comTelext;

    @ApiModelProperty("传真分机号")
    private String comFaxext;

    @ApiModelProperty("长途区号")
    private String comZone;

    @ApiModelProperty("固定电话")
    private String comTel;

    @ApiModelProperty("移动电话")
    private String comMobile;

    @ApiModelProperty("行业门类")
    private String comIndustrytype;

    @ApiModelProperty("联系电话")
    private String comContacttel;


    @ApiModelProperty("单位所在地地址（省）")
    private String comAddressSheng;

    @ApiModelProperty("单位所在地地址（地）")
    private String comAddressDi;

    @ApiModelProperty("单位所在地地址（县）")
    private String comAddressXian;

    @ApiModelProperty("单位所在地地址乡")
    private String comAddressXiang;

    @ApiModelProperty("单位所在地位于-街道办事处")
    private String comAddressJie;

    @ApiModelProperty("单位所在地位于-社区（居委会）")
    private String comAddressShe;

    @ApiModelProperty("所在地单位所在地地址（街）【所在地详细地址】")
    private String comAddress;


    @ApiModelProperty("单位负责人")
    private String comComprincipal;

    @ApiModelProperty("统计负责人")
    private String comStaprincipal;

    @ApiModelProperty("填报人")
    private String comFillperson;


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

    public String getComOrgtype() {
        return comOrgtype;
    }

    public void setComOrgtype(String comOrgtype) {
        this.comOrgtype = comOrgtype;
    }

    public String getComImgurl() {
        return comImgurl;
    }

    public void setComImgurl(String comImgurl) {
        this.comImgurl = comImgurl;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
