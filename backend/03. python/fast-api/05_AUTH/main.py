from fastapi import FastAPI, Request, Depends, HTTPException
from starlette.middleware.sessions import SessionMiddleware 
from fastapi.security import HTTPBasic, HTTPAuthorizationCredentials

app = FastAPI()
app.add_middleware(SessionMiddleware, secret_key="your-secret-key")
security = HTTPBasic()

'''
    FastAPI와 Depends는 FastAPI 프레임워크의 핵심 클래스
    HttpException은 HTTP 예외를 발생 시키는데 사용
    HTTPBasic 과 HTTPBasicCredentials은 HTTP Basic 인증을 구현하는데 필요한 클래스
    
    * Depends
        - FastAPI의 Depends는 의존성 주입(Dependency Injection) 시스템의 핵심 기능입니다. 이 기능을 통해 코드의 재사용성을 높이고 애플리케이션의 구조를 개선할 수 있습니다
        - Depends의 주요 특징
            => 코드 재사용: 공통 로직을 의존성으로 정의하여 여러 엔드포인트에서 재사용할 수 있습니다.
            => 자동 실행: FastAPI는 요청이 들어올 때마다 의존성 함수를 자동으로 실행합니다.
            => 유연성: 함수, 클래스, 또는 호출 가능한 객체를 의존성으로 사용할 수 있습니다   
        - 참고 URL : https://www.perplexity.ai/search/fastapi-dependse-daehae-seolmy-hOFP4myoQ_KbWa.1Fc1SrQ

    * SessionMiddelWare를 사용하면 Session의 기능을 사용할 수 있음.     
       - secret_key는 세션 데이터를 암호화 하는데 사용, 안전하게 관리가 되어야함(*)   
'''

# HTTPAuthorizationCredentials -> base64 디코딩
def get_current_username(credentials: HTTPAuthorizationCredentials = Depends(security)):
    
    #username -> alice , password -> password가 아니면 401 예외처리
    if credentials.username != "alice" or credentials.password != "password":
        raise HTTPException(status_code=401, detail="Unauthorized")
    return credentials.username


# Depends(get_current_username) -> get_current_username 함수를 자동으로 주입함
@app.get("/users/me")
def read_current_user(username: str = Depends(get_current_username)):
    return {"username": username}

@app.post("/set/")
async def set_session(request: Request):
    request.session("username") = "john"
    return {"message": "Session value set"}

@app.get("/get/")
async def set_session(request: Request):
    username = request.session.get("username","Guest")
    return {"username": username}

@app.post("/login/")
async def login(request: Request, username: str, password: str):
    if username == "john" and password == "1234":
        request.session["username"] = username
        return {"message": "Successfully logged in"}
    else:
        raise HTTPException(status_code=401, detail="invalid credentials")

@app.get("/dashboard/")
async def dashboard(request: Request):
    username = request.session.get("username")
    
    '''
        * if not username:은 username이 None일 때뿐만 아니라, 빈 문자열('')이거나 다른 falsy 값일 때도 True가 됩니다.
        * Falsy
            - boolean값에서 false로 평가되는 값을 말한다.  (쉽게 말해 if문의 조건으로 넣었을 때 false로 변환되는 값) 이렇게 6가지의 falsy값은 꼭 기억해두자!
            - ex)
                if(false)
                if(null)
                if(undefined)
                if(0)
                if(NaN)
                if('')
    '''
    if not username:
        raise HTTPException(status_code=401, detail="Not authorized")
    return {"message": f"Welcome to the dashboard, {username}"}