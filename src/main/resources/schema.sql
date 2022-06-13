--APIで登録する側
CREATE TABLE IF NOT EXISTS task (
    id INT auto_increment primary key,
    name VARCHAR(255) NOT NULL,
    file_id VARCHAR(MAX)
);

--csvで登録する側
CREATE TABLE IF NOT EXISTS task_detail (
    id INT auto_increment primary key,
    task_id INT NOT NULL,
    content VARCHAR(255)
);