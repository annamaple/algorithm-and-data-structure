package com.annamaple.utils.test;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * 负面主体
 *
 * @author xionglei
 * @create 2021-11-24 14:07
 */
public class FmZt implements Serializable {

    private static final long serialVersionUID = 3747763912333960029L;

    private Date createTime;

    private String createDate;

    /**
     * 负面主体编码
     */
    private String ptyKey;

    /**
     * 负面主体状态 N正常 L逻辑删除 D物理删除
     */
    private String ptyState;

    /**
     * 负面主体类型编码 根据负面主题类型名称获取对应数据
     */
    private String ptyClass;
    /**
     * 负面主体类型名称, 该字段excel导入时使用, 入库时不需要
     */
    private String pthClassName;

    /**
     * 负面主体名称
     */
    private String ptyName;

    /**
     * 负面主体代码
     */
    private String ptyId;

    /**
     * 所属外汇局代码
     */
    private String deptCode;

    /**
     * 所属外汇局名称
     */
    private String deptName;

    /**
     * 违规类型编码 从违规类型表通过违规类型获取类型编码
     */
    private String ptyWglx;
    /**
     * 违规类型名称，用于excel导入 入库不需要
     */
    private String ptyWglxName;

    /**
     * 负面主体来源编码 从负面主体来源表通过来源获取来源编码
     */
    private String ptyLy;
    /**
     * 负面主题来源名称 入库不需要
     */
    private String ptyLyName;

    /**
     * 违规金额（折美元）
     */
    private BigDecimal wgAmtUsd;

    /**
     * 违规金额（元人民币）
     */
    private BigDecimal wgAmtCn;

    /**
     * 案件系统编号
     */
    private String ajBh;

    /**
     * 罚没款总额
     */
    private BigDecimal fkAmt;

    /**
     * 实控人
     */
    private String name;

    /**
     * 实控人身份证号码
     */
    private String nameCode;

    /**
     * 地址
     */
    private String ptyAddress;

    /**
     * 电话
     */
    private String ptyTel;

    /**
     * 账户
     */
    private String ptyZh;

    /**
     * 货物贸易外汇管理分类等级
     */
    private String hwDj;

    /**
     * 货物贸易外汇管理分类标签描述
     */
    private String hwMs;

    /**
     * 货物贸易外汇管理分类时间
     */
    private Date hwClock;

    /**
     * 资本项目外汇业务管控
     */
    private String zbGk;

    /**
     * 资本项目外汇管理管控标签描述
     */
    private String zbMs;

    /**
     * 资本项目外汇业务管控时间
     */
    private Date zbClock;

    /**
     * 银行评估等级
     */
    private String yhpgDj;

    /**
     * 银行评估年度
     */
    private String yhpgYear;

    /**
     * 导入分局代码 系统默认填入
     */
    private String drDebtCode;

    /**
     * 导入分局名称 系统默认填入
     */
    private String drDebtName;

    /**
     * 导入用户 系统默认填入
     */
    private String user;

    /**
     * 导入时间 系统默认填入
     */
    private Date drClock;

    /**
     * 上次更新时间
     */
    private Date lastUpdateTime;


    @Override
    public String toString() {
        return "FmZt{" +
                "ptyClass='" + ptyClass + '\'' +
                ", deptCode='" + deptCode + '\'' +
                ", ptyWglx='" + ptyWglx + '\'' +
                ", ptyWglxName='" + ptyWglxName + '\'' +
                ", ptyLy='" + ptyLy + '\'' +
                ", fkAmt=" + fkAmt +
                ", name='" + name + '\'' +
                ", hwClock=" + hwClock +
                '}';
    }

    public String getPtyState() {
        return ptyState;
    }

