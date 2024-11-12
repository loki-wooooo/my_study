from fastapi import FastAPI, Request, Depends, HTTPException
from fastapi.templating import Jinja2Templates
from sqlalchemy.orm import Session
from sqlalchemy import create_engine, MetaData, Table, Column, Integer, String, ForeignKey
from sqlalchemy.ext.declarative import declarative_base
from pydantic import BaseModel
from typing import Optional

 # 암호화 기법 - 패스워드 저장 기법
from passlib.context import CryptContext

from starlette.middleware.sessions import SessionMiddleware


# bcrypt -> 패스워드를 변환할때 사용하는 가장 많이쓰이는 알고리즘(해싱기법)
# schemes -> 여러개 사용할 수 있음, 
# deprecated="auto"를 설정하면 CryptContext가 자동으로 기본 해시 알고리즘을 제외한 모든 알고리즘을 deprecated(사용 중지)로 표시합니다
#   - 다른 알고리즘이 아닌 명시된 알고리즘만 사용할 수 있음.

pwd_context = CryptContext(schemes=['bcrypt'], deprecated='auto')


# plain text를 이용한 암호를 알아 볼 수 없도록 변경
def get_password_hash(password):
    return pwd_context.hash(password)

# 데이터 베이스에 변환된 데이터를 비교
def verify_password(plain_password, hashed_password):
    return pwd_context.verify(plain_password, hashed_password)



'''
    1. 로그인이 된 후, 접속이 가능한 페이지 SessionMiddelware
        - 세션을 갖고 있어야 함

    2. 회원가입 시 등록한 패스워드를 어디에 저장할 것 인지?
        - plain text 를 해싱기법을 통해서, 알아볼 수 없는 데이터로 저장
        - 패스워드 입력시, 동일 해싱기법으로 변환 후, 데이터베이스의 데이터로 확인
'''
app = FastAPI()
app.add_middleware(SessionMiddleware, secret_key='your-secret-key')

template = Jinja2Templates(directory="templates")

DATABASE_URL = "postgresql+psycopg2://postgres:1234@220.220.220.18/postgres"  # 사용자의 데이터베이스 정보로 변경해야 합니다.
engine = create_engine(DATABASE_URL)

# 메타데이터 객체 생성, 스키마 설정
metadata_obj = MetaData(schema='sql_alchemy')

Base = declarative_base(metadata=metadata_obj)

class User(Base):
    __tablename__ = "users"
    id = Column(Integer, primary_key=True, index=True)
    username = Column(String(100), unique=True, index=True)
    email = Column(String(200))
    hashed_password = Column(String(512))


# 회원가입시 데이터 검증
class UserCreate(BaseModel):
    username: str
    password: str # 해시 전 패스워드를 받습니다.(plain text)
    email: str

# 회원 로그인시 데이터 검증
class UserLogin(BaseModel):
    username: str
    password: str # 해시 전 패스워드를 받습니다.


class Memo(Base):
    __tablename__ = "memo"
    id = Column(Integer, primary_key=True, index=True)
    
    # users table의 id를 참조기로 설정
    user_id = Column(Integer, ForeignKey("users.id"))
    title = Column(String(100))
    content = Column(String(1000))

class MemoCreate(BaseModel):
    title: str
    content: str

class MemoUpdate(BaseModel):
    # optional - > default value
    title: Optional[str] = None
    content: Optional[str] = None

def get_db():
    db = Session(bind=engine)
    try:
        yield db
    finally:
        db.close()

Base.metadata.create_all(bind=engine)

# 회원 가입
@app.post("/sign-up/")
async def sing_up(sign_up_data: UserCreate, db: Session = Depends(get_db)):
    hashed_password = get_password_hash(sign_up_data.password)
    new_user = User(username=sign_up_data.username, email=sign_up_data.email, hashed_password=hashed_password)
    db.add(new_user)
    db.commit()
    db.refresh(new_user)
    return {"message": "Account Created Successfully !", "user_id": new_user.id}

# 로그인
@app.post("/login/")
async def sing_up(request: Request, sign_in_data: UserLogin, db: Session = Depends(get_db)):
    user = db.query(User).filter(User.username == sign_in_data.username).first()
    if user and verify_password(sign_in_data.password, user.hashed_password):
        # 세션저장
        request.session['username'] = user.username
        return {"message": "Login in Successfully !"}
    else:
        raise HTTPException(status_code=401, detail="Invalid credentials")

