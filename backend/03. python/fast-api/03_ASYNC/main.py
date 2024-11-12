from fastapi import FastAPI
import asyncio

app = FastAPI()

# async -> 비동기를 사용할 수 있도록 정의
async def fetch_data():
    await asyncio.sleep(2) # 비동기로 2초간 대기
    return {"data": "some_data"}

@app.get("/")
async def read_root():
    data = await fetch_data()
    return {"message": "Hello, World!", "fetched_data": data}