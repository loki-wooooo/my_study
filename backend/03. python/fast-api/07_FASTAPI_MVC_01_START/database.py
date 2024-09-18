from sqlalchemy import create_engine
from sqlalchemy.ext.declarative import declarative_base, MetaData
from sqlalchemy.orm import sessionmaker

DATABASE_URL = "postgresql+psycopg2://postgres:1234@220.220.220.18/postgres"  # 사용자의 데이터베이스 정보로 변경해야 합니다.
engine = create_engine(DATABASE_URL)

# 메타데이터 객체 생성, 스키마 설정
metadata_obj = MetaData(schema='sql_alchemy')

SessionLocal = sessionmaker(autocommit=False, autoflush=False, bind=engine)

Base = declarative_base(metadata=metadata_obj)