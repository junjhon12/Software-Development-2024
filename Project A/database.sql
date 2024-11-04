CREATE DATABASE employeeData;

USE employeeData;

CREATE TABLE employees (
    empId INT PRIMARY KEY,
    name VARCHAR(100),
    ssn VARCHAR(11),
    jobTitle VARCHAR(50),
    division VARCHAR(50),
    salary DOUBLE
);