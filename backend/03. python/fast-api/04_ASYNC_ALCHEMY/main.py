from fastapi import FastAPI, Depends
from sqlalchemy import Column, Integer, String, MetaData, func
from sqlalchemy.ext.asyncio import create_async_engine, AsyncSession
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy.orm import sessionmaker
from pydantic import BaseModel
from sqlalchemy.future import select
from contextlib import asynccontextmanager
from typing import Optional

'''
1. psycopg2: 이 드라이버는 가장 많이 사용되는 PostgreSQL 드라이버로, 동기식 코드에서 사용됩니다.
2. asyncpg: 이 드라이버는 비동기식 코드에서 사용할 수 있는 PostgreSQL 드라이버입니다. SQLAlchemy의 비동기 기능을 활용할 때 사용됩니다.
3. aiopg: 이 드라이버는 psycopg2를 기반으로 하여 비동기식 코드를 지원합니다. 하지만 asyncpg가 더 최신의 비동기 드라이버로 많이 사용됩니다
'''
# 비동기 데이터베이스 설정을 위한 문자열을 정의합니다. 이 문자열에는 사용자 이름, 비밀번호, 서버 주소, 데이터베이스 이름이 포함되어 있습니다.
# DATABASE_URL = "mysql+aiomysql://funcoding:funcoding@localhost/db_name"  # 사용자의 데이터베이스 정보로 변경해야 합니다.
DATABASE_URL = "postgresql+psycopg2://postgres:1234@220.220.220.18/postgres"

# SQLAlchemy의 비동기 엔진을 생성합니다. 
engine = create_async_engine(DATABASE_URL, echo=True)

# metadata 추가(db schema)
metadata_obj = MetaData(schema='sql_alchemy')

# 비동기 세션 생성을 위한 세션메이커를 정의합니다.
# 비동기 처리에서 많이 사용
AsyncSessionLocal = sessionmaker(
    autocommit=False, autoflush=False, bind=engine, class_=AsyncSession
)

# SQLAlchemy의 모델 기본 클래스를 선언합니다. 이 클래스를 상속받아 데이터베이스 테이블을 정의할 수 있습니다.
Base = declarative_base()

class User(Base):
    # 'users' 테이블을 정의합니다.
    __tablename__ = 'users'
    # 각 열(column)을 정의합니다. id는 기본 키(primary key)로 설정됩니다.
    id = Column(Integer, primary_key=True, index=True)
    username = Column(String(50), unique=True, index=True)  # 사용자 이름, 중복 불가능하고 인덱싱합니다.
    email = Column(String(120))  # 이메일 주소, 길이는 120자로 제한합니다.
    

# Pydantic 모델을 정의합니다. 이 모델은 클라이언트로부터 받은 데이터의 유효성을 검사하는 데 사용됩니다.
class UserCreate(BaseModel):
    username: str
    email: str

# 비동기 데이터베이스 세션을 생성하고 관리하는 의존성 함수를 정의합니다.
async def get_db():
    async with AsyncSessionLocal() as session:
        yield session
        await session.commit()


# 테이블의 초기화 과정
'''
    # 동기 방식
    # ddl create 실행
    Base.metadata.create_all(bind=engine)

    # 비동기 방식
    # DB 접속, Table 생성, framework가 끝날때 자기가 할 수 있는 작업을 처리함
    @asynccontextmanager -> 실행 컨텍스트를 관리하는 객체, 리소스 할당과 해제를 비동기적으로 관리

    @asynccontextmanager 란?
    - 비동기 코드에서 리소스의 획득과 해제를 자동으로 처리
'''
@asynccontextmanager
async def app_lifespan(app: FastAPI):
    # 애플리케이션 시작 시 실행될 로직
    async with engine.begin() as conn:
        await conn.run_sync(Base.metadata.create_all)
    yield
    # 애플리케이션 종료 시 실행될 로직 (필요한 경우)


# FastAPI 애플리케이션을 초기화합니다.
# application이 처음 초기화 할시, lifespan-> app_lifespan 함수를 호출함
app = FastAPI(lifespan=app_lifespan)

@app.get("/")
async def read_root():
    # 루트 경로에 접근했을 때 비동기적으로 메시지를 반환합니다.
    return {"message": "Hello, World!"}

# 사용자를 생성하는 POST API 엔드포인트를 추가합니다. 이번에는 비동기 방식을 사용합니다.
@app.post("/users/")
async def create_user(user: UserCreate, db: AsyncSession = Depends(get_db)):
    # Pydantic 모델을 사용하여 전달받은 데이터의 유효성을 검증하고, 새 User 인스턴스를 생성합니다.
    new_user = User(username=user.username, email=user.email)
    db.add(new_user)  # 생성된 User 인스턴스를 데이터베이스 세션에 추가합니다.
    await db.commit()  # 데이터베이스에 대한 변경사항을 비동기적으로 커밋합니다.
    await db.refresh(new_user)  # 데이터베이스로부터 새 User 인스턴스의 최신 정보를 비동기적으로 가져옵니다.
    # 새로 생성된 사용자의 정보를 반환합니다.
    return {"id": new_user.id, "username": new_user.username, "email": new_user.email}

@app.get("/users/{user_id}")
async def read_user(user_id: int, db: AsyncSession = Depends(get_db)):
    # 비동기 세션을 사용하여 데이터베이스 쿼리를 실행합니다.
    result = await db.execute(select(User).filter(User.id == user_id))
    db_user = result.scalars().first()
    if db_user is None:
        return {"error": "User not found"}
    return {"id": db_user.id, "username": db_user.username, "email": db_user.email}

# Pydantic 모델 정의
class UserUpdate(BaseModel):
    username: Optional[str] = None
    email: Optional[str] = None

# Update 부분을 비동기로 변환
@app.put("/users/{user_id}")
async def update_user(user_id: int, user: UserUpdate, db: AsyncSession = Depends(get_db)):
    # 비동기 쿼리 실행
    result = await db.execute(select(User).filter(User.id == user_id))
    db_user = result.scalars().first()

    if db_user is None:
        return {"error": "User not found"}
    
    # 사용자 정보 업데이트
    if user.username is not None:
        db_user.username = user.username
    if user.email is not None:
        db_user.email = user.email

    # 데이터베이스 커밋 및 객체 새로고침
    await db.commit()
    await db.refresh(db_user)
    
    return {"id": db_user.id, "username": db_user.username, "email": db_user.email}


@app.delete("/users/{user_id}")
async def delete_user(user_id: int, db: AsyncSession = Depends(get_db)):
    # 비동기 쿼리 실행하여 사용자 찾기
    result = await db.execute(select(User).filter(User.id == user_id))
    db_user = result.scalars().first()

    if db_user is None:
        return {"error": "사용자를 찾을 수 없습니다"}

    # 사용자 삭제 및 데이터베이스 커밋
    await db.delete(db_user)
    await db.commit()
    return {"message": "사용자가 성공적으로 삭제되었습니다"}

# count 함수 사용시 
@app.get("/count")
async def count_user(db: AsyncSession = Depends(get_db)):
     # 비동기 쿼리 실행하여 사용자 찾기
    result = await db.execute(select(func.count()).select_from(User))
    count = result.scalars().one()
    return {"count": count}
