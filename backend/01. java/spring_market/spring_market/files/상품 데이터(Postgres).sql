CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

SELECT e.extname, n.nspname as schema_name
FROM pg_extension e
         LEFT JOIN pg_namespace n ON n.oid = e.extnamespace
WHERE e.extname = 'uuid-ossp';

-- 관리자 계정 생성
INSERT INTO spring_market.tb_user (
    USER_ID,
    USER_NAME,
    USER_PASSWORD,
    USER_EMAIL,
    IS_USE,
    CREATED_ON,
    CREATED_USER_ID,
    CREATED_USER_NAME,
    LAST_EDITED_ON,
    LAST_EDITED_USER_ID,
    LAST_EDITED_USER_NAME
) VALUES (
             'b7e5a5b3-5f4a-4c8b-9e18-5b1c8e2f3d4a',
             'admin',
             '$2a$10$z0wqR6EBo3p1ixDqGVxY0.YdPYYbv9uTe08YP2Qw95X647yr8J1fm',
             'admin@admin.com',
             true,
             CURRENT_TIMESTAMP,
             'SYSTEM_USER',
             'SYSTEM_USER',
             CURRENT_TIMESTAMP,
             'SYSTEM_USER',
             'SYSTEM_USER'
         );

-- 1. 카테고리 생성 (의류/가전 2단계 계층)
INSERT INTO spring_market.tb_category (
    CATEGORY_ID,
    CATEGORY_NAME,
    CATEGORY_LEVEL,
    CATEGORY_PARENT_ID,
    IS_USE,
    CREATED_ON,
    CREATED_USER_ID,
    CREATED_USER_NAME,
    LAST_EDITED_ON,
    LAST_EDITED_USER_ID,
    LAST_EDITED_USER_NAME
) VALUES
-- 1차 분류
(spring_market.uuid_generate_v4(), '패션의류', 1, NULL,
 true, CURRENT_TIMESTAMP, 'SYSTEM_USER', 'SYSTEM_USER',
 CURRENT_TIMESTAMP, 'SYSTEM_USER', 'SYSTEM_USER'),
(spring_market.uuid_generate_v4(), '가전제품', 1, NULL,
 true, CURRENT_TIMESTAMP, 'SYSTEM_USER', 'SYSTEM_USER',
 CURRENT_TIMESTAMP, 'SYSTEM_USER', 'SYSTEM_USER');

-- 2차 분류 (패션)
INSERT INTO spring_market.tb_category (
    CATEGORY_ID,
    CATEGORY_NAME,
    CATEGORY_LEVEL,
    CATEGORY_PARENT_ID,
    IS_USE,
    CREATED_ON,
    CREATED_USER_ID,
    CREATED_USER_NAME,
    LAST_EDITED_ON,
    LAST_EDITED_USER_ID,
    LAST_EDITED_USER_NAME
)
SELECT
    spring_market.uuid_generate_v4(),
    '남성의류',
    2,
    CATEGORY_ID,
    true,
    CURRENT_TIMESTAMP,
    'SYSTEM_USER',
    'SYSTEM_USER',
    CURRENT_TIMESTAMP,
    'SYSTEM_USER',
    'SYSTEM_USER'
FROM spring_market.tb_category WHERE CATEGORY_NAME = '패션의류';

INSERT INTO spring_market.tb_category (
    CATEGORY_ID,
    CATEGORY_NAME,
    CATEGORY_LEVEL,
    CATEGORY_PARENT_ID,
    IS_USE,
    CREATED_ON,
    CREATED_USER_ID,
    CREATED_USER_NAME,
    LAST_EDITED_ON,
    LAST_EDITED_USER_ID,
    LAST_EDITED_USER_NAME
)
SELECT
    spring_market.uuid_generate_v4(),
    '여성의류',
    2,
    CATEGORY_ID,
    true,
    CURRENT_TIMESTAMP,
    'SYSTEM_USER',
    'SYSTEM_USER',
    CURRENT_TIMESTAMP,
    'SYSTEM_USER',
    'SYSTEM_USER'
