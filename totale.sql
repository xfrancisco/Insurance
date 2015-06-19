--project.name=InsuranceWS

/*CREATE USER WEBUSER IDENTIFIED BY WEBUSER;
GRANT CREATE SESSION, GRANT ANY PRIVILEGE TO WEBUSER;
GRANT SELECT ON V_$SESSION TO WEBUSER;
GRANT ALL PRIVILEGES TO WEBUSER;*/


/*select * from all_objects where owner =  'WEBUSER';
DROP TABLE COD_VERSION CASCADE CONSTRAINTS;
DROP TABLE COD_CIVILITY CASCADE CONSTRAINTS;
DROP TABLE CLI_CLIENT CASCADE CONSTRAINTS;
DROP TABLE CLI_ADDRESS CASCADE CONSTRAINTS;
DROP TABLE COD_COUNTRY CASCADE CONSTRAINTS;
DROP TABLE CLI_CONTRACT CASCADE CONSTRAINTS;
DROP TABLE COD_BRANCH CASCADE CONSTRAINTS;
DROP TABLE COD_CATEGORY CASCADE CONSTRAINTS;
DROP TABLE COD_CANCEL CASCADE CONSTRAINTS;
DROP TABLE CLI_GUARANTEE CASCADE CONSTRAINTS;
DROP TABLE COD_GUARANTEE CASCADE CONSTRAINTS;
DROP TABLE COD_SECTION CASCADE CONSTRAINTS;
DROP TABLE COD_TAX CASCADE CONSTRAINTS;
DROP TABLE COD_PREMIUM CASCADE CONSTRAINTS;
DROP TABLE COD_PREMIUMCONFIG CASCADE CONSTRAINTS;
DROP TABLE COD_CATCLI CASCADE CONSTRAINTS;
DROP TABLE USR_USER CASCADE CONSTRAINTS;
DROP TABLE USR_ROLE CASCADE CONSTRAINTS;
DROP TABLE COD_MOVEMENT CASCADE CONSTRAINTS;
DROP TABLE COD_MOVEMENTDET CASCADE CONSTRAINTS;
DROP TABLE CLI_MOVEMENT CASCADE CONSTRAINTS;
DROP TABLE CLI_MOVEMENTDET CASCADE CONSTRAINTS;
DROP TABLE COD_EMAIL CASCADE CONSTRAINTS;
DROP TABLE COD_ADDRESS CASCADE CONSTRAINTS;
DROP TABLE COD_PHONE CASCADE CONSTRAINTS;
DROP TABLE COD_DURATION CASCADE CONSTRAINTS;
DROP TABLE CLI_EMAIL CASCADE CONSTRAINTS;
DROP TABLE CLI_PHONE CASCADE CONSTRAINTS;
DROP TABLE CLI_CATCLI CASCADE CONSTRAINTS;
DROP TABLE CLI_QUOTE CASCADE CONSTRAINTS;
DROP TABLE COD_TABLE CASCADE CONSTRAINTS;
DROP TABLE COD_QUOTESTATUS  CASCADE CONSTRAINTS;

DROP SEQUENCE NUMGUARANTEE_SEQ;
DROP SEQUENCE NUMCLI_SEQ;
DROP SEQUENCE NUMADDRESS_SEQ;
DROP SEQUENCE NUMMOVEMENT_SEQ;
DROP SEQUENCE NUMMOVEMENTDET_SEQ;
DROP SEQUENCE NUMCLICATCLI_SEQ;
DROP SEQUENCE NUMPHONE_SEQ;
DROP SEQUENCE NUMEMAIL_SEQ;

*/
CREATE TABLE COD_VERSION
( 
  CVERSION VARCHAR2(16) NOT NULL,
  LVERSION VARCHAR2(256) NOT NULL ,
  VERSIONDATE DATE DEFAULT sysdate NOT NULL,
  CONSTRAINT CVERSION_PK PRIMARY KEY (CVERSION)
);

INSERT INTO COD_VERSION(CVERSION, LVERSION, VERSIONDATE) VALUES ('0.0.1', 'POC 0.0.1', sysdate);

comment on table COD_VERSION is 'Table des versions applicatives';
comment on column COD_VERSION.CVERSION is 'Code de la version';
comment on column COD_VERSION.LVERSION is 'Libelle de la version';
comment on column COD_VERSION.VERSIONDATE is 'Date de l''installation de la version';


/*****************************************************************************************************************************************************************/
/*****************************************************************************************************************************************************************/
/******************************************************** COORDONNEES ********************************************************************************************/
/*****************************************************************************************************************************************************************/
/*****************************************************************************************************************************************************************/

/* ADDRESSE*/
CREATE TABLE COD_ADDRESS
( 
  CADDRESS VARCHAR2(8) NOT NULL,
  LADDRESS VARCHAR2(128) NOT NULL,
  INDDEFAULT VARCHAR2(1) DEFAULT '1' NOT NULL,
  INDVALI VARCHAR2(1) DEFAULT '1' NOT NULL,
  CONSTRAINT CODADDRESS_PK PRIMARY KEY (CADDRESS)
);

insert into COD_ADDRESS(CADDRESS, LADDRESS, INDDEFAULT, INDVALI) values ('MAIN', 'ADRESSE PRINCIPALE', '1', '1');

comment on table COD_ADDRESS is 'Table des types d''addresse';
comment on column COD_ADDRESS.CADDRESS is 'Code du type';
comment on column COD_ADDRESS.LADDRESS is 'Libelle du type';
comment on column COD_ADDRESS.INDDEFAULT is 'Indicateur de type par defaut. Si a 1 alors type par defaut';
comment on column COD_ADDRESS.INDVALI is 'Indicateur de validite de l''enregistrement. Si a 1 alors valide';

/* EMAIL*/
CREATE TABLE COD_EMAIL
( 
  CEMAIL VARCHAR2(8) NOT NULL,
  LEMAIL VARCHAR2(128) NOT NULL,
  PATTERN VARCHAR2(128) NOT NULL,
  INDDEFAULT VARCHAR2(1) DEFAULT '1' NOT NULL,
  INDVALI VARCHAR2(1) DEFAULT '1' NOT NULL,
  CONSTRAINT CODEMAIL_PK PRIMARY KEY (CEMAIL)
);

insert into COD_EMAIL(CEMAIL, LEMAIL, PATTERN, INDDEFAULT, INDVALI) values ('MAIN', 'EMAIL PRINCIPAL', '^[_A-Za-z0-9-]+(\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\.[A-Za-z0-9-]+)+$', '1', '1');
insert into COD_EMAIL(CEMAIL, LEMAIL, PATTERN, INDDEFAULT, INDVALI) values ('FACEBOOK', 'COMPTE FACEBOOK', '^[_A-Za-z0-9-]+(\.[_A-Za-z0-9-]+)*@[fF][Aa][Cc][Ee][Bb][Oo][Oo][Kk]\.[Cc][Oo][Mm]$', '0', '0');
insert into COD_EMAIL(CEMAIL, LEMAIL, PATTERN, INDDEFAULT, INDVALI) values ('TWITTER', 'COMPTE TWITTER', '^@[_A-Za-z0-9-]+(\.[_A-Za-z0-9-]+)*$', '0', '0');

comment on table COD_EMAIL is 'Table des types d''email';
comment on column COD_EMAIL.CEMAIL is 'Code du type';
comment on column COD_EMAIL.LEMAIL is 'Libelle du type';
comment on column COD_EMAIL.PATTERN is 'Pattern du format du type. Utile pour les controles de surface';
comment on column COD_EMAIL.INDDEFAULT is 'Indicateur de type par defaut. Si a 1 alors type par defaut';
comment on column COD_EMAIL.INDVALI is 'Indicateur de validite de l''enregistrement. Si a 1 alors valide';


/* PHONES*/
CREATE TABLE COD_PHONE
( 
  CPHONE VARCHAR2(8) NOT NULL,
  LPHONE VARCHAR2(128) NOT NULL,
  PATTERN VARCHAR2(128),
  INDMOBILE VARCHAR2(1) DEFAULT '1' NOT NULL,
  INDDEFAULT VARCHAR2(1) DEFAULT '1' NOT NULL,
  INDVALI VARCHAR2(1) DEFAULT '1' NOT NULL,
  CONSTRAINT CODPHONE_PK PRIMARY KEY (CPHONE)
);

INSERT INTO COD_PHONE(CPHONE, LPHONE, PATTERN, INDMOBILE, INDDEFAULT, INDVALI) values ('WORK', 'TELEPHONE', NULL, '0', '1', '1');
INSERT INTO COD_PHONE(CPHONE, LPHONE, PATTERN, INDMOBILE, INDDEFAULT, INDVALI) values ('MOBILE', 'PORTABLE', NULL, '1', '1', '1');


comment on table COD_PHONE is 'Table des types de telephones';
comment on column COD_PHONE.CPHONE is 'Code du type';
comment on column COD_PHONE.LPHONE is 'Libelle du type';
comment on column COD_PHONE.PATTERN is 'Pattern du format du type. Utile pour les controles de surface';
comment on column COD_PHONE.INDMOBILE is 'Indicateur telephone portable. Si a 1 alors telephone portable';
comment on column COD_PHONE.INDDEFAULT is 'Indicateur de type par defaut. Si a 1 alors type par defaut';
comment on column COD_PHONE.INDVALI is 'Indicateur de validite de l''enregistrement. Si a 1 alors valide';


/*CIVILITES */
CREATE TABLE COD_CIVILITY
( 
  CCIVIL VARCHAR2(2) NOT NULL,
  LCIVIL VARCHAR2(64) NOT NULL,
  INDVALI VARCHAR2(1) DEFAULT '1' NOT NULL,
  CONSTRAINT CCIVIL_PK PRIMARY KEY (CCIVIL)
);

INSERT INTO COD_CIVILITY(CCIVIL, LCIVIL, INDVALI) VALUES ('01', 'MONSIEUR', '1');
INSERT INTO COD_CIVILITY(CCIVIL, LCIVIL, INDVALI) VALUES ('02', 'MADAME', '1');
INSERT INTO COD_CIVILITY(CCIVIL, LCIVIL, INDVALI) VALUES ('03', 'SOCIETE', '1');
INSERT INTO COD_CIVILITY(CCIVIL, LCIVIL, INDVALI) VALUES ('04', 'SOCIETE SA', '1');

comment on table COD_CIVILITY is 'Table des civilites';
comment on column COD_CIVILITY.CCIVIL is 'Code';
comment on column COD_CIVILITY.LCIVIL is 'Libelle';
comment on column COD_CIVILITY.INDVALI is 'Indicateur de validite de l''enregistrement. Si a 1 alors valide';

/*PAYS*/
CREATE TABLE COD_COUNTRY
( 
  CCOUNTRY VARCHAR2(3) NOT NULL,
  LCOUNTRY VARCHAR2(32) NOT NULL,
  INDFOREIGN VARCHAR2(1) DEFAULT '0' NOT NULL,
  INDDEFAULT VARCHAR2(1) DEFAULT '0' NOT NULL,
  INDVALI VARCHAR2(1) DEFAULT '1' NOT NULL,
  CONSTRAINT CODCOUNTRY_PK PRIMARY KEY (CCOUNTRY)
);

INSERT INTO COD_COUNTRY(CCOUNTRY,LCOUNTRY,INDFOREIGN, INDDEFAULT, INDVALI) VALUES ('FRA', 'FRANCE', '0', '1', '1');
INSERT INTO COD_COUNTRY(CCOUNTRY,LCOUNTRY,INDFOREIGN, INDDEFAULT, INDVALI) VALUES ('MON', 'MONACO', '0', '0', '1');

comment on table COD_COUNTRY is 'Table des pays';
comment on column COD_COUNTRY.CCOUNTRY is 'Code';
comment on column COD_COUNTRY.LCOUNTRY is 'Libelle';
comment on column COD_COUNTRY.INDFOREIGN is 'Indicateur. Si a 1 alors pays etranger';
comment on column COD_COUNTRY.INDFOREIGN is 'Indicateur. Si a 1 alors pays par defaut';
comment on column COD_COUNTRY.INDVALI is 'Indicateur de validite de l''enregistrement. Si a 1 alors valide';

/*CODES POSTAUX*/
CREATE TABLE COD_POSTAL
( 
  CPOSTAL VARCHAR2(5) NOT NULL,
  CITY VARCHAR2(128) NOT NULL,
  CCOUNTRY VARCHAR2(3) NOT NULL,
  INDVALI VARCHAR2(1) DEFAULT '1' NOT NULL,
  CONSTRAINT CODPOSTAL_PK PRIMARY KEY (CPOSTAL, CITY, CCOUNTRY)
);

CREATE INDEX IDX_CITYCOUNTRY ON COD_POSTAL (CITY,CCOUNTRY);
CREATE INDEX IDX_CPOSTALCOUNTRY ON COD_POSTAL (CPOSTAL,CCOUNTRY);

comment on table COD_POSTAL is 'Table des codes postaux';
comment on column COD_POSTAL.CPOSTAL is 'Code postal';
comment on column COD_POSTAL.CITY is 'Ville ou commune';
comment on column COD_POSTAL.CCOUNTRY is 'Code pays. Reference COD_COUNTRY';
comment on column COD_POSTAL.INDVALI is 'Indicateur de validite de l''enregistrement. Si a 1 alors valide';

