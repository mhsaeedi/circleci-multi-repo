CREATE SCHEMA de_indexation;

CREATE TABLE de_indexation.url_list_table
(
	id        SERIAL PRIMARY KEY,
	list_name TEXT,
	urls      TEXT
);


CREATE TABLE de_indexation.job_config
(
	id                                       SERIAL PRIMARY KEY,
	health_threshold                         FLOAT,
	increase_threshold                       FLOAT,
	google_min_item_count                    INTEGER,
	old_guard_trigger_threshold              INTEGER,
	old_guard_fail_threshold                 INTEGER,
	old_guard_fail_threshold_redirected_tags INTEGER,
	short_time_page_impression_threshold     INTEGER,
	long_time_page_impression_threshold      INTEGER,
	seo_landing_threshold                    INTEGER,
	min_shop_count_threshold                 INTEGER,
	under_the_protection_urls                INTEGER REFERENCES de_indexation.url_list_table


);

CREATE TABLE de_indexation.email_group
(
	id     SERIAL PRIMARY KEY,
	name   TEXT,
	emails TEXT
);

CREATE TABLE de_indexation.source_patch
(
	id             SERIAL PRIMARY KEY,
	creation_date  DATE,
	number_of_urls INTEGER
);

CREATE TABLE de_indexation.job
(
	id              SERIAL PRIMARY KEY,
	name            TEXT,
	reason          TEXT,
	job_config_id   INTEGER REFERENCES de_indexation.job_config,
	email_group_id  INTEGER REFERENCES de_indexation.email_group,
	source_patch_id INTEGER REFERENCES de_indexation.source_patch
);

CREATE TYPE de_indexation.BATCH_PROCESS_STATUS AS ENUM ('READY','PROCESSING','FAILED','SUCCEED');

CREATE TABLE de_indexation.batch_process
(
	id           SERIAL PRIMARY KEY,
	batch_number INTEGER,
	status       de_indexation.BATCH_PROCESS_STATUS,
	job_id       INTEGER REFERENCES de_indexation.batch_process
);

