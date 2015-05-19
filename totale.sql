--project.name=InsuranceWS

/*CREATE USER WEBUSER IDENTIFIED BY WEBUSER;
GRANT CREATE SESSION, GRANT ANY PRIVILEGE TO WEBUSER;
GRANT SELECT ON V_$SESSION TO WEBUSER;
GRANT ALL PRIVILEGES TO WEBUSER;*/

DROP TABLE COD_VERSION CASCADE CONSTRAINTS;
DROP TABLE COD_CIVILITY CASCADE CONSTRAINTS;
DROP TABLE CLI_CLIENT CASCADE CONSTRAINTS;
DROP TABLE CLI_ADDRESS CASCADE CONSTRAINTS;
DROP TABLE COD_COUNTRY CASCADE CONSTRAINTS;
DROP TABLE COD_ZIP CASCADE CONSTRAINTS;
DROP TABLE CLI_CONTRACT CASCADE CONSTRAINTS;
DROP TABLE COD_BRANCH CASCADE CONSTRAINTS;
DROP TABLE COD_CATEGORY CASCADE CONSTRAINTS;
DROP TABLE COD_CANCEL CASCADE CONSTRAINTS;
DROP TABLE CLI_GUARANTEE CASCADE CONSTRAINTS;
DROP TABLE COD_GUARANTEE CASCADE CONSTRAINTS;

CREATE TABLE COD_VERSION
( 
  CVERSION VARCHAR2(16) ,
  LVERSION VARCHAR2(256) ,
  DATEVERSION DATE,
  CONSTRAINT CVERSION_PK PRIMARY KEY (CVERSION)
);

CREATE TABLE COD_CIVILITY
( 
  CCIVIL VARCHAR2(2) ,
  LCIVIL VARCHAR2(64) ,
  INDVALI VARCHAR2 (1),
  CONSTRAINT CCIVIL_PK PRIMARY KEY (CCIVIL)
);

CREATE TABLE CLI_CLIENT
( 
  NUMCLI NUMBER(8) ,
  CCIVIL VARCHAR2(2),
  NAME VARCHAR2(128),
  FIRSTNAME VARCHAR2(128),
  COMPANYNAME VARCHAR2(128),
  CONSTRAINT CLICLIENT_PK PRIMARY KEY (NUMCLI)
);

CREATE TABLE CLI_ADDRESS
( 
  NUMADDRESS NUMBER(8),
  NUMCLI NUMBER(8),
  STREET1 VARCHAR2(128),
  STREET2 VARCHAR2(128),
  STREET3 VARCHAR2(128),
  STREET4 VARCHAR2(128),
  ZIPCODE VARCHAR2(5),
  CITY VARCHAR2(128),
  CCOUNTRY VARCHAR2(3),
  CONSTRAINT CLIADDRESS_PK PRIMARY KEY (NUMADDRESS)
);

CREATE TABLE COD_COUNTRY
( 
  CCOUNTRY VARCHAR2(3),
  LCOUNTRY VARCHAR2(32),
  INDVALI VARCHAR2(1),
  CONSTRAINT CODCOUNTRY_PK PRIMARY KEY (CCOUNTRY)
);

CREATE TABLE COD_ZIP
( 
  ZIPCODE VARCHAR2(5),
  CITY VARCHAR2(128),
  CCOUNTRY VARCHAR2(3),
  INDVALI VARCHAR2(1),
  CONSTRAINT CODZIP_PK PRIMARY KEY (ZIPCODE, CITY, CCOUNTRY)
);

CREATE TABLE CLI_CONTRACT
( 
  NUMCLI NUMBER(8),
  NUMCON NUMBER(3),
  CBRANCH VARCHAR2(5),
  CCATEG VARCHAR2(5),
  STARTDATE DATE,
  ENDDATE DATE,
  CCANCEL VARCHAR2(2),
  CONSTRAINT CLICONTRACT_PK PRIMARY KEY (NUMCLI, NUMCON)
);

CREATE TABLE COD_BRANCH
( 
  CBRANCH VARCHAR2(5),
  LBRANCH VARCHAR2(128),
  INDVALI VARCHAR2(1),
  CONSTRAINT CODBRANCH_PK PRIMARY KEY (CBRANCH)
);

CREATE TABLE COD_CATEGORY
( 
  CCATEG VARCHAR2(5),
  LCATEG VARCHAR2(128),
  INDVALI VARCHAR2(1),
  CONSTRAINT CODCATEG_PK PRIMARY KEY (CCATEG)
);