/*CATEG CLIENT*/
CREATE TABLE COD_CATCLI
( 
  CCATCLI VARCHAR2(5) NOT NULL,
  LCATCLI VARCHAR2(128) NOT NULL,
  INDVALI VARCHAR2(1) DEFAULT '1' NOT NULL,
  INDCLIENT VARCHAR2(1) DEFAULT '0' NOT NULL ,
  INDINSURANCE VARCHAR2(1) DEFAULT '0' NOT NULL,
  INDREINSURANCE VARCHAR2(1) DEFAULT '0' NOT NULL,
  INDBROKER VARCHAR2(1) DEFAULT '0' NOT NULL,
  INDEXPERT VARCHAR2(1) DEFAULT '0' NOT NULL,
  INDLAWYER VARCHAR2(1) DEFAULT '0' NOT NULL,
  INDBENEF VARCHAR2(1) DEFAULT '0' NOT NULL,
  INDTIERS VARCHAR2(1) DEFAULT '0' NOT NULL,
  INDAGENCY VARCHAR2(1) DEFAULT '0' NOT NULL,
  CONSTRAINT CODCATCLI_PK PRIMARY KEY (CCATCLI)
);

INSERT INTO COD_CATCLI(CCATCLI, LCATCLI, INDCLIENT, INDINSURANCE, INDREINSURANCE, INDBROKER, INDEXPERT, INDLAWYER, INDBENEF, INDTIERS, INDVALI, INDAGENCY) values ('CLI', 'CLIENT', '1', '0', '0', '0', '0', '0', '0', '0', '1', '0');
INSERT INTO COD_CATCLI(CCATCLI, LCATCLI, INDCLIENT, INDINSURANCE, INDREINSURANCE, INDBROKER, INDEXPERT, INDLAWYER, INDBENEF, INDTIERS, INDVALI, INDAGENCY) values ('INS', 'ASSUREUR/COASSUREUR', '0', '1', '0', '0', '0', '0', '0', '0', '1', '0');
INSERT INTO COD_CATCLI(CCATCLI, LCATCLI, INDCLIENT, INDINSURANCE, INDREINSURANCE, INDBROKER, INDEXPERT, INDLAWYER, INDBENEF, INDTIERS, INDVALI, INDAGENCY) values ('REI', 'REASSUREUR', '0', '0', '1', '0', '0', '0', '0', '0', '1', '0');
INSERT INTO COD_CATCLI(CCATCLI, LCATCLI, INDCLIENT, INDINSURANCE, INDREINSURANCE, INDBROKER, INDEXPERT, INDLAWYER, INDBENEF, INDTIERS, INDVALI, INDAGENCY) values ('BRO', 'COURTIER', '0', '0', '0', '1', '0', '0', '0', '0', '1', '0');
INSERT INTO COD_CATCLI(CCATCLI, LCATCLI, INDCLIENT, INDINSURANCE, INDREINSURANCE, INDBROKER, INDEXPERT, INDLAWYER, INDBENEF, INDTIERS, INDVALI, INDAGENCY) values ('EXP', 'EXPERT', '0', '0', '0', '0', '1', '0', '0', '0', '1', '0');
INSERT INTO COD_CATCLI(CCATCLI, LCATCLI, INDCLIENT, INDINSURANCE, INDREINSURANCE, INDBROKER, INDEXPERT, INDLAWYER, INDBENEF, INDTIERS, INDVALI, INDAGENCY) values ('LAW', 'AVOCAT', '0', '0', '0', '0', '0', '1', '0', '0', '1', '0');
INSERT INTO COD_CATCLI(CCATCLI, LCATCLI, INDCLIENT, INDINSURANCE, INDREINSURANCE, INDBROKER, INDEXPERT, INDLAWYER, INDBENEF, INDTIERS, INDVALI, INDAGENCY) values ('BEN', 'BENEFICIAIRE', '0', '0', '0', '0', '0', '0', '1', '0', '1', '0');
INSERT INTO COD_CATCLI(CCATCLI, LCATCLI, INDCLIENT, INDINSURANCE, INDREINSURANCE, INDBROKER, INDEXPERT, INDLAWYER, INDBENEF, INDTIERS, INDVALI, INDAGENCY) values ('TIE', 'TIERS', '0', '0', '0', '0', '0', '0', '0', '1', '1', '0');
INSERT INTO COD_CATCLI(CCATCLI, LCATCLI, INDCLIENT, INDINSURANCE, INDREINSURANCE, INDBROKER, INDEXPERT, INDLAWYER, INDBENEF, INDTIERS, INDVALI, INDAGENCY) values ('AGE', 'AGENCE DE SOUSCRIPTION', '0', '0', '0', '0', '0', '0', '0', '0', '1', '1');

comment on table COD_CATCLI is 'Table des categories d''individus';
comment on column COD_CATCLI.CCATCLI is 'Code';
comment on column COD_CATCLI.LCATCLI is 'Libelle';
comment on column COD_CATCLI.INDCLIENT is 'Indicateur. Si a 1, individu de type client';
comment on column COD_CATCLI.INDINSURANCE is 'Indicateur. Si a 1, individu de type assureur';
comment on column COD_CATCLI.INDREINSURANCE is 'Indicateur. Si a 1, individu de type reassureur';
comment on column COD_CATCLI.INDBROKER is 'Indicateur. Si a 1, individu de type courtier/agence';
comment on column COD_CATCLI.INDEXPERT is 'Indicateur. Si a 1, individu de type expert';
comment on column COD_CATCLI.INDLAWYER is 'Indicateur. Si a 1, individu de type avocat';
comment on column COD_CATCLI.INDBENEF is 'Indicateur. Si a 1, individu de type beneficiaire';
comment on column COD_CATCLI.INDTIERS is 'Indicateur. Si a 1, individu de type tiers';
comment on column COD_CATCLI.INDVALI is 'Indicateur de validite de l''enregistrement. Si a 1 alors valide';



/*****************************************************************************************************************************************************************/
/*****************************************************************************************************************************************************************/
/******************************************************** MOUVEMENTS *********************************************************************************************/
/*****************************************************************************************************************************************************************/
/*****************************************************************************************************************************************************************/

create table COD_MOVEMENT
(
  CMOVEMENT     VARCHAR2(16) NOT NULL,
  LMOVEMENT     VARCHAR2(128) NOT NULL,
  INDVALI       VARCHAR2(1) NOT NULL,
  CONSTRAINT CODMOVEMENT_PK PRIMARY KEY (CMOVEMENT)
);

INSERT INTO COD_MOVEMENT(CMOVEMENT, LMOVEMENT, INDVALI) values ('NEWPERSON', 'NOUVEL INDIVIDU', '1');
INSERT INTO COD_MOVEMENT(CMOVEMENT, LMOVEMENT, INDVALI) values ('MODPERSON', 'MODIFICATION INDIVIDU', '1');
INSERT INTO COD_MOVEMENT(CMOVEMENT, LMOVEMENT, INDVALI) values ('NEWADDRESS', 'NOUVELLE ADRESSE', '1');
INSERT INTO COD_MOVEMENT(CMOVEMENT, LMOVEMENT, INDVALI) values ('MODADDRESS', 'MODIFICATION ADRESSE', '1');
INSERT INTO COD_MOVEMENT(CMOVEMENT, LMOVEMENT, INDVALI) values ('NEWCATCLI', 'NOUVELLE CATEGORIE INDIVIDU', '1');
INSERT INTO COD_MOVEMENT(CMOVEMENT, LMOVEMENT, INDVALI) values ('DELCATCLI', 'RETRAIT CATEGORIE INDIVIDU', '1');
INSERT INTO COD_MOVEMENT(CMOVEMENT, LMOVEMENT, INDVALI) values ('NEWPHONE', 'AJOUT D''UN NUMERO DE TELEPHONE', '1');
INSERT INTO COD_MOVEMENT(CMOVEMENT, LMOVEMENT, INDVALI) values ('DELPHONE', 'RETRAIT D''UN NUMERO DE TELEPHONE', '1');
INSERT INTO COD_MOVEMENT(CMOVEMENT, LMOVEMENT, INDVALI) values ('MODPHONE', 'MODIFICATION D''UN NUMERO DE TELEPHONE', '1');
INSERT INTO COD_MOVEMENT(CMOVEMENT, LMOVEMENT, INDVALI) values ('NEWMAIL', 'AJOUT D''UN EMAIL', '1');
INSERT INTO COD_MOVEMENT(CMOVEMENT, LMOVEMENT, INDVALI) values ('DELMAIL', 'RETRAIT D''UN EMAIL', '1');
INSERT INTO COD_MOVEMENT(CMOVEMENT, LMOVEMENT, INDVALI) values ('MODMAIL', 'MODIFICATION D''UN EMAIL', '1');


comment on table COD_MOVEMENT is 'Table des types de mouvements';
comment on column COD_MOVEMENT.CMOVEMENT is 'Code';
comment on column COD_MOVEMENT.LMOVEMENT is 'Libelle';
comment on column COD_MOVEMENT.INDVALI is 'Indicateur de validite de l''enregistrement. Si a 1 alors valide';

create table COD_MOVEMENTDET
(
  CCOLUMN       VARCHAR2(128) NOT NULL,
  LABEL         VARCHAR2(128) NOT NULL,
  VALUETABLE    VARCHAR2(64),
  VALUECOLUMN   VARCHAR2(64),
  CONSTRAINT CODMOVEMENTDET_PK PRIMARY KEY (CCOLUMN)
);

INSERT INTO COD_MOVEMENTDET(CCOLUMN, LABEL, VALUETABLE, VALUECOLUMN) values ('CCIVIL', 'CIVILITE', 'COD_CIVILITY', 'LCIVIL');
INSERT INTO COD_MOVEMENTDET(CCOLUMN, LABEL, VALUETABLE, VALUECOLUMN) values ('NAME', 'NOM', NULL, NULL);
INSERT INTO COD_MOVEMENTDET(CCOLUMN, LABEL, VALUETABLE, VALUECOLUMN) values ('FIRSTNAME', 'PRENOM', NULL, NULL);
INSERT INTO COD_MOVEMENTDET(CCOLUMN, LABEL, VALUETABLE, VALUECOLUMN) values ('COMPANYNAME', 'SOCIETE', NULL, NULL);
INSERT INTO COD_MOVEMENTDET(CCOLUMN, LABEL, VALUETABLE, VALUECOLUMN) values ('COMPANYID', 'SIRET', NULL, NULL);
INSERT INTO COD_MOVEMENTDET(CCOLUMN, LABEL, VALUETABLE, VALUECOLUMN) values ('STREET1', 'COMPLEMENT DE NOM', NULL, NULL);
INSERT INTO COD_MOVEMENTDET(CCOLUMN, LABEL, VALUETABLE, VALUECOLUMN) values ('STREET2', 'NUMERO ET NOM DE VOIE', NULL, NULL);
INSERT INTO COD_MOVEMENTDET(CCOLUMN, LABEL, VALUETABLE, VALUECOLUMN) values ('STREET3', 'COMPLEMENT D''ADRESSE', NULL, NULL);
INSERT INTO COD_MOVEMENTDET(CCOLUMN, LABEL, VALUETABLE, VALUECOLUMN) values ('STREET4', 'BP, LIEU-DIT', NULL, NULL);
INSERT INTO COD_MOVEMENTDET(CCOLUMN, LABEL, VALUETABLE, VALUECOLUMN) values ('CPOSTAL', 'CODE POSTAL', NULL, NULL);
INSERT INTO COD_MOVEMENTDET(CCOLUMN, LABEL, VALUETABLE, VALUECOLUMN) values ('CITY', 'VILLE', NULL, NULL);
INSERT INTO COD_MOVEMENTDET(CCOLUMN, LABEL, VALUETABLE, VALUECOLUMN) values ('CCOUNTRY', 'PAYS', 'COD_COUNTRY', 'LCOUNTRY');
INSERT INTO COD_MOVEMENTDET(CCOLUMN, LABEL, VALUETABLE, VALUECOLUMN) values ('CCATCLI', 'CATEGORIE INDIVIDU', 'COD_CATCLI', 'LCATCLI');
INSERT INTO COD_MOVEMENTDET(CCOLUMN, LABEL, VALUETABLE, VALUECOLUMN) values ('CPHONE', 'TYPE DE TELEPHONE', 'COD_PHONE', 'LPHONE');
INSERT INTO COD_MOVEMENTDET(CCOLUMN, LABEL, VALUETABLE, VALUECOLUMN) values ('CEMAIL', 'TYPE DE MAIL', 'COD_EMAIL', 'LEMAIL');
INSERT INTO COD_MOVEMENTDET(CCOLUMN, LABEL, VALUETABLE, VALUECOLUMN) values ('EMAIL', 'ADRESSE EMAIL', NULL, NULL);
INSERT INTO COD_MOVEMENTDET(CCOLUMN, LABEL, VALUETABLE, VALUECOLUMN) values ('PHONENUMBER', 'NUMERO DE TELEPHONE', NULL, NULL);
INSERT INTO COD_MOVEMENTDET(CCOLUMN, LABEL, VALUETABLE, VALUECOLUMN) values ('CADDRESS', 'TYPE D''ADRESSE', 'COD_ADDRESS', 'LADDRESS');

comment on table COD_MOVEMENTDET is 'Table des details de mouvements';
comment on column COD_MOVEMENTDET.CCOLUMN is 'Cle';
comment on column COD_MOVEMENTDET.LABEL is 'Libelle';
comment on column COD_MOVEMENTDET.VALUETABLE is 'Table ou trouver le libelle de la valeur de la cle';
comment on column COD_MOVEMENTDET.VALUECOLUMN is 'Colonne ou trouver le libelle de la valeur de la cle';


