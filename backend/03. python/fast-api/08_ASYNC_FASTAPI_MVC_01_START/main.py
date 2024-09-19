from fastapi import FastAPI, Request
from fastapi.templating import Jinja2Templates
from starlette.middleware.sessions import SessionMiddleware
from database import Base, engine
from controllers import router
from contextlib import asynccontextmanager

# 테이블을 자동으로 생성하는 부분(현재 동기)
#Base.metadata.create_all(bind=engine)

# 비동기로 처리
# FastAPI 실행 시 시작
@asynccontextmanager
async def app_lifespan(app: FastAPI):
    # 애플리케이션 시작 시 실행
    async with engine.begin() as conn:
        await conn.run_sync(Base.metadata.create_all)
    yield
    # 애플리케이션 종료 시 실행

# FastAPI 애플리케이션을 초기화 합니다.
# Swagger, ReDoc 문서 기능을 비활성화
app = FastAPI(lifespan=app_lifespan, docs_url=None, redoc_url=None)
app.add_middleware(SessionMiddleware, secret_key='your-secret-key')
app.include_router(router)

templates = Jinja2Templates(directory="templates")

@app.get('/')
async def read_root(request: Request):
    return templates.TemplateResponse('home.html', {'request': request})