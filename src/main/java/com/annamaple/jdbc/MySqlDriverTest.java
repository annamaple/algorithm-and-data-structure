package com.annamaple.jdbc;

import cn.hutool.core.lang.Console;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author xionglei
 * @create 2022-04-08 10:42
 */
public class MySqlDriverTest {

    private static final String DRIVER = "com.informix.jdbc.IfxDriver";
    private static final String URL = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF-8";
    // private static final String URL = "jdbc:informix-sqli://10.2.31.20:9088/jmgc_mzsytjgl:informixserver=gbaseserver;DB_LOCALE=zh_CN.utf8;CLIENT_LOCALE=zh_CN.utf8;NEWCODESET=UTF8,utf8,57372;GL_USEGLU=1;IFX_LOCK_MODE_WAIT=100";
    private static final String USER = "informix";
    private static final String PWD = "informix";

    public static Connection connection() {
        // 加载驱动
        // 创建连接
        Connection connection = null;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USER, PWD);
            if (connection != null) {
                Console.log("数据库连接获取成功");
            } else {
                Console.log("数据库连接获取失败");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
            Console.log("数据库连接获取失败");
        }
        return connection;
    }

    public static void main(String[] args) throws ClassNotFoundException {
       Connection connection = connection();
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement;
            ResultSet resultSet;
            
            preparedStatement = connection.prepareStatement(sql3());
            resultSet = preparedStatement.executeQuery();
            Console.log("执行成功，执行结果为：{}", resultSet);

            preparedStatement = connection.prepareStatement(sql4());
            resultSet = preparedStatement.executeQuery();
            Console.log("执行成功，执行结果为：{}", resultSet);

            preparedStatement = connection.prepareStatement(sql4());
            resultSet = preparedStatement.executeQuery();
            Console.log("执行成功，执行结果为：{}", resultSet);
            
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static String sql1(){
        String sql = "SELECT \n" +
                "    xzqhdj0_.ID                AS id1_4_0_, \n" +
                "    xzqhdj0_.RECVER            AS recver2_4_0_, \n" +
                "    xzqhdj0_.CREATE_TIME       AS create_t3_4_0_, \n" +
                "    xzqhdj0_.MODIFY_TIME       AS modify_t4_4_0_, \n" +
                "    xzqhdj0_.ORG_ID            AS org_id5_4_0_, \n" +
                "    xzqhdj0_.ORG_CODE          AS org_code6_4_0_, \n" +
                "    xzqhdj0_.ORG_NAME          AS org_name7_4_0_, \n" +
                "    xzqhdj0_.CREATOR_ID        AS creator_8_4_0_, \n" +
                "    xzqhdj0_.CREATOR_NAME      AS creator_9_4_0_, \n" +
                "    xzqhdj0_.CREATOR_FULL_NAME AS creator10_4_0_, \n" +
                "    xzqhdj0_.BILL_CODE         AS bill_co11_4_0_, \n" +
                "    xzqhdj0_.BILL_STATE        AS bill_st12_4_0_, \n" +
                "    xzqhdj0_.flow_state        AS flow_sta1_6_0_, \n" +
                "    xzqhdj0_.flow_submit_time  AS flow_sub2_6_0_, \n" +
                "    xzqhdj0_.flow_finish_time  AS flow_fin3_6_0_, \n" +
                "    xzqhdj0_.flow_process_id   AS flow_pro4_6_0_, \n" +
                "    xzqhdj0_.OLDNAME           AS oldname1_22_0_, \n" +
                "    xzqhdj0_.OLDCODE           AS oldcode2_22_0_, \n" +
                "    xzqhdj0_.NEWCODE           AS newcode3_22_0_, \n" +
                "    xzqhdj0_.NEWNAME           AS newname4_22_0_, \n" +
                "    xzqhdj0_.EFFECTIVETIME     AS effectiv5_22_0_, \n" +
                "    xzqhdj0_.OPERATOR          AS operator6_22_0_, \n" +
                "    xzqhdj0_.OPERATECODE       AS operatec7_22_0_, \n" +
                "    xzqhdj0_.OPERATENAME       AS operaten8_22_0_, \n" +
                "    xzqhdj0_.OPERATEKIND       AS operatek9_22_0_, \n" +
                "    xzqhdj0_.IDENTITYNUM       AS identit10_22_0_, \n" +
                "    xzqhdj0_.PHONENUM          AS phonenu11_22_0_, \n" +
                "    xzqhdj0_.REMARK            AS remark12_22_0_, \n" +
                "    xzqhdj0_.OLDKIND           AS oldkind13_22_0_, \n" +
                "    xzqhdj0_.SUGGESTCODE       AS suggest14_22_0_, \n" +
                "    xzqhdj0_.VALIDTIME         AS validti15_22_0_, \n" +
                "    xzqhdj0_.INVALIDTIME       AS invalid16_22_0_, \n" +
                "    xzqhdj0_.TABLENAME         AS tablena17_22_0_, \n" +
                "    xzqhdj0_.BILLDEFINECODE    AS billdef18_22_0_, \n" +
                "    xzqhdj0_.RETURNCODE        AS returnc19_22_0_, \n" +
                "    xzqhdj0_.VERSIONTITLE      AS version20_22_0_, \n" +
                "    xzqhdj0_.OPERATEORGCODE    AS operate21_22_0_, \n" +
                "    xzqhdj0_.NEWKIND           AS newkind1_47_0_, \n" +
                "    xzqhdj0_.CHANGEREASON      AS changere2_47_0_, \n" +
                "    xzqhdj0_.LEGAL             AS legal3_47_0_, \n" +
                "    xzqhdj0_.PROCESSFIELD      AS processf4_47_0_, \n" +
                "    xzqhdj0_.ORGKIND           AS orgkind5_47_0_, \n" +
                "    xzqhdj0_.AUDITKIND         AS auditkin6_47_0_, \n" +
                "    xzqhdj0_.CHANGETYPE        AS changety7_47_0_, \n" +
                "    xzqhdj0_.CATEGORYNAME      AS category8_47_0_, \n" +
                "    xzqhdj0_.BYOPERATECODE     AS byoperat9_47_0_, \n" +
                "    xzqhdj0_.MODELFULLNAME     AS modelfu10_47_0_, \n" +
                "    xzqhdj0_.OPERATEPARENTS    AS operate11_47_0_ \n" +
                "FROM \n" +
                "    XZQHDJ xzqhdj0_\n" +
                "    where xzqhdj0_.ID = '5e2dc401-dbb0-4c99-8bf8-877c385286fe'";
        return sql;
    }

    public static String sql2(){
        String sql = "SELECT \n" +
                "    paramexpre0_.master_id AS master_i2_9_0_, \n" +
                "    paramexpre0_.id        AS id1_9_0_, \n" +
                "    paramexpre0_.id        AS id1_9_1_, \n" +
                "    paramexpre0_.master_id AS master_i2_9_1_, \n" +
                "    paramexpre0_.prop_name AS prop_nam3_9_1_, \n" +
                "    paramexpre0_.prop_expr AS prop_exp4_9_1_ \n" +
                "FROM \n" +
                "    gms_workflow_param_expr paramexpre0_ \n" +
                "WHERE \n" +
                "    paramexpre0_.master_id='7cf24e86-6372-46a3-8f2d-234cd983cf5e' \n" +
                "ORDER BY \n" +
                "    paramexpre0_.prop_name";
        return sql;
    }

    public static String sql3(){
        String sql = "SELECT \n" +
                "    workflowbi0_.id                 AS id1_8_, \n" +
                "    workflowbi0_.biz_type           AS biz_type2_8_, \n" +
                "    workflowbi0_.biz_name           AS biz_name3_8_, \n" +
                "    workflowbi0_.biz_title          AS biz_titl4_8_, \n" +
                "    workflowbi0_.data_type          AS data_typ5_8_, \n" +
                "    workflowbi0_.workflow_name      AS workflow6_8_, \n" +
                "    workflowbi0_.workflow_published AS workflow7_8_, \n" +
                "    workflowbi0_.listener_class     AS listener8_8_, \n" +
                "    workflowbi0_.task_listener      AS task_lis9_8_, \n" +
                "    workflowbi0_.form_name          AS form_na10_8_, \n" +
                "    workflowbi0_.form_settings      AS form_se11_8_, \n" +
                "    workflowbi0_.expr_type          AS expr_ty12_8_, \n" +
                "    workflowbi0_.expr_env           AS expr_en13_8_, \n" +
                "    workflowbi0_.can_start_expr     AS can_sta14_8_, \n" +
                "    workflowbi0_.biz_key_expr       AS biz_key15_8_, \n" +
                "    workflowbi0_.task_title_expr    AS task_ti16_8_ \n" +
                "FROM \n" +
                "    gms_workflow_biz_define workflowbi0_ \n" +
                "WHERE \n" +
                "    workflowbi0_.biz_name='bill.updateDivision'";
        return sql;
    }

    public static String sql4(){
        String sql = "SELECT \n" +
                "    workflowbi_.id, \n" +
                "    workflowbi_.biz_type           AS biz_type2_8_, \n" +
                "    workflowbi_.biz_name           AS biz_name3_8_, \n" +
                "    workflowbi_.biz_title          AS biz_titl4_8_, \n" +
                "    workflowbi_.data_type          AS data_typ5_8_, \n" +
                "    workflowbi_.workflow_name      AS workflow6_8_, \n" +
                "    workflowbi_.workflow_published AS workflow7_8_, \n" +
                "    workflowbi_.listener_class     AS listener8_8_, \n" +
                "    workflowbi_.task_listener      AS task_lis9_8_, \n" +
                "    workflowbi_.form_name          AS form_na10_8_, \n" +
                "    workflowbi_.form_settings      AS form_se11_8_, \n" +
                "    workflowbi_.expr_type          AS expr_ty12_8_, \n" +
                "    workflowbi_.expr_env           AS expr_en13_8_, \n" +
                "    workflowbi_.can_start_expr     AS can_sta14_8_, \n" +
                "    workflowbi_.biz_key_expr       AS biz_key15_8_, \n" +
                "    workflowbi_.task_title_expr    AS task_ti16_8_ \n" +
                "FROM \n" +
                "    gms_workflow_biz_define workflowbi_ \n" +
                "WHERE \n" +
                "    workflowbi_.id='9f7eafd7-9602-4d6f-a753-429ed422c83c'";
        return sql;
    }
}