# 로그아웃
@app.post("/logout")
async def logout(request: Request):
    
    # 값을 삭제
    request.session.pop("username", None)
    return {"message": "Logged out Successfully !"}



# 메모 생성
'''
    return ()
        - 괄호로 딕셔너리를 감싸고 있습니다.
        - 괄호는 여기서 불필요하지만, 문법적으로 유효합니다.
        - Python에서 괄호는 때때로 가독성을 위해 사용되거나 여러 줄의 표현식을 그룹화하는 데 사용됩니다.
'''
@app.post(path="/memos/")
async def create_memo(request: Request, memo: MemoCreate, db: Session = Depends(get_db)):
    
    # step.1 사용자명이 세션에 없을때,
    username = request.session.get('username')
    if username is None:
        raise HTTPException(status_code=401, detail="Not Authorized !")
    
    # step.2 사용자가 db에 없을때
    user = db.query(User).filter(User.username == username).first()
    if user is None:
        raise HTTPException(status_code=404, detail="User not Found !")

    # step.3 인증된 사용자
    new_memo = Memo(user_id = user.id, title=memo.title, content=memo.conten)
    #new_memo = Memo(title=memo.title, content=memo.content)

    db.add(new_memo)
    db.commit()
    db.refresh(new_memo)
    return new_memo

# 메모 조회
@app.get(path="/memos/")
async def list_memo(request:Request, db: Session = Depends(get_db)):
    # step.1 사용자명이 세션에 없을때,
    username = request.session.get('username')
    if username is None:
        raise HTTPException(status_code=401, detail="Not Authorized !")

    # step.2 사용자가 db에 없을때
    user = db.query(User).filter(User.username == username).first()
    if user is None:
        raise HTTPException(status_code=404, detail="User not Found !")
    

    memos = db.query(Memo).filter(Memo.user_id == user.id).all()
    # return [{"id": memo.id, "title": memo.title, "content": memo.content} for memo in memos]
    return template.TemplateResponse("memo.html", {"request": request, "memos": memos})


# 메모 수정
# 더티체킹 - 엔티티의 변경을 자동으로 감지하여 데이터베이스에 반영하는 메커니즘
@app.put(path="/memos/{memo_id}")
async def update_memo(request:Request, memo_id: int, memo: MemoUpdate, db: Session = Depends(get_db)):

    # step.1 사용자명이 세션에 없을때,
    username = request.session.get('username')
    if username is None:
        raise HTTPException(status_code=401, detail="Not Authorized !")

    # step.2 사용자가 db에 없을때
    user = db.query(User).filter(User.username == username).first()
    if user is None:
        raise HTTPException(status_code=404, detail="User not Found !")

    db_memo = db.query(Memo).filter(Memo.user_id == user.id, Memo.id == memo_id).first()

    if db_memo is None:
        return ({"error": "Memo not found"})
    
    if memo.title is not None:
        db_memo.title = memo.title
    if memo.content is not None:
        db_memo.content = memo.content
    db.commit()
    db.refresh(db_memo)
    return db_memo

# 메모 삭제
@app.delete(path="/memos/{memo_id}")
async def delete_memo(memo_id: int, db: Session = Depends(get_db)):

    # step.1 사용자명이 세션에 없을때,
    username = request.session.get('username')
    if username is None:
        raise HTTPException(status_code=401, detail="Not Authorized !")

    # step.2 사용자가 db에 없을때
    user = db.query(User).filter(User.username == username).first()
    if user is None:
        raise HTTPException(status_code=404, detail="User not Found !")
    
    '''
        from sqlalchemy import and_
        db.query(Memo).filter(and_(Memo.user_id == user.id, Memo.id == memo_id)).first()
    '''
    db_memo = db.query(Memo).filter(Memo.user_id == user.id, Memo.id == memo_id).first()

    if db_memo is None:
        return ({"error": "Memo not found"})
    

    db.delete(db_memo)
    db.commit()
    return ({"message": "Memo Deleted"})


# 기본 라우터
@app.get("/")
async def read_root(request: Request):
    return template.TemplateResponse("home.html", {"request": request})

@app.get("/about")
async def about():
    return {"message": "이것은 마이 메모 앱의 소개 페이지입니다."}