from fastapi import FastAPI, Request, Depends
from fastapi.templating import Jinja2Templates
from sqlalchemy.orm import Session
from sqlalchemy import create_engine, MetaData, Table, Column, Integer, String
from sqlalchemy.ext.declarative import declarative_base
from pydantic import BaseModel
from typing import Optional

app = FastAPI()
template = Jinja2Templates(directory="templates")

DATABASE_URL = "postgresql+psycopg2://postgres:1234@220.220.220.18/postgres"  # 사용자의 데이터베이스 정보로 변경해야 합니다.
engine = create_engine(DATABASE_URL)

# 메타데이터 객체 생성, 스키마 설정
metadata_obj = MetaData(schema='sql_alchemy')

Base = declarative_base(metadata=metadata_obj)

class Memo(Base):
    __tablename__ = "memo"
    id = Column(Integer, primary_key=True, index=True)
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

# 메모 생성
'''
    return ()
        - 괄호로 딕셔너리를 감싸고 있습니다.
        - 괄호는 여기서 불필요하지만, 문법적으로 유효합니다.
        - Python에서 괄호는 때때로 가독성을 위해 사용되거나 여러 줄의 표현식을 그룹화하는 데 사용됩니다.
'''
@app.post(path="/memos/")
async def create_memo(memo: MemoCreate, db: Session = Depends(get_db)):
    new_memo = Memo(title=memo.title, content=memo.content)
    db.add(new_memo)
    db.commit()
    db.refresh(new_memo)
    return ({"id": new_memo.id, "title": new_memo.title, "content": new_memo.content})

# 메모 조회
@app.get(path="/memos/")
async def list_memo(db: Session = Depends(get_db)):
    memos = db.query(Memo).all()
    return [{"id": memo.id, "title": memo.title, "content": memo.content} for memo in memos]

# 메모 수정
# 더티체킹 - 엔티티의 변경을 자동으로 감지하여 데이터베이스에 반영하는 메커니즘
@app.put(path="/memos/{memo_id}")
async def update_memo(memo_id: int, memo: MemoUpdate, db: Session = Depends(get_db)):
    db_memo = db.query(Memo).filter(Memo.id == memo_id).first()

    if db_memo is None:
        return ({"error": "Memo not found"})
    
    if memo.title is not None:
        db_memo.title = memo.title
    if memo.content is not None:
        db_memo.content = memo.content
    db.commit()
    db.refresh(db_memo)
    return ({"id": db_memo.id, "title": db_memo.title, "content": db_memo.content})

# 메모 삭제
@app.delete(path="/memos/{memo_id}")
async def delete_memo(memo_id: int, db: Session = Depends(get_db)):
    db_memo = db.query(Memo).filter(Memo.id == memo_id).first()

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