/*****************************************************************************************************************************************************************/
/*****************************************************************************************************************************************************************/
/******************************************************** PRIMES *********************************************************************************************/
/*****************************************************************************************************************************************************************/
/*****************************************************************************************************************************************************************/

CREATE TABLE COD_BRANCH
( 
  CBRANCH VARCHAR2(6),
  LBRANCH VARCHAR2(128) NOT NULL,
  INDVALI VARCHAR2(1) DEFAULT '1' NOT NULL,
  CONSTRAINT CODBRANCH_PK PRIMARY KEY (CBRANCH)
);

INSERT INTO COD_BRANCH(CBRANCH, LBRANCH, INDVALI) values ('IA', 'INDIVIDUELLE ACCIDENT', '1');
INSERT INTO COD_BRANCH(CBRANCH, LBRANCH, INDVALI) values ('RSP', 'RISQUES SPECIAUX', '1');

comment on table COD_BRANCH is 'Table des branches';
comment on column COD_BRANCH.CBRANCH is 'Code';
comment on column COD_BRANCH.LBRANCH is 'Libelle';
comment on column COD_BRANCH.INDVALI is 'Indicateur de validite de l''enregistrement. Si a 1 alors valide';

CREATE TABLE COD_CATEGORY
( 
  CCATEGORY VARCHAR2(6),
  LCATEGORY VARCHAR2(128) NOT NULL,
  INDVALI VARCHAR2(1) DEFAULT '1' NOT NULL,
  CONSTRAINT CODCATEG_PK PRIMARY KEY (CCATEGORY)
);

INSERT INTO COD_CATEGORY(CCATEGORY, LCATEGORY, INDVALI) values ('IAG', 'INDIVIDUELLE ACCIDENT GROUPE', '1');
INSERT INTO COD_CATEGORY(CCATEGORY, LCATEGORY, INDVALI) values ('IAP', 'INDIVIDUELLE ACCIDENT PARTICULIER', '1');
INSERT INTO COD_CATEGORY(CCATEGORY, LCATEGORY, INDVALI) values ('RSP', 'RISQUES SPECIAUX', '1');
INSERT INTO COD_CATEGORY(CCATEGORY, LCATEGORY, INDVALI) values ('ATMP', 'ACCIDENT DU TRAVAIL ET MALADIE PROFESSIONNELLE', '1');

comment on table COD_CATEGORY is 'Table des categories';
comment on column COD_CATEGORY.CCATEGORY is 'Code';
comment on column COD_CATEGORY.LCATEGORY is 'Libelle';
comment on column COD_CATEGORY.INDVALI is 'Indicateur de validite de l''enregistrement. Si a 1 alors valide';


CREATE TABLE COD_SECTION
( 
  CSECTION VARCHAR2(6),
  LSECTION VARCHAR2(128),
  INDVALI VARCHAR2(1) DEFAULT '1' NOT NULL,
  CONSTRAINT CODSECTION_PK PRIMARY KEY (CSECTION)
);

INSERT INTO COD_SECTION(CSECTION, LSECTION, INDVALI) values ('PEFI', 'PERTE FINANCIERE', '1');
INSERT INTO COD_SECTION(CSECTION, LSECTION, INDVALI) values ('DOMM', 'DOMMAGE', '1');

comment on table COD_SECTION is 'Table des sections';
comment on column COD_SECTION.CSECTION is 'Code';
comment on column COD_SECTION.LSECTION is 'Libelle';
comment on column COD_SECTION.INDVALI is 'Indicateur de validite de l''enregistrement. Si a 1 alors valide';

CREATE TABLE COD_GUARANTEE
( 
  CGUARANTEE VARCHAR2(6),
  LGUARANTEE VARCHAR2(128),
  INDVALI VARCHAR2(1) DEFAULT '1' NOT NULL,
  CONSTRAINT CODGUARANTEE_PK PRIMARY KEY (CGUARANTEE)
);

INSERT INTO COD_GUARANTEE(CGUARANTEE, LGUARANTEE, INDVALI) values ('DCG', 'DECES GROUPE', '1');
INSERT INTO COD_GUARANTEE(CGUARANTEE, LGUARANTEE, INDVALI) values ('IPG', 'I.P. GROUPE', '1');
INSERT INTO COD_GUARANTEE(CGUARANTEE, LGUARANTEE, INDVALI) values ('ASS', 'ASSISTANCE', '1');
INSERT INTO COD_GUARANTEE(CGUARANTEE, LGUARANTEE, INDVALI) values ('TRB', 'BAGAGES', '1');
INSERT INTO COD_GUARANTEE(CGUARANTEE, LGUARANTEE, INDVALI) values ('RCVP', 'RESPONSABILITE CIVILE VIE PRIVEE', '1');
INSERT INTO COD_GUARANTEE(CGUARANTEE, LGUARANTEE, INDVALI) values ('DCP', 'DECES PARTICULIER', '1');
INSERT INTO COD_GUARANTEE(CGUARANTEE, LGUARANTEE, INDVALI) values ('IPP', 'I.P. PARTICULIER', '1');
INSERT INTO COD_GUARANTEE(CGUARANTEE, LGUARANTEE, INDVALI) values ('ANN', 'ANNULATION', '1');
INSERT INTO COD_GUARANTEE(CGUARANTEE, LGUARANTEE, INDVALI) values ('TRE', 'TOUS RISQUES EXPOSITION', '1');
INSERT INTO COD_GUARANTEE(CGUARANTEE, LGUARANTEE, INDVALI) values ('ATMP', 'ACCIDENT DU TRAVAIL ET MALADIE PROFESSIONNELLE', '1');
INSERT INTO COD_GUARANTEE(CGUARANTEE, LGUARANTEE, INDVALI) values ('ASP', 'ASSISTANCE PSY', '1');

comment on table COD_GUARANTEE is 'Table des garanties';
comment on column COD_GUARANTEE.CGUARANTEE is 'Code';
comment on column COD_GUARANTEE.LGUARANTEE is 'Libelle';
comment on column COD_GUARANTEE.INDVALI is 'Indicateur de validite de l''enregistrement. Si a 1 alors valide';

CREATE TABLE COD_TAX
( 
  CTAX VARCHAR2(2),
  LTAX VARCHAR2(128),
  TAXVALUE NUMBER(3),
  INDVALI VARCHAR2(1) DEFAULT '1' NOT NULL,
  CONSTRAINT CODTAX_PK PRIMARY KEY (CTAX)
);



INSERT INTO COD_TAX(CTAX, LTAX, TAXVALUE, INDVALI) values ('00', 'TAUX TAXE 0%', 0, '1');
INSERT INTO COD_TAX(CTAX, LTAX, TAXVALUE, INDVALI) values ('09', 'TAUX TAXE 9%', 9, '1');

update COD_TAX set taxvalue = null; 
alter table cod_tax modify  TAXVALUE NUMBER(16,3);

update COD_TAX set taxvalue = 0 where ctax = '00';
update COD_TAX set taxvalue = 9 where ctax = '09';

comment on table COD_TAX is 'Table des taux de taxes';
comment on column COD_TAX.CTAX is 'Code';
comment on column COD_TAX.LTAX is 'Libelle';
comment on column COD_TAX.TAXVALUE is 'Pourcentage de la taxe';
comment on column COD_TAX.INDVALI is 'Indicateur de validite de l''enregistrement. Si a 1 alors valide';


CREATE TABLE COD_PREMIUM
( 
  CPREMIUM VARCHAR2(6),
  LPREMIUM VARCHAR2(128),
  CTAX VARCHAR2(5),
  INDVALI VARCHAR2(1) DEFAULT '1' NOT NULL,
  CONSTRAINT CODPREMIUM_PK PRIMARY KEY (CPREMIUM)
);
alter table COD_PREMIUM ADD CONSTRAINT FK_PREMIUMTAX FOREIGN KEY (CTAX) REFERENCES COD_TAX(CTAX);

INSERT INTO COD_PREMIUM(CPREMIUM, LPREMIUM, CTAX, INDVALI) values ('DCG00', 'DECES GROUPE 0%', '00', '1');
INSERT INTO COD_PREMIUM(CPREMIUM, LPREMIUM, CTAX, INDVALI) values ('DCG09', 'DECES GROUPE 9%', '09', '1');
INSERT INTO COD_PREMIUM(CPREMIUM, LPREMIUM, CTAX, INDVALI) values ('IPG00', 'I.P. GROUPE 0%', '00', '1');
INSERT INTO COD_PREMIUM(CPREMIUM, LPREMIUM, CTAX, INDVALI) values ('IPG09', 'I.P. GROUPE 9%', '09', '1');
INSERT INTO COD_PREMIUM(CPREMIUM, LPREMIUM, CTAX, INDVALI) values ('ASS00', 'ASSISTANCE 0%', '00', '1');
INSERT INTO COD_PREMIUM(CPREMIUM, LPREMIUM, CTAX, INDVALI) values ('ASS09', 'ASSISTANCE 9%', '09', '1');
INSERT INTO COD_PREMIUM(CPREMIUM, LPREMIUM, CTAX, INDVALI) values ('TRB00', 'BAGAGES 0%', '00', '1');
INSERT INTO COD_PREMIUM(CPREMIUM, LPREMIUM, CTAX, INDVALI) values ('TRB09', 'BAGAGES 9%', '09', '1');
INSERT INTO COD_PREMIUM(CPREMIUM, LPREMIUM, CTAX, INDVALI) values ('RCVP00', 'RESPONSABILITE CIVILE VIE PRIVEE 0%', '00', '1');
INSERT INTO COD_PREMIUM(CPREMIUM, LPREMIUM, CTAX, INDVALI) values ('RCVP09', 'RESPONSABILITE CIVILE VIE PRIVEE 9%', '09', '1');
INSERT INTO COD_PREMIUM(CPREMIUM, LPREMIUM, CTAX, INDVALI) values ('DCP00', 'DECES PARTICULIER 0%', '00', '1');
INSERT INTO COD_PREMIUM(CPREMIUM, LPREMIUM, CTAX, INDVALI) values ('DCP09', 'DECES PARTICULIER 9%', '09', '1');
INSERT INTO COD_PREMIUM(CPREMIUM, LPREMIUM, CTAX, INDVALI) values ('IPP00', 'I.P. PARTICULIER 0%', '00', '1');
INSERT INTO COD_PREMIUM(CPREMIUM, LPREMIUM, CTAX, INDVALI) values ('IPP09', 'I.P. PARTICULIER 9%', '09', '1');
INSERT INTO COD_PREMIUM(CPREMIUM, LPREMIUM, CTAX, INDVALI) values ('ANN00', 'ANNULATION 0%', '00', '1');
INSERT INTO COD_PREMIUM(CPREMIUM, LPREMIUM, CTAX, INDVALI) values ('ANN09', 'ANNULATION 9%', '09', '1');
INSERT INTO COD_PREMIUM(CPREMIUM, LPREMIUM, CTAX, INDVALI) values ('TRE00', 'TOUS RISQUES EXPOSITION 0%', '00', '1');
INSERT INTO COD_PREMIUM(CPREMIUM, LPREMIUM, CTAX, INDVALI) values ('TRE09', 'TOUS RISQUES EXPOSITION 9%', '09', '1');
INSERT INTO COD_PREMIUM(CPREMIUM, LPREMIUM, CTAX, INDVALI) values ('ATMP09', 'ACCIDENT DU TRAVAIL ET MALADIE PROFESSIONNELLE 9%', '09', '1');
INSERT INTO COD_PREMIUM(CPREMIUM, LPREMIUM, CTAX, INDVALI) values ('ASP09', 'ASSISTANCE PSY 9%', '09', '1');

comment on table COD_PREMIUM is 'Table des primes';
comment on column COD_PREMIUM.CPREMIUM is 'Code';
comment on column COD_PREMIUM.LPREMIUM is 'Libelle';
comment on column COD_PREMIUM.CTAX is 'Taux de taxe';
comment on column COD_PREMIUM.INDVALI is 'Indicateur de validite de l''enregistrement. Si a 1 alors valide';


CREATE TABLE COD_PREMIUMCONFIG
( 
  CBRANCH VARCHAR2(6),
  CCATEGORY VARCHAR2(6),
  CSECTION VARCHAR2(6),
  CGUARANTEE VARCHAR2(6),
  CPREMIUM VARCHAR2(6),
  INDVALI VARCHAR2(1) DEFAULT '1' NOT NULL,
  CONSTRAINT CODPREMIUMCONF_PK PRIMARY KEY (CBRANCH, CCATEGORY, CSECTION, CGUARANTEE, CPREMIUM)
);

comment on table COD_PREMIUMCONFIG is 'Table de configuration des primes avec les rattachements aux branches, categories...';
comment on column COD_PREMIUMCONFIG.CBRANCH is 'Code de la branche';
comment on column COD_PREMIUMCONFIG.CCATEGORY is 'Code de la categorie';
comment on column COD_PREMIUMCONFIG.CSECTION is 'Code de la section';
comment on column COD_PREMIUMCONFIG.CGUARANTEE is 'Code de la garantie';
comment on column COD_PREMIUMCONFIG.CPREMIUM is 'Code de la prime';
comment on column COD_PREMIUMCONFIG.INDVALI is 'Indicateur de validite de l''enregistrement. Si a 1 alors valide';

