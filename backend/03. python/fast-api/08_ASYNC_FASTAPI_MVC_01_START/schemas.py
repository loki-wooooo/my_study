from pydantic import BaseModel
from typing import Optional

# 회원가입시 데이터 검증
class UserCreate(BaseModel):
    username: str
    password: str # 해시 전 패스워드를 받습니다.(plain text)
    email: str

# 회원 로그인시 데이터 검증
class UserLogin(BaseModel):
    username: str
    password: str # 해시 전 패스워드를 받습니다.

class MemoCreate(BaseModel):
    title: str
    content: str

class MemoUpdate(BaseModel):
    # optional - > default value
    title: Optional[str] = None
    content: Optional[str] = None