CREATE TABLE COD_CANCEL
( 
  CCANCEL VARCHAR2(3),
  LCANCEL VARCHAR2(128),
  INDVALI VARCHAR2(1),
  CONSTRAINT CODCANCEL_PK PRIMARY KEY (CCANCEL)
);

CREATE TABLE CLI_GUARANTEE
( 
  NUMGUARANTEE NUMBER(8),
  NUMCLI NUMBER(8),
  NUMCON NUMBER(3),
  CGUARANTEE VARCHAR2(5),
  AMOUNT NUMBER,
  STARTVAL DATE,
  ENDVAL DATE,
  CONSTRAINT CLIGUARANTEE_PK PRIMARY KEY (NUMGUARANTEE)
);

CREATE TABLE COD_GUARANTEE
( 
  CGUARANTEE VARCHAR2(3),
  LGUARANTEE VARCHAR2(128),
  INDVALI VARCHAR2(1),
  CONSTRAINT CODGUARANTEE_PK PRIMARY KEY (CGUARANTEE)
);

alter table CLI_CLIENT ADD CONSTRAINT FK_CLIENTCCIVIL FOREIGN KEY (CCIVIL) REFERENCES COD_CIVILITY(CCIVIL);
alter table CLI_ADDRESS ADD CONSTRAINT FK_ADDRNUMCLI FOREIGN KEY (NUMCLI) REFERENCES CLI_CLIENT(NUMCLI);
alter table CLI_ADDRESS ADD CONSTRAINT FK_ADDRCCOUNTRY FOREIGN KEY (CCOUNTRY) REFERENCES COD_COUNTRY(CCOUNTRY);
alter table COD_ZIP ADD CONSTRAINT FK_ZIPCCOUNTRY FOREIGN KEY (CCOUNTRY) REFERENCES COD_COUNTRY(CCOUNTRY);
alter table CLI_ADDRESS ADD CONSTRAINT FK_ADDRZIPCITY FOREIGN KEY (ZIPCODE, CITY, CCOUNTRY) REFERENCES COD_ZIP(ZIPCODE, CITY, CCOUNTRY);
alter table CLI_CONTRACT ADD CONSTRAINT FK_CONTRACTNUMCLI FOREIGN KEY (NUMCLI) REFERENCES CLI_CLIENT(NUMCLI);
alter table CLI_GUARANTEE ADD CONSTRAINT FK_GUARCONTRACT FOREIGN KEY (NUMCLI, NUMCON) REFERENCES CLI_CONTRACT(NUMCLI, NUMCON);
alter table CLI_GUARANTEE ADD CONSTRAINT FK_GUARCGUAR FOREIGN KEY (CGUARANTEE) REFERENCES COD_GUARANTEE(CGUARANTEE);
alter table CLI_CONTRACT ADD CONSTRAINT FK_CONTRACTCCATEG FOREIGN KEY (CCATEG) REFERENCES COD_CATEGORY(CCATEG);
alter table CLI_CONTRACT ADD CONSTRAINT FK_CONTRACTCBRANCH FOREIGN KEY (CBRANCH) REFERENCES COD_BRANCH(CBRANCH);
alter table CLI_CONTRACT ADD CONSTRAINT FK_CONTRACTCANCEL FOREIGN KEY (CCANCEL) REFERENCES COD_CANCEL(CCANCEL);

INSERT INTO COD_VERSION(CVERSION, LVERSION, DATEVERSION) VALUES ('0.0.1', 'POC 0.0.1', sysdate);
INSERT INTO COD_CIVILITY(CCIVIL, LCIVIL, INDVALI) VALUES ('01', 'MONSIEUR', '1');
INSERT INTO COD_CIVILITY(CCIVIL, LCIVIL, INDVALI) VALUES ('02', 'MADAME', '1');
INSERT INTO COD_COUNTRY(CCOUNTRY,LCOUNTRY,INDVALI) VALUES ('FRA', 'FRANCE', '1');
INSERT INTO COD_ZIP(ZIPCODE, CITY, CCOUNTRY, INDVALI) values ('75015', 'PARIS', 'FRA', '1');
INSERT INTO COD_BRANCH(CBRANCH, LBRANCH, INDVALI) values ('B01', 'BRANCHE1', '1');
INSERT INTO COD_CATEGORY(CCATEG, LCATEG, INDVALI) values ('C01', 'CATEG1', '1');


