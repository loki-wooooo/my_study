from fastapi import FastAPI, Depends
from sqlalchemy.orm import Session
from sqlalchemy import Column, Integer, String, create_engine, MetaData
from sqlalchemy.ext.declarative import declarative_base
from pydantic import BaseModel
from typing import Optional


DATABASE_URL = "postgresql+psycopg2://postgres:1234@220.220.220.18/postgres"  # 사용자의 데이터베이스 정보로 변경해야 합니다.


# echo를 true로 설정하면 command창에 실행된 sql문이 뜨게 됨
engine = create_engine(DATABASE_URL, echo = True)

# 메타데이터 객체 생성, 스키마 설정
metadata_obj = MetaData(schema='sql_alchemy')


Base = declarative_base()

class User(Base):
    __tablename__ = 'users'
    id = Column(Integer, primary_key=True, index=True)
    username = Column(String(50), unique=True, index=True)  # 사용자 이름, 중복 불가능하고 인덱싱합니다.
    email = Column(String(120))  # 이메일 주소, 길이는 120자로 제한합니다.

class UserCreate(BaseModel):
    username: str
    email: str

def get_db():
    db = Session(bind=engine)
    try:
        yield db
    finally:
        db.close()

Base.metadata.create_all(bind=engine)

app = FastAPI()

@app.get("/")
def read_root():
    return {"message": "Hello, World!"}

@app.post("/users/")
def create_user(user: UserCreate, db: Session = Depends(get_db)):
    new_user = User(username=user.username, email=user.email)
    db.add(new_user)
    db.commit() 
    db.refresh(new_user)
    return {"id": new_user.id, "username": new_user.username, "email": new_user.email}

@app.get("/users/{user_id}")
def read_user(user_id: int, db: Session = Depends(get_db)):
    db_user = db.query(User).filter(User.id == user_id).first()
    if db_user is None:
        return {"error": "User not found"}
    return {"id": db_user.id, "username": db_user.username, "email": db_user.email}

class UserUpdate(BaseModel):
    username: Optional[str] = None
    email: Optional[str] = None
    

@app.put("/users/{user_id}")
def update_user(user_id: int, user: UserUpdate, db: Session = Depends(get_db)):
    db_user = db.query(User).filter(User.id == user_id).first()
    if db_user is None:
        return {"error": "User not found"}
    
    if user.username is not None:
        db_user.username = user.username
    if user.email is not None:
        db_user.email = user.email

    db.commit()
    db.refresh(db_user)
    return {"id": db_user.id, "username": db_user.username, "email": db_user.email}

# Delete 부분
@app.delete("/users/{user_id}")
def delete_user(user_id: int, db: Session = Depends(get_db)):
    db_user = db.query(User).filter(User.id == user_id).first()
    if db_user is None:
        return {"error": "사용자를 찾을 수 없습니다"}
    db.delete(db_user)
    db.commit()
    return {"message": "사용자가 성공적으로 삭제되었습니다"}


# 파일명을 main.py로 저장하고 FastAPI 애플리케이션을 실행합니다.
# uvicorn main:app --reload 명령을 사용하여 서버를 시작합니다.