from fastapi import FastAPI, Request
from fastapi.templating import Jinja2Templates

app = FastAPI()
template = Jinja2Templates(directory="templates")

@app.get("/")
async def read_root(request: Request):
    return template.TemplateResponse("home.html", {"request": request})

@app.get("/about")
async def about():
    return {"message": "이것은 마이 메모 앱의 소개 페이지입니다."}