from passlib.context import CryptContext
from database import AsyncSessionLocal

pwd_context = CryptContext(schemes=['bcrypt'], deprecated='auto')


# plain text를 이용한 암호를 알아 볼 수 없도록 변경
def get_password_hash(password):
    return pwd_context.hash(password)

# 데이터 베이스에 변환된 데이터를 비교
def verify_password(plain_password, hashed_password):
    return pwd_context.verify(plain_password, hashed_password)

# 비동기로 처리 필요
# link - https://www.perplexity.ai/search/def-get-db-db-session-bind-eng-P2X3o5TgTtO81AVypzBk7A
async def get_db():
    async with AsyncSessionLocal() as session:
        yield session
        await session.commit()