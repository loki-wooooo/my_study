# from fastapi import FastAPI, HTTPException, Request
# from fastapi.templating import Jinja2Templates #jinja2 template를 사용하기 위한 import
# from pydantic import BaseModel, Field
# from typing import Optional, List, Union, Query, Body

from fastapi import FastAPI, Request
from fastapi.templating import Jinja2Templates #jinja2 template를 사용하기 위한 import
from fastapi.staticfiles import StaticFiles
from typing import Optional, List, Union

# 실행 
# uvicorn main:app --reload


app = FastAPI()

#directory -> default dir : templates (생략가능)
templates = Jinja2Templates(directory="templates") 

#static mount(img, css, js)
app.mount("/static", StaticFiles(directory="static"), name="static")


# Request -> http 전문을 갖고있는 변수
@app.get("/")
def read_root(request: Request):
    return templates.TemplateResponse("index.html", {"request": request, "username": "John Doe"})

# username -> path variable
# @app.get("/user/{username}")
# def get_user(request: Request, username: str):
#     return templates.TemplateResponse("index.html", {"request": request, "username": username})

# query parameter
@app.get("/user")
def get_user(request: Request, username: str = "John"):
    return templates.TemplateResponse("index.html", {"request": request, "username": username})

@app.get("/safe")
def read_root_safe(request: Request):
    my_variable_with_html = "<h1> hello, FastAPI</h1>"
    return templates.TemplateResponse("index_with_safe.html", {"request": request, "my_variable_with_html": my_variable_with_html})

@app.get("/greet")
def greeting(request: Request, time_of_day:str):
    return templates.TemplateResponse("index.html", {"request": request, "time_of_day": time_of_day})

@app.get("/dynamic-items")
def get_items(request: Request, item_list: str = ""):
    items = item_list.split(",")
    return templates.TemplateResponse("index.html", {"request": request, "items": items})


@app.get("/inherit")
def template_inherit(request: Request):
    my_text = "FastAPI와 Jinja2를 이용한 예시 입니다."
    return templates.TemplateResponse("index.html", {"request": request, "text": my_text})


# class Item(BaseModel):
#     # (..., -> 필수값, None -> 옵션)
#     name: str = Field(..., title="Item Name", min_length=2, max_length=50)

#     #gt, lt -> 숫자 필드의 값 제한
#     price: float = Field(..., gt=0, description="price")
#     description: Optional[str] = Field(None, title="Description", max_length=2000)
#     is_offer:bool = None
#     tax: float = 0.1

#     # default -> 값이 없으면 default 값([]) 이 들어감
#     # alias => 데이터 전송시 JSON필드 이름을 Python변수명과 다르게 설정
#     tag: List[str] = Field(default=[], alias="item-tags")


# # 중첩모델
# class Image(BaseModel):
#     url: str
#     name: str

# class Item(BaseModel):
#     name: str
#     description: str
#     image: Image

# class Item(BaseModel):
#     name: str
#     tag: List[str]
#     #int, str 둘중 하나 사용할 수 있음
#     variant: Union[int, str]

# class Cat(BaseModel):
#     name: str


# class Dog(BaseModel):
#     name: str

# @app.get("/animal/", response_model=Union[Cat, Dog])
# async def get_animal(animal: str):
#     if animal == "cat":
#         return Cat(name="cat")
#     else:
#         return Cat(name="dog")


# class Item(BaseModel):
#     name: str
#     description: str = None
#     price: float

# def get_item_from_db(id):
#     # 매우 간단한 아이템 반환
#     return {
#         "name": "Simple Item"
#         , "description": "A simple item description"
#         , "price": 50.0
#         , "dis_price": 45.0
#     }


# @app.get("/")
# def read_root():
#     return {"message": "Hello, World!"}


# @app.get("/hello")
# def read_hello():
#     return {"message": "Hi, World!"}

# # 경로 매개변수
# @app.get("/items/{item_id}", response_model=Item)
# def read_item(item_id: int):
#     item = get_item_from_db(item_id)
#     return item

# # 쿼리 매개변수
# # skip:int = 0, limit:int = 20 -> 0, 20 default value
# @app.get("/items/")
# def read_items(skip:int = 0, limit:int = 20):
#     return {"skip": skip, "limit": limit}

# @app.get("/getdata/")
# def read_data(data:str = "funcoding"):
#     return {"data": data}

# # Dict -> 타입힌트 , dict -> 파이썬 내장
# @app.post("/items/")
# def create_item(item: Item):
#     return {"item": item}

# @app.put("/items/{item_id}")
# def update_item(item_id: int, item: dict):
#     return {"item_id": item_id, "updated_item": item}

# @app.delete("/items/{item_id}")
# def delete_item(item_id: int):
#     return {"message": "Item {item_id} has been deleted"}


# from fastapi.responses import JSONResponse, HTMLResponse, PlainTextResponse, RedirectResponse

# @app.get("/json", response_class=JSONResponse)
# def reed_root():
#     return {"msg": "this is json"}

# @app.get("/html", response_class=HTMLResponse)
# def reed_root():
#     return "<h1> this is html </h1>"

# @app.get("/text", response_class=PlainTextResponse)
# def reed_root():
#     return "This is Plain Text"

# @app.get("/redirect")
# def read_directory():
#     return RedirectResponse(url="/text")


# '''
#     Query(None, max_length=50) 
#         -> Pydantic model의 Field와 유사함
#     Query Parameter에 대한 정의
#         None -> Optional
#         max_length -> 최대 query 글자수
#         deprecated -> 지원은 하지만 언젠가는 삭제하기 위한 정의
#         description -> 자동문서화에(Redoc, swagger) 대한 정의
#         regex -> 정규표현식 패턴
# '''
# @app.get("/users/")
# def read_users(q: str = Query(None, max_length=50, deprecated=True, description="정보를 입력해 주세요")):
#     return {"q": q}


# # alias -> 서버 내부(클라이언트)에서 사용하는 변수명
# @app.get("/items/")
# def read_items(iternal_query: str = Query(None, alias="search")):
#     return {"iternal_query": iternal_query}

# '''
#     ... -> 해당 필드가 필수
#     None -> 해당 필드는 옵션(선택적 필드)
# '''
# @app.post("/items")
# def post_items(item: dict = Body(...)):
#     return {"item": item}


# @app.post("/advanced_items/")
# def post_advanced_items(item: dict = Body(
#     default = None,
#     example = {"key": "value"},
#     alias = "item_alias",
#     title = "Sample Item",
#     description = "This is a sample item",
#     deprecated = True
# )):
#     return {"item":item}


# @app.get("/items/{item_id}")
# def read_item(item_id: int):
#     try:
#         if item_id < 0:
#             return ValueError("음수는 허용 하지 않습니다.")
#     except:
#         raise HTTPException(status_code=500, detail=str(e))