alter table COD_PREMIUMCONFIG ADD CONSTRAINT FK_PCBRANCH FOREIGN KEY (CBRANCH) REFERENCES COD_BRANCH(CBRANCH);
alter table COD_PREMIUMCONFIG ADD CONSTRAINT FK_PCCATEG FOREIGN KEY (CCATEGORY) REFERENCES COD_CATEGORY(CCATEGORY);
alter table COD_PREMIUMCONFIG ADD CONSTRAINT FK_PCSECTION FOREIGN KEY (CSECTION) REFERENCES COD_SECTION(CSECTION);
alter table COD_PREMIUMCONFIG ADD CONSTRAINT FK_PCGUARANTEE FOREIGN KEY (CGUARANTEE) REFERENCES COD_GUARANTEE(CGUARANTEE);
alter table COD_PREMIUMCONFIG ADD CONSTRAINT FK_PCPREMIUM FOREIGN KEY (CPREMIUM) REFERENCES COD_PREMIUM(CPREMIUM);
INSERT INTO COD_PREMIUMCONFIG(CBRANCH, CCATEGORY, CSECTION, CGUARANTEE, CPREMIUM, INDVALI) values ('IA', 'IAG', 'PEFI', 'DCG', 'DCG00', '1');
INSERT INTO COD_PREMIUMCONFIG(CBRANCH, CCATEGORY, CSECTION, CGUARANTEE, CPREMIUM, INDVALI) values ('IA', 'IAG', 'PEFI', 'DCG', 'DCG09', '1');
INSERT INTO COD_PREMIUMCONFIG(CBRANCH, CCATEGORY, CSECTION, CGUARANTEE, CPREMIUM, INDVALI) values ('IA', 'IAG', 'PEFI', 'IPG', 'IPG00', '1');
INSERT INTO COD_PREMIUMCONFIG(CBRANCH, CCATEGORY, CSECTION, CGUARANTEE, CPREMIUM, INDVALI) values ('IA', 'IAG', 'PEFI', 'IPG', 'IPG09', '1');
INSERT INTO COD_PREMIUMCONFIG(CBRANCH, CCATEGORY, CSECTION, CGUARANTEE, CPREMIUM, INDVALI) values ('IA', 'IAG', 'PEFI', 'ASS', 'ASS00', '1');
INSERT INTO COD_PREMIUMCONFIG(CBRANCH, CCATEGORY, CSECTION, CGUARANTEE, CPREMIUM, INDVALI) values ('IA', 'IAG', 'PEFI', 'ASS', 'ASS09', '1');
INSERT INTO COD_PREMIUMCONFIG(CBRANCH, CCATEGORY, CSECTION, CGUARANTEE, CPREMIUM, INDVALI) values ('IA', 'IAG', 'DOMM', 'TRB', 'TRB00', '1');
INSERT INTO COD_PREMIUMCONFIG(CBRANCH, CCATEGORY, CSECTION, CGUARANTEE, CPREMIUM, INDVALI) values ('IA', 'IAG', 'DOMM', 'TRB', 'TRB09', '1');
INSERT INTO COD_PREMIUMCONFIG(CBRANCH, CCATEGORY, CSECTION, CGUARANTEE, CPREMIUM, INDVALI) values ('IA', 'IAG', 'PEFI', 'RCVP', 'RCVP00', '1');
INSERT INTO COD_PREMIUMCONFIG(CBRANCH, CCATEGORY, CSECTION, CGUARANTEE, CPREMIUM, INDVALI) values ('IA', 'IAG', 'PEFI', 'RCVP', 'RCVP09', '1');
INSERT INTO COD_PREMIUMCONFIG(CBRANCH, CCATEGORY, CSECTION, CGUARANTEE, CPREMIUM, INDVALI) values ('IA', 'IAP', 'PEFI', 'DCP', 'DCP00', '1');
INSERT INTO COD_PREMIUMCONFIG(CBRANCH, CCATEGORY, CSECTION, CGUARANTEE, CPREMIUM, INDVALI) values ('IA', 'IAP', 'PEFI', 'DCP', 'DCP09', '1');
INSERT INTO COD_PREMIUMCONFIG(CBRANCH, CCATEGORY, CSECTION, CGUARANTEE, CPREMIUM, INDVALI) values ('IA', 'IAP', 'PEFI', 'IPP', 'IPP00', '1');
INSERT INTO COD_PREMIUMCONFIG(CBRANCH, CCATEGORY, CSECTION, CGUARANTEE, CPREMIUM, INDVALI) values ('IA', 'IAP', 'PEFI', 'IPP', 'IPP09', '1');
INSERT INTO COD_PREMIUMCONFIG(CBRANCH, CCATEGORY, CSECTION, CGUARANTEE, CPREMIUM, INDVALI) values ('IA', 'IAP', 'PEFI', 'ASS', 'ASS00', '1');
INSERT INTO COD_PREMIUMCONFIG(CBRANCH, CCATEGORY, CSECTION, CGUARANTEE, CPREMIUM, INDVALI) values ('IA', 'IAP', 'PEFI', 'ASS', 'ASS09', '1');
INSERT INTO COD_PREMIUMCONFIG(CBRANCH, CCATEGORY, CSECTION, CGUARANTEE, CPREMIUM, INDVALI) values ('IA', 'IAP', 'DOMM', 'TRB', 'TRB00', '1');
INSERT INTO COD_PREMIUMCONFIG(CBRANCH, CCATEGORY, CSECTION, CGUARANTEE, CPREMIUM, INDVALI) values ('IA', 'IAP', 'DOMM', 'TRB', 'TRB09', '1');
INSERT INTO COD_PREMIUMCONFIG(CBRANCH, CCATEGORY, CSECTION, CGUARANTEE, CPREMIUM, INDVALI) values ('RSP', 'RSP', 'PEFI', 'ANN', 'ANN00', '1');
INSERT INTO COD_PREMIUMCONFIG(CBRANCH, CCATEGORY, CSECTION, CGUARANTEE, CPREMIUM, INDVALI) values ('RSP', 'RSP', 'PEFI', 'ANN', 'ANN09', '1');
INSERT INTO COD_PREMIUMCONFIG(CBRANCH, CCATEGORY, CSECTION, CGUARANTEE, CPREMIUM, INDVALI) values ('RSP', 'RSP', 'DOMM', 'TRE', 'TRE00', '1');
INSERT INTO COD_PREMIUMCONFIG(CBRANCH, CCATEGORY, CSECTION, CGUARANTEE, CPREMIUM, INDVALI) values ('RSP', 'RSP', 'DOMM', 'TRE', 'TRE09', '1');
INSERT INTO COD_PREMIUMCONFIG(CBRANCH, CCATEGORY, CSECTION, CGUARANTEE, CPREMIUM, INDVALI) values ('RSP', 'ATMP', 'PEFI', 'ATMP', 'ATMP09', '1');
INSERT INTO COD_PREMIUMCONFIG(CBRANCH, CCATEGORY, CSECTION, CGUARANTEE, CPREMIUM, INDVALI) values ('RSP', 'ATMP', 'PEFI', 'ASP', 'ASP09', '1');

/*****************************************************************************************************************************************************************/
/*****************************************************************************************************************************************************************/
/************************************************************* USERS *********************************************************************************************/
/*****************************************************************************************************************************************************************/
/*****************************************************************************************************************************************************************/

CREATE TABLE USR_ROLE
( 
  CROLE VARCHAR2(16),
  LROLE VARCHAR2(128),
  INDVALI VARCHAR2(1) DEFAULT '1' NOT NULL,
  CONSTRAINT USRROLE_PK PRIMARY KEY (CROLE)
);

INSERT INTO USR_ROLE(CROLE, LROLE, INDVALI) VALUES ('ADMIN', 'ADMINISTRATEUR MFI', '1');

comment on table USR_ROLE is 'Table des roles';
comment on column USR_ROLE.CROLE is 'Code';
comment on column USR_ROLE.LROLE is 'Libelle';
comment on column USR_ROLE.INDVALI is 'Indicateur de validite de l''enregistrement. Si a 1 alors valide';

CREATE TABLE USR_USER
( 
  CUSER VARCHAR2(32),
  LUSER VARCHAR2(128),
  CROLE VARCHAR2(16),
  INDVALI VARCHAR2(1) DEFAULT '1' NOT NULL,
  CUSERCRE VARCHAR2(32) NOT NULL,
  CREATIONDATE DATE NOT NULL,
  CUSERMOD VARCHAR2(32),
  MODIFDATE DATE,
  CONSTRAINT USRUSER_PK PRIMARY KEY (CUSER)
);

comment on table USR_USER is 'Table des utilisateurs';
comment on column USR_USER.CUSER is 'Code';
comment on column USR_USER.LUSER is 'Libelle';
comment on column USR_USER.CROLE is 'Role associe';
comment on column USR_USER.INDVALI is 'Indicateur. Si a 1 alors non valide.';
comment on column USR_USER.CUSERCRE is 'Utilisateur de creation';
comment on column USR_USER.CREATIONDATE is 'Date de creation';
comment on column USR_USER.CUSERMOD is 'Utilisateur de mise a jour';
comment on column USR_USER.MODIFDATE is 'Date de mise a jour';

alter table USR_USER ADD CONSTRAINT FK_USRROLE FOREIGN KEY (CROLE) REFERENCES USR_ROLE(CROLE);


INSERT INTO USR_USER(CUSER, LUSER, CROLE, INDVALI, CUSERCRE, CREATIONDATE) VALUES ('XAVIER', 'XAVIER', 'ADMIN', '1', 'XAVIER', sysdate);
INSERT INTO USR_USER(CUSER, LUSER, CROLE, INDVALI, CUSERCRE, CREATIONDATE) VALUES ('DIMITRI', 'DIMITRI', 'ADMIN', '1', 'XAVIER', sysdate);
INSERT INTO USR_USER(CUSER, LUSER, CROLE, INDVALI, CUSERCRE, CREATIONDATE) VALUES ('LAURENT', 'LAURENT', 'ADMIN', '1', 'XAVIER', sysdate);


/*****************************************************************************************************************************************************************/
/*****************************************************************************************************************************************************************/
/*********************************************************** CONTRAT *********************************************************************************************/
/*****************************************************************************************************************************************************************/
/*****************************************************************************************************************************************************************/


CREATE TABLE COD_QUOTESTATUS
( 
  CQUOTESTATUS VARCHAR2(6),
  LQUOTESTATUS VARCHAR2(128) NOT NULL,
  INDPENDING VARCHAR2(1) DEFAULT '1' NOT NULL,
  INDACCEPTED VARCHAR2(1) DEFAULT '1' NOT NULL,
  INDREFUSED VARCHAR2(1) DEFAULT '1' NOT NULL,
  INDVALIDATED VARCHAR2(1) DEFAULT '1' NOT NULL,
  INDABORTED VARCHAR2(1) DEFAULT '1' NOT NULL,
  INDVALI VARCHAR2(1) DEFAULT '1' NOT NULL,
  CONSTRAINT CODQUOTESTATUS_PK PRIMARY KEY (CQUOTESTATUS)
);

comment on table COD_QUOTESTATUS is 'Table des statuts des devis';
comment on column COD_QUOTESTATUS.CQUOTESTATUS is 'Code';
comment on column COD_QUOTESTATUS.LQUOTESTATUS is 'Libelle';
comment on column COD_QUOTESTATUS.INDPENDING is 'Indicateur. Si a 1 alors en attente d''elements complementaires';
comment on column COD_QUOTESTATUS.INDACCEPTED is 'Indicateur. Si a 1 alors risque accepte';
comment on column COD_QUOTESTATUS.INDREFUSED is 'Indicateur. Si a 1 alors en risque refuse';
comment on column COD_QUOTESTATUS.INDVALIDATED is 'Indicateur. Si a 1 alors transforme en contrat';
comment on column COD_QUOTESTATUS.INDABORTED is 'Indicateur. Si a 1 alors sans suite';
comment on column COD_QUOTESTATUS.INDVALI is 'Indicateur de validite de l''enregistrement. Si a 1 alors valide';

insert into COD_QUOTESTATUS(CQUOTESTATUS, LQUOTESTATUS, INDPENDING, INDACCEPTED, INDREFUSED, INDVALIDATED, INDABORTED, INDVALI) values ('00', 'EN COURS', '1', '0', '0', '0', '0', '1');
insert into COD_QUOTESTATUS(CQUOTESTATUS, LQUOTESTATUS, INDPENDING, INDACCEPTED, INDREFUSED, INDVALIDATED, INDABORTED, INDVALI) values ('01', 'ACCEPTE', '0', '1', '0', '0', '0', '1');
insert into COD_QUOTESTATUS(CQUOTESTATUS, LQUOTESTATUS, INDPENDING, INDACCEPTED, INDREFUSED, INDVALIDATED, INDABORTED, INDVALI) values ('02', 'REFUSE', '0', '0', '1', '0', '0', '1');
insert into COD_QUOTESTATUS(CQUOTESTATUS, LQUOTESTATUS, INDPENDING, INDACCEPTED, INDREFUSED, INDVALIDATED, INDABORTED, INDVALI) values ('03', 'VALIDE', '0', '0', '0', '1', '0', '1');
insert into COD_QUOTESTATUS(CQUOTESTATUS, LQUOTESTATUS, INDPENDING, INDACCEPTED, INDREFUSED, INDVALIDATED, INDABORTED, INDVALI) values ('04', 'SANS SUITE', '0', '0', '0', '0', '1', '1');