FROM spring_market.tb_category WHERE CATEGORY_NAME = '패션의류';

-- 2차 분류 (가전)
INSERT INTO spring_market.tb_category (
    CATEGORY_ID,
    CATEGORY_NAME,
    CATEGORY_LEVEL,
    CATEGORY_PARENT_ID,
    IS_USE,
    CREATED_ON,
    CREATED_USER_ID,
    CREATED_USER_NAME,
    LAST_EDITED_ON,
    LAST_EDITED_USER_ID,
    LAST_EDITED_USER_NAME
)
SELECT
    spring_market.uuid_generate_v4(),
    '주방가전',
    2,
    CATEGORY_ID,
    true,
    CURRENT_TIMESTAMP,
    'SYSTEM_USER',
    'SYSTEM_USER',
    CURRENT_TIMESTAMP,
    'SYSTEM_USER',
    'SYSTEM_USER'
FROM spring_market.tb_category WHERE CATEGORY_NAME = '가전제품';

INSERT INTO spring_market.tb_category (
    CATEGORY_ID,
    CATEGORY_NAME,
    CATEGORY_LEVEL,
    CATEGORY_PARENT_ID,
    IS_USE,
    CREATED_ON,
    CREATED_USER_ID,
    CREATED_USER_NAME,
    LAST_EDITED_ON,
    LAST_EDITED_USER_ID,
    LAST_EDITED_USER_NAME
)
SELECT
    spring_market.uuid_generate_v4(),
    '생활가전',
    2,
    CATEGORY_ID,
    true,
    CURRENT_TIMESTAMP,
    'SYSTEM_USER',
    'SYSTEM_USER',
    CURRENT_TIMESTAMP,
    'SYSTEM_USER',
    'SYSTEM_USER'
FROM spring_market.tb_category WHERE CATEGORY_NAME = '가전제품';

-- 3차 분류 (남성의류)
INSERT INTO spring_market.tb_category (
    CATEGORY_ID,
    CATEGORY_NAME,
    CATEGORY_LEVEL,
    CATEGORY_PARENT_ID,
    IS_USE,
    CREATED_ON,
    CREATED_USER_ID,
    CREATED_USER_NAME,
    LAST_EDITED_ON,
    LAST_EDITED_USER_ID,
    LAST_EDITED_USER_NAME
)
SELECT
    spring_market.uuid_generate_v4(),
    '티셔츠',
    3,
    CATEGORY_ID,
    true,
    CURRENT_TIMESTAMP,
    'SYSTEM_USER',
    'SYSTEM_USER',
    CURRENT_TIMESTAMP,
    'SYSTEM_USER',
    'SYSTEM_USER'
FROM spring_market.tb_category WHERE CATEGORY_NAME = '남성의류';

INSERT INTO spring_market.tb_category (
    CATEGORY_ID,
    CATEGORY_NAME,
    CATEGORY_LEVEL,
    CATEGORY_PARENT_ID,
    IS_USE,
    CREATED_ON,
    CREATED_USER_ID,
    CREATED_USER_NAME,
    LAST_EDITED_ON,
    LAST_EDITED_USER_ID,
    LAST_EDITED_USER_NAME
)
SELECT
    spring_market.uuid_generate_v4(),
    '청바지',
    3,
    CATEGORY_ID,
    true,
    CURRENT_TIMESTAMP,
    'SYSTEM_USER',
    'SYSTEM_USER',
    CURRENT_TIMESTAMP,
    'SYSTEM_USER',
    'SYSTEM_USER'
FROM spring_market.tb_category WHERE CATEGORY_NAME = '남성의류';

