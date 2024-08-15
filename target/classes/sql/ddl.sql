CREATE DATABASE IF NOT EXISTS pharmacy_ofc;
USE pharmacy_ofc;

CREATE TABLE country(
    country_code VARCHAR(5),
    country_name VARCHAR(50),
    CONSTRAINT pk_id_country PRIMARY KEY (country_code)
);

CREATE TABLE active_principle(
    id_act_p INT(4) AUTO_INCREMENT,
    name_act_p varchar(60),
    CONSTRAINT pk_id_act_p PRIMARY KEY (id_act_p)
);

CREATE TABLE mode_admi(
    id_mod_admi INT(4) AUTO_INCREMENT,
    description_mod_admi VARCHAR(60),
    CONSTRAINT pk_id_mod_admi PRIMARY KEY (id_mod_admi)
);

CREATE TABLE unit_measurement(
    id_uni_m INT(4) AUTO_INCREMENT,
    name_uni_m VARCHAR(50),
    CONSTRAINT pk_id_uni_m PRIMARY KEY (id_uni_m)
);

CREATE TABLE region(
    code_reg VARCHAR(5),
    name_reg VARCHAR(50),
    country_code VARCHAR(5),
    CONSTRAINT pk_id_reg PRIMARY KEY (code_reg),
    CONSTRAINT fk_region_country_code FOREIGN KEY (country_code) REFERENCES country(country_code)
);

CREATE TABLE city(
    code_city VARCHAR(5),
    name_city VARCHAR(50),
    code_reg VARCHAR(5),
    CONSTRAINT pk_id_city PRIMARY KEY (code_city),
    CONSTRAINT fk_city_code_reg FOREIGN KEY (code_reg) REFERENCES region(code_reg)
);

CREATE TABLE laboratory(
    id_lab INT(4) AUTO_INCREMENT,
    name_lab VARCHAR(50),
    code_city VARCHAR(5),
    CONSTRAINT pk_id_lab PRIMARY KEY (id_lab),
    CONSTRAINT fk_lab_code_city FOREIGN KEY (code_city) REFERENCES city(code_city)
);

CREATE TABLE medicine(
    id_medicine INT(4) AUTO_INCREMENT,
    proceedings VARCHAR(10),
    name_medicine VARCHAR(100),
    health_register VARCHAR(50),
    description TEXT,
    description_short VARCHAR(60),
    name_rol VARCHAR(100),
    code_mode_adm INT(4),
    code_act_p INT(4),
    code_uni_m INT(4),
    code_lab INT(4),
    CONSTRAINT pk_id_medicine PRIMARY KEY (id_medicine),
    CONSTRAINT fk_medicine_code_mode_adm FOREIGN KEY (code_mode_adm) REFERENCES mode_admi(id_mod_admi),
    CONSTRAINT fk_medicine_code_act_p FOREIGN KEY (code_act_p) REFERENCES active_principle(id_act_p),
    CONSTRAINT fk_medicine_code_uni_m FOREIGN KEY (code_uni_m) REFERENCES unit_measurement(id_uni_m),
    CONSTRAINT fk_medicine_code_lab FOREIGN KEY (code_lab) REFERENCES laboratory(id_lab)
);

CREATE TABLE pharmacy(
    id_fm INT(4) AUTO_INCREMENT,
    name_fm VARCHAR(50),
    adress_fm VARCHAR(100),
    long_fm FLOAT(8),
    lat_fm FLOAT(8),
    logo_fm VARCHAR(50),
    code_city_fm VARCHAR(5),
    CONSTRAINT pk_id_fm PRIMARY KEY (id_fm),
    CONSTRAINT fk_fm_code_city FOREIGN KEY (code_city_fm) REFERENCES city(code_city)
);

CREATE TABLE pharmacy_medicine(
    id_medicine INT(4),
    id_fm INT(4),
    price FLOAT(8),
    CONSTRAINT pk_id_med_pharm PRIMARY KEY (id_medicine, id_fm),
    CONSTRAINT fk_id_fm_m FOREIGN KEY (id_medicine) REFERENCES medicine (id_medicine),
    CONSTRAINT fk_id_fm_f FOREIGN KEY (id_fm) REFERENCES pharmacy (id_fm)
);

CREATE TABLE customer(
    id_customer VARCHAR(20),
    name_customer VARCHAR(50),
    last_name_customer VARCHAR(50),
    email_customer VARCHAR(100),
    birthdate_customer DATE,
    long_customer FLOAT(8),
    lat_customer FLOAT(8),
    code_city_cm VARCHAR(5),
    CONSTRAINT pk_id_customer PRIMARY KEY (id_customer),
    CONSTRAINT fk_customer_code_city FOREIGN KEY (code_city_cm) REFERENCES city(code_city)
);