CREATE TABLE COD_DURATION
( 
  CDURATION VARCHAR2(6),
  LDURATION VARCHAR2(128) NOT NULL,
  INDVALI VARCHAR2(1) DEFAULT '1' NOT NULL,
  CONSTRAINT CODDURATION_PK PRIMARY KEY (CDURATION)
);

comment on table COD_DURATION is 'Table des durees de contrat';
comment on column COD_DURATION.CDURATION is 'Code';
comment on column COD_DURATION.LDURATION is 'Libelle';
comment on column COD_DURATION.INDVALI is 'Indicateur de validite de l''enregistrement. Si a 1 alors valide';

insert into COD_DURATION(CDURATION, LDURATION, INDVALI) values ('00', 'TEMPORAIRE', '1');
insert into COD_DURATION(CDURATION, LDURATION, INDVALI) values ('01', 'ANNUEL', '1');

CREATE TABLE COD_CANCEL
( 
  CCANCEL VARCHAR2(6),
  LCANCEL VARCHAR2(128) NOT NULL,
  INDVALI VARCHAR2(1) DEFAULT '1' NOT NULL,
  CONSTRAINT CODCANCEL_PK PRIMARY KEY (CCANCEL)
);

comment on table COD_CANCEL is 'Table des motifs de resiliation';
comment on column COD_CANCEL.CCANCEL is 'Code';
comment on column COD_CANCEL.LCANCEL is 'Libelle';
comment on column COD_CANCEL.INDVALI is 'Indicateur de validite de l''enregistrement. Si a 1 alors valide';
-------------------------------------------------------------------------------------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------------------------------------------------------------------------------------

/* INDIVIDU */
CREATE TABLE CLI_CLIENT
( 
  NUMCLI NUMBER(8),
  CCIVIL VARCHAR2(2) NOT NULL,
  NAME VARCHAR2(128),
  FIRSTNAME VARCHAR2(128),
  COMPANYNAME VARCHAR2(128),
  COMPANYID VARCHAR2(8),
  CUSERCRE VARCHAR2(32) NOT NULL,
  CREATIONDATE DATE NOT NULL,
  CUSERMOD VARCHAR2(32),
  MODIFDATE DATE,
  CONSTRAINT CLICLIENT_PK PRIMARY KEY (NUMCLI)
);

comment on table CLI_CLIENT is 'Table des individus';
comment on column CLI_CLIENT.NUMCLI is 'Identifiant de l''individu';
comment on column CLI_CLIENT.CCIVIL is 'Civilite. Lien avec COD_CIVILITY';
comment on column CLI_CLIENT.NAME is 'Nom de famille';
comment on column CLI_CLIENT.FIRSTNAME is 'Prenom';
comment on column CLI_CLIENT.COMPANYNAME is 'Raison sociale';
comment on column CLI_CLIENT.COMPANYID is 'SIRET';
comment on column CLI_CLIENT.CUSERCRE is 'Utilisateur de creation';
comment on column CLI_CLIENT.CREATIONDATE is 'Date de creation';
comment on column CLI_CLIENT.CUSERMOD is 'Utilisateur de modification';
comment on column CLI_CLIENT.MODIFDATE is 'Date de modification';

alter table CLI_CLIENT ADD CONSTRAINT FK_CLIENTCCIVIL FOREIGN KEY (CCIVIL) REFERENCES COD_CIVILITY(CCIVIL);
alter table CLI_CLIENT ADD CONSTRAINT FK_CLIENTUSERCRE FOREIGN KEY (CUSERCRE) REFERENCES USR_USER(CUSER);
alter table CLI_CLIENT ADD CONSTRAINT FK_CLIENTUSERMOD FOREIGN KEY (CUSERMOD) REFERENCES USR_USER(CUSER);

CREATE SEQUENCE NUMCLI_SEQ
START WITH     1
INCREMENT BY   1
NOCACHE
NOCYCLE;


/* ADRESSE */
CREATE TABLE CLI_ADDRESS
( 
  NUMADDRESS NUMBER(8),
  NUMCLI NUMBER(8) NOT NULL,
  CADDRESS VARCHAR2(8),
  STARTVAL DATE NOT NULL,
  ENDVAL DATE,
  STREET1 VARCHAR2(128),
  STREET2 VARCHAR2(128) NOT NULL,
  STREET3 VARCHAR2(128),
  STREET4 VARCHAR2(128),
  CPOSTAL VARCHAR2(5) NOT NULL,
  CITY VARCHAR2(128) NOT NULL,
  CCOUNTRY VARCHAR2(3) DEFAULT 'FRA' NOT NULL,
  CUSERCRE VARCHAR2(32) NOT NULL,
  CREATIONDATE DATE NOT NULL,
  CUSERMOD VARCHAR2(32),
  MODIFDATE DATE,
  CONSTRAINT CLIADDRESS_PK PRIMARY KEY (NUMADDRESS)
);

comment on table CLI_ADDRESS is 'Table des adresses des individus';
comment on column CLI_ADDRESS.NUMADDRESS is 'Identifiant de l''adresse';
comment on column CLI_ADDRESS.NUMCLI is 'Identifiant de l''individu';
comment on column CLI_ADDRESS.CADDRESS is 'Type d''adresse. Reference COD_ADDRESS';
comment on column CLI_ADDRESS.STARTVAL is 'Debut de validite de l''adresse';
comment on column CLI_ADDRESS.ENDVAL is 'Fin de validite de l''adresse';
comment on column CLI_ADDRESS.STREET1 is 'Complement de nom';
comment on column CLI_ADDRESS.STREET2 is 'Numero et voie';
comment on column CLI_ADDRESS.STREET3 is 'Complement d''adresse';
comment on column CLI_ADDRESS.STREET4 is 'Boite postale, Lieu-dit...';
comment on column CLI_ADDRESS.CPOSTAL is 'Code postal. Reference COD_POSTAL';
comment on column CLI_ADDRESS.CITY is 'Ville. Reference COD_POSTAL';
comment on column CLI_ADDRESS.CCOUNTRY is 'Pays. Reference COD_POSTAL et COD_COUNTRY';
comment on column CLI_ADDRESS.CUSERCRE is 'Utilisateur de creation';
comment on column CLI_ADDRESS.CREATIONDATE is 'Date de creation';
comment on column CLI_ADDRESS.CUSERMOD is 'Utilisateur de modification';
comment on column CLI_ADDRESS.MODIFDATE is 'Date de modification';

alter table CLI_ADDRESS ADD CONSTRAINT FK_ADDRNUMCLI FOREIGN KEY (NUMCLI) REFERENCES CLI_CLIENT(NUMCLI);
alter table CLI_ADDRESS ADD CONSTRAINT FK_ADDRCCOUNTRY FOREIGN KEY (CCOUNTRY) REFERENCES COD_COUNTRY(CCOUNTRY);


alter table CLI_ADDRESS ADD CONSTRAINT FK_ADDRZIPCITY FOREIGN KEY (CPOSTAL, CITY, CCOUNTRY) REFERENCES COD_POSTAL(CPOSTAL, CITY, CCOUNTRY);
alter table CLI_ADDRESS ADD CONSTRAINT FK_ADDRUSERCRE FOREIGN KEY (CUSERCRE) REFERENCES USR_USER(CUSER);
alter table CLI_ADDRESS ADD CONSTRAINT FK_ADDRUSERMOD FOREIGN KEY (CUSERMOD) REFERENCES USR_USER(CUSER);

CREATE SEQUENCE NUMADDRESS_SEQ
START WITH     1
INCREMENT BY   1
NOCACHE
NOCYCLE;

/* CATEGORIE INDIVIDU */
CREATE TABLE CLI_CATCLI
( 
  NUMCLICATCLI NUMBER(8),
  NUMCLI NUMBER(8),
  CCATCLI VARCHAR2(5),
  CONSTRAINT CLICATCLI_PK PRIMARY KEY (NUMCLICATCLI)
);
alter table CLI_CATCLI ADD CONSTRAINT FK_CLICATCATFK FOREIGN KEY (CCATCLI) REFERENCES COD_CATCLI(CCATCLI);
alter table CLI_CATCLI ADD CONSTRAINT FK_CLICATCLIFK FOREIGN KEY (NUMCLI) REFERENCES CLI_CLIENT(NUMCLI);

CREATE SEQUENCE NUMCLICATCLI_SEQ
START WITH     1
INCREMENT BY   1
NOCACHE
NOCYCLE;

comment on table CLI_CATCLI is 'Table des categories des individus';
comment on column CLI_CATCLI.NUMCLICATCLI is 'PK';
comment on column CLI_CATCLI.NUMCLI is 'Identifiant de l''individu';
comment on column CLI_CATCLI.CCATCLI is 'Categorie. Reference COD_CATCLI';


/*MOUVEMENTS*/
create table CLI_MOVEMENT
(
  NUMMOVEMENT     NUMBER(10) not null,
  NUMCLI          NUMBER(8) not null,
  NUMCON          NUMBER(3),
  CMOVEMENT       VARCHAR2(16) not null,
  CUSERMOVEMENT   VARCHAR2(20),
  MOVEMENTDATE    DATE default sysdate not null,
  CONSTRAINT CLIMOVEMENT_PK PRIMARY KEY (NUMMOVEMENT)
);
alter table CLI_MOVEMENT ADD CONSTRAINT FK_MVTCMVT FOREIGN KEY (CMOVEMENT) REFERENCES COD_MOVEMENT(CMOVEMENT);
alter table CLI_MOVEMENT ADD CONSTRAINT FK_MVTCUSER FOREIGN KEY (CUSERMOVEMENT) REFERENCES USR_USER(CUSER);
alter table CLI_MOVEMENT ADD CONSTRAINT FK_MVTCLIENT FOREIGN KEY (NUMCLI) REFERENCES CLI_CLIENT(NUMCLI);

CREATE SEQUENCE NUMMOVEMENT_SEQ
START WITH     1
INCREMENT BY   1
NOCACHE
NOCYCLE;

/*DETAILS MOUVEMENTS*/

create table CLI_MOVEMENTDET
(
  NUMMOVEMENTDET NUMBER(10) not null,
  NUMMOVEMENT    NUMBER(10) not null,
  CCOLUMN      VARCHAR2(64) not null,
  OLDVALUE     VARCHAR2(128),
  NEWVALUE     VARCHAR2(128),
  CONSTRAINT CLIMOVEMENTDET_PK PRIMARY KEY (NUMMOVEMENTDET)
);

alter table CLI_MOVEMENTDET ADD CONSTRAINT FK_MVTDETMVT FOREIGN KEY (NUMMOVEMENT) REFERENCES CLI_MOVEMENT(NUMMOVEMENT);
alter table CLI_MOVEMENTDET ADD CONSTRAINT FK_MVTDETCCOLUMN FOREIGN KEY (CCOLUMN) REFERENCES COD_MOVEMENTDET(CCOLUMN);

CREATE SEQUENCE NUMMOVEMENTDET_SEQ
START WITH     1
INCREMENT BY   1
NOCACHE
NOCYCLE;


/* TELEPHONE */
CREATE TABLE CLI_PHONE
( 
  NUMPHONE NUMBER(8) NOT NULL,
  NUMCLI NUMBER(8) NOT NULL,
  CPHONE VARCHAR2(8) NOT NULL,
  PHONENUMBER VARCHAR2(64) NOT NULL,
  STARTVAL DATE NOT NULL,
  ENDVAL DATE,
  CUSERCRE VARCHAR2(32) NOT NULL,
  CREATIONDATE DATE NOT NULL,
  CUSERMOD VARCHAR2(32),
  MODIFDATE DATE,
  CONSTRAINT CLIPHONE_PK PRIMARY KEY (NUMPHONE)
);

comment on table CLI_PHONE is 'Table des telephones des individus';
comment on column CLI_PHONE.NUMPHONE is 'Identifiant du telephone';
comment on column CLI_PHONE.NUMCLI is 'Identifiant de l''individu';
comment on column CLI_PHONE.CPHONE is 'Type de telephone. Reference COD_PHONE';
comment on column CLI_PHONE.PHONENUMBER is 'Numero de telephone';
comment on column CLI_PHONE.STARTVAL is 'Debut de validite du telephone';
comment on column CLI_PHONE.ENDVAL is 'Fin de validite du telephone';
comment on column CLI_PHONE.CUSERCRE is 'Utilisateur de creation';
comment on column CLI_PHONE.CREATIONDATE is 'Date de creation';
comment on column CLI_PHONE.CUSERMOD is 'Utilisateur de modification';
comment on column CLI_PHONE.MODIFDATE is 'Date de modification';

alter table CLI_PHONE ADD CONSTRAINT FK_PHONENUMCLI FOREIGN KEY (NUMCLI) REFERENCES CLI_CLIENT(NUMCLI);
alter table CLI_PHONE ADD CONSTRAINT FK_PHONECPHONE FOREIGN KEY (CPHONE) REFERENCES COD_PHONE(CPHONE);
alter table CLI_PHONE ADD CONSTRAINT FK_PHONEUSERCRE FOREIGN KEY (CUSERCRE) REFERENCES USR_USER(CUSER);
alter table CLI_PHONE ADD CONSTRAINT FK_PHONEUSERMOD FOREIGN KEY (CUSERMOD) REFERENCES USR_USER(CUSER);

CREATE SEQUENCE NUMPHONE_SEQ
START WITH     1
INCREMENT BY   1
NOCACHE
NOCYCLE;

