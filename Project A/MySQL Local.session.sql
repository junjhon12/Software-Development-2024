
-- Create the employees table if it doesn't exist
CREATE TABLE IF NOT EXISTS employees (
    empid INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    job_title VARCHAR(50),
    division VARCHAR(50),
    salary DECIMAL(10, 2),
    ssn VARCHAR(9)  -- SSN without dashes
);

INSERT INTO employees (name, job_title, division, salary, ssn) VALUES
('Alice Smith', 'Software Engineer', 'Engineering', 85000.00, '123456789'),
('Bob Johnson', 'Product Manager', 'Product', 95000.00, '987654321'),
('Carol White', 'Data Analyst', 'Analytics', 62000.00, '456789123'),
('David Brown', 'HR Specialist', 'Human Resources', 70000.00, '789123456'),
('Eve Davis', 'Designer', 'Design', 65000.00, '321654987');