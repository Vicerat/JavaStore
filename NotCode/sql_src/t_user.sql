CREATE DATABASE store character SET utf8;

CREATE TABLE t_user (
	uid INT AUTO_INCREMENT COMMENT '�û�id',
	username VARCHAR(20) NOT NULL UNIQUE COMMENT '�û���',
	password CHAR(32) NOT NULL COMMENT '����',
	salt CHAR(36) COMMENT '��ֵ',
	phone VARCHAR(20) COMMENT '�绰����',
	email VARCHAR(30) COMMENT '��������',
	gender INT COMMENT '�Ա�:0-Ů��1-��',
	avatar VARCHAR(50) COMMENT 'ͷ��',
	is_delete INT COMMENT '�Ƿ�ɾ����0-δɾ����1-��ɾ��',
	created_user VARCHAR(20) COMMENT '��־-������',
	created_time DATETIME COMMENT '��־-����ʱ��',
	modified_user VARCHAR(20) COMMENT '��־-����޸�ִ����',
	modified_time DATETIME COMMENT '��־-����޸�ʱ��',
	PRIMARY KEY (uid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE t_address (
	aid INT AUTO_INCREMENT COMMENT '�ջ���ַid',
	uid INT COMMENT '�������û�id',
	name VARCHAR(20) COMMENT '�ջ�������',
	province_name VARCHAR(15) COMMENT 'ʡ-����',
	province_code CHAR(6) COMMENT 'ʡ-��������',
	city_name VARCHAR(15) COMMENT '��-����',
	city_code CHAR(6) COMMENT '��-��������',
	area_name VARCHAR(15) COMMENT '��-����',
	area_code CHAR(6) COMMENT '��-��������',
	zip CHAR(6) COMMENT '��������',
	address VARCHAR(50) COMMENT '��ϸ��ַ',
	phone VARCHAR(20) COMMENT '�ֻ�',
	tel VARCHAR(20) COMMENT '�̻�',
	tag VARCHAR(6) COMMENT '��ǩ',
	is_default INT COMMENT '�Ƿ�Ĭ�ϣ�0-��Ĭ�ϣ�1-Ĭ��',
	created_user VARCHAR(20) COMMENT '������',
	created_time DATETIME COMMENT '����ʱ��',
	modified_user VARCHAR(20) COMMENT '�޸���',
	modified_time DATETIME COMMENT '�޸�ʱ��',
	PRIMARY KEY (aid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;