    /**
     * 设置负面主体状态, 为FmZtStateType枚举类型
     */
    public void setPtyState(String ptyState) {
        this.ptyState = ptyState;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getPtyKey() {
        return ptyKey;
    }

    public void setPtyKey(String ptyKey) {
        this.ptyKey = ptyKey;
    }

    public String getPtyClass() {
        return ptyClass;
    }

    public void setPtyClass(String ptyClass) {
        this.ptyClass = ptyClass;
    }

    public String getPthClassName() {
        return pthClassName;
    }

    public void setPthClassName(String pthClassName) {
        this.pthClassName = pthClassName;
    }

    public String getPtyName() {
        return ptyName;
    }

    public void setPtyName(String ptyName) {
        this.ptyName = ptyName;
    }

    public String getPtyId() {
        return ptyId;
    }

    public void setPtyId(String ptyId) {
        this.ptyId = ptyId;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getPtyWglx() {
        return ptyWglx;
    }

    public void setPtyWglx(String ptyWglx) {
        this.ptyWglx = ptyWglx;
    }

    public String getPtyWglxName() {
        return ptyWglxName;
    }

    public void setPtyWglxName(String ptyWglxName) {
        this.ptyWglxName = ptyWglxName;
    }

    public String getPtyLy() {
        return ptyLy;
    }

    public void setPtyLy(String ptyLy) {
        this.ptyLy = ptyLy;
    }

    public String getPtyLyName() {
        return ptyLyName;
    }

    public void setPtyLyName(String ptyLyName) {
        this.ptyLyName = ptyLyName;
    }

    public BigDecimal getWgAmtUsd() {
        return wgAmtUsd;
    }

    public void setWgAmtUsd(BigDecimal wgAmtUsd) {
        this.wgAmtUsd = wgAmtUsd;
    }

    public BigDecimal getWgAmtCn() {
        return wgAmtCn;
    }

    public void setWgAmtCn(BigDecimal wgAmtCn) {
        this.wgAmtCn = wgAmtCn;
    }

    public String getAjBh() {
        return ajBh;
    }

    public void setAjBh(String ajBh) {
        this.ajBh = ajBh;
    }

    public BigDecimal getFkAmt() {
        return fkAmt;
    }

    public void setFkAmt(BigDecimal fkAmt) {
        this.fkAmt = fkAmt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameCode() {
        return nameCode;
    }

    public void setNameCode(String nameCode) {
        this.nameCode = nameCode;
    }

    public String getPtyAddress() {
        return ptyAddress;
    }

    public void setPtyAddress(String ptyAddress) {
        this.ptyAddress = ptyAddress;
    }

    public String getPtyTel() {
        return ptyTel;
    }

    public void setPtyTel(String ptyTel) {
        this.ptyTel = ptyTel;
    }

    public String getPtyZh() {
        return ptyZh;
    }

    public void setPtyZh(String ptyZh) {
        this.ptyZh = ptyZh;
    }

    public String getHwDj() {
        return hwDj;
    }

    public void setHwDj(String hwDj) {
        this.hwDj = hwDj;
    }

    public String getHwMs() {
        return hwMs;
    }

    public void setHwMs(String hwMs) {
        this.hwMs = hwMs;
    }

    public Date getHwClock() {
        return hwClock;
    }

    public void setHwClock(Date hwClock) {
        this.hwClock = hwClock;
    }

    public String getZbGk() {
        return zbGk;
    }

    public void setZbGk(String zbGk) {
        this.zbGk = zbGk;
    }

    public String getZbMs() {
        return zbMs;
    }

    public void setZbMs(String zbMs) {
        this.zbMs = zbMs;
    }

    public Date getZbClock() {
        return zbClock;
    }

    public void setZbClock(Date zbClock) {
        this.zbClock = zbClock;
    }

    public String getYhpgDj() {
        return yhpgDj;
    }

    public void setYhpgDj(String yhpgDj) {
        this.yhpgDj = yhpgDj;
    }

    public String getYhpgYear() {
        return yhpgYear;
    }

    public void setYhpgYear(String yhpgYear) {
        this.yhpgYear = yhpgYear;
    }

    public String getDrDebtCode() {
        return drDebtCode;
    }

    public void setDrDebtCode(String drDebtCode) {
        this.drDebtCode = drDebtCode;
    }

    public String getDrDebtName() {
        return drDebtName;
    }

    public void setDrDebtName(String drDebtName) {
        this.drDebtName = drDebtName;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Date getDrClock() {
        return drClock;
    }

    public void setDrClock(Date drClock) {
        this.drClock = drClock;
    }
}