-- 3차 분류 (여성의류)
INSERT INTO spring_market.tb_category (
    CATEGORY_ID,
    CATEGORY_NAME,
    CATEGORY_LEVEL,
    CATEGORY_PARENT_ID,
    IS_USE,
    CREATED_ON,
    CREATED_USER_ID,
    CREATED_USER_NAME,
    LAST_EDITED_ON,
    LAST_EDITED_USER_ID,
    LAST_EDITED_USER_NAME
)
SELECT
    spring_market.uuid_generate_v4(),
    '원피스',
    3,
    CATEGORY_ID,
    true,
    CURRENT_TIMESTAMP,
    'SYSTEM_USER',
    'SYSTEM_USER',
    CURRENT_TIMESTAMP,
    'SYSTEM_USER',
    'SYSTEM_USER'
FROM spring_market.tb_category WHERE CATEGORY_NAME = '여성의류';

INSERT INTO spring_market.tb_category (
    CATEGORY_ID,
    CATEGORY_NAME,
    CATEGORY_LEVEL,
    CATEGORY_PARENT_ID,
    IS_USE,
    CREATED_ON,
    CREATED_USER_ID,
    CREATED_USER_NAME,
    LAST_EDITED_ON,
    LAST_EDITED_USER_ID,
    LAST_EDITED_USER_NAME
)
SELECT
    spring_market.uuid_generate_v4(),
    '스커트',
    3,
    CATEGORY_ID,
    true,
    CURRENT_TIMESTAMP,
    'SYSTEM_USER',
    'SYSTEM_USER',
    CURRENT_TIMESTAMP,
    'SYSTEM_USER',
    'SYSTEM_USER'
FROM spring_market.tb_category WHERE CATEGORY_NAME = '여성의류';

-- 3차 분류 (주방가전)
INSERT INTO spring_market.tb_category (
    CATEGORY_ID,
    CATEGORY_NAME,
    CATEGORY_LEVEL,
    CATEGORY_PARENT_ID,
    IS_USE,
    CREATED_ON,
    CREATED_USER_ID,
    CREATED_USER_NAME,
    LAST_EDITED_ON,
    LAST_EDITED_USER_ID,
    LAST_EDITED_USER_NAME
)
SELECT
    spring_market.uuid_generate_v4(),
    '냉장고',
    3,
    CATEGORY_ID,
    true,
    CURRENT_TIMESTAMP,
    'SYSTEM_USER',
    'SYSTEM_USER',
    CURRENT_TIMESTAMP,
    'SYSTEM_USER',
    'SYSTEM_USER'
FROM spring_market.tb_category WHERE CATEGORY_NAME = '주방가전';

INSERT INTO spring_market.tb_category (
    CATEGORY_ID,
    CATEGORY_NAME,
    CATEGORY_LEVEL,
    CATEGORY_PARENT_ID,
    IS_USE,
    CREATED_ON,
    CREATED_USER_ID,
    CREATED_USER_NAME,
    LAST_EDITED_ON,
    LAST_EDITED_USER_ID,
    LAST_EDITED_USER_NAME
)
SELECT
    spring_market.uuid_generate_v4(),
    '전자레인지',
    3,
    CATEGORY_ID,
    true,
    CURRENT_TIMESTAMP,
    'SYSTEM_USER',
    'SYSTEM_USER',
    CURRENT_TIMESTAMP,
    'SYSTEM_USER',
    'SYSTEM_USER'
FROM spring_market.tb_category WHERE CATEGORY_NAME = '주방가전';

DO
$do$
    DECLARE
product_id UUID;
        category_lv3_id UUID;
        opt_group_id UUID;
        add_prod_id UUID;
        opt_group_name TEXT;
        opt_value TEXT;
        i INT;
        j INT;
BEGIN
FOR i IN 1..100000 LOOP
                product_id := spring_market.uuid_generate_v4();

