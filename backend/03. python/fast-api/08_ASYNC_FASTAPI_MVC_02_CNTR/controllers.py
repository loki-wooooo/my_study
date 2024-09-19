from fastapi import APIRouter, Request, Depends, HTTPException
from fastapi.templating import Jinja2Templates
from models import User, Memo
from schemas import UserCreate, UserLogin, MemoCreate, MemoUpdate
from sqlalchemy.orm import Session
from sqlalchemy.ext.asyncio import AsyncSession
from sqlalchemy.future import select
from dependencies import get_db, get_password_hash, verify_password

router = APIRouter()
templates = Jinja2Templates(directory="templates")


@router.post('/sign-up/')
async def sing_up(sign_up_data: UserCreate, db: AsyncSession = Depends(get_db)):

    # 먼저 username이 있는지 확인
    #existing_user = db.query(User).filter(User.username == sign_up_data.username).first()

    result = await db.execute(select(User).where(User.username == sign_up_data.username))
    existing_user = result.scalars().first()

    if existing_user:
        raise HTTPException(status_code=400, detail="이미 동일 사용자가 가입 되어 있습니다.")

    hashed_password = get_password_hash(sign_up_data.password)
    new_user = User(username=sign_up_data.username, email=sign_up_data.email, hashed_password=hashed_password)
    db.add(new_user)

    try:
        await db.commit()
    except Exception as e:
        print(e)
        raise HTTPException(status_code=500, detail="회원 가입이 실패하였습니다. 기입한 내용을 확인해 주세요.")
    await db.refresh(new_user)
    return {"message": "회원가입이 성공 하였습니다."}

# 로그인
@router.post("/login/")
async def sing_up(request: Request, sign_in_data: UserLogin, db: AsyncSession = Depends(get_db)):
    #user = db.query(User).filter(User.username == sign_in_data.username).first()


    result = await db.execute(select(User).where(User.username == sign_in_data.username))
    user = result.scalars().first()

    if user and verify_password(sign_in_data.password, user.hashed_password):
        # 세션저장
        request.session['username'] = user.username
        return {"message": "Login in Successfully !"}
    else:
        raise HTTPException(status_code=401, detail="Invalid credentials")

# 로그아웃
@router.post("/logout")
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
@router.post(path="/memos/")
async def create_memo(request: Request, memo: MemoCreate, db: Session = Depends(get_db)):
    
    # step.1 사용자명이 세션에 없을때,
    username = request.session.get('username')
    if username is None:
        raise HTTPException(status_code=401, detail="Not Authorized !")
    
    # step.2 사용자가 db에 없을때
    #user = db.query(User).filter(User.username == username).first()
    result = await db.execute(select(User).where(User.username == username))
    user = result.scalars().first()
    if user is None:
        raise HTTPException(status_code=404, detail="User not Found !")

    # step.3 인증된 사용자
    new_memo = Memo(user_id = user.id, title=memo.title, content=memo.conten)
    #new_memo = Memo(title=memo.title, content=memo.content)

    # 직접 DB접근이 아니여서 "await" 제거
    db.add(new_memo)
    await db.commit()
    await db.refresh(new_memo)
    return new_memo

# 메모 조회
@router.get(path="/memos/")
async def list_memo(request:Request, db: Session = Depends(get_db)):
    # step.1 사용자명이 세션에 없을때,
    username = request.session.get('username')
    if username is None:
        raise HTTPException(status_code=401, detail="Not Authorized !")

    # step.2 사용자가 db에 없을때
    #user = db.query(User).filter(User.username == username).first()

    result = await db.execute(select(User).where(User.username == username))
    user = result.scalars().first()
    if user is None:
        raise HTTPException(status_code=404, detail="User not Found !")
    

    #memos = db.query(Memo).filter(Memo.user_id == user.id).all()
    result = await db.execute(select(Memo).where(Memo.user_id == user.id))
    memos = result.scalars().all()

    # return [{"id": memo.id, "title": memo.title, "content": memo.content} for memo in memos]
    return templates.TemplateResponse("memo.html", {"request": request, "memos": memos, "username": user.username})


# 메모 수정
# 더티체킹 - 엔티티의 변경을 자동으로 감지하여 데이터베이스에 반영하는 메커니즘
@router.put(path="/memos/{memo_id}")
async def update_memo(request:Request, memo_id: int, memo: MemoUpdate, db: Session = Depends(get_db)):

    # step.1 사용자명이 세션에 없을때,
    username = request.session.get('username')
    if username is None:
        raise HTTPException(status_code=401, detail="Not Authorized !")

    # step.2 사용자가 db에 없을때
    #user = db.query(User).filter(User.username == username).first()
    result = await db.execute(select(User).where(User.username == username))
    user = result.scalars().first()
    if user is None:
        raise HTTPException(status_code=404, detail="User not Found !")

    #db_memo = db.query(Memo).filter(Memo.user_id == user.id, Memo.id == memo_id).first()

    # where, filter 둘다 동일해서 조건절만 입력해서 사용하면 됨
    result = db.execute(select(Memo).where(Memo.user_id == user.id, Memo.id == memo_id))
    db_memo = result.scalars().first()


    if db_memo is None:
        return ({"error": "Memo not found"})
    
    if memo.title is not None:
        db_memo.title = memo.title
    if memo.content is not None:
        db_memo.content = memo.content
    
    await db.commit()
    await db.refresh(db_memo)
    
    return db_memo

# 메모 삭제
@router.delete(path="/memos/{memo_id}")
async def delete_memo(request:Request, memo_id: int, db: Session = Depends(get_db)):

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
@router.get("/")
async def read_root(request: Request):
    return templates.TemplateResponse("home.html", {"request": request})

@router.get("/about")
async def about():
    return {"message": "이것은 마이 메모 앱의 소개 페이지입니다."}