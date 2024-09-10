import asyncio

async def fun1():
    print("fun1() : start!!")
    await asyncio.sleep(2) # 비동기로 2초간 대기
    print("fun1() : end!!")

async def fun2():
    print("fun2() : start!!")
    await asyncio.sleep(1) # 비동기로 1초간 대기
    print("fun2() : end!!")



async def main():
    
    #Fun1, Fun2 동시에 실행
    await asyncio.gather(fun1(), fun2())

if __name__ == "__main__":
    asyncio.run(main())