/* EMAIL */
CREATE TABLE CLI_EMAIL
( 
  NUMEMAIL NUMBER(8) NOT NULL,
  NUMCLI NUMBER(8) NOT NULL,
  CEMAIL VARCHAR2(8) NOT NULL,
  EMAIL VARCHAR2(256) NOT NULL,
  STARTVAL DATE NOT NULL,
  ENDVAL DATE,
  CUSERCRE VARCHAR2(32) NOT NULL,
  CREATIONDATE DATE NOT NULL,
  CUSERMOD VARCHAR2(32),
  MODIFDATE DATE,
  CONSTRAINT CLIEMAIL_PK PRIMARY KEY (NUMEMAIL)
);

comment on table CLI_EMAIL is 'Table des emails des individus';
comment on column CLI_EMAIL.NUMEMAIL is 'Identifiant de l''email';
comment on column CLI_EMAIL.NUMCLI is 'Identifiant de l''individu';
comment on column CLI_EMAIL.CEMAIL is 'Type de telephone. Reference COD_EMAIL';
comment on column CLI_EMAIL.EMAIL is 'Adresse email';
comment on column CLI_EMAIL.STARTVAL is 'Debut de validite';
comment on column CLI_EMAIL.ENDVAL is 'Fin de validite';
comment on column CLI_EMAIL.CUSERCRE is 'Utilisateur de creation';
comment on column CLI_EMAIL.CREATIONDATE is 'Date de creation';
comment on column CLI_EMAIL.CUSERMOD is 'Utilisateur de modification';
comment on column CLI_EMAIL.MODIFDATE is 'Date de modification';

alter table CLI_EMAIL ADD CONSTRAINT FK_EMAILNUMCLI FOREIGN KEY (NUMCLI) REFERENCES CLI_CLIENT(NUMCLI);
alter table CLI_EMAIL ADD CONSTRAINT FK_EMAILCEMAIL FOREIGN KEY (CEMAIL) REFERENCES COD_EMAIL(CEMAIL);
alter table CLI_EMAIL ADD CONSTRAINT FK_EMAILUSERCRE FOREIGN KEY (CUSERCRE) REFERENCES USR_USER(CUSER);
alter table CLI_EMAIL ADD CONSTRAINT FK_EMAILUSERMOD FOREIGN KEY (CUSERMOD) REFERENCES USR_USER(CUSER);

CREATE SEQUENCE NUMEMAIL_SEQ
 START WITH     1
 INCREMENT BY   1
 NOCACHE
 NOCYCLE;
 
 
CREATE TABLE COD_TABLE
( 
  CTABLE VARCHAR2(32) NOT NULL,
  LTABLE VARCHAR2(128) NOT NULL,
  TABLENAME VARCHAR2(128) NOT NULL,
  TABLECODE VARCHAR2(128) NOT NULL,
  TABLELABEL VARCHAR2(128) NOT NULL,
  INDVALI VARCHAR2(1) DEFAULT '1' NOT NULL,
  CONSTRAINT CODTABLE_PK PRIMARY KEY (CTABLE)
);

comment on table COD_TABLE is 'Table des codes tables standards (listes de valeurs cle/valeur sans indicateurs supplementaires)';
comment on column COD_TABLE.CTABLE is 'Code';
comment on column COD_TABLE.LTABLE is 'Libelle';
comment on column COD_TABLE.TABLENAME is 'Nom de la table';
comment on column COD_TABLE.TABLECODE is 'Colonne dans la table avec le code';
comment on column COD_TABLE.TABLELABEL is 'Colonne dans la table avec le libelle';
comment on column COD_TABLE.INDVALI is 'Identifiant de validite. Si a 1 alors valide';

INSERT INTO COD_TABLE(CTABLE, LTABLE, TABLENAME, TABLECODE, TABLELABEL, INDVALI) VALUES ('CIVILITIES', 'LISTE DES CIVILITES', 'Cod_civility', 'ccivil', 'lcivil', '1' );
INSERT INTO COD_TABLE(CTABLE, LTABLE, TABLENAME, TABLECODE, TABLELABEL, INDVALI) VALUES ('CANCELLATIONS', 'LISTE DES MOTIFS DE RESILIATION', 'Cod_cancel', 'ccancel', 'lcancel', '1' );
INSERT INTO COD_TABLE(CTABLE, LTABLE, TABLENAME, TABLECODE, TABLELABEL, INDVALI) VALUES ('BRANCHES', 'LISTE DES BRANCHES', 'Cod_branch', 'cbranch', 'lbranch', '1' );
INSERT INTO COD_TABLE(CTABLE, LTABLE, TABLENAME, TABLECODE, TABLELABEL, INDVALI) VALUES ('CATEGORIES', 'LISTE DES CATEGORIES', 'Cod_category', 'ccategory', 'lcategory', '1' );
INSERT INTO COD_TABLE(CTABLE, LTABLE, TABLENAME, TABLECODE, TABLELABEL, INDVALI) VALUES ('COUNTRIES', 'LISTE DES PAYS', 'Cod_country', 'ccountry', 'lcountry', '1' );
INSERT INTO COD_TABLE(CTABLE, LTABLE, TABLENAME, TABLECODE, TABLELABEL, INDVALI) VALUES ('GUARANTEES', 'LISTE DES GARANTIES', 'Cod_guarantee', 'cguarantee', 'lguarantee', '1' );
INSERT INTO COD_TABLE(CTABLE, LTABLE, TABLENAME, TABLECODE, TABLELABEL, INDVALI) VALUES ('POPULATIONS', 'LISTE DES POPULATIONS', 'Cod_catcli', 'ccatcli', 'lcatcli', '1' );
INSERT INTO COD_TABLE(CTABLE, LTABLE, TABLENAME, TABLECODE, TABLELABEL, INDVALI) VALUES ('ADDRESSES', 'LISTE DES TYPES D''ADRESSES', 'Cod_address', 'caddress', 'laddress', '1' );
INSERT INTO COD_TABLE(CTABLE, LTABLE, TABLENAME, TABLECODE, TABLELABEL, INDVALI) VALUES ('PHONES', 'LISTE DES TYPES DE TELEPHONES', 'Cod_phone', 'cphone', 'lphone', '1' );
INSERT INTO COD_TABLE(CTABLE, LTABLE, TABLENAME, TABLECODE, TABLELABEL, INDVALI) VALUES ('EMAILS', 'LISTE DES TYPES D''EMAILS', 'Cod_email', 'cemail', 'lemail', '1' );
INSERT INTO COD_TABLE(CTABLE, LTABLE, TABLENAME, TABLECODE, TABLELABEL, INDVALI) VALUES ('DURATIONS', 'LISTE DES DUREES DE CONTRAT', 'Cod_duration', 'cduration', 'lduration', '1' );





CREATE TABLE CLI_QUOTE
( 
  NUMCLI NUMBER(8) NOT NULL,
  NUMQUOTE NUMBER(3) NOT NULL,
  NUMCON NUMBER(3),
  CBRANCH VARCHAR2(5) NOT NULL,
  CCATEGORY VARCHAR2(5) NOT NULL,
  NUMCLIBROKER NUMBER(8) NOT NULL,
  NUMCLILEADER NUMBER(8) NOT NULL,
  STARTVAL DATE NOT NULL,
  ENDVAL DATE,
  RECEPTIONDATE DATE NOT NULL,
  ACCEPTANCEDATE DATE,
  CQUOTESTATUS VARCHAR2(6) DEFAULT '00' NOT NULL ,
  CDURATION VARCHAR2(6),
  CUSERUW VARCHAR2(32) NOT NULL,
  GUARANTEEDAMOUNT NUMBER(16,3),
  PREMIUMAMOUNT NUMBER(16,3),
  SHAREPART NUMBER(16,3),
  CUSERCRE VARCHAR2(32) NOT NULL,
  CREATIONDATE DATE NOT NULL,
  CUSERMOD VARCHAR2(32),
  MODIFDATE DATE,
  CUSERCANCEL VARCHAR2(32),
  CANCELDATE DATE,
  CONSTRAINT CLIQUOTE_PK PRIMARY KEY (NUMCLI, NUMQUOTE)
);

alter table CLI_QUOTE ADD CONSTRAINT FK_QUOTENUMCLI FOREIGN KEY (NUMCLI) REFERENCES CLI_CLIENT(NUMCLI);
alter table CLI_QUOTE ADD CONSTRAINT FK_QUOTEBRANCH FOREIGN KEY (CBRANCH) REFERENCES COD_BRANCH(CBRANCH);
alter table CLI_QUOTE ADD CONSTRAINT FK_QUOTECATEG FOREIGN KEY (CCATEGORY) REFERENCES COD_CATEGORY(CCATEGORY);
alter table CLI_QUOTE ADD CONSTRAINT FK_QUOTENUMCLIB FOREIGN KEY (NUMCLIBROKER) REFERENCES CLI_CLIENT(NUMCLI);
alter table CLI_QUOTE ADD CONSTRAINT FK_QUOTENUMCLIL FOREIGN KEY (NUMCLILEADER) REFERENCES CLI_CLIENT(NUMCLI);
alter table CLI_QUOTE ADD CONSTRAINT FK_QUOTESTATUS FOREIGN KEY (CQUOTESTATUS) REFERENCES COD_QUOTESTATUS(CQUOTESTATUS);
alter table CLI_QUOTE ADD CONSTRAINT FK_QUOTEDURATION FOREIGN KEY (CDURATION) REFERENCES COD_DURATION(CDURATION);
alter table CLI_QUOTE ADD CONSTRAINT FK_QUOTEUSERUW FOREIGN KEY (CUSERUW) REFERENCES USR_USER(CUSER);
alter table CLI_QUOTE ADD CONSTRAINT FK_QUOTEUSERCRE FOREIGN KEY (CUSERCRE) REFERENCES USR_USER(CUSER);
alter table CLI_QUOTE ADD CONSTRAINT FK_QUOTEUSERMOD FOREIGN KEY (CUSERMOD) REFERENCES USR_USER(CUSER);



---

INSERT INTO COD_MOVEMENT(CMOVEMENT, LMOVEMENT, INDVALI) values ('NEWQUOTE', 'Nouvelle proposition', '1');
INSERT INTO COD_MOVEMENT(CMOVEMENT, LMOVEMENT, INDVALI) values ('MODQUOTE', 'Modification de proposition', '1');

INSERT INTO COD_MOVEMENTDET(CCOLUMN, LABEL, VALUETABLE, VALUECOLUMN) VALUES ('ACCEPTANCEDATE', 'DATE D''ACCEPTATION', NULL, NULL);
INSERT INTO COD_MOVEMENTDET(CCOLUMN, LABEL, VALUETABLE, VALUECOLUMN) VALUES ('CBRANCH', 'BRANCHE', 'COD_BRANCH', 'LBRANCH');
INSERT INTO COD_MOVEMENTDET(CCOLUMN, LABEL, VALUETABLE, VALUECOLUMN) VALUES ('CCATEGORY', 'CATEGORIE', 'COD_CATEGORY', 'LCATEGORY');
INSERT INTO COD_MOVEMENTDET(CCOLUMN, LABEL, VALUETABLE, VALUECOLUMN) VALUES ('CDURATION', 'DUREE', 'COD_DURATION', 'LDURATION');
INSERT INTO COD_MOVEMENTDET(CCOLUMN, LABEL, VALUETABLE, VALUECOLUMN) VALUES ('CQUOTESTATUS', 'STATUT', 'COD_QUOTESTATUS', 'LQUOTESTATUS');
INSERT INTO COD_MOVEMENTDET(CCOLUMN, LABEL, VALUETABLE, VALUECOLUMN) VALUES ('CUSERUW', 'SOUSCRIPTEUR', NULL, NULL);
INSERT INTO COD_MOVEMENTDET(CCOLUMN, LABEL, VALUETABLE, VALUECOLUMN) VALUES ('ENDVAL', 'FIN DE VALIDITE', NULL, NULL);
INSERT INTO COD_MOVEMENTDET(CCOLUMN, LABEL, VALUETABLE, VALUECOLUMN) VALUES ('GUARANTEEDAMOUNT', 'MONTANT GARANTI', NULL, NULL);
INSERT INTO COD_MOVEMENTDET(CCOLUMN, LABEL, VALUETABLE, VALUECOLUMN) VALUES ('NUMCLIBROKER', 'COURTIER', NULL, NULL);
INSERT INTO COD_MOVEMENTDET(CCOLUMN, LABEL, VALUETABLE, VALUECOLUMN) VALUES ('NUMCLILEADER', 'APERITEUR', NULL, NULL);
INSERT INTO COD_MOVEMENTDET(CCOLUMN, LABEL, VALUETABLE, VALUECOLUMN) VALUES ('NUMQUOTE', 'IDENTIFIANT PROPOSITION', NULL, NULL);
INSERT INTO COD_MOVEMENTDET(CCOLUMN, LABEL, VALUETABLE, VALUECOLUMN) VALUES ('PREMIUMAMOUNT', 'MONTANT DE LA PRIME', NULL, NULL);
INSERT INTO COD_MOVEMENTDET(CCOLUMN, LABEL, VALUETABLE, VALUECOLUMN) VALUES ('RECEPTIONDATE', 'DATE DE RECEPTION', NULL, NULL);
INSERT INTO COD_MOVEMENTDET(CCOLUMN, LABEL, VALUETABLE, VALUECOLUMN) VALUES ('SHAREPART', 'PART', NULL, NULL);
INSERT INTO COD_MOVEMENTDET(CCOLUMN, LABEL, VALUETABLE, VALUECOLUMN) VALUES ('STARTVAL', 'DEBUT DE VALIDITE', NULL, NULL);

