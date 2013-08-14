package com.hodanet.sanre.common.constant;

/**
 * <pre>
 * ͨ�ó�������
 * </pre>
 */
public class CommonConstant {

    public static final String VIRTUAL_ID             = "vid";
    public static final String USER_ID                = "uid";
    public static final String USER_STATUS            = "status";

    /** �������-�ɹ� 1 */
    public static final int    RESULT_SUCCESS         = 0x01;
    /** �������-ʧ�� 0 */
    public static final int    RESULT_FAIL            = 0x0;
    /** ����-����Ϊ�� */
    public static final int    RESULT_PARAM_EMPTY     = 0x11;
    /** ����-�û������������ */
    public static final int    RESULT_PARAM_ERROR     = 0x12;
    /** ����-�û�����ռ�� */
    public static final int    RESULT_PARAM_EXIST     = 0x13;
    /** �������-�����쳣 -1 */
    public static final int    RESULT_NETWORK_FAIL    = 0xffffffff;

    public static final int    RESULT_EXIST           = 0X09;

    public static final int    RESULT_GETFREEULR      = 0X10;

    /** �ɼ� */
    public static final int    VISIBILITY_VISIBLE     = 0;
    /** ���ɼ� */
    public static final int    VISIBILITY_INVISIBLE   = 4;
    /** ���ɼ����Ҳ�ռ�ò��ֿռ� */
    public static final int    VISIBILITY_GONE        = 8;

    /** δ��¼״̬ */
    public static final String STATUS_LOGIN           = "1";
    /** δ��¼״̬ */
    public static final String STATUS_UNLOGIN         = "0";

    /** ��ʾ�� ���ظ��� */
    public static final String REMIND_TEXT_LOAD_MORE  = "���ظ���";
    /** ��ʾ�� �Ѽ���ȫ�� */
    public static final String REMIND_TEXT_LOADED_ALL = "�Ѽ���ȫ��";
    /** �Һŷ� ��λ */
    public static final String RES_TEXT_PRICE_UNIT    = "Ԫ";
    /** ���ź��� */
    public static final String SMS_PHONE_NUMBER       = "10659128";
    /** ��Ʒ�ṩ�� */
    public static final String PRODUCT_PROVIDER       = "���ݺ�����緢չ���޹�˾";
    /** �ͷ��绰 */
    public static final String SERVICE_PHONE          = "95105196";
    /** ��˾��վ */
    public static final String COMPANY_URL            = "http://www.hodanet.com";

    /** ����ǽ���� 0.�ر� 1.���� */
    public static int          ADWALL_ONOFF           = 0;
    public static int          ADWALL_DEFAULTSCORE    = 100;

    public static final int    RESULT_GETADWALL_ONOFF = 0X13;

    /** ����ǽ���id ����idΪ 0f470c7647f9a7b0550d562638fcb577 */
    public static final String ADWALL_ADUNITID        = "69eb6350fbcea228dc3d817656e662b8";
}
