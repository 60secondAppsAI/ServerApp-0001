CREATE DATABASE IF NOT EXISTS ServerApp;

CREATE TABLE ServerApp.servers (
    `server_id` INT AUTO_INCREMENT,
    `hostname` VARCHAR(255),
    `serial_number` VARCHAR(255),
    `manufacturer` VARCHAR(255),
    `model` VARCHAR(255),
    `status` VARCHAR(255),
PRIMARY KEY (server_id)) ENGINE=MyISAM DEFAULT CHARSET=latin1;

CREATE TABLE ServerApp.processors (
    `processor_id` INT AUTO_INCREMENT,
    `server_id` INT,
    `model` VARCHAR(255),
    `core_count` INT,
    `clock_speed_g_hz` DOUBLE,
PRIMARY KEY (processor_id)) ENGINE=MyISAM DEFAULT CHARSET=latin1;

CREATE TABLE ServerApp.memory_modules (
    `memory_module_id` INT AUTO_INCREMENT,
    `server_id` INT,
    `capacity_g_b` INT,
    `memory_type` VARCHAR(255),
PRIMARY KEY (memory_module_id)) ENGINE=MyISAM DEFAULT CHARSET=latin1;

CREATE TABLE ServerApp.storage_devices (
    `storage_device_id` INT AUTO_INCREMENT,
    `server_id` INT,
    `capacity_g_b` INT,
    `storage_type` VARCHAR(255),
    `status` VARCHAR(255),
PRIMARY KEY (storage_device_id)) ENGINE=MyISAM DEFAULT CHARSET=latin1;

USE drcvelocity;