ALTER TABLE CLI_QUOTE ADD COMMENTARY VARCHAR2(4000);
comment on column CLI_QUOTE.COMMENTARY is 'Commentaire';


---- 03/06/2015


ALTER TABLE CLI_MOVEMENT ADD NUMQUOTE NUMBER(3);
comment on column CLI_MOVEMENT.NUMQUOTE is 'Identifiant de la proposition';

comment on table CLI_QUOTE is 'Table des propositions';
comment on column CLI_QUOTE.NUMCLI is 'Identifiant du client. Reference CLI_CLIENT';
comment on column CLI_QUOTE.NUMQUOTE is 'Identifiant de la proposition.';
comment on column CLI_QUOTE.NUMCON is 'Identifiant du contrat. Renseigne quand la proposition devient contrat. Reference CLI_CONTRACT';
comment on column CLI_QUOTE.CBRANCH is 'Identifiant de la branche. Reference COD_BRANCH';
comment on column CLI_QUOTE.CCATEGORY  is 'Identifiant de la categorie. Reference COD_CATEGORY';
comment on column CLI_QUOTE.NUMCLIBROKER  is 'Identifiant du courtier. Reference CLI_CLIENT';
comment on column CLI_QUOTE.NUMCLILEADER  is 'Identifiant de l''aperiteur. Reference CLI_CLIENT';
comment on column CLI_QUOTE.STARTVAL is 'Debut de validite';
comment on column CLI_QUOTE.ENDVAL is 'Fin de validite';
comment on column CLI_QUOTE.RECEPTIONDATE is 'Date de reception';
comment on column CLI_QUOTE.ACCEPTANCEDATE is 'Date d''acceptation';
comment on column CLI_QUOTE.CQUOTESTATUS is 'Statut de la proposition. Reference COD_QUOTESTATUS';
comment on column CLI_QUOTE.CDURATION is 'Duree. Reference COD_DURATION';
comment on column CLI_QUOTE.CUSERUW is 'Identifiant souscripteur. Reference USR_USER';
comment on column CLI_QUOTE.GUARANTEEDAMOUNT is 'Montant garanti';
comment on column CLI_QUOTE.PREMIUMAMOUNT is 'Montant de la prime';
comment on column CLI_QUOTE.SHAREPART is 'Part du risque prise en charge';
comment on column CLI_QUOTE.CUSERCRE is 'Utilisateur de creation';
comment on column CLI_QUOTE.CREATIONDATE is 'Date de creation';
comment on column CLI_QUOTE.CUSERMOD is 'Utilisateur de modification';
comment on column CLI_QUOTE.MODIFDATE is 'Date de modification';
comment on column CLI_QUOTE.CUSERCANCEL is 'Utilisateur de resilisation';
comment on column CLI_QUOTE.CANCELDATE is 'Date de resilisation';

CREATE INDEX IDX_MVTCONTRAT ON CLI_MOVEMENT (NUMCLI, NUMCON);
CREATE INDEX IDX_MVTQUOTE ON CLI_MOVEMENT (NUMCLI, NUMQUOTE);


---- 04/06/2015



/* CONTRATS */
CREATE TABLE CLI_CONTRACT
( 
  NUMCLI NUMBER(8) NOT NULL,
  NUMCON NUMBER(3) NOT NULL,
  CBRANCH VARCHAR2(5) NOT NULL,
  CCATEGORY VARCHAR2(5) NOT NULL,
  NUMCLIBROKER NUMBER(8) NOT NULL,
  NUMCLILEADER NUMBER(8) NOT NULL,
  STARTVAL DATE NOT NULL,
  ENDVAL DATE NOT NULL,
  CDURATION VARCHAR2(6),
  CUSERUW VARCHAR2(32) NOT NULL,
  CCANCEL VARCHAR2(2),
  CUSERCRE VARCHAR2(32) NOT NULL,
  CREATIONDATE DATE NOT NULL,
  CUSERMOD VARCHAR2(32),
  MODIFDATE DATE,
  CUSERCANCEL VARCHAR2(32),
  CANCELDATE DATE,
  CONSTRAINT CLICONTRACT_PK PRIMARY KEY (NUMCLI, NUMCON)
);

comment on table CLI_CONTRACT is 'Table des contrats des individus';
comment on column CLI_CONTRACT.NUMCLI is 'Identifiant de l''individu';
comment on column CLI_CONTRACT.NUMCON is 'Identifiant du contrat';
comment on column CLI_CONTRACT.CBRANCH is 'Branche principale du contrat. Reference COD_BRANCH';
comment on column CLI_CONTRACT.CCATEGORY is 'Categorie principale du contrat. Reference COD_CATEGORY';
comment on column CLI_CONTRACT.NUMCLIBROKER  is 'Identifiant du courtier. Reference CLI_CLIENT';
comment on column CLI_CONTRACT.NUMCLILEADER  is 'Identifiant de l''aperiteur. Reference CLI_CLIENT';
comment on column CLI_CONTRACT.CUSERUW is 'Souscripteur. Reference USR_USER';
comment on column CLI_CONTRACT.CDURATION is 'Duree du contrat. Reference COD_DURATION';
comment on column CLI_CONTRACT.STARTVAL is 'Debut de validite du contrat';
comment on column CLI_CONTRACT.ENDVAL is 'Fin de validite du contrat';
comment on column CLI_CONTRACT.CCANCEL is 'Motif de resilisation. Reference COD_ANNUL';
comment on column CLI_CONTRACT.CUSERCRE is 'Utilisateur de creation';
comment on column CLI_CONTRACT.CREATIONDATE is 'Date de creation';
comment on column CLI_CONTRACT.CUSERMOD is 'Utilisateur de modification';
comment on column CLI_CONTRACT.MODIFDATE is 'Date de modification';
comment on column CLI_CONTRACT.CUSERCANCEL is 'Utilisateur de resilisation';
comment on column CLI_CONTRACT.CANCELDATE is 'Date de resilisation';

alter table CLI_CONTRACT ADD CONSTRAINT FK_CONTRACTNUMCLI FOREIGN KEY (NUMCLI) REFERENCES CLI_CLIENT(NUMCLI);
alter table CLI_CONTRACT ADD CONSTRAINT FK_CONTRACTCCATEG FOREIGN KEY (CCATEGORY) REFERENCES COD_CATEGORY(CCATEGORY);
alter table CLI_CONTRACT ADD CONSTRAINT FK_CONTRACTCBRANCH FOREIGN KEY (CBRANCH) REFERENCES COD_BRANCH(CBRANCH);
alter table CLI_CONTRACT ADD CONSTRAINT FK_CONTRACTCANCEL FOREIGN KEY (CCANCEL) REFERENCES COD_CANCEL(CCANCEL);
alter table CLI_CONTRACT ADD CONSTRAINT FK_CONTRACTRUSERCRE FOREIGN KEY (CUSERCRE) REFERENCES USR_USER(CUSER);
alter table CLI_CONTRACT ADD CONSTRAINT FK_CONTRACTUSERMOD FOREIGN KEY (CUSERMOD) REFERENCES USR_USER(CUSER);
alter table CLI_CONTRACT ADD CONSTRAINT FK_CONTRACTUSERCANCEL FOREIGN KEY (CUSERCANCEL) REFERENCES USR_USER(CUSER);
alter table CLI_CONTRACT ADD CONSTRAINT FK_CONTRACTDURATION FOREIGN KEY (CDURATION) REFERENCES COD_DURATION(CDURATION);
alter table CLI_CONTRACT ADD CONSTRAINT FK_CONTRACTUSERUW FOREIGN KEY (CUSERUW) REFERENCES USR_USER(CUSER);
alter table CLI_CONTRACT ADD CONSTRAINT FK_CONNUMCLIB FOREIGN KEY (NUMCLIBROKER) REFERENCES CLI_CLIENT(NUMCLI);
alter table CLI_CONTRACT ADD CONSTRAINT FK_CONNUMCLIL FOREIGN KEY (NUMCLILEADER) REFERENCES CLI_CLIENT(NUMCLI);




/*GARANTIES*/
CREATE TABLE CLI_GUARANTEE
( 
  NUMGUARANTEE NUMBER(8) NOT NULL,
  NUMCLI NUMBER(8) NOT NULL,
  NUMCON NUMBER(3) NOT NULL,
  CSECTION VARCHAR2(6) NOT NULL,
  CGUARANTEE VARCHAR2(6) NOT NULL,
  CPREMIUM VARCHAR2(6) NOT NULL,
  GUARANTEEAMOUNT NUMBER(16,3) NOT NULL,
  PREMIUMAMOUNT NUMBER(16,3) NOT NULL,
  STARTVAL DATE NOT NULL,
  ENDVAL DATE,
  CUSERCRE VARCHAR2(32) NOT NULL,
  CREATIONDATE DATE NOT NULL,
  CUSERMOD VARCHAR2(32),
  MODIFDATE DATE,
  CONSTRAINT CLIGUARANTEE_PK PRIMARY KEY (NUMGUARANTEE)
);

comment on table CLI_GUARANTEE is 'Table des guaranties d''un contrat';
comment on column CLI_GUARANTEE.NUMGUARANTEE is 'Pk fictive';
comment on column CLI_GUARANTEE.NUMCLI is 'Identifiant de l''individu';
comment on column CLI_GUARANTEE.NUMCON is 'Identifiant du contrat';
comment on column CLI_GUARANTEE.CSECTION is 'Section. Reference COD_SECTION';
comment on column CLI_GUARANTEE.CGUARANTEE is 'Garantie. Reference COD_GUARANTEE';
comment on column CLI_GUARANTEE.CPREMIUM is 'Prime. Reference COD_PREMIUM';
comment on column CLI_GUARANTEE.GUARANTEEAMOUNT is 'Montant garanti';
comment on column CLI_GUARANTEE.PREMIUMAMOUNT is 'Montant de la prime';
comment on column CLI_GUARANTEE.STARTVAL is 'Debut de validite du contrat';
comment on column CLI_GUARANTEE.ENDVAL is 'Fin de validite du contrat';
comment on column CLI_GUARANTEE.CUSERCRE is 'Utilisateur de creation';
comment on column CLI_GUARANTEE.CREATIONDATE is 'Date de creation';
comment on column CLI_GUARANTEE.CUSERMOD is 'Utilisateur de modification';
comment on column CLI_GUARANTEE.MODIFDATE is 'Date de modification';


alter table CLI_GUARANTEE ADD CONSTRAINT FK_GUARCONTRACT FOREIGN KEY (NUMCLI, NUMCON) REFERENCES CLI_CONTRACT(NUMCLI, NUMCON);
alter table CLI_GUARANTEE ADD CONSTRAINT FK_GUARSECTION FOREIGN KEY (CSECTION) REFERENCES COD_SECTION(CSECTION);
alter table CLI_GUARANTEE ADD CONSTRAINT FK_GUARCGUAR FOREIGN KEY (CGUARANTEE) REFERENCES COD_GUARANTEE(CGUARANTEE);
alter table CLI_GUARANTEE ADD CONSTRAINT FK_GUARPREMIUM FOREIGN KEY (CPREMIUM) REFERENCES COD_PREMIUM(CPREMIUM);
alter table CLI_GUARANTEE ADD CONSTRAINT FK_GUARRUSERCRE FOREIGN KEY (CUSERCRE) REFERENCES USR_USER(CUSER);
alter table CLI_GUARANTEE ADD CONSTRAINT FK_GUARUSERMOD FOREIGN KEY (CUSERMOD) REFERENCES USR_USER(CUSER);

CREATE SEQUENCE NUMGUARANTEE_SEQ
START WITH     1
INCREMENT BY   1
NOCACHE
NOCYCLE;



CREATE INDEX IDX_QUOUSERW ON CLI_QUOTE (CUSERUW);
CREATE INDEX IDX_QUOBRANCH ON CLI_QUOTE (CBRANCH, CCATEGORY);
CREATE INDEX IDX_QUOBROKER ON CLI_QUOTE (NUMCLIBROKER);
CREATE INDEX IDX_QUOLEADER ON CLI_QUOTE (NUMCLILEADER);
CREATE INDEX IDX_CONBRANCH ON CLI_CONTRACT (CBRANCH, CCATEGORY);
CREATE INDEX IDX_CONUSERW ON CLI_CONTRACT (CUSERUW);
CREATE INDEX IDX_CONBROKER ON CLI_CONTRACT (NUMCLIBROKER);
CREATE INDEX IDX_CONLEADER ON CLI_CONTRACT (NUMCLILEADER);


CREATE TABLE CPT_GUARDISPATCH
( 
  NUMDISPATCH NUMBER(8) NOT NULL,
  NUMGUARANTEE NUMBER(8) NOT NULL,
  NUMCLIINS NUMBER(8) NOT NULL,
  SHAREPART NUMBER(16,3) NOT NULL,
  CUSERCRE VARCHAR2(32) NOT NULL,
  CREATIONDATE DATE NOT NULL,
  CUSERMOD VARCHAR2(32),
  MODIFDATE DATE,
  CONSTRAINT CPTGUARDISPATCH_PK PRIMARY KEY (NUMDISPATCH)
);

