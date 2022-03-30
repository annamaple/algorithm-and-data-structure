package com.annamaple.utils.test;

public enum FmZtModifyColumn {

    PTY_CLASS("负面主体类型编码", "ptyClass"),
    PTY_NAME("负面主体名称", "ptyName"),
    PTY_ID("负面主体代码", "ptyId"),
    DEPT_CODE("所属外汇局代码", "deptCode"),
    PTY_WGLX("违规类型", "ptyWglx"),
    PTY_LY("负面主体来源", "ptyLy"),
    WG_AMT_USD("违规金额（折美元）", "wgAmtUsd"),
    WG_AMT_CN("违规金额（元人民币）", "wgAmtCn"),
    AJ_BH("案件系统编号", "ajBh"),
    FK_AMT("罚没款总额", "fkAmt"),
    NAME("实控人", "name"),
    NAME_CODE("实控人身份证号码", "nameCode"),
    PTY_ADDRESS("地址", "ptyAddress"),
    PTY_TEL("电话", "ptyTel"),
    PTY_ZH("账户", "ptyZh"),
    HW_DJ("货物贸易外汇管理分类等级", "hwDj"),
    HW_MS("货物贸易外汇管理分类标签描述", "hwMs"),
    HW_CLOCK("货物贸易外汇管理分类时间", "hwClock"),
    ZB_GK("资本项目外汇业务管控", "zbGk"),
    ZB_MS("资本项目外汇管理管控标签描述", "zbMs"),
    ZB_CLOCK("资本项目外汇业务管控时间", "zbClock"),
    YHPG_DJ("银行评估等级", "yhpgDj"),
    YHPG_YEAR("银行评估年度", "yhpgYear");
    private final String desc;
    private final String methodName;


    FmZtModifyColumn(String desc, String methodName) {
        this.desc = desc;
        this.methodName = methodName;
    }

    public String getDesc() {
        return desc;
    }

    public String getMethodName() {
        return methodName;
    }
}