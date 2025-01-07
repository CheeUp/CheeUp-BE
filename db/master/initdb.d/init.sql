CREATE TABLE skills (
                        id BIGSERIAL PRIMARY KEY,
                        name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE jobs (
                      id BIGSERIAL PRIMARY KEY,
                      name VARCHAR(20) NOT NULL UNIQUE
);

CREATE TABLE majors (
                        id BIGSERIAL PRIMARY KEY,
                        category_id BIGINT NOT NULL,
                        name VARCHAR(20) NOT NULL UNIQUE
);

CREATE TABLE major_categories (
                                  id BIGSERIAL PRIMARY KEY,
                                  name VARCHAR(20) NOT NULL UNIQUE
);



CREATE TYPE MEMBER_ROLE AS ENUM ('GUEST', 'MEMBER', 'ADMIN');
CREATE TYPE FILE_TYPE AS ENUM ('IMAGE', 'VIDEO', 'ETC');
CREATE TYPE REPORT_STATUS AS ENUM ('PENDING', 'IN_PROGRESS', 'COMPLETED');
CREATE TYPE TIER AS ENUM ('BRONZE','SILVER', 'GOLD', 'PLATINUM', 'DIAMOND', 'RUBY');
CREATE TYPE ACTIVITY_TYPE AS ENUM ('글 작성');

CREATE TABLE members (
                         id BIGSERIAL PRIMARY KEY,
                         kakao_id VARCHAR(30) NOT NULL UNIQUE,
                         name VARCHAR(20) NOT NULL,
                         email VARCHAR(50) NOT NULL UNIQUE,
                         nickname VARCHAR(20) NOT NULL UNIQUE,
                         role MEMBER_ROLE NOT NULL DEFAULT 'GUEST',
                         github_url VARCHAR(255) NULL,
                         profile_image_url VARCHAR(255) NULL,
                         is_activated BOOLEAN NOT NULL DEFAULT TRUE,
                         experience INT NOT NULL DEFAULT 0,
                         tier TIER NOT NULL DEFAULT 'BRONZE',
                         created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         deleted_at TIMESTAMP NULL DEFAULT NULL
);

-- 경험치 획득 관련 테이블
CREATE TABLE member_activities (
                                   id BIGSERIAL PRIMARY KEY,
                                   member_id BIGINT NOT NULL,
                                   type ACTIVITY_TYPE NOT NULL,
                                   experience INT NOT NULL,
                                   created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                   deleted_at TIMESTAMP NULL DEFAULT NULL
);

-- 소속, 직무, 전화번호

CREATE TABLE member_skills (
                               id BIGSERIAL PRIMARY KEY,
                               member_id BIGINT NOT NULL,
                               skill_id BIGINT NOT NULL
);

CREATE TABLE inactive_members (
                                  id BIGSERIAL PRIMARY KEY,
                                  member_id BIGINT NOT NULL,
                                  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                  deleted_at TIMESTAMP NULL DEFAULT NULL
);

CREATE TABLE member_files (
                              id BIGSERIAL PRIMARY KEY,
                              member_id BIGINT NOT NULL,
                              name VARCHAR(255) NOT NULL,
                              size INT NOT NULL,
                              type file_type NOT NULL,
                              url varchar(255) NOT NULL,
                              created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                              updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                              deleted_at TIMESTAMP NULL DEFAULT NULL
);





CREATE TABLE boards (
                        id BIGSERIAL PRIMARY KEY,
                        name VARCHAR(20) NOT NULL UNIQUE,
                        is_anonymous BOOLEAN NOT NULL,
                        created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE favorite_boards (
                                 id BIGSERIAL PRIMARY KEY,
                                 member_id BIGINT NOT NULL,
                                 board_id BIGINT NOT NULL,
                                 created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- 조회수 관련 테이블 설계 고려
CREATE TABLE posts (
                       id BIGSERIAL PRIMARY KEY,
                       member_id BIGINT NOT NULL,
                       board_id BIGINT NOT NULL,
                       title VARCHAR(100) NOT NULL, -- 제목이 너무길어보임
                       content TEXT NOT NULL,
                       likes int DEFAULT 0 NOT NULL,
                       hit INT NOT NULL DEFAULT 0,
                       scrap INT NOT NULL DEFAULT 0,
                       is_anonymous BOOLEAN NOT NULL,
                       created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                       deleted_at TIMESTAMP NULL DEFAULT NULL
);

CREATE TABLE post_files (
                            id BIGSERIAL PRIMARY KEY,
                            post_id BIGINT NOT NULL,
                            name VARCHAR(255) NOT NULL,
                            size INT NOT NULL,
                            type FILE_TYPE NOT NULL,
                            url VARCHAR(255) NOT NULL,
                            created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                            updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                            deleted_at TIMESTAMP NULL DEFAULT NULL
);

CREATE TABLE post_likes (
                            id BIGSERIAL PRIMARY KEY,
                            member_id BIGINT NOT NULL,
                            post_id BIGINT NOT NULL,
                            created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE post_scraps (
                             id BIGSERIAL PRIMARY KEY,
                             member_id BIGINT NOT NULL,
                             post_id BIGINT NOT NULL,
                             created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE post_reports (
                              id BIGSERIAL PRIMARY KEY,
                              member_id BIGINT NOT NULL,
                              post_id BIGINT NOT NULL,
                              reason VARCHAR(255) NOT NULL,
                              status REPORT_STATUS NOT NULL DEFAULT 'PENDING',
                              created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                              updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE comments (
                          id BIGSERIAL PRIMARY KEY,
                          member_id BIGINT NOT NULL,
                          post_id BIGINT NOT NULL,
                          parent_id BIGINT NULL DEFAULT NULL,
                          content TEXT NOT NULL,
                          likes INT NOT NULL DEFAULT 0,
                          created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                          updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                          deleted_at TIMESTAMP NULL DEFAULT NULL
);

CREATE TABLE comment_likes (
                               id BIGSERIAL PRIMARY KEY,
                               member_id BIGINT NOT NULL,
                               comment_id BIGINT NOT NULL,
                               created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE comment_reports (
                                 id BIGSERIAL PRIMARY KEY,
                                 member_id BIGINT NOT NULL,
                                 comment_id BIGINT NOT NULL,
                                 reason VARCHAR(255) NOT NULL,
                                 status REPORT_STATUS NOT NULL DEFAULT 'PENDING',
                                 created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                 updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);


CREATE TYPE SKILL_LEVEL AS ENUM ('기초', '입문', '중급', '고급', '전문가');

CREATE TABLE portfolios (
                            id BIGSERIAL PRIMARY KEY,
                            member_id BIGINT NOT NULL,
                            job_id BIGINT NOT NULL,
                            title varchar(50) NOT NULL,
                            open BOOLEAN NOT NULL DEFAULT FALSE,
                            created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                            updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                            deleted_at TIMESTAMP NULL DEFAULT NULL
);

CREATE TABLE portfolio_skills (
                                  id BIGSERIAL PRIMARY KEY,
                                  portfolio_id BIGINT NOT NULL,
                                  skill_id BIGINT NOT NULL,
                                  level SKILL_LEVEL NOT NULL,
                                  description TEXT NULL
);

CREATE TABLE portfolio_authors (
                                   id BIGSERIAL PRIMARY KEY,
                                   portfolio_id BIGINT NOT NULL,
                                   name VARCHAR(10) NOT NULL,
                                   email VARCHAR(50) NOT NULL,
                                   introduction TEXT NULL,
                                   github_url VARCHAR(255) NULL,
                                   blog_url VARCHAR(255) NULL
);

CREATE TABLE portfolio_certificates (
                                        id BIGSERIAL PRIMARY KEY,
                                        portfolio_id BIGINT NOT NULL,
                                        name VARCHAR(50) NOT NULL,
                                        grade VARCHAR(30) NULL,
                                        earned_date DATE NOT NULL
);

-- 어학 성적
CREATE TABLE portfolio_languages (
                                     id BIGSERIAL PRIMARY KEY,
                                     portfolio_id BIGINT NOT NULL,
                                     name VARCHAR(50) NOT NULL,
                                     grade VARCHAR(30) NOT NULL,
                                     earned_date DATE NOT NULL
);

-- 수상내역
CREATE TABLE portfolio_awards (
                                  id BIGSERIAL PRIMARY KEY,
                                  portfolio_id BIGINT NOT NULL,
                                  name VARCHAR(50) NOT NULL,
                                  grade VARCHAR(30) NULL,
                                  earned_date DATE NOT NULL
);

-- 논문
CREATE TABLE portfolio_theses (
                                  id BIGSERIAL PRIMARY KEY,
                                  portfolio_id BIGINT NOT NULL,
                                  title VARCHAR(50) NOT NULL,
                                  subtitle VARCHAR(100) NOT NULL,
                                  summary TEXT NOT NULL,
                                  submission_target VARCHAR(30) NULL,
                                  submission_date DATE NOT NULL
);

CREATE TABLE portfolio_projects (
                                    id BIGSERIAL PRIMARY KEY,
                                    portfolio_id BIGINT NOT NULL,
                                    title VARCHAR(255) NOT NULL,
                                    outline VARCHAR(255) NOT NULL,
                                    role TEXT NOT NULL,
                                    description TEXT NOT NULL,
                                    result TEXT NOT NULL,
                                    github_url VARCHAR(255) NOT NULL
);

CREATE TABLE portfolio_project_skills (
                                          id BIGSERIAL PRIMARY KEY,
                                          portfolio_project_id BIGINT NOT NULL,
                                          skill_id BIGINT NOT NULL
);

CREATE TABLE portfolio_project_files (
                                         id BIGSERIAL PRIMARY KEY,
                                         portfolio_project_id BIGINT NOT NULL,
                                         name VARCHAR(255) NOT NULL,
                                         size INT NOT NULL,
                                         type FILE_TYPE NOT NULL,
                                         url VARCHAR(255) NOT NULL,
                                         created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                         updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                         deleted_at TIMESTAMP NULL DEFAULT NULL
);

CREATE TYPE HIGH_SCHOOL_MAJOR AS ENUM ('인문계', '자연계', '실업계', '예체능');
CREATE TYPE HIGH_SCHOOL_TYPE AS ENUM ('일반고', '특목고', '특성화고', '자율고');

CREATE TABLE portfolio_high_schools (
                                        id BIGSERIAL PRIMARY KEY,
                                        portfolio_id BIGINT NOT NULL,
                                        name VARCHAR(100) NOT NULL,
                                        major HIGH_SCHOOL_MAJOR NOT NULL, -- 인문계, 실업계, 예체능 등등
                                        type HIGH_SCHOOL_TYPE NOT NULL,
                                        start_date DATE NOT NULL,
                                        end_date DATE NOT NULL
);

CREATE TYPE UNIVERSITY_TYPE AS ENUM ('일반대학', '전문대학');
CREATE TYPE UNIVERSITY_CAMPUS AS ENUM ('본캠', '분캠');
CREATE TYPE UNIVERSITY_ENTRY AS ENUM ('입학', '편입');
CREATE TYPE UNIVERSITY_STATUS AS ENUM ('재학', '휴학', '졸업', '자퇴', '제적');

CREATE TABLE portfolio_universities (
                                        id BIGSERIAL PRIMARY KEY,
                                        portfolio_id BIGINT NOT NULL,
                                        name VARCHAR(100) NOT NULL,
                                        region VARCHAR(20) NOT NULL,
                                        type UNIVERSITY_TYPE NOT NULL,
                                        campus UNIVERSITY_CAMPUS NOT NULL,
                                        entry UNIVERSITY_ENTRY NOT NULL,
                                        status UNIVERSITY_STATUS NOT NULL,
                                        start_date DATE NOT NULL,
                                        end_date DATE NULL,
                                        leave_date DATE NULL
);

CREATE TYPE GRADUATE_DEGREE AS ENUM ('학사', '석사', '박사');

CREATE TABLE portfolio_graduates(
                                    id BIGSERIAL PRIMARY KEY,
                                    portfolio_id BIGINT NOT NULL,
                                    name VARCHAR(100) NOT NULL,
                                    region VARCHAR(20) NOT NULL,
                                    degree GRADUATE_DEGREE NOT NULL,
                                    type UNIVERSITY_TYPE NOT NULL,
                                    campus UNIVERSITY_CAMPUS NOT NULL,
                                    entry UNIVERSITY_ENTRY NOT NULL,
                                    status UNIVERSITY_STATUS NOT NULL,
                                    start_date DATE NOT NULL,
                                    end_date DATE NULL,
                                    leave_date DATE NULL
);

CREATE TYPE MAJOR_TYPE AS ENUM ('주전공', '부전공', '복수전공');
CREATE TYPE MAJOR_TIME AS ENUM ('주간', '야간');


CREATE TABLE portfolio_university_majors (
                                             id BIGSERIAL PRIMARY KEY,
                                             portfolio_university_id BIGINT NOT NULL,
                                             major_id BIGINT NOT NULL,
                                             type MAJOR_TYPE NOT NULL,
                                             time MAJOR_TIME NOT NULL,
                                             grade DECIMAL(3,2) NOT NULL,
                                             max_grade DECIMAL(3,2) NOT NULL
);

CREATE TABLE portfolio_graduate_majors(
                                          id BIGSERIAL PRIMARY KEY,
                                          portfolio_graduate_id BIGINT NOT NULL,
                                          major_id BIGINT NOT NULL,
                                          type MAJOR_TYPE NOT NULL,
                                          time MAJOR_TIME NOT NULL,
                                          grade DECIMAL(3,2) NOT NULL,
                                          max_grade DECIMAL(3,2) NOT NULL
);


CREATE TYPE CAREER_STATUS AS ENUM ('재직', '휴직', '퇴직');

CREATE TABLE portfolio_careers (
                                   id BIGSERIAL PRIMARY KEY,
                                   portfolio_id BIGINT NOT NULL,
                                   status CAREER_STATUS NOT NULL,
                                   company VARCHAR(20) NOT NULL,
                                   role VARCHAR(30) NOT NULL,
                                   description TEXT NULL,
                                   start_date DATE NOT NULL,
                                   end_date DATE NULL,
                                   leave_date DATE NULL
);

CREATE TYPE TRAINING_STATUS AS ENUM ('이수중', '이수 완료', '중도 포기');

CREATE TABLE portfolio_trainings (
                                     id BIGSERIAL PRIMARY KEY,
                                     portfolio_id BIGINT NOT NULL,
                                     company VARCHAR(20) NOT NULL,
                                     description TEXT NULL,
                                     content TEXT NULL,
                                     start_date DATE NOT NULL,
                                     end_date DATE NULL

);

CREATE TYPE MILITARY_TYPE AS ENUM ('비대상', '군필', '공익', '면제', '복무중');
CREATE TYPE MILITARY_BRANCH AS ENUM ('육군', '해군', '공군', '해병대');
CREATE TYPE MILITARY_SPECIALTY AS ENUM ('운전병', '통신병', '총기병', '의무병', '정비병');
CREATE TYPE MILITARY_RANK AS ENUM ('이병', '일병', '상병', '병장', '소위', '중위', '대위');
CREATE TYPE MILITARY_DISCHARGE AS ENUM ('의가사제대', '만기제대', '소집해제');


CREATE TABLE portfolio_militaries (
                                      id BIGSERIAL PRIMARY KEY,
                                      portfolio_id BIGINT NOT NULL,
                                      type MILITARY_TYPE NOT NULL,
                                      branch MILITARY_BRANCH NOT NULL,
                                      specialty MILITARY_SPECIALTY NOT NULL,
                                      rank MILITARY_RANK NOT NULL,
                                      discharge MILITARY_DISCHARGE NOT NULL,
                                      start_date DATE NOT NULL,
                                      end_date DATE NULL
);

CREATE TABLE portfolio_veterans (
                                    id BIGSERIAL PRIMARY KEY,
                                    portfolio_id BIGINT NOT NULL,
                                    status BOOLEAN NOT NULL, -- 대상 비대상
                                    relation VARCHAR(20) NULL,
                                    number VARCHAR(20) NULL,
                                    ratio SMALLINT NULL -- 0% 5% 10%
);

CREATE TYPE DISABILITY_TYPE AS ENUM ('간장애', '뇌전증', '호흡기');

CREATE TABLE portfolio_disabilities (
                                        id BIGSERIAL PRIMARY KEY,
                                        portfolio_id BIGINT NOT NULL,
                                        status BOOLEAN NOT NULL, -- 대상 비대상
                                        type DISABILITY_TYPE NULL, -- 간장애 뇌전증 장애 호흡기 장애 등
                                        grade SMALLINT NULL -- 1급 2급 등
);

CREATE TABLE portfolio_feedbacks (
                                     id BIGSERIAL PRIMARY KEY,
                                     portfolio_id BIGINT NOT NULL,
                                     member_id BIGINT NOT NULL,
                                     content TEXT NOT NULL,
                                     likes INT NOT NULL DEFAULT 0,
                                     created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                     updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                     deleted_at TIMESTAMP NULL DEFAULT NULL
);

CREATE TABLE portfolio_feedback_likes (
                                          id BIGSERIAL PRIMARY KEY,
                                          member_id BIGINT NOT NULL,
                                          portfolio_feedback_id BIGINT NOT NULL,
                                          created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);


CREATE TABLE job_notices (
                             id BIGSERIAL PRIMARY KEY,
                             member_id BIGINT NOT NULL,
                             title VARCHAR(30) NULL,
                             company VARCHAR(30) NOT NULL,
                             position VARCHAR(30) NOT NULL,
                             company_size SMALLINT NULL,
                             url VARCHAR(255) NOT NULL,
                             start_date DATE NOT NULL,
                             end_date DATE NOT NULL,
                             likes INT NOT NULL DEFAULT 0,
                             scrap INT NOT NULL DEFAULT 0,
                             hit INT NOT NULL DEFAULT 0,
                             created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                             updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                             deleted_at TIMESTAMP NULL DEFAULT NULL
);

CREATE TABLE job_notice_files (
                                  id BIGSERIAL PRIMARY KEY,
                                  job_notice_id BIGINT NOT NULL,
                                  origin_name VARCHAR(255) NOT NULL,
                                  size INT NOT NULL,
                                  type file_type NOT NULL,
                                  url varchar(255) NOT NULL,
                                  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                  updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                  deleted_at TIMESTAMP NULL DEFAULT NULL
);

CREATE TABLE job_notice_likes (
                                  id BIGSERIAL PRIMARY KEY,
                                  member_id BIGINT NOT NULL,
                                  job_notice_id BIGINT NOT NULL,
                                  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE job_notice_scraps (
                                   id BIGSERIAL PRIMARY KEY,
                                   member_id BIGINT NOT NULL,
                                   job_notice_id BIGINT NOT NULL,
                                   created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TYPE JOB_DESCRIPTION_TYPE AS ENUM('신입', '경력');

CREATE TABLE job_descriptions (
                                  id BIGSERIAL PRIMARY KEY,
                                  job_notice_id BIGINT NOT NULL,
                                  type JOB_DESCRIPTION_TYPE NOT NULL, -- 신입 경력
                                  title VARCHAR(100) NOT NULL,
                                  location VARCHAR(50) NULL,
                                  description TEXT NOT NULL,
                                  requirement TEXT NOT NULL,
                                  preferred_requirement TEXT NULL
);

CREATE TABLE job_description_skills (
                                        id BIGSERIAL PRIMARY KEY,
                                        job_description_id BIGINT NOT NULL,
                                        skill_id BIGINT NOT NULL
);