comment on table CPT_GUARDISPATCH is 'Table des repartition des garanties par assureur';
comment on column CPT_GUARDISPATCH.NUMDISPATCH is 'Pk fictive';
comment on column CPT_GUARDISPATCH.NUMGUARANTEE is 'Identifiant de la garantie. Reference CLI_GUARANTEE';
comment on column CPT_GUARDISPATCH.NUMCLIINS is 'Identifiant de l''assureur. Reference CLI_CLIENT';
comment on column CPT_GUARDISPATCH.SHAREPART is 'Pourcentage assur�';
comment on column CPT_GUARDISPATCH.CUSERCRE is 'Utilisateur de creation';
comment on column CPT_GUARDISPATCH.CREATIONDATE is 'Date de creation';
comment on column CPT_GUARDISPATCH.CUSERMOD is 'Utilisateur de modification';
comment on column CPT_GUARDISPATCH.MODIFDATE is 'Date de modification';

alter table CPT_GUARDISPATCH ADD CONSTRAINT FK_DISPATCHGUAR FOREIGN KEY (NUMGUARANTEE) REFERENCES CLI_GUARANTEE(NUMGUARANTEE);
alter table CPT_GUARDISPATCH ADD CONSTRAINT FK_DISPATCHINS FOREIGN KEY (NUMCLIINS) REFERENCES CLI_CLIENT(NUMCLI);


CREATE SEQUENCE NUMDISPATCH_SEQ
START WITH     1
INCREMENT BY   1
NOCACHE
NOCYCLE;


-- 07/06/2015

alter table cli_contract add NUMQUOTE NUMBER (3);
comment on column cli_contract.NUMQUOTE is 'Identifiant de la proposition ayant mene au contrat';

insert into cod_movement(cmovement, lmovement, indvali) values ('VALQUOTE', 'VALIDATION PROPOSITION', '1');


-- 08/06/2015

alter table cli_guarantee add CBRANCH VARCHAR2(6) NOT NULL;
alter table cli_guarantee add CCATEGORY VARCHAR2(6) NOT NULL;
comment on column cli_guarantee.CBRANCH is 'Identifiant de la branche. Reference COD_BRANCH';
comment on column cli_guarantee.CCATEGORY is 'Identifiant de la categorie. Reference COD_CATEGORY';
alter table cli_guarantee ADD CONSTRAINT FK_GUARBRANCH FOREIGN KEY (CBRANCH) REFERENCES COD_BRANCH(CBRANCH);
alter table cli_guarantee ADD CONSTRAINT FK_GUARCATEG FOREIGN KEY (CCATEGORY) REFERENCES COD_CATEGORY(CCATEGORY);



alter table cod_duration add INDRENEWAL VARCHAR2(1) DEFAULT '0' NOT NULL;
comment on column cod_duration.INDRENEWAL is 'Indicateur. Si a 1, alors le contrat sur cette duree sera tacitement reconduit';

update cod_duration set INDRENEWAL = '1' where cduration = '01';

commit;

CREATE TABLE COD_FREQUENCY
( 
  CFREQUENCY VARCHAR2(6) NOT NULL,
  LFREQUENCY VARCHAR2(128) NOT NULL,
  NBMONTHS NUMBER(2) NOT NULL, 
  INDVALI VARCHAR2(1) DEFAULT '1' NOT NULL,
  CONSTRAINT CODFREQUENCY_PK PRIMARY KEY (CFREQUENCY)
);

comment on table COD_FREQUENCY is 'Table des frequences de facturation';
comment on column COD_FREQUENCY.CFREQUENCY is 'Code';
comment on column COD_FREQUENCY.LFREQUENCY is 'Libelle';
comment on column COD_FREQUENCY.INDVALI is 'Indicateur de validite de l''enregistrement. Si a 1 alors valide';
comment on column COD_FREQUENCY.NBMONTHS is 'Nombre de mois associes a la frequence';

insert into COD_FREQUENCY(CFREQUENCY, LFREQUENCY, INDVALI, NBMONTHS) values ('12', 'ANNUELLE', '1', 12);
insert into COD_FREQUENCY(CFREQUENCY, LFREQUENCY, INDVALI, NBMONTHS) values ('01', 'MENSUELLE', '1', 1);
insert into COD_FREQUENCY(CFREQUENCY, LFREQUENCY, INDVALI, NBMONTHS) values ('03', 'TRIMESTRIELLE', '1', 4);
insert into COD_FREQUENCY(CFREQUENCY, LFREQUENCY, INDVALI, NBMONTHS) values ('06', 'SEMESTRIELLE', '1', 4);



INSERT INTO COD_TABLE(CTABLE, LTABLE, TABLENAME, TABLECODE, TABLELABEL, INDVALI) VALUES ('FREQUENCIES', 'LISTE DES FREQUENCES', 'Cod_frequency', 'cfrequency', 'lfrequency', '1' );

alter table cli_contract add CFREQUENCY VARCHAR2(6) default '12' NOT NULL;
comment on column cli_contract.CFREQUENCY is 'Frequence de facturation. Reference COD_FREQUENCY';
alter table cli_contract ADD CONSTRAINT FK_CONFREQUENCY FOREIGN KEY (CFREQUENCY) REFERENCES COD_FREQUENCY(CFREQUENCY);

commit;

update cod_frequency set NBMONTHS = '6' where cfrequency = '06';

update cod_frequency set NBMONTHS = 3 where cfrequency = '03';
commit;


alter table cod_duration add INDTEMPORARY VARCHAR2(1) DEFAULT '0' NOT NULL;
update cod_duration set indtemporary = '1' where cduration = '00';
commit;


CREATE TABLE COD_FEE
( 
  CFEE VARCHAR2(6) NOT NULL,
  LFEE VARCHAR2(128) NOT NULL,
  INDPOLICYFEE VARCHAR2(1) DEFAULT '0' NOT NULL,
  INDVALI VARCHAR2(1) DEFAULT '1' NOT NULL,
  CONSTRAINT CODFEE_PK PRIMARY KEY (CFEE)
);

comment on table COD_FEE is 'Table des frais et des couts';
comment on column COD_FEE.CFEE is 'Code';
comment on column COD_FEE.LFEE is 'Libelle';
comment on column COD_FEE.INDPOLICYFEE is 'Indicateur de cout de police. Si a 1 alors cout initial de police';
comment on column COD_FEE.INDVALI is 'Indicateur de validite de l''enregistrement. Si a 1 alors valide';


insert into COD_FEE(CFEE, LFEE, INDPOLICYFEE, INDVALI) values ('INIT', 'COUT DE POLICE', '1', '1');

commit;


-- 18/06/2015

CREATE TABLE CPT_FEE
( 
  NUMFEE NUMBER(8) NOT NULL,
  NUMCLI NUMBER(8) NOT NULL,
  NUMCON NUMBER(3) NOT NULL,
  CFEE VARCHAR2(6) NOT NULL,
  AMOUNT NUMBER(16,3) NOT NULL,
  CUSERCRE VARCHAR2(32) NOT NULL,
  CREATIONDATE DATE NOT NULL,
  CUSERMOD VARCHAR2(32),
  MODIFDATE DATE,
  CONSTRAINT CPTFEE_PK PRIMARY KEY (NUMFEE)
);

comment on table CPT_FEE is 'Table des frais et des couts par contrat';
comment on column CPT_FEE.NUMFEE is 'Pk fictive';
comment on column CPT_FEE.NUMCLI is 'Identifiant de l''individu';
comment on column CPT_FEE.NUMCON is 'Identifiant du contrat';
comment on column CPT_FEE.AMOUNT is 'Montant';
comment on column CPT_FEE.CFEE is 'Identifiant du frais. Reference COD_FEE';
comment on column CPT_FEE.CUSERCRE is 'Utilisateur de creation';
comment on column CPT_FEE.CREATIONDATE is 'Date de creation';
comment on column CPT_FEE.CUSERMOD is 'Utilisateur de modification';
comment on column CPT_FEE.MODIFDATE is 'Date de modification';


alter table CPT_FEE ADD CONSTRAINT FK_FEENUMCLINUMCON FOREIGN KEY (NUMCLI,NUMCON) REFERENCES CLI_CONTRACT(NUMCLI, NUMCON);
alter table CPT_FEE ADD CONSTRAINT FK_FEECFEE FOREIGN KEY (CFEE) REFERENCES COD_FEE(CFEE);


CREATE SEQUENCE NUMFEE_SEQ
START WITH     1
INCREMENT BY   1
NOCACHE
NOCYCLE;


drop table CPT_GUARCOMMI cascade constraints;

CREATE TABLE CPT_GUARCOMMI
( 
  NUMCOMMI NUMBER(8) NOT NULL,
  NUMGUARANTEE NUMBER(8) NOT NULL,
  NUMCLICOMMI NUMBER(8) NOT NULL,
  RATE NUMBER(16,3) NOT NULL,
  CUSERCRE VARCHAR2(32) NOT NULL,
  CREATIONDATE DATE NOT NULL,
  CUSERMOD VARCHAR2(32),
  MODIFDATE DATE,
  CONSTRAINT CPTGUARCOMMI_PK PRIMARY KEY (NUMCOMMI)
);

comment on table CPT_GUARCOMMI is 'Table des commission pour l''agence';
comment on column CPT_GUARCOMMI.NUMCOMMI is 'Pk fictive';
comment on column CPT_GUARCOMMI.NUMGUARANTEE is 'Identifiant de la garantie. Reference CLI_GUARANTEE';
comment on column CPT_GUARCOMMI.NUMCLICOMMI is 'Identifiant du coassureur qui doit la commission. Reference CLI_CLIENT';
comment on column CPT_GUARCOMMI.RATE is 'Taux de la commission';
comment on column CPT_GUARCOMMI.CUSERCRE is 'Utilisateur de creation';
comment on column CPT_GUARCOMMI.CREATIONDATE is 'Date de creation';
comment on column CPT_GUARCOMMI.CUSERMOD is 'Utilisateur de modification';
comment on column CPT_GUARCOMMI.MODIFDATE is 'Date de modification';

alter table CPT_GUARCOMMI ADD CONSTRAINT FK_COMMIGUAR FOREIGN KEY (NUMGUARANTEE) REFERENCES CLI_GUARANTEE(NUMGUARANTEE);
alter table CPT_GUARCOMMI ADD CONSTRAINT FK_COMMINUMCLI FOREIGN KEY (NUMCLICOMMI) REFERENCES CLI_CLIENT(NUMCLI);


CREATE SEQUENCE NUMCOMMI_SEQ
START WITH     1
INCREMENT BY   1
NOCACHE
NOCYCLE;


alter table CLI_CONTRACT ADD RENEWALDATE DATE;
comment on column CPT_GUARCOMMI.MODIFDATE is 'Date d''echeance du contrat';

drop table CPT_LEADINGFEE cascade constraints;

CREATE TABLE CPT_LEADINGFEE
( 
  NUMLEADINGFEE NUMBER(8) NOT NULL,
  NUMGUARANTEE NUMBER(8) NOT NULL,
  NUMCLISRC NUMBER(8) NOT NULL,
  NUMCLIDEST NUMBER(8) NOT NULL,
  RATE NUMBER(16,3) NOT NULL,
  CUSERCRE VARCHAR2(32) NOT NULL,
  CREATIONDATE DATE NOT NULL,
  CUSERMOD VARCHAR2(32),
  MODIFDATE DATE,
  CONSTRAINT CPTLEADINGFEE_PK PRIMARY KEY (NUMLEADINGFEE)
);

alter table CPT_LEADINGFEE ADD CONSTRAINT FK_LEADGUAR FOREIGN KEY (NUMGUARANTEE) REFERENCES CLI_GUARANTEE(NUMGUARANTEE);
alter table CPT_LEADINGFEE ADD CONSTRAINT FK_LEADNUMCLISRC FOREIGN KEY (NUMCLISRC) REFERENCES CLI_CLIENT(NUMCLI);
alter table CPT_LEADINGFEE ADD CONSTRAINT FK_LEADNUMCLIDEST FOREIGN KEY (NUMCLIDEST) REFERENCES CLI_CLIENT(NUMCLI);



CREATE SEQUENCE NUMLEADINGFEE_SEQ
START WITH     1
INCREMENT BY   1
NOCACHE
NOCYCLE;

commit;


CREATE TABLE CPT_GUARPLACEMENT
( 
  NUMPLACEMENT NUMBER(8) NOT NULL,
  NUMGUARANTEE NUMBER(8) NOT NULL,
  NUMCLIINS NUMBER(8) NOT NULL,
  SHAREPART NUMBER(16,3) NOT NULL,
  CUSERCRE VARCHAR2(32) NOT NULL,
  CREATIONDATE DATE NOT NULL,
  CUSERMOD VARCHAR2(32),
  MODIFDATE DATE,
  CONSTRAINT CPTGUARPLACEMENT_PK PRIMARY KEY (NUMPLACEMENT)
);

alter table CPT_GUARPLACEMENT ADD CONSTRAINT FK_PLACEMENTGUAR FOREIGN KEY (NUMGUARANTEE) REFERENCES CLI_GUARANTEE(NUMGUARANTEE);
alter table CPT_GUARPLACEMENT ADD CONSTRAINT FK_PLACEMENTINS FOREIGN KEY (NUMCLIINS) REFERENCES CLI_CLIENT(NUMCLI);

CREATE SEQUENCE NUMPLACEMENT_SEQ
START WITH     1
INCREMENT BY   1
NOCACHE
NOCYCLE;

commit;


insert into COD_TABLE(CTABLE, LTABLE, TABLENAME, TABLECODE, TABLELABEL, INDVALI)
values ('FEES', 'LISTE DES FRAIS', 'Cod_fee', 'cfee', 'lfee', '1');




commit;
