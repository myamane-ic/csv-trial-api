--APIで登録する側
CREATE TABLE IF NOT EXISTS task (
    id INT IDENTITY(1,1) NOT NULL,
    name VARCHAR(255) NOT NULL,
    file_id VARCHAR(255) NOT NULL
);

--csvで登録する側
CREATE TABLE IF NOT EXISTS task_detail (
    id INT IDENTITY(1,1) NOT NULL,
    task_id INT NOT NULL,
    content VARCHAR(255) NOT NULL
);