INSERT INTO spring_market.tb_product (
    PRODUCT_ID,
    CATEGORY_ID,
    PRODUCT_NAME,
    PRODUCT_CONTENT,
    PRODUCT_PRICE,
    PRODUCT_STOCK,
    IS_USE,
    CREATED_ON,
    CREATED_USER_ID,
    CREATED_USER_NAME,
    LAST_EDITED_ON,
    LAST_EDITED_USER_ID,
    LAST_EDITED_USER_NAME
)
VALUES (
           product_id,
           (SELECT CATEGORY_ID FROM spring_market.tb_category WHERE CATEGORY_LEVEL = 3 ORDER BY RANDOM() LIMIT 1),
    '상품_' || i,
    '상품 설명 ' || i,
       (RANDOM() * 100000 + 10000)::NUMERIC(12,2),
                           (RANDOM() * 100)::INT,
                           true,
                           CURRENT_TIMESTAMP,
                           'SYSTEM_USER',
                           'SYSTEM_USER',
                           CURRENT_TIMESTAMP,
                           'SYSTEM_USER',
                           'SYSTEM_USER'
                       );

CASE (i % 4)
                    WHEN 0 THEN
                        CONTINUE;
WHEN 1 THEN
                        FOR j IN 1..2 LOOP
                                add_prod_id := spring_market.uuid_generate_v4();
INSERT INTO spring_market.tb_additional_product (
    ADDITIONAL_PRODUCT_ID,
    PRODUCT_ID,
    ADDITIONAL_PRODUCT_NAME,
    ADDITIONAL_PRODUCT_PRICE,
    ADDITIONAL_PRODUCT_STOCK,
    IS_USE,
    CREATED_ON,
    CREATED_USER_ID,
    CREATED_USER_NAME,
    LAST_EDITED_ON,
    LAST_EDITED_USER_ID,
    LAST_EDITED_USER_NAME
)
VALUES (
           add_prod_id,
           product_id,
           '추가상품_' || j,
           (RANDOM() * 50000)::NUMERIC(12,2),
           (RANDOM() * 50)::INT,
           true,
           CURRENT_TIMESTAMP,
           'SYSTEM_USER',
           'SYSTEM_USER',
           CURRENT_TIMESTAMP,
           'SYSTEM_USER',
           'SYSTEM_USER'
       );
END LOOP;
WHEN 2 THEN
                        FOR opt_group_name IN (
                            SELECT unnest(ARRAY['색상', '사이즈'])
                        ) LOOP
                                opt_group_id := spring_market.uuid_generate_v4();
INSERT INTO spring_market.tb_product_option_group (
    PRODUCT_OPTION_GROUP_ID,
    PRODUCT_ID,
    PRODUCT_OPTION_GROUP_NAME,
    IS_USE,
    CREATED_ON,
    CREATED_USER_ID,
    CREATED_USER_NAME,
    LAST_EDITED_ON,
    LAST_EDITED_USER_ID,
    LAST_EDITED_USER_NAME
)
VALUES (
           opt_group_id,
           product_id,
           opt_group_name,
           true,
           CURRENT_TIMESTAMP,
           'SYSTEM_USER',
           'SYSTEM_USER',
           CURRENT_TIMESTAMP,
           'SYSTEM_USER',
           'SYSTEM_USER'
       );

FOR opt_value IN
SELECT unnest(
               CASE opt_group_name
                   WHEN '색상' THEN ARRAY['빨강','파랑','노랑']::text[]
                   ELSE ARRAY['S','M','L']::text[]
                   END
       )
           LOOP
    INSERT INTO spring_market.tb_product_option (
    PRODUCT_OPTION_ID,
    PRODUCT_OPTION_GROUP_ID,
    PRODUCT_OPTION_VALUE,
    PRODUCT_OPTION_PRICE,
    PRODUCT_OPTION_STOCK,
    IS_USE,
    CREATED_ON,
    CREATED_USER_ID,
    CREATED_USER_NAME,
    LAST_EDITED_ON,
    LAST_EDITED_USER_ID,
    LAST_EDITED_USER_NAME
)
VALUES (
    spring_market.uuid_generate_v4(),
    opt_group_id,
    opt_value,
    CASE WHEN opt_group_name = '색상' THEN (RANDOM() * 10000)::NUMERIC(12,2) ELSE 0 END,
    (RANDOM() * 50)::INT,
    true,
    CURRENT_TIMESTAMP,
    'SYSTEM_USER',
    'SYSTEM_USER',
    CURRENT_TIMESTAMP,
    'SYSTEM_USER',
    'SYSTEM_USER'
    );
