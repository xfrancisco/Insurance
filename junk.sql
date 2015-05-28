CREATE TABLE COD_BRANCHCATEG
( 
  CBRANCH VARCHAR2(5),
  CCATEGORY VARCHAR2(5),
  CONSTRAINT CODBRANCHCATEG_PK PRIMARY KEY (CBRANCH,CCATEGORY)
);

CREATE TABLE COD_CATEGSECTION
( 
  CCATEGORY VARCHAR2(5),
  CSECTION VARCHAR2(5),
  CONSTRAINT CODCATEGSECTION_PK PRIMARY KEY (CCATEGORY,CSECTION)
);


CREATE TABLE COD_SECTIONGUARANTEE
( 
  CSECTION VARCHAR2(5),
  CGUARANTEE VARCHAR2(5),
  CONSTRAINT CODSECTIONGUARANTEE_PK PRIMARY KEY (CSECTION,CGUARANTEE)
);


CREATE TABLE COD_GUARANTEEPREMIUM
( 
  CGUARANTEE VARCHAR2(5),
  CPREMIUM VARCHAR2(5),
  CONSTRAINT CODGUARANTEEPREMIUM_PK PRIMARY KEY (CGUARANTEE,CPREMIUM)
);

alter table COD_BRANCHCATEG ADD CONSTRAINT FK_BCCATEG FOREIGN KEY (CCATEGORY) REFERENCES COD_CATEGORY(CCATEGORY);
alter table COD_BRANCHCATEG ADD CONSTRAINT FK_BCBRANCH FOREIGN KEY (CBRANCH) REFERENCES COD_BRANCH(CBRANCH);
alter table COD_CATEGSECTION ADD CONSTRAINT FK_CSCATEG FOREIGN KEY (CCATEGORY) REFERENCES COD_CATEGORY(CCATEGORY);
alter table COD_CATEGSECTION ADD CONSTRAINT FK_CSSECTION FOREIGN KEY (CSECTION) REFERENCES COD_SECTION(CSECTION);
alter table COD_SECTIONGUARANTEE ADD CONSTRAINT FK_SGSECTION FOREIGN KEY (CSECTION) REFERENCES COD_SECTION(CSECTION);
alter table COD_SECTIONGUARANTEE ADD CONSTRAINT FK_SGGUARANTEE FOREIGN KEY (CGUARANTEE) REFERENCES COD_GUARANTEE(CGUARANTEE);
alter table COD_GUARANTEEPREMIUM ADD CONSTRAINT FK_GPPREMIUM FOREIGN KEY (CPREMIUM) REFERENCES COD_PREMIUM(CPREMIUM);
alter table COD_GUARANTEEPREMIUM ADD CONSTRAINT FK_GPGUARANTEE FOREIGN KEY (CGUARANTEE) REFERENCES COD_GUARANTEE(CGUARANTEE);