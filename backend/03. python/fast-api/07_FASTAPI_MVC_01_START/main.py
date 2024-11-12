from fastapi import FastAPI, Request
from fastapi.templating import Jinja2Templates
from starlette.middleware.sessions import SessionMiddleware
from database import Base, engine

app = FastAPI()
app.add_middleware(SessionMiddleware, secret_key='your-secret-key')
Base.metadata.create_all(bind=engine)

templates = Jinja2Templates(directory="templates")

@app.get('/')
async def read_root(request: Request):
    return templates.TemplateResponse('home.html', {'request': request})