-- 创建数据库
CREATE DATABASE IF NOT EXISTS safety_management CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE safety_management;

-- 生产安全表
CREATE TABLE IF NOT EXISTS operation_safety (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    operation_name VARCHAR(255) NOT NULL,
    procedure_content LONGTEXT,
    safety_level VARCHAR(50) NOT NULL,
    department VARCHAR(255) NOT NULL,
    owner VARCHAR(255) NOT NULL,
    risk_points LONGTEXT,
    control_measures LONGTEXT,
    status VARCHAR(50) NOT NULL,
    last_review_date DATETIME,
    last_review_by VARCHAR(255),
    created_at DATETIME NOT NULL,
    updated_at DATETIME NOT NULL,
    INDEX idx_department (department),
    INDEX idx_status (status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS special_operation (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    operation_type VARCHAR(100) NOT NULL,
    operator_name VARCHAR(255) NOT NULL,
    certificate_number VARCHAR(255) NOT NULL,
    certificate_issued_date DATE NOT NULL,
    certificate_expiry_date DATE NOT NULL,
    issuing_authority VARCHAR(255) NOT NULL,
    last_training_date DATE NOT NULL,
    next_training_date DATE NOT NULL,
    department VARCHAR(255) NOT NULL,
    work_location VARCHAR(255) NOT NULL,
    notes LONGTEXT,
    status VARCHAR(50) NOT NULL,
    created_at DATETIME NOT NULL,
    updated_at DATETIME NOT NULL,
    INDEX idx_operation_type (operation_type),
    INDEX idx_department (department),
    INDEX idx_status (status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS work_injury (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    injury_type VARCHAR(100) NOT NULL,
    affected_employee VARCHAR(255) NOT NULL,
    employee_id VARCHAR(255) NOT NULL,
    incident_date DATE NOT NULL,
    incident_location VARCHAR(255) NOT NULL,
    description LONGTEXT,
    severity VARCHAR(50) NOT NULL,
    treatment_description LONGTEXT,
    hospital_name VARCHAR(255) NOT NULL,
    department VARCHAR(255) NOT NULL,
    reported_by VARCHAR(255) NOT NULL,
    root_cause LONGTEXT,
    preventive_measures LONGTEXT,
    status VARCHAR(50) NOT NULL,
    created_at DATETIME NOT NULL,
    updated_at DATETIME NOT NULL,
    INDEX idx_department (department),
    INDEX idx_severity (severity),
    INDEX idx_status (status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 消防安全表
CREATE TABLE IF NOT EXISTS fire_control (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    facility_name VARCHAR(255) NOT NULL,
    facility_type VARCHAR(100) NOT NULL,
    location VARCHAR(255) NOT NULL,
    quantity INT NOT NULL,
    installation_date DATE NOT NULL,
    last_maintenance_date DATE NOT NULL,
    next_maintenance_date DATE NOT NULL,
    maintenance_company VARCHAR(255) NOT NULL,
    maintenance_notes LONGTEXT,
    status VARCHAR(50) NOT NULL,
    notes LONGTEXT,
    created_at DATETIME NOT NULL,
    updated_at DATETIME NOT NULL,
    INDEX idx_location (location),
    INDEX idx_facility_type (facility_type),
    INDEX idx_status (status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS hot_work_permit (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    permit_number VARCHAR(255) NOT NULL UNIQUE,
    work_date DATE NOT NULL,
    work_type VARCHAR(100) NOT NULL,
    location VARCHAR(255) NOT NULL,
    operator VARCHAR(255) NOT NULL,
    supervisor VARCHAR(255) NOT NULL,
    safety_officer VARCHAR(255) NOT NULL,
    work_description LONGTEXT,
    safety_measures LONGTEXT,
    risk_assessment LONGTEXT,
    start_time TIME NOT NULL,
    end_time TIME NOT NULL,
    status VARCHAR(50) NOT NULL,
    approved_at DATETIME,
    approved_by VARCHAR(255),
    created_at DATETIME NOT NULL,
    updated_at DATETIME NOT NULL,
    INDEX idx_location (location),
    INDEX idx_work_type (work_type),
    INDEX idx_status (status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS fire_drill (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    drill_name VARCHAR(255) NOT NULL,
    drill_date DATE NOT NULL,
    drill_type VARCHAR(100) NOT NULL,
    drill_content LONGTEXT,
    participant_count INT NOT NULL,
    organizer VARCHAR(255) NOT NULL,
    objectives LONGTEXT,
    execution_process LONGTEXT,
    findings_and_issues LONGTEXT,
    improvement_measures LONGTEXT,
    status VARCHAR(50) NOT NULL,
    created_at DATETIME NOT NULL,
    updated_at DATETIME NOT NULL,
    INDEX idx_drill_type (drill_type),
    INDEX idx_status (status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 环保安全表
CREATE TABLE IF NOT EXISTS waste_water (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    record_date DATE NOT NULL,
    source VARCHAR(255) NOT NULL,
    treatment_process VARCHAR(255) NOT NULL,
    flow_rate DECIMAL(10, 2) NOT NULL,
    unit VARCHAR(50) NOT NULL,
    ph DECIMAL(5, 2) NOT NULL,
    cod DECIMAL(10, 2) NOT NULL,
    ammonia_nitrogen DECIMAL(10, 2) NOT NULL,
    suspended_solids DECIMAL(10, 2) NOT NULL,
    discharge_location VARCHAR(255) NOT NULL,
    compliance_status VARCHAR(50) NOT NULL,
    remarks LONGTEXT,
    recorded_by VARCHAR(255) NOT NULL,
    created_at DATETIME NOT NULL,
    updated_at DATETIME NOT NULL,
    INDEX idx_source (source),
    INDEX idx_compliance_status (compliance_status),
    INDEX idx_discharge_location (discharge_location)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS waste_gas (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    record_date DATE NOT NULL,
    emission_source VARCHAR(255) NOT NULL,
    treatment_method VARCHAR(255) NOT NULL,
    sulphur_dioxide DECIMAL(10, 4) NOT NULL,
    nitrogen_oxide DECIMAL(10, 4) NOT NULL,
    particulate_matter DECIMAL(10, 4) NOT NULL,
    volatile_organic_compounds DECIMAL(10, 4) NOT NULL,
    unit VARCHAR(50) NOT NULL,
    emission_rate DECIMAL(10, 4) NOT NULL,
    compliance_status VARCHAR(50) NOT NULL,
    remarks LONGTEXT,
    recorded_by VARCHAR(255) NOT NULL,
    created_at DATETIME NOT NULL,
    updated_at DATETIME NOT NULL,
    INDEX idx_emission_source (emission_source),
    INDEX idx_compliance_status (compliance_status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS hazardous_waste (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    waste_name VARCHAR(255) NOT NULL,
    waste_code VARCHAR(100) NOT NULL,
    hazard_characteristics VARCHAR(255) NOT NULL,
    quantity DECIMAL(10, 2) NOT NULL,
    unit VARCHAR(50) NOT NULL,
    storage_date DATE NOT NULL,
    disposal_date DATE,
    storage_location VARCHAR(255) NOT NULL,
    disposal_method VARCHAR(255),
    disposal_company VARCHAR(255),
    storage_conditions LONGTEXT,
    status VARCHAR(50) NOT NULL,
    remarks LONGTEXT,
    created_at DATETIME NOT NULL,
    updated_at DATETIME NOT NULL,
    INDEX idx_status (status),
    INDEX idx_storage_location (storage_location)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS hazardous_chemical (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    chemical_name VARCHAR(255) NOT NULL,
    chemical_formula VARCHAR(100),
    classification_code VARCHAR(100) NOT NULL,
    current_stock DECIMAL(10, 2) NOT NULL,
    unit VARCHAR(50) NOT NULL,
    max_storage_limit DECIMAL(10, 2) NOT NULL,
    storage_location VARCHAR(255) NOT NULL,
    storage_method VARCHAR(255) NOT NULL,
    container_type VARCHAR(255),
    hazard_description LONGTEXT,
    safety_precautions LONGTEXT,
    emergency_measures LONGTEXT,
    last_inspection_date DATE NOT NULL,
    next_inspection_date DATE NOT NULL,
    status VARCHAR(50) NOT NULL,
    remarks LONGTEXT,
    created_at DATETIME NOT NULL,
    updated_at DATETIME NOT NULL,
    INDEX idx_status (status),
    INDEX idx_storage_location (storage_location)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 园区安全表
CREATE TABLE IF NOT EXISTS access_control (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    device_name VARCHAR(255) NOT NULL,
    location VARCHAR(255) NOT NULL,
    device_type VARCHAR(100) NOT NULL,
    manufacturer VARCHAR(255),
    model VARCHAR(255),
    installation_time DATETIME NOT NULL,
    last_maintenance_time DATETIME NOT NULL,
    next_maintenance_time DATETIME NOT NULL,
    maintenance_notes LONGTEXT,
    status VARCHAR(50) NOT NULL,
    remarks LONGTEXT,
    created_at DATETIME NOT NULL,
    updated_at DATETIME NOT NULL,
    INDEX idx_location (location),
    INDEX idx_status (status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS access_log (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    visitor_name VARCHAR(255) NOT NULL,
    visitor_id VARCHAR(255) NOT NULL,
    access_point VARCHAR(255) NOT NULL,
    access_time DATETIME NOT NULL,
    access_type VARCHAR(50) NOT NULL,
    purpose VARCHAR(255) NOT NULL,
    department VARCHAR(255) NOT NULL,
    remarks LONGTEXT,
    created_at DATETIME NOT NULL,
    updated_at DATETIME NOT NULL,
    INDEX idx_access_point (access_point),
    INDEX idx_access_type (access_type),
    INDEX idx_department (department)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS surveillance (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    camera_name VARCHAR(255) NOT NULL,
    location VARCHAR(255) NOT NULL,
    camera_type VARCHAR(100) NOT NULL,
    manufacturer VARCHAR(255),
    model VARCHAR(255),
    resolution INT,
    installation_time DATETIME NOT NULL,
    last_maintenance_time DATETIME NOT NULL,
    next_maintenance_time DATETIME NOT NULL,
    recording_status VARCHAR(50) NOT NULL,
    storage_location VARCHAR(255),
    retention_days INT,
    maintenance_notes LONGTEXT,
    status VARCHAR(50) NOT NULL,
    remarks LONGTEXT,
    created_at DATETIME NOT NULL,
    updated_at DATETIME NOT NULL,
    INDEX idx_location (location),
    INDEX idx_status (status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS surveillance_log (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    camera_name VARCHAR(255) NOT NULL,
    location VARCHAR(255) NOT NULL,
    event_time DATETIME NOT NULL,
    event_type VARCHAR(100) NOT NULL,
    description LONGTEXT,
    severity VARCHAR(50) NOT NULL,
    status VARCHAR(50) NOT NULL,
    remarks LONGTEXT,
    created_at DATETIME NOT NULL,
    updated_at DATETIME NOT NULL,
    INDEX idx_camera_name (camera_name),
    INDEX idx_event_type (event_type),
    INDEX idx_severity (severity),
    INDEX idx_status (status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