END LOOP;
END LOOP;
ELSE
                        -- 옵션과 추가상품 모두 있는 케이스
                        FOR opt_group_name IN (
                            SELECT unnest(ARRAY['색상', '사이즈'])
                        ) LOOP
                                opt_group_id := spring_market.uuid_generate_v4();
INSERT INTO spring_market.tb_product_option_group (
    PRODUCT_OPTION_GROUP_ID,
    PRODUCT_ID,
    PRODUCT_OPTION_GROUP_NAME,
    IS_USE,
    CREATED_ON,
    CREATED_USER_ID,
    CREATED_USER_NAME,
    LAST_EDITED_ON,
    LAST_EDITED_USER_ID,
    LAST_EDITED_USER_NAME
)
VALUES (
           opt_group_id,
           product_id,
           opt_group_name,
           true,
           CURRENT_TIMESTAMP,
           'SYSTEM_USER',
           'SYSTEM_USER',
           CURRENT_TIMESTAMP,
           'SYSTEM_USER',
           'SYSTEM_USER'
       );

FOR opt_value IN
SELECT unnest(
               CASE opt_group_name
                   WHEN '색상' THEN ARRAY['빨강','파랑','노랑']::text[]
                   ELSE ARRAY['S','M','L']::text[]
                   END
       )
           LOOP
    INSERT INTO spring_market.tb_product_option (
    PRODUCT_OPTION_ID,
    PRODUCT_OPTION_GROUP_ID,
    PRODUCT_OPTION_VALUE,
    PRODUCT_OPTION_PRICE,
    PRODUCT_OPTION_STOCK,
    IS_USE,
    CREATED_ON,
    CREATED_USER_ID,
    CREATED_USER_NAME,
    LAST_EDITED_ON,
    LAST_EDITED_USER_ID,
    LAST_EDITED_USER_NAME
)
VALUES (
    spring_market.uuid_generate_v4(),
    opt_group_id,
    opt_value,
    CASE WHEN opt_group_name = '색상' THEN (RANDOM() * 10000)::NUMERIC(12,2) ELSE 0 END,
    (RANDOM() * 50)::INT,
    true,
    CURRENT_TIMESTAMP,
    'SYSTEM_USER',
    'SYSTEM_USER',
    CURRENT_TIMESTAMP,
    'SYSTEM_USER',
    'SYSTEM_USER'
    );
END LOOP;
END LOOP;

FOR j IN 1..2 LOOP
                                add_prod_id := spring_market.uuid_generate_v4();
INSERT INTO spring_market.tb_additional_product (
    ADDITIONAL_PRODUCT_ID,
    PRODUCT_ID,
    ADDITIONAL_PRODUCT_NAME,
    ADDITIONAL_PRODUCT_PRICE,
    ADDITIONAL_PRODUCT_STOCK,
    IS_USE,
    CREATED_ON,
    CREATED_USER_ID,
    CREATED_USER_NAME,
    LAST_EDITED_ON,
    LAST_EDITED_USER_ID,
    LAST_EDITED_USER_NAME
)
VALUES (
           add_prod_id,
           product_id,
           '추가상품_' || j,
           (RANDOM() * 50000)::NUMERIC(12,2),
           (RANDOM() * 50)::INT,
           true,
           CURRENT_TIMESTAMP,
           'SYSTEM_USER',
           'SYSTEM_USER',
           CURRENT_TIMESTAMP,
           'SYSTEM_USER',
           'SYSTEM_USER'
       );
END LOOP;
END CASE;
END LOOP;
END;